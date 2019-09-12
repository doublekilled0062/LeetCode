import java.util.*;

/**
 * 282. 给表达式添加运算符
 *
 * 给定一个仅包含数字 0-9 的字符串和一个目标值，在数字之间添加二元运算符（不是一元）+、- 或 * ，
 * 返回所有能够得到目标值的表达式。
 *
 * 示例 1:
 * 输入: num = "123", target = 6
 * 输出: ["1+2+3", "1*2*3"]
 *
 * 示例 2:
 * 输入: num = "232", target = 8
 * 输出: ["2*3+2", "2+3*2"]
 *
 * 示例 3:
 * 输入: num = "105", target = 5
 * 输出: ["1*0+5","10-5"]
 *
 * 示例 4:
 * 输入: num = "00", target = 0
 * 输出: ["0+0", "0-0", "0*0"]
 *
 * 示例 5:
 * 输入: num = "3456237490", target = 9191
 * 输出: []
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/expression-add-operators
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode282 {
    /**
     * 回溯
     * @param num
     * @param target
     * @return
     */
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        if(num == null || num.length() == 0){
            return res;
        }
        //得到的式子最长长度是num.length() * 2 - 1，相邻两个式子之间最多添加一个符号
        //沿途的数字和+-*的决策拷贝到path中
        char[] path = new char[num.length() * 2 - 1];
        dfs(num.toCharArray(), 0, target, res, 0, 0, path, 0);
        return res;
    }

    /**
     *
     * @param nums      每次要处理的字符串
     * @param index     遍历到的索引
     * @param target    目标值
     * @param res       结果值
     * @param curRes    已经计算好的字符串的值
     * @param preNum    已经计算好的字符串的 上一个部分的值 乘法的时候要更新成一个preNum * 当前要计算的值
     * @param path      效率高的都是用数组代替临时计算好的字符串
     * @param pathLen   path的长度
     */
    private void dfs(char[] nums, int index, int target, List<String> res,
                    long curRes, long preNum, char[] path, int pathLen){
        if(curRes == target && index == nums.length){
            res.add(new String(path, 0, pathLen));
        }

        long curNum = 0;
        int tempPathLen = pathLen + 1;

        //遍历回溯
        for(int i = index; i < nums.length; i++){
            // 当前需要计算的数
            curNum = curNum * 10 + nums[i] - '0';
            if(index == 0){
                //先放数字
                path[pathLen++] = nums[i];
                //第一个数处理 不加运算符
                dfs(nums, i + 1, target, res, curNum, curNum, path, pathLen);
            }else {

                path[tempPathLen++] = nums[i];
                //乘法
                dfs(nums, i + 1, target, res, curRes - preNum + preNum * curNum, preNum * curNum, path, tempPathLen);
                //加法
                path[pathLen] = '+';
                dfs(nums, i + 1, target, res, curRes + curNum, curNum, path, tempPathLen);
                //减法
                path[pathLen] = '-';
                dfs(nums, i + 1, target, res, curRes - curNum, -curNum, path, tempPathLen);
                path[pathLen] = '*';
            }
            //当前字符为0 往后就不用算了
            if(curNum == 0){
                break;
            }
        }
    }


    /**
     * 20ms的题解 思路其实差不多 只不过开头的遍历挪到外面了
     * @param num
     * @param target
     * @return
     */
    public List<String> addOperators1(String num, int target) {
        List<String> ret = new LinkedList<>();   //返回式子
        if (num.length() == 0) {
            return ret;
        }

        //得到的式子最长长度是num.length() * 2 - 1，相邻两个式子之间最多添加一个符号
        //沿途的数字和+-*的决策拷贝到path中
        char[] path = new char[num.length() * 2 - 1];
        char[] digits = num.toCharArray();

        //n是前缀，用long类型，比较安全
        long n = 0;
        for (int i = 0; i < digits.length; i++) { //尝试0~i前缀作为我的第一部分
            n = n * 10 + digits[i] - '0';  //求0~i这一部分的转出来数值，技巧很重要
            path[i] = digits[i];   //path添加


            dfs(ret, path, i + 1, 0, n, digits, i + 1, target);
            //0~i前缀共i+1个字符，默认left是0，假定整个串是0+式子

            if (n == 0) {
                break;
            }
        }
        return ret;
    }

    public static void dfs(List<String> ret, char[] path, int len, long left,
                           long cur, char[] digits, int pos, int target) {

        //basecase:在递归底层直接计算式子结果
        if (pos == digits.length) {
            if (left + cur == target) {
                ret.add(new String(path, 0, len));
            }
            return;
        }
        long n = 0;
        int j = len + 1;
        for (int i = pos; i < digits.length; i++) {
            n = n * 10 + digits[i] - '0';
            path[j++] = digits[i];
            path[len] = '+';
            dfs(ret, path, j, left + cur, n, digits, i + 1, target);
            path[len] = '-';
            dfs(ret, path, j, left + cur, -n, digits, i + 1, target);
            path[len] = '*';
            dfs(ret, path, j, left, cur * n, digits, i + 1, target);
            if (digits[pos] == '0') {
                break;
            }
        }
    }
    public static void main(String[] args) {
        LeetCode282 leetCode282 = new LeetCode282();
        leetCode282.addOperators("123", 6);
    }

}
