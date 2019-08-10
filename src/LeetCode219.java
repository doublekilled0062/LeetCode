import java.util.HashMap;
import java.util.Map;

/**
 * 219. 存在重复元素 II
 *
 * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的绝对值最大为 k。
 *
 * 示例 1:
 * 输入: nums = [1,2,3,1], k = 3
 * 输出: true
 *
 * 示例 2:
 * 输入: nums = [1,0,1,1], k = 1
 * 输出: true
 *
 * 示例 3:
 * 输入: nums = [1,2,3,1,2,3], k = 2
 * 输出: false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/contains-duplicate-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode219 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(nums[i]) && i - map.get(nums[i]) <= k){
                return true;
            }else {
                map.put(nums[i], i);
            }
        }
        return false;
    }

    /**
     * 网友题解的2ms的 但是这个遇到k值很大 然后没有合法数的时候 比如长倒序数组的时候复杂度就是O(len*k) 效率会很低
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate1(int[] nums, int k) {
        int max = 0;
        for (int i = 1; i < nums.length; i++) {
            // 找到递增终点
            if (nums[i] > nums[max]) {
                max = i;
            } else {
                for (int j = i - 1; j >= i - k && j >= 0; j--) {
                    if (nums[j] == nums[i]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        LeetCode219 leetCode219 = new LeetCode219();
        System.out.println(leetCode219.containsNearbyDuplicate1(new int[]{0,1,2,3,4,5,1,2,4,6,2,1,}, 3));
    }
}
