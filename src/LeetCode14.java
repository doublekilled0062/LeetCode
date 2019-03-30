/**
 * 14. 最长公共前缀
 *
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 * 示例 1:
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 * 所有输入只包含小写字母 a-z 。
 */
public class LeetCode14 {
    /**
     * 答案给了5种解法 水平 按列 分治 二分 trie树
     * 其实我觉得按列扫描比较好，因为有时候数组的值可能是个null或者空串 这样在第一次循环就能出结果了 而且代码好维护
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0 || strs[0] == null || strs[0].length() == 0){
            return "";
        }
        if(strs.length == 1){
            return strs[0];
        }
        char[] chars = strs[0].toCharArray();
        int index = -1;
        for(int i = 0; i < chars.length; i++){
            for(int j = 1; j < strs.length; j++){
                if(strs[j] == null || strs[j].length() == 0){
                    return "";
                }else {
                    if(i >= strs[j].length() || chars[i] != strs[j].charAt(i)){
                        return strs[0].substring(0, index + 1);
                    }
                }
            }
            index++;
        }
        return strs[0];
    }
}
