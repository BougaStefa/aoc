package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
)

func main() {
	// test := []string{"L68", "L30", "R48", "L5", "R60", "L55", "L1", "L99", "R14", "L82"}

	file, _ := os.Open("input.txt")
	defer file.Close()

	scanner := bufio.NewScanner(file)
	var rotations []string

	for scanner.Scan() {
		rotations = append(rotations, scanner.Text())
	}

	partOneSolver(rotations)
	partTwoSolver(rotations)
}

func partOneSolver(rotations []string) {
	totalZeros := 0
	dial := 50
	for _, rotation := range rotations {
		dir := string(rotation[0])
		num, _ := strconv.Atoi(rotation[1:])

		if dir == "R" {
			dial += num
		} else {
			dial -= num
		}

		if dial%100 == 0 {
			totalZeros++
		}
	}

	fmt.Println(totalZeros)
}

func partTwoSolver(rotations []string) {
	dial := 50
	totalClicks := 0
	for _, rotation := range rotations {
		dir := string(rotation[0])
		num, _ := strconv.Atoi(rotation[1:])

		for num > 0 {
			if dir == "R" {
				dial++
			} else {
				dial--
			}

			if math.Abs(float64(dial)) == 100 || dial == 0 {
				dial = 0
				totalClicks++
			}
			num--
		}

	}
	fmt.Println(totalClicks)
}
