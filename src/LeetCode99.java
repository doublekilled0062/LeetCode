import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 99. 恢复二叉搜索树
 *
 * 二叉搜索树中的两个节点被错误地交换。
 * 请在不改变其结构的情况下，恢复这棵树。
 * 示例 1:
 * 输入: [1,3,null,null,2]
 *          1
 *         /
 *        3
 *         \
 *          2
 * 输出: [3,1,null,null,2]
 *          3
 *         /
 *        1
 *         \
 *          2
 * 示例 2:
 * 输入: [3,1,4,null,null,2] 中序遍历 1 3 2 4
 *          3
 *         / \
 *        1   4
 *           /
 *          2
 * 输出: [2,1,4,null,null,3] 中序遍历 1 2 3 4
 *          2
 *         / \
 *        1   4
 *           /
 *          3
 * 进阶:
 * 使用 O(n) 空间复杂度的解法很容易实现。
 * 你能想出一个只使用常数空间的解决方案吗？
 */
public class LeetCode99 {

    private TreeNode preNode = null;
    private TreeNode p1 = null;
    private TreeNode p2 = null;

    /**
     * 递归 空间复杂度不符合
     * @param root
     */
    public void recoverTree(TreeNode root) {
        midOrder(root);
        swap(p1, p2);
    }

    private void midOrder(TreeNode root) {
        if (root == null) return;
        midOrder(root.left);

        // 找到第一个逆序节点
        if (p1 == null && preNode != null && preNode.val > root.val){
            //第一个逆序的数据对
            p1 = preNode;
        }
        if (p1 != null && preNode != null && preNode.val > root.val){
            //第二个逆序的数据对 有可能和第一个是一对 有可能在后面
            p2 = root;
        }

        preNode = root;
        midOrder(root.right);
    }

    /**
     * 借助栈 这个复杂度不符合O(1)
     * 递归空间复杂度也不符合 遍历存TreeNode空间复杂度也不符合
     * 解题思路就是找1或者2个逆序对 交换第一个的大值和最后一个的小值
     * @param root
     */
    public void recoverTree2(TreeNode root) {
        TreeNode preNode = null;
        TreeNode p1 = null;
        TreeNode p2 = null;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode treeNode = root;
        while (treeNode != null || !stack.isEmpty()){
            if(treeNode != null){
                stack.add(treeNode);
                treeNode = treeNode.left;
            }else {
                TreeNode node = stack.pop();
                if (p1 == null && preNode != null && preNode.val > node.val){
                    //第一个逆序的数据对
                    p1 = preNode;
                }
                if (p1 != null && preNode != null && preNode.val > node.val){
                    //第二个逆序的数据对 有可能和第一个是一对 有可能在后面
                    p2 = node;
                }
                preNode = node;
                treeNode = node.right;
            }
        }
        swap(p1, p2);
    }

    //交换两个节点
    private void swap(TreeNode n1, TreeNode n2) {
        if (n1 != null && n2 != null) {
            int temp = n1.val;
            n1.val = n2.val;
            n2.val = temp;
        }
    }

    /**
     * 用非递归Morris中序遍历 倒序的相邻两个节点（有可能一对，有可能两对）
     * 交换第一对的大值 和 最后一对的小值
     * 然后交换这两个节点
     * 复杂度符合
     * @param root
     */
    public void recoverTree3(TreeNode root) {
        TreeNode node = root;
        TreeNode preNode = null;
        TreeNode p1 = null;
        TreeNode p2 = null;
        while (node != null) {
            if(node.left != null){
                //前面判断保证一定有左孩子 所以前驱节点一定在左孩子或者左孩子的底右孩子 而不是空或者其父节点
                TreeNode pre = getPredecessor(node);
                if (pre.right == null) {
                    pre.right = node;
                    node = node.left;
                    continue;
                }else if (pre.right == node) {
                    pre.right = null;
                }
            }
            //当左子树为null，或者pre.right为root时，先判断before节点和root节点是否倒序，然后再遍历右子树
            if (p1 == null && preNode != null && preNode.val > node.val){
                //第一个逆序的数据对
                p1 = preNode;
            }
            if (p1 != null && preNode != null && preNode.val > node.val){
                //第二个逆序的数据对 有可能和第一个是一对 有可能在后面
                p2 = node;
            }
            preNode = node;
            //遍历到该值node 然后转向右子树
            node = node.right;
        }
        swap(p1, p2);
    }

    /**
     * 获得有左孩子的节点的 前驱节点
     * pre的right有特殊处理 会指向root 所以要判断一下
     * @param root
     * @return
     */
    private TreeNode getPredecessor(TreeNode root) {
        TreeNode pre = null;
        if (root.left != null) {
            pre = root.left;
            while (pre.right != null && pre.right != root) {
                pre = pre.right;
            }
        }
        return pre;
    }

    public static void main(String[] args) {
        LeetCode99 leetCode99 = new LeetCode99();
        leetCode99.recoverTree(TreeNode.initTreeByLoop(new Integer[]{3,1,4,null,null,2}));
    }
}
