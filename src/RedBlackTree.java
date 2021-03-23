/**
 * 一个红黑树的实现
 * @param <K> 排序的值
 * @param <V> 保存的值
 */
public class RedBlackTree<K extends Comparable<K>, V> {
    public static int RED = 0;
    public static int BLACK = 1;

    /**
     * 判断为根节点可以直接用node.parent == null判断
     */
    public RedBlackNode<K, V> root = new RedBlackNode<K, V>(null, null,  BLACK);

    /**
     * 红黑树查找
     * @param k
     * @return
     */
    public RedBlackNode<K, V> search(K k){
        RedBlackNode<K, V> p = root;
        while (p.key != null){
            int res = k.compareTo(p.key);
            if(res > 0){
                p = p.right;
            }else if(res < 0){
                p = p.left;
            }else {
                return p;
            }
        }
        return null;
    }

    public void add(K key, V value){
        //先做二叉查找树的插入 默认红节点 注意叶子节点是一个黑空节点
        RedBlackNode<K, V> p = root;
        while (p.key != null){
            int res = key.compareTo(p.key);
            if(res == 0){
                //替换节点 返回
                p.value = value;
                return;
            }else if(res < 0){
                //插入节点小于当前节点 往左走
                p = p.left;
            }else {
                //插入节点大于当前节点 往右走
                p = p.right;
            }
        }

        RedBlackNode<K, V> left = new RedBlackNode<K, V>(null, null,  BLACK);
        RedBlackNode<K, V> right = new RedBlackNode<K, V>(null, null, BLACK);

        p.key = key;
        p.value = value;
        p.color = RED;
        p.left = left;
        p.right = right;

        left.parent = p;
        right.parent = p;

        //重平衡策略 添加的节点在未重平衡之前一定是有两个黑色key空叶子节点
        if(p.parent == null || p.parent.color == RED){
            //根节点是红色或者父节点是红色 触发重平衡
            balanceAdd(p);
        }
        return;
    }

    /**
     * 删除节点
     * @param k
     */
    public void del(K k){
        //删除节点 相当于把左子树后继节点替换到当前节点 再删除该后继节点
        RedBlackNode<K, V> node = search(k);
        if(node == null){
            //节点不存在 直接返回
            return;
        }
        //如果有两个非key空子树
        if(node.left.key != null && node.right.key != null){
            //找后继节点
            RedBlackNode<K, V> next = node.right;
            if(next.left.key != null){
                next = next.left;
            }
            //替换
            node.value = next.value;
            node = next;
        }
        //删除node node只有两种情况只有一个带值子树或者没有带值子树
        RedBlackNode<K, V> parent = node.parent;
        boolean nodeLeft = false;
        if(parent != null || parent.left == node){
            nodeLeft = true;
        }
        if(node.left.key != null){
            //node.left肯定是红色
            node.left.parent = parent;
            if(parent != null){
                if(nodeLeft){
                    parent.left = node.left;
                }else {
                    parent.right = node.left;
                }
            }else {
                root = node.left;
            }
            node.left.color = BLACK;
            node.left = null;
        }else if(node.right.key != null){
            //node.right肯定是红色
            node.right.parent = parent;
            if(parent != null){
                if(nodeLeft){
                    parent.left = node.right;
                }else {
                    parent.right = node.right;
                }
            }else {
                root = node.right;
            }
            node.right.color = BLACK;
            node.right = null;
        }else {
            if(parent == null){
                //删除的是根节点
                root = new RedBlackNode<K, V>(null, null,  BLACK);
            }else {
                RedBlackNode<K, V> temp = new RedBlackNode<K, V>(null, null,  BLACK);
                temp.parent = parent;
                if(nodeLeft){
                    parent.left = temp;
                }else {
                    parent.right = temp;
                }
                if(node.color == BLACK){
                    balanceDel(temp);
                }
            }
        }
        return;
    }

    public boolean check(){
        return true;
    }


    /**
     * 添加重平衡
     * @param node 关注节点 是一个红色节点
     */
    private void balanceAdd(RedBlackNode<K, V> node){
        if(node.parent == null){
            //如果是根节点 直接染黑 结束
            node.color = BLACK;
            return;
        }
        RedBlackNode<K, V> parent = node.parent;
        if(parent.color == BLACK){
            //父节点是黑色 直接返回
            return;
        }
        //找到关注节点的叔叔节点 肯定有叔叔节点 只不过有可能是黑key空节点
        RedBlackNode<K, V> grandParent = parent.parent;
        RedBlackNode<K, V> uncle = null;
        boolean parentLeft = false;
        if(grandParent.left == parent){
            parentLeft = true;
            uncle = grandParent.right;
        }else {
            uncle = grandParent.left;
        }
        if(uncle.color == RED){
            //父叔同红 -> 父叔黑 爷红 递归
            parent.color = BLACK;
            uncle.color = BLACK;
            grandParent.color = RED;
            balanceAdd(grandParent);
            return;
        }
        //叔黑的情况比较多
        boolean nodeLeft = false;
        if(parent.left == node){
            nodeLeft = true;
        }
        //父子同左 要以爷为支点右旋
        if(parentLeft && nodeLeft){
            rightRoute(grandParent);
        }else if(!parentLeft && !nodeLeft){
            leftRoute(grandParent);
        }else if(parentLeft && !nodeLeft){
            leftRoute(parent);
            rightRoute(grandParent);
        }else if(!parentLeft && nodeLeft){
            rightRoute(parent);
            leftRoute(grandParent);
        }
        grandParent.color = RED;
        grandParent.parent.color = BLACK;
        return;
    }

    /**
     * 删除节点
     * @param node 关注节点 是一个黑空叶节点
     */
    public void balanceDel(RedBlackNode<K, V> node){
        //node一定有兄弟节点 并且兄弟节点一定有子节点
        if(node.parent == null){
            return;
        }
        RedBlackNode<K, V> parent = node.parent;
        RedBlackNode<K, V> brother = null;
        boolean nodeLeft = false;
        if(parent.left == node){
            nodeLeft = true;
        }
        if(nodeLeft){
            brother = parent.right;
        }else {
            brother = parent.left;
        }
        if(brother.color == RED){
            if(nodeLeft){
                //兄在右 以parent为支点左旋 然后交换brother和parent颜色
                // 可以递归了关注节点不变 但是兄弟节点已经变了
                leftRoute(parent);
            }else {
                //兄在左 以parent为支点右旋 然后交换brother和parent颜色
                // 可以递归了关注节点不变 但是兄弟节点已经变了
                rightRoute(parent);
            }
            parent.color = RED;
            brother.color = BLACK;
            balanceDel(node);
        }else {
            //兄弟节点是黑色节点
            if(brother.left.color == BLACK && brother.right.color == BLACK){
                //如果兄弟节点的子节点全是黑色 需要判断parent
                if(parent.color == RED){
                    parent.color = BLACK;
                    brother.color = RED;
                    return;
                }else {
                    brother.color = RED;
                    //关注节点变成parent
                    balanceDel(parent);
                }
            }else if(nodeLeft){
                //兄弟节点是右节点
                if(brother.right.color != RED){
                    //如果兄弟节点的右节点不是红色 先以brother右旋 交换颜色
                    rightRoute(brother);
                    brother.color = RED;
                    brother.parent.color = BLACK;
                    brother = brother.parent;
                }

                //兄弟节点的右子节点是红色 以parent左旋
                leftRoute(parent);
                //交换parent和brother颜色  brother右子树染黑
                brother.color = parent.color;
                parent.color = BLACK;
                brother.right.color = BLACK;

            }else {
                //兄弟节点是左节点  兄弟节点的右子节点是红色 先以brother左旋
                leftRoute(brother);
                brother.color = RED;
                brother.parent.color = BLACK;
                brother = brother.parent;

                //兄弟节点的左子节点是红色 以parent右旋
                rightRoute(parent);
                brother.color = parent.color;
                parent.color = BLACK;
                brother.left.color = BLACK;
            }
        }
    }

    /**
     * 以node为支点进行左旋
     * @param node
     */
    private void leftRoute(RedBlackNode<K, V> node){
        RedBlackNode<K, V> parent = node.parent;
        boolean left = false;
        if(node.parent != null && node.parent.left == node){
            left = true;
        }
        RedBlackNode<K, V> childRoot = node.right;
        node.right = childRoot.left;
        childRoot.left.parent = node;

        childRoot.left = node;
        node.parent = childRoot;

        childRoot.parent = parent;
        if(parent != null){
            if(left){
                parent.left = root;
            }else {
                parent.right = root;
            }
        }else {
            root = childRoot;
        }
    }

    /**
     * 以node为支点进行右旋
     * @param node
     */
    private void rightRoute(RedBlackNode<K, V> node){
        RedBlackNode<K, V> parent = node.parent;
        boolean left = false;
        if(node.parent != null && node.parent.left == node){
            left = true;
        }
        RedBlackNode<K, V> childRoot = node.left;
        node.left = childRoot.right;
        childRoot.right.parent = node;

        childRoot.right = node;
        node.parent = childRoot;

        childRoot.parent = parent;
        if(parent != null){
            if(left){
                parent.left = root;
            }else {
                parent.right = root;
            }
        }else {
            root = childRoot;
        }
    }

    public class RedBlackNode<K extends Comparable<K>, V>{

        public RedBlackNode<K, V> parent;
        public RedBlackNode<K, V> left;
        public RedBlackNode<K, V> right;

        public K key;
        public V value;
        public int color;

        public RedBlackNode(K key, V value, int color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }

        public RedBlackNode(K key, V value, int color, RedBlackNode<K, V> parent,
                            RedBlackNode<K, V> left, RedBlackNode<K, V> right) {
            this.key = key;
            this.value = value;
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.color = color;
        }
    }
}
