/**
 * 67. 二进制求和
 *
 * 给定两个二进制字符串，返回他们的和（用二进制表示）。
 * 输入为非空字符串且只包含数字 1 和 0。
 *
 * 示例 1:
 * 输入: a = "11", b = "1"
 * 输出: "100"
 *
 * 示例 2:
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 */
public class LeetCode67 {
    public String addBinary(String a, String b) {
        //结果集看有无进位肯定是小于等与最长的+1
        int maxLen = Math.max(a.length(), b.length());
        char[] result = new char[maxLen + 1];
        int i = a.length() - 1;
        int j = b.length() - 1;
        int index = maxLen - 1;
        int carry = 0;
        while (index >= 0){
            int xa = 0;
            int xb = 0;
            if(i >= 0){
                xa = a.charAt(i) - '0';
            }
            if(j >= 0){
                xb = b.charAt(j) - '0';
            }
            carry = carry + xa + xb;
            result[index] = (char)(carry/2 + '0');
            result[index + 1] = (char) (carry % 2 + '0');
            carry = carry/2;
            i--;
            j--;
            index--;
        }
        index = -1;
        for(int k = 0; k < result.length; k++){
            if(result[k] - '0' != 0){
                index = k;
                break;
            }
        }
        if(index != -1){
            return new String(result, index, result.length - index);
        }else {
            return "0";
        }

    }

    public static void main(String[] args) {
        LeetCode67 leetCode67 = new LeetCode67();
        System.out.println(leetCode67.addBinary("11","1"));
    }
}
