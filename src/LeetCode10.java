/**
 * 10. 正则表达式匹配
 *
 * 给定一个字符串 (s) 和一个字符模式 (p)。实现支持 '.' 和 '*' 的正则表达式匹配。
 * '.' 匹配任意单个字符。
 * '*' 匹配零个或多个前面的元素。
 * 匹配应该覆盖整个字符串 (s) ，而不是部分字符串。
 *
 * 说明:
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 *
 * 示例 1:
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 *
 * 示例 2:
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: '*' 代表可匹配零个或多个前面的元素, 即可以匹配 'a' 。因此, 重复 'a' 一次, 字符串可变为 "aa"。
 *
 * 示例 3:
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个('*')任意字符('.')。
 *
 * 示例 4:
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 'c' 可以不被重复, 'a' 可以被重复一次。因此可以匹配字符串 "aab"。
 *
 * 示例 5:
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 */
public class LeetCode10 {
    /**
     * 看答案刚研究明白
     * 主要是开始的第一列初始化和bb与b*的递推难懂
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        if(p == null || s == null){
            return false;
        }

        char[] ss = s.toCharArray();
        char[] ps = p.toCharArray();

        boolean[][] dp = new boolean[ss.length + 1][ps.length + 1];
        dp[0][0] = true;

        //初始化这个不能丢
        for(int i = 1; i < ps.length; i++){
            if (ps[i] == '*' && dp[0][i - 1])
                dp[0][i + 1] = true;
        }

        //不怕""情况，因为dp[0][0]有值 索引都+1
        for(int i = 0; i < ss.length; i++){
            for(int j = 0; j < ps.length; j++){
                if(ss[i] == ps[j]){
                    //本次结果受 前一个的结果影响 前一个匹配则匹配 前一个不匹配则这个也不匹配
                    dp[i + 1][j + 1] = dp[i][j];
                }else if(ps[j] == '.'){
                    //和上个分支情况一个 就是'.'是匹配任意字符的
                    dp[i + 1][j + 1] = dp[i][j];
                }else if(ps[j] == '*'){
                    //需要看ps[j-1] 和 ss[i]的情况
                    if(ps[j - 1] != ss[i] && ps[j - 1] != '.'){
                        //类似 ab和c* c*必须为空
                        dp[i+1][j+1] = dp[i+1][j-1];
                    }else {
                        dp[i+1][j+1] = dp[i + 1][j] //ab 和 b*
                                      ||dp[i][j+1]  //bb和b*  这个是一直往回倒的过程 倒第一个ab和b*
                                      ||dp[i+1][j-1]; //ab 和 .*
                    }
                }
            }
        }
        return dp[ss.length][ps.length];
    }

    public static void main(String[] args) {
        LeetCode10 leetCode10 = new LeetCode10();
        System.out.println(leetCode10.isMatch("aab", "c*a*b"));
    }

}
