import java.util.LinkedList;
import java.util.Stack;

/**
 * 227. 基本计算器 II
 *
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 * 字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。
 *
 * 示例 1:
 * 输入: "3+2*2"
 * 输出: 7
 *
 * 示例 2:
 * 输入: " 3/2 "
 * 输出: 1
 *
 * 示例 3:
 * 输入: " 3+5 / 2 "
 * 输出: 5
 * 说明：
 *
 * 你可以假设所给定的表达式都是有效的。
 * 请不要使用内置的库函数 eval。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/basic-calculator-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode227 {
    /**
     * 先算乘除法 其他的加减先保存 最后算一遍
     * @param s
     * @return
     */
    public int calculate(String s) {
        char[] chars = s.toCharArray();
        LinkedList<Character> listOp = new LinkedList<>();
        LinkedList<Integer> listNum = new LinkedList<>();
        int i = 0;
        while (i < s.length()){
            if(chars[i] == ' '){
                i++;
                continue;
            } else if(chars[i] - '0' >= 0 && chars[i] - '0' <= 9){
                //求出操作数
                int val = chars[i] - '0';
                i = i + 1;
                while (i < s.length() && chars[i] - '0' >= 0 && chars[i] - '0' <= 9){
                    val = val * 10 + (chars[i] - '0');
                    i++;
                }

                handleList(listOp, listNum, val);
            }else {
                //运算符 进栈
                listOp.addLast(chars[i]);
                i++;
            }
        }

        if(listOp.isEmpty()){
            return listNum.getLast();
        }

        while (!listOp.isEmpty()){
            char op = listOp.removeFirst();
            int var1 = listNum.removeFirst();
            int var2 = listNum.removeFirst();
            if(op == '+'){
                listNum.addFirst(var1 + var2);
            }else {
                listNum.addFirst(var1 - var2);
            }
        }
        return listNum.getLast();
    }

    private void handleList(LinkedList<Character> listOp, LinkedList<Integer> listNum, int val){
        if(listOp.isEmpty() || listOp.getLast() == '+' || listOp.getLast() == '-'){
            listNum.addLast(val);
            return;
        }
        if(listOp.getLast() == '*'){
            listOp.removeLast();
            int val2 = listNum.removeLast();
            listNum.addLast(val2 * val);
        }else if(listOp.getLast() == '/'){
            listOp.removeLast();
            int val2 = listNum.removeLast();
            listNum.addLast(val2 / val);
        }
    }

    private int posOp = -1;
    private int posNum = -1;

    /**
     * 数组模拟 17ms 95%
     * @param s
     * @return
     */
    public int calculate2(String s) {
        char[] chars = s.toCharArray();
        char[] listOp = new char[s.length()];
        int[] listNum = new int[s.length()];

        int i = 0;
        while (i < s.length()){
            if(chars[i] == ' '){
                i++;
                continue;
            } else if(chars[i] - '0' >= 0 && chars[i] - '0' <= 9){
                //求出操作数
                int val = chars[i] - '0';
                i = i + 1;
                while (i < s.length() && chars[i] - '0' >= 0 && chars[i] - '0' <= 9){
                    val = val * 10 + (chars[i] - '0');
                    i++;
                }

                handleList(listOp, listNum, val);
            }else {
                //运算符 进栈
                listOp[++posOp] = chars[i];
                i++;
            }
        }

        if(posOp < 0){
            return listNum[posNum];
        }

        int pOp = 0;
        int pNum = 0;

        while (pOp <= posOp){
            char op = listOp[pOp];
            int var1 = listNum[pNum];
            int var2 = listNum[pNum+1];
            if(op == '+'){
                listNum[pNum+1] = var1 + var2;
            }else {
                listNum[pNum+1] = var1 - var2;
            }
            pOp++;
            pNum++;
        }
        return listNum[pNum];
    }

    private void handleList(char[] listOp, int[] listNum, int val){
        if(posOp < 0 || listOp[posOp] == '+' || listOp[posOp] == '-'){
            listNum[++posNum] = val;
            return;
        }
        if(listOp[posOp] == '*'){
            posOp--;
            int val2 = listNum[posNum--];
            listNum[++posNum] = val * val2;
        }else if(listOp[posOp] == '/'){
            posOp--;
            int val2 = listNum[posNum--];
            listNum[++posNum] = val2 / val;
        }
    }

    private int pre = 0;
    private int cur = 0;
    private int next = 0;
    private char sign = '+';

    /**
     * 状态转移法
     * 任何表达式最后都可以精简成 pre + cur *+-/ next;
     *
     * pre cur next 分别为三个状态状态转移方式
     * sign = + : pre += cur, cur = next
     * sign = - : pre += cur, cur = -next
     * sign = * : cur *= next
     * sign = / : cur /= next
     * 
     * @param s
     * @return
     */
    public int calculate3(String s) {
        char[] chars = s.toCharArray();
        for(int i = 0; i < s.length(); i++){
            if(chars[i] == ' '){
                continue;
            } else if(chars[i] - '0' >= 0 && chars[i] - '0' <= 9){
                //求出操作数
                next = next * 10 + (chars[i] - '0');
            }else {
                handlePre();
                sign = chars[i];
            }
        }
        handlePre();
        return pre + cur;
    }

    private void handlePre(){
        switch (sign){
            case '+':
                pre = pre + cur;
                cur = next;
                break;
            case '-':
                pre = pre + cur;
                cur = -next;
                break;
            case '*':
                cur = cur * next;
                break;
            case '/':
                cur = cur/next;
        }
        next = 0;
    }

    public static void main(String[] args) {
        LeetCode227 leetCode227 = new LeetCode227();
        System.out.println(leetCode227.calculate3("3+2*2"));
    }
}
