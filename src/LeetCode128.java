import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 128. 最长连续序列
 *
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 * 要求算法的时间复杂度为 O(n)。
 * 示例:
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 */
public class LeetCode128 {
    /**
     * 用一个map存当前每个key所在的连续数的个数
     * 20ms 20%
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int max = 0;
        //value为某个数的连续个数
        Map<Integer, Integer> map = new HashMap<>();
        //value为某个数的连续序列的最小数
        Map<Integer, Integer> mapMin = new HashMap<>();
        //value为某个数的连续序列的最大数
        Map<Integer, Integer> mapMax = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(nums[i])){
                continue;
            }
            int total = 1;
            if(map.containsKey(nums[i] - 1)){
                //包含左边序列
                total += map.get(nums[i] - 1);
            }
            if(map.containsKey(nums[i] + 1)){
                total += map.get(nums[i] + 1);
            }
            //更新3个map
            map.put(nums[i], total);
            if(map.containsKey(nums[i] - 1)){
                //左边有值
                int minValue = mapMin.get(nums[i] - 1);
                mapMin.put(nums[i], minValue);
                map.put(minValue, total);
            }else {
                mapMin.put(nums[i], nums[i]);
            }
            if(map.containsKey(nums[i] + 1)){
                //右边有值
                int maxValue = mapMax.get(nums[i] + 1);
                mapMax.put(nums[i], maxValue);
                map.put(maxValue, total);
            }else {
                mapMax.put(nums[i], nums[i]);
            }
            //更新最小值的右边 和最大值的左边
            mapMax.put(mapMin.get(nums[i]), mapMax.get(nums[i]));
            mapMin.put(mapMax.get(nums[i]), mapMin.get(nums[i]));
            max = Math.max(total, max);
        }
        return max;
    }

    /**
     * 思路还是那个思路 但是mapMin和mapMax中的最小值和最大值可以用计算来代替
     * @param nums
     * @return
     */
    public int longestConsecutive2(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int max = 0;
        //value为某个数的连续个数
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums){
            if(map.containsKey(num)){
                continue;
            }
            //包含左边序列
            int left = map.getOrDefault(num - 1, 0);
            int right = map.getOrDefault(num + 1, 0);
            int total = 1 + left + right;

            //更新两个端点的map值
            map.put(num, total);
            if(left > 0){
                map.put(num - left, total);
            }
            if(right > 0){
                map.put(num + right, total);
            }
            if(total > max){
                max = total;
            }
        }
        return max;
    }

    /**
     * 笑了 题干要求复杂度O(n)
     * 结果提交记录上最快的是用排序的方法
     * @param nums
     * @return
     */
    public int longestConsecutive3(int[] nums) {
        if(nums.length < 2){
            return nums.length;
        }
        Arrays.sort(nums);
        int max = 1;
        int count = 1;
        for(int i = 1; i<nums.length; i++){
            if(nums[i] == nums[i-1] + 1){
                count++;
            }else if(nums[i] != nums[i-1]){
                count = 1;
            }
            max = Math.max(max,count);
        }
        return max;
    }

    public static void main(String[] args) {
        LeetCode128 leetCode128 = new LeetCode128();
        System.out.println(leetCode128.longestConsecutive2(new int[]{1,2,0,1}));
        System.out.println(leetCode128.longestConsecutive2(new int[]{-2,-3,-3,7,-3,0,5,0,-8,-4,-1,2}));
    }
}
