import java.util.HashMap;

/**
 * 166. 分数到小数
 *
 * 给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以字符串形式返回小数。
 *
 * 如果小数部分为循环小数，则将循环的部分括在括号内。
 *
 * 示例 1:
 *
 * 输入: numerator = 1, denominator = 2
 * 输出: "0.5"
 * 示例 2:
 *
 * 输入: numerator = 2, denominator = 1
 * 输出: "2"
 * 示例 3:
 *
 * 输入: numerator = 2, denominator = 3
 * 输出: "0.(6)"
 *
 */
public class LeetCode166 {
    /**
     * 算余数用 乘10%denominator
     * 用hashmap存余数 key=余数 value = 出现位置
     * 如果余数有重复 就是有循环小树 根据索引加括号
     * 还要有正负号判断
     * @param numerator
     * @param denominator
     * @return
     */
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        //判断符号位
        StringBuilder builder = new StringBuilder();
        if((numerator ^ denominator) < 0 ){
            builder.append('-');
        }

        long unsignNumerator = Math.abs(Long.valueOf(numerator));
        long unsignDenominator = Math.abs(Long.valueOf(denominator));

        long quotients = unsignNumerator/unsignDenominator;
        builder.append(quotients);

        long remain = unsignNumerator % unsignDenominator;
        if(remain == 0){
            return builder.toString();
        }
        builder.append(".");

        HashMap<Long, Integer> map = new HashMap<>();
        while (remain != 0) {
            remain *= 10;
            if (map.containsKey(remain)) {
                builder.insert(map.get(remain), "(").append(")");
                break;
            }
            map.put(remain, builder.length());
            builder.append(remain / unsignDenominator);
            remain %= unsignDenominator;
        }
        return builder.toString();
    }

    /**
     * 题解的用了数学知识 如果分母都是2或者5的乘积 能整除
     * 没有2或者5 纯循环小数
     * 有2或者5还有别的 混循环小数 需要把2和5的求好了 再求循环部分
     * @param numerator
     * @param denominator
     * @return
     */
    public String fractionToDecimal1(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        return fractionToDecimal2(numerator, denominator);
    }

    public String fractionToDecimal2(long numerator, long denominator) {
        StringBuilder sb1 = new StringBuilder();
        if((numerator ^ denominator) < 0 ){
            sb1.append('-');
        }
        numerator = Math.abs(numerator);
        denominator = Math.abs(denominator);

        //能整除
        sb1.append(numerator / denominator);
        if (numerator % denominator == 0){
            return sb1.toString();
        }

        sb1.append(".");
        numerator %= denominator;
        while (numerator != 0 && denominator % 2 == 0 || denominator % 5 == 0) {
            if (denominator % 10 == 0){
                denominator /= 10;
            }
            else if (denominator % 2 == 0) {
                denominator /= 2;
                numerator *= 5;
            } else {
                denominator /= 5;
                numerator *= 2;
            }
            sb1.append(numerator / denominator);
            numerator %= denominator;
        }
        //非循环小数
        if (numerator % denominator == 0){
            return sb1.toString();
        }

        numerator %= denominator;
        long reminder = numerator;
        StringBuilder sb2 = new StringBuilder();
        while (true) {
            reminder *= 10;
            sb2.append(reminder / denominator);
            reminder = reminder % denominator;
            if (reminder % denominator == 0 || reminder == numerator){
                break;
            }
        }
        if (reminder % denominator == 0){
            return sb1.append(sb2).toString();
        } else {
            return sb1.append("(").append(sb2).append(")").toString();
        }
    }

    public static void main(String[] args) {
        LeetCode166 leetCode166 = new LeetCode166();
        System.out.println(leetCode166.fractionToDecimal(1, 2));
    }
}
