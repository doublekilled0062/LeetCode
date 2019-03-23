/**
 * 1008. 先序遍历构造二叉树
 *
 * 返回与给定先序遍历 preorder 相匹配的二叉搜索树（binary search tree）的根结点。
 * (回想一下，二叉搜索树是二叉树的一种，其每个节点都满足以下规则，对于 node.left 的任何后代，值总 < node.val，而 node.right 的任何后代，值总 > node.val。
 * 此外，先序遍历首先显示节点的值，然后遍历 node.left，接着遍历 node.right。）
 *
 * 示例：
 * 输入：[8,5,1,7,10,12]
 * 输出：[8,5,10,1,7,null,12]
 *
 *                      8
 *                     / \
 *                    5  10
 *                   / \  \
 *                  1  7  12
 *
 * 提示：
 * 1 <= preorder.length <= 100
 * 先序 preorder 中的值是不同的。
 */
public class LeetCode1008 {
    /**
     * 递归
     * @param preorder
     * @return
     */
    public TreeNode bstFromPreorder(int[] preorder) {
        if(preorder == null || preorder.length == 0){
            return null;
        }
        return bstFromPreorder(preorder, 0, preorder.length - 1);
    }

    private TreeNode bstFromPreorder(int[] preorder, int start, int end) {
        if(start > end){
            return null;
        }
        TreeNode root = new TreeNode(preorder[start]);
        if(start == end){
            return root;
        }
        //最少两个数
        int maxIndex = findIndex(preorder, start, end, root.val);
        if(maxIndex != -1){
            //找到
            TreeNode right = bstFromPreorder(preorder, maxIndex, end);
            TreeNode left = bstFromPreorder(preorder, start + 1, maxIndex - 1);
            root.left = left;
            root.right = right;
        }else {
            //没找到
            TreeNode left = bstFromPreorder(preorder, start + 1, end);
            root.left = left;
        }
        return root;
    }

    /**
     * 找到第一个大于target的索引
     * @param preorder
     * @param start
     * @param end
     * @param target
     * @return
     */
    private int findIndex(int[] preorder, int start, int end, int target){
        for(int i = start; i <= end; i++){
            if(preorder[i] > target){
                return i;
            }
        }
        return -1;
    }

}
