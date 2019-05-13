import java.util.Arrays;

public class sudokuSolver{
    public static void main(String[]args){
        char[][] board =
        {{'5','3','.','.','7','.','.','.','.'},
        {'6','.','.','1','9','5','.','.','.'},
        {'.','9','8','.','.','.','.','6','.'},
        {'8','.','.','.','6','.','.','.','3'},
        {'4','.','.','8','.','3','.','.','1'},
        {'7','.','.','.','2','.','.','.','6'},
        {'.','6','.','.','.','.','2','8','.'},
        {'.','.','.','4','1','9','.','.','5'}
        ,{'.','.','.','.','8','.','.','7','9'}};
        sudokuSolver s = new sudokuSolver();
        s.solveSudoku(board);
        System.out.println(Arrays.deepToString(board));
        }

    public void solveSudoku(char[][] board) {
        boolean[][] column = new boolean[9][10];
        boolean[][] row = new boolean[9][10];
        boolean[][] boxes = new boolean[9][10];
        initialSudoku(board, row, column, boxes);
        solveSudokuHelper(board, row, column, boxes);
    }

    private boolean solveSudokuHelper(char[][] board, boolean[][] row,
                                      boolean[][] col, boolean[][] boxes){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                if(board[i][j]!='.') continue;

                for(char ch = '1'; ch <= '9'; ch++){
                    int chInt = ch - '0';
                    if(row[i][chInt] || col[j][chInt] ||
                            boxes[i/3*3+j/3][chInt]) continue;

                    row[i][chInt] = true; col[j][chInt] = true;
                    boxes[i/3*3 + j/3][chInt] = true;
                    board[i][j] = ch;
                    if(solveSudokuHelper(board, row, col, boxes)) return true;
                    row[i][chInt] = false; col[j][chInt] = false;
                    boxes[i/3*3 + j/3][chInt] = false;
                    board[i][j] = '.';
                }

                return false;
            }
        }
        return true;
    }

    public void initialSudoku(char[][] board,
                              boolean[][] row,
                              boolean[][] col,
                              boolean[][] boxes){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                if(board[i][j] == '.') continue;

                int val = board[i][j] - '0';

                row[i][val] = true;
                col[j][val] = true;
                boxes[i/3 * 3 + j/3][val] = true;
            }
        }
    }
}