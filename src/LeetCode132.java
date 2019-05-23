/**
 * 132. 分割回文串 II
 *
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 * 返回符合要求的最少分割次数。
 * 示例:
 * 输入: "aab"
 * 输出: 1
 * 解释: 进行一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
 *
 */
public class LeetCode132 {
    /**
     * 一开始尝试了各种leetcode131的回溯优化 类似 https://blog.csdn.net/yutianzuijin/article/details/16850031 的思路结果被打脸
     * 然后搞出来个动态规划
     * cut[i]表示第 0到i 之间的最小回文串cut
     * cut[n-1] = min(1 + cut[i])  且对所有的i，i+1到n-1回文
     * dp[i][j]表示i到j回文
     * dp[i][j] = s.charAt(i)==s.charAt(j) && j >= i && (j - i <=1 || dp[i+1][j-1])
     * 可以一边更新回文矩阵 一边计算最小值
     * 40ms 25%
     * @param s
     * @return
     */
    public int minCut(String s) {
        if(s == null || s.isEmpty()){
            return 0;
        }
        int len = s.length();
        boolean[][] dp = new boolean[s.length()][s.length()];
        int[] cut = new int[s.length()];

        for(int i = 0; i < len; i++){
            //最大划分就是i次
            cut[i]= i;
            for(int j = 0; j <= i; j++){
                if(s.charAt(i) == s.charAt(j) &&(i-j <= 1 || dp[j+1][i-1])){
                    dp[j][i] = true;
                    if(j == 0) {
                        //0-i直接是回文
                        cut[i] = 0;
                    } else {
                        cut[i] = Math.min(cut[j-1]+1, cut[i]);
                    }
                }
            }
        }
        return cut[len-1];
    }

    /**
     * 别人的高效算法 4ms的 需要好好研究
     * 遍历一边给的串 以遍历点为中心找最大的回文串 更新dp
     * 回文串有两种方式left为中心的奇数型 以left，left+1为中心的偶数型
     * @param s
     * @return
     */
    public int minCut2(String s) {
        int len = s.length();
        int[] f = new int[len];
        for (int i = 0; i < len; i++) {
            f[i] = i;
        }

        for (int i = 0; i < len; i++) {
            search(s, f, i, i);
            search(s, f, i, i + 1);
        }

        // System.out.println(Arrays.toString(f));
        return f[len - 1];
    }

    private void search(String s, int[] f, int left, int right) {
        while (left >= 0 && right < s.length()
                && s.charAt(left) == s.charAt(right)) {
            if (left == 0) {
                // left == 0, substring(0, right+1) is palindrom, no cut needed
                f[right] = 0;
            } else {
                f[right] = Math.min(f[right], f[left - 1] + 1);
            }
            left--;
            right++;
        }
    }


    public static void main(String[] args) {
        LeetCode132 leetCode132 = new LeetCode132();
//        System.out.println(leetCode132.minCut("abba"));
        System.out.println(leetCode132.minCut("apjesgpsxoeiokmqmfgvjslcjukbqxpsobyhjpbgdfruqdkeiszrlmtwgfxyfostpqczidfljwfbbrflkgdvtytbgqalguewnhvvmcgxboycffopmtmhtfizxkmeftcucxpobxmelmjtuzigsxnncxpaibgpuijwhankxbplpyejxmrrjgeoevqozwdtgospohznkoyzocjlracchjqnggbfeebmuvbicbvmpuleywrpzwsihivnrwtxcukwplgtobhgxukwrdlszfaiqxwjvrgxnsveedxseeyeykarqnjrtlaliyudpacctzizcftjlunlgnfwcqqxcqikocqffsjyurzwysfjmswvhbrmshjuzsgpwyubtfbnwajuvrfhlccvfwhxfqthkcwhatktymgxostjlztwdxritygbrbibdgkezvzajizxasjnrcjwzdfvdnwwqeyumkamhzoqhnqjfzwzbixclcxqrtniznemxeahfozp"));
    }
}
