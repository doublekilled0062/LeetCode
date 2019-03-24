import java.util.*;

/**
 * 500. 键盘行
 * 
 * 给定一个单词列表，只返回可以使用在键盘同一行的字母打印出来的单词。键盘如下图所示。
 * American keyboard
 *  Q W E R T Y U I O P
 *   A S D F G H J K L
 *    Z X C V B N M
 *
 * 示例：
 * 输入: ["Hello", "Alaska", "Dad", "Peace"]
 * 输出: ["Alaska", "Dad"]
 * 注意：
 * 你可以重复使用键盘上同一字符。
 * 你可以假设输入的字符串将只包含字母。
 */
public class LeetCode500 {
    Map<Character, Integer> map = new HashMap<Character, Integer>(){{
        put('Q', 1);put('q', 1);
        put('W', 1);put('w', 1);
        put('E', 1);put('e', 1);
        put('R', 1);put('r', 1);
        put('T', 1);put('t', 1);
        put('Y', 1);put('y', 1);
        put('U', 1);put('u', 1);
        put('I', 1);put('i', 1);
        put('O', 1);put('o', 1);
        put('P', 1);put('p', 1);
        put('A', 2);put('a', 2);
        put('S', 2);put('s', 2);
        put('D', 2);put('d', 2);
        put('F', 2);put('f', 2);
        put('G', 2);put('g', 2);
        put('H', 2);put('h', 2);
        put('J', 2);put('j', 2);
        put('K', 2);put('k', 2);
        put('L', 2);put('l', 2);
        put('Z', 3);put('z', 3);
        put('X', 3);put('x', 3);
        put('C', 3);put('c', 3);
        put('V', 3);put('v', 3);
        put('B', 3);put('b', 3);
        put('N', 3);put('n', 3);
        put('M', 3);put('m', 3);
    }};

    /**
     * 这个题好无聊
     * @param words
     * @return
     */
    public String[] findWords(String[] words) {
        List<String> result = new ArrayList<>();
        for(String word : words){
            int row = 0;
            boolean isSame = true;
            char[] chars = word.toCharArray();
            for(char c : chars){
                if(row == 0){
                    row = map.get(c);
                }
                if(map.get(c) != row){
                    isSame = false;
                    break;
                }
            }
            if(isSame){
                result.add(word);
            }
        }
        return result.toArray(new String[0]);
    }
}
