import java.util.*;

/**
 * 218. 天际线问题
 *
 * 城市的天际线是从远处观看该城市中所有建筑物形成的轮廓的外部轮廓。
 * 现在，假设您获得了城市风光照片（图A）上显示的所有建筑物的位置和高度，
 * 请编写一个程序以输出由这些建筑物形成的天际线（图B）。
 *        _____
 *  |    |     |
 *  |    |     |
 *  |    |   __|______
 *  |    |  |  |      |
 *  |    |  |  |      |
 *  |   _|__|__|__    |     ______
 *  |  | |  |  |  |   |    |      |
 *  |  | |  |  |  |   |    |     _|____
 *  |  | |  |  |  |   |    |    | |    |
 *  |  | |  |  |  |   |    |    | |    |
 *  |  | |  |  |  |   |    |    | |    |
 *  |  | |  |  |  |   |    |    | |    |
 *  |  | |  |  |  |   |    |    | |    |
 *  |  | |  |  |  |   |    |    | |    |
 *  |__|_|__|__|__|___|____|____|_|____|______
 *     2 3  5  7  9  12    15    20    24
 *
 *
 *       *_____
 *  |    |     |
 *  |    |     |
 *  |    |   __*______
 *  |    |  |  |      |
 *  |    |  |  |      |
 *  |  *_|__|__|__    |    *______
 *  |  | |  |  |  |   |    |      |
 *  |  | |  |  |  |   |    |     _*____
 *  |  | |  |  |  |   |    |    | |    |
 *  |  | |  |  |  |   |    |    | |    |
 *  |  | |  |  |  |   |    |    | |    |
 *  |  | |  |  |  |   |    |    | |    |
 *  |  | |  |  |  |   |    |    | |    |
 *  |  | |  |  |  |   |    |    | |    |
 *  |__|_|__|__|__|__ *____|____|_|____*______
 *     2 3  5  7  9  12    15    20    24
 *
 *
 * 每个建筑物的几何信息用三元组 [Li，Ri，Hi] 表示，其中 Li 和 Ri 分别是第 i 座建筑物左右边缘的 x 坐标，
 * Hi 是其高度。可以保证 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX 和 Ri - Li > 0。
 * 您可以假设所有建筑物都是在绝对平坦且高度为 0 的表面上的完美矩形。
 *
 * 例如，图A中所有建筑物的尺寸记录为：[ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] 。
 * 输出是以 [ [x1,y1], [x2, y2], [x3, y3], ... ] 格式的“关键点”（图B中的红点）的列表，它们唯一地定义了天际线。
 * 关键点是水平线段的左端点。请注意，最右侧建筑物的最后一个关键点仅用于标记天际线的终点，并始终为零高度。
 * 此外，任何两个相邻建筑物之间的地面都应被视为天际线轮廓的一部分。
 *
 * 例如，图B中的天际线应该表示为：[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ]。
 *
 * 说明:
 * 任何输入列表中的建筑物数量保证在 [0, 10000] 范围内。
 * 输入列表已经按左 x 坐标 Li  进行升序排列。
 * 输出列表必须按 x 位排序。
 * 输出天际线中不得有连续的相同高度的水平线。例如 [...[2 3], [4 5], [7 5], [11 5], [12 7]...] 是不正确的答案；
 * 三条高度为 5 的线应该在最终输出中合并为一个：[...[2 3], [4 5], [12 7], ...]
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/the-skyline-problem
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode218 {

    public class Node {
        public int x2;//结束点
        public int h;//高度

        public Node(int x2, int h) {
            this.x2 = x2;
            this.h = h;
        }
    }

    /**
     * 参考 https://blog.csdn.net/u012501459/article/details/47271561
     * 其实就是一个暴利O(n^2)每个节点算与之相交的然后排序
     * 优化成一个优先队列 只算与自己相关的 即当前的curX和队首的x2的范围 如果在范围内就算相交，不在范围内弹出一个迭代
     * @param buildings
     * @return
     */
    public List<List<Integer>> getSkyline(int[][] buildings) {
        // 按高度从大到小排序，高度相同按 x的结束点从大到小排
        PriorityQueue<Node> queue = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if(o1.h != o2.h){
                    return o2.h - o1.h;
                }
                return o2.x2 - o1.x2;
            }
        });

        List<List<Integer>> result = new ArrayList<List<Integer>>();

        int curIndex = 0;
        int curX = -1;
        int curH = -1;
        int len = buildings.length;

        while (curIndex < len || !queue.isEmpty()) {
            // 如果是最开始处理建筑，或者出现建筑物不连续的情况（即对于上面第4个建筑和第3个建筑分开的情况）
            // 最高建筑的结束点
            curX = queue.isEmpty() ? buildings[curIndex][0] : queue.peek().x2;

            if (curIndex >= len || buildings[curIndex][0] > curX) {
                //将结束时间小于等于最高建筑结束点的哪些建筑物从优先队列中弹出
                while (!queue.isEmpty() && queue.peek().x2 <= curX) {
                    queue.poll();
                }
            } else {
                //如果当前遍历到的建筑物在最高的建筑物结束之前开始，那么处理当前的建筑物
                curX = buildings[curIndex][0];
                while (curIndex < len && buildings[curIndex][0] == curX) {
                    // 处理所有在同一点开始的建筑物 放到队列里
                    queue.offer(new Node(buildings[curIndex][1], buildings[curIndex][2]));
                    curIndex++;
                }
            }
            // 输出最顶端的建筑物的高度
            curH = queue.isEmpty() ? 0 : queue.peek().h;
            if (result.isEmpty() || (result.get(result.size() - 1).get(1) != curH)) {
                List<Integer> pair = new ArrayList<>();
                pair.add(curX);
                pair.add(curH);
                result.add(pair);
            }
        }
        return result;
    }

    /**
     * 这个思路简单 但是效率不高，就是把 x1,-h 和 x2,h放到一个优先级队列里
     * 看到开始点 入队，看到结束点 出队
     * @param buildings
     * @return
     */
    public List<List<Integer>> getSkyline1(int[][] buildings){
        PriorityQueue<Node> queue = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if(o1.x2 != o2.x2){
                    return o1.x2 - o2.x2;
                }
                return o1.h - o2.h;
            }
        });
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        for(int[] build : buildings){
            queue.offer(new Node(build[0], -build[2]));
            queue.offer(new Node(build[1], build[2]));
        }

        PriorityQueue<Integer> height = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        while (!queue.isEmpty()){
            Node node = queue.poll();
            if (node.h < 0) {
                // 左端点，高度入堆
                height.offer(-node.h);
            } else {
                // 右端点，移除高度
                height.remove(node.h);
            }

            // 当前关键点，最大高度
            int maxHeight = height.isEmpty() ? 0 : height.peek();

            // 当前最大高度如果不同于上一个高度，说明这是一个转折点
            if (result.isEmpty() || result.get(result.size()-1).get(1) != maxHeight) {
                // 更新 last，并加入结果集
                List<Integer> temp = new ArrayList<>();
                temp.add(node.x2);
                temp.add(maxHeight);
                result.add(temp);
            }
        }
        return result;
    }

    /**
     *  官方题解的分治merge法 但是速度并不快
     *  Divide-and-conquer algorithm to solve skyline problem,
     *  which is similar with the merge sort algorithm.
     */
    public List<List<Integer>> getSkyline2(int[][] buildings) {
        int n = buildings.length;
        List<List<Integer>> output = new ArrayList<List<Integer>>();

        // The base cases
        if (n == 0) return output;
        if (n == 1) {
            int xStart = buildings[0][0];
            int xEnd = buildings[0][1];
            int y = buildings[0][2];

            output.add(new ArrayList<Integer>() {{add(xStart); add(y); }});
            output.add(new ArrayList<Integer>() {{add(xEnd); add(0); }});
            // output.add(new int[]{xStart, y});
            // output.add(new int[]{xEnd, 0});
            return output;
        }

        // If there is more than one building,
        // recursively divide the input into two subproblems.
        List<List<Integer>> leftSkyline, rightSkyline;
        leftSkyline = getSkyline2(Arrays.copyOfRange(buildings, 0, n / 2));
        rightSkyline = getSkyline2(Arrays.copyOfRange(buildings, n / 2, n));

        // Merge the results of subproblem together.
        return mergeSkylines(leftSkyline, rightSkyline);
    }

    /**
     *  Merge two skylines together.
     */
    public List<List<Integer>> mergeSkylines(List<List<Integer>> left, List<List<Integer>> right) {
        int nL = left.size(), nR = right.size();
        int pL = 0, pR = 0;
        int currY = 0, leftY = 0, rightY = 0;
        int x, maxY;
        List<List<Integer>> output = new ArrayList<List<Integer>>();

        // while we're in the region where both skylines are present
        while ((pL < nL) && (pR < nR)) {
            List<Integer> pointL = left.get(pL);
            List<Integer> pointR = right.get(pR);
            // pick up the smallest x
            if (pointL.get(0) < pointR.get(0)) {
                x = pointL.get(0);
                leftY = pointL.get(1);
                pL++;
            }
            else {
                x = pointR.get(0);
                rightY = pointR.get(1);
                pR++;
            }
            // max height (i.e. y) between both skylines
            maxY = Math.max(leftY, rightY);
            // update output if there is a skyline change
            if (currY != maxY) {
                updateOutput(output, x, maxY);
                currY = maxY;
            }
        }

        // there is only left skyline
        appendSkyline(output, left, pL, nL, currY);

        // there is only right skyline
        appendSkyline(output, right, pR, nR, currY);

        return output;
    }

    /**
     * Update the final output with the new element.
     */
    public void updateOutput(List<List<Integer>> output, int x, int y) {
        // if skyline change is not vertical -
        // add the new point
        if (output.isEmpty() || output.get(output.size() - 1).get(0) != x)
            output.add(new ArrayList<Integer>() {{add(x); add(y); }});
            // if skyline change is vertical -
            // update the last point
        else {
            output.get(output.size() - 1).set(1, y);
        }
    }

    /**
     *  Append the rest of the skyline elements with indice (p, n)
     *  to the final output.
     */
    public void appendSkyline(List<List<Integer>> output, List<List<Integer>> skyline,
                              int p, int n, int currY) {
        while (p < n) {
            List<Integer> point = skyline.get(p);
            int x = point.get(0);
            int y = point.get(1);
            p++;

            // update output
            // if there is a skyline change
            if (currY != y) {
                updateOutput(output, x, y);
                currY = y;
            }
        }
    }


    class Building {
        int L;
        int R;
        int H;

        public Building(int l, int r, int h) {
            L = l;
            R = r;
            H = h;
        }
    }

    /**
     * 优化后的分治
     * @param buildings
     * @return
     */
    public List<List<Integer>> getSkyline3(int[][] buildings) {
        if (buildings.length == 0 || buildings[0].length != 3) {
            return Collections.emptyList();
        }

        List<List<Integer>> result = new ArrayList<>();
        List<Building> merged = merge(buildings, 0, buildings.length);
        Building prev = merged.get(0);
        Building cur;
        int size = merged.size();
        for (int i = 1; i < size; i++) {
            cur = merged.get(i);
            if (cur.L > prev.R || cur.H != prev.H) {
                result.add(keyPoint(prev.L, prev.H));
                if (cur.L > prev.R) result.add(keyPoint(prev.R, 0));
                prev = cur;
            } else {
                prev.R = cur.R;
            }
        }

        result.add(keyPoint(prev.L, prev.H));
        result.add(keyPoint(prev.R, 0));
        return result;
    }

    private List<Integer> keyPoint(int x, int y) {
        List<Integer> list = new ArrayList<>();
        list.add(x);
        list.add(y);
        return list;
    }

    private List<Building> merge(int[][] b, int start, int end) {
        List<Building> result = new ArrayList<>();
        if (start >= end) return result;
        if (start == end - 1) {
            result.add(new Building(b[start][0], b[start][1], b[start][2]));
            return result;
        }

        int mid = start + (end - start) / 2;
        List<Building> r1 = merge(b, start, mid);
        List<Building> r2 = merge(b, mid, end);

        int size1 = r1.size();
        int size2 = r2.size();
        int i = 0;
        int j = 0;
        Building b1;
        Building b2;
        while (i < size1 && j < size2) {
            b1 = r1.get(i);
            b2 = r2.get(j);
            if (b1.R <= b2.L) {
                result.add(b1);
                i++;
            } else if (b2.R <= b1.L) {
                result.add(b2);
                j++;
            } else {
                result.addAll(merge(b1, b2));
                if (b1.L >= b1.R) i++;
                if (b2.L >= b2.R) j++;
            }
        }

        if (i < size1) {
            for (; i < size1; i++) result.add(r1.get(i));
        }
        if (j < size2) {
            for (; j < size2; j++) result.add(r2.get(j));
        }

        return result;
    }

    private List<Building> merge(Building b1, Building b2) {
        List<Building> r = new ArrayList<>();
        int margin = Math.min(b1.R, b2.R);
        if (b1.L <= b2.L) {
            if (b1.H >= b2.H) {
                r.add(new Building(b1.L, margin, b1.H));
            } else {
                if (b1.L < b2.L) r.add(new Building(b1.L, b2.L, b1.H));
                r.add(new Building(b2.L, margin, b2.H));
            }
        } else {
            if (b2.H >= b1.H) {
                r.add(new Building(b2.L, margin, b2.H));
            } else {
                r.add(new Building(b2.L, b1.L, b2.H));
                r.add(new Building(b1.L, margin, b1.H));
            }
        }
        b1.L = margin;
        b2.L = margin;
        return r;
    }

}
