
public class Matrix {

    public static void main(String[] args) {
        int[][] mat = {{1,3,5,7,25}, {2,12,15,19,28}, {7,15,18,20,32}, {8,17,21,25,35}};
        System.out.print("21, " + matrixSearch(mat, 21) + "\n");
        System.out.print("50, " + matrixSearch(mat, 50) + "\n");
        System.out.print("7, " + matrixSearch(mat, 7) + "\n");
        System.out.print("27 " + matrixSearch(mat, 27) + "\n");
    }

    public static boolean matrixSearch (int[][] mat, int target) {
        for (int i = 0; i < mat.length; i++) {
            int[] row = mat[i];
            if (helper (row, 0, row.length - 1, target)) {
                return true;
            }
        }
        return false;
    }

    private static boolean helper (int[] row, int start, int end, int target) {
        int mid = (start + end) / 2;
        if (row[mid] == target) {
            return true;
        } else if (end < start || start < 0 || end >= row.length) {
            return false;
        } else if (row[mid] > target) {
            return helper(row, start, mid - 1, target);
        } else {
            return helper(row, mid + 1, end, target);
        }
    }
}
