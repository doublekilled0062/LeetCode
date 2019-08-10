import java.util.Stack;

/**
 * 224. 基本计算器
 *
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 * 字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格  。
 *
 * 示例 1:
 * 输入: "1 + 1"
 * 输出: 2
 *
 * 示例 2:
 * 输入: " 2-1 + 2 "
 * 输出: 3
 *
 * 示例 3:
 * 输入: "(1+(4+5+2)-3)+(6+8)"
 * 输出: 23
 * 说明：
 *
 * 你可以假设所给定的表达式都是有效的。
 * 请不要使用内置的库函数 eval。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/basic-calculator
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode224 {
    /**
     * 暴力解法
     * @param s
     * @return
     */
    public int calculate(String s) {
        char[] chars = s.toCharArray();
        Stack<String> stack = new Stack<>();
        int i = 0;
        while (i < s.length()){
            if(chars[i] == ' '){
                i++;
                continue;
            }else if(chars[i] == '('){
                stack.push("(");
                if(chars[i+1] - '0' >= 0 && chars[i+1] - '0' <= 9){
                    i = i + 1;
                    int val = 0;
                    while (i < s.length() && chars[i] - '0' >= 0 && chars[i] - '0' <= 9){
                        val = val * 10 + (chars[i] - '0');
                        i++;
                    }
                    stack.push(val + "");
                }else {
                    stack.push(chars[i + 1] + "");
                    i = i + 2;
                }
            }else if(chars[i] == '+' || chars[i] == '-'){
                if(chars[i+1] - '0' >= 0 && chars[i+1] - '0' <= 9){
                    stack.push(chars[i] + "");
                }else{
                    stack.push(chars[i] + "");
                }
                i++;
            }else if(chars[i] - '0' >= 0 && chars[i] - '0' <= 9){
                int val = 0;
                while (i < s.length() && chars[i] - '0' >= 0 && chars[i] - '0' <= 9){
                    val = val * 10 + (chars[i] - '0');
                    i++;
                }
                if(!stack.isEmpty() && stack.peek().equals("+")){
                    stack.pop();
                    Integer val2 = Integer.parseInt(stack.pop());
                    stack.push(String.valueOf(val + val2));
                }else if(!stack.isEmpty() && stack.peek().equals("-")){
                    stack.pop();
                    Integer val2 = Integer.parseInt(stack.pop());
                    stack.push(String.valueOf(val2 - val));
                }else {
                    stack.push(val + "");
                }
            }else if(chars[i] == ')'){
                int val1 = Integer.parseInt(stack.pop());
                stack.pop();
                if(!stack.isEmpty() && stack.peek().equals("+")){
                    stack.pop();
                    Integer val2 = Integer.parseInt(stack.pop());
                    stack.push(String.valueOf(val1 + val2));
                }else if(!stack.isEmpty() && stack.peek().equals("-")){
                    stack.pop();
                    Integer val2 = Integer.parseInt(stack.pop());
                    stack.push(String.valueOf(val2 - val1));
                }else {
                    stack.push(val1 + "");
                }
                i++;
            }
        }
        return Integer.parseInt(stack.peek());
    }

    /**
     * 双栈一个放运算符 一个放数字
     * @param s
     * @return
     */
    public int calculate1(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stackOp = new Stack<>();
        Stack<Integer> stackNum = new Stack<>();
        int i = 0;
        while (i < s.length()){
            if(chars[i] == ' '){
                i++;
                continue;
            } else if(chars[i] - '0' >= 0 && chars[i] - '0' <= 9){
                int val = chars[i] - '0';
                i = i + 1;
                while (i < s.length() && chars[i] - '0' >= 0 && chars[i] - '0' <= 9){
                    val = val * 10 + (chars[i] - '0');
                    i++;
                }

                hanleStack(stackOp, stackNum, val);
            }else if(chars[i] == '(' || chars[i] == '+' || chars[i] == '-'){
                stackOp.push(chars[i]);
                i++;
            }else {
                int val = stackNum.pop();
                stackOp.pop();
                hanleStack(stackOp, stackNum, val);
                i++;
            }
        }
        return stackNum.peek();
    }

    private void hanleStack(Stack<Character> stackOp, Stack<Integer> stackNum, int val){
        if(stackOp.isEmpty() || stackOp.peek() == '('){
            stackNum.push(val);
            return;
        }
        if(stackOp.peek() == '+'){
            stackOp.pop();
            int val2 = stackNum.pop();
            stackNum.push(val + val2);
        }else if(stackOp.peek() == '-'){
            stackOp.pop();
            int val2 = stackNum.pop();
            stackNum.push(val2 - val);
        }
    }

    private int posOp = -1;
    private int posNum = -1;

    /**
     * 数组模拟栈
     * @param s
     * @return
     */
    public int calculate2(String s) {
        char[] chars = s.toCharArray();
        char[] stackOp = new char[s.length()];
        int[] stackNum = new int[s.length()];

        int i = 0;
        while (i < s.length()){
            if(chars[i] == ' '){
                i++;
                continue;
            } else if(chars[i] - '0' >= 0 && chars[i] - '0' <= 9){
                int val = chars[i] - '0';
                i = i + 1;
                while (i < s.length() && chars[i] - '0' >= 0 && chars[i] - '0' <= 9){
                    val = val * 10 + (chars[i] - '0');
                    i++;
                }

                hanleStack(stackOp, stackNum, val);
            }else if(chars[i] == '(' || chars[i] == '+' || chars[i] == '-'){
                stackOp[++posOp] = chars[i];
                i++;
            }else {
                int val = stackNum[posNum--];
                posOp--;
                hanleStack(stackOp, stackNum, val);
                i++;
            }
        }
        return stackNum[0];
    }

    private void hanleStack(char[] stackOp, int[] stackNum, int val){
        if(posOp < 0 || stackOp[posOp] == '('){
            stackNum[++posNum] = val;
            return;
        }
        if(stackOp[posOp] == '+'){
            posOp--;
            int val2 = stackNum[posNum--];
            stackNum[++posNum] = val + val2;
        }else if(stackOp[posOp] == '-'){
            posOp--;
            int val2 = stackNum[posNum--];
            stackNum[++posNum] = val2 - val;
        }
    }

    /**
     * 题解 6ms的遇到左括号就递归的方式
     * @param s
     * @return
     */
    public int calculate3(String s) {
        return doCalculate(s.toCharArray(), 0)[0];
    }

    public int[] doCalculate(char[] str, int i) {
        int[] rst = new int[2];
        int signal = 1;
        int result = 0;
        int n = 0;
        while (i < str.length && str[i] != ')') {
            if (str[i] == ' ') {
                i++;
            } else if (str[i] >= '0' && str[i] <= '9') {
                n = n * 10 + str[i++] - '0';
            } else if (str[i] != '(') {
                result += signal * n;
                n = 0;
                signal = str[i++] == '+' ? 1 : -1;
            } else {
                rst = doCalculate(str, i + 1);
                n = rst[0];
                i = rst[1] + 1;
            }
        }
        result += signal * n;
        rst[0] = result;
        rst[1] = i;
        return rst;
    }

    public static void main(String[] args) {
        LeetCode224 leetCode224 = new LeetCode224();
        System.out.println(leetCode224.calculate3("(1+(4+5+2)-3)+(6+8)"));
//        System.out.println(leetCode224.calculate3("1 + 1"));
    }
}
