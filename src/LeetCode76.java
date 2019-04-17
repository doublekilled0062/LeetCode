/**
 * 76. 最小覆盖子串
 *
 * 给定一个字符串 S 和一个字符串 T，请在 S 中找出包含 T 所有字母的最小子串。
 * 示例：
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 * 说明：
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 *
 */
public class LeetCode76 {
    /**
     * 滑动窗口
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        if(s == null || s.length() == 0){
            return "";
        }
        //数组长度123以上更合适 122-65 不用-A
        int[] needList = new int[128];
        int[] findList = new int[128];
        char[] ts = t.toCharArray();
        for(char c : ts){
            needList[c]++;
        }

        int left = 0;
        int right = 0;

        int total = 0;
        int minLen = Integer.MAX_VALUE;
        //不暂存结果 只暂存索引 最后返回结果再subString
        int minLeft = 0;
        int minRight = 0;

        char[] ss = s.toCharArray();
        for(; right < s.length(); right++){
            if(needList[ss[right]] <= 0){
                //不是模式串的字符
                continue;
            }
            //找到一个合适字符 +1
            findList[ss[right]]++;
            if(findList[ss[right]] <=  needList[ss[right]]){
                //total计数
                total++;
            }
            if(total == t.length()){
                //开始滑左窗
                while (needList[ss[left]] == 0 || findList[ss[left]] > needList[ss[left]]){
                    if(findList[ss[left]] > needList[ss[left]]){
                        findList[ss[left]]--;
                    }
                    left++;
                }
                //滑完以后暂存结果
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minLeft = left;
                    minRight = right;
                }
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minLeft, minRight + 1);
    }

    public static void main(String[] args) {
        LeetCode76 leetCode76 = new LeetCode76();
        System.out.println(leetCode76.minWindow("ADOBECODEBANC","ABC"));
    }
}
