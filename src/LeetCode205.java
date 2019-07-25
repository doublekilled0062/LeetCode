import java.util.HashMap;
import java.util.Map;

/**
 * 205. 同构字符串
 *
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
 * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
 *
 * 示例 1:
 * 输入: s = "egg", t = "add"
 * 输出: true
 * 示例 2:
 *
 * 输入: s = "foo", t = "bar"
 * 输出: false
 * 示例 3:
 *
 * 输入: s = "paper", t = "title"
 * 输出: true
 * 说明:
 * 你可以假设 s 和 t 具有相同的长度。
 *
 */
public class LeetCode205 {
    public boolean isIsomorphic(String s, String t) {
        char[] map1 = new char[128];
        char[] map2 = new char[128];
        char[] ss = s.toCharArray();
        char[] ts = t.toCharArray();
        for(int i = 0; i < s.length(); i++){
            if(map1[ss[i]] == 0){
                if(map2[ts[i]] != 0){
                    return false;
                }
                map1[ss[i]] = ts[i];
                map2[ts[i]] = ss[i];
            }else {
                if(map1[ss[i]] != ts[i]){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode205 leetCode205 = new LeetCode205();
        System.out.println(leetCode205.isIsomorphic("foo", "bar"));
        System.out.println(leetCode205.isIsomorphic("egg", "add"));
        System.out.println(leetCode205.isIsomorphic("paper", "title"));
        System.out.println(leetCode205.isIsomorphic("ab", "aa"));
    }
}
