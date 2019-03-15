/**
 * 4. 寻找两个有序数组的中位数
 *
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 示例 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * 则中位数是 2.0
 *
 * 示例 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * 则中位数是 (2 + 3)/2 = 2.5
 */
public class LeetCode4 {

    /**
     * 要用类似二分的思想
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if(m > n){
            //交换
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
            m = nums1.length;
            n = nums2.length;
        }
        int start = 0;
        int end = nums1.length;
        int halfLen = (m + n + 1)/2;
        while (start <= end){
            int i = (start + end)/2;
            int j = halfLen - i;
            if(i < end && nums2[j - 1] > nums1[i]){
                //说明i小 需要往右偏移
                start = i + 1;
            }else if(i > start && nums1[i - 1] > nums2[j]){
                //说明i大了 需要往左移动
                end = i - 1;
            }else {
                int maxLeft = 0;
                if(i == 0){
                    //左边为空集
                    maxLeft = nums2[j - 1];
                }else if (j == 0){
                    maxLeft = nums1[i - 1];
                }else {
                    maxLeft = Math.max(nums2[j - 1], nums1[i - 1]);
                }
                if((m + n) % 2 == 1){
                    return maxLeft;
                }
                int minRight = 0;
                if(i == m){
                    minRight = nums2[j];
                }else if(j == n){
                    minRight = nums1[i];
                }else {
                    minRight = Math.min(nums1[i], nums2[j]);
                }
                return (maxLeft + minRight)/2.0d;
            }
        }
        return 0.0;
    }

    /**
     * 遍历的方法，复杂度不符合要求，但是系统上过了
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        if(nums1 == null || nums1.length == 0){
            if(nums2.length % 2 == 0){
                return (nums2[nums2.length/2 - 1] + nums2[nums2.length/2])/2.0d;
            }else {
                return nums2[nums2.length/2] + 0d;
            }
        }
        if(nums2 == null || nums2.length == 0){
            if(nums1.length % 2 == 0){
                return (nums1[nums1.length/2 - 1] + nums1[nums1.length/2])/2.0d;
            }else {
                return nums1[nums1.length/2] + 0d;
            }
        }

        int i = 0;
        int j = 0;
        int k = 0;
        int value1 = 0;
        int value2 = 0;
        while (k <= (nums1.length + nums2.length)/2){
            if(i >= nums1.length || (j < nums2.length && nums1[i] > nums2[j])){
                value1 = value2;
                value2 = nums2[j];
                k++;
                j++;
                continue;
            }
            if(j >= nums2.length || (i < nums1.length && nums1[i] <= nums2[j])){
                value1 = value2;
                value2 = nums1[i];
                k++;
                i++;
                continue;
            }
        }
        if((nums1.length + nums2.length) % 2 == 0){
            return (value1 + value2)/2.0d;
        }else {
            return value2;
        }
    }
}
