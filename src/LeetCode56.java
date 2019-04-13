import sun.jvm.hotspot.utilities.Interval;

import java.util.*;

/**
 * 56. 合并区间
 *
 * 给出一个区间的集合，请合并所有重叠的区间。
 * 示例 1:
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 */
public class LeetCode56 {
    /**
     * 贪心算法 先排序
     * @param intervals
     * @return
     */
    public List<Interval> merge(List<Interval> intervals) {
        if(intervals == null || intervals.size() == 0){
            return new ArrayList<>();
        }
        if(intervals.size() == 1){
            return intervals;
        }
        Collections.sort(intervals, new Comparator<Interval>(){
            @Override
            public int compare(Interval o1, Interval o2) {
                if(o1.start < o2.start){
                    return -1;
                }else if(o1.start > o2.start){
                    return 1;
                }else if(o1.end < o2.end){
                    return -1;
                }else if(o1.end > o2.end){
                    return 1;
                }
                return 0;
            }
        });
        List<Interval> result = new ArrayList<>();
        Interval temp = null;
        for(Interval interval : intervals){
            if(temp == null){
                temp = interval;
                continue;
            }
            if(temp.end < interval.start){
                result.add(temp);
                temp = interval;
            }else {
                //合并
                temp.start = Math.min(temp.start, interval.start);
                temp.end = Math.max(temp.end, interval.end);
            }
        }
        if(temp != null){
            result.add(temp);
        }
        return result;
    }

    /**
     * 这个要快点
     * @param intervals
     * @return
     */
    public List<Interval> merge2(List<Interval> intervals) {
        int[] starts = new int[intervals.size()];
        int[] ends = new int[intervals.size()];

        for(int i = 0 ; i < intervals.size(); i++) {
            starts[i] = intervals.get(i).start;
            ends[i] = intervals.get(i).end;
        }

        Arrays.sort(starts);
        Arrays.sort(ends);

        int pStart = 0;
        int pEnd = 0;
        List<Interval> res = new ArrayList<>();
        while(pEnd < ends.length) {
            //第二个的起点把 第一个的终点覆盖 pEnd++
            if(pEnd < ends.length - 1 && starts[pEnd + 1] <= ends[pEnd]) {
                pEnd++;
            } else {
                res.add(new Interval(starts[pStart], ends[pEnd]));
                pStart = ++pEnd;
            }
        }
        return res;
    }

    public class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }
}
