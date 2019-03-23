import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个 N 叉树，返回其节点值的后序遍历。
 * 例如，给定一个 3叉树 :
 *                    1
 *                  / | \
 *                 3  2  4
 *                / \
 *               5   6
 *
 * 返回其后序遍历: [5,6,3,2,4,1].
 * 说明: 递归法很简单，你可以使用迭代法完成此题吗?
 * 在真实的面试中遇到过这道题？
 */
public class LeetCode590 {
    /**
     * 先递归法
     * @param root
     * @return
     */
    public List<Integer> postorder(Node root) {
        if(root == null){
            return new ArrayList<>();
        }
        LinkedList<Integer> result = new LinkedList<>();
        if(root.children != null){
            for(Node node : root.children){
                result.addAll(postorder(node));
            }
        }
        result.add(root.val);
        return result;
    }

    /**
     * 非递归法 二叉树后序遍历（leetcode145）第二第三个方法都不太好用
     * 只能用逆序更新
     * @param root
     * @return
     */
    public List<Integer> postorder2(Node root) {
        if(root == null){
            return new ArrayList<>();
        }
        LinkedList<Integer> result = new LinkedList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            Node node = stack.pop();
            result.push(node.val);
            if(node.children != null){
                for(Node n : node.children){
                    if(n != null){
                        stack.push(n);
                    }
                }
            }
        }
        return result;
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
