package com.company;

public class Board {
    private final char[][] gameBoard;
    private int lastRowCount;
    private boolean isFullorFinished;

    private static final int ROWS = 6;
    private static final int COLUMNS = 7;


    public Board(){
        isFullorFinished = false;
        this.gameBoard = createBoard();
        this.lastRowCount = 0;
    }

    private char[][] createBoard(){
        char[][] b = new char[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                b[i][j] = '*';
            }
        }
        return b;
    }

    public void showBoard(){
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print(gameBoard[i][j] + (j==COLUMNS-1? "":"\t"));
            }
            System.out.print("\n");
        }
    }

    private int getFreeRow(int column, int row){
        try
        {
            if (row < 0)
            {
                System.out.println("The column if full, please select a valid column!!");
                showBoard();
                return -1;
            }
            else if (gameBoard[row][column] == '*')
            {
                return row;
            }
            else
            {
                return getFreeRow(column, row - 1);
            }
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Select a valid column!!");
            showBoard();
            return -1;
        }
    }

    public boolean isFullorFinished(){
        return isFullorFinished;
    }

    private boolean isBoardFull(int column){
        if (gameBoard[0][column] == 'X' || gameBoard[0][column] == 'O')
            lastRowCount+=1;
        return lastRowCount == COLUMNS;
    }

    private boolean hasWonAnyPlayer(int column, int row, char playerToken){
        int minCol = column - 3;
        int maxCol = column + 3;
        int minRow = row - 3;
        int maxRow = row + 3;

        for (int i = minRow; i<= maxRow; i++){
            for (int j = minCol; j <= maxCol; j++) {
                if (i < ROWS && i >= 0 && j < COLUMNS && j >= 0){
                    if(i +3  < ROWS && gameBoard[i][j] == playerToken && gameBoard[i + 1][j] == playerToken && gameBoard[i + 2][j] == playerToken && gameBoard[i + 3][j] == playerToken)
                    {
                        System.out.println("Vertical Win");
                        return true;
                    }
                    if(j + 3 < COLUMNS && gameBoard[i][j] == playerToken && gameBoard[i][j + 1] == playerToken && gameBoard[i][j + 2] == playerToken && gameBoard[i][j + 3] == playerToken)
                    {
                        System.out.println("Horizontal Win");
                        return true;
                    }
                    if( i+3 < ROWS && j+3 < COLUMNS && gameBoard[i][j] == playerToken && gameBoard[i + 1][j +1] == playerToken && gameBoard[i + 2][j +2] == playerToken && gameBoard[i +3][j + 3] == playerToken)
                    {
                        System.out.println("Left Diagonal Win");
                        return true;
                    }
                    if( i-3 >= 0 && j + 3 < COLUMNS && gameBoard[i][j] == playerToken && gameBoard[i - 1][j + 1] == playerToken && gameBoard[i - 2][j + 2] == playerToken && gameBoard[i - 3][j + 3] == playerToken)
                    {
                        System.out.println("Right Diagonal Win");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void getFinalMessage(String playerName){
        showBoard();
        if(lastRowCount==COLUMNS){
            System.out.println("TIE!!");
        }
        else{
            System.out.println(playerName+ " WON!!");
        }
    }

    public boolean insert(int column, char playerToken){
        int row = getFreeRow(column, ROWS-1);
        if (row != -1) {
            gameBoard[row][column] = playerToken;
            if (isBoardFull(column) || hasWonAnyPlayer(column, row, playerToken))
                isFullorFinished = true;
            return !isFullorFinished;
        }
        else {
            return false;
        }
    }
}
