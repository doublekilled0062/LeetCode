/**
 * 306. 累加数
 *
 * 累加数是一个字符串，组成它的数字可以形成累加序列。
 * 一个有效的累加序列必须至少包含 3 个数。除了最开始的两个数以外，字符串中的其他数都等于它之前两个数相加的和。
 * 给定一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是累加数。
 *
 * 说明: 累加序列里的数不会以 0 开头，所以不会出现 1, 2, 03 或者 1, 02, 3 的情况。
 *
 * 示例 1:
 * 输入: "112358"
 * 输出: true
 * 解释: 累加序列为: 1, 1, 2, 3, 5, 8 。1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 *
 * 示例 2:
 * 输入: "199100199"
 * 输出: true
 * 解释: 累加序列为: 1, 99, 100, 199。1 + 99 = 100, 99 + 100 = 199
 *
 * 进阶:
 * 你如何处理一个溢出的过大的整数输入?
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/additive-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode306 {
    private boolean res = false;

    /**
     * 标准dfs
     * @param num
     * @return
     */
    public boolean isAdditiveNumber(String num) {
        if(num == null || num.length() < 3){
            return false;
        }
        long first = 0;
        for(int i = 0; i < num.length() - 2; i++){
            first = first * 10 + (num.charAt(i) - '0');
            long second = 0;
            for(int j = i + 1; j < num.length() - 1; j++){
                second = second * 10 + (num.charAt(j) - '0');
                if(res){
                    return true;
                }
                //判断位数简单剪枝
                if(num.length() - j - 1 < j - i || num.length() - j - 1 < i + 1){
                    break;
                }
                dfs(num, j + 1, first, second, 0);
                if(second == 0){
                    break;
                }
            }
            if(first == 0){
                return res;
            }
        }
        return res;
    }

    private void dfs(String num, int index, long first, long second, long temp){
        if(index >= num.length()){
            return;
        }
        temp = temp * 10 + (num.charAt(index) - '0');
        if(first + second == temp){
            if(index == num.length() - 1){
                //到头递归成功
                res = true;
                return;
            }else {
                //往前继续找
                dfs(num, index+1, second, temp, 0);
            }
        }else if(first + second < temp){
            //超了直接返回
            return;
        }else {
            if(temp == 0){
                return;
            }
            dfs(num, index + 1, first, second, temp);
        }
    }

    public static void main(String[] args) {
        LeetCode306 leetCode306 = new LeetCode306();
//        System.out.println(leetCode306.isAdditiveNumber("112358"));
//        System.out.println(leetCode306.isAdditiveNumber("199100199"));
//        System.out.println(leetCode306.isAdditiveNumber("1991001991"));
//        System.out.println(leetCode306.isAdditiveNumber("101"));
        System.out.println(leetCode306.isAdditiveNumber("000"));
    }
}
