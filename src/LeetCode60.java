/**
 * 60. 第k个排列
 *
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 * 说明：
 * 给定 n 的范围是 [1, 9]。
 * 给定 k 的范围是[1,  n!]。
 * 示例 1:
 * 输入: n = 3, k = 3
 * 输出: "213"
 * 示例 2:
 * 输入: n = 4, k = 9
 * 输出: "2314"
 */
public class LeetCode60 {
    /**
     * 不用求全排列 找规律即可 123全排列中 1 和 2 和 3开头的排列都为(3-1)!个
     * @param n
     * @param k
     * @return
     */
    public String getPermutation(int n, int k) {
        StringBuilder builder = new StringBuilder();
        boolean[] used = new boolean[n];
        int[] factorials = new int[n - 1];
        int nTemp = n;
        int i = 0;
        for(; i < n; i++){
            int index = getIndex(nTemp, k, factorials);
            int v = 1;
            int j = 0;
            for(; j < n; j++){
                if(used[j] == true){
                    continue;
                }
                if(v == index){
                    break;
                }
                v++;
            }
            builder.append(j+1);
            if(i < n - 1){
                used[j] = true;
                k = k - (index - 1) * getFactorial(nTemp - 1, factorials);
                nTemp = nTemp - 1;
            }
        }
        return builder.toString();
    }

    /**
     * 获得idnex的值为 index *(n-1)! <= k < (index+1)*(n-1)!
     * @param n
     * @param k
     * @return
     */
    public int getIndex(int n, int k, int[] factorials){
        if(n == 1 && k == 1){
            return 1;
        }
        int index = 1;
        int factorial = getFactorial(n - 1, factorials);
        while (k > 0){
            k -= factorial;
            if(k > 0){
                index++;
            }
        }
        return index;
    }

    /**
     * 算阶乘 有暂存结果
     * @param n
     * @param factorials
     * @return
     */
    private int getFactorial(int n, int[] factorials){
        if(factorials[n - 1] > 0){
            return factorials[n - 1];
        }
        int res = 1;
        for(int i = 1; i <= n; i++){
            res *= i;
        }
        factorials[n - 1] = res;
        return res;
    }

    public static void main(String[] args) {
        LeetCode60 leetCode60 = new LeetCode60();
        System.out.println(leetCode60.getPermutation(4, 9));
//        System.out.println(leetCode60.getIndex(3, 6, new int[2]));
    }
}
