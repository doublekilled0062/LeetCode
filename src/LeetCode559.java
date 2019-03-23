import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 559. N叉树的最大深度
 * 给定一个 N 叉树，找到其最大深度。
 * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
 * 例如，给定一个 3叉树 :
 *
 *                    1
 *                  / | \
 *                 3  2  4
 *                / \
 *               5   6
 *
 * 我们应返回其最大深度，3。
 * 说明:
 * 树的深度不会超过 1000。
 * 树的节点总不会超过 5000。
 */
public class LeetCode559 {

    /**
     * 递归方式
     * @param root
     * @return
     */
    public int maxDepth(Node root) {
        if(root == null){
            return 0;
        }
        if(root.children == null){
            return 1;
        }
        int maxDepth = 0;
        for(Node node : root.children){
            maxDepth = Math.max(maxDepth, maxDepth(node));
        }
        return maxDepth + 1;
    }

    /**
     * 非递归方式 按层打印计数
     * 但是速度会比递归慢
     * @param root
     * @return
     */
    public int maxDepth2(Node root) {
        if(root == null){
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int layerLen = 1;
        int maxdepth = 0;
        while (!queue.isEmpty()){
            Node node = queue.poll();
            layerLen--;
            if(node.children != null){
                for(Node c : node.children){
                    queue.offer(c);
                }
            }
            if(layerLen == 0){
                layerLen = queue.size();
                maxdepth++;
            }
        }
        return maxdepth;
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val,List<Node> _children) {
            val = _val;
            children = _children;
        }
    };
}
