import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 树以及数组初始化一个树
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x){
        val = x;
    }


    /**
     * 递归方式
     * @param values
     * @param index
     * @return
     */
    public static TreeNode initTreeByRecursive(Integer[] values, int index){
        if(values == null || values.length == 0 || index >= values.length || values[index] == null){
            return null;
        }
        TreeNode root = new TreeNode(values[index]);
        root.left = initTreeByRecursive(values, 2 * index + 1);
        root.right = initTreeByRecursive(values, 2 * index + 2);
        return root;
    }

    /**
     * 循环方式
     * @param values
     * @return
     */
    public static TreeNode initTreeByLoop(Integer[] values){
        if(values == null || values.length == 0 || values[0] == null){
            return null;
        }
        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int index = 1;
        while (!queue.isEmpty()){
            TreeNode cur = queue.poll();
            if(cur != null){
                //左节点
                if(values[index] != null){
                    TreeNode left = new TreeNode(values[index]);
                    cur.left = left;
                    queue.offer(left);
                }else {
                    queue.offer(null);
                }
                //右节点
                if(index + 1 < values.length && values[index + 1] != null){
                    TreeNode right = new TreeNode(values[index + 1]);
                    cur.right = right;
                    queue.offer(right);
                }else {
                    queue.offer(null);
                }
            }
            index += 2;
            if(index >= values.length){
                break;
            }
        }
        return root;
    }



    public static void main(String[] args) {
        Integer[] values1 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Integer[] values2 = {3, 9, 20, null, null, 15};
        TreeNode root1 = TreeNode.initTreeByRecursive(values1, 0);
        TreeNode root2 = TreeNode.initTreeByRecursive(values2, 0);

        TreeNode root3 = TreeNode.initTreeByLoop(values1);
        TreeNode root4 = TreeNode.initTreeByLoop(values2);

        LeetCode102 leetCode102 = new LeetCode102();
        List<List<Integer>> result1 = leetCode102.levelOrder2(root1);
        List<List<Integer>> result2 = leetCode102.levelOrder2(root2);

        List<List<Integer>> result3 = leetCode102.levelOrder2(root3);
        List<List<Integer>> result4 = leetCode102.levelOrder2(root4);

        System.out.println("result1 = : ");
        for(List<Integer> r : result1){
            for(Integer i : r){
                System.out.print(i + " ");
            }
            System.out.println();
        }
        System.out.println("result2 = : ");
        for(List<Integer> r : result2){
            for(Integer i : r){
                System.out.print(i + " ");
            }
            System.out.println();
        }

        System.out.println("result1 Loop = : ");
        for(List<Integer> r : result3){
            for(Integer i : r){
                System.out.print(i + " ");
            }
            System.out.println();
        }
        System.out.println("result2 Loop = : ");
        for(List<Integer> r : result4){
            for(Integer i : r){
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
