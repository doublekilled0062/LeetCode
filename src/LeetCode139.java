import java.util.ArrayList;
import java.util.List;

/**
 * 139. 单词拆分
 *
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * 说明：
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 *
 * 示例 1：
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 *
 * 示例 2：
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 * 注意你可以重复使用字典中的单词。
 *
 * 示例 3：
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 */
public class LeetCode139 {
    /**
     * 0-1背包问题
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        //长度为len+1 处理起来简单一些  表示到i-1的字符串是否符合条件
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for(int i = 1; i <= len; i++){
            dp[i] = false;
            for(int j = 0; j < i; j++){
                if(dp[j] && wordDict.contains(s.substring(j, i))){
                    dp[i] =  true;
                    break;
                }
            }
        }
        return dp[len];
    }

    /**
     * 第二层循环按单词遍历
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak2(String s, List<String> wordDict) {
        int len = s.length();
        //长度为len+1 处理起来简单一些  表示到i-1的字符串是否符合条件
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for(int i = 1; i <= len; i++){
            dp[i] = false;
            //按单词遍历
            for(String word : wordDict){
                if(i >= word.length() && word.equals(s.substring(i - word.length(), i))){
                    dp[i] = dp[i - word.length()];
                    if(dp[i]){
                        break;
                    }
                }
            }
        }
        return dp[len];
    }

    /**
     * 递归求解 和上面动态规划其实思想一样
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak3(String s, List<String> wordDict) {
        //表示一个空格放到这里合适不合适
        Boolean[] dp = new Boolean[s.length()];
        return wordBreak(0, s, wordDict, dp);
    }

    private boolean wordBreak(int index, String s, List<String> wordDict, Boolean[] dp) {
        if (index == s.length()) {
            return true;
        }
        if (dp[index] != null) {
            return dp[index];
        }
        boolean canSplit = false;
        for (String s1 : wordDict){
            if (s.startsWith(s1, index)) {
                canSplit = canSplit || wordBreak(index + s1.length(), s, wordDict, dp);
            }
        }
        dp[index] = canSplit;
        return canSplit;
    }

    public static void main(String[] args) {
        LeetCode139 leetCode139 = new LeetCode139();
        System.out.println(leetCode139.wordBreak3("leetcode", new ArrayList<String>(){{
            add("leet");
            add("code");
        }}));
    }
}
