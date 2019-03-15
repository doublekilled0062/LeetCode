import java.util.Stack;

/**
 * 100. 相同的树
 *
 * 给定两个二叉树，编写一个函数来检验它们是否相同。
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 *
 * 示例 1:
 * 输入:       1         1
 *           / \       / \
 *          2   3     2   3
 * [1,2,3],   [1,2,3]
 * 输出: true
 *
 * 示例 2:
 * 输入:      1          1
 *           /           \
 *          2             2
 * [1,2],     [1,null,2]
 * 输出: false
 *
 * 示例 3:
 * 输入:       1         1
 *           / \       / \
 *          2   1     1   2
 * [1,2,1],   [1,1,2]
 * 输出: false
 *
 * */
public class LeetCode100 {

    /**
     * 递归方式
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null){
            return true;
        }
        if(p != null && q != null && p.val == q.val){
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
        return false;
    }

    /**
     * 非递归方式
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree2(TreeNode p, TreeNode q) {
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
                TreeNode pLeft = pNode.left;
                TreeNode qLeft = qNode.left;
                if(pLeft != null && qLeft != null){
                    pStack.push(pLeft);
                    qStack.push(qLeft);
                }if((pLeft == null && qLeft != null) || (pLeft != null && qLeft == null)){
                    return false;
                }
                TreeNode pRight = pNode.right;
                TreeNode qRight = qNode.right;
                if(pRight != null && qRight != null){
                    pStack.push(pRight);
                    qStack.push(qRight);
                } else if ((pRight == null && qRight != null) || (pRight != null && qRight == null)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
