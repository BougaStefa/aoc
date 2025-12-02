package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
)

func main() {
	// test := [10]string{"L68", "L30", "R48", "L5", "R60", "L55", "L1", "L99", "R14", "L82"}

	totalZeros := 0
	dial := 50
	file, _ := os.Open("input.txt")
	defer file.Close()

	scanner := bufio.NewScanner(file)
	var rotations []string

	for scanner.Scan() {
		rotations = append(rotations, scanner.Text())
	}

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
