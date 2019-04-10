/**
 * 44. 通配符匹配
 *
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * 两个字符串完全匹配才算匹配成功。
 * 说明:
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
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
 * p = "*"
 * 输出: true
 * 解释: '*' 可以匹配任意字符串。
 *
 * 示例 3:
 * 输入:
 * s = "cb"
 * p = "?a"
 * 输出: false
 * 解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
 *
 * 示例 4:
 * 输入:
 * s = "adceb"
 * p = "*a*b"
 * 输出: true
 * 解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
 *
 * 示例 5:
 * 输入:
 * s = "acdcb"
 * p = "a*c?b"
 * 输入: false
 */
public class LeetCode44 {
    boolean match = false;  //剪枝用

    /**
     * 回溯算法超时。。安心用动态规划吧
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        if(s == null){
            if(p == null){
                return true;
            }else {
                return false;
            }
        }
        match = false;
        isMatch(s.toCharArray(), 0, p.toCharArray(), 0);
        return match;
    }


    public void isMatch(char[] s, int indexs, char[] p, int indexp){
        if(match){
            return;
        }
        if(indexp == p.length){
            if(indexs == s.length){
                match = true;
            }
            return;
        }
        if(p[indexp] == '?'){
            //看题目 ? 只匹配一个字符
//            isMatch(s, indexs, p, indexp + 1);
            isMatch(s, indexs + 1, p, indexp + 1);
        }else if(p[indexp] == '*'){
            for(int i = 0; i <= s.length - indexs; i++){
                isMatch(s, indexs + i, p, indexp + 1);
            }
        }else if(indexs < s.length && s[indexs] == p[indexp]){
            isMatch(s, indexs + 1, p, indexp + 1);
        }else {
            return;
        }
    }

    /**
     * 动态规划 比leetcode10简单的动态规划
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch2(String s, String p) {
        if(p == null || s == null){
            return false;
        }

        char[] ss = s.toCharArray();
        char[] ps = p.toCharArray();

        boolean[][] dp = new boolean[ss.length + 1][ps.length + 1];
        dp[0][0] = true;

        //初始化这个不能丢
        for(int i = 1; i < ps.length + 1; i++){
            if (ps[i - 1] == '*' && dp[0][i - 1]){
                dp[0][i] = true;
            }
            //这里可以优化走到一个false就不用往下走了
            if(dp[0][i] == false){
                break;
            }
        }
        for(int i = 0; i < s.length(); i++){
            for(int j = 0; j < p.length(); j++){
                if(ps[j] == '?'){
                    dp[i + 1][j + 1] = dp[i][j];
                }else if(ps[j] == '*'){
                    dp[i + 1][j + 1] = dp[i][j] || dp[i][j + 1] || dp[i + 1][j];
                }else if(ss[i] == ps[j]){
                    dp[i + 1][j + 1] = dp[i][j];
                }
            }
        }
        return dp[ss.length][ps.length];
    }
    public static void main(String[] args) {
        LeetCode44 leetCode44 = new LeetCode44();
        System.out.println(leetCode44.isMatch2("aa", "a"));
        System.out.println(leetCode44.isMatch2("aa", "a*"));
        System.out.println(leetCode44.isMatch2("cb", "?a"));
        System.out.println(leetCode44.isMatch2("adceb", "*a*b"));
        System.out.println(leetCode44.isMatch2("acdcb","a*c?b"));
        System.out.println(leetCode44.isMatch2("aaabbbaabaaaaababaabaaabbabbbbbbbbaabababbabbbaaaaba","a*******b"));
        System.out.println(leetCode44.isMatch2("ab", "?*"));
        System.out.println(leetCode44.isMatch2("a", "a*"));
    }
}
