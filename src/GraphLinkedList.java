import java.util.LinkedList;
import java.util.List;

/**
 * 邻接表
 */
public class GraphLinkedList extends Graph{

    public LinkedList<Edge>[] adj;  //邻接表数组
    public LinkedList<Edge>[] inverseAdj; //逆邻接表

    public Vertex[] vertexes ;// 顶点数组

    public GraphLinkedList(int v){
        this.v = v;
        this.vertexes = new Vertex[this.v];
        adj = new LinkedList[v];
        for(int i = 0; i < adj.length; i++){
            adj[i] = new LinkedList<Edge>();
        }
        inverseAdj = new LinkedList[v];
        for(int i = 0; i < inverseAdj.length; i++){
            inverseAdj[i] = new LinkedList<Edge>();
        }
    }

    public void addVetex(int id, int x, int y) {
        vertexes[id] = new Vertex(id, x, y);
    }

    public void addEdge(int s, int t, int w){
        if(s >= adj.length){
            return;
        }
        Edge edge = new Edge(s, t, w);
        adj[s].add(edge);
        inverseAdj[t].add(edge);
    }

    public int manhattan(Vertex v1, Vertex v2) {
        return Math.abs(v1.x - v2.x) + Math.abs(v1.y - v2.y);
    }

    public void kahn(){
        int[] inDegree = new int[v];
        for(int i = 0; i < v; i++){
            for(int j = 0; j < adj[i].size(); j++){
                int t = adj[i].get(j).tid;
                inDegree[t] = inDegree[t] + 1;
            }
        }
        LinkedList<Integer> queue = new LinkedList<>();
        for(int i = 0; i < v; i++){
            if(inDegree[i] == 0){
                queue.add(i);
            }
        }
        while (!queue.isEmpty()){
            int i = queue.poll();
            System.out.print(i + " ");
            for(int j = 0; j < adj[i].size(); j++){
                int t = adj[i].get(j).tid;
                inDegree[t] = inDegree[t] - 1;
                if(inDegree[t] == 0){
                    queue.add(t);
                }
            }
        }
    }

    /**
     * 顶点s到t的路径 单源最短路径
     * @param s
     * @param t
     * @return
     */
    public List<Integer> dijkstra(int s, int t){
        //下标为当前顶点的 值为上一个顶点
        int[] road = new int[this.v];
        Vertex[] vertexes = new Vertex[this.v];
        for (int i = 0; i < this.v; ++i) {
            vertexes[i] = new Vertex(i, Integer.MAX_VALUE);
        }

        MinHeap queue = new MinHeap(this.v);
        //标记是否在队列里
        boolean[] inQueue = new boolean[this.v];
        vertexes[s].dist = 0;
        vertexes[s].f = 0;
        queue.insert(vertexes[s]);
        inQueue[s] = true;

        while (!queue.isEmpty()) {
            Vertex v = queue.remove();
            if (v.id == t) {
                //循环结束
                break;
            }
            for (int i = 0; i < adj[v.id].size(); i++) {
                //去该点相连的边 更新和起点的距离
                Edge e = adj[v.id].get(i);
                Vertex next = vertexes[e.tid];
                //看距离是否小更新
                if (v.dist + e.w < next.dist) { // 更新 next 的 dist
                    next.dist = v.dist + e.w;
                    next.f = next.dist;
                    road[next.id] = v.id;
                    if (inQueue[next.id] == true) {
                        queue.update(next); // 更新队列中的 dist 值
                    } else {
                        queue.insert(next);
                        inQueue[next.id] = true;
                    }
                }
            }
        }
        LinkedList<Integer> result = new LinkedList<>();
        int i = t;
        while (i != s){
            result.push(i);
            i = road[i];
        }
        result.push(s);
        return result;
    }

    /**
     * 顶点s到t的距离 A*算法
     * @param s
     * @param t
     */
    public List<Integer> aStar(int s, int t) {
        //下标为当前顶点的 值为上一个顶点
        int[] road = new int[this.v];
        MinHeap queue = new MinHeap(this.v);
        //是否进入过队列
        boolean[] inQueue = new boolean[this.v];
        vertexes[s].dist = 0;
        vertexes[s].f = 0;
        queue.insert(vertexes[s]);
        inQueue[s] = true;
        while (!queue.isEmpty()) {
            Vertex v = queue.remove();
            for (int i = 0; i < adj[v.id].size(); i++) {
                //取和顶点相连的边 然后更新距离和启发函数
                Edge e = adj[v.id].get(i);
                Vertex next = vertexes[e.tid];
                if (v.dist + e.w < next.dist) {
                    next.dist = next.dist + e.w;
                    next.f = next.dist + manhattan(next, vertexes[t]);
                    road[next.id] = v.id;
                    if (inQueue[next.id] == true) {
                        queue.update(next);
                    } else {
                        queue.insert(next);
                        inQueue[next.id] = true;
                    }
                }
                if (next.id == t) {
                    //遇到t就结束
                    queue.clear();
                    break;
                }
            }
        }

        LinkedList<Integer> result = new LinkedList<>();
        int i = t;
        while (i != s){
            result.push(i);
            i = road[i];
        }
        result.push(s);
        return result;
    }

    protected class Edge{
        public int sid;     //其实顶点编号
        public int tid;     //终点编号
        public int w;
        public Edge(int sid, int tid, int w){
            this.tid = tid;
            this.sid = sid;
            this.w = w;
        }
    }

    public class Vertex{
        public int id;
        public int dist; //最短路径用 从起始顶点到这个顶点的距离

        public int f; // f(i) = g(i) + h(i) g(i)为dist h(i)为曼哈顿距离

        public int x;//坐标
        public int y;//坐标

        public Vertex(int id, int dist){
            this.id = id;
            this.dist = dist;
        }

        public Vertex(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.f = Integer.MAX_VALUE;
            this.dist = Integer.MAX_VALUE;
        }
    }

    public class MinHeap{
        private Vertex[] lists;
        private int index = 1;      //索引开始为1 根据子节点查找父节点只需要除二 不用判断奇偶

        public MinHeap(int len){
            lists = new Vertex[len + 1];
        }

        public Vertex insert(Vertex node){
            if(index == lists.length){
                return lists[1];
            }
            int pos = index;
            lists[index++] = node;
            //堆化
            while (pos > 1){
                int midPos = pos >> 1;
                if(lists[pos].f < lists[midPos].f){
                    Vertex tmp = lists[midPos];
                    lists[midPos] = lists[pos];
                    lists[pos] = tmp;
                    pos = midPos;
                }else {
                    break;
                }
            }
            return lists[1];
        }

        public Vertex remove(){
            Vertex result = lists[1];
            lists[1] = lists[index - 1];
            lists[index - 1] = null;
            index--;
            int pos = 1;
            while (pos <= (index - 1)/2){
                int minPos = pos;
                int minValue = lists[pos].f;
                if(lists[pos].f > lists[pos * 2].f){
                    minPos = pos * 2;
                    minValue = lists[pos * 2].f;
                }
                if(index - 1 >= 2 * pos + 1){
                    //右节点存在
                    if(minValue > lists[2 * pos + 1].f){
                        minPos = 2 * pos + 1;
                        minValue = lists[2 * pos + 1].f;
                    }
                }
                //和minPos互换
                if(pos != minPos){
                    Vertex tmp = lists[pos];
                    lists[pos] = lists[minPos];
                    lists[minPos] = tmp;
                    pos = minPos;
                }else {
                    break;
                }
            }
            return result;
        }

        public void update(Vertex vertex){
            int pos = 1;
            for(int i = 1; i <= index - 1; i++){
                if(lists[i].id == vertex.id){
                    //id相等更新 然后
                    lists[i] = vertex;
                    pos = i;
                    break;
                }
            }

            //堆化
            while (pos > 1){
                int midPos = pos >> 1;
                if(lists[pos].dist < lists[midPos].f){
                    Vertex tmp = lists[midPos];
                    lists[midPos] = lists[pos];
                    lists[pos] = tmp;
                    pos = midPos;
                }else {
                    break;
                }
            }
        }

        public boolean isEmpty(){
            return index <= 1;
        }

        public void clear(){
            lists = new Vertex[lists.length];
            index = 1;
        }
    }

    public static void main(String[] args) {
        GraphLinkedList graph = new GraphLinkedList(6);
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 2, 5);
        graph.addEdge(0, 3, 6);
        graph.addEdge(2, 3, 7);
        graph.addEdge(2, 4, 1);
        graph.addEdge(2, 5, 2);
        graph.addEdge(3, 1, 3);
        graph.addEdge(4, 3, 4);
        graph.addEdge(4, 5, 8);
        graph.addEdge(5, 3, 4);

        System.out.println("拓扑排序：");
        graph.kahn();

        //二叉堆测试
        GraphLinkedList.MinHeap minHeap = graph.new MinHeap(6);
        minHeap.insert(graph.new Vertex(0, 3));
        minHeap.insert(graph.new Vertex(1, 2));
        minHeap.insert(graph.new Vertex(2, 1));
        minHeap.insert(graph.new Vertex(3, 0));
        minHeap.insert(graph.new Vertex(4, 5));
        minHeap.insert(graph.new Vertex(5, 6));

        System.out.println();
        System.out.println("二叉堆测试：");
        System.out.print(minHeap.remove().dist + " ");
        minHeap.update(graph.new Vertex(1, 4));
        while (!minHeap.isEmpty()){
            System.out.print(minHeap.remove().dist + " ");
        }

        //最短路
        GraphLinkedList graph1 = new GraphLinkedList(6);
        graph1.addEdge(0, 1, 10);
        graph1.addEdge(0, 4, 15);
        graph1.addEdge(1, 2, 15);
        graph1.addEdge(1, 3, 2);
        graph1.addEdge(2, 5, 5);
        graph1.addEdge(3, 2, 1);
        graph1.addEdge(3, 5, 12);
        graph1.addEdge(4, 5, 10);

        graph1.addVetex(0, 0, 0);
        graph1.addVetex(1, 0, 1);
        graph1.addVetex(2, 5, 10);
        graph1.addVetex(3, 10, 15);
        graph1.addVetex(4, 10, 18);
        graph1.addVetex(5, 10, 20);

        List<Integer> list = graph1.dijkstra(0, 5);
        System.out.println();
        System.out.println("最短路测试：");
        for(int i = 0; i < list.size(); i++){
            System.out.print(list.get(i) + " ");
        }

        List<Integer> list1 = graph1.aStar(0, 5);
        System.out.println();
        System.out.println("A*测试：");
        for(int i = 0; i < list1.size(); i++){
            System.out.print(list1.get(i) + " ");
        }
    }
}
