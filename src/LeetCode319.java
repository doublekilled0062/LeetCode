/**
 * 319. 灯泡开关
 *
 * 初始时有 n 个灯泡关闭。 第 1 轮，你打开所有的灯泡。
 * 第 2 轮，每两个灯泡你关闭一次。
 * 第 3 轮，每三个灯泡切换一次开关（如果关闭则开启，如果开启则关闭）。
 * 第 i 轮，每 i 个灯泡切换一次开关。
 * 对于第 n 轮，你只切换最后一个灯泡的开关。
 * 找出 n 轮后有多少个亮着的灯泡。
 *
 * 示例:
 * 输入: 3
 * 输出: 1
 *
 * 解释:
 * 初始时, 灯泡状态 [关闭, 关闭, 关闭].
 * 第一轮后, 灯泡状态 [开启, 开启, 开启].
 * 第二轮后, 灯泡状态 [开启, 关闭, 开启].
 * 第三轮后, 灯泡状态 [开启, 关闭, 关闭].
 *
 * 你应该返回 1，因为只有一个灯泡还亮着。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/bulb-switcher
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode319 {
    /**
     * 从2开始 除了1和本身 还有奇数个因子
     * 用这个跑了下数据 发现都是完全平方数
     * @param n
     * @return
     */
    public int bulbSwitch(int n) {
        if(n == 0){
            return 0;
        }
        int sum = 1;
        for(int i = 2; i <= n; i++){
            int count = 0;
            for(int j = 2; j < i; j++){
                if(i % j == 0){
                    count++;
                }
            }
            if(count % 2 == 1){
                System.out.println(i);
                sum++;
            }
        }
        return sum;
    }

    public int bulbSwitch1(int n) {
        int sum = 0;
        for(int i = 1; i * i <= n; i++){
            sum++;
        }
        return sum;
    }

    public static void main(String[] args) {
        LeetCode319 leetCode319 = new LeetCode319();
        System.out.println(leetCode319.bulbSwitch1(1225));
    }
}
