/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 *
 * 示例 2：
 * 输入: "cbbd"
 * 输出: "bb"
 */
public class LeetCode5 {

    /**
     * 从字符串中心开始找
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if(s == null || s.length() == 0){
            return "";
        }
        int start = 0;
        int end = 0;
        for(int i = 0; i < s.length(); i ++){
            int len1 = palindrome(s, i, i);
            int len2 = palindrome(s, i, i + 1);
            int len = Math.max(len1, len2);
            if(len >= end - start + 1){
                start = i - (len - 1)/2;
                end = i + len/2;
            }

        }
        return s.substring(start, end + 1);
    }

    private int palindrome(String s, int start, int end){
        while (start >= 0 && end <= s.length() - 1 && s.charAt(start) == s.charAt(end)){
            start--;
            end++;
        }
        //end - start + 1 - 2;
        return end - start - 1;
    }

    /**
     * Manacher算法 马拉车算法
     * @param s
     * @return
     */
    public String longestPalindrome2(String s) {
        if(s == null || s.length() == 0){
            return "";
        }
        //构建新的字符串
        StringBuffer buffer = new StringBuffer();
        buffer.append('$');
        for(int i = 0; i < s.length(); i++){
            buffer.append('#').append(s.charAt(i));
        }
        buffer.append('#');
        String newStr = buffer.toString();
        int[] p = new int[newStr.length()];
        p[0] = 0;
        int midIndex = 0;

        int id = 0;
        int mx = 0;
        int maxLen = -1;
        int begin = 0;
        for(int i = 1; i < newStr.length(); i++){
            if(i < mx){
                p[i] = Math.min(p[ 2 * id - i], mx - i);
            }else {
                p[i] = 1;
            }
            //遍历一下
            while (i + p[i] < newStr.length() && newStr.charAt(i - p[i]) == newStr.charAt(i + p[i])){
                p[i] = p[i] + 1;
            }
            if(mx < i + p[i]){
                id = i;
                mx = i + p[i];
            }
            if(maxLen < p[i]){
                maxLen = p[i];
                midIndex = i;
                begin = (midIndex - p[i])/2;
            }
        }
        return s.substring(begin, begin + p[midIndex] - 1);
    }
    public static void main(String[] args) {
        String s = "babad";
        LeetCode5 leetCode5 = new LeetCode5();
        System.out.println(leetCode5.longestPalindrome2(s));
    }
}
