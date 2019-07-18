import java.util.LinkedList;
import java.util.Stack;

/**
 * 173. 二叉搜索树迭代器
 *
 * 实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。
 * 调用 next() 将返回二叉搜索树中的下一个最小的数。
 *
 * 示例：
 *             7
 *            / \
 *           3  15
 *             /  \
 *            9   20
 *
 * BSTIterator iterator = new BSTIterator(root);
 * iterator.next();    // 返回 3
 * iterator.next();    // 返回 7
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 9
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 15
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 20
 * iterator.hasNext(); // 返回 false
 *
 * 提示：
 *
 * next() 和 hasNext() 操作的时间复杂度是 O(1)，并使用 O(h) 内存，其中 h 是树的高度。
 * 你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 中至少存在一个下一个最小的数。
 *
 */
public class LeetCode173 {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    class BSTIterator {
        //保存节点数少 stack比linkedlist可能好点
        Stack<TreeNode> stack = new Stack<>();
        public BSTIterator(TreeNode root) {
            if(root == null){
                return;
            }
            stack.push(root);
            TreeNode left = root.left;
            while (left != null){
                stack.push(left);
                left = left.left;
            }
        }

        /** @return the next smallest number */
        public int next() {
            //结果是stack的栈顶 如果栈顶元素还有右子树 找右子树最小的
            if(stack.isEmpty()){
                return -1;
            }
            TreeNode res = stack.pop();
            //把res的后继节点加入到栈
            TreeNode node = res.right;
            while (node != null){
                stack.push(node);
                node = node.left;
            }
            return res.val;
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            if(stack.isEmpty()){
                return false;
            }
            return true;
        }
    }

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
}
