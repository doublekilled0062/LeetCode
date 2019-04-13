import java.util.List;

/**
 * 59. 螺旋矩阵 II
 *
 * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
 * 示例:
 * 输入: 3
 * 输出:
 * [
 * [ 1, 2, 3 ],
 * [ 8, 9, 4 ],
 * [ 7, 6, 5 ]
 * ]
 */
public class LeetCode59 {
    public int[][] generateMatrix(int n) {
        if(n == 0){
            return new int[0][0];
        }

        int[][] matrix = new int[n][n];
        int row = n;
        int col = n;
        int i = 0;
        int j = 0;
        int dir = 0; //0左 1下 2右 3上
        int maxRow = row - 1;
        int maxCol = col - 1;
        int minRow = 0;
        int minCol = 0;
        //一共需要遍历这么多次
        for (int num = 0; num < row * col; num++) {
            matrix[i][j] = num + 1;
            switch (dir) {
                case 0:
                    //左
                    if (j == maxCol) {
                        //换方向
                        dir = (dir + 1) % 4;
                        minRow++;
                        i++;
                    } else {
                        j++;
                    }
                    break;
                case 1:
                    //下
                    if (i == maxRow) {
                        dir = (dir + 1) % 4;
                        maxCol--;
                        j--;
                    } else {
                        i++;
                    }
                    break;
                case 2:
                    //右
                    if (j == minCol) {
                        dir = (dir + 1) % 4;
                        maxRow--;
                        i--;
                    } else {
                        j--;
                    }
                    break;
                case 3:
                    //上
                    if (i == minRow) {
                        dir = (dir + 1) % 4;
                        minCol++;
                        j++;
                    } else {
                        i--;
                    }
                    break;
            }
        }
        return matrix;
    }
}
