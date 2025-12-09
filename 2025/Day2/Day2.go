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

	s := strings.TrimRight(string(data), "\n")

	ranges := strings.Split(s, ",")

	partOne(ranges)
	partTwo(ranges)
}

func partOne(ranges []string) {
	totalOfIDs := 0
	for _, pair := range ranges {
		left, right, ok := strings.Cut(pair, "-")
		if !ok {
			log.Fatal("No separator found")
		}

		firstId, err := strconv.Atoi(left)
		if err != nil {
			log.Fatal(err)
		}
		secondId, err := strconv.Atoi(right)
		if err != nil {
			log.Fatal(err)
		}

		for i := firstId; i <= secondId; i++ {
			currentNum := strconv.Itoa(i)
			if len(currentNum)%2 != 0 || !isRepeat(currentNum) {
				continue
			}
			totalOfIDs += i
		}
	}
	fmt.Println("Total of IDs:", totalOfIDs)
}

func isRepeat(s string) bool {
	mid := len(s) / 2
	if s[:mid] == s[mid:] {
		return true
	}
	return false
}

func partTwo(ranges []string) {
	totalOfIDs := 0
	for _, pair := range ranges {
		left, right, ok := strings.Cut(pair, "-")
		if !ok {
			log.Fatal("No separator found")
		}

		firstId, err := strconv.Atoi(left)
		if err != nil {
			log.Fatal(err)
		}
		secondId, err := strconv.Atoi(right)
		if err != nil {
			log.Fatal(err)
		}

		for i := firstId; i <= secondId; i++ {
			currentNum := strconv.Itoa(i)
			if !isRepeatTwice(currentNum) {
				continue
			}
			totalOfIDs += i
		}
	}
	fmt.Println("Total of IDs:", totalOfIDs)
}

func isRepeatTwice(s string) bool {
	dupeString := (s + s)[1 : len(s)*2-1]

	if strings.Contains(dupeString, s) {
		fmt.Printf("%v meets the criteria\n", s)
		return true
	}

	return false
}
