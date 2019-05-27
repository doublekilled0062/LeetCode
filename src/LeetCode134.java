import java.util.ArrayList;
import java.util.List;

/**
 * 134. 加油站
 *
 * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 *
 * 说明:
 * 如果题目有解，该答案即为唯一答案。
 * 输入数组均为非空数组，且长度相同。
 * 输入数组中的元素均为非负数。
 *
 * 示例 1:
 * 输入:
 * gas  = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 * 输出: 3
 * 解释:
 * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
 * 因此，3 可为起始索引。
 *
 * 示例 2:
 * 输入:
 * gas  = [2,3,4]
 * cost = [3,4,3]
 * 输出: -1
 * 解释:
 * 你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
 * 我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
 * 开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
 * 你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
 * 因此，无论怎样，你都不可能绕环路行驶一周。
 */
public class LeetCode134 {
    /**
     * 第一个开始的索引 肯定是gas[i] >= cost[i]
     * 先遍历找每一个这样的i 然后有三个数值 时刻保持 pre + gas[i] - cost[i] >= 0
     * pre 初始值为0 迭代 pre = pre + gas[i] - cost[i]
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;
        List<Integer> startList = new ArrayList<>();
        for(int i = 0; i < len; i++){
            if(gas[i] >= cost[i]){
                startList.add(i);
            }
        }
        //没找到肯定不能成功
        if(startList.isEmpty()){
            return -1;
        }

        //对于每一个可能的索引
        for(Integer start : startList){
            int pre = 0;
            boolean success = true;
            //循环索引
            for(int i = 0; i < len; i++){
                int index = (start + i) % len;
                pre = pre + gas[index] - cost[index];
                if(pre < 0){
                    success = false;
                    break;
                }
            }
            if(success){
                return start;
            }
        }
        return -1;
    }

    /**
     * 两步遍历可以合成一步
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int len = gas.length;
        for(int i = 0; i < len; i++){
            if(gas[i] >= cost[i]){
                int pre = 0;
                boolean success = true;
                //循环索引
                for(int j = 0; j < len; j++){
                    int index = (i + j) % len;
                    pre = pre + gas[index] - cost[index];
                    if(pre < 0){
                        success = false;
                        break;
                    }
                }
                if(success){
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 再尝试优化一下 开始就计算通项公式 pre + gas[i] - cost[i] >= 0
     * 最后pre要大于等于0 证明能成功
     * 起始点开始为第一个gas[i] - cost[i] >= 0 的i值 然后通过不断更新cur值
     * 最后一个使cur >= 0的起始索引
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit3(int[] gas, int[] cost) {
        int len = gas.length;
        int pre = 0;
        int start = -1;
        int cur = 0;
        for(int i = 0; i < len; i++){
            pre = pre + gas[i] - cost[i];
            if(start >= 0){
                cur = cur + gas[i] - cost[i];
                if(cur < 0){
                    //这个start开始已经到不了其他的节点所以不合法
                    start = -1;
                    cur = 0;
                }
            }else {
                if(gas[i] >= cost[i]){
                    start = i;
                    cur = gas[i] - cost[i];
                }
            }
        }
        if(pre < 0){
            return -1;
        }
        return start;
    }

    public static void main(String[] args) {
        LeetCode134 leetCode134 = new LeetCode134();
//        System.out.println(leetCode134.canCompleteCircuit3(new int[]{1,2,3,4,5}, new int[]{3,4,5,1,2}));
//        System.out.println(leetCode134.canCompleteCircuit3(new int[]{2,3,4}, new int[]{3,4,3}));
        System.out.println(leetCode134.canCompleteCircuit3(new int[]{2,0,1,2,3,4,0}, new int[]{0,1,0,0,0,0,11}));
    }
}
