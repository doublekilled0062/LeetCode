import java.util.ArrayList;
import java.util.List;

/**
 * 17. 电话号码的字母组合
 *
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 示例:
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 */
public class LeetCode17 {
    String[] strings = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    /**
     * 循环法
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        if(digits == null || digits.length() == 0){
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        char[] chars = strings[digits.charAt(0) - '0' - 2].toCharArray();
        for(int i = 0; i < chars.length; i++){
            StringBuilder builder = new StringBuilder();
            builder.append(chars[i]);
            result.add(builder.toString());
        }
        for(int i = 1; i < digits.length(); i++){
            List<String> temp = new ArrayList<>();
            for(String s : result){
                temp.addAll(combinations(s, strings[digits.charAt(i) - '0' - 2].toCharArray()));
            }
            result = temp;
        }
        return result;
    }
    List<String> combinations(String s1, char[] s2){
        List<String> result = new ArrayList<>();
        for(int j = 0; j < s2.length; j++){
            StringBuilder builder = new StringBuilder();
            builder.append(s1).append(s2[j]);
            result.add(builder.toString());
        }
        return result;
    }

    /**
     * 递归法
     * @param digits
     * @return
     */
    public List<String> letterCombinations2(String digits) {
        if(digits == null || digits.length() == 0){
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        result = combinations2(result, digits, 0, "");
        return result;
    }

    List<String> combinations2(List<String> result, String digits, int index, String tempStr){
        if(index < digits.length() - 1){
            char[] chars = strings[digits.charAt(index) - '0' - 2].toCharArray();
            for(int i = 0; i < chars.length; i++){
                result = combinations2(result, digits, index + 1, tempStr + chars[i]);
            }
            index++;
        }else {
            char[] chars = strings[digits.charAt(index) - '0' - 2].toCharArray();
            for(int i = 0; i < chars.length; i++){
                result.add(tempStr + chars[i]);
            }
        }
        return result;
    }

}
