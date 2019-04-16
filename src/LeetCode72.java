/**
 * 72. 编辑距离
 *
 * 给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。
 * 你可以对一个单词进行如下三种操作：
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * 示例 1:
 * 输入: word1 = "horse", word2 = "ros"
 * 输出: 3
 * 解释:
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2:
 * 输入: word1 = "intention", word2 = "execution"
 * 输出: 5
 * 解释:
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 */
public class LeetCode72 {
    /**
     * 莱文斯坦编辑距离
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        if(word1 == null && word2 == null){
            return 0;
        }
        if(word1 == null || word1.length() == 0){
            return word2.length();
        }
        if(word2 == null || word2.length() == 0){
            return word1.length();
        }
        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();
        int[][] dp = new int[word1.length()][word2.length()];
        if(chars1[0] == chars2[0]){
            dp[0][0] = 0;
        }else {
            dp[0][0] = 1;
        }
        for(int i = 1; i < word1.length(); i++){
            if(chars1[i] == chars2[0]){
                dp[i][0] = i;
            }else {
                dp[i][0] = dp[i-1][0] + 1;
            }
        }
        for(int j = 1; j < word2.length(); j++){
            if(chars1[0] == chars2[j]){
                dp[0][j] = j;
            }else {
                dp[0][j] = dp[0][j-1] + 1;
            }
        }
        for(int i = 1; i < word1.length(); i++){
            for(int j = 1; j < word2.length(); j++){
                if(chars1[i] == chars2[j]){
                    dp[i][j] = dp[i-1][j-1];
                }else {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                }
            }
        }
        return dp[word1.length() - 1][word2.length() - 1];
    }

    /**
     * 差不多就是优化下代码行数
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance2(String word1, String word2) {
        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];

        for(int i = 0; i <= word1.length(); i++){
            dp[i][0] = i;
        }
        for(int j = 0; j <= word2.length(); j++){
            dp[0][j] = j;
        }
        for(int i = 0; i < word1.length(); i++){
            for(int j = 0; j < word2.length(); j++){
                if(chars1[i] == chars2[j]){
                    dp[i + 1][j + 1] = dp[i][j];
                }else {
                    dp[i + 1][j + 1] = Math.min(Math.min(dp[i][j+1], dp[i+1][j]), dp[i][j]) + 1;
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
}