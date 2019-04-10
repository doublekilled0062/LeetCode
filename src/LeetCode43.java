import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 43. 字符串相乘
 *
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * 示例 1:
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 */
public class LeetCode43 {
    /**
     * 常规做法 时间
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        if(num1.equals("0") || num2.equals("0")){
            return "0";
        }
        List<List<Integer>> tempList = new ArrayList<>();
        for(int i = num2.length() - 1; i >= 0; i--){
            if(num2.charAt(i) != '0'){
                int flag = 0;
                List<Integer> str = new LinkedList<>();
                for(int j = num1.length() - 1; j >= 0; j--){
                    int sum = flag;
                    sum += (num2.charAt(i) - '0') * (num1.charAt(j) - '0');
                    flag = sum/10;
                    str.add(0, sum % 10);
                }
                if(flag != 0){
                    str.add(0, flag);
                }
                //补0 其实按错位往下传也行
                for(int j = 0; j < num2.length() - 1 - i; j++){
                    str.add(0);
                }
                tempList.add(str);
            }
        }
        return add(tempList, 1);
    }

    //字符串相加
    public String add(List<List<Integer>> tempList, int index){
        if(index >= tempList.size()){
            StringBuilder builder = new StringBuilder();
            List<Integer> res = tempList.get(0);
            for(int i = 0; i < res.size(); i++){
                builder.append(res.get(i));
            }
            return builder.toString();
        }
        List<Integer> str = new LinkedList<>();
        int i = tempList.get(0).size() - 1;
        int j = tempList.get(index).size() - 1;
        int flag = 0; //是否进位
        while (i >= 0 || j >=0 || flag > 0){
            int sum = flag;
            if(i >= 0){
                sum = sum + (tempList.get(0).get(i));
            }
            if(j >= 0){
                sum = sum + (tempList.get(index).get(j));
            }
            flag = sum/10;
            str.add(0, sum % 10);
            i--;
            j--;
        }
        tempList.set(0, str);
        return add(tempList, index + 1);
    }

    /**
     * 优化下上面直接用char[len1+len2]暂存结果累加 最后再处理进位
     * @param num1
     * @param num2
     * @return
     */
    public String multiply2(String num1, String num2) {
        if(num1.equals("0") || num2.equals("0")){
            return "0";
        }
        int len1 = num1.length();
        int len2 = num2.length();
        int len = len1 + len2;
        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();
        char[] result = new char[len1 + len2];
        for(int i = 0; i < len1; i++){
            for(int j = 0; j < len2; j++){
                result[i + j + 1] += (chars1[i] - '0') * (chars2[j] - '0');
            }
        }
        //处理进位
        int flag = 0;
        for(int i = len - 1; i >= 0; i--){
            result[i] += flag;
            flag = result[i]/10;
            result[i] = (char)(result[i] % 10 + '0');
        }
        int offset = 0;
        for(int i = 0; i < len; i++){
            if(result[i] != '0'){
                offset = i;
                break;
            }
        }
        return new String(result, offset, len - offset);

    }
    public static void main(String[] args) {
        LeetCode43 leetCode43 = new LeetCode43();
//        System.out.println(leetCode43.add(new String[]{"123", "977", "234"}, 1));
        System.out.println(leetCode43.multiply2("123", "456"));
    }

}
