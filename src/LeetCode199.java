import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 199. 二叉树的右视图
 *
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 * 示例:
 *
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1, 3, 4]
 * 解释:
 *
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-right-side-view
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode199 {
    /**
     * 按层打印 拿每层的对列的最后一个
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        if(root == null){
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size > 0){
                TreeNode node = queue.poll();
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }

                if(--size == 0){
                    res.add(node.val);
                }
            }
        }
        return res;
    }

    /**
     * 递归方式
     * @param root
     * @return
     */
    public List<Integer> rightSideView1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }

        rightSideView(res, 0, root);

        return res;
    }

    private void rightSideView(List<Integer> res, int pos, TreeNode node){
        if(res.size() == pos){
            res.add(node.val);
        }

        if(node.right != null){
            rightSideView(res, pos + 1, node.right);
        }
        if(node.left != null){
            rightSideView(res, pos + 1, node.left);
        }
    }
}
