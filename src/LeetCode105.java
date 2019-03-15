/**
 * 105. 从前序与中序遍历序列构造二叉树
 *
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * 注意:
 * 你可以假设树中没有重复的元素。
 * 例如，给出
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *             3
 *            / \
 *           9  20
 *             /  \
 *            15   7
 */
public class LeetCode105 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder != null && preorder.length > 0 && inorder != null && inorder.length > 0){
            return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
        }
        return null;
    }

    public TreeNode buildTree(int[] preorder, int startPre, int endPre, int[] inorder, int startIn, int endIn) {
        int rootVal = preorder[startPre];
        TreeNode root = new TreeNode(rootVal);
        if(startPre == endPre){
            return root;
        }
        int index = 0;
        for(int i = startIn; i <= endIn; i++){
            if(inorder[i] == rootVal){
                index = i;
            }
        }
        //没有左子树 startIn = index
        if(index == startIn){
            root.right = buildTree(preorder, startPre + 1, endPre, inorder, startIn + 1, endIn);
        }else if(index == endIn){
            root.left = buildTree(preorder, startPre + 1, endPre, inorder, startIn, endIn - 1);
        }else {
            root.left = buildTree(preorder, startPre + 1, startPre + index - startIn, inorder, startIn, index - 1);
            root.right = buildTree(preorder, startPre + index - startIn + 1, endPre, inorder, index + 1, endIn);
        }
        return root;
    }
}
