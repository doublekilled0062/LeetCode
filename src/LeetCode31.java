/**
 * 31. 下一个排列
 *
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须原地修改，只允许使用额外常数空间。
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 */
public class LeetCode31 {
    /**
     * 从后往前找第一个顺序的  4 7 ... 2模式 然后4换成7...2之间第一个大于4的数 再反转
     * 如果都是从大到小 则全部反转
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        if(nums == null || nums.length == 0 || nums.length == 1){
            return;
        }
        int len = nums.length;
        int i = len - 2;
        while (i >= 0){
            if(nums[i] < nums[i + 1]){
                break;
            }
            i--;
        }
        if(i >= 0){
            //找到这样一个i 然后找i+1 往后第一个大于nums[i]的索引
            int index = binarySearch(nums, i + 1, nums[i]);
            int tmp = nums[i];
            nums[i] = nums[index];
            nums[index] = tmp;
        }

        int j = nums.length - 1;
        while (i + 1 < j){
            int tmp = nums[i + 1];
            nums[i + 1] = nums[j];
            nums[j] = tmp;
            i++;
            j--;
        }
        return;
    }

    private int binarySearch(int[] nums, int startIndex, int target){
        int start = startIndex;
        int end = nums.length - 1;
        while (start <= end){
            int mid = start + ((end - start) >> 1);
            if(nums[mid] > target){
                if(mid == nums.length - 1 || nums[mid + 1] <= target){
                    return mid;
                }else {
                    start = mid + 1;
                }
            }else {
                if(mid == startIndex){
                    return -1;
                }
                if(nums[mid - 1] > target){
                    return mid - 1;
                }
                end = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        LeetCode31 leetCode31 = new LeetCode31();
        leetCode31.nextPermutation(new int[]{1, 2, 3});
    }
}
