import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 315. 计算右侧小于当前元素的个数
 *
 * 给定一个整数数组 nums，按要求返回一个新数组 counts。
 * 数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
 *
 * 示例:
 * 输入: [5,2,6,1]
 * 输出: [2,1,1,0]
 *
 * 解释:
 * 5 的右侧有 2 个更小的元素 (2 和 1).
 * 2 的右侧仅有 1 个更小的元素 (1).
 * 6 的右侧有 1 个更小的元素 (1).
 * 1 的右侧有 0 个更小的元素.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode315 {
    /**
     * O(n^2)的算法超时了
     * @param nums
     * @return
     */
    public List<Integer> countSmaller(int[] nums) {
        if(nums.length <= 0){
            return new ArrayList<>();
        }

        Integer[] array = new Integer[nums.length];
        Arrays.fill(array, 0);
        for(int i = nums.length - 2; i >= 0; i--){
            for(int j = i + 1; j < nums.length; j++){
                if(nums[i] < nums[j]){
                    continue;
                }else if(nums[i] == nums[j]){
                    array[i] += array[j];
                    break;
                }else {
                    array[i]++;
                }
            }
        }
        return Arrays.asList(array);
    }

    public class TreeNode{
        private int leftCount; //左节点的数量
        private int value;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int val, int leftCount) {
            this.value = val;
            this.leftCount = leftCount;
            this.left = null;
            this.right = null;
        }

        public int insert(int value){
            TreeNode root = this;
            int count = 0;
            while (root != null){
                if (root.value >= value) {
                    root.leftCount += 1;
                    if (root.left == null) {
                        root.left = new TreeNode(value, 0);
                        return count;
                    }else {
                        root = root.left;
                    }
                } else if (root.value < value) {
                    //root.leftCount + 1 为节点总数
                    count = count + root.leftCount + 1;
                    if (root.right == null) {
                        root.right = new TreeNode(value, 0);
                        return count;
                    }else {
                        root = root.right;
                    }
                }
            }
            return count;
        }
    }

    /**
     * 二叉搜索树法
     * @param nums
     * @return
     */
    public List<Integer> countSmaller1(int[] nums) {
        LinkedList<Integer> res = new LinkedList<>();
        if (nums.length <= 0) {
            return res;
        }

        TreeNode root = new TreeNode(nums[nums.length - 1], 0);
        res.addFirst(0);
        for (int i = nums.length - 2; i >= 0; i--) {
            int count = root.insert(nums[i]);
            res.addFirst(count);
        }

        return res;
    }


    private int[] help;  //中间排序结果结果暂存数组
    private int[] indexs;//索引
    private int[] ans;   //结果

    /**
     * 题解有一个归并排序的做法 类似求逆序对
     * 排序用索引排序 保持原来数组数值不变
     * 在归并的时候，左边元素出列的时候计数
     * @param nums
     * @return
     */
    public List<Integer> countSmaller2(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        if (n == 0) {
            return res;
        }

        help = new int[n];
        indexs = new int[n];
        ans = new int[n];

        for (int i = 0; i < n; i++) {
            indexs[i] = i;//给索引数组赋值
        }
        mergeSort(nums, 0, n - 1);
        for (int i = 0; i < n; i++) {
            res.add(ans[i]);
        }
        return res;
    }

    public void mergeSort(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }

        int mid = l + ((r - l) >> 1);

        mergeSort(nums, l, mid);
        mergeSort(nums, mid + 1, r);

        if (nums[indexs[mid]] > nums[indexs[mid + 1]]) {
            merge(nums, l, mid, r);
        }
    }

    public void merge(int[] nums, int l, int mid, int r) {

        int len = r - l + 1;
        int p1 = l;
        int p2 = mid + 1;

        int i = 0;
        while (p1 <= mid && p2 <= r) {
            if (nums[indexs[p1]] <= nums[indexs[p2]]) {//这种情况前面的数出列
                ans[indexs[p1]] += p2 - mid - 1;
            }
            help[i++] = nums[indexs[p1]] <= nums[indexs[p2]] ? indexs[p1++] : indexs[p2++];
        }

        while (p1 <= mid) {//p2已越界
            ans[indexs[p1]] += r - mid;
            help[i++] = indexs[p1++];

        }

        while (p2 <= r) {//p1越界不用计算
            help[i++] = indexs[p2++];
        }

        for (int k = 0; k < len; k++) {
            indexs[k + l] = help[k];
        }

    }

    public static void main(String[] args) {
        LeetCode315 leetCode315 = new LeetCode315();
        leetCode315.countSmaller2(new int[]{5,2,6,1});
    }
}


