package com.company;

// Grid
public class Grid {
    private int width;
    private int height;
    private int targetCellX;
    private int targetCellY;
    private long generation;
    private long targetCellMaturity = 0;
    private Cell[][] grid;


    Grid(Cell[][] grid, int width, int height, int targetCellX, int targetCellY){
        this.grid = grid;
        this.width = width;
        this.height = height;
        this.targetCellX = targetCellX;
        this.targetCellY = targetCellY;
        this.generation = 0;
    }

    public void drawGrid() {
        for(int row = 0; row < grid.length; row++ ){
            for(int col = 0; col < grid[row].length; col++ ){
//                cellList.add(new Cell(row, col, grid[row][col]));
                System.out.print(grid[row][col].isGreen() ? '1' : '0');
                System.out.print(' ');
            }
            System.out.println();
        }
        System.out.println("Generation:" + generation);
    }

    // Compute the next generation
    public void nextGeneration(){
        Cell[][] newGrid = new Cell[height][width];
        for(int row = 0; row < height; row++ ){
            for(int col = 0; col < width; col++ ){
                if (row == targetCellY && col == targetCellX) {
                    targetCellMaturity += isGreen(grid, row, col) ? 1 : 0;
                }
                newGrid[row][col] = new Cell(isGreen(grid, row, col));
            }
        }
        grid = newGrid;
        generation++;
    }

    // Apply rules
    private boolean isGreen(Cell[][] oldGrid, int row, int col){
        boolean currentGreenCell = oldGrid[row][col].isGreen();

        // count adjacent neighbours without the current one
        int greenNeighboursCount = countNeighbors(oldGrid, row, col);

        if(currentGreenCell) {
            // green cell is surrounded by green neighbours (2,3,6) stays green (1) else red
            if (greenNeighboursCount == 2 || greenNeighboursCount == 3 || greenNeighboursCount == 6) {
                return true;
            } else {
                return false;
            }
        } else {
            // red cell is surrounded by green neighbours (3,6) becomes green (1) else red
            if ( greenNeighboursCount == 3 || greenNeighboursCount == 6) {
                return true;
            } else {
                return false;
            }
        }
    }


    // Check neighbours according to its placement
    private int countNeighbors(Cell[][] generation, int row, int col) {
        int numNeighbors = 0;

        // NW
        if ((row - 1 >= 0) && (col - 1 >= 0)) {
            numNeighbors = generation[row - 1][col - 1].isGreen() ? numNeighbors + 1 : numNeighbors;
        }
        // W
        if ((row >= 0) && (col - 1 >= 0)) {
            numNeighbors = generation[row][col - 1].isGreen() ? numNeighbors + 1 : numNeighbors;
        }
        // SW
        if ((row + 1 < generation.length) && (col - 1 >= 0)) {
            numNeighbors = generation[row + 1][col - 1].isGreen() ? numNeighbors + 1 : numNeighbors;
        }
        // S
        if ((row + 1 < generation.length) && (col < generation[0].length)) {
            numNeighbors = generation[row + 1][col].isGreen() ? numNeighbors + 1 : numNeighbors;
        }
        // SE
        if ((row + 1 < generation.length) && (col + 1 < generation[0].length)) {
            numNeighbors = generation[row + 1][col + 1].isGreen() ? numNeighbors + 1 : numNeighbors;
        }
        // E
        if ((row < generation.length) && (col + 1 < generation[0].length)) {
            numNeighbors = generation[row][col + 1].isGreen() ? numNeighbors + 1 : numNeighbors;
        }
        // NE
        if ((row - 1 >= 0) && (col + 1 < generation[0].length)) {
            numNeighbors = generation[row - 1][col + 1].isGreen() ? numNeighbors + 1 : numNeighbors;
        }
        // N
        if ((row - 1 >= 0) && (col < generation[0].length)) {
            numNeighbors = generation[row - 1][col].isGreen() ? numNeighbors + 1 : numNeighbors;
        }

        return numNeighbors;
    }

    // Get the num of times the cell has been green
    public long getTargetCellMaturity() {
        return targetCellMaturity;
    }
}