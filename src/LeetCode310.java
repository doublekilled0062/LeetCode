import java.util.*;

/**
 * 310. 最小高度树
 *
 * 对于一个具有树特征的无向图，我们可选择任何一个节点作为根。
 * 图因此可以成为树，在所有可能的树中，具有最小高度的树被称为最小高度树。
 * 给出这样的一个图，写出一个函数找到所有的最小高度树并返回他们的根节点。
 *
 * 格式
 * 该图包含 n 个节点，标记为 0 到 n - 1。给定数字 n 和一个无向边 edges 列表（每一个边都是一对标签）。
 * 你可以假设没有重复的边会出现在 edges 中。
 * 由于所有的边都是无向边， [0, 1]和 [1, 0] 是相同的，因此不会同时出现在 edges 里。
 *
 * 示例 1:
 * 输入: n = 4, edges = [[1, 0], [1, 2], [1, 3]]
 *
 *         0
 *         |
 *         1
 *        / \
 *       2   3
 *
 * 输出: [1]
 *
 * 示例 2:
 * 输入: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
 *
 *      0  1  2
 *       \ | /
 *         3
 *         |
 *         4
 *         |
 *         5
 *
 * 输出: [3, 4]
 *
 * 说明:
 * 根据树的定义，树是一个无向图，其中任何两个顶点只通过一条路径连接。 换句话说，一个任何没有简单环路的连通图都是一棵树。
 * 树的高度是指根节点和叶子节点之间最长向下路径上边的数量。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-height-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode310 {
    /**
     * 先构建邻街表 然后按照每个点进行bfs
     * 这个会超时
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        LinkedList<Integer>[] graph = buildGraph(n, edges);
        if(graph == null || graph.length == 0){
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        int min = n;
        for(int i = 0; i < graph.length; i++){
            int height = getHeight(graph, i, new boolean[n]);
            if(height < min){
                min = height;
                result = new ArrayList<>();
                result.add(i);
            }else if(height == min){
                result.add(i);
            }
        }
        return result;
    }

    private int getHeight(LinkedList<Integer>[] graph, int root, boolean[] used){
        int height = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(root);
        used[root] = true;
        while (!queue.isEmpty()){
            height++;
            int size = queue.size();
            while (size > 0){
                int node = queue.poll();
                if(graph[node] != null){
                    for(Integer child : graph[node]){
                        if(!used[child]){
                            queue.add(child);
                            used[child] = true;
                        }
                    }
                }
                size--;
            }
        }
        return height;
    }

    private LinkedList<Integer>[] buildGraph(int n, int[][] edges){
        if(n == 0){
            return null;
        }
        LinkedList<Integer>[] graph = new LinkedList[n];
        for(int i = 0; i < edges.length; i++){
            //因为无向图 所以一个边要加两次
            if(graph[edges[i][0]] == null){
                graph[edges[i][0]] = new LinkedList<>();
            }
            graph[edges[i][0]].add(edges[i][1]);

            if(graph[edges[i][1]] == null){
                graph[edges[i][1]] = new LinkedList<>();
            }
            graph[edges[i][1]].add(edges[i][0]);
        }
        return graph;
    }

    /**
     * 题干的树的形状其实是提示 这个需要按照类似hahn算法
     * 一圈一圈剥离只有一条边的节点 每一圈记一个临时
     * 最后全剥离完 上一圈就是答案
     * 并且节点 不是一个就是两个 不会有别的 因为只要大于两个就能再剥离一圈
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findMinHeightTrees1(int n, int[][] edges) {
        LinkedList<Integer> result = new LinkedList<>();
        if(n == 1){
            result.add(0);
            return result;
        }

        LinkedList<Integer>[] graph = new LinkedList[n];
        for(int i = 0; i < edges.length; i++){
            //因为无向图 所以一个边要加两次
            if(graph[edges[i][0]] == null){
                graph[edges[i][0]] = new LinkedList<>();
            }
            graph[edges[i][0]].add(edges[i][1]);

            if(graph[edges[i][1]] == null){
                graph[edges[i][1]] = new LinkedList<>();
            }
            graph[edges[i][1]].add(edges[i][0]);
        }

        for (int i = 0; i < n; i++) {
            if (graph[i].size() == 1) {
                result.offer(i);
            }
        }
        while (n > 2){
            int size = result.size();
            n = n - size;
            while (size > 0){
                int node = result.poll();
                int temp = graph[node].get(0);
                Iterator<Integer> it = graph[temp].iterator();
                while (it.hasNext()){
                    if(it.next() == node){
                        it.remove();
                        break;
                    }
                }
                if(graph[temp].size()==1){
                    result.offer(temp);
                }
                size--;
            }
        }
        return result;
    }

    private HashSet<Integer>[] buildGraphSet(int n, int[][] edges){
        if(n == 0){
            return null;
        }
        HashSet<Integer>[] graph = new HashSet[n];
        for(int i = 0; i < edges.length; i++){
            //因为无向图 所以一个边要加两次
            if(graph[edges[i][0]] == null){
                graph[edges[i][0]] = new HashSet<>();
            }
            graph[edges[i][0]].add(edges[i][1]);

            if(graph[edges[i][1]] == null){
                graph[edges[i][1]] = new HashSet<>();
            }
            graph[edges[i][1]].add(edges[i][0]);
        }
        return graph;
    }

    public static void main(String[] args) {
        LeetCode310 leetCode310 = new LeetCode310();
        leetCode310.findMinHeightTrees1(4, new int[][]{{1,0},{1,2},{1,3}});
    }
}
