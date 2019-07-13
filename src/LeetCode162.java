/**
 * 162. 寻找峰值
 *
 * 峰值元素是指其值大于左右相邻值的元素。
 *
 * 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
 *
 * 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
 *
 * 你可以假设 nums[-1] = nums[n] = -∞。
 *
 * 示例 1:
 *
 * 输入: nums = [1,2,3,1]
 * 输出: 2
 * 解释: 3 是峰值元素，你的函数应该返回其索引 2。
 * 示例 2:
 *
 * 输入: nums = [1,2,1,3,5,6,4]
 * 输出: 1 或 5
 * 解释: 你的函数可以返回索引 1，其峰值元素为 2；
 *      或者返回索引 5， 其峰值元素为 6。
 * 说明:
 *
 * 你的解法应该是 O(logN) 时间复杂度的。
 *
 */
public class LeetCode162 {
    private int res = -1;
    public int findPeakElement(int[] nums) {
        if(nums.length == 1){
            return 0;
        }
        findPeakElement(nums, 0, nums.length - 1);
        return res;
    }

    private void findPeakElement(int[] nums, int start, int end){
        if(start == end || start < 0 || end >= nums.length || res >= 0){
            return;
        }
        if(end - start == 1){
            if(start == 0){
                if(nums[end] < nums[start]){
                    res = start;
                    return;
                }
            }
            if(end == nums.length - 1){
                if(nums[end] > nums[start]){
                    res = end;
                    return;
                }
            }
            return;
        }
        int mid = start + (end - start)/2;
        if(nums[mid] > nums[mid - 1]){
            if(nums[mid] > nums[mid + 1]){
                res = mid;
                return;
            }else {
                findPeakElement(nums, start, mid - 1);
                findPeakElement(nums, mid, end);
            }
        }else {
            findPeakElement(nums, start, mid);
            if(nums[mid] < nums[mid] + 1){
                findPeakElement(nums, mid, end);
            }else {
                findPeakElement(nums, mid + 1, end);
            }
        }
    }

    /**
     * 题解 思路简洁
     * 首先要注意题目条件，在题目描述中出现了 nums[-1] = nums[n] = -∞，这就代表着 只要数组中存在一个元素比相邻元素大，那么沿着它一定可以找到一个峰值
     * 根据上述结论，我们就可以使用二分查找找到峰值
     * 查找时，左指针 l，右指针 r，以其保持左右顺序为循环条件
     * 根据左右指针计算中间位置 m，并比较 m 与 m+1 的值，如果 m 较大，则左侧存在峰值，r = m，如果 m + 1 较大，则右侧存在峰值，l = m + 1
     *
     * @param nums
     * @return
     */
    public int findPeakElement1(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > nums[mid + 1]) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    public static void main(String[] args) {
        LeetCode162 leetCode162 = new LeetCode162();
        System.out.println(leetCode162.findPeakElement(new int[]{3, 2, 1}));
    }
}
