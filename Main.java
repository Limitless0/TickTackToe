package tictactoe;

import java.util.Scanner;


public class Main {

    public static char[][] grid = new char[3][3];
    static boolean finished = false;
    static boolean draw = false;
    static boolean xVictory = false;
    static boolean oVictory = false;
    static boolean validBoard = true;
    static boolean emptyCell = true;
    static char[] xo = {'X', 'O'} ;
    static int iterator = 0;

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String input = "_________";
        int ii = 0;
        //char[][] grid = new char[3][3];
        int jj = 0;
        int kk = 0;

        while (ii < 3) {                //assign values to grid
            while (jj < 3) {
                grid[ii][jj] = input.charAt(kk);
                jj++;
                kk++;
            }
            ii++;
            jj = 0;
        }
        draw();


        int xCount = 0;
        int oCount = 0;
        int emptyCount = 0;

        badger(scan);
        while (!finished) {

            for (char[] row : grid) {
                for (char chr : row) {
                    switch (chr)
                    {
                        case 'X': xCount++;
                        case 'O': oCount++;
                        case '_': emptyCount++;
                    }
                }
            }


            if (xCount - oCount == 2) {
                validBoard = false;
            }
            if (emptyCount == 0) {
                emptyCell = false;
                draw = true;
            }
            boolean temp = false;
            for (ii = 0; ii < 3; ii++) {
                temp = false || chkClumO(ii) || chkDiagonalsO() || chkRowO(ii) || temp;
            }

            oVictory = temp;
            temp = false;
            for (ii = 0; ii < 3; ii++) {
                temp = false || chkClumX(ii) || chkDiagonalsX() || chkRowX(ii) || temp;
            }

            xVictory = temp;

            if (oVictory || xVictory) {
                finished = true;
            }

            if (!finished && !emptyCell) {
                draw = true;
                finished = true;
            }

            if (xVictory && oVictory) {
                System.out.println("Impossible");
                return;
            }
            if (!validBoard) {
                System.out.println("Impossible");
                return;
            }


            if (draw) {
                System.out.println("Draw");
                return;
            }

            if (!finished) {
                System.out.println("Game not finished");
                badger(scan);
            }

            if (xVictory) {
                System.out.println("X wins");
                return;
            }

            if (oVictory) {
                System.out.println("O wins");
                return;
            }
        }
    }
    public static void badger(Scanner scan) { // because it badgers the player, shush and let me have my fun
        boolean playing = true;
        while (playing) {
            boolean valid = true;

            System.out.print("Enter the coordinates: ");
            if (!scan.hasNextInt()) {
                System.out.println("You should enter numbers!");
                System.out.print("Enter the coordinates: ");
            }
            int[] playerCoords = new int[2];
            playerCoords[0] = scan.nextInt();
            playerCoords[1] = scan.nextInt();
            if ((playerCoords[0] > 3) || (playerCoords[1] > 3)) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            if (grid[Math.abs(playerCoords[1] - 3)][playerCoords[0] - 1] != '_') {
                System.out.println("This cell is occupied! Choose another one!");
            } else {
                grid[Math.abs(playerCoords[1] - 3)][playerCoords[0] - 1] = xo[iterator % 2];
                draw();
                iterator++;
                playing = false;
            }
            draw();
        }
    }
    public static  boolean chkDiagonalsX() {
        if (grid[1][1] != 'X') {
            return false;
        }
        if (grid[0][0] == 'X' && grid[2][2] == 'X') {
            return true;
        }
        if (grid[0][2] == 'X' && grid[2][0] == 'X') {
            return true;
        }
        return false;

    }
    public static boolean chkDiagonalsO () {
        if (grid[1][1] != 'O') {
            return false;
        }
        if (grid[0][0] == 'O' && grid[2][2] == 'O') {
            return true;
        }
        if (grid[0][2] == 'O' && grid[2][0] == 'O') {
            return true;
        }
        return false;

    }
    public static boolean chkRowX(int ii) {
        if (grid[ii][1] != 'X') {
            return false;
        }
        if ((grid[ii][0] == 'X') && (grid[ii][2] == 'X')) {
            return true;
        } else {
            return false;
        }
    }public static boolean chkRowO(int ii) {
        if (grid[ii][1] != 'O') {
            return false;
        }
        if ((grid[ii][0] == 'O') && (grid[ii][2] == 'O')) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean chkClumX(int ii) {
        if (grid[1][ii] != 'X') {
            return false;
        }
        if ((grid[0][ii] == 'X') && (grid[2][ii] == 'X')) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean chkClumO(int ii) {
        if (grid[1][ii] != 'O') {
            return false;
        }
        if ((grid[0][ii] == 'O') && (grid[2][ii] == 'O')) {
            return true;
        } else {
            return false;
        }
    }


    public static void draw() {

        int ii = 0;
        int jj = 0;

        System.out.println("---------");
        for (;ii < 3; ii++){
            System.out.print("| ");
            for (; jj < 3; jj++) {
                System.out.print(grid[ii][jj] + " ");
            }
            jj = 0;
            System.out.println("|");
        }
        System.out.println("---------");
    }
}
