import java.util.HashSet;
import java.util.Set;

/**
 * 771. 宝石与石头
 *
 * 给定字符串J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。
 * S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
 * J 中的字母不重复，J 和 S中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头。
 *
 * 示例 1:
 * 输入: J = "aA", S = "aAAbbbb"
 * 输出: 3
 *
 * 示例 2:
 * 输入: J = "z", S = "ZZ"
 * 输出: 0
 * 注意:
 *
 * S 和 J 最多含有50个字母。
 * J 中的字符不重复。
 */
public class LeetCode771 {

    /**
     * 这个结果比双循环还慢
     * @param J
     * @param S
     * @return
     */
    public int numJewelsInStones(String J, String S) {
        if(J == null || S == null){
            return 0;
        }
        int total = 0;
        Set<Character> set = new HashSet<>();
        for(int i = 0; i < J.length(); i++){
            set.add(J.charAt(i));
        }
        for(int i = 0; i < S.length(); i++){
            if(set.contains(S.charAt(i))){
                total++;
            }
        }
        return total;
    }

    /**
     * 思想没错，所以集合或者散列表只能自己拿数组实现
     * 每次调用charAt也要优化
     * 逐步优化到和9ms的一个代码 但是我是18ms 所以用例可能跑的时间不一样
     * @param J
     * @param S
     * @return
     */
    public int numJewelsInStones2(String J, String S) {
        if(J == null || S == null){
            return 0;
        }
        int total = 0;
        boolean[] map = new boolean[128];
        char[] Js = J.toCharArray();
        char[] Ss = S.toCharArray();
        for(int i = 0; i < Js.length; i++){
            map[Js[i]] = true;
        }
        for(int i = 0; i < Ss.length; i++){
            if(map[Ss[i]]){
                total++;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        LeetCode771 leetCode771 = new LeetCode771();
        System.out.println(leetCode771.numJewelsInStones2("aA","aAAbbbb"));
    }
}
