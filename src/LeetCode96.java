import java.util.ArrayList;
import java.util.List;

/**
 * 96. 不同的二叉搜索树
 *
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 * 示例:
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 *       1         3     3      2      1
 *        \       /     /      / \      \
 *         3     2     1      1   3      2
 *        /     /       \                 \
 *       2     1         2                 3
 *
 */
public class LeetCode96 {
    /**
     * 和leetcode95一样 这个简单
     * 然而用一样的套路去写这个代码 会有1160秒
     * 所以递归备忘录或者动态规划就可以用了
     * @param n
     * @return
     */
    public int numTrees(int n) {
        if(n <= 0){
            return 0;
        }
        return numTrees(1, n);
    }

    public int numTrees(int start, int end) {
        if(start == end){
            return 1;
        }
        if(end - start == 1){
            return 2;
        }
        int sum = 0;
        for(int mid = start; mid <= end; mid++){
            int left = 1;
            int right = 1;
            if(start != mid){
                //说明有左子树
                left = numTrees(start, mid - 1);
            }
            if(end != mid){
                //说明有右子树
                right = numTrees(mid + 1, end);
            }
            sum += left * right;

        }
        return sum;
    }

    public int numTrees2(int n) {
        if(n <= 0){
            return 0;
        }
        int[] total = new int[n+1];
        total[0] = 1;
        total[1] = 2;
        return numTrees(1, n, total);
    }

    public int numTrees(int start, int end, int[] total) {
        if(total[end - start] != 0){
            return total[end - start];
        }
        int sum = 0;
        for(int mid = start; mid <= end; mid++){
            int left = 1;
            int right = 1;
            if(start != mid){
                //说明有左子树
                left = numTrees(start, mid - 1, total);
            }
            if(end != mid){
                //说明有右子树
                right = numTrees(mid + 1, end, total);
            }
            sum += left * right;

        }
        total[end - start] = sum;
        return sum;
    }

    /**
     * 动态规划也一样
     * @param n
     * @return
     */
    public int numTrees3(int n) {
        if (n <= 1) {
            return 1;
        }

        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            int count = 0;
            for (int j = 1; j <= i; j++) {
                count += dp[j - 1] * dp[i - j];
            }
            dp[i] = count;
        }
        return dp[n];
    }
}
