/**
 * 211. 添加与搜索单词 - 数据结构设计
 *
 * 设计一个支持以下两种操作的数据结构：
 *
 * void addWord(word)
 * bool search(word)
 * search(word) 可以搜索文字或正则表达式字符串，字符串只包含字母 . 或 a-z 。 . 可以表示任何一个字母。
 *
 * 示例:
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 *
 * 说明:
 * 你可以假设所有单词都是由小写字母 a-z 组成的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-and-search-word-data-structure-design
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode211 {
    class WordDictionary {
        private TrieNode root;

        /** Initialize your data structure here. */
        public WordDictionary() {
            root = new TrieNode();
        }

        /** Adds a word into the data structure. */
        public void addWord(String word) {
            char[] chars = word.toCharArray();
            TrieNode p = root;
            for (int i = 0; i < word.length(); i++) {
                int index = chars[i] - 'a';
                if (p.next[index] == null) {
                    p.next[index] = new TrieNode();
                }
                p = p.next[index];
            }
            p.isEnd = true;
        }

        /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
        public boolean search(String word) {
            return search(word, 0, root);
        }

        private boolean search(String word, int index, TrieNode root){
            if(index == word.length()){
                return root.isEnd;
            }
            char c = word.charAt(index);
            if(c == '.'){
                for(TrieNode node : root.next){
                    if(node != null && search(word, index + 1, node)){
                        return true;
                    }
                }
            }else {
                if(root.next[c - 'a'] != null){
                    return search(word, index + 1, root.next[c - 'a']);
                }else {
                    return false;
                }
            }
            return false;
        }
    }

    public class TrieNode{
        public boolean isEnd;
        public TrieNode[] next = new TrieNode[26];

        public TrieNode(){
        }
    }

    public static void main(String[] args) {
        LeetCode211 leetCode211 = new LeetCode211();
        WordDictionary wordDictionary = leetCode211.new WordDictionary();
        wordDictionary.addWord("bad");
//        wordDictionary.addWord("dad");
//        wordDictionary.addWord("mad");
//        wordDictionary.search("pad");
        wordDictionary.search("bad");
        wordDictionary.search(".ad");
        wordDictionary.search("b..");

    }
/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
}
