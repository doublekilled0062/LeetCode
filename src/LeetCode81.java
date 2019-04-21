/**
 * 81. 搜索旋转排序数组 II
 *
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。
 * 编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。
 * 示例 1:
 * 输入: nums = [2,5,6,0,0,1,2], target = 0
 * 输出: true
 * 示例 2:
 * 输入: nums = [2,5,6,0,0,1,2], target = 3
 * 输出: false
 * 进阶:
 * 这是 搜索旋转排序数组 的延伸题目，本题中的 nums  可能包含重复元素。
 * 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？
 */
public class LeetCode81 {
    /**
     * 还是二分 比leetcode33多了个去重
     * @param nums
     * @param target
     * @return
     */
    public boolean search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end){
            //加上提前去重 保证如果mid != start或者mid != end时
            // num[mid] != num[start] 或者 num[mid] != num[end]
            while(start < end && nums[start] == nums[start + 1]){
                start++;
            }
            while(start < end && nums[end] == nums[end - 1]){
                end--;
            }

            int mid = start + (end - start)/2;
            if(nums[mid] == target){
                return true;
            }
            //左半边有序
            if(nums[mid] >= nums[start]){
                if(nums[start] <= target && nums[mid] > target){
                    //在左边
                    end = mid - 1;
                }else {
                    start = mid + 1;
                }
            }else {
                //右半边有序
                if(nums[mid] < target && nums[end] >= target){
                    //再右边
                    start = mid + 1;
                }else {
                    end = mid - 1;
                }
            }
        }
        return false;
    }

    /**
     * 不去重
     * @param nums
     * @param target
     * @return
     */
    public boolean search2(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end){
            int mid = start + (end - start)/2;
            if(nums[mid] == target){
                return true;
            }
            //左半边严格有序
            if(nums[mid] > nums[start]){
                if(nums[start] <= target && nums[mid] > target){
                    //在左边
                    end = mid - 1;
                }else {
                    start = mid + 1;
                }
            }else if(nums[mid] < nums[end]){
                //右半边有序
                if(nums[mid] < target && nums[end] >= target){
                    //再右边
                    start = mid + 1;
                }else {
                    end = mid - 1;
                }
            }else {
                // nums[mid] <= nums[start] 并且 nums[mid] >= nums[end]
                // 这样要么 nums[mid] = nums[start] 要么 nums[mid] = nums[end]
                if(nums[mid] ==  nums[start] && nums[mid] == nums[end]){
                    do{
                        start++;
                    } while (start < end && nums[start] == nums[mid] );
                    do{
                        end--;
                    } while (start < end && nums[end] == nums[mid]);
                }else if(nums[mid] ==  nums[start]){
                    start = mid + 1;
                }else {
                    end = mid - 1;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        LeetCode81 leetCode81 = new LeetCode81();
        System.out.println(leetCode81.search2(new int[]{2,5,6,0,0,1,2},3));
    }
}
