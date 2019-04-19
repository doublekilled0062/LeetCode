import java.util.ArrayList;
import java.util.List;

/**
 * 93. 复原IP地址
 *
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * 示例:
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 */
public class LeetCode93 {
    /**
     * 典型回溯法 注意剪枝
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        if(s == null){
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        restoreIpAddresses(s.toCharArray(), 0, 0, new ArrayList<>(), res);
        return res;
    }

    /**
     * ["0.10.0.10","0.100.1.0"] 注意这个case
     * @param chars
     * @param index
     * @param size
     * @param tmp
     * @param result
     */
    private void restoreIpAddresses(char[] chars, int index, int size, List<String> tmp, List<String> result) {
        if(index >= chars.length){
            return;
        }
        if(size == 3){
            if(check255(chars, index, chars.length - 1)){
                result.add(tmp.get(0) + "." + tmp.get(1) + "." + tmp.get(2) + "." + new String(chars, index, chars.length - index));
            }
            return;
        }

        int offset = index;
        if(chars[offset] == '0'){
            tmp.add(new String(chars, index, offset - index + 1));
            restoreIpAddresses(chars, offset + 1, size + 1, tmp, result);
            tmp.remove(tmp.size() - 1);
            offset++;
        }else {
            while (offset <= chars.length - 1 && check255(chars, index, offset)){
                tmp.add(new String(chars, index, offset - index + 1));
                restoreIpAddresses(chars, offset + 1, size + 1, tmp, result);
                tmp.remove(tmp.size() - 1);
                offset++;
            }
        }

    }

    private boolean check255(char[] chars, int start, int end){
        if(chars[start] == '0'){
            if(start == end){
                return true;
            }else {
                return false;
            }
        }
        int sum = 0;
        for(int i = start; i <= end; i++){
            sum = sum * 10 + (chars[i] - '0');
            if(sum > 255){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode93 leetCode93 = new LeetCode93();
        leetCode93.restoreIpAddresses("25525511135");
        leetCode93.restoreIpAddresses("010010");
    }
}
