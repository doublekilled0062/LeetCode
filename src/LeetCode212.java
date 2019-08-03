import java.util.*;

/**
 * 212. 单词搜索 II
 *
 * 给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母在一个单词中不允许被重复使用。
 *
 * 示例:
 * 输入:
 * words = ["oath","pea","eat","rain"] and board =
 * [
 *   ['o','a','a','n'],
 *   ['e','t','a','e'],
 *   ['i','h','k','r'],
 *   ['i','f','l','v']
 * ]
 *
 * 输出: ["eat","oath"]
 * 说明:
 * 你可以假设所有输入都由小写字母 a-z 组成。
 *
 * 提示:
 * 你需要优化回溯算法以通过更大数据量的测试。你能否早点停止回溯？
 * 如果当前单词不存在于所有单词的前缀中，则可以立即停止回溯。
 * 什么样的数据结构可以有效地执行这样的操作？散列表是否可行？为什么？
 * 前缀树如何？如果你想学习如何实现一个基本的前缀树，请先查看这个问题： 实现Trie（前缀树）。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-search-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode212 {
    /**
     * 按照每个单词去搜 时间复杂度不好
     * @param board
     * @param words
     * @return
     */
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        if(board == null || board.length == 0 || board[0].length == 0){
            return res;
        }

        LinkedList<Integer>[] map = new LinkedList[26];
        int row = board.length;
        int col = board[0].length;

        //初始化
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(map[board[i][j] - 'a'] == null){
                    map[board[i][j] - 'a'] = new LinkedList<>();
                }
                map[board[i][j] - 'a'].add(i * col + j);
            }
        }
        for(String word : words){
            LinkedList<Integer> list = map[word.charAt(0) - 'a'];
            if(list == null){
                continue;
            }
            for(int val : list){
                int i = val / col;
                int j = val % col;
                if(dfs(board, row, col, i, j, word, 0, new boolean[row][col])){
                    res.add(word);
                    break;
                }
            }
        }
        return res;
    }

    private boolean dfs(char[][] board, int row, int col, int i, int j, String word, int index, boolean[][] used){
        if(index >= word.length()){
            return true;
        }
        if(i < 0 || i >= row || j < 0 || j >= col || used[i][j] || board[i][j] != word.charAt(index)){
            return false;
        }
        used[i][j] = true;
        if(dfs(board, row, col, i - 1, j, word, index + 1, used)
                || dfs(board, row, col, i + 1, j, word, index + 1, used)
                || dfs(board, row, col, i, j - 1, word, index + 1, used)
                || dfs(board, row, col, i, j + 1, word, index + 1, used)){
            return true;
        }
        used[i][j] = false;
        return false;
    }

    /**
     * 把给定单词放进trie树里，这样搜索复杂度就和给的单词关联度有关，关联度高就不用搜索重复的了
     * @param board
     * @param words
     * @return
     */
    public List<String> findWords1(char[][] board, String[] words) {
        Trie trie = new Trie();
        for (String word: words){
            trie.insert(word);
        }

        int row = board.length;
        int col = board[0].length;
        List<String> result = new ArrayList<>();

        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j){
                dfs(board, row, col, i, j, trie.root, result);
            }
        }
        return result;
    }

    private void dfs(char[][] board, int row, int col, int i, int j, TrieNode root, List<String> result) {
        if (board[i][j] == '.'){
            return;
        }

        root = root.children[board[i][j] - 'a'];
        if (root == null){
            return;
        }

        if (root.word != null){
            //找到一个单词
            result.add(root.word);
            root.word = null;
        }

        char c = board[i][j];
        board[i][j] = '.';
        if(i - 1 >= 0){
            dfs(board, row, col, i - 1, j, root, result);
        }
        if(i + 1 < row){
            dfs(board, row, col, i + 1, j, root, result);
        }
        if(j - 1 >= 0){
            dfs(board, row, col, i, j - 1, root, result);
        }
        if(j + 1 < col){
            dfs(board, row, col, i, j + 1, root, result);
        }
        board[i][j] = c;
    }

    class Trie {
        public TrieNode root = new TrieNode();

        public void insert(String word) {
            TrieNode node = root;
            int length = word.length();
            for (int i = 0; i < length; ++i) {
                int index = word.charAt(i) - 'a';
                if (node.children[index] == null){
                    node.children[index] = new TrieNode();
                }
                node = node.children[index];
            }
            //结束标记
            node.word = word;
        }
    }

    class TrieNode {
        public TrieNode[] children = new TrieNode[26];
        public String word = null;
    }
}
