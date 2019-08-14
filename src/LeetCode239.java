import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 239. 滑动窗口最大值
 *
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 *
 * 示例:
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *  
 *
 * 提示：
 * 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
 *
 * 进阶：
 * 你能在线性时间复杂度内解决此题吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sliding-window-maximum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode239 {
    /**
     * 传统做法
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0){
            return new int[]{};
        }
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        int[] res = new int[nums.length - k + 1];
        for(int i = 0; i < nums.length; i++){
            if(i < k - 1){
                queue.offer(nums[i]);
            }else if(i == k - 1){
                queue.offer(nums[i]);
                res[i - k + 1] = queue.peek();
            }else {
                queue.remove(nums[i - k]);
                queue.offer(nums[i]);
                res[i- k + 1] = queue.peek();
            }
        }
        return res;
    }

    /**
     * 把上面改进一下 做成单调双端队列
     * 把队列头永远是最大 不在索引范围内就删除
     * 从尾部插入时候把小于当前数的全删掉再插入
     * 队列里存的是索引
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow1(int[] nums, int k) {
        if(nums == null || nums.length == 0){
            return new int[]{};
        }
        if(k == 1){
            return nums;
        }
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        int[] res = new int[nums.length - k + 1];
        for(int i = 0; i < nums.length; i++){
            if(queue.isEmpty()){
                queue.addLast(i);
            }else {
                if(i - queue.getFirst() + 1 > k){
                    queue.removeFirst();
                }
                while (!queue.isEmpty() && nums[queue.getLast()] < nums[i]){
                    queue.removeLast();
                }
                queue.addLast(i);
            }

            if(i >= k - 1){
                res[i - k + 1] = nums[queue.getFirst()];
            }
        }
        return res;
    }

    /**
     * 两步动态规划
     * 按k个一起分组
     * left表示 这个组开始到当前索引最大
     * right表示 这个组的最后一个到当前索引最大
     * k个一组 要么在一个块内 要么跨两个块
     * 在一个块内left[j]和right[i]是相等的
     * 在两个块的时候 left[j]表示块分界点到j的最大值 right[i]表示分界点到i的最大值 然后求两者最大
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        if(nums == null || nums.length == 0){
            return new int[]{};
        }
        if(k == 1){
            return nums;
        }

        int len = nums.length;

        int[] left = new int[len];
        int[] right = new int[len];

        left[0] = nums[0];
        right[len - 1] = nums[len - 1];
        for (int i = 1; i < len; i++) {
            if (i % k == 0) {
                left[i] = nums[i];
            } else {
                left[i] = Math.max(left[i - 1], nums[i]);
            }

            int j = len - i - 1;
            if ((j + 1) % k == 0) {
                right[j] = nums[j];
            } else {
                right[j] = Math.max(right[j + 1], nums[j]);
            }
        }

        int[] res = new int[len - k + 1];
        for (int i = 0; i < len - k + 1; i++){
            res[i] = Math.max(left[i + k - 1], right[i]);
        }
        return res;
    }

    /**
     * 然而最快的是暴力算法 暴利找 如果当前maxid大于遍历索引 就比较和末尾的值
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow3(int[] nums, int k) {
        if (nums.length == 0) return new int[0];
        int maxIdx = maxIdx(nums,0,k);
        int[] result = new int[nums.length - k + 1];
        result[0] = nums[maxIdx];
        for (int i = 1; i + k <= nums.length; i++) {
            if (maxIdx < i) {
                maxIdx = maxIdx(nums,i,i+k);
            }else if (nums[i+k-1] > nums[maxIdx]) {
                maxIdx = i+k-1;
            }
            result[i] = nums[maxIdx];
        }

        return result;

    }

    private int maxIdx(int[] nums,int left, int right) {
        int maxIdx = left;
        for (int i = left + 1; i < right; i++) {
            if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    public static void main(String[] args) {
        LeetCode239 leetCode239 = new LeetCode239();
        leetCode239.maxSlidingWindow1(new int[]{7, 2, 4}, 2);
    }
}
