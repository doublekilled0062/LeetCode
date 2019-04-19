/**
 * 91. 解码方法
 *
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 * 示例 1:
 * 输入: "12"
 * 输出: 2
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2:
 * 输入: "226"
 * 输出: 3
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 */
public class LeetCode91 {
    /**
     * 斐波那契数列变形 所以动态规划最佳
     * 特殊点要考虑 如果首位是0 直接返回0无法编码
     * 如果中间位是0 要看前一位 如果前一位大于2 也没法编码
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        char[] chars = s.toCharArray();
        int len = s.length();
        int[] dp = new int[len];

        if (chars[0] == '0') {
            return 0;
        } else {
            if(len == 1){
                return 1;
            }
            dp[0] = 1;
        }

        if(chars[1] == '0'){
            if(chars[0] > '2' || chars[0] <= '0'){
                return 0;
            }else {
                dp[1] = 1;
            }
        }else {
            if (chars[0] == '1' || (chars[0] == '2' && chars[1] <= '6')) {
                dp[1] = 2;
            }else {
                dp[1] = 1;
            }
        }

        for(int i = 2; i < s.length(); i++){
            if (chars[i] == '0') {
                //要看前一个
                if (chars[i - 1] > '2' || chars[i - 1] <= '0') {
                    return 0;
                } else {
                    dp[i] = dp[i - 2];
                }
            } else {
                if (chars[i - 1] == '1' || (chars[i - 1] == '2' && chars[i] <= '6')) {
                    dp[i] = dp[i - 1] + dp[i - 2];
                } else {
                    dp[i] = dp[i - 1];
                }
            }
        }
        return dp[s.length() - 1];
    }

    public static void main(String[] args) {
        LeetCode91 leetCode91 = new LeetCode91();
        System.out.println(leetCode91.numDecodings("226312"));
        System.out.println(leetCode91.numDecodings("10"));
        System.out.println(leetCode91.numDecodings("100"));
        System.out.println(leetCode91.numDecodings("101"));
        System.out.println(leetCode91.numDecodings("17"));
    }
}
