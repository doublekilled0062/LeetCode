/**
 * 942. 增减字符串匹配
 *
 * 给定只含 "I"（增大）或 "D"（减小）的字符串 S ，令 N = S.length。
 * 返回 [0, 1, ..., N] 的任意排列 A 使得对于所有 i = 0, ..., N-1，都有：
 * 如果 S[i] == "I"，那么 A[i] < A[i+1]
 * 如果 S[i] == "D"，那么 A[i] > A[i+1]
 *
 * 示例 1：
 * 输入："IDID"
 * 输出：[0,4,1,3,2]
 *
 * 示例 2：
 * 输入："III"
 * 输出：[0,1,2,3]
 *
 * 示例 3：
 * 输入："DDI"
 * 输出：[3,2,0,1]
 *
 * 提示：
 * 1 <= S.length <= 1000
 * S 只包含字符 "I" 或 "D"。
 */
public class LeetCode942 {
    /**
     * 定义最大值和最小值
     * 从头开始 遇到I放最小 遇到D放最大
     * @param S
     * @return
     */
    public int[] diStringMatch(String S) {
        int len = S.length();
        int max = len;
        int min = 0;
        int[] result = new int[len + 1];
        char[] chars = S.toCharArray();
        for(int i = 0; i < len; i++){
            if(chars[i] == 'I'){
                result[i] = min++;
            }else {
                result[i] = max--;
            }
        }
        result[len] = max;
        return result;
    }
}
