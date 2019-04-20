/**
 * 97. 交错字符串
 *
 * 给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。
 * 示例 1:
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出: true
 * 示例 2:
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出: false
 *
 */
public class LeetCode97 {
    /**
     * 回溯如果思路不对很容易超时
     * 因为是按顺序匹配 再第i+j+1个字符 要么等于i+1 要么等于j+1
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1 == null || s2 == null || s3 == null){
            return false;
        }
        if(s1.length() + s2.length() != s3.length()){
            return false;
        }
        return isInterleave(s1.toCharArray(), 0, s2.toCharArray(), 0, s3.toCharArray(), new boolean[s1.length() + 1][s2.length() + 1]);
    }

    /**
     * 主要就是used剪枝 因为前i,j的交错字符串i+j可能有多种组合模式 往下算一种即可
     * @param s1
     * @param s1index
     * @param s2
     * @param s2index
     * @param s3
     * @param used
     * @return
     */
    public boolean isInterleave(char[] s1, int s1index, char[] s2, int s2index, char[] s3, boolean[][] used) {
        if(used[s1index][s2index]){
            return false;
        }
        used[s1index][s2index] = true;
        if (s1index == s1.length && s2index == s2.length) {
            return true;
        }
        if (s1index < s1.length && s1[s1index] == s3[s1index + s2index]){
            if(isInterleave(s1, s1index + 1, s2, s2index, s3, used)){
                return true;
            }
        }
        if(s2index < s2.length && s2[s2index] == s3[s1index + s2index]){
            if(isInterleave(s1, s1index, s2, s2index + 1, s3, used)){
                return true;
            }
        }
        return false;
    }

    /**
     * 模型转化 相当于展平为一个二维地图，判断是否能从左上角走到右下角。
     * 当s1到达第i个元素，s2到达第j个元素:
     * 地图上往右一步就是s2[j-1]匹配s3[i+j-1]。
     * 地图上往下一步就是s1[i-1]匹配s3[i+j-1]。
     * 示例：s1="aa",s2="ab",s3="aaba"。标1的为可行。最终返回右下角。
     *     0  a  b
     * 0   1  1  0
     * a   1  1  1
     * a   1  0  1
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave2(String s1, String s2, String s3) {
        if(s1 == null || s2 == null || s3 == null){
            return false;
        }
        int len1 = s1.length();
        int len2 = s2.length();
        if(len1 + len2 != s3.length()){
            return false;
        }
        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        dp[0][0] = true;
        for(int i = 1; i <= len1; i++){
            if(dp[i-1][0] && (s1.charAt(i-1)== s3.charAt(i-1))){
                dp[i][0] = true;
            }
        }
        for(int j = 1; j <= len2;  j++) {
            if(dp[0][j-1] && (s2.charAt(j-1) ==  s3.charAt(j-1))){
                dp[0][j] = true;
            }
        }

        for(int i = 1; i <= len1; i++){
            for(int j = 1;j <= len2; j++){
                dp[i][j]=(dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1))
                        || (dp[i][j-1] && s2.charAt(j-1) ==s3.charAt(i+j-1));
            }
        }
        return dp[len1][len2];
    }

    public static void main(String[] args) {
        LeetCode97 leetCode97 = new LeetCode97();
        System.out.println(leetCode97.isInterleave("aabcca", "dbbcab", "aadbbcbcacab"));
        System.out.println(leetCode97.isInterleave("abbbbbbcabbacaacccababaabcccabcacbcaabbbacccaaaaaababbbacbb", "ccaacabbacaccacababbbbabbcacccacccccaabaababacbbacabbbbabc",
                "cacbabbacbbbabcbaacbbaccacaacaacccabababbbababcccbabcabbaccabcccacccaabbcbcaccccaaaaabaaaaababbbbacbbabacbbacabbbbabc"));
//        System.out.println(leetCode97.isInterleave("a", "b", "ab"));
    }
}
