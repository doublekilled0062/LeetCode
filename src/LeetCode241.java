import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 241. 为运算表达式设计优先级
 *
 * 给定一个含有数字和运算符的字符串，为表达式添加括号，改变其运算优先级以求出不同的结果。
 * 你需要给出所有可能的组合的结果。有效的运算符号包含 +, - 以及 * 。
 *
 * 示例 1:
 * 输入: "2-1-1"
 * 输出: [0, 2]
 * 解释:
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 *
 * 示例 2:
 * 输入: "2*3-4*5"
 * 输出: [-34, -14, -10, -10, 10]
 * 解释:
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/different-ways-to-add-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode241 {
    private HashMap<String, List<Integer>> map = new HashMap<String, List<Integer>>();

    /**
     * 标准递归回溯 但是效率不高 提高效率的办法就是找一个map存放已经算好的值防止重复递归
     * @param input
     * @return
     */
    public List<Integer> diffWaysToCompute(String input) {
        if(map.containsKey(input)){
            return map.get(input);
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                //递归
                List<Integer> temp1 = diffWaysToCompute(input.substring(0, i));
                List<Integer> temp2 = diffWaysToCompute(input.substring(i + 1, input.length()));
                for (int t1 : temp1) {//计算结果
                    for (int t2 : temp2) {
                        if (c == '+') {
                            res.add(t1 + t2);
                        } else if (c == '-') {
                            res.add(t1 - t2);
                        } else if (c == '*') {
                            res.add(t1 * t2);
                        }
                    }
                }
            }
        }
        if (res.isEmpty()) {
            //纯数字，input中没符号
            res.add(Integer.parseInt(input));
        }
        map.put(input, res);
        return res;
    }

    public static void main(String[] args) {
        LeetCode241 leetCode241 = new LeetCode241();
        leetCode241.diffWaysToCompute("2-1-1");
    }
}
