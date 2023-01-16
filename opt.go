package main

import (
    "log"
    "fmt"
    "strings"
    "time"
    "sync"
    "sync/atomic"
    "os"
    "os/exec"
)

const MAPS = "maptestsmall,AllElements,DefaultMap,Horizontal,SmallElements,Vertical"

func countWins(out string) (int, int) {
    A := 0
    B := 0
    for true {
        idx := strings.Index(out, "wins")
        if idx == -1 {
            break
        }
        if out[idx - 3] == 'A' {
            A++
        } else if out[idx - 3] == 'B' {
            B++
        }
        out = out[idx + 1:]
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
    log.Printf("play %s vs %s took %s", a, b, time.Now().Sub(start))
    return countWins(string(out))
}

func main() {
    if len(os.Args) > 1 {
        bots := os.Args[1:]
        wins := make([]int64, len(bots))
        var wg sync.WaitGroup
        guard := make(chan struct{}, 5)
        for i, a := range bots {
            for j, b := range bots {
                if i == j { continue }
                guard <- struct{}{}
                wg.Add(1)
                go func(i, j int, a, b string) {
                    defer wg.Done()
                    defer func() { <-guard } ()
                    A, B := play(a, b)
                    atomic.AddInt64(&wins[i], int64(A))
                    atomic.AddInt64(&wins[j], int64(B))
                    fmt.Printf("%16s vs %16s:   %2d : %2d\n", a, b, A, B)
                } (i, j, a, b)
            }
        }
        wg.Wait()
        for i, w := range wins {
            fmt.Printf("%16s: %3d wins", bots[i], w)
        }
        return
    }
    field := NewField(10, 10)
    iter := 0
    for true {
        iter += 1
        for i := range field.Rings {
            field.Rings[i].Score = field.Rings[i].Data[0]
        }
        field.Step()
        log.Println(iter, field.Best.Score)
    }
}
