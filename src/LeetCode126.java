import java.util.*;

/**
 * 126. 单词接龙 II
 *
 * 给定两个单词（beginWord 和 endWord）和一个字典 wordList，
 * 找出所有从 beginWord 到 endWord 的最短转换序列。转换需遵循如下规则：
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 说明:
 * 如果不存在这样的转换序列，返回一个空列表。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 *
 * 示例 1:
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出:
 * [
 *  ["hit","hot","dot","dog","cog"],
 *  ["hit","hot","lot","log","cog"]
 * ]
 *
 * 示例 2:
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * 输出: []
 * 解释: endWord "cog" 不在字典中，所以不存在符合要求的转换序列。
 */
public class LeetCode126 {

    /**
     * 和leetcode127一样 只不过各自保存已经处理过的列表和字典集合
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.size() == 0) {
            return new ArrayList<>();
        }
        Set<String> dict = new HashSet<>(wordList);
        if(!dict.contains(endWord)){
            return new ArrayList<>();
        }

        List<List<String>> totalList = new ArrayList<>();
        List<String> beginList = new ArrayList<>();
        beginList.add(beginWord);
        totalList.add(beginList);
        dict.remove(beginWord);

        List<List<String>> result = new ArrayList<>();
        bfs(totalList, endWord, dict, result);
        return result;
    }

    /**
     * 同一个key可能会有多条路径 外层需要包一个list
     * @param totalList
     * @param endWord
     * @param dict
     * @param result
     */
    public void bfs(List<List<String>> totalList, String endWord, Set<String> dict, List<List<String>> result) {
        if(totalList.isEmpty() || dict.isEmpty()){
            return;
        }

        List<List<String>> totalTempList = new ArrayList<>();
        Set<String> removeSet = new HashSet<>();
        boolean findMin = false;
        for(List<String> begin : totalList){
            String ns = begin.get(begin.size() - 1);
            char[] arr = ns.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                char tmp = arr[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    if (tmp == c) {
                        continue;
                    }
                    arr[i] = c;
                    String nstr = new String(arr);
                    if (dict.contains(nstr)) {
                        List<String> resTemp = new ArrayList<>(begin);
                        resTemp.add(nstr);
                        if (endWord.equals(nstr)) {
                            result.add(resTemp);
                            findMin = true;
                        } else {
                            totalTempList.add(resTemp);
                            removeSet.add(nstr);
                        }
                    }
                }
                arr[i] = tmp;
            }
        }
        if(!findMin){
            dict.removeAll(removeSet);
            bfs(totalTempList, endWord, dict, result);
        }
    }

    /**
     * 类似leetcode126的优化 需要两头计算 然后根据中间结果集构造结果
     * 比上一个方案时间短的原因是在bfs的时候不用每次都构造一个新的List
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public  List<List<String>> findLadders2(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        if(wordList == null) {
            return res;
        }
        Set<String> dicts = new HashSet<>(wordList);
        if(!dicts.contains(endWord)) {
            return res;
        }
        dicts.remove(beginWord);

        Set<String> endList = new HashSet<>();
        Set<String> beginList = new HashSet<>();

        Map<String, List<String>> map = new HashMap<>();
        beginList.add(beginWord);
        endList.add(endWord);

        bfs(map, beginList, endList, dicts, false);

        List<String> tempList = new ArrayList<>();
        tempList.add(beginWord);

        dfs(map, res, tempList, beginWord, endWord);

        return res;
    }

    /**
     * 保存map是关键 是一个中间结果集 相当于预处理的一个序列
     * @param map
     * @param handleSet
     * @param receiveSet
     * @param wordList
     * @param reverse
     */
    private void bfs(Map<String, List<String>> map, Set<String> handleSet, Set<String> receiveSet, Set<String> wordList, boolean reverse){
        if(handleSet.size() == 0) {
            return;
        }

        wordList.removeAll(handleSet);
        boolean findMin = false;
        Set<String> handleTempSet = new HashSet<>();

        for(String str : handleSet){
            char[] arr = str.toCharArray();
            for(int i = 0; i < arr.length; i++){
                char temp = arr[i];
                for(char ch = 'a'; ch <= 'z'; ch++){
                    if(ch == temp){
                        continue;
                    }
                    arr[i] = ch;
                    String newstr = new String(arr);
                    if(!wordList.contains(newstr)){
                        continue;
                    }
                    if(receiveSet.contains(newstr)){
                        findMin = true;
                    }else{
                        handleTempSet.add(newstr);
                    }

                    //key = pre value = after reverse为true时是从后往前算的
                    String key = reverse ? newstr : str;
                    String value = reverse ? str : newstr;

                    if(!map.containsKey(key)){
                        map.put(key, new ArrayList<>());
                    }
                    map.get(key).add(value);

                }
                arr[i] = temp;
            }
        }
        if(!findMin) {
            if(handleTempSet.size() > receiveSet.size()){
                bfs(map, receiveSet, handleTempSet, wordList, !reverse);
            }else{
                bfs(map, handleTempSet, receiveSet, wordList, reverse);
            }
        }
    }

    /**
     * 根据中间结果集构造结果
     * @param map
     * @param result
     * @param tempList
     * @param beginWord
     * @param endWord
     */
    private void dfs (Map<String, List<String>> map, List<List<String>> result, List<String> tempList, String beginWord, String endWord) {
        if(beginWord.equals(endWord)) {
            result.add(new ArrayList<>(tempList));
            return;
        }
        if (!map.containsKey(beginWord)) {
            return;
        }
        for (String word : map.get(beginWord)) {
            tempList.add(word);
            dfs(map, result, tempList, word, endWord);
            tempList.remove(tempList.size() - 1);
        }
    }


    public static void main(String[] args) {
        LeetCode126 leetCode126 = new LeetCode126();
        System.out.println(leetCode126.findLadders("qa", "sq",
                Arrays.asList(new String[]{"si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le","av","sm","ar",
                        "ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma","re",
                        "or","rn","au","ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge","th","pm",
                        "rb","sh","co","ga","li","ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an","me",
                        "mo","na","la","st","er","sc","ne","mn","mi","am","ex","pt","io","be","fm","ta","tb","ni",
                        "mr","pa","he","lr","sq","ye"})));

        System.out.println(leetCode126.findLadders("hog", "cog",
                Arrays.asList(new String[]{"cog"})));

        System.out.println(leetCode126.findLadders("hit", "cog",
                Arrays.asList(new String[]{"hot","dot","dog","lot","log","cog"})));
    }
}
