import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 18. 四数之和
 *
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * 注意：
 * 答案中不可以包含重复的四元组。
 * 示例：
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * 满足要求的四元组集合为：
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 */
public class LeetCode18 {
    /**
     * 用三数之和的思路去找
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if(nums == null || nums.length < 4){
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0; i <= nums.length - 4; i++){
            //每一个都用双指针去找
            if(i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            for(int j = i + 1; j <= nums.length - 3; j++){
                if(j > i + 1 && nums[j] == nums[j - 1]){
                    continue;
                }
                int l = j + 1;
                int r = nums.length - 1;
                while (l < r){
                    if(nums[l] + nums[r] + nums[i] + nums[j ]== target){
                        List<Integer> pairs = new ArrayList<>();
                        pairs.add(nums[i]);
                        pairs.add(nums[j]);
                        pairs.add(nums[l]);
                        pairs.add(nums[r]);
                        result.add(pairs);
                        l++;
                        r--;
                        while (l < r && nums[l] == nums[l - 1]){
                            //l重复了 可能会有重复结果
                            l++;
                        }
                        while (r > l && nums[r] == nums[r + 1]){
                            //r重复了 可能有重复结果
                            r--;
                        }
                    }else if(nums[l] + nums[r] + nums[i] + nums[j] < target){
                        l++;
                    }else {
                        r--;
                    }
                }
            }
        }
        return result;
    }

    /**
     * 递归的 效率略差 但是解决n数之和问题
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum2(int[] nums, int target) {
        if(nums == null || nums.length < 4){
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        return findSum(nums, target, 0, 4);
    }

    private List<List<Integer>> findSum(int[] nums, int target, int index, int count){
        List<List<Integer>> result = new ArrayList<>();
        int len = nums.length;
        if(index >= len || len - index < count){
            return result;
        }
        if(count == 2){
            //两个数用双指针
            int l = index;
            int r = len-1;
            while(l < r){
                if(nums[r]+nums[l] == target){
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[l]);
                    temp.add(nums[r]);
                    result.add(temp);
                    r--;
                    l++;
                    while (l < len && nums[l] == nums[l-1]){
                        l++;
                    }
                    while (r >= 0 && nums[r] == nums[r+1]){
                        r--;
                    }
                }else if (nums[r] + nums[l] > target) {
                    r--;
                }else {
                    l++;
                }
            }
            return result;
        }
        for (int i = index; i < len; i++) {
            //递归调用 然后组合结果
            if(i > index && nums[i] == nums[i - 1]){
                continue;
            }
            List<List<Integer>> temp = findSum(nums, target - nums[i], i + 1, count - 1);
            for(List<Integer> t : temp){
                //组合结果
                t.add(0, nums[i]);
                result.add(t);
            }
        }
        return result;
    }


    public static void main(String[] args) {
        LeetCode18 leetCode18 = new LeetCode18();
        System.out.println(leetCode18.fourSum2(new int[]{-3,-2,-1,0,0,1,2,3}, 0));
    }
}
