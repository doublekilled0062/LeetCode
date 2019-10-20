import java.util.Stack;

/**
 * 316. 去除重复字母
 *
 * 给定一个仅包含小写字母的字符串，去除字符串中重复的字母，使得每个字母只出现一次。
 * 需保证返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 *
 * 示例 1:
 * 输入: "bcabc"
 * 输出: "abc"
 *
 * 示例 2:
 * 输入: "cbacdcbc"
 * 输出: "acdb"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicate-letters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode316 {
    public String removeDuplicateLetters(String s) {
        if(s == null || s.length() <= 1){
            return s;
        }

        //用字典和栈 如果栈中有该元素直接跳过
        //如果没有 判断栈顶元素是否大于当前元素，如果大于看当前元素的后面是否还有栈顶元素，
        //如果有就出栈栈顶直到不符合条件，入栈当前
        int[] lastIndex = new int[26];
        for(int i = 0; i < s.length(); i++){
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        int[] stackIn = new int[26];
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(stackIn[ch - 'a'] > 0){
                continue;
            }
            while (!stack.isEmpty() && stack.peek() > ch && lastIndex[stack.peek() - 'a'] > i) {
                char temp = stack.pop();
                stackIn[temp - 'a'] = 0;
            }
            stack.push(ch);
            stackIn[ch - 'a'] = 1;
        }
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < stack.size(); i++){
            builder.append(stack.get(i));
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        LeetCode316 leetCode316 = new LeetCode316();
        System.out.println(leetCode316.removeDuplicateLetters("cbacdcbc"));
    }
}
