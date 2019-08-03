/**
 * 179. 最大数
 *
 * 给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。
 *
 * 示例 1:
 *
 * 输入: [10,2]
 * 输出: 210
 * 示例 2:
 *
 * 输入: [3,30,34,5,9]
 * 输出: 9534330
 * 说明: 输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 *
 */
public class LeetCode179 {
    /**
     * 排序思想 但是判断的条件要改变 12 和 121大小变成 12 121 和 121 12 两个字符串比较
     * @param nums
     * @return
     */
    public String largestNumber(int[] nums) {
        if(nums == null || nums.length == 0){
            return "0";
        }
        if(nums.length == 1){
            return String.valueOf(nums[0]);
        }

        boolean allZero = true;
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != 0){
                allZero = false;
            }
            strs[i] = String.valueOf(nums[i]);
        }

        if(allZero){
            return "0";
        }

        quickSort(strs, 0, nums.length - 1);
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < strs.length; i++){
            builder.append(strs[i]);
        }
        return builder.toString();
    }

    /**
     * 快排
     * @param nums
     * @param start
     * @param end
     */
    private void quickSort(String[] nums, int start, int end){
        if(start > end){
            return;
        }
        String mid = nums[start];
        int left = start;
        int right = end;
        while (left < right){
            while (isBiggerOrEqual(mid, nums[right]) && left < right){
                right--;
            }
            while (isBiggerOrEqual(nums[left], mid) && left < right){
                left++;
            }
            if(left < right){
                String tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
            }
        }
        //left right相等 需要把哨兵换掉
        nums[start] = nums[left];
        nums[left] = mid;
        quickSort(nums, start, right - 1);
        quickSort(nums, right + 1, end);
    }

    private boolean isBiggerOrEqual(String num1, String num2){
        String s1 = num1 + num2;
        String s2 = num2 + num1;
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        int len = chars1.length;
        for(int i = 0; i < len; i++){
            if(chars1[i] < chars2[i]){
                return false;
            }else if(chars1[i] > chars2[i]){
                return true;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode179 leetCode179 = new LeetCode179();
//        System.out.println(leetCode179.largestNumber(new int[]{2, 10}));
//        System.out.println(leetCode179.largestNumber(new int[]{3,30,34,5,9}));
        System.out.println(leetCode179.largestNumber(new int[]{121,12}));
    }
}
