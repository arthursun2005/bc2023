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

const N = 64
const M = 10

// const MAPS = "maptestsmall,AllElements,DefaultMap,Horizontal,SmallElements,Vertical,Woh"
const SPRINT1 = "ArtistRendition,BatSignal,BowAndArrow,Cat,Clown,Diagonal,Eyelands,Frog,Grievance,Hah,Jail,KingdomRush,Minefield,Movepls,Orbit,Pathfind,Pit,Pizza,Quiet,Rectangle,Scatter,Sun,Tacocat"
const MAPS = "Cat,Pit,Tacocat"

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
	cmd := fmt.Sprintf("./gradlew run --offline -Pmaps=%s -PteamA=%s -PteamB=%s | grep wins", MAPS, a, b)
	out, err := exec.Command("bash", "-c", cmd).Output()
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
	readline.PcItem("best"),
	readline.PcItem("save"),
	readline.PcItem("load"),
	readline.PcItem("exit",
		readline.PcItem("now"),
		readline.PcItem("cancel"),
		readline.PcItem("[seconds]"),
	),
	readline.PcItem("watch",
		readline.PcItem("enable"),
		readline.PcItem("disable"),
		readline.PcItem("[seconds]"),
	),
)

func main() {
	if len(os.Args) > 1 {
		bots := os.Args[1:]
		wins := make([]int64, len(bots))
		var wg sync.WaitGroup
		guard := make(chan struct{}, 3)
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
			fmt.Printf("%16s: %3d wins\n", bots[i], w)
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

	eCall := time.Now()
	until := time.Duration(0)
	rate := time.Duration(5) * time.Second
	ticker := time.NewTicker(rate)
	watching := true
	exited := false

	field := NewField(N, M)
	iter := 0
	go func() {
		log.Print("started main loop")
		for {
			if until > time.Duration(0) && time.Since(eCall) > until {
				break
			}
			iter += 1
			for i := range field.Rings {
				field.Rings[i].Score = 0.0
				for j := 0; j < M; j++ {
					field.Rings[i].Score += field.Rings[i].Data[j]
				}
			}
			field.Step()
		}
		log.Print("exited main loop")
		exited = true
	}()

	summary := func(printf func(string, ...any)) {
		printf("iter = %d; best score = %.3f\n", iter, field.Best.Score)
	}

	go func() {
		for range ticker.C {
			if watching && !exited {
				summary(log.Printf)
			}
		}
	}()

	w := rl.Stderr()
	log.SetOutput(w)

main:
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
			summary(func(f string, x ...any) { fmt.Printf(f, x...) })

		case line == "best":
			for i := 0; i < M; i++ {
				fmt.Printf("%.3f ", field.Best.Data[i])
			}
			fmt.Println()

		case line == "save":
			fmt.Printf("lol\n")

		case line == "load":
			fmt.Printf("lol\n")

		case line == "exit":
			fmt.Printf("exiting in %s\n", until)

		case strings.HasPrefix(line, "exit"):
			line = strings.TrimSpace(line[4:])
			if line == "now" {
				break main
			} else if line == "cancel" {
				until = time.Duration(0)
				fmt.Printf("exit duration resetted to %s\n", until)
			} else {
				u, err := strconv.ParseInt(line, 10, 64)
				if err != nil {
					fmt.Printf("%s\n", err)
					break
				}
				eCall = time.Now()
				until = time.Duration(u) * time.Second
			}

		case line == "watch":
			if watching {
				fmt.Printf("current watch rate: %s\n", rate)
			} else {
				fmt.Printf("watching disabled\n")
			}

		case strings.HasPrefix(line, "watch"):
			line = strings.TrimSpace(line[5:])
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
				fmt.Printf("watch rate set to: %s\n", rate)
				if !watching {
					fmt.Printf("watching currently disabled!\n")
				}
			}

		default:
			fmt.Printf("%s: ???\n", line)
		}
	}
}
