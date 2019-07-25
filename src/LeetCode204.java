import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 204. 计数质数
 *
 * 统计所有小于非负整数 n 的质数的数量。
 *
 * 示例:
 *
 * 输入: 10
 * 输出: 4
 * 解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 */
public class LeetCode204 {
    /**
     * 常规算法
     * 判断因子有没有1-该数平方根下的素数因子
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        if(n <= 2){
            return 0;
        }
        List<Integer> res = new ArrayList<>();
        res.add(2);
        for(int i = 3; i < n; i++){
            if(i % 2 == 0){
                continue;
            }
            if(checkPrime(i, res)){
                res.add(i);
            }
        }
        return res.size();
    }

    private boolean checkPrime(int n, List<Integer> list){
        for(Integer i : list){
            if(i * i > n){
                break;
            }
            if( n % i == 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 查资料
     * 厄拉多塞筛法 划 2 3 5的倍数
     * @param n
     * @return
     */
    public int countPrimes1(int n) {
        boolean[] nums = new boolean[n];

        //只需要遍历到n的平方根
        for (int i = 2; i * i < nums.length; i++) {
            if (!nums[i]) {
                for (int j = i * i; j < n; j += i) {
                    nums[j] = true;
                }
            }
        }

        int res = 0;
        for (int i = 2; i < n; i++) {
            if (!nums[i]){
                res++;
            }
        }
        return res;
    }

    /**
     * 查资料
     * 欧拉算法
     * prime数组中的素数是递增的
     * 当 i 能整除 prime[j]，那么 i*prime[j+1] 这个合数肯定被 prime[j] 乘以某个数筛掉。
     * 筛选与Eratosthenes不同，并不是按照顺序筛选，但每一个合数都等于一个数字乘以它的最小素因子，
     * 所以遍历每个数字 i 乘以小于i（若大于i，则i为最小素因子）的所有素因子可以保证，每个合数都被遍历到
     * @param n
     * @return
     */
    public int countPrimes2(int n) {
        int res = 0;
        boolean[] nums = new boolean[n];
        int[] primes = new int[n];

        for (int i = 2; i < n; i++) {
            if (!nums[i]){
                primes[res++] = i;
            }

            for (int j = 0; j < res && i * primes[j] < n; j++) {
                nums[i * primes[j]] = true;
                // 关键  每一个筛选数，只被一个数乘以它的最小素因子，
                // 如果i % prime[j] == 0，则证明 i中含有prime[j]这个素因子，
                // 所以prime[j + 1] 至 prime[prime.size()-1]都不是最小素因子
                if (i % primes[j] == 0){
                    break;
                }
            }
        }
        return res;//返回小于n的素数的个数

    }
    public static void main(String[] args) {
        LeetCode204 leetCode204 = new LeetCode204();
        System.out.println(leetCode204.countPrimes(10));
    }
}
