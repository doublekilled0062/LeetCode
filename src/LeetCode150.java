import java.util.LinkedList;
import java.util.Stack;

/**
 * 150. 逆波兰表达式求值
 *
 * 根据逆波兰表示法，求表达式的值。
 * 有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 * 说明：
 * 整数除法只保留整数部分。
 * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 * 示例 1：
 * 输入: ["2", "1", "+", "3", "*"]
 * 输出: 9
 * 解释: ((2 + 1) * 3) = 9
 * 示例 2：
 * 输入: ["4", "13", "5", "/", "+"]
 * 输出: 6
 * 解释: (4 + (13 / 5)) = 6
 * 示例 3：
 * 输入: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
 * 输出: 22
 * 解释:
 * ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 */
public class LeetCode150 {
    private int stackIndex;
    /**
     * 借助辅助栈 栈费时间 可以借助索引判断
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        stackIndex = tokens.length;
        for(int i = tokens.length - 1; i >= 0; i--){
            evalRPN(tokens[i], tokens);
        }
        return Integer.parseInt(tokens[tokens.length - 1]);
    }

    public void evalRPN(String val, String[] tokens){
        if(!isNumber(val)){
            tokens[--stackIndex] = val;
        }else{
            if(stackIndex == tokens.length){
                tokens[tokens.length - 1] = val;
                return;
            }
            //如果是数字就看栈顶是不是数字
            if(!isNumber(tokens[stackIndex])){
                //不是数字压栈
                tokens[--stackIndex] = val;
            }else {
                //是数字就计算一下 pop两个的值再
                String val2 = tokens[stackIndex++];
                String op = tokens[stackIndex++];
                int temp = calc(val, val2, op);
                evalRPN(String.valueOf(temp), tokens);
            }
        }
    }

    public int calc(String val1, String val2, String op){
        switch (op){
            case "+":
                return Integer.parseInt(val1) + Integer.parseInt(val2);
            case "-":
                return Integer.parseInt(val1) - Integer.parseInt(val2);
            case "*":
                return Integer.parseInt(val1) * Integer.parseInt(val2);
            case "/":
                return Integer.parseInt(val1) / Integer.parseInt(val2);
            default:
                return 0;
        }
    }

    public boolean isNumber(String s){
        if(s.equals("+") || s.equals("-")
                || s.equals("*") || s.equals("/")){
            return false;
        }
        return true;
    }
}
