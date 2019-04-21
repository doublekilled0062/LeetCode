import java.util.ArrayList;
import java.util.List;

/**
 * 68. 文本左右对齐
 *
 * 给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
 * 你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
 * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
 * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
 * 说明:
 * 单词是指由非空格字符组成的字符序列。
 * 每个单词的长度大于 0，小于等于 maxWidth。
 * 输入单词数组 words 至少包含一个单词。
 *
 * 示例:
 * 输入:
 * words = ["This", "is", "an", "example", "of", "text", "justification."]
 * maxWidth = 16
 * 输出:
 * [
 * "This    is    an",
 * "example  of text",
 * "justification.  "
 * ]
 *
 *  示例 2:
 * 输入:
 * words = ["What","must","be","acknowledgment","shall","be"]
 * maxWidth = 16
 * 输出:
 * [
 * "What   must   be",
 * "acknowledgment  ",
 * "shall be        "
 * ]
 * 解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
 * 因为最后一行应为左对齐，而不是左右两端对齐。
 * 第二行同样为左对齐，这是因为这行只包含一个单词。
 *
 * 示例 3:
 * 输入:
 * words = ["Science","is","what","we","understand","well","enough","to","explain",
 * "to","a","computer.","Art","is","everything","else","we","do"]
 * maxWidth = 20
 * 输出:
 * [
 * "Science  is  what we",
 * "understand      well",
 * "enough to explain to",
 * "a  computer.  Art is",
 * "everything  else  we",
 * "do                  "
 * ]
 */
public class LeetCode68 {
    /**
     * 这个题就是考代码简洁度
     * @param words
     * @param maxWidth
     * @return
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int curWidth = 0;//所选单词的长度 不包括空格
        int left = 0;
        char[] chars = new char[maxWidth];
        for(int i = 0; i < maxWidth; i++){
            chars[i] = ' ';
        }
        for(int i = 0; i < words.length; i++){
            //当前单词长度 + 所选单词长度 + 空格数
            int totalLen = curWidth + words[i].length() + i - left;
            if(totalLen > maxWidth){
                //不需要第i个
                res.add(getLine(words, left, i - 1, curWidth, maxWidth, chars));
                //要倒回去一个i
                left = i;
                i = i - 1;
                curWidth = 0;
            }else {
                curWidth += words[i].length();
                //当i是最后一行的时候调用 否则走上面分支
                if(i == words.length - 1){
                    res.add(getLine(words, left, i, curWidth, maxWidth, chars));
                }
            }
        }
        return res;
    }

    private String getLine(String[] words, int left, int right, int curWidth, int maxWidth, char[] chars){
        StringBuilder builder = new StringBuilder();
        if(right - left == 0 || right == words.length - 1){
            //如果是只有一行或者到了最后一行 左对齐
            for(int i = left; i <= right; i++){
                builder.append(words[i]);
                if(i != right){
                    builder.append(' ');
                }
            }
            int blankLen = maxWidth - curWidth - (right - left);
            if(blankLen > 0){
                builder.append(new String(chars, 0, blankLen));
            }
            return builder.toString();
        }else {
            //有多行 算空格数 最少的空格数
            int blankLen = (maxWidth - curWidth)/(right - left);
            //余的空格 要分布在前blankIndex个 每个多一个空格
            int blankIndex = (maxWidth - curWidth) % (right - left);
            for(int i = left; i <= right; i++){
                if(i != right){
                    if(blankIndex > 0){
                        builder.append(words[i]).append(new String(chars, 0, blankLen + 1));
                        blankIndex--;
                    }else {
                        builder.append(words[i]).append(new String(chars, 0, blankLen));
                    }
                }else {
                    builder.append(words[i]);
                }
            }
            return builder.toString();
        }
    }

    public static void main(String[] args) {
        LeetCode68 leetCode68 = new LeetCode68();
        leetCode68.fullJustify(new String[]{"This", "is", "an", "example",
                        "of", "text", "justification."}, 16);
    }
}
