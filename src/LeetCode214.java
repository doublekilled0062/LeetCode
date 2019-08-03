/**
 * 214. 最短回文串
 *
 * 给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。
 *
 * 示例 1:
 * 输入: "aacecaaa"
 * 输出: "aaacecaaa"
 *
 * 示例 2:
 * 输入: "abcd"
 * 输出: "dcbabcd"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shortest-palindrome
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode214 {
    /**
     * 指针从中间往左找 然后用双指针判断是不是接近回文
     * 要求往左一定到0，则右指针到最后就都是补全的
     * 例如abbaa 从第二个b找 到找到第一个b
     * 如果pos和pos+1相等 就类似偶数型回文 不等就是奇数型回文
     * 73ms 48%
     * @param s
     * @return
     */
    public String shortestPalindrome(String s) {
        if(s == null || s.length() <= 1){
            return s;
        }
        int pos = (s.length() - 1)/2;
        int right = -1;
        while (pos >= 0){
            if(s.charAt(pos) == s.charAt(pos + 1)){
                if(s.length() % 2 == 0 || pos != s.length()/2){
                    right = likePalindrome(s, pos, pos + 1);
                    if(right > 0){
                        break;
                    }
                }
            }
            right = likePalindrome(s, pos - 1, pos + 1);
            if(right > 0){
                break;
            }
            pos--;
        }
        StringBuilder builder = new StringBuilder();
        for(int i = s.length() - 1; i >= right; i--){
            builder.append(s.charAt(i));
        }
        return builder.append(s).toString();
    }

    /**
     * 符合规则时返回当左指针到-1时返回右指针的位置
     * 不符合返回-1;
     * @param s
     * @param left
     * @param right
     * @return
     */
    private int likePalindrome(String s, int left, int right){
        while (left >= 0){
            if(s.charAt(left) == s.charAt(right)){
                left--;
                right++;
            }else {
                return -1;
            }
        }
        return right;
    }

    /**
     * 从i=len开始依次判断 字符串是不是回文
     * 312ms...
     * @param s
     * @return
     */
    public String shortestPalindrome1(String s) {
        if(s == null || s.length() <= 1){
            return s;
        }
        StringBuilder builder = new StringBuilder();
        for(int i = s.length() - 1; i >= 0; i--){
            if(isPalindrome(s, i)){
                return builder.append(s).toString();
            }
            builder.append(s.charAt(i));
        }
        return s;

    }

    private boolean isPalindrome(String s, int pos){
        int left = 0;
        int right = pos;
        while (left < right){
            if(s.charAt(left) != s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * 找的题解
     * 需要证明 [0,i)包括最大的回文子串 然后递归
     * 如果i本身回文 显然成立
     * 反证如果[0,k]回文 i最差要走k次到k+1(因为j至少从k走到0)
     * @param s
     * @return
     */
    public String shortestPalindrome2(String s) {
        int i = 0;
        for(int j = s.length() - 1; j >= 0; j--){
            if(s.charAt(j) == s.charAt(i)){
                i++;
            }
        }
        if(i == s.length()){
            return s;
        }
        return reverse(s.substring(i)) + shortestPalindrome2(s.substring(0, i)) + s.substring(i);
    }

    private String reverse(String s){
        StringBuilder stringBuilder = new StringBuilder(s);
        return stringBuilder.reverse().toString();
    }

    /**
     * 网上资料kmp也可以
     * 应该马拉车也可以
     * 不过应该都没有递归那个快
     * @param s
     * @return
     */
    public String shortestPalindrome3(String s) {
        String rev = reverse(s);
        String temp = s + "#" + rev;
        int[] next = getNext(temp);
        return rev.substring(0, rev.length() - next[temp.length() - 1]) + s;
    }

    private int[] getNext(String s){
        int[] result = new int[s.length()];
        result[0] = 0;
        for(int i = 1; i < result.length; i++){
            int temp = result[i - 1];
            while(temp > 0 && s.charAt(i) != s.charAt(temp)){
                temp = result[temp - 1];
            }
            if(s.charAt(i) == s.charAt(temp)){
                temp++;
            }
            result[i] = temp;
        }
        return result;
    }

    public static void main(String[] args) {
        LeetCode214 leetCode214 = new LeetCode214();
//        System.out.println(leetCode214.shortestPalindrome1("ab"));
//        System.out.println(leetCode214.shortestPalindrome1("aa"));
        System.out.println(leetCode214.shortestPalindrome2("aacecaaa"));
//        System.out.println(leetCode214.shortestPalindrome1("abcd"));
//        System.out.println(leetCode214.shortestPalindrome1("abbbbbbbbbbbbbbbbbbbbcd"));
    }
}
