import java.util.*;

/**
 * 103. 二叉树的锯齿形层次遍历
 *
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *                  3
 *                 / \
 *                9  20
 *                /  \
 *               15   7
 * 返回锯齿形层次遍历如下：
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 */
public class LeetCode103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int order = 0;
        while (!stack.isEmpty()){
            int count = stack.size();
            List<Integer> nodeList = new ArrayList<>();
            Stack<TreeNode> newStack = new Stack<>();
            while (count > 0){
                TreeNode node = stack.pop();
                nodeList.add(node.val);
                if((order & 1) == 0){
                    //偶数
                    if(node.left != null){
                        newStack.push(node.left);
                    }
                    if(node.right != null){
                        newStack.push(node.right);
                    }
                }else {
                    if(node.right != null){
                        newStack.push(node.right);
                    }
                    if(node.left != null){
                        newStack.push(node.left);
                    }
                }
                count--;
            }
            order++;
            result.add(nodeList);
            stack = newStack;
        }
        return result;
    }

    /**
     * 双栈
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Stack<TreeNode> stack0 = new Stack<>();
        Stack<TreeNode> stack1 = new Stack<>();
        stack0.push(root);
        int order = 0;
        while (((order & 1) == 0 && !stack0.isEmpty()) || ((order & 1) == 1 && !stack1.isEmpty())){
            List<Integer> nodeList = new ArrayList<>();
            if((order & 1) == 0){
                int count = stack0.size();
                while (count > 0){
                    TreeNode node = stack0.pop();
                    nodeList.add(node.val);
                    //偶数
                    if(node.left != null){
                        stack1.push(node.left);
                    }
                    if(node.right != null){
                        stack1.push(node.right);
                    }
                    count--;
                }
            }else {
                int count = stack1.size();
                while (count > 0){
                    TreeNode node = stack1.pop();
                    nodeList.add(node.val);
                    //奇数
                    if(node.right != null){
                        stack0.push(node.right);
                    }
                    if(node.left != null){
                        stack0.push(node.left);
                    }
                    count--;
                }
            }
            order++;
            result.add(nodeList);
        }
        return result;
    }
}
