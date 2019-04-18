import java.util.LinkedList;

/**
 * 84. 柱状图中最大的矩形
 *
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 *
 *
 * |            ___
 * |           |   |
 * |        ___|   |
 * |       |   |   |
 * |       |   |   |
 * |       |   |   |
 * |       |   |   |    ___
 * |       |   |   |   |   |
 * |___    |   |   |___|   |
 * |   |   |   |   |   |   |
 * |   |___|   |   |   |   |
 * | 2 | 1 | 5 | 6 | 1 | 3 |
 * |---|---|---|---|---|---|---|---|---|---|---|---|---|-------
 *   0   1   2   3   4   5   6   7   8   9   10  11  12
 * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
 *
 * |            ___
 * |           |   |
 * |        ___|___|
 * |       |***|***|
 * |       |   |   |
 * |       |***|***|
 * |       |   |   |    ___
 * |       |***|***|   |   |
 * |___    |   |   |___|   |
 * |   |   |***|***|   |   |
 * |   |___|   |   |   |   |
 * | 2 | 1 | 5 | 6 | 1 | 3 |
 * |---|---|---|---|---|---|---|---|---|---|---|---|---|-------
 *   0   1   2   3   4   5   6   7   8   9   10  11  12
 *
 * 示例:
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 */
public class LeetCode84 {
    /**
     * 直接暴力法 效率O(n^2)
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        if(heights == null || heights.length == 0){
            return 0;
        }
        int max = 0;
        for(int i = 0; i < heights.length; i++){
            int maxArea = 0;
            int minHeight = Integer.MAX_VALUE;
            for(int j = i; j >= 0; j--){
                minHeight = Math.min(minHeight, heights[j]);
                maxArea = Math.max(maxArea, (i - j + 1) * minHeight);
            }
            max = Math.max(maxArea, max);
        }
        return max;
    }

    /**
     * 暴力法可以剪枝 没必要每次返回算 只需要在h[i] < h[i+1] 或者 最后一个元素时去算
     * @param heights
     * @return
     */
    public int largestRectangleArea2(int[] heights) {
        if(heights == null || heights.length == 0){
            return 0;
        }
        int max = 0;
        for(int i = 0; i < heights.length; i++){
            if(i + 1 == heights.length || heights[i] > heights[i+1]){
                int minHeight = heights[i];
                for(int j = i; j >= 0; j--){
                    minHeight = Math.min(minHeight, heights[j]);
                    max = Math.max(max, (i - j + 1) * minHeight);
                }
            }
        }
        return max;
    }

    /**
     * 发现还能继续剪枝
     * 每次记一个minIndex 在minIndex之前的可以一步算出来
     * 只要算到 minIndex 就可以
     * @param heights
     * @return
     */
    public int largestRectangleArea3(int[] heights) {
        if(heights == null || heights.length == 0){
            return 0;
        }
        int max = 0;
        int minIndex = 0;
        for(int i = 0; i < heights.length; i++){
            if(i + 1 == heights.length || heights[i] > heights[i+1]){
                int minHeight = heights[i];
                int temMinIndex = minIndex;
                for(int j = i; j >= temMinIndex; j--){
                    minHeight = Math.min(minHeight, heights[j]);
                    max = Math.max(max, (i - j + 1) * minHeight);
                    if(i < heights.length - 1 && heights[j] <= heights[i+1]){
                        minIndex = j;
                    }
                }
                //再看minIndex到底挪位置了没
                if(i < heights.length - 1 && heights[minIndex] >= heights[i+1]){
                    minIndex = i + 1;
                }
                //temMinIndex之前的可以直接计算
                if(temMinIndex > 0){
                    max = Math.max(max, (i + 1)* heights[temMinIndex]);
                }
            }
        }
        return max;
    }

    /**
     * 单调栈的做法
     * 如果已知height数组是升序 比如1,2,5,7,8
     * 那么就是(1*5) vs. (2*4) vs. (5*3) vs. (7*2) vs. (8*1)
     * 也就是max(height[i]*(size-i))
     * 构造单调栈
     * @param heights
     * @return
     */
    public int largestRectangleArea4(int[] heights) {
        if(heights == null || heights.length == 0){
            return 0;
        }
        int maxArea = 0;
        LinkedList<Integer> stack = new LinkedList<>();
        for(int i = 0; i < heights.length; i++){
            if(stack.isEmpty() || heights[stack.peek()] <= heights[i]){
                stack.push(i);
            }else {
                int top = stack.peek();
                while (!stack.isEmpty() && heights[stack.peek()] > heights[i]){
                    int node = stack.pop();
                    int len = 0;
                    if(stack.isEmpty()){
                        //len = i 即 top+1
                        len = i;
                    }else {
                        //如果新栈顶和node不连续 则新栈顶左边的元素肯定肯定大于node 如 1 3 (6) 4
                        //然后起始索引为i-1(即top、因为i是不合适的)
                        len = top - (stack.peek() + 1) + 1;
                    }
                    maxArea = Math.max(maxArea, len * heights[node]);
                }
                stack.push(i);
            }
        }
        //如果栈非空 则还有没处理的问题
        while (!stack.isEmpty()){
            //栈顶一定是最后一个数
            int top = heights.length - 1;
            int node = stack.pop();
            int len = 0;
            if(stack.isEmpty()){
                len = heights.length;
            }else {
                //如果新栈顶和node不连续 则新栈顶左边的元素肯定肯定大于node 如 1 3 (6) 4
                //然后起始索引为i-1(即top、因为i是不合适的)
                len = top - (stack.peek() + 1) + 1;
            }
            maxArea = Math.max(maxArea, len * heights[node]);
        }
        return maxArea;
    }

    /**
     * 看到别人有个2ms的 就是用数组模拟了一个栈
     * @param heights
     * @return
     */
    public int largestRectangleArea5(int[] heights) {
        if ((heights == null) || (heights.length == 0)) return 0;
        final int N = heights.length;
        int[] s = new int[N + 1];
        int i, top = 0, hi, area = 0;
        s[0] = -1;
        for (i = 0; i < N; i++) {
            hi = heights[i];
            while ((top > 0) && (heights[s[top]] > hi)) {
                area = Math.max(area, heights[s[top]] * (i - s[top - 1] - 1));
                top--;
            }
            s[++top] = i;
        }
        while (top > 0) {
            area = Math.max(area, heights[s[top]] * (N - s[top -1] - 1));
            top--;
        }
        return area;
    }

    public static void main(String[] args) {
        LeetCode84 leetCode84 = new LeetCode84();
        System.out.println(leetCode84.largestRectangleArea3(new int[]{5,4,1,2}));
    }
}
