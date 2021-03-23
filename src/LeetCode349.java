import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 349. 两个数组的交集
 *
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 * 示例 1:
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2]
 *
 * 示例 2:
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [9,4]
 *
 * 说明:
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode349 {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums1) {
            map.put(i, 1);
        }
        Set<Integer> list = new HashSet<>();
        for (int i : nums2) {
            if (map.containsKey(i)) {
                list.add(i);
            }
        }
        int[] res = new int[list.size()];
        int index = 0;
        for (Integer i : list) {
            res[index++] = i;
        }
        return res;
    }


    public static void main(String[] args) {

    }
}


