/**
 * 33. 搜索螺旋排序数组
 *
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 你可以假设数组中不存在重复的元素。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 示例 1:
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 *
 * 示例 2:
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 */
public class LeetCode33 {
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        while (start <= end){
            int mid = start + ((end - start) >> 1);
            if(nums[mid] == target){
                return mid;
            }else if(nums[mid] > target){
                if(nums[start] == target){
                    return start;
                } else if(nums[start] < target){
                    end = mid - 1;
                }else {
                    if(nums[start] <= nums[mid]){
                        start = mid + 1;
                    }else {
                        end = mid - 1;
                    }
                }
            }else {
                if(nums[end] == target){
                    return end;
                }else if(nums[end] > target){
                    start = mid + 1;
                }else {
                    if(nums[mid] <= nums[end]){
                        end = mid - 1;
                    }else {
                        start = mid + 1;
                    }
                }
            }
        }
        return -1;
    }

    /**
     * 这样思路清晰点
     * @param nums
     * @param target
     * @return
     */
    public int search2(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end){
            int mid = start + (end - start)/2;
            if(nums[mid] == target){
                return mid;
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
        return -1;
    }
    public static void main(String[] args) {
        LeetCode33 leetCode33 = new LeetCode33();
        int a[] = {4, 5, 6, 7, 8, 9, 0, 1, 2, 3};
        int b[] = {0, 1, 2, 3, 4, 5, 6};
        int c[] = {3, 1};
        System.out.println(leetCode33.search2(a, 3));
        System.out.println(leetCode33.search(b, 4));
        System.out.println(leetCode33.search(c, 1));
    }

}
