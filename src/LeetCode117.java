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
    public Node connect(Node root) {
        if(root == null){
            return root;
        }
        Node levelNode = root;
        while (levelNode != null){
            Node cur = levelNode;
            while (cur != null){
                if(cur.left != null){
                    //有右节点
                    if(cur.right != null){
                        //左到右
                        cur.left.next = cur.right;
                        //有next
                        Node next = cur.next;
                        while (next != null){
                            //next左不空就指左 右不空就指右 全空就不指
                            if(next.left != null){
                                cur.right.next = next.left;
                                break;
                            }else if(next.right != null){
                                cur.right.next = next.right;
                                break;
                            }else {
                                next = next.next;
                            }
                        }
                    }else {
                        Node next = cur.next;
                        while (next != null){
                            //next左不空就指左 右不空就指右 全空就不指
                            if(next.left != null){
                                cur.left.next = next.left;
                                break;
                            }else if(next.right != null){
                                cur.left.next = next.right;
                                break;
                            }else {
                                next = next.next;
                            }
                        }
                    }
                }else {
                    //只有右节点
                    if(cur.right != null){
                        //有next
                        Node next = cur.next;
                        while (next != null){
                            //next左不空就指左 右不空就指右 全空就不指
                            if(next.left != null){
                                cur.right.next = next.left;
                                break;
                            }else if(next.right != null){
                                cur.right.next = next.right;
                                break;
                            }else {
                                next = next.next;
                            }
                        }
                    }
                }
                cur = cur.next;
            }
            if(levelNode.left != null){
                levelNode = levelNode.left;
            }else if(levelNode.right != null){
                levelNode = levelNode.right;
            }else {
                levelNode = levelNode.next;
            }
        }
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
    };
}
