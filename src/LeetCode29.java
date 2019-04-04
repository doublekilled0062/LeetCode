/**
 * 29. 两数相除
 *
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 * 示例 1:
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 示例 2:
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 说明:
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
 */
public class LeetCode29 {
    /**
     * 位运算 dividend 不停的去减 divisor*2^x (x = 31-0)逼近
     * 题目上要求只能用32位存储，所以网上说的用long升位代替32位无符号数有点不严谨
     * 所以需要额外的处理和判断
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        if(divisor == 0){
            //除数不能为0
            return 0;
        }
        //针对溢出和不用long类型做特殊处理
        if(dividend == Integer.MIN_VALUE) {
            if (divisor == -1) {
                //这种情况会溢出
                return Integer.MAX_VALUE;
            } else if (divisor == Integer.MIN_VALUE) {
                return 1;
            }
        }
        //其他情况除数为0x80000000的情况直接返回0
        if(divisor == Integer.MIN_VALUE){
            return 0;
        }else if(divisor == 1){
            return dividend;
        }

        //然后结果的正负号
        boolean sign = false;
        if((dividend ^ divisor) < 0){
            sign = true;
        }

        //然后新除数和被除数
        int unsignedDivisor = Math.abs(divisor);
        int unsignedDividend = 0;
        int unsignedMore = 0;
        if(dividend == Integer.MIN_VALUE){
            unsignedDividend = Integer.MAX_VALUE;
            unsignedMore = 1;
        }else {
            unsignedDividend = Math.abs(dividend);
        }
        int result = 0;
        int i = 31;
        while (i >= 0){
            if((unsignedDividend >> i) >= unsignedDivisor){
                result += 1 << i;
                unsignedDividend = unsignedDividend - (unsignedDivisor << i) + unsignedMore;
                if(unsignedMore > 0){
                    unsignedMore = 0;
                }
            }else {
                i--;
            }
        }
        return sign ? -result : result;
    }

    public static void main(String[] args) {
        LeetCode29 leetCode29 = new LeetCode29();
        System.out.println(leetCode29.divide(-2147483648, -2));
    }
}