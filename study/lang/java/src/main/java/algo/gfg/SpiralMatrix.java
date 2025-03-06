package algo.gfg;

public class SpiralMatrix {

    public void print(int[][] matrix, int startRow, int startCol, int endRow, int endCol) {
        int row = matrix.length;
        int column = matrix[0].length;

        System.out.println("start col => " + startCol);
        // print increasing row
        if (startRow == 0 && startCol == 0) {
            for (int i = 0; i < endCol; i++) {
                System.out.println(matrix[startRow][i]);
            }
            print(matrix, startRow, column, endRow, endCol);
        }

        // print increasing column
        if (startRow == 0 && startCol == column) {
            for (int i = 0; i < row; i++) {
                System.out.println(matrix[i][startCol - 1]);
            }
            print(matrix, row, column, endRow, endCol);
        }

        // print row
        if (0 < startRow && startCol == column) {
            for (int i = 0; i < column; i++) {
                System.out.println(matrix[startRow][i]);
            }
        }
    }
}
