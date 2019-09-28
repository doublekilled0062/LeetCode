import java.util.ArrayList;
import java.util.List;

/**
 * 301. 删除无效的括号
 *
 * 删除最小数量的无效括号，使得输入的字符串有效，返回所有可能的结果。
 * 说明: 输入可能包含了除 ( 和 ) 以外的字符。
 *
 * 示例 1:
 * 输入: "()())()"
 * 输出: ["()()()", "(())()"]
 *
 * 示例 2:
 * 输入: "(a)())()"
 * 输出: ["(a)()()", "(a())()"]
 *
 * 示例 3:
 * 输入: ")("
 * 输出: [""]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-invalid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode301 {
    /**
     * dfs 来自评论区c++思路
     * 以右边括号多为例 每次找到多余)的时候 都从头到当前多余)的地方随机删除一个)即可
     * 为了提高效率所以有一个上次遍历的索引和一个上次删除的索引
     * 每次递归都从这两个索引开始即可
     * @param s
     * @return
     */
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<String>();
        dfs(s, result, 0, 0, '(', ')');
        return result;
    }

    /**
     * ()配对删除左括号 )(配对删除(括号
     * @param s
     * @param result
     * @param index
     * @param removeIndex
     * @param ch
     */
    public void dfs(String s, List<String> result, int index, int removeIndex, char ch, char removeCh){
        int count = 0;
        for (int i = index; i < s.length(); i++) {
            char c = s.charAt(i);
            //两种括号的配对方式一起搞了
            if(c == ch || c == removeCh){
                if(c == ch){
                    count++;
                }else {
                    count--;
                }
            }
            if (count >= 0) {
                continue;
            }
            //从上次删除的随机删一个括号进行递归
            for (int j = removeIndex; j <= i; j++) {
                if (s.charAt(j) == removeCh && (j == removeIndex || s.charAt(j - 1) != removeCh)) {
                    dfs(s.substring(0, j) + s.substring(j + 1), result, i, j, ch, removeCh);
                }
            }
            return;
        }
        s = new StringBuilder(s).reverse().toString();
        if (ch == '(') {
            dfs(s, result, 0, 0, ')', '(');
        } else {
            result.add(s);
        }
    }
}
