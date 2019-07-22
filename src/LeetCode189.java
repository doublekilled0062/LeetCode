/**
 * 189. 旋转数组
 *
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 *
 * 示例 1:
 *
 * 输入: [1,2,3,4,5,6,7] 和 k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 * 示例 2:
 *
 * 输入: [-1,-100,3,99] 和 k = 2
 * 输出: [3,99,-1,-100]
 * 解释:
 * 向右旋转 1 步: [99,-1,-100,3]
 * 向右旋转 2 步: [3,99,-1,-100]
 * 说明:
 *
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 要求使用空间复杂度为 O(1) 的 原地 算法。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode189 {
    /**
     * 循环移位k次 和用临时数组的暂不考虑
     * 二次反转法
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        if(nums == null || nums.length == 0){
            return;
        }

        k = k % nums.length;
        if(k  == 0){
            return;
        }
        int i = 0;
        int j = nums.length - 1;
        while (i < j){
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
        i = 0;
        j = k - 1;
        while (i < j){
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
        i = k;
        j = nums.length - 1;
        while (i < j){
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }

    /**
     * 循环移位 1 2 3 4 5 6 7
     * k = 3
     * 1换到4 4换到7 7换到1 2 换到5 3换到6
     * @param nums
     * @param k
     */
    public void rotate1(int[] nums, int k) {
        if(nums == null || nums.length == 0){
            return;
        }

        k = k % nums.length;
        if(k  == 0){
            return;
        }

        //count为总的移位次数
        int count = 0;
        //起始坐标 如果n%k == 0 的话循环移位会提前到start结束 移位不到次数
        int start = 0;
        while (count < nums.length){
            int cur = (start + k) % nums.length;
            int swap = nums[start];
            while (cur != start){
                int temp = nums[cur];
                nums[cur] = swap;
                swap = temp;
                count++;
                cur = (cur + k) % nums.length;
            }
            nums[cur] = swap;
            count++;
            start++;
        }
    }

    public void rotate2(int[] nums, int k) {
        if(nums == null || nums.length == 0){
            return;
        }

        k = k % nums.length;
        if(k  == 0){
            return;
        }
        rotate(nums, 0, nums.length - k - 1, nums.length - k, nums.length - 1);
    }

    /**
     * 分段递归交换
     * @param nums
     * @param start1
     * @param end1
     * @param start2
     * @param end2
     */
    public void rotate(int[] nums, int start1, int end1, int start2, int end2) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        int minLen = Math.min(len1, len2);
        for(int i = 0; i < minLen; i++){
            int temp = nums[start1 + i];
            nums[start1 + i] = nums[end2 - minLen + i];
            nums[end2 - minLen + i] = temp;
        }
        if(len1 == len2){
            //等长直接返回
            return;
        }else if(len1 > len2){
            //前面的是交换好的
            rotate(nums, start1 + len2, end1, start2, end2);
        }else {
            //后面是交换好的
            rotate(nums, start1, end1, start2, end2 - len1);
        }
    }

    public static void main(String[] args) {
        LeetCode189 leetCode189 = new LeetCode189();
        leetCode189.rotate1(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 3);
    }
}
