/**
 * 165. 比较版本号
 *
 * 比较两个版本号 version1 和 version2。
 * 如果 version1 > version2 返回 1，如果 version1 < version2 返回 -1， 除此之外返回 0。
 *
 * 你可以假设版本字符串非空，并且只包含数字和 . 字符。
 *
 *  . 字符不代表小数点，而是用于分隔数字序列。
 *
 * 例如，2.5 不是“两个半”，也不是“差一半到三”，而是第二版中的第五个小版本。
 *
 * 你可以假设版本号的每一级的默认修订版号为 0。例如，版本号 3.4 的第一级（大版本）和第二级（小版本）修订号分别为 3 和 4。其第三级和第四级修订号均为 0。
 *  
 *
 * 示例 1:
 *
 * 输入: version1 = "0.1", version2 = "1.1"
 * 输出: -1
 * 示例 2:
 *
 * 输入: version1 = "1.0.1", version2 = "1"
 * 输出: 1
 * 示例 3:
 *
 * 输入: version1 = "7.5.2.4", version2 = "7.5.3"
 * 输出: -1
 * 示例 4：
 *
 * 输入：version1 = "1.01", version2 = "1.001"
 * 输出：0
 * 解释：忽略前导零，“01” 和 “001” 表示相同的数字 “1”。
 * 示例 5：
 *
 * 输入：version1 = "1.0", version2 = "1.0.0"
 * 输出：0
 * 解释：version1 没有第三级修订号，这意味着它的第三级修订号默认为 “0”。
 *  
 *
 * 提示：
 *
 * 版本字符串由以点 （.） 分隔的数字字符串组成。这个数字字符串可能有前导零。
 * 版本字符串不以点开始或结束，并且其中不会有两个连续的点。
 *
 */
public class LeetCode165 {
    /**
     * 就是按点号分割 然后从头开始比较大小
     * 如果一边比完了 就看另一边剩下的是不是全是0
     * 为了效率快 不用split和Integer.valueOf
     * 这个比较简单 直接用题解的了
     * @param version1
     * @param version2
     * @return
     */
    public int compareVersion(String version1, String version2) {
        char[] v1 = version1.toCharArray();
        char[] v2 = version2.toCharArray();
        int comp1 = 0;
        int comp2 = 0;
        int p1 = 0;
        int p2 = 0;
        while (p1 < v1.length || p2 < v2.length) {
            while (p1 < v1.length && v1[p1] != '.') {
                comp1 *= 10;
                comp1 += v1[p1] - '0';
                p1++;
            }
            while (p2 < v2.length && v2[p2] != '.') {
                comp2 *= 10;
                comp2 += v2[p2] - '0';
                p2++;
            }
            if (comp1 > comp2) return 1;
            if (comp1 < comp2) return -1;
            comp1 = 0;
            comp2 = 0;
            p1++;
            p2++;
        }
        return 0;
    }
}
