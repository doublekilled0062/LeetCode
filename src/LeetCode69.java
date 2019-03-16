/**
 * 69. x 的平方根
 *
 * 实现 int sqrt(int x) 函数。
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 *
 * 示例 1:
 * 输入: 4
 * 输出: 2
 *
 * 示例 2:
 * 输入: 8
 * 输出: 2
 *
 * 说明: 8 的平方根是 2.82842...,
 * 由于返回类型是整数，小数部分将被舍去。
 */
public class LeetCode69 {
    /**
     * 牛顿迭代法 切线方程 Y = f'(X)(X - Xn) + Xn
     * Xk+1 = Xk - f(Xk)/f'(Xk)
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if(x == 0 || x == 1){
            return x;
        }
        long start = x;
        while (start * start > x && (start - 1)*(start - 1) > x){
            start = start - (start * start - x)/(2 * start);
        }
        long result = (int)start;
        return result * result > x ? (int)(result - 1) : (int)result;
    }

    /**
     * 公式化简一下
     * @param x
     * @return
     */
    public int mySqrt2(int x) {
        if(x == 0 || x == 1){
            return x;
        }
        long start = x;
        while (start * start > x){
            start = (start + x/start)/2;
        }
        return (int)start;
    }

    public static void main(String[] args) {
        LeetCode69 leetCode69 = new LeetCode69();
        System.out.println(leetCode69.mySqrt(8));
    }
}
