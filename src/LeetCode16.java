import java.util.ArrayList;
import java.util.Arrays;

/**
 * 16. 最接近的三数之和
 *
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。
 * 找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。
 * 假定每组输入只存在唯一答案。
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 */
public class LeetCode16 {
    /**
     * 15和16题虽然通过了 但是感觉都不严谨因为几个数加起来可能会溢出的 中间值最好用long
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        if(nums == null || nums.length < 3){
            throw new RuntimeException();
        }
        Arrays.sort(nums);
        int minValue = 0;
        for(int i = 0; i <= nums.length - 3; i++){
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k){
                int value = nums[j] + nums[k] + nums[i];
                if(value == target){
                    return target;
                }else {
                    if(minValue == 0){
                        minValue = value - target;
                    }else {
                        minValue = Math.abs(minValue) > Math.abs(value - target) ? value - target : minValue;
                    }
                    if(value < target){
                        j++;
                    }else {
                        k--;
                    }
                }
            }
        }
        return minValue + target;
    }
}
