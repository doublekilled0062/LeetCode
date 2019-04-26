import java.util.ArrayList;
import java.util.List;

/**
 * 118. 杨辉三角
 *
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * 示例:
 * 输入: 5
 * 输出:
 * [
 *    [1],
 *    [1,1],
 *    [1,2,1],
 *    [1,3,3,1],
 *    [1,4,6,4,1]
 * ]
 * 在真实的面试中遇到过这道题？
 */
public class LeetCode118 {
    /**
     * 这个不想用递归
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<>();
        if(numRows <= 0){
            return new ArrayList<>();
        }
        for(int i = 1; i <= numRows; i++){
            List<Integer> temp = new ArrayList<>();
            temp.add(1);
            if(i == 1){
                list.add(temp);
            }else {
                List<Integer> pre = list.get(i - 2);
                for(int j = 1; j < i - 1; j++){
                    temp.add(pre.get(j) + pre.get(j-1));
                }
                temp.add(1);
                list.add(temp);
            }
        }
        return list;
    }

    public List<List<Integer>> generate2(int numRows) {
        if(numRows <= 0){
            return new ArrayList<>();
        }
        List<Integer> temp = new ArrayList<>();
        if(numRows == 1){
            List<List<Integer>> list = new ArrayList<>();
            temp.add(1);
            list.add(temp);
            return list;
        }
        List<List<Integer>> tempList = generate2(numRows - 1);
        temp.add(1);
        List<Integer> pre = tempList.get(tempList.size() - 1);
        for(int j = 1; j < numRows - 1; j++){
            temp.add(pre.get(j) + pre.get(j-1));
        }
        temp.add(1);
        tempList.add(temp);
        return tempList;
    }
}
