import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 273. 整数转换英文表示
 *
 * 将非负整数转换为其对应的英文表示。可以保证给定输入小于 231 - 1 。
 *
 * 示例 1:
 * 输入: 123
 * 输出: "One Hundred Twenty Three"
 *
 * 示例 2:
 * 输入: 12345
 * 输出: "Twelve Thousand Three Hundred Forty Five"
 *
 * 示例 3:
 * 输入: 1234567
 * 输出: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 *
 * 示例 4:
 * 输入: 1234567891
 * 输出: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/integer-to-english-words
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode273 {
    private Map<Integer, String> map = new HashMap<Integer, String>(){{
        put(1, "One");
        put(2, "Two");
        put(3, "Three");
        put(4, "Four");
        put(5, "Five");
        put(6, "Six");
        put(7, "Seven");
        put(8, "Eight");
        put(9, "Nine");
        put(10, "Ten");
        put(11, "Eleven");
        put(12, "Twelve");
        put(13, "Thirteen");
        put(14, "Fourteen");
        put(15, "Fifteen");
        put(16, "Sixteen");
        put(17, "Seventeen");
        put(18, "Eighteen");
        put(19, "Nineteen");
        put(20, "Twenty");
        put(30, "Thirty");
        put(40, "Forty");
        put(50, "Fifty");
        put(60, "Sixty");
        put(70, "Seventy");
        put(80, "Eighty");
        put(90, "Ninety");
        put(100, "Hundred");
        put(1000, "Thousand");
        put(1000000, "Million");
        put(1000000000, "Billion");
    }};

    private int[] list = new int[]{1000000000, 1000000, 1000, 100, 90, 80, 70, 60, 50,
            40, 30, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

    /**
     * 说实话这个题很蛋疼
     * @param num
     * @return
     */
    public String numberToWords(int num) {
        if(num == 0){
            return "Zero";
        }
        if(map.containsKey(num)){
            if(num == 100 || num == 1000 || num == 1000000 || num == 1000000000){
                return "One " + map.get(num);
            }else {
                return map.get(num);
            }
        }else {
            for(int i = 0; i < list.length; i++){
                int temp = num/list[i];
                int remain = num % list[i];
                if(temp > 0){
                    if(list[i] == 100 || list[i] == 1000 || list[i] == 1000000 || list[i] == 1000000000){
                        if(remain != 0){
                            return numberToWords(temp) + " " + map.get(list[i]) + " " + numberToWords(num % list[i]);
                        }else {
                            return numberToWords(temp) + " " + map.get(list[i]);
                        }
                    }else {
                        return map.get(list[i]) + " " + numberToWords(num % list[i]);
                    }
                }
            }
        }
        return "";
    }

    public String numberToWords1(int num) {
        if(num == 0){
            return "Zero";
        }
        if(num >= 1000000000){
            int divided = num/1000000000;
            int remain = num % 1000000000;
            if(remain == 0){
                return numberToWords1(divided) + " Billion";
            }else {
                return numberToWords1(divided) + " Billion " + numberToWords1(remain);
            }
        }else if(num >= 1000000){
            int divided = num/1000000;
            int remain = num % 1000000;
            if(remain == 0){
                return numberToWords1(divided) + " Million";
            }else {
                return numberToWords1(divided) + " Million " + numberToWords1(remain);
            }
        }else if(num >= 1000){
            int divided = num/1000;
            int remain = num % 1000;
            if(remain == 0){
                return numberToWords1(divided) + " Thousand";
            }else {
                return numberToWords1(divided) + " Thousand " + numberToWords1(remain);
            }
        }else if(num >= 100){
            int divided = num/100;
            int remain = num % 100;
            if(remain == 0){
                return numberToWords1(divided) + " Hundred";
            }else {
                return numberToWords1(divided) + " Hundred " + numberToWords1(remain);
            }
        }else {
            for(int i = 0; i < list.length; i++){
                if(num >= list[i]){
                    int remain = num % list[i];
                    if(remain == 0){
                        return map.get(list[i]);
                    }else {
                        return map.get(list[i]) + " " + map.get(remain);
                    }
                }
            }
        }
        return "";
    }

    public String one(int num) {
        switch (num) {
            case 1:
                return "One";
            case 2:
                return "Two";
            case 3:
                return "Three";
            case 4:
                return "Four";
            case 5:
                return "Five";
            case 6:
                return "Six";
            case 7:
                return "Seven";
            case 8:
                return "Eight";
            case 9:
                return "Nine";
        }
        return "";
    }

    public String twoLessThan20(int num) {
        switch (num) {
            case 10:
                return "Ten";
            case 11:
                return "Eleven";
            case 12:
                return "Twelve";
            case 13:
                return "Thirteen";
            case 14:
                return "Fourteen";
            case 15:
                return "Fifteen";
            case 16:
                return "Sixteen";
            case 17:
                return "Seventeen";
            case 18:
                return "Eighteen";
            case 19:
                return "Nineteen";
        }
        return "";
    }

    public String ten(int num) {
        switch (num) {
            case 2:
                return "Twenty";
            case 3:
                return "Thirty";
            case 4:
                return "Forty";
            case 5:
                return "Fifty";
            case 6:
                return "Sixty";
            case 7:
                return "Seventy";
            case 8:
                return "Eighty";
            case 9:
                return "Ninety";
        }
        return "";
    }

    public String two(int num) {
        if (num == 0)
            return "";
        else if (num < 10)
            return one(num);
        else if (num < 20)
            return twoLessThan20(num);
        else {
            int tenner = num / 10;
            int rest = num - tenner * 10;
            if (rest != 0)
                return ten(tenner) + " " + one(rest);
            else
                return ten(tenner);
        }
    }

    public String three(int num) {
        int hundred = num / 100;
        int rest = num - hundred * 100;
        String res = "";
        if (hundred * rest != 0)
            res = one(hundred) + " Hundred " + two(rest);
        else if ((hundred == 0) && (rest != 0))
            res = two(rest);
        else if ((hundred != 0) && (rest == 0))
            res = one(hundred) + " Hundred";
        return res;
    }

    public String numberToWords2(int num) {
        if (num == 0)
            return "Zero";

        int billion = num / 1000000000;
        int million = (num - billion * 1000000000) / 1000000;
        int thousand = (num - billion * 1000000000 - million * 1000000) / 1000;
        int rest = num - billion * 1000000000 - million * 1000000 - thousand * 1000;

        String result = "";
        if (billion != 0)
            result = three(billion) + " Billion";
        if (million != 0) {
            if (!result.isEmpty())
                result += " ";
            result += three(million) + " Million";
        }
        if (thousand != 0) {
            if (!result.isEmpty())
                result += " ";
            result += three(thousand) + " Thousand";
        }
        if (rest != 0) {
            if (!result.isEmpty())
                result += " ";
            result += three(rest);
        }
        return result;
    }

    public static void main(String[] args) {
        LeetCode273 leetCode273 = new LeetCode273();
        System.out.println(leetCode273.numberToWords1(12345));
        System.out.println(leetCode273.numberToWords1(1234567));
        System.out.println(leetCode273.numberToWords1(1234567891));
    }
}
