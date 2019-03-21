/**
 * 905. 按奇偶排序数组
 *
 * 给定一个非负整数数组 A，返回一个由 A 的所有偶数元素组成的数组，后面跟 A 的所有奇数元素。
 * 你可以返回满足此条件的任何数组作为答案。
 *
 * 示例：
 * 输入：[3,1,2,4]
 * 输出：[2,4,3,1]
 * 输出 [4,2,3,1]，[2,4,1,3] 和 [4,2,1,3] 也会被接受。
 *
 * 提示：
 * 1 <= A.length <= 5000
 * 0 <= A[i] <= 5000
 */
public class LeetCode905 {

    /**
     * 两头搞
     * @param A
     * @return
     */
    public int[] sortArrayByParity(int[] A) {
        int[] result = new int[A.length];
        int index0 = 0;
        int index1 = A.length - 1;
        for(int i = 0; i < A.length; i++){
            if(A[i] % 2 == 0){
                result[index0++] = A[i];
            }else {
                result[index1--] = A[i];
            }
        }
        return result;
    }

    /**
     * O(1)下的快慢针
     * @param A
     * @return
     */
    public int[] sortArrayByParity2(int[] A) {
        int i = 0;
        int j = A.length - 1;
        while (i <= j){
            if(A[i] % 2 == 0){
                i++;
            }else if(A[j] % 2 == 1){
                j--;
            }else {
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
                i++;
                j--;
            }
        }
        return A;
    }
}
