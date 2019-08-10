import java.util.HashSet;

/**
 * 217. 存在重复元素
 *
 * 给定一个整数数组，判断是否存在重复元素。
 * 如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。
 *
 * 示例 1:
 * 输入: [1,2,3,1]
 * 输出: true
 *
 * 示例 2:
 * 输入: [1,2,3,4]
 * 输出: false
 *
 * 示例 3:
 * 输入: [1,1,1,3,3,4,3,2,4,2]
 * 输出: true
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/contains-duplicate
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode217 {
    public boolean containsDuplicate(int[] nums) {
//        if(nums == null || nums.length < 2){
//            return false;
//        }
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++){
            if(!set.add(nums[i])){
                return true;
            }

        }
        return false;
    }

    /**
     * 提交记录里3秒的答案是错的 例如测试用例 [5,4,3,2,1,2,3,4,5]就通不过
     * 下面这个就是
     * @param nums
     * @return
     */
    @Deprecated
    public boolean containsDuplicate1(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) break;
                if (nums[i] == nums[j]) return true;
            }
        }

        return false;
    }

    /**
     * 还有下面提交记录里0ms的这个位图只能判断小于1024的数
     * 然后判断系统的测试用例 大于1024的都直接返回了
     * [1024, 2048, 0, 1] 这个就通不过
     * @param nums
     * @return
     */
    @Deprecated
    public boolean containsDuplicate2(int[] nums) {
        if (nums.length < 1 || nums[0] == 237384 || nums[0] == -24500)
            return false;
        boolean[] bc = new boolean[1024];
        for (int num : nums) {
            if (bc[num & 1023])
                return true;
            bc[num & 1023] = true;
        }
        return false;
    }

    public static void main(String[] args) {
        LeetCode217 leetCode217 = new LeetCode217();
        System.out.println(leetCode217.containsDuplicate1(new int[]{5,4,3,2,1,2,3,4,5}));
        System.out.println(leetCode217.containsDuplicate2(new int[]{1024, 2048, 0, 1}));
    }
}
