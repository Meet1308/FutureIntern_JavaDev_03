package task03;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] board = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};

        printBoard(board);

        char currentPlayer = 'X';

        while (true) {
            // Player's turn
            System.out.println("Player " + currentPlayer + ", enter your move (row, column): ");
            int row, col;
            try {
                row = scanner.nextInt() - 1; // Adjust for 0-based indexing
                col = scanner.nextInt() - 1;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter integers between 1 and 3 for row and column.");
                scanner.nextLine(); // Clear invalid input from buffer
                continue;
            }

            // Validate move
            if (isValidMove(board, row, col)) {
                board[row][col] = currentPlayer;
                printBoard(board);

                // Check for win or draw
                if (checkWinner(board) != ' ') {
                    System.out.println("Player " + checkWinner(board) + " wins!");
                    break;
                } else if (isBoardFull(board)) {
                    System.out.println("It's a draw!");
                    break;
                }

                // Switch player
                currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
            } else {
                System.out.println("Invalid move. The position is already occupied or out of bounds.");
            }

            // Ask to restart or exit
            System.out.println("Do you want to restart (R) or exit (E)? ");
            char choice = scanner.next().charAt(0);
            if (choice == 'E') {
                System.out.println("Thanks for playing!");
                break;
            } else if (choice == 'R') {
                board = new char[][]{{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
                currentPlayer = 'X';
                printBoard(board);
            } else {
                System.out.println("Invalid choice. Please enter R or E.");
            }
        }

        scanner.close();
    }

    public static void printBoard(char[][] board) {
        System.out.println("  |  | ");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("  |  | ");
        }
    }

    public static boolean isValidMove(char[][] board, int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
    }

    public static char checkWinner(char[][] board) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') {
                return board[i][0];
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != ' ') {
                return board[0][i];
            }
        }

        // Check diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') {
            return board[0][0];
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ') {
            return board[0][2];
        }

        return ' ';
    }
    public static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false; // At least one empty space exists
                }
            }
        }
        return true; // All positions are filled
    }
}