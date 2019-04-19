/**
 * 87. 扰乱字符串
 *
 * 给定一个字符串 s1，我们可以把它递归地分割成两个非空子字符串，从而将其表示为二叉树。
 * 下图是字符串 s1 = "great" 的一种可能的表示形式。
 *           great
 *          /    \
 *        gr    eat
 *       / \    /  \
 *      g   r  e   at
 *                / \
 *               a   t
 * 在扰乱这个字符串的过程中，我们可以挑选任何一个非叶节点，然后交换它的两个子节点。
 * 例如，如果我们挑选非叶节点 "gr" ，交换它的两个子节点，将会产生扰乱字符串 "rgeat" 。
 *          rgeat
 *         /    \
 *        rg    eat
 *       / \    /  \
 *      r   g  e   at
 *                / \
 *               a   t
 * 我们将 "rgeat” 称作 "great" 的一个扰乱字符串。
 * 同样地，如果我们继续将其节点 "eat" 和 "at" 进行交换，将会产生另一个新的扰乱字符串 "rgtae" 。
 *          rgtae
 *         /    \
 *        rg    tae
 *       / \    /  \
 *      r   g  ta  e
 *            / \
 *           t   a
 * 我们将 "rgtae” 称作 "great" 的一个扰乱字符串。
 * 给出两个长度相等的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串。
 * 示例 1:
 * 输入: s1 = "great", s2 = "rgeat"
 * 输出: true
 *
 * 示例 2:
 * 输入: s1 = "abcde", s2 = "caebd"
 * 输出: false
 */
public class LeetCode87 {
    /**
     * 递归可解 只是不知道效率
     * 主要是可以在任意地方分隔
     * @param s1
     * @param s2
     * @return
     */
    public boolean isScramble(String s1, String s2) {
        if(s1.equals(s2)) {
            return true;
        }

        if(s1.length() == 1){
            return s1.equals(s2);
        }
        if(s1.length() == 2){
            String s10 = s1.substring(0,1);
            String s11 = s1.substring(1,2);
            String s20 = s2.substring(0,1);
            String s21 = s2.substring(1,2);
            return (s10.equals(s20) && s11.equals(s21))
                    || (s11.equals(s20) && s10.equals(s21));
        }

        if(!isSortSame(s1, s2)){
            return false;
        }

        int len = s1.length();
        for(int index = 1; index < s1.length(); index++){
            String s10 = s1.substring(0, index);
            String s11 = s1.substring(index, len);

            String s20 = s2.substring(0, index);
            String s21 = s2.substring(index, len);

            //这个不用算 当i = len -index时会重复
//            String s12 = s1.substring(0, len - index);
//            String s13 = s1.substring(len - index, len);

            String s22 = s2.substring(0, len - index);
            String s23 = s2.substring(len - index, len);

            //剪枝 先判断排序后相等
            if (isScramble(s10, s20) && isScramble(s11, s21)) {
                return true;
            }
//            if (isScramble(s12, s22) && isScramble(s13, s23)) {
//                return true;
//            }

            if (isScramble(s10, s23) && isScramble(s11, s22)) {
                return true;
            }

//            if (isScramble(s12, s21) && isScramble(s13, s20)) {
//                return true;
//            }
        }
        return false;
    }

    /**
     * 比排序验证相等优化一下
     * @param s1
     * @param s2
     * @return
     */
    private boolean isSortSame(String s1, String s2){
        int[] count = new int[26];
        //判定是否含有相同元素
        for(int i = 0; i < s1.length(); i++){
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }
        for(int num : count){
            if(num != 0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode87 leetCode87 = new LeetCode87();
        System.out.println(leetCode87.isScramble("great", "rgeat"));
        System.out.println(leetCode87.isScramble("abcde", "caebd"));
        System.out.println(leetCode87.isScramble("abb", "bba"));
        System.out.println(leetCode87.isScramble("abab", "baba"));
        System.out.println(leetCode87.isScramble("abab", "aabb"));
        System.out.println(leetCode87.isScramble("abcdefghijklmn","efghijklmncadb"));
    }
}
