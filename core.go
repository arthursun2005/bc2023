package main

import (
	"container/heap"
	"math"
	"math/rand"
	"sort"
)

const RESET_PROB = 0.00001
const MUT_RESET_PROB = 0.01
const MUT_PARETO_A = 5.0
const BORN_OFFSET = -10
const AGE_GROUP = 600
const POOL_LIMIT = 65536

func RandPareto(a float64) float64 {
	return 1.0/math.Pow(rand.Float64(), 1.0/a) - 1.0
}

type Ring struct {
	Score float64
	Age   int
	Data  []float64
}

func NewRing(n int) Ring {
	var u Ring
	u.Data = make([]float64, n)
	u.Age = AGE_GROUP + BORN_OFFSET
	for i := 0; i < n; i++ {
		u.Data[i] = rand.NormFloat64()
	}
	return u
}

func (r Ring) Mutate() {
	i := rand.Intn(len(r.Data))
	if rand.Float64() < MUT_RESET_PROB {
		r.Data[i] = rand.NormFloat64()
	} else {
		r.Data[i] += RandPareto(MUT_PARETO_A) * rand.NormFloat64()
	}
}

type RingHeap []Ring

func (rh RingHeap) Len() int { return len(rh) }

func (rh RingHeap) Less(i, j int) bool {
	ag := rh[i].Age / AGE_GROUP
	bg := rh[j].Age / AGE_GROUP
	if ag != bg {
		return ag < bg
	}
	return rh[i].Score > rh[j].Score
}

func (rh RingHeap) Swap(i, j int) {
	rh[i], rh[j] = rh[j], rh[i]
}

func (rh *RingHeap) Push(x any) {
	*rh = append(*rh, x.(Ring))
}

func (rh *RingHeap) Pop() any {
	n := len(*rh)
	x := (*rh)[n-1]
	*rh = (*rh)[0 : n-1]
	return x
}

type Field struct {
	Rings []Ring
	Best  Ring
	Pool  RingHeap
}

func NewField(n int, m int) Field {
	var f Field
	f.Rings = make([]Ring, n)
	for i := 0; i < n; i++ {
		f.Rings[i] = NewRing(m)
	}
	f.Best.Score = math.Inf(-1)
	return f
}

func (f *Field) Step() {
	for _, ring := range f.Rings {
		if ring.Score > f.Best.Score {
			f.Best = ring
		}
		heap.Push(&f.Pool, ring)
	}
	if len(f.Pool) > POOL_LIMIT {
		sort.Sort(f.Pool)
		f.Pool = f.Pool[:POOL_LIMIT/2]
		heap.Init(&f.Pool)
	}
	m := len(f.Rings[0].Data)
	for i := range f.Rings {
		f.Rings[i].Data = make([]float64, m)

		parent := heap.Pop(&f.Pool).(Ring)
		copy(f.Rings[i].Data, parent.Data)
		parent.Age++
		f.Rings[i].Age = parent.Age
		if f.Rings[i].Age > AGE_GROUP {
			f.Rings[i].Age = AGE_GROUP
		}
		heap.Push(&f.Pool, parent)
		f.Rings[i].Mutate()
		if rand.Float64() < RESET_PROB {
			f.Rings[i] = NewRing(m)
		}
	}
}
