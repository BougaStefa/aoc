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

	// partOne(banks)
	partTwo(banks)
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

func partTwo(banks []string) {
	totalJoltage := 0
	for _, bank := range banks {

		num1, index := findBiggestNum(bank[:len(bank)-11])
		offset := index + 1

		end := len(bank) - 10
		if end <= offset {
			log.Fatal("no elements")
		}
		num2, localIndex := findBiggestNum(bank[offset:end])
		offset = localIndex + 1 + offset

		end = len(bank) - 9
		if end == offset {
			log.Fatal("no elements")
		}
		num3, localIndex := findBiggestNum(bank[offset:end])
		offset = localIndex + 1 + offset

		end = len(bank) - 8
		if end == offset {
			log.Fatal("no elements")
		}
		num4, localIndex := findBiggestNum(bank[offset:end])
		offset = localIndex + 1 + offset

		end = len(bank) - 7
		if end == offset {
			log.Fatal("no elements")
		}
		num5, localIndex := findBiggestNum(bank[offset:end])
		offset = localIndex + 1 + offset

		end = len(bank) - 6
		if end == offset {
			log.Fatal("no elements")
		}
		num6, localIndex := findBiggestNum(bank[offset:end])
		offset = localIndex + 1 + offset

		end = len(bank) - 5
		if end == offset {
			log.Fatal("no elements")
		}
		num7, localIndex := findBiggestNum(bank[offset:end])
		offset = localIndex + 1 + offset

		end = len(bank) - 4
		if end == offset {
			log.Fatal("no elements")
		}
		num8, localIndex := findBiggestNum(bank[offset:end])
		offset = localIndex + 1 + offset

		end = len(bank) - 3
		if end == offset {
			log.Fatal("no elements")
		}
		num9, localIndex := findBiggestNum(bank[offset:end])
		offset = localIndex + 1 + offset

		end = len(bank) - 2
		if end == offset {
			log.Fatal("no elements")
		}
		num10, localIndex := findBiggestNum(bank[offset:end])
		offset = localIndex + 1 + offset

		end = len(bank) - 1
		if end == offset {
			log.Fatal("no elements")
		}
		num11, localIndex := findBiggestNum(bank[offset:end])
		offset = localIndex + 1 + offset

		end = len(bank)
		if end <= offset {
			log.Fatal("no elements")
		}
		num12, _ := findBiggestNum(bank[offset:])

		joltage, err := strconv.Atoi(
			num1 + num2 + num3 + num4 + num5 + num6 + num7 + num8 + num9 + num10 + num11 + num12,
		)
		fmt.Printf("DEBUG: joltage was %v\n", joltage)
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
