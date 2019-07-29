import java.util.*;

/**
 * 207. 课程表
 *
 * 现在你总共有 n 门课需要选，记为 0 到 n-1。
 * 在选修某些课程之前需要一些先修课程。 
 * 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
 * 给定课程总量以及它们的先决条件，判断是否可能完成所有课程的学习？
 *
 * 示例 1:
 * 输入: 2, [[1,0]]
 * 输出: true
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
 *
 * 示例 2:
 * 输入: 2, [[1,0],[0,1]]
 * 输出: false
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；
 * 并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
 *
 * 说明:
 * 输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法。
 * 你可以假定输入的先决条件中没有重复的边。
 *
 * 提示:
 * 这个问题相当于查找一个循环是否存在于有向图中。如果存在循环，则不存在拓扑排序，因此不可能选取所有课程进行学习。
 * 通过 DFS 进行拓扑排序 - 一个关于Coursera的精彩视频教程（21分钟），介绍拓扑排序的基本概念。
 * 拓扑排序也可以通过 BFS 完成。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/course-schedule
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode207 {
    /**
     * 拓扑排序 kahn算法
     * 构建邻接表
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites == null || prerequisites.length == 0 ){
            return true;
        }

        //记录已经学的课程数
        int total = 0;

        //入度表 如果有入度为0的点 就是第一门课的起点
        int[] inDegree = new int[numCourses];

        //邻接链表
        List<Integer>[] graph = new ArrayList[numCourses];
        for(int i = 0; i < prerequisites.length; i++){
            //格式 [1, 0] 为 1 <- 0 构造邻接表
            if(graph[prerequisites[i][1]] == null){
                graph[prerequisites[i][1]] = new ArrayList<>();
            }
            graph[prerequisites[i][1]].add(prerequisites[i][0]);
            //入度值加1
            inDegree[prerequisites[i][0]] += 1;
        }

        //array实现的比较快
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i = 0; i < numCourses; i++){
            if(inDegree[i] == 0){
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()){
            int node = queue.poll();
            total++;
            List<Integer> list = graph[node];
            if(list != null && !list.isEmpty()){
                for(int v : list){
                    inDegree[v] -= 1;
                    if(inDegree[v] == 0){
                        queue.offer(v);
                    }
                }
            }
        }

        return total == numCourses;
    }

    /**
     * dfs的解法 构建逆邻接矩阵 从后往前找环
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish1(int numCourses, int[][] prerequisites) {

        if (prerequisites == null || prerequisites.length == 0) {
            return true;
        }

        List<Integer>[] graph = new ArrayList[numCourses];
        for(int i = 0; i < prerequisites.length; i++){
            //格式 [1, 0] 为 1 <- 0 构造逆邻接表
            if(graph[prerequisites[i][0]] == null){
                graph[prerequisites[i][0]] = new ArrayList<>();
            }
            graph[prerequisites[i][0]].add(prerequisites[i][1]);
        }

        boolean[] used = new boolean[numCourses];
        boolean[] temp = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(used, temp, graph, i)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(boolean[] used, boolean[] temp, List<Integer>[] graph, int curNode) {
        if (temp[curNode]) {
            //找到了环
            return false;
        }
        if (used[curNode]) {
            //已经遍历过
            return true;
        }
        used[curNode] = true;
        if(graph[curNode] == null || graph[curNode].isEmpty()){
            return true;
        }
        //回溯
        temp[curNode] = true;
        for (int nextNode : graph[curNode]) {
            if (!dfs(used, temp, graph, nextNode)) {
                return false;
            }
        }
        temp[curNode] = false;
        return true;
    }

    public static void main(String[] args) {
        LeetCode207 leetCode207 = new LeetCode207();
        System.out.println(leetCode207.canFinish1(2, new int[][]{{1,0}}));
    }
}
