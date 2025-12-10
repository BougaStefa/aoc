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

	banks := strings.Split(strings.TrimSpace(string(data)), "\n")

	partOne(banks)
}

func partOne(banks []string) {
	totalJoltage := 0
	for _, bank := range banks {
		num1, index := findBiggestNum(bank[:len(bank)-1])
		num2, _ := findBiggestNum(bank[index+1:])

		joltage, err := strconv.Atoi(num1 + num2)
		if err != nil {
			log.Fatal(err)
		}
		totalJoltage += joltage
	}
	fmt.Println(totalJoltage)
}

func findBiggestNum(s string) (num string, index int) {
	biggest := 0
	for i, numb := range s {
		current := int(numb - '0')
		if current > biggest {
			biggest = current
			index = i
		}
	}
	num = strconv.Itoa(biggest)
	return num, index
}
