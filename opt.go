package main

import (
	"fmt"
	"io"
	"log"
	"os"
	"os/exec"
	"strconv"
	"strings"
	"sync"
	"sync/atomic"
	"time"

	"github.com/chzyer/readline"
)

const MAPS = "maptestsmall,AllElements,DefaultMap,Horizontal,SmallElements,Vertical,Woh"

func countWins(out string) (int, int) {
	A := 0
	B := 0
	for {
		idx := strings.Index(out, "wins")
		if idx == -1 {
			break
		}
		if out[idx-3] == 'A' {
			A++
		} else if out[idx-3] == 'B' {
			B++
		}
		out = out[idx+1:]
	}
	return A, B
}

func play(a, b string) (int, int) {
	start := time.Now()
	out, err := exec.Command("./gradlew", "run", "--offline", fmt.Sprintf("-Pmaps=%s", MAPS), fmt.Sprintf("-PteamA=%s", a), fmt.Sprintf("-PteamB=%s", b)).Output()
	if err != nil {
		log.Print(err)
		return 0, 0
	}
	log.Printf("play %s vs %s took %s", a, b, time.Since(start))
	return countWins(string(out))
}

var completer = readline.NewPrefixCompleter(
	readline.PcItem("help"),
	readline.PcItem("score"),
	readline.PcItem("save"),
	readline.PcItem("rate",
		readline.PcItem("disable"),
		readline.PcItem("[newrate]"),
	),
)

func main() {
	if len(os.Args) > 1 {
		bots := os.Args[1:]
		wins := make([]int64, len(bots))
		var wg sync.WaitGroup
		guard := make(chan struct{}, 5)
		for i, a := range bots {
			for j, b := range bots {
				if i == j {
					continue
				}
				guard <- struct{}{}
				wg.Add(1)
				go func(i, j int, a, b string) {
					defer wg.Done()
					defer func() { <-guard }()
					A, B := play(a, b)
					atomic.AddInt64(&wins[i], int64(A))
					atomic.AddInt64(&wins[j], int64(B))
					fmt.Printf("%16s vs %16s:   %2d : %2d\n", a, b, A, B)
				}(i, j, a, b)
			}
		}
		wg.Wait()
		for i, w := range wins {
			fmt.Printf("%16s: %3d win\n", bots[i], w)
		}
		return
	}

	rl, err := readline.NewEx(&readline.Config{
		Prompt:            "\033[35;1m>\033[0m ",
		InterruptPrompt:   "^C",
		AutoComplete:      completer,
		EOFPrompt:         "exit",
		HistorySearchFold: true,
	})
	if err != nil {
		log.Panic(err)
	}
	defer rl.Close()

	field := NewField(10, 10)
	go func() {
		iter := 0
		for {
			iter += 1
			for i := range field.Rings {
				field.Rings[i].Score = 0.0
				for j := 0; j < 10; j++ {
					field.Rings[i].Score += field.Rings[i].Data[j]
				}
			}
			field.Step()
		}
	}()

	rate := time.Duration(5) * time.Second
	ticker := time.NewTicker(rate)
	watching := true

	go func() {
		for range ticker.C {
			if watching {
				log.Printf("best score = %.3f", field.Best.Score)
			}
		}
	}()

	w := rl.Stderr()
	log.SetOutput(w)

	for {
		line, err := rl.Readline()
		if err == readline.ErrInterrupt {
			continue
		} else if err == io.EOF {
			break
		}
		line = strings.TrimSpace(line)
		if len(line) == 0 {
			continue
		}
		switch {
		case line == "help":
			io.WriteString(w, "commands:\n")
			io.WriteString(w, completer.Tree("    "))

		case line == "score":
			fmt.Printf("best score = %.3f\n", field.Best.Score)

		case line == "save":
			fmt.Printf("lol\n")

		case line == "rate":
			if watching {
				fmt.Printf("current watch rate: %s\n", rate)
			} else {
				fmt.Printf("watching disabled\n")
			}

		case strings.HasPrefix(line, "rate"):
			line = strings.TrimSpace(line[4:])
			if line == "disable" {
				watching = false
				fmt.Printf("watching disabled\n")
			} else if line == "enable" {
				watching = true
				fmt.Printf("watching enabled\n")
			} else {
				u, err := strconv.ParseInt(line, 10, 64)
				if err != nil {
					fmt.Printf("%s\n", err)
					break
				}
				if u <= 0 {
					fmt.Printf("duration <= 0\n")
					break
				}
				rate = time.Duration(u) * time.Second
				ticker.Reset(rate)
			}

		default:
			fmt.Printf("???: %s\n", line)
		}
	}
}
