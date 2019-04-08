import java.util.Stack;

/**
 * 32. 最长有效括号
 *
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 * 示例 1:
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * 示例 2:
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 */
public class LeetCode32 {
    /**
     * 用了一半动态规划
     * 6ms-9ms 100%
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        if(s == null || s.length() < 2){
            return 0;
        }
        int len = s.length();
        char[] chars = s.toCharArray();
        int[] result = new int[len];  //只有0和1 有效括号就是1 非有效暂标0
        result[0] = 0;

        for(int i = 0; i < len; i++){
            if(chars[i] == '('){
                result[i] = 0;
            }else {
                //右括号时要分情况 找到第一个为0的括号
                int j = i - 1;
                while (j >= 0){
                    if(result[j] == 0){
                        break;
                    }
                    j--;
                }
                if(j < 0){
                    //没找到
                    result[i] = 0;
                    //括号已断 更新最小索引
                }else {
                    //需要判断result[j]是左还是右
                    if(chars[j] == '('){
                        //配对成功
                        result[j] = 1;
                        result[i] = 1;
                    }else {
                        result[i] = 0;
                    }
                }
            }
        }
        int max = 0;
        int temp = 0;
        for(int i = 0; i < len; i++){
            if(result[i] > 0){
                temp++;
            }else {
                max = Math.max(temp, max);
                temp = 0;
            }
        }
        max = Math.max(temp, max);
        return max;
    }

    /**
     * 如果用栈其实上面的思路就可以简化了
     * 左括号入栈
     * 右括号分情况 如果栈空更新起始索引 如果非空则拿出一个 长度再按栈空不空计算
     * 然后 并没有上一个快 这个14ms - 16ms 93%
     * @param s
     * @return
     */
    public int longestValidParentheses2(String s){
        if(s == null || s.length() < 2){
            return 0;
        }
        int len = s.length();
        char[] chars = s.toCharArray();
        int start = 0;
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < chars.length; i++){
            if(chars[i] == '('){
                stack.push(i);
            }else {
                if(stack.isEmpty()){
                    //右括号时 若栈空
                    start = i + 1;
                }else {
                    //取出一个配对的括号
                    int index = stack.pop();
                    if(stack.isEmpty()){
                        max = Math.max(max, i - start + 1);
                    }else {
                        max = Math.max(max, i - stack.peek());
                    }
                }
            }
        }
        return max;
    }

    /**
     * 肯定有一个动态规划的解法
     * @param s
     * @return
     */
    public int longestValidParentheses3(String s){
        if(s == null || s.length() < 2){
            return 0;
        }
        int len = s.length();
        char[] chars = s.toCharArray();
        int[] result = new int[len];//result[i]表示到0-i中包含i的有效连续括号的个数 其中为'('的恒为0;
        int max = 0;

        for(int i = 1; i < chars.length; i++){
            if(chars[i] == ')'){
                if(chars[i - 1] == '('){
                    // '('或者')'    (      )
                    //    i-2       i-1    i
                    if(i >= 2){
                        result[i] = result[i - 2] + 2;
                    }else {
                        result[i] = 2;
                    }
                }else {
                    //result[i-1]的值为有效括号个数 index(i-1) - result[i-1]就是第一个倒着不匹配的括号位置
                    // ( (         (             ( ( ) ) )
                    // 0 0         0             0 0 2 4
                    //     i-1-result[i - 1]
                    if(i - 1 - result[i - 1] >= 0){
                        if(chars[i - 1 - result[i - 1]] == '('){
                            //匹配上 就和最上面分支类似了
                            if(i - 1 - result[i - 1] - 1 >= 0){
                                result[i] = result[i - 1 - result[i - 1] - 1] + result[i - 1] + 2;
                            }else {
                                result[i] = result[i - 1] + 2;
                            }
                        }
                    }else {
                        result[i] = 0;
                    }
                }
                max = Math.max(result[i], max);
            }
        }
        return max;
    }

    /**
     * 还有个两次遍历的方法
     * @param s
     * @return
     */
    public int longestValidParentheses4(String s){
        if(s == null || s.length() < 2){
            return 0;
        }
        int len = s.length();
        char[] chars = s.toCharArray();

        //左括号长度
        int startNum = 0;
        int curLen = 0;
        int maxLen = 0;
        for(int i = 0; i < len; i++){
            if(chars[i] == '('){
                startNum++;
            }else {
                if(startNum > 0){
                    curLen = curLen + 2;
                    startNum--;
                    if(startNum == 0){
                        maxLen = Math.max(curLen, maxLen);
                    }
                }else {
                    curLen = 0;
                    startNum = 0;
                }
            }
        }

        //右括号
        startNum = 0;
        curLen = 0;
        for(int i = len - 1; i >= 0; i--){
            if(chars[i] == ')'){
                startNum++;
            }else {
                if(startNum > 0){
                    curLen = curLen + 2;
                    startNum--;
                    if(startNum == 0){
                        maxLen = Math.max(curLen, maxLen);
                    }
                }else {
                    curLen = 0;
                    startNum = 0;
                }
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        LeetCode32 leetCode32 = new LeetCode32();
        System.out.println(leetCode32.longestValidParentheses4( ")()(((())))("));
    }
}
