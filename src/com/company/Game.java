package com.company;

import java.util.Scanner;

public class Game {
    private static Scanner scanner = new Scanner(System.in);
    private static Grid grid;
    private static int numberOfLoops = 0;

    // Input method
    public static void setup () {
        String gridDimensions = scanner.nextLine();
        String[] gridDimensionsArray = gridDimensions.split("\\s*,\\s*");

        int width = Integer.parseInt(gridDimensionsArray[0]);
        int height = Integer.parseInt(gridDimensionsArray[1]);
        if (height > 1000 || width > 1000 ) {
            throw new IllegalArgumentException("Opsy, You may need to download some RAM.");
        }

        Cell[][] generationZero = new Cell[height][width];

        for (int i = 0; i < height; i++) {
            String test = scanner.nextLine();

            if (test.length() > width || test.length() != width ) {
                throw new IllegalArgumentException("Number of elements is incorrect! Expected - " + width  +". Instead received - " + i);
            }
            if (!test.matches("^[01]+$")) {
                throw new IllegalArgumentException("Invalid element type. Only 1 or 0 are allowed");
            }

            String[] rowOfNums = test.split("(?!^)");

            for (int j = 0; j < rowOfNums.length; j++) {
                generationZero[i][j] = new Cell((rowOfNums[j].equals("1") ? true : false));
            }
        }

        String cellToBeTracked = scanner.nextLine();
        String[] arguments = cellToBeTracked.split("\\s*,\\s*");

        if (arguments.length != 3) {
            throw new IllegalArgumentException("Invalid number of arguments provided. Expected exactly 3 are required");
        }

        int targetCellX = Integer.parseInt(arguments[0]);
        int targetCellY = Integer.parseInt(arguments[1]);
        numberOfLoops = Integer.parseInt(arguments[2]);

//        System.out.println(generationZero[targetCellY][targetCellX]);
        grid = new Grid(generationZero, width, height, targetCellX, targetCellY);
    }

    // Execute the logic
    public static long start () {
//        grid.drawGrid();
        for (int i = 0; i < numberOfLoops; i++) {
//            Thread.sleep(100);
            grid.nextGeneration();
//            grid.drawGrid();
        }
        System.out.println(grid.getTargetCellMaturity());
        return grid.getTargetCellMaturity();
    }
}



