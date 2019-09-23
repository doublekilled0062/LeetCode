import java.util.*;

/**
 * 297. 二叉树的序列化与反序列化
 *
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，
 * 同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，
 * 你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 *
 * 示例: 
 * 你可以将以下二叉树：
 *
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 *
 * 序列化为 "[1,2,3,null,null,4,5]"
 * 提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。
 * 你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 *
 * 说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode297 {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    /**
     * 掏出以前的陈年老代码
     * 不过这个是按打印所有二叉树的节点
     * 对于稀疏二叉树性能比较差 超时了
     */
    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if(root == null){
                return null;
            }
            StringBuilder result = new StringBuilder();

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int nodeLen = 1;
            boolean isEnd = true;
            StringBuilder builder = new StringBuilder();
            while (!queue.isEmpty()){
                TreeNode node = queue.poll();
                if(node != null){
                    builder.append(node.val).append(',');
                    if(node.left != null){
                        queue.offer(node.left);
                        isEnd = false;
                    }else {
                        queue.offer(null);
                    }
                    if(node.right != null){
                        queue.offer(node.right);
                        isEnd = false;
                    }else {
                        queue.offer(null);
                    }
                }else {
                    builder.append("null,");
                    queue.offer(null);
                    queue.offer(null);
                }

                nodeLen--;
                if(nodeLen == 0){
                    result.append(builder);
                    nodeLen = queue.size();
                    builder = new StringBuilder();
                    if(isEnd){
                        break;
                    }
                    isEnd = true;
                }
            }
            return result.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if(data == null){
                return null;
            }
            String[] strs = data.substring(0, data.length() - 1).split(",");
            TreeNode root = new TreeNode(Integer.parseInt(strs[0]));
            if(strs.length == 1){
                return root;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int index = 1;
            while (!queue.isEmpty()){
                TreeNode cur = queue.poll();
                if(cur != null){
                    //左节点
                    if(!strs[index].equals("null")){
                        TreeNode left = new TreeNode(Integer.parseInt(strs[index]));
                        cur.left = left;
                        queue.offer(left);
                    }else {
                        queue.offer(null);
                    }
                    //右节点
                    if(index + 1 < strs.length && !strs[index + 1].equals("null")){
                        TreeNode right = new TreeNode(Integer.parseInt(strs[index + 1]));
                        cur.right = right;
                        queue.offer(right);
                    }else {
                        queue.offer(null);
                    }
                }else {
                    queue.offer(null);
                    queue.offer(null);
                }
                index += 2;
                if(index >= strs.length){
                    break;
                }
            }
            return root;
        }
    }

    /**
     * 这个是对于稀疏二叉树效率比较高 对于null的就不入队列了
     */
    public class Codec1 {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if(root == null){
                return null;
            }
            StringBuilder result = new StringBuilder();

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int nodeLen = 1;
            boolean isEnd = true;
            StringBuilder builder = new StringBuilder();
            while (!queue.isEmpty()){
                TreeNode node = queue.poll();
                if(node != null){
                    builder.append(node.val).append(',');
                    if(node.left != null){
                        queue.offer(node.left);
                        isEnd = false;
                    }else {
                        queue.offer(null);
                    }
                    if(node.right != null){
                        queue.offer(node.right);
                        isEnd = false;
                    }else {
                        queue.offer(null);
                    }
                }else {
                    builder.append("null,");
                }

                nodeLen--;
                if(nodeLen == 0){
                    result.append(builder);
                    nodeLen = queue.size();
                    builder = new StringBuilder();
                    if(isEnd){
                        break;
                    }
                    isEnd = true;
                }
            }
            return result.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if(data == null){
                return null;
            }
            String[] strs = data.substring(0, data.length() - 1).split(",");
            TreeNode root = new TreeNode(Integer.parseInt(strs[0]));
            if(strs.length == 1){
                return root;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int index = 1;
            while (!queue.isEmpty()){
                TreeNode cur = queue.poll();
                if(cur != null){
                    //左节点
                    if(!strs[index].equals("null")){
                        TreeNode left = new TreeNode(Integer.parseInt(strs[index]));
                        cur.left = left;
                        queue.offer(left);
                    }else {
                        queue.offer(null);
                    }
                    //右节点
                    if(index + 1 < strs.length && !strs[index + 1].equals("null")){
                        TreeNode right = new TreeNode(Integer.parseInt(strs[index + 1]));
                        cur.right = right;
                        queue.offer(right);
                    }else {
                        queue.offer(null);
                    }
                    index += 2;
                }
                if(index >= strs.length){
                    break;
                }
            }
            return root;
        }
    }

    /**
     * 题解给的是dfs 原理差不多 但是效率感人
     */
    public class Codec2 {
        public String rserialize(TreeNode root, String str) {
            // Recursive serialization.
            if (root == null) {
                str += "null,";
            } else {
                str += str.valueOf(root.val) + ",";
                str = rserialize(root.left, str);
                str = rserialize(root.right, str);
            }
            return str;
        }

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            return rserialize(root, "");
        }
        public TreeNode rdeserialize(List<String> l) {
            // Recursive deserialization.
            if (l.get(0).equals("null")) {
                l.remove(0);
                return null;
            }

            TreeNode root = new TreeNode(Integer.valueOf(l.get(0)));
            l.remove(0);
            root.left = rdeserialize(l);
            root.right = rdeserialize(l);

            return root;
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] data_array = data.split(",");
            List<String> data_list = new LinkedList<String>(Arrays.asList(data_array));
            return rdeserialize(data_list);
        }
    }

    /**
     * 这个是题解的思路 效率比较快
     */
    public class Codec3 {
        public static final String NN = "N";
        public static final String DELIMITER = "X";

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if(root == null) return NN;
            return root.val + DELIMITER + serialize(root.left) + DELIMITER + serialize(root.right);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            int[] t = new int[1];
            return deserialize(data, t);
        }

        private TreeNode deserialize(String data, int[] t) {
            if(String.valueOf(data.charAt(t[0])).equals(NN)) {
                t[0] += 2;
                return null;
            }
            int idx = data.indexOf(DELIMITER, t[0]);
            int val = Integer.valueOf(data.substring(t[0], idx));
            TreeNode node = new TreeNode(val);
            t[0] = idx + 1;

            node.left = deserialize(data, t);
            node.right = deserialize(data, t);

            return node;
        }
    }

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

    public static void main(String[] args) {
        LeetCode297 leetCode297 = new LeetCode297();
        LeetCode297.Codec codec = leetCode297.new Codec();
//        String str = codec.serialize(TreeNode.initTreeByLoop(new Integer[]{1,2,3,null,null,4,5}));

//        codec.deserialize(str);
//        codec.deserialize(str1);

        LeetCode297.Codec1 codec1 = leetCode297.new Codec1();
        TreeNode node1 = new TreeNode(1);
        node1.left = new TreeNode(2);
        node1.left.left = new TreeNode(3);
        node1.left.left.left = new TreeNode(4);
        node1.left.left.left.left = new TreeNode(5);
        String str1 = codec1.serialize(node1);
        codec1.deserialize("1,2,null,3,null,4,null,5,");
    }
}
