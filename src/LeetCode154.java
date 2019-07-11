/**
 * 154. 寻找旋转排序数组中的最小值 II
 *
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 请找出其中最小的元素。
 *
 * 注意数组中可能存在重复的元素。
 *
 * 示例 1：
 *
 * 输入: [1,3,5]
 * 输出: 1
 * 示例 2：
 *
 * 输入: [2,2,2,0,1]
 * 输出: 0
 * 说明：
 *
 * 这道题是 寻找旋转排序数组中的最小值 的延伸题目。
 * 允许重复会影响算法的时间复杂度吗？会如何影响，为什么？
 *
 */
public class LeetCode154 {
    public int findMin(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }
        int start = 0;
        int end = nums.length - 1;
        int min = nums[0];
        while (start <= end){
            while (start < end){
                if(nums[start] == nums[start+1]){
                    start++;
                }else {
                    break;
                }
            }
            while (end > start){
                if(nums[end] == nums[end - 1]){
                    end--;
                }else {
                    break;
                }
            }
            if(start == end){
                return min < nums[start] ? min : nums[start];
            }
            if(nums[start] < nums[end]){
                return min < nums[start] ? min : nums[start];
            }
            int mid = start + (end - start)/2;
            if(nums[start] <= nums[mid]){
                min = min < nums[start] ? mid : nums[start];
                start = mid + 1;
            }else {
                min = min < nums[mid] ? mid : nums[mid];
                end = mid - 1;
            }
        }
        return min;
    }
}
