import java.util.ArrayList;

public class Paper {


    public static void main (String[] args) {
        String operations = "RLRRRL";
        int rows = 1;
        int columns = (int) Math.pow(2, operations.length());
    int[][] init;
    init = new int[rows][columns];
    for (int k = 0; k < columns; k++) {
        init[0][k] = k + 1;
    }
    int[][] previous = init;
    int[][] next = new int[rows * 2][columns / 2];
    int k = 0;
    while (k < operations.length()) {
        if (operations.charAt(k) == 'R') {
            next = R(previous);
        } else {
            next = L(previous);
        }
        columns = next[0].length;
        rows = next.length;
        previous = next;
        next = new int[rows * 2][columns / 2];
        k++;
    }
    for (int i = 0; i < rows; i++) {
        System.out.println(previous[i][0]);
    }
}

    public static int[][] R (int[][] paper) {
        int row = paper.length;
        int col = paper[0].length;
        int n_row = row * 2;
        int n_col = col / 2;
        int[][] result = new int[n_row][n_col];
        int[][] mini = new int[row][n_col];
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < col; y++) {
                if (y < n_col) {
                    result[row + x][y] = paper[x][y];
                } else {
                    mini[x][y - n_col] = paper[x][y];
                }
            }
        }
        mini = flip_horiz(flip_vert(mini));
        for (int x = 0; x < mini.length; x++) {
            for (int y = 0; y < mini[0].length; y++) {
                result[x][y] = mini[x][y];
            }
        }
        return result;
    }

    public static int[][] L (int[][] paper) {
        int row = paper.length;
        int col = paper[0].length;
        int n_row = row * 2;
        int n_col = col / 2;
        int[][] result = new int[n_row][n_col];
        int[][] mini = new int[row][n_col];
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < col; y++) {
                if (y >= n_col) {
                    result[row + x][y - n_col] = paper[x][y];
                } else {
                    mini[x][y] = paper[x][y];
                }
            }
        }
        mini = flip_horiz(flip_vert(mini));
        for (int x = 0; x < mini.length; x++) {
            for (int y = 0; y < mini[0].length; y++) {
                result[x][y] = mini[x][y];
            }
        }
        return result;
    }

    public static int[][] flip_horiz(int[][] horiz) {
        for (int x = 0; x < horiz.length; x++) {
            for (int y = 0; y < horiz[0].length / 2; y++) {
                int temp = horiz[x][y];
                horiz[x][y] = horiz[x][horiz[0].length - y - 1];
                horiz[x][horiz[0].length - y - 1] = temp;
            }
        }
        return horiz;
    }

    public static int[][] flip_vert(int[][] vert) {
        for (int x = 0; x < vert.length / 2; x++) {
            for (int y = 0; y < vert[0].length; y++) {
                int temp = vert[x][y];
                vert[x][y] = vert[vert.length - x - 1][y];
                vert[vert.length - x - 1][y] = temp;
                }
            }
            return vert;
        }

}