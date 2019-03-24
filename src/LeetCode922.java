/**
 * 922. 按奇偶排序数组 II
 *
 * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
 * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
 * 你可以返回任何满足上述条件的数组作为答案。
 *
 * 示例：
 * 输入：[4,2,5,7]
 * 输出：[4,5,2,7]
 * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
 *
 * 提示：
 * 2 <= A.length <= 20000
 * A.length % 2 == 0
 * 0 <= A[i] <= 1000
 */
public class LeetCode922 {
    /**
     * 双指针
     * @param A
     * @return
     */
    public int[] sortArrayByParityII(int[] A) {
        int i = 0;
        int j = 1;
        while (i < A.length && j < A.length){
            if((A[i] & 1) == 0){
                i += 2;
            }else if((A[j] & 1) == 1){
                j += 2;
            }else {
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
                i += 2;
                j += 2;
            }
        }
        return A;
    }

    /**
     * 还是官方题解快点
     * 原因是 上面的每次都回到循环开始有重复判断
     * 不如直接找到一个合适的
     * 然后 上机结果 实际并没有快
     * @param A
     * @return
     */
    public int[] sortArrayByParityII2(int[] A) {
        int j = 1;
        for(int i = 0; i < A.length - 1; i += 2){
            if((A[i] & 1) != 0){
                while ((A[j] & 1) != 0){
                    j = j + 2;
                }
                //不用进行边界判断，因为只要有一个非法的 肯定能在奇数位也找到一个
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
        }
        return A;
    }
}
