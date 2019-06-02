import java.util.*;

/**
 * 140. 单词拆分 II
 *
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，
 * 在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。
 * 返回所有这些可能的句子。
 * 说明：
 * 分隔时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 *
 * 示例 1：
 * 输入:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * 输出:
 * [
 * "cats and dog",
 * "cat sand dog"
 * ]
 *
 * 示例 2：
 * 输入:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * 输出:
 * [
 * "pine apple pen apple",
 * "pineapple pen apple",
 * "pine applepen apple"
 * ]
 * 解释: 注意你可以重复使用字典中的单词。
 *
 * 示例 3：
 * 输入:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出:
 * []
 */
public class LeetCode140 {
    /**
     * 先判断能不能分隔 能分割再回溯这样dp就要保存上一个合法的索引列表
     * @param s
     * @param wordDict
     * @return
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        //长度为len+1 处理起来简单一些  表示到i-1的字符串是否符合条件
        List<Integer>[] dp = new ArrayList[len + 1];
        dp[0] = new ArrayList<>();
        dp[0].add(0);
        for(int i = 1; i <= len; i++){
            dp[i] = new ArrayList<>();
            //按单词遍历
            for(String word : wordDict){
                if(i >= word.length() && word.equals(s.substring(i - word.length(), i))){
                    if(dp[i - word.length()].isEmpty()){
                        continue;
                    }
                    dp[i].add(i - word.length());
                }
            }
        }
        //提前计算不合法的情况
        if(dp[len].isEmpty()){
            return new ArrayList<>();
        }

        List<String>[] result = new ArrayList[len + 1];
        for(int i = 1; i <= len; i++){
            if(dp[i].isEmpty()){
                continue;
            }
            result[i] = new ArrayList<>();
            for (int j : dp[i]) {
                if (result[j] == null || result[j].isEmpty()) {
                    result[i].add(s.substring(j, i));
                } else {
                    for (String str : result[j]) {
                        result[i].add(str + ' ' + s.substring(j, i));
                    }
                }
            }
        }

        return result[len];
    }

    /**
     * 这个效率高是因为开始判定能不能拆分的时候根据leetcode139的方法构造一维数组比存详细信息快
     * 然后根据一维数组穷举所有的情况 引入单词的长度的范围能更好剪枝
     * 论证方式在第三种解法 快速验证 然后再回溯
     * @param s
     * @param wordDict
     * @return
     */
    public List<String> wordBreak2(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        int max = 0, min = Integer.MAX_VALUE;
        Set<String> set = new HashSet<>();
        for (String word : wordDict) {
            set.add(word);
            max = Integer.max(max, word.length());
            min = Integer.min(min, word.length());
        }

        boolean f[] = new boolean[s.length() + 1];
        f[0] = true;
        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = Math.max(i - max, 0); j <= i - min; j++) {
                if (f[j] && set.contains(s.substring(j, i))) {
                    f[i] = true;
                    break;
                }
            }
        }
        if (f[s.length()]) {
            dfs(s, res, new StringBuilder(), set, 0, max, min);
        }
        return res;
    }

    private void dfs(String s, List<String> res, StringBuilder sb, Set<String> set, int index, int max, int min) {
        if (index == s.length()) {
            sb.deleteCharAt(sb.length() - 1);
            res.add(sb.toString());
            return;
        }
        String str;
        int size;
        for (int i = index + min; i <= s.length() && i <= index + max; i++) {
            if (set.contains(str = s.substring(index, i))) {
                size = sb.length();
                sb.append(str).append(' ');
                dfs(s, res, sb, set, i, max, min);
                sb.delete(size, sb.length());
            }
        }
    }

    public List<String> wordBreak3(String s, List<String> wordDict) {
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
        if(!dp[len]){
            return new ArrayList<>();
        }

        List<String> res = new ArrayList<>();
        int max = 0, min = Integer.MAX_VALUE;
        Set<String> set = new HashSet<>();
        for (String word : wordDict) {
            set.add(word);
            max = Integer.max(max, word.length());
            min = Integer.min(min, word.length());
        }
        dfs(s, res, new StringBuilder(), set, 0, max, min);
        return res;
    }

    public static void main(String[] args) {
        LeetCode140 leetCode140 = new LeetCode140();
        System.out.println(leetCode140.wordBreak("leetcode", new ArrayList<String>() {{
            add("leet");
            add("code");
        }}));
        System.out.println(leetCode140.wordBreak("aaaaaaaaaaaaabaaaaaaaaaaaaaaa",
                new ArrayList<String>() {{
                    add("ab");
                    add("aa");
                    add("aaa");
                    add("aaaa");
                    add("aaaaa");
                    add("aaaaaa");
                    add("aaaaaaa");
                    add("aaaaaaaa");
                    add("aaaaaaaaa");
                    add("aaaaaaaaaa");
                }}));
    }
}
