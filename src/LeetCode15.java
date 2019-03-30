import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和
 *
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 */
public class LeetCode15 {
    /**
     * 同16题 虽然通过了 但是写的不严谨 因为可能会溢出 所以结果中间值最好用long
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        if(nums == null || nums.length < 3){
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0; i <= nums.length - 3; i++){
            //每一个都用双指针去找
            if(i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            if(nums[i] > 0){
                //最小的数都大于0 别好了
                break;
            }
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k){
                if(nums[j] + nums[k] + nums[i] == 0){
                    List<Integer> pairs = new ArrayList<>();
                    pairs.add(nums[i]);
                    pairs.add(nums[j]);
                    pairs.add(nums[k]);
                    result.add(pairs);
                    j++;
                    k--;
                    while (j < k && nums[j] == nums[j - 1]){
                        //j重复了 可能会有重复结果
                        j++;
                    }
                    while (k > j && nums[k] == nums[k + 1]){
                        //k重复了 可能有重复结果
                        k--;
                    }
                }else if(nums[j] + nums[k] + nums[i] < 0){
                    j++;
                }else {
                    k--;
                }
            }
        }
        return result;
    }
}
