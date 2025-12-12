package main

import (
	"fmt"
	"log"
	"os"
	"strings"
)

func main() {
	data, err := os.ReadFile("input.txt")
	if err != nil {
		log.Fatal(err)
	}
	grid := strings.Split(strings.TrimRight(string(data), "\n"), "\n")
	partOne(grid)
	partTwo(grid)
}

func partOne(grid []string) {
	totalRolls := 0
	for i, row := range grid {
		for j, item := range row {
			totalAdjacents := 0
			if item == '@' {
				// Check left if possible
				if j != 0 && row[j-1] == '@' {
					totalAdjacents++
				}
				// Check right if possible
				if j != len(row)-1 && row[j+1] == '@' {
					totalAdjacents++
				}
				// Check above if possible
				if i != 0 && grid[i-1][j] == '@' {
					totalAdjacents++
				}
				// Check below if possible
				if i != len(grid)-1 && grid[i+1][j] == '@' {
					totalAdjacents++
				}
				// Check top left if possible
				if i != 0 && j != 0 && grid[i-1][j-1] == '@' {
					totalAdjacents++
				}
				// Check top right if possible
				if i != 0 && j != len(row)-1 && grid[i-1][j+1] == '@' {
					totalAdjacents++
				}
				// Check bottom left if possible
				if i != len(grid)-1 && j != 0 && grid[i+1][j-1] == '@' {
					totalAdjacents++
				}
				// Check bottom right if possible
				if i != len(grid)-1 && j != len(row)-1 && grid[i+1][j+1] == '@' {
					totalAdjacents++
				}

				if totalAdjacents < 4 {
					totalRolls++
				}
			}
		}
	}
	fmt.Println(totalRolls)
}

func partTwo(grid []string) {
	totalRolls := 0
	prevRolls := 0
	for {
		prevRolls = totalRolls
		for i, row := range grid {
			runeRow := []rune(row)
			for j, item := range runeRow {
				totalAdjacents := 0
				if item == '@' {
					// Check left if possible
					if j != 0 && row[j-1] == '@' {
						totalAdjacents++
					}
					// Check right if possible
					if j != len(row)-1 && row[j+1] == '@' {
						totalAdjacents++
					}
					// Check above if possible
					if i != 0 && grid[i-1][j] == '@' {
						totalAdjacents++
					}
					// Check below if possible
					if i != len(grid)-1 && grid[i+1][j] == '@' {
						totalAdjacents++
					}
					// Check top left if possible
					if i != 0 && j != 0 && grid[i-1][j-1] == '@' {
						totalAdjacents++
					}
					// Check top right if possible
					if i != 0 && j != len(row)-1 && grid[i-1][j+1] == '@' {
						totalAdjacents++
					}
					// Check bottom left if possible
					if i != len(grid)-1 && j != 0 && grid[i+1][j-1] == '@' {
						totalAdjacents++
					}
					// Check bottom right if possible
					if i != len(grid)-1 && j != len(row)-1 && grid[i+1][j+1] == '@' {
						totalAdjacents++
					}

					if totalAdjacents < 4 {
						totalRolls++
						runeRow[j] = 'x'
					}
				}
			}
			grid[i] = string(runeRow)
		}
		if prevRolls == totalRolls {
			fmt.Println(totalRolls)
			return
		}
	}
}
