/**
 * 307. 区域和检索 - 数组可修改
 *
 * 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。
 * update(i, val) 函数可以通过将下标为 i 的数值更新为 val，从而对数列进行修改。
 *
 * 示例:
 * Given nums = [1, 3, 5]
 * sumRange(0, 2) -> 9
 * update(1, 2)
 * sumRange(0, 2) -> 8
 *
 * 说明:
 * 数组仅可以在 update 函数下进行修改。
 * 你可以假设 update 函数与 sumRange 函数的调用次数是均匀分布的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/range-sum-query-mutable
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode307 {
    /**
     * 这样其实还没有直接算快
     */
    class NumArray {
        private int[] dp;
        private int[] nums;
        private int[] update;
        public NumArray(int[] nums) {
            this.nums = nums;
            this.update = new int[nums.length];
            dp = new int[nums.length + 1];
            for(int i = 0; i < nums.length; i++){
                dp[i+1] = nums[i] + dp[i];
            }
        }

        public void update(int i, int val) {
            update[i] = val - nums[i];
        }

        public int sumRange(int i, int j) {
            int sum = 0;
            for(int k = i; k <= j; k++){
                sum += update[k];
            }
            return dp[j+1] - dp[i] + sum;
        }
    }

    /**
     * sqrt分解 分成sqrt大小的段 这样更新的时候只需要更新一部分
     * 直接用题解
     */
    class NumArray1 {
        private int[] sqrt;
        private int len;
        private int[] nums;

        public NumArray1(int[] nums) {
            this.nums = nums;
            double l = Math.sqrt(nums.length);
            len = (int) Math.ceil(nums.length/l);
            sqrt = new int [len];
            for (int i = 0; i < nums.length; i++)
                sqrt[i / len] += nums[i];
        }

        public int sumRange(int i, int j) {
            int sum = 0;
            int startBlock = i / len;
            int endBlock = j / len;
            if (startBlock == endBlock) {
                for (int k = i; k <= j; k++){
                    sum += nums[k];
                }
            } else {
                for (int k = i; k <= (startBlock + 1) * len - 1; k++){
                    sum += nums[k];
                }
                for (int k = startBlock + 1; k <= endBlock - 1; k++){
                    sum += sqrt[k];
                }
                for (int k = endBlock * len; k <= j; k++){
                    sum += nums[k];
                }
            }
            return sum;
        }

        public void update(int i, int val) {
            int blockIndex = i / len;
            sqrt[blockIndex] += val - nums[i];
            nums[i] = val;
        }
    }

    /**
     * 线段树解法
     */
    class NumArray2 {
        int[] tree;
        int n;
        public NumArray2(int[] nums) {
            if (nums.length > 0) {
                n = nums.length;
                tree = new int[n * 2];
                buildTree(nums);
            }
        }

        /**
         * 和二叉树一样 后半部分存把数组当叶子节点存
         * @param nums
         */
        private void buildTree(int[] nums) {
            for (int i = n, j = 0;  i < 2 * n; i++,  j++){
                tree[i] = nums[j];
            }

            for (int i = n - 1; i > 0; --i){
                tree[i] = tree[i * 2] + tree[i * 2 + 1];
            }
        }

        public int sumRange(int i, int j) {
            int left = i + n;
            int right = j + n;
            int sum = 0;
            /**
             * 求和按照2k、2k+1这样的索引特点一对一对的求值
             */
            while (left <= right) {
                //剔除 i是2k+1的点
                if ((left % 2) == 1) {
                    sum += tree[left];
                    left++;
                }
                //剔除 j是2k的点
                if ((right % 2) == 0) {
                    sum += tree[right];
                    right--;
                }
                //递归往上找，上面是直接计算好的和
                left /= 2;
                right /= 2;
            }
            return sum;
        }

        /**
         * 自底向上更新 按 2k、2k+1这样的一对更新
         * @param i
         * @param val
         */
        public void update(int i, int val) {
            //找到后半节点
            i += n;
            tree[i] = val;
            while (i > 0) {
                int left = i;
                int right = i;
                if (i % 2 == 0) {
                    //更新的索引是左节点
                    right = i + 1;
                } else {
                    //更新的索引是右节点
                    left = i - 1;
                }
                i /= 2;
                tree[i] = tree[left] + tree[right];
            }
        }
    }

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */
}
