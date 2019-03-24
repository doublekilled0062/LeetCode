import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 589. N叉树的前序遍历
 *
 * 给定一个 N 叉树，返回其节点值的前序遍历。
 * 例如，给定一个 3叉树 :
 *                    1
 *                  / | \
 *                 3  2  4
 *                / \
 *               5   6
 *
 * 返回其前序遍历: [1,3,5,6,2,4]。
 * 说明: 递归法很简单，你可以使用迭代法完成此题吗?
 */
public class LeetCode589 {
    /**
     * 递归的不写了 直接写非递归
     * @param root
     * @return
     */
    public List<Integer> preorder(Node root) {
        if(root == null){
            return new ArrayList<>();
        }
        List<Integer> result = new LinkedList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            Node node = stack.pop();
            result.add(node.val);
            if(node.children != null && !node.children.isEmpty()){
                for(int i = node.children.size() - 1; i >= 0; i--){
                    stack.push(node.children.get(i));
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
