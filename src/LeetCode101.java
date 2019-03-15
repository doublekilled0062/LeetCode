import java.util.Stack;

/**
 * 101. 对称二叉树
 *
 * 给定一个二叉树，检查它是否是镜像对称的。
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *             1
 *            / \
 *           2   2
 *          / \ / \
 *         3  4 4  3
 *
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *            1
 *           / \
 *          2   2
 *          \   \
 *          3    3
 * 说明:
 * 如果你可以运用递归和迭代两种方法解决这个问题，会很加分。
 */
public class LeetCode101 {
    /**
     * 递归方式
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if(root == null){
            return true;
        }
        return isTreeSymmetric(root.left, root.right);
    }

    private boolean isTreeSymmetric(TreeNode p, TreeNode q) {
        if(p == null && q == null){
            return true;
        }
        if(p != null && q != null){
            return p.val == q.val && isTreeSymmetric(p.left, q.right) && isTreeSymmetric(p.right, q.left);
        }
        return false;
    }

    /**
     * 非递归方式
     * @param root
     * @return
     */
    public boolean isSymmetric2(TreeNode root) {
        if(root == null){
            return true;
        }
        return isTreeSymmetric3(root.left, root.right);
    }

    /**
     * 两个栈
     * @param p
     * @param q
     * @return
     */
    private boolean isTreeSymmetric2(TreeNode p, TreeNode q) {
        if(p == null && q == null){
            return true;
        }
        if(p != null && q != null){
            Stack<TreeNode> pStack = new Stack<>();
            Stack<TreeNode> qStack = new Stack<>();
            pStack.push(p);
            qStack.push(q);
            while (!pStack.isEmpty() && !qStack.isEmpty()){
                TreeNode pNode = pStack.pop();
                TreeNode qNode = qStack.pop();
                if(pNode.val != qNode.val){
                    return false;
                }
                if(pNode.left != null && qNode.right != null){
                    pStack.push(pNode.left);
                    qStack.push(qNode.right);
                }else if((pNode.left != null && qNode.right == null) || (pNode.left == null && qNode.right != null)){
                    return false;
                }
                if(pNode.right != null && qNode.left != null){
                    pStack.push(pNode.right);
                    qStack.push(qNode.left);
                }else if((pNode.right == null && qNode.left != null) || (pNode.right != null && qNode.left == null)){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 一个栈
     * @param p
     * @param q
     * @return
     */
    private boolean isTreeSymmetric3(TreeNode p, TreeNode q) {
        if(p == null && q == null){
            return true;
        }
        if(p != null && q != null){
            Stack<TreeNode> stack = new Stack<>();
            stack.push(p);
            stack.push(q);
            while (!stack.isEmpty()){
                TreeNode pNode = stack.pop();
                TreeNode qNode = stack.pop();
                if(pNode == null && qNode == null){
                    continue;
                } else if(pNode != null && qNode != null){
                    if(pNode.val != qNode.val){
                        return false;
                    }
                    stack.push(pNode.left);
                    stack.push(qNode.right);
                    stack.push(pNode.right);
                    stack.push(qNode.left);
                }else {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 一个栈或者队列的代码精简方式 但是需要遍历两边
     * @param root
     * @return
     */
    public boolean isSymmetric3(TreeNode root) {
        if(root == null){
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode pNode = stack.pop();
            TreeNode qNode = stack.pop();
            if(pNode == null && qNode == null){
                continue;
            } else if(pNode != null && qNode != null){
                if(pNode.val != qNode.val){
                    return false;
                }
                stack.push(pNode.left);
                stack.push(qNode.right);
                stack.push(pNode.right);
                stack.push(qNode.left);
            }else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode rootNode = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(3);
        TreeNode node6 = new TreeNode(4);

        rootNode.left = node1;
        rootNode.right = node2;

        node1.left = node3;
        node1.right = node4;

        node2.left = node5;
        node2.right = node6;

        LeetCode101 leetcode101 = new LeetCode101();
        System.out.println(leetcode101.isSymmetric2(rootNode));
    }
}
