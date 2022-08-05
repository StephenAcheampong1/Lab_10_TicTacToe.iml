import java.util.Scanner;

public class TicTacToe {

    private static final int ROW = 3;
    private static final int COL = 3;
    private static String board[][] = new String[ROW][COL];
    //Clear the board
    private static void clearBoard() //sets all the board elements to a space
    {
        for (int row = 0; row < ROW; row++)
        {
            for (int col = 0; col < COL; col++)
            {
                board[row][col] = " "; //make this cell a space
            }
        }
    }
    //Show the board
       private static void showBoard() {
        System.out.println("\n 1 2 3\n");
        for (int row = 0; row < 3; row++) {
            System.out.print(row + 1);
            for (int col = 0; col < 3; col++) {
                System.out.print(" " + board[row][col] + " ");
                if (col != 2)
                    System.out.print(" | ");
            }
            System.out.println();
            if (row != 2) {
                System.out.println(" ____|____|____");
                System.out.println(" ");
            }
        }
        System.out.println();
    }

    // Checks to see if the move is valid
    private static boolean isValidMove(int row, int col)
    {
        boolean retVal = false;
        if (board[row][col].equals(" "))
            retVal = true;
        return retVal;
    }

    // Checks for possible win on the current board
    private static boolean isWin(String player) {
        if (isColWin(player) || isRowWin(player) || isDiagonalWin(player))
            return true;
        return false;
    }

    // Checks for a column win
    private static boolean isColWin(String player) {
        for (int col = 0; col < COL; col++) {
            if (board[0][col].equals(player) && board[0][col].equals(board[1][col]) && board[1][col].equals(board[2][col]))
                return true;
        }
        return false;
    }

    // Checks for a row win
    private static boolean isRowWin(String player) {
        for (int row = 0; row < ROW; row++) {
            if (board[row][0].equals(player) && board[row][0].equals(board[row][1]) && board[row][1].equals(board[row][2]))
                return true;
        }
        return false;
    }

    // checks for a diagonal win
    private static boolean isDiagonalWin(String player) {
        if (board[1][1].equals(player) && ((board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) ||
                (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]))))
            return true;
        return false;
    }

    // Checks for a Tie
    private static boolean isTie() {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                if (board[row][col].equals(" "))
                    return false;
            }
        }
        return true;
    }

    //Main Method
    public static void main(String[] args)
    {
        //create instance of Scanner class
        Scanner in = new Scanner(System.in);

        do {
            String player = "X";
            clearBoard();
            showBoard();

        //Start of the game
            while (true)
            {
                System.out.println("Player " + player);
                //get a move
                int rowMove = SafeInput.getRangedInt(in, "Enter row: ", 1, 3);
                int colMove = SafeInput.getRangedInt(in, "Enter col: ", 1, 3);
                rowMove--;
                colMove--;
                board[rowMove][colMove] = player;
                showBoard();

                if (isWin(player)) {
                    System.out.println("Player " +player + " wins!");
                    break;
                } else if (isTie()) {
                    System.out.println("Tie game!");
                    break;
                }
                //Player
                if (player == "X")
                    player = "O";
                else
                    player = "X";
            }

        } while (SafeInput.getYNConfirm(in, "\nDo you want to continue playing Tic Tac Toe? "));
    }
}
