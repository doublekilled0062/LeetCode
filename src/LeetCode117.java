/**
 * 117. 填充每个节点的下一个右侧节点指针 II
 *
 * 给定一个二叉树
 * struct Node {
 *     int val;
 *     Node *left;
 *     Node *right;
 *     Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * 初始状态下，所有 next 指针都被设置为 NULL。
 * 示例：
 *                 1                        1
 *               /   \                    ／  \
 *              2    3                   2--->3
 *             / \    \                 / \    \
 *            4  5    7                4->5---->7
 * 输入：{"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":null,"right":null,"val":4},
 * "next":null,"right":{"$id":"4","left":null,"next":null,"right":null,"val":5},"val":2},
 * "next":null,"right":{"$id":"5","left":null,"next":null,"right":{"$id":"6","left":null,
 * "next":null,"right":null,"val":7},"val":3},"val":1}
 *
 * 输出：{"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":{"$id":"4","left":null,
 * "next":{"$id":"5","left":null,"next":null,"right":null,"val":7},"right":null,"val":5},"right":null,"val":4},
 * "next":{"$id":"6","left":null,"next":null,"right":{"$ref":"5"},"val":3},"right":{"$ref":"4"},"val":2},
 * "next":null,"right":{"$ref":"6"},"val":1}
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
 * 提示：
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 */
public class LeetCode117 {
    /**
     * 非递归常量空间解法
     * 比递归解法慢在后面
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if(root == null){
            return root;
        }
        Node levelNode = root;
        //为了优化底下注释 引入一个临时变量 在下面循环的时候 就找到了levelNode的下一个
        Node nextLevelNode = null;
        while (levelNode != null){
            Node cur = levelNode;
            while (cur != null){
                //节省点代码的判断 要先判断左节点
                if(cur.left != null){
                    if(nextLevelNode == null){
                        nextLevelNode = cur.left;
                    }
                    if(cur.right != null){
                        //左向右
                        cur.left.next = cur.right;
                    }else {
                        //左向 下一个
                        cur.left.next = nextNode(cur.next);
                    }
                }
                if(cur.right != null){
                    if(nextLevelNode == null){
                        nextLevelNode = cur.right;
                    }
                    //右向下一个
                    cur.right.next = nextNode(cur.next);
                }
                cur = cur.next;
            }
            //慢在这里 可能会一直遍历完最后一层
//            if(levelNode.left != null){
//                levelNode = levelNode.left;
//            }else if(levelNode.right != null){
//                levelNode = levelNode.right;
//            }else {
//                levelNode = levelNode.next;
//            }
            levelNode = nextLevelNode;
            nextLevelNode = null;
        }
        return root;
    }

    private Node nextNode(Node node) {
        while (node != null) {
            if (node.left != null) {
                return node.left;
            }
            if (node.right != null) {
                return node.right;
            }
            node = node.next;
        }
        return null;
    }

    /**
     * 网友递归写法放这里了 和上面非递归其实差不多
     * @param root
     * @return
     */
    public Node connect2(Node root) {
        if (root == null) {
            return null;
        }
        if (root.left != null) {
            if (root.right != null) {
                // 若右子树不为空，则左子树的 next 即为右子树
                root.left.next = root.right;
            } else {
                // 若右子树为空，则右子树的 next 由根节点的 next 得出
                root.left.next = nextNode(root.next);
            }
        }
        if (root.right != null) {
            // 右子树的 next 由根节点的 next 得出
            root.right.next = nextNode(root.next);
        }
        // 先确保 root.right 下的节点的已完全连接，因 root.left 下的节点的连接
        // 需要 root.left.next 下的节点的信息，若 root.right 下的节点未完全连
        // 接（即先对 root.left 递归），则 root.left.next 下的信息链不完整，将
        // 返回错误的信息。可能出现的错误情况如下图所示。此时，底层最左边节点将无
        // 法获得正确的 next 信息：先递归虚线
        //                  o root
        //                 / \
        //     root.left  o —— o  root.right
        //               /    / \
        //              o —— o-- o
        //             /        / \
        //            o        o   o
        connect2(root.right);
        connect2(root.left);
        return root;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val,Node _left,Node _right,Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public static void main(String[] args) {
        LeetCode117 leetCode117 = new LeetCode117();
        Node node1 = leetCode117.new Node();
        Node node2 = leetCode117.new Node();
        Node node3 = leetCode117.new Node();
        Node node4 = leetCode117.new Node();
        Node node5 = leetCode117.new Node();
        Node node7 = leetCode117.new Node();
        node1.val = 1;
        node2.val = 2;
        node3.val = 3;
        node4.val = 4;
        node5.val = 5;
        node7.val = 7;

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node7;
        leetCode117.connect(node1);
    }
}
