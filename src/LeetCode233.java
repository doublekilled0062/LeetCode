import java.util.HashMap;
import java.util.Map;

/**
 * 233. 数字 1 的个数
 *
 * 给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。
 *
 * 示例:
 * 输入: 13
 * 输出: 6
 * 解释: 数字 1 出现在以下数字中: 1, 10, 11, 12, 13 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-digit-one
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode233 {
    private Map<Integer, Integer> map = new HashMap<Integer, Integer>(){{
        put(9,1);
    }};

    public int countDigitOne(int n) {
        if(n < 1){
            return 0;
        }
        if(n < 10){
            return 1;
        }
        int sum = 1;
        int temp = 1;
        int base = 9;
        int i = 1;
        for(; i < n; i++){
            if(n/10 - 1 >= base){
                temp = temp * 10;
                sum = sum + temp + 9 * sum;
                base = base * 10 + 9;
                map.put(base, sum);
            }else {
                break;
            }
        }
        //1523 - 999; 2234 - 999
        int remain = n - base - 1;
        if(remain < base + 1){
            return sum + remain + 1 + countDigitOne(remain);
        }else {
            int rate = remain/(base + 1);
            int remain2 = remain%(base + 1);
            return sum +  base + 1 + rate * map.get(base) + countDigitOne(remain2);
        }
    }

    public int countDigitOne1(int n) {
        int countr = 0;
        for (long i = 1; i <= n; i *= 10){
            long divider = i * 10;
            countr += (n / divider) * i + Math.min(Math.max(n % divider - i + 1, 0L), i);
        }
        return countr;
    }

    public static void main(String[] args) {
        LeetCode233 leetCode233 = new LeetCode233();
        System.out.println(leetCode233.countDigitOne(999));
        System.out.println(leetCode233.countDigitOne(1410065408));
    }
}
