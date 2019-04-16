/**
 * 74. 搜索二维矩阵
 *
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 示例 1:
 * 输入:
 * matrix = [
 * [1,   3,  5,  7],
 * [10, 11, 16, 20],
 * [23, 30, 34, 50]
 * ]
 * target = 3
 * 输出: true
 * 示例 2:
 * 输入:
 * matrix = [
 * [1,   3,  5,  7],
 * [10, 11, 16, 20],
 * [23, 30, 34, 50]
 * ]
 * target = 13
 * 输出: false
 */
public class LeetCode74 {
    /**
     * 矩阵二分查找
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null){
            return false;
        }
        int row = matrix.length;
        if(row == 0){
            return false;
        }
        int col = matrix[0].length;
        if(col == 0){
            return false;
        }
        int start = 0;
        int end = row * col - 1;
        while (start <= end){
            int mid = start + ((end - start)>>1);
            int i = mid/col;
            int j = mid%col;
            if(matrix[i][j] == target){
                return true;
            }else if(matrix[i][j] < target){
                start = mid + 1;
            }else {
                end = mid - 1;
            }
        }
        return false;
    }

    /**
     * 找到可能存在的那一行 用二分查找
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix2(int[][] matrix, int target) {
        if(matrix == null){
            return false;
        }
        int row = matrix.length;
        if(row == 0){
            return false;
        }
        int col = matrix[0].length;
        if(col == 0){
            return false;
        }
        for(int[] rows : matrix){
            if(rows[col - 1] == target){
                return true;
            }else if(rows[col - 1] > target){
                return binarySearch(rows, target);
            }
        }
        return false;
    }

    private boolean binarySearch(int[] nums, int target){
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + ((end - start)>>1);
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else if (nums[mid] < target) {
                start = mid + 1;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        int[][] matrix = {{1,1}};
        LeetCode74 leetCode74 = new LeetCode74();
        System.out.println(leetCode74.searchMatrix(matrix, 2));
    }
}
