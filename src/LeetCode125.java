/**
 * 125. 验证回文串
 *
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * 示例 1:
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 示例 2:
 * 输入: "race a car"
 * 输出: false
 *
 */
public class LeetCode125 {
    /**
     * 常规双指针判断
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        if(s == null || s.length() == 0){
            return true;
        }
        int start = 0;
        int end = s.length() - 1;
        while (start < end){
            char cs = s.charAt(start);
            char ce = s.charAt(end);
            if(!isNum(cs) && !isChar(cs)){
                start++;
            }else if(!isNum(ce) && !isChar(ce)){
                end--;
            }else {
                if(!charEqual(cs, ce)){
                    return false;
                }
                start++;
                end--;
            }
        }
        return true;
    }

    public boolean isChar(char c){
        if((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')){
            return true;
        }else {
            return false;
        }
    }

    public boolean isNum(char c){
        if(c >= '0' && c <= '9'){
            return true;
        }else {
            return false;
        }
    }

    public boolean charEqual(char c1, char c2){
        if(isChar(c1) && isChar(c2)){
            return c1 - c2 == 32 || c2 - c1 == 32 || c1 == c2;
        }
        if(isNum(c1) && isNum(c2)){
            return c1 == c2;
        }
        return false;
    }
}
