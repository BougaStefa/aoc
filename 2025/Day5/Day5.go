package main

import (
	"fmt"
	"log"
	"os"
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
