public class KnightTour
{
    // Set the base size for the chess board
    private static final int boardSize = 8;

    // Constant array for all the movements the knight piece can move
    private static final int[][] KNIGHT_MOVES =
    {
            {-2, -1}, {-1, -2}, {1, -2}, {2, -1},
            {2, 1}, {1, 2}, {-1, 2}, {-2, 1}
    };

    public static void main(String[] args)
    {
        // Create a 2D array for our chessboard
        int[][] chessBoard = new int [boardSize][boardSize];

        // Set the initial starting point for the knight and the move counter
        int currentPosX = 0, currentPosY = 0, moveCounter = 0;

        // Place the counter value to the current knight location in the chessboard array
        chessBoard[currentPosX][currentPosY] = moveCounter;
        //System.out.printf("Move number %1$s : X = %2$s, Y = %3$s %n", moveCounter, currentPosX, currentPosY);

        while (moveCounter <= boardSize * boardSize)
        {
            int[] nextMove = findNextMove(chessBoard, currentPosX, currentPosY);
            currentPosX = nextMove[0];
            currentPosY = nextMove[1];
            chessBoard[currentPosX][currentPosY] = moveCounter;
            moveCounter++;

            //System.out.printf("Move number %1$s : X = %2$s, Y = %3$s %n", moveCounter, currentPosX, currentPosY);
        }

        for (int x = 0; x < boardSize; x++)
        {
            for (int y = 0; y < boardSize; y++)
            {
                System.out.print(" " + chessBoard[x][y] + " ");
            }
            System.out.println();
        }

    }

    // Checks if the position is on the board, and if the position has not yet been visited
    private static boolean isValidMove(int[][] board, int xPos, int yPos)
    {
        return (xPos >= 0 && xPos < boardSize && yPos >= 0 && yPos < boardSize && board[xPos][yPos] == 0);
    }

    // Determine how many available positions there are from a provided position
    private static int availablePositions(int[][] board, int xPos, int yPos)
    {
        int count = 0;
        for (int[] move : KNIGHT_MOVES) {
            int newX = xPos + move[0];
            int newY = yPos + move[1];
            if (isValidMove(board, newX, newY)) {
                count++;
            }
        }
        return count;
    }

    // Returns the best choice for the next move based on the minimum number of available positions
    private static int[] findNextMove(int[][] board, int xPos, int yPos)
    {
        int minMoves = Integer.MAX_VALUE;
        int[] bestMove = {-1, -1};

        for (int[] move : KNIGHT_MOVES) {
            int newX = xPos + move[0];
            int newY = yPos + move[1];
            if (isValidMove(board, newX, newY)) {
                int moves = availablePositions(board, newX, newY);
                if (moves < minMoves) {
                    minMoves = moves;
                    bestMove[0] = newX;
                    bestMove[1] = newY;
                }
            }
        }
        return bestMove;
    }
}