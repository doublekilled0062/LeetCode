import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 131. 分割回文串
 *
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 * 返回 s 所有可能的分割方案。
 * 示例:
 * 输入: "aab"
 * 输出:
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 */
public class LeetCode131 {
    /**
     * 递归回溯 适当剪枝 然后判断回文应该有个结果集省的每次都判断
     * 居然不加结果集set要快
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        if(s == null || s.length() == 0){
            return result;
        }
        partition(s, 0, s.length() - 1, new ArrayList<>(), result);
        return result;
    }

    private void partition(String s, int start, int end, List<String> temp, List<List<String>> result) {
        if(start > end){
            result.add(new ArrayList<>(temp));
            return;
        }
        for(int i = start; i <= end; i++){
            if(checkPartition(s, start, i)){
                temp.add(s.substring(start, i + 1));
                partition(s, i + 1, end, temp, result);
                temp.remove(temp.size() - 1);
            }
        }
    }

    private boolean checkPartition(String s, int start, int end){
        int i = start;
        int j = end;
        while (i < j){
            if(s.charAt(i) != s.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode131 leetCode131 = new LeetCode131();
        leetCode131.partition("abbab");
    }
}
