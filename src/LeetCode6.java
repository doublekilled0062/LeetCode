/**
 * 6. Z 字形变换
 *
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 *
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 *
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 * 请你实现这个将字符串进行指定行数变换的函数：
 * string convert(string s, int numRows);
 *
 * 示例 1:
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 * 示例 2:
 *
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * 解释:
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 */
public class LeetCode6 {

    /**
     * 分行存储 mod = 2 * nuRows - 2;
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        if(s == null || numRows <= 1 || numRows >= s.length()){
            return s;
        }
        StringBuffer[] buffers = new StringBuffer[numRows];
        for(int i = 0; i < numRows; i++){
            buffers[i] = new StringBuffer();
        }
        int mod = 2 * numRows - 2;
        for(int i = 0; i < s.length(); i++){
            int j = i % mod;
            if(j < numRows){
                buffers[j].append(s.charAt(i));
            }else {
                buffers[mod - j].append(s.charAt(i));
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        for(int i = 0; i < buffers.length; i++){
            stringBuffer.append(buffers[i]);
        }
        return stringBuffer.toString();
    }

    /**
     * 优化下上面
     * @param s
     * @param numRows
     * @return
     */
    public String convert2(String s, int numRows) {
        if(s == null || numRows <= 1 || numRows >= s.length()){
            return s;
        }
        int length = s.length();
        char[] newStr = new char[length];
        int mod = 2 * numRows - 2;
        int index = 0;
        for(int i = 0; i < numRows; i++){
            for(int j = 0; j + i < length; j = j + mod){
                //第一列先写
                newStr[index] = s.charAt(j + i);
                index++;
                //再写不是第一行和最后一行的第二列
                if(i != 0 && i != numRows - 1 && j + mod - i < length){
                    newStr[index] = s.charAt(j + mod - i);
                    index++;
                }
            }
        }
        return new String(newStr);
    }

    public static void main(String[] args) {
        LeetCode6 leetCode6 = new LeetCode6();
        System.out.print(leetCode6.convert("LEETCODEISHIRING", 4));
    }
}
