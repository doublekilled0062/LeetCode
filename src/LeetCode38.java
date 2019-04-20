/**
 * 38. 报数
 *
 * 报数序列是一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 *
 * 1 被读作  "one 1"  ("一个一") , 即 11。
 * 11 被读作 "two 1s" ("两个一"）, 即 21。
 * 21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。
 * 给定一个正整数 n（1 ≤ n ≤ 30），输出报数序列的第 n 项。
 * 注意：整数顺序将表示为一个字符串。
 *
 * 示例 1:
 * 输入: 1
 * 输出: "1"
 *
 * 示例 2:
 * 输入: 4
 * 输出: "1211"
 */
public class LeetCode38 {
    public String countAndSay(int n) {
        if(n == 1){
            return "1";
        }
        String result = "1";
        for(int i = 2; i <= n; i++){
            int j = 0;
            int k = 0;
            StringBuffer temp = new StringBuffer();
            while (j < result.length()){
                while (k < result.length() && result.charAt(j) == result.charAt(k)){
                    k++;
                }
                temp.append(k-j).append(result.charAt(j));
                j = k;
            }
            result = temp.toString();
        }
        return result;
    }

    /**
     * 递归
     * @param n
     * @return
     */
    public String countAndSay2(int n) {
        if (n == 1){
            return "1";
        }else {
            char[] c = countAndSay2(n-1).toCharArray();
            StringBuilder builder = new StringBuilder("");
            int a = 0;
            char ca = c[0];
            for (int i = 0; i < c.length; i++){
                if (c[i] == ca){
                    a++;
                }else {
                    builder.append(a);
                    builder.append(ca);
                    ca = c[i];
                    a = 1;
                }
            }
            builder.append(a);
            builder.append(ca);
            return builder.toString();
        }
    }
}
