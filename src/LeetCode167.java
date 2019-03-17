/**
 * 167. 两数之和 II - 输入有序数组
 *
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 * 说明:
 * 返回的下标值（index1 和 index2）不是从零开始的。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 *
 * 示例:
 * 输入: numbers = [2, 7, 11, 15], target = 9
 * 输出: [1,2]
 * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 */
public class LeetCode167 {
    /**
     * 双指针
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        //题目不判断输入不合法的边界条件了
        int start = 0;
        int end = numbers.length - 1;
        while (start < end){
            if(numbers[start] + numbers[end] > target){
                end--;
                continue;
            }else if(numbers[start] + numbers[end] < target){
                start++;
            }else {
                return new int[]{start + 1, end + 1};
            }
        }
        return new int[]{};
    }

    /**
     * 看了0ms的 是在每次大于和小于的时候用二分查找一个最接近的数
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum2(int[] numbers, int target) {
        //题目不判断输入不合法的边界条件了
        int start = 0;
        int end = numbers.length - 1;
        while (start < end){
            if(numbers[start] + numbers[end] > target){
                end = getEndPosTarget(numbers, start, end, target - numbers[start], false);
            }else if(numbers[start] + numbers[end] < target){
                start = getEndPosTarget(numbers, start, end, target - numbers[end], true);
            }else {
                return new int[]{start + 1, end + 1};
            }
        }
        return new int[]{};
    }

    /**
     * 下面两个方法我优化了一下 遇到target直接返回了
     * 是返回大的值还是小的值
     * @param numbers
     * @param start
     * @param end
     * @param target
     * @return
     */
    private int getEndPosTarget(int[] numbers, int start, int end, int target, boolean needLarge){
        while (start <= end){
            int mid = start + ((end - start) >> 1);
            if(numbers[mid] > target){
                end = mid - 1;
            }else if(numbers[mid] < target){
                start = mid + 1;
            }else {
                return mid;
            }
        }
        return needLarge ? start : end;
    }

    public static void main(String[] args) {
        LeetCode167 leetCode167 = new LeetCode167();
        System.out.println(leetCode167.twoSum2(new int[]{3,24,50,79,88,150,345}, 200));
    }
}
