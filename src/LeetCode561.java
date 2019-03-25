/**
 * 561. 数组拆分 I
 *
 * 给定长度为 2n 的数组, 你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从1 到 n 的 min(ai, bi) 总和最大。
 *
 * 示例 1:
 * 输入: [1,4,3,2]
 * 输出: 4
 * 解释: n 等于 2, 最大总和为 4 = min(1, 2) + min(3, 4).
 *
 * 提示:
 * n 是正整数,范围在 [1, 10000].
 * 数组中的元素范围在 [-10000, 10000].
 */
public class LeetCode561 {
    /**
     * 肯定是{最小，次小}这种配对方式
     * 先排序 然后再结对 关键是用什么方法排序  推荐桶排序 然后就是往下统计就好了
     * @param nums
     * @return
     */
    public int arrayPairSum(int[] nums) {
        //加10000修正值放进去
        int[] buckets = new int[20001];
        for(int i = 0; i < nums.length; i++){
            buckets[nums[i] + 10000] += 1;
        }
        int flag = 0; //未配对个数
        int sum = 0;
        for(int i = 0; i < buckets.length; i++){
            if(buckets[i] == 0){
                continue;
            }
            if(flag == 0){
                int rate = (buckets[i] + 1) >> 1;
                if((buckets[i] & 1) == 0){
                    //数有偶数个 加上rate个 flag不变
                    sum += (i - 10000) * rate;
                }else {
                    //数有奇数个 加上rate个 flag = 1
                    sum += (i - 10000) * rate;
                    flag = 1;
                }
            }else {
                int rate = buckets[i] >> 1;
                if((buckets[i] & 1) == 0){
                    sum += (i - 10000) * rate;
                }else {
                    sum += (i - 10000) * rate;
                    flag = 0;
                }
            }
        }
        return sum;
    }
}
