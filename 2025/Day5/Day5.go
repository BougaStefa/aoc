package main

import (
	"fmt"
	"log"
	"os"
	"slices"
	"strconv"
	"strings"
)

func main() {
	data, err := os.ReadFile("input.txt")
	if err != nil {
		log.Fatal(err)
	}

	lines := strings.Split(string(data), "\n")

	var freshRanges, ingredients []string
	target := &freshRanges
	for _, line := range lines {
		if strings.TrimSpace(line) == "" {
			target = &ingredients
			continue
		}
		*target = append(*target, line)
	}

	partOne(freshRanges, ingredients)
	partTwo(freshRanges)
}

func partOne(ranges []string, ingredients []string) {
	totalFresh := 0
	for _, ingredient := range ingredients {
		ingId, _ := strconv.Atoi(ingredient)
		if isInRange(ingId, ranges) {
			totalFresh++
		}
	}
	fmt.Println(totalFresh)
}

func isInRange(item int, ranges []string) bool {
	isInRange := false
	for _, ran := range ranges {
		splIndex := strings.Index(ran, "-")
		floor, _ := strconv.Atoi(ran[:splIndex])
		ceil, _ := strconv.Atoi(ran[splIndex+1:])
		if item >= floor && item <= ceil {
			isInRange = true
		}
	}
	return isInRange
}

// This one is a bit demented would take 25 days to run (should be right though)
// func partTwo(freshRange []string) {
// 	totalInRange := 0
// 	m := make(map[int]string)
// 	for i, ran := range freshRange {
// 		fmt.Println("We are at iterration: ", i)
// 		splIndex := strings.Index(ran, "-")
// 		floor, _ := strconv.Atoi(ran[:splIndex])
// 		ceil, _ := strconv.Atoi(ran[splIndex+1:])
// 		for j := floor; j <= ceil; j++ {
// 			fmt.Println("We are in neted loop iterration: ", j)
// 			_, ok := m[j]
// 			if !ok {
// 				totalInRange++
// 				m[j] = ""
// 			}
// 		}
// 	}
// 	fmt.Println(totalInRange)
// }

func partTwo(freshRange []string) {
	type ranges struct {
		Start int
		End   int
	}
	totalInRange := 0
	allRanges := []ranges{}
	for _, ran := range freshRange {
		splIndex := strings.Index(ran, "-")
		floor, _ := strconv.Atoi(ran[:splIndex])
		ceil, _ := strconv.Atoi(ran[splIndex+1:])
		allRanges = append(allRanges, ranges{Start: floor, End: ceil})
	}

	slices.SortFunc(allRanges, func(a, b ranges) int {
		if a.Start < b.Start {
			return -1
		}
		if a.Start > b.Start {
			return 1
		}
		if a.End < b.End {
			return -1
		}
		if a.End > b.End {
			return 1
		}
		return 0
	})

	curStart := allRanges[0].Start
	curEnd := allRanges[0].End
	for i, ran := range allRanges {
		if i == 0 {
			continue
		}
		if ran.Start <= curEnd+1 {
			if ran.End > curEnd {
				curEnd = ran.End
			}
		} else {
			totalInRange += curEnd - curStart + 1
			curStart = ran.Start
			curEnd = ran.End
		}
	}
	totalInRange += curEnd - curStart + 1
	fmt.Println(totalInRange)
}
