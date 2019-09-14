import java.util.HashMap;
import java.util.Map;

/**
 * 290. 单词规律
 *
 * 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
 * 这里的 遵循 指完全匹配，例如， 
 * pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
 *
 * 示例1:
 * 输入: pattern = "abba", str = "dog cat cat dog"
 * 输出: true
 *
 * 示例 2:
 * 输入:pattern = "abba", str = "dog cat cat fish"
 * 输出: false
 *
 * 示例 3:
 * 输入: pattern = "aaaa", str = "dog cat cat dog"
 * 输出: false
 *
 * 示例 4:
 * 输入: pattern = "abba", str = "dog dog dog dog"
 * 输出: false
 * 说明:
 * 你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。    
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-pattern
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode290 {
    public boolean wordPattern(String pattern, String str) {
        Map<Character, String> map1 = new HashMap<>();
        Map<String, Character> map2 = new HashMap<>();
        String[] strs = str.split(" ");
        if(pattern.length() != strs.length){
            return false;
        }
        for(int i = 0; i < pattern.length(); i++){
            if(!map1.containsKey(pattern.charAt(i)) && !map2.containsKey(strs[i])){
                map1.put(pattern.charAt(i), strs[i]);
                map2.put(strs[i], pattern.charAt(i));
                continue;
            }else if(map1.containsKey(pattern.charAt(i)) && map2.containsKey(strs[i])){
                if(map1.get(pattern.charAt(i)).equals(strs[i])
                        && map2.get(strs[i]).equals(pattern.charAt(i))){
                    continue;
                }else {
                    return false;
                }
            }else {
                return false;
            }
        }
        return true;
    }

    /**
     * 不用双map用containsValue反而会快，可能元素比较少
     * @param pattern
     * @param str
     * @return
     */
    public boolean wordPattern1(String pattern, String str) {
        String[] strArray = str.split(" ");
        if (strArray.length != pattern.length()) return false;
        HashMap<Character, String> map = new HashMap<>();
        for (int i = 0; i < strArray.length; i++) {
            if (map.containsKey(pattern.charAt(i))) {
                if (!map.get(pattern.charAt(i)).equals(strArray[i])){
                    return false;
                }
            } else if (map.containsValue(strArray[i])) {
                return false;
            } else {
                map.put(pattern.charAt(i), strArray[i]);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode290 leetCode290 = new LeetCode290();
        System.out.println(leetCode290.wordPattern("abba", "dog cat cat dog"));
    }
}
