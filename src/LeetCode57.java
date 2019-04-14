import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 57. 插入区间
 *
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * 示例 1:
 * 输入: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出: [[1,5],[6,9]]
 * 示例 2:
 * 输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出: [[1,2],[3,10],[12,16]]
 * 解释: 这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 */
public class LeetCode57 {
    /**
     * 和56一样
     * 4ms 99.31%
     * @param intervals
     * @param newInterval
     * @return
     */
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        int[] starts = new int[intervals.size() + 1];
        int[] ends = new int[intervals.size() + 1];

        for(int i = 0 ; i < intervals.size(); i++) {
            starts[i] = intervals.get(i).start;
            ends[i] = intervals.get(i).end;
        }
        starts[intervals.size()] = newInterval.start;
        ends[intervals.size()] = newInterval.end;

        Arrays.sort(starts);
        Arrays.sort(ends);

        int pStart = 0;
        int pEnd = 0;
        List<Interval> res = new ArrayList<>();
        while (pEnd < ends.length){
            if(pEnd < ends.length - 1 && starts[pEnd + 1] <= ends[pEnd]){
                pEnd++;
            }else {
                res.add(new Interval(starts[pStart], ends[pEnd]));
                pStart = ++pEnd;
            }
        }
        return res;
    }

    /**
     * 题干说明是排好序的不重叠的 所以可以一遍 不用排序
     * @param intervals
     * @param newInterval
     * @return
     */
    public List<Interval> insert2(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>();
        if(intervals == null || intervals.size() == 0){
            result.add(newInterval);
            return result;
        }

        //返回值也要按从小到大排好 所以这里要分开处理
        if(newInterval.end < intervals.get(0).start ){
            result.add(newInterval);
            result.addAll(intervals);
            return result;
        }

        if(intervals.get(intervals.size() - 1).end < newInterval.start){
            intervals.add(newInterval);
            return intervals;
        }

        int i = 0;
        //1.先把不重合的 newInterval左边的加入
        while (i < intervals.size()){
            if(intervals.get(i).end  < newInterval.start){
                result.add(intervals.get(i));
                i++;
            }else {
                break;
            }
        }
        //肯定不会到头，前面已经判断
        boolean newInFlag = true;
        while (i < intervals.size()){
            if(newInterval.end < intervals.get(i).start){
                result.add(newInterval);
                result.add(intervals.get(i));
                newInFlag = false;
                i++;
                break;
            }else {
                //肯定有重叠
                newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
                newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
                i++;
            }
        }
        //一直合并到最后
        if(newInFlag == true){
            result.add(newInterval);
            return result;
        }
        //继续添加剩下的
        while (i < intervals.size()){
            result.add(intervals.get(i));
            i++;
        }
        return result;
    }

    public class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }
}
