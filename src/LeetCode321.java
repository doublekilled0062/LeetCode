import java.util.LinkedList;

/**
 * 321. 拼接最大数
 *
 * 给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。
 * 现在从这两个数组中选出 k (k <= m + n) 个数字拼接成一个新的数，
 * 要求从同一个数组中取出的数字保持其在原数组中的相对顺序。
 * 求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。
 *
 * 说明: 请尽可能地优化你算法的时间和空间复杂度。
 *
 * 示例 1:
 * 输入:
 * nums1 = [3, 4, 6, 5]
 * nums2 = [9, 1, 2, 5, 8, 3]
 * k = 5
 * 输出:
 * [9, 8, 6, 5, 3]
 *
 * 示例 2:
 * 输入:
 * nums1 = [6, 7]
 * nums2 = [6, 0, 4]
 * k = 5
 * 输出:
 * [6, 7, 6, 0, 4]
 *
 * 示例 3:
 * 输入:
 * nums1 = [3, 9]
 * nums2 = [8, 9]
 * k = 3
 * 输出:
 * [9, 8, 9]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/create-maximum-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode321 {
    /**
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        if(len1 > len2){
            //小的在前
            return maxNumber(nums2, nums1, k);
        }

        int[] res = new int[k];
        //从num1中取0个数，从num2中取k个数；从num1中取1个数，从num2中取k-1个数
        for(int i = Math.max(0, k - len2); i <= Math.min(k, len1); i++){
            int[] temp = merge(getMaxByLen(nums1, i), getMaxByLen(nums2, k - i));
            if(!isBig(res, 0, temp, 0)){
                res = temp;
            }
        }
        return res;
    }

    /**
     * 在数组里找count个顺序最大的数
     * 效率都体现这个方法上 从后往前填数
     * @param nums
     * @param count
     * @return
     */
    private int[] getMaxByLen(int[] nums, int count){
        //已经填的数的个数
        int size = 0;
        int len = nums.length;
        int[] res = new int[count];
        if(count == 0) {
            return res;
        }
        for(int i = 0; i < len; i++) {
            while(size > 0 && len - i > count - size && nums[i] > res[size - 1]) {
                size--;
            }
            if(size < count) {
                res[size++] = nums[i];
            }
        }
        return res;
    }

    private int[] merge(int[] nums1, int[] nums2){
        int[] res = new int[nums1.length + nums2.length];
        int i = 0;
        int j = 0;
        int index = 0;
        while (index < res.length && i < nums1.length && j < nums2.length){
            if(isBig(nums1, i, nums2, j)){
                res[index++] = nums1[i++];
            }else {
                res[index++] = nums2[j++];
            }
        }
        while (i < nums1.length){
            res[index++] = nums1[i++];
        }
        while (j < nums2.length){
            res[index++] = nums2[j++];
        }

        return res;
    }

    /**
     * 比较相同长度的数组同位置的数大小 如果相等需要比较第一个不同的
     * @param nums1
     * @param nums2
     * @return
     */
    private boolean isBig(int[] nums1, int i, int[] nums2, int j){
        while (i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
            i++;
            j++;
        }
        return j == nums2.length || (i < nums1.length && nums1[i] > nums2[j]);
    }


    public static void main(String[] args) {
        LeetCode321 leetCode321 = new LeetCode321();
//        leetCode321.maxNumber(new int[]{3, 4, 6, 5}, new int[]{9, 1, 2, 5, 8, 3}, 5);
//        leetCode321.maxNumber(new int[]{6, 7}, new int[]{6, 0, 4}, 5);
        leetCode321.maxNumber(new int[]{5, 6, 8}, new int[]{6, 4, 0}, 3);
    }
}
