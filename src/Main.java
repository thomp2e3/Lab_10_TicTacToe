import java.util.Scanner;

public class Main
{
    private static final int ROWS = 3;
    private static final int COLS = 3;
  private static String board [][] = new String[ROWS][COLS];
    private static String player = "X";

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);


        while(true)
        {
            clearBoard();
            player = "X";

            while(true)
            {
                int row, col;
                do
                {
                    row = SafeInput.getRangedInt(in,"Player " + player + ", enter your row move between [1-3]: ", 1, 3) - 1;
                    col = SafeInput.getRangedInt(in,"Player " + player + ", enter your column move between [1-3]: ", 1, 3) - 1;
                }
                while (!isValidMove(row, col));

                board[row][col] = player;

                if (isWin(player))
                {
                    display();
                    System.out.println("Player " + player + " wins!");
                    break;
                }

                if (isTie())
                {
                    display();
                    System.out.println("It's a tie!");
                    break;

                }
                player = player.equals("X") ? "O" : "X";
            }

            System.out.println("Do you want to play again? (Y/N): ");
            String playAgain = in.next().toUpperCase();
            if (!playAgain.equals("Y"))
            {
                break;
            }
        }
        in.close();
    }


    /**
     *  sets all the board elements to a space
     *
     */
    private static void clearBoard()
    {
        for (int x = 0; x < ROWS; x++)
        {
            for (int y = 0; y < COLS; y++)
            {
                board[x][y] = " ";
            }
        }
    }

    /**
     * shows game used as part of the prompt for the users move choice
     * displays inputted moves
     *
     */
    private static void display()
    {
        System.out.println("-------------");
        for (int x = 0; x < ROWS; x++)
        {
            for (int y = 0; y < COLS; y++)
            {
                System.out.print(board[x][y] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    /**
     * returns true if there is a space at the given proposed
     * move coordinates which means it is a legal move.
     *
     */
    private static boolean isValidMove(int row, int col)
    {
        if (row < 0 || row >= ROWS || col < 0 || col >= COLS)
        {
            System.out.println("Invalid move. Please try again.");
            return false;
        }

        if(!board[row][col].equals(" "))
        {
            System.out.println("Space taken. Please try again.");
            return false;
        }

        return true;
    }


    /**
     *checks to see if there is a win state on the current board
     * for the specified player (X or O) This method in turn calls
     * three additional methods that break down the 3 kinds of wins
     * that are possible.
     *
     */
    private static boolean isWin(String player)
    {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    /**
     *checks for a col win for specified player
     *
     */
    private static boolean isColWin(String player)
    {
        for (int x = 0; x < COLS; x++)
        {
            if (board[0][x].equals(player) && board[1][x].equals(player) && board[2][x].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    /**
     *checks for a row win for the specified player
     *
     */
    private static boolean isRowWin(String player)
    {
        for (int x = 0; x < ROWS; x++)
        {
            if (board[x][0].equals(player) && board[x][1].equals(player) && board[x][2].equals(player))
            {
                return true;
            }
        }
        return false;
    }


    /**
     *checks for a diagonal win for the specified player
     *
     */
    private static boolean isDiagonalWin(String player)
    {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player) || board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }


    /**
     * checks for a tie condition: all spaces on the board are
     * filled OR there is an X and O in every win vector
     *
     */
    private static boolean isTie()
    {
        for (int x = 0; x < ROWS; x++)
        {
            for (int y = 0; y < COLS; y++)
            {
                if (board[x][y].equals(" "))
                {
                    return false;
                }
            }
        }
        return true;
    }
}