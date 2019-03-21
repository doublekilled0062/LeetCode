/**
 * 977. 有序数组的平方
 *
 * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
 * 示例 1：
 * 输入：[-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 *
 * 示例 2：
 * 输入：[-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 *
 * 提示：
 * 1 <= A.length <= 10000
 * -10000 <= A[i] <= 10000
 * A 已按非递减顺序排序。
 */
public class LeetCode977 {
    /**
     * 思路 先二分查找第一个大于0的下标索引
     * 第一版理解错意思了 以为小于0的倒序 大于0的正序
     * 这个算完全排序
     * @param A
     * @return
     */
    public int[] sortedSquares(int[] A) {
        int index = binarySearch(A);
        int[] result = new int[A.length];

        int i = index - 1;
        int j = index;
        int k = 0;
        while (i >= 0 && j <= A.length - 1){
            if(A[i]*A[i] >= A[j]*A[j]){
                result[k] = A[j] * A[j];
                j++;
            }else {
                result[k] = A[i] * A[i];
                i--;
            }
            k++;
        }
        if(i >= 0){
            for(; i >= 0; i--){
                result[k] = A[i] * A[i];
                k++;
            }
        }
        if(index <= A.length - 1){
            for(; j <= A.length - 1; j++){
                result[k] = A[j] * A[j];
                k++;
            }
        }
        return result;
    }

    /**
     * 返回第一个大于等于0的索引
     * @param nums
     * @return
     */
    private int binarySearch(int[] nums){
        int start = 0;
        int end = nums.length - 1;
        while (start <= end){
            int mid = start + (end - start)/2;
            if(nums[mid] >= 0){
                if(mid == 0 || nums[mid - 1] < 0){
                    return mid;
                }else {
                    end = mid - 1;
                }
            }else {
                if(mid == nums.length - 1 || nums[mid + 1] >= 0){
                    return mid + 1;
                }else {
                    start = mid + 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        LeetCode977 leetCode977 = new LeetCode977();
        int[] nums = leetCode977.sortedSquares(new int[]{-4,-1,0,3,10});
        for(int i = 0; i < nums.length; i++){
            System.out.println(nums[i]);
        }
    }
}
