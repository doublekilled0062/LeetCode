import java.util.*;

/**
 * 187. 重复的DNA序列
 *
 * 所有 DNA 由一系列缩写为 A，C，G 和 T 的核苷酸组成，例如：“ACGAATTCCG”。在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。
 * 编写一个函数来查找 DNA 分子中所有出现超多一次的10个字母长的序列（子串）。
 *
 * 示例:
 * 输入: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * 输出: ["AAAAACCCCC", "CCCCCAAAAA"]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/repeated-dna-sequences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode187 {
    public List<String> findRepeatedDnaSequences(String s) {
        if(s == null || s.length() < 10){
            return new ArrayList<>();
        }
        int start = 0;
        Map<String, Boolean> map = new HashMap<>();
        List<String> res = new ArrayList<>();

        for(int i = start; i <= s.length() - 10; i++){
            String str = s.substring(i, i + 10);
            if(!map.containsKey(str)){
                map.put(str, false);
            }else {
                if(!map.get(str)){
                    res.add(str);
                    map.put(str, true);
                }
            }
        }
        return res;
    }

    /**
     * 看的大神的关于hashmap的优化 ACGT四个字母可以用2个bit表示，10个字母一共是20个bit
     * 但是时间差不多
     * @param s
     * @return
     */
    public List<String> findRepeatedDnaSequences1(String s) {
        List<String> seqs = new ArrayList<>();
        Set<Integer> seq = new HashSet<>();
        Set<Integer> repeatedSeq = new HashSet<>();
        HashMap<Character,Integer> map = new HashMap<Character,Integer>();
        map.put('A',0);
        map.put('C',1);
        map.put('G',2);
        map.put('T',3);

        int v = 0;
        // Use a sliding window to check every 10-bit substring
        for (int i = 0; i < s.length(); i++) {
            // 2 bits/char * 10 char = 20 bits so use 0xfffff
            v = (v<<2 | map.get(s.charAt(i))) & 0xfffff;
            if (i < 9) {
                continue;
                // Check each 10-bit substring
            } else {
                // If first come out duplicates, then add to list
                if (!seq.add(v) && repeatedSeq.add(v))
                    seqs.add(s.substring(i-9, i+1));
            }
        }

        return seqs;
    }

    public static void main(String[] args) {
        LeetCode187 leetCode187 = new LeetCode187();
        leetCode187.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT");
    }
}
