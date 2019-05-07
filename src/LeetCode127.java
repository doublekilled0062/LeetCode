import java.util.*;

/**
 * 127. 单词接龙
 *
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 *
 * 说明:
 * 如果不存在这样的转换序列，返回 0。
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
 * 输出: 5
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * 返回它的长度 5。
 *
 * 示例 2:
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * 输出: 0
 * 解释: endWord "cog" 不在字典中，所以无法进行转换。
 */
public class LeetCode127 {
    private Map<String, Set<String>> wordMap = new HashMap<>();

    /**
     * 看题解类似一个回溯的机制，字典里不能有环每个单词只能用一次，
     * 为什么不能有环 可以用反证法证明有环就不是最短
     * 字典要预处理 保证字典里的词都在beginWord 和 endWord的并集里
     * 用DFS回溯的方法会超时 所以该用BFS第一个符合条件的就是最小值
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(wordList == null || wordList.isEmpty()){
            return 0;
        }

        Set<String> realList = new HashSet<>(wordList);
        if(!realList.contains(endWord)){
            return 0;
        }

        handleWorldList(beginWord, new ArrayList<>(realList));
        Map<String, Boolean> usedMap = new HashMap<>();
        LinkedList<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int level = 0;
        while (!queue.isEmpty()){
            level++;
            int size = queue.size();
            while (size > 0){
                size--;
                String node = queue.poll();
                if(usedMap.containsKey(node)){
                    continue;
                }
                if(node.equals(endWord)){
                    return level;
                }
                if(!wordMap.containsKey(node)){
                    return 0;
                }
                usedMap.put(node, true);
                for(String str : wordMap.get(node)){
                    if(!str.equals(beginWord) && !usedMap.containsKey(str)){
                        queue.offer(str);
                    }
                }
            }
        }

        return 0;
    }

    private void handleWorldList(String beginWord, List<String> worldList){
        worldList.add(beginWord);
        for(int i = 0; i < worldList.size() - 1; i++){
            for(int j = i + 1; j < worldList.size(); j++){
                if(diff(worldList.get(i), worldList.get(j)) != 1){
                    continue;
                }
                if(!wordMap.containsKey(worldList.get(i))){
                    wordMap.put(worldList.get(i), new HashSet<>());
                }
                wordMap.get(worldList.get(i)).add(worldList.get(j));
                if(!wordMap.containsKey(worldList.get(j))){
                    wordMap.put(worldList.get(j), new HashSet<>());
                }
                wordMap.get(worldList.get(j)).add(worldList.get(i));
            }
        }
    }

    private int diff(String s1, String s2){
        int diff = 0;
        int len = s1.length();
        for(int i = 0; i < len; i++){
            if(s1.charAt(i) != s2.charAt(i)){
                diff++;
            }
        }
        return diff;
    }

    public static void main(String[] args) {
        LeetCode127 leetCode127 = new LeetCode127();
        System.out.println(leetCode127.ladderLength("qa", "sq",
                Arrays.asList(new String[]{"si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le","av","sm","ar",
                        "ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma","re",
                        "or","rn","au","ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge","th","pm",
                        "rb","sh","co","ga","li","ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an","me",
                        "mo","na","la","st","er","sc","ne","mn","mi","am","ex","pt","io","be","fm","ta","tb","ni",
                        "mr","pa","he","lr","sq","ye"})));

        System.out.println(leetCode127.ladderLength("hog", "cog",
                Arrays.asList(new String[]{"cog"})));
    }
}
