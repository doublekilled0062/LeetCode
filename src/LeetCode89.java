import java.util.ArrayList;
import java.util.List;

/**
 * 89. 格雷编码
 *
 * 格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
 * 给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。格雷编码序列必须以 0 开头。
 * 示例 1:
 * 输入: 2
 * 输出: [0,1,3,2]
 * 解释:
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 * 对于给定的 n，其格雷编码序列并不唯一。
 * 例如，[0,2,3,1] 也是一个有效的格雷编码序列。
 * 00 - 0
 * 10 - 2
 * 11 - 3
 * 01 - 1
 * 示例 2:
 * 输入: 0
 * 输出: [0]
 * 解释: 我们定义格雷编码序列必须以 0 开头。
 * 给定编码总位数为 n 的格雷编码序列，其长度为 2^n。当 n = 0 时，长度为 2^0 = 1。
 * 因此，当 n = 0 时，其格雷编码序列为 [0]。
 */
public class LeetCode89 {
    /**
     * 找规律题
     * 1的编码 [0 1] 2的 [00 01 11 10] 3的 [000 001 011 010 110 111 101 100]
     * 用递推每一个的编码都是 前一个编码的前一位（最后一位也一样）补0 然后倒序补1
     * 如果在最前位补0 则n 到n-1的编码是有一半不变的 这样比后面补0少一半操作
     * 即 3的[0 1 3 2  6 7 5 4] 是 2的[0 1 3 2] 扩展
     * 最后一位补0和1 暂时没想到简便的方法
     * @param n
     * @return
     */
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        if(n == 0){
            res.add(0);
            return res;
        }

        if(n == 1){
            res.add(0);
            res.add(1);
            return res;
        }
        List<Integer> temp = grayCode(n-1);
        //向前加0或者1 只需要计算一半的量 size要提前获取
        int size = temp.size();
        int step = 1 << (n-1);
        for(int i = size - 1; i >= 0; i--){
            temp.add(temp.get(i) + step);
        }
        return temp;
    }

    /**
     * 递归变循环
     * @param n
     * @return
     */
    public List<Integer> grayCode2(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(0);
        for (int i = 0; i < n; i++) {
            int step = 1 << i;
            int size = res.size();
            for (int j = size - 1; j >= 0; j--) {
                res.add(res.get(j) + step);
            }
        }
        return res;
    }

    /**
     * 看了高分答案 这个应该是设计算法的本质
     * @param n
     * @return
     */
    public List<Integer> grayCode3(int n) {
        /**
         关键是搞清楚格雷编码的生成过程, G(i) = i ^ (i/2);
         如 n = 3:
         G(0) = 0 ^ 0 = 000 ^ 000 = 000
         G(1) = 1 ^ 0 = 001 ^ 000 = 001
         G(2) = 2 ^ 1 = 010 ^ 001 = 011
         G(3) = 3 ^ 1 = 011 ^ 001 = 010
         G(4) = 4 ^ 2 = 100 ^ 010 = 110
         G(5) = 5 ^ 2 = 101 ^ 010 = 111
         G(6) = 6 ^ 3 = 110 ^ 011 = 101
         G(7) = 7 ^ 3 = 111 ^ 011 = 100
         **/
        List<Integer> ret = new ArrayList<>();
        for(int i = 0; i < 1<<n; ++i){
            ret.add(i ^ i>>1);
        }
        return ret;
    }

    public static void main(String[] args) {
        LeetCode89 leetCode89 = new LeetCode89();
        leetCode89.grayCode(3);
    }
}
