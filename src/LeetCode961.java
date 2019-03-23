/**
 * 961. 重复 N 次的元素
 *
 * 在大小为 2N 的数组 A 中有 N+1 个不同的元素，其中有一个元素重复了 N 次。
 * 返回重复了 N 次的那个元素。
 *
 * 示例 1：
 * 输入：[1,2,3,3]
 * 输出：3
 *
 * 示例 2：
 * 输入：[2,1,2,5,3,2]
 * 输出：2
 *
 * 示例 3：
 * 输入：[5,1,5,2,5,3,5,4]
 * 输出：5
 *
 * 提示：
 * 4 <= A.length <= 10000
 * 0 <= A[i] < 10000
 * A.length 为偶数
 */
public class LeetCode961 {
    /**
     * 判断重复的set hashmap和数组就不实现了
     * 关键看插空法
     * N个数最多有N+1个空位 然后让N个相同的数插
     * 差的一个空 要么在队首 要么在队尾 要么在队中 要么有挨着的
     * @param A
     * @return
     */
    public int repeatedNTimes(int[] A) {
        //空在队中
        if(A[0] == A[A.length - 1]){
            return A[0];
        }

        //空在对首或者队尾 要么等于队首或者队尾 要么挨着
        for(int i = 1; i < A.length - 1; i++){
            if(A[i] == A[0] || A[i] == A[A.length - 1] || A[i] == A[i + 1]){
                return A[i];
            }
        }
        return -1;
    }
}
