import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 22. 括号生成
 *
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 * 例如，给出 n = 3，生成结果为：
 * [
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 */
public class LeetCode22 {
    /**
     * 递归
     * 一个括号 其他的结果要么放到这个括号后面 或者前面 或者中间 要么放在其中一个右括号后面
     * 但是要用set去重
     * 复杂度太高了
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        if(n <= 0){
            return new ArrayList<>();
        }
        Set<String> result = new HashSet<>();
        if(n == 1){
            result.add("()");
            return new ArrayList<String>(){{
                addAll(result);
            }};
        }
        List<String> temp = generateParenthesis(n - 1);
        for(String s : temp){
            result.add("()" + s);
            result.add(s + "()");
            result.add("(" + s + ")");
            //扩号放里面
            char[] chars = s.toCharArray();
            for(int i = 0; i < s.length() - 1; i++){
                StringBuilder builder = new StringBuilder();
                builder.append(s.substring(0, i + 1));
                builder.append("()");
                builder.append(s.substring(i + 1, s.length()));
                result.add(builder.toString());
            }
        }
        return new ArrayList<String>(){{
            addAll(result);
        }};
    }
}
