/**
 * 300. 最长上升子序列
 *
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 *
 * 示例:
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 *
 * 说明:
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 */
public class LeetCode300 {
    /**
     * 初版动态规划 复杂度o(n^2)
     * dis[i]表示包括nums[i]并且为结尾的最大上升子序列的长度
     * 然后求dis[i]最大值
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int len = nums.length;
        int max = 1;
        int[] dis = new int[len];
        for(int i = 0; i < len; i++){
            dis[i] = 1;
            //计算每个dis[i] 遍历从dis[i-1]到0小于num[i]的j值
            // 使得dis[i] = max{dis[j], 对于所有的num[j] < num[i]}
            for(int j = i - 1; j >= 0; j--){
                if(nums[j] < nums[i]){
                    dis[i] = Math.max(dis[i], dis[j] + 1);
                }
            }
            max = Math.max(max, dis[i]);
        }
        return max;
    }

    /**
     * nlogn的
     * dis[i]变成对于上升子序列长度为i的时候 子序列最后一个元素的最小值
     * 所以对于一个数组中的数num 如果大于最后一个di[i]里的值 则d[i+1] = num
     * 所以就变成一个变形的二分查找问题
     * @param nums
     * @return
     */
    public int lengthOfLIS2(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int[] dis = new int[nums.length + 1];
        //从dis[1]开始
        dis[0] = Integer.MIN_VALUE;
        int index = 1;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] > dis[index - 1]){
                dis[index] = nums[i];
                index++;
            }else {
                int pos = getLastSmallIndex(dis, index - 1, nums[i]);
                dis[pos + 1] = nums[i];
            }
        }
        return index - 1;
    }

    private int getLastSmallIndex(int[] dis, int end, int value){
        int start = 1;
        while (start <= end){
            int mid = start + ((end - start) >> 1);
            if(dis[mid] >= value){
                end = mid - 1;
            }else {
                //start+1肯定不会出届 因为如果value > dis[end] 就不会调用到这里
                if(dis[mid + 1] >= value){
                    return mid;
                }
                start = mid + 1;
            }
        }
        return 0;
    }
}
