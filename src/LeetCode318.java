/**
 * 318. 最大单词长度乘积
 *
 * 给定一个字符串数组 words，找到 length(word[i]) * length(word[j]) 的最大值，并且这两个单词不含有公共字母。
 * 你可以认为每个单词只包含小写字母。如果不存在这样的两个单词，返回 0。
 *
 * 示例 1:
 * 输入: ["abcw","baz","foo","bar","xtfn","abcdef"]
 * 输出: 16
 * 解释: 这两个单词为 "abcw", "xtfn"。
 *
 * 示例 2:
 * 输入: ["a","ab","abc","d","cd","bcd","abcd"]
 * 输出: 4
 * 解释: 这两个单词为 "ab", "cd"。
 *
 * 示例 3:
 * 输入: ["a","aa","aaa","aaaa"]
 * 输出: 0
 * 解释: 不存在这样的两个单词。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-product-of-word-lengths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode318 {
    /**
     * 没有特殊算法，只有计算是否有重复字母的时候有简便方法
     * 用数组存下中间值看看每个字符串可以表示为一个int
     * 每个字母存在表示为int中的一个位
     * @param words
     * @return
     */
    public int maxProduct(String[] words) {
        if(words == null || words.length < 2){
            return 0;
        }
        int max = 0;
        int[] sets = new int[words.length];
        for(int i = 0; i < words.length; i++){
            for(char c : words[i].toCharArray()){
                sets[i] |= 1 << (c - 'a');
            }
        }
        for(int i = 0; i < words.length - 1; i++) {
            for(int j = i + 1; j < words.length; j++){
                if((sets[i] & sets[j]) == 0){
                    max = Math.max(max, words[i].length() * words[j].length());
                }
            }
        }
        return max;
    }
}
