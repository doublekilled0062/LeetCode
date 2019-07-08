/**
 * 151. 翻转字符串里的单词
 *
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 *  
 * 示例 1：
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 *
 * 示例 2：
 * 输入: "  hello world!  "
 * 输出: "world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 *
 * 示例 3：
 * 输入: "a good   example"
 * 输出: "example good a"
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *  
 * 说明：
 * 无空格字符构成一个单词。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *  
 * 进阶：
 * 请选用 C 语言的用户尝试使用 O(1) 额外空间复杂度的原地解法。
 */
public class LeetCode151 {
    /**
     * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个
     * 所以先反转字符串再反转单词 还要处理多余空格
     * 所以多余空格和两头的空格放到最后处理最好
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        if (s == null) {
            return null;
        }

        char[] chars = s.toCharArray();
        int len = chars.length;
        reverse(chars, 0, len - 1);
        reverseWords(chars, len);

        //去掉多余的空格 就是在原数组上重新去掉空格排一下字符
        int i = 0;
        int j = 0;
        while(j < len) {
            //前面的空格忽略
            while (j < len && chars[j] == ' ') {
                j++;
            }
            //不是空格的依次挪一下
            while (j < len && chars[j] != ' ') {
                chars[i++] = chars[j++];
            }
            //忽略后面的空格
            while (j < len && chars[j] == ' ') {
                j++;
            }
            //如果没到头 就用一个空格代替之前略过的所有空格
            if (j < len) {
                chars[i++] = ' ';
            }
        }
        return new String(chars, 0 , i);
    }

    /**
     * 按索引反转字符串
     * @param chars
     * @param i
     * @param j
     */
    private void reverse(char[] chars, int i, int j) {
        while(i < j) {
            char temp = chars[i];
            chars[i++] = chars[j];
            chars[j--] = temp;
        }
    }

    /**
     * 按单词反转
     * @param chars
     * @param len
     */
    private void reverseWords(char[] chars, int len) {
        int i = 0;
        int j = 0;
        while (i < len) {
            while (i < j || (i < len && chars[i] == ' ')) {
                i++;
            }
            while (j < i || (j < len && chars[j] != ' ')) {
                j++;
            }
            reverse(chars, i, j - 1);
        }
    }

    /**
     * 库函数系列 因为只需要一个空格间隔 所以可以取巧
     * @param s
     * @return
     */
    public String reverseWords1(String s) {
        if(s.length() == 0 || s == null){
            return "";
        }
        String[] array = s.split(" ");
        StringBuilder sb = new StringBuilder();

        for(int i = array.length - 1; i >= 0; i--){
            if(!array[i].equals("")) {
                if (sb.length() > 0) {
                    sb.append(" ");
                }
                sb.append(array[i]);
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        LeetCode151 leetCode151 = new LeetCode151();
        System.out.println(leetCode151.reverseWords("the sky is blue"));
        System.out.println(leetCode151.reverseWords("  hello world!  "));
        System.out.println(leetCode151.reverseWords("a good   example"));
        System.out.println(leetCode151.reverseWords("        "));
    }
}
