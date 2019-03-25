/**
 * 821. 字符的最短距离
 *
 * 给定一个字符串 S 和一个字符 C。返回一个代表字符串 S 中每个字符到字符串 S 中的字符 C 的最短距离的数组。
 *
 * 示例 1:
 * 输入: S = "loveleetcode", C = 'e'
 * 输出: [3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0]
 * 说明:
 * 字符串 S 的长度范围为 [1, 10000]。
 * C 是一个单字符，且保证是字符串 S 里的字符。
 * S 和 C 中的所有字母均为小写字母。
 */
public class LeetCode821 {
    /**
     * 正确的距离肯定是在两个字符C的位置之间取最小，然后要处理下边界问题
     * @param S
     * @param C
     * @return
     */
    public int[] shortestToChar(String S, char C) {
        int lastIndex = -20000;//取int最小值 j-lastIndex容易越界
        int i = 0;
        int len = S.length();
        int[] result = new int[len];
        char[] chars = S.toCharArray();
        while (i < len){
            if(chars[i] != C){
                i++;
                continue;
            }
            for(int j = Math.max(0, lastIndex); j < i; j++){
                if(chars[j] != C){
                     result[j] = Math.min(j - lastIndex, i - j);
                }
            }
            lastIndex = i;
            i++;
        }
        //判断右边界
        if(chars[len - 1] != C){
            for(int j = lastIndex + 1; j < len; j++){
                result[j] = j - lastIndex;
            }
        }
        return result;
    }
}
