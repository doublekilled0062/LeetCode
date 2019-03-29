import java.util.HashMap;
import java.util.Map;

/**
 * 13. 罗马数字转整数
 *
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 *
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。
 * 12 写做 XII ，即为 X + II 。
 * 27 写做  XXVII, 即为 XX + V + II 。
 * 通常情况下，罗马数字中小的数字在大的数字的右边。
 * 但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。
 * 同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 *
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 *
 * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
 *
 * 示例 1:
 * 输入: "III"
 * 输出: 3
 *
 * 示例 2:
 * 输入: "IV"
 * 输出: 4
 *
 * 示例 3:
 * 输入: "IX"
 * 输出: 9
 *
 * 示例 4:
 * 输入: "LVIII"
 * 输出: 58
 * 解释: L = 50, V= 5, III = 3.
 *
 * 示例 5:
 * 输入: "MCMXCIV"
 * 输出: 1994
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 */
public class LeetCode13 {
    Map<Character, Integer> map = new HashMap<Character, Integer>(){{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};

    /**
     * 这个题就不追求时间了
     * 优化效率可以从这些方面入手 不用map用硬编码 代码里也多用硬编码
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        if(s == null){
            return 0;
        }
        int len = s.length();
        char[] chars = s.toCharArray();
        int i = 0;
        int result = 0;
        while (i < len){
            if(chars[i] == 'I' || chars[i] == 'X' || chars[i] == 'C'){
                if(i == len - 1){
                    return result += map.get(chars[i]);
                }else {
                    if(map.get(chars[i + 1]) > map.get(chars[i])){
                        result += map.get(chars[i + 1]) - map.get(chars[i]);
                        i = i + 2;
                    }else {
                        result += map.get(chars[i]);
                        i = i + 1;
                    }
                }
            }else {
                result += map.get(chars[i]);
                i = i + 1;
            }
        }
        return result;
    }

    /**
     * 所以这样快的毫无意义 19ms 超过100%
     * @param s
     * @return
     */
    public int romanToInt2(String s) {
        if(s == null){
            return 0;
        }
        int len = s.length();
        char lastc = ' ';
        char[] chars = s.toCharArray();
        int result = 0;
        for(int i = len - 1; i >=0; i--){
            int num = 0;
            switch (chars[i]){
                case 'I':
                    if(lastc != ' ' && (lastc == 'V' || lastc == 'X')){
                        num = -1;
                    }else {
                        num = 1;
                    }
                    break;
                case 'V':
                    num = 5;
                    break;
                case 'X':
                    if(lastc != ' ' && (lastc == 'L' || lastc == 'C')){
                        num = -10;
                    }else {
                        num = 10;
                    }
                    break;
                case 'L':
                    num = 50;
                    break;
                case 'C':
                    if(lastc != ' ' && (lastc == 'D' || lastc == 'M')){
                        num = -100;
                    }else {
                        num = 100;
                    }
                    break;
                case 'D':
                    num = 500;
                    break;
                case 'M':
                    num = 1000;
                    break;
                default:
                    break;
            }
            lastc = chars[i];
            result += num;
        }
        return result;
    }
}
