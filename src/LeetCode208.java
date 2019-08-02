/**
 * 208. 实现 Trie (前缀树)
 *
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 *
 * 示例:
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 *
 * 说明:
 * 你可以假设所有的输入都是由小写字母 a-z 构成的。
 * 保证所有输入均为非空字符串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode208 {
    /**
     * 优化一下 第一个不用放根目录 直接判断数组索引下是否为空即可
     */
    class Trie {
        private TrieNode root;

        /** Initialize your data structure here. */
        public Trie() {
            root = new TrieNode();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
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

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            char[] chars = word.toCharArray();
            TrieNode p = root;
            for (int i = 0; i < word.length(); i++) {
                int index = chars[i] - 'a';
                if (p.next[index] == null) {
                    return false;
                }
                p = p.next[index];
            }
            if (p.isEnd == false) {
                return false;
            }else {
                return true;
            }
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            char[] chars = prefix.toCharArray();
            TrieNode p = root;
            for (int i = 0; i < prefix.length(); i++) {
                int index = chars[i] - 'a';
                if (p.next[index] == null) {
                    return false;
                }
                p = p.next[index];
            }
            return true;
        }

        public class TrieNode{
            public boolean isEnd;
            public TrieNode[] next = new TrieNode[26];

            public TrieNode(){
            }
        }
    }

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

    public static void main(String[] args) {
        LeetCode208 leetCode208 = new LeetCode208();
        LeetCode208.Trie trie = leetCode208.new Trie();

        trie.insert("apple");
        System.out.println(trie.search("apple"));   // 返回 true
        System.out.println(trie.search("app"));     // 返回 false
        System.out.println(trie.startsWith("app")); // 返回 true
        trie.insert("app");
        System.out.println(trie.search("app"));     // 返回 true
    }
}
