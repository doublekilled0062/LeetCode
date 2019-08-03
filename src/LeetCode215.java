import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 215. 数组中的第K个最大元素
 *
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 *
 * 示例 2:
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 *
 * 说明:
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode215 {
    /**
     * 优先级队列 12ms 超过57%
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        for (int i = 0; i < nums.length; i++) {
            if(queue.size() != k){
                queue.offer(nums[i]);
            }else {
                if(queue.peek() >= nums[i]){
                    continue;
                }else {
                    queue.poll();
                    queue.offer(nums[i]);
                }
            }
        }
        return queue.peek();
    }

    /**
     * 类似快排思想
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest1(int[] nums, int k) {
        return quickSort(nums, 0, nums.length - 1, nums.length - k);
    }

    private int quickSort(int[] nums, int start, int end, int index) {
        int left = start;
        int right = end;
        int pivot = nums[(start + end) / 2];
        while (start <= end) {
            while (start <= end && nums[start] < pivot) start++;
            while (start <= end && nums[end] > pivot) end--;
            if (start <= end) {
                swap(nums, start, end);
                start++;
                end--;
            }
        }
        if (index <= end) return quickSort(nums, left, end, index);
        if (index >= start) return quickSort(nums, start, right, index);
        return nums[index];
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 标准快排是不行的 不知道为什么
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest2(int[] nums, int k) {
        return quickSort1(nums, 0, nums.length - 1, nums.length - k);
    }

    private int quickSort1(int[] nums, int start, int end, int index) {
        int value = nums[end];
        int i = start - 1;
        for(int j = start; j <= end - 1; j++){
            if(nums[j] <= value){
                i = i + 1;
                swap(nums, i, j);
            }
        }
        swap(nums, i + 1, end);
        if(i + 1 == index){
            return nums[i + 1];
        }else if(i + 1 > index){
            return quickSort1(nums, start, i, index);
        }else {
            return quickSort1(nums, i + 2, end, index);
        }
    }

    public static void main(String[] args) {
        LeetCode215 leetCode215 = new LeetCode215();
        System.out.println(leetCode215.findKthLargest2(new int[]{3,2,3,1,2,4,5,5,6}, 4));
    }
}
