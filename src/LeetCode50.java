/**
 * 50. Pow(x, n)
 *
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 * 示例 1:
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 * 示例 2:
 * 输入: 2.10000, 3
 * 输出: 9.26100
 * 示例 3:
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2-2 = 1/22 = 1/4 = 0.25
 * 说明:
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
 *
 */
public class LeetCode50 {
    /**
     * 这个题想错方向了 甘当代码搬运工
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        double half = myPow(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        }
        if (n > 0) {
            return half * half * x;
        }
        return half * half / x;
    }

    double myPow2(double x, int n) {
        double res = 1.0;
        for (int i = n; i != 0; i /= 2) {
            if (i % 2 != 0) {
                res *= x;
            }
            x *= x;
        }
        return n < 0 ? 1 / res : res;
    }

    public double myPow3(double x, int n) {
        double result = 1;
        if (n == Integer.MIN_VALUE) {
            n = n + 2;
        }
        int tempN = Math.abs(n);

        for (int i = 31; i >= 0; i--) {
            result = result * result;
            if ((tempN & (1 << i)) > 0) {
                result = result * x;
            }
        }
        if (n < 0) {
            result = 1 / result;
        }

        return result;
    }

    public static void main(String[] args) {
        LeetCode50 leetCode50 = new LeetCode50();
        System.out.println(leetCode50.myPow2(-100.0, -3));
    }
}
