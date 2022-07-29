package com.company;

public enum Arena {

    BasicArena(new int[][]{
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0},
            {0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0},

            {0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 0, 3, 3, 3, 0, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0},

            {0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0},
            {0, 1, 1, 0, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 0, 1, 1, 0},
            {0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0},

            {0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    });
    public static final int WALL = 0;
    public static final int FLOOR_WITH_COINS = 1;
    public static final int NORMAL_FLOOR = 2;
    public static final int ENEMY_CAVE = 3;
    public static final int START_POINT = 4;


    private int heightOfField;
    private int widthOfField;
    private State[][] states;

    private int startRow;
    private int startCol;


    Arena(int[][] fields) {
        states = new State[fields.length][fields[0].length];
        for (int row = 0; row < fields.length; row++) {
            for (int col = 0; col < fields[0].length; col++) {
                states[row][col] = State.getValueOF(fields[row][col]);
                if (states[row][col] == State.START_POINT){
                    startRow = row;
                    startCol = col;
                }
            }
        }
    }
    

    public int getStartRow(){
        return startRow;
    }
    public int getStartCol(){
        return startCol;
    }

    public int getRows() {
        return states.length;
    }
    public int getCols() {
        return states[0].length;
    }

    public int getWidthOfField() {
        return widthOfField;
    }
    public int getHeightOfField() {
        return heightOfField;
    }
    public void setWidthOfField(int widthOfField) {
        this.widthOfField = widthOfField;
    }
    public void setHeightOfField(int heightOfField) {
        this.heightOfField = heightOfField;
    }

    public int getWidth() {
        return getCols() * getWidthOfField();
    }
    public int getHeight() {
        return getRows() * getHeightOfField();
    }



    public State getStateOfField(int row, int col) {
        try {
            return states[row][col];
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            if (row < 0){
                row += getRows();
            } else if (row >= getRows()){
                row -= getRows();
            } else if (col < 0) {
                col += getCols();
            } else if (col >= getCols()) {
                col -= getCols();
            }
            return getStateOfField(row, col);
        }
    }

    public State getStateOfNextField(int row, int col, Figure.Direction direction) {
        switch (direction) {
            case UP -> {
                row--;
            }
            case DOWN -> {
                row++;
            }
            case LEFT -> {
                col--;
            }
            case RIGHT -> {
                col++;
            }
        }
        return getStateOfField(row, col);
    }

    public enum State{
        WALL(false),
        FLOOR_WITH_COINS(true),
        NORMAL_FLOOR(true),
        ENEMY_CAVE(true),
        START_POINT(true);

        private boolean canGoOn;


        State(boolean canGoOn){
            this.canGoOn = canGoOn;
        }

        public boolean canGoOnThis(){
            return canGoOn;
        }

        public static State getValueOF(int number){
            return switch (number){
                case 0 -> WALL;
                case 1 -> FLOOR_WITH_COINS;
                case 2 -> NORMAL_FLOOR;
                case 3 -> ENEMY_CAVE;
                case 4 -> START_POINT;
                default -> null;
            };
        }
    }
}

