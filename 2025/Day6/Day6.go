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
	rows := strings.Split(string(data), "\n")
	nums1 := strings.Fields(rows[0])
	nums2 := strings.Fields(rows[1])
	nums3 := strings.Fields(rows[2])
	nums4 := strings.Fields(rows[3])
	ops := strings.Fields(rows[4])

	var total int
	for i, op := range ops {

		n1, _ := strconv.Atoi(nums1[i])
		n2, _ := strconv.Atoi(nums2[i])
		n3, _ := strconv.Atoi(nums3[i])
		n4, _ := strconv.Atoi(nums4[i])

		switch op {
		case "*":
			total += n1 * n2 * n3 * n4
		case "+":
			total += n1 + n2 + n3 + n4
		}
	}
	fmt.Println(total)
}
