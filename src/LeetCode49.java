import java.util.*;

/**
 * 49. 字母异位词分组
 *
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * 示例:
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * 输出:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * 说明：
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 */
public class LeetCode49 {
    /**
     * 题解的判重也不是很高明。。。先实现一下
     * 以为有什么高深算法。。
     * 题解两个做法只是判重key的方式不同
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        if(strs == null || strs.length == 0){
            return new ArrayList<>();
        }
        Map<String, List> ans = new HashMap<String, List>();
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String key = String.valueOf(ca);
            if (!ans.containsKey(key)) {
                ans.put(key, new ArrayList());
            }
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }

    /**
     * 多元化只是key的特色
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        int[] nums = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29,
                31, 37, 41, 43, 47, 53, 59, 61, 67, 71,
                73, 79, 83, 89, 97, 101};
        if(strs == null || strs.length == 0){
            return new ArrayList<>();
        }
        Map<Long, List> ans = new HashMap<Long, List>();
        for (String s : strs) {
            char[] ca = s.toCharArray();
            long key = 1;
            for(int i = 0; i < s.length(); i++){
                key = key * nums[ca[i] - 'a'];
            }
            if (!ans.containsKey(key)) {
                ans.put(key, new ArrayList());
            }
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }

    public static void main(String[] args) {
        LeetCode49 leetCode49 = new LeetCode49();
        leetCode49.groupAnagrams2(new String[]{"cab","tin","pew","duh","may","ill","buy","bar","max","doc"});
    }
}
