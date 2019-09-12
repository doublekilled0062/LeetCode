import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 284. 顶端迭代器
 * <p>
 * 给定一个迭代器类的接口，接口包含两个方法： next() 和 hasNext()。
 * 设计并实现一个支持 peek() 操作的顶端迭代器 -- 其本质就是把原本应由 next() 方法返回的元素 peek() 出来。
 * <p>
 * 示例:
 * 假设迭代器被初始化为列表 [1,2,3]。
 * <p>
 * 调用 next() 返回 1，得到列表中的第一个元素。
 * 现在调用 peek() 返回 2，下一个元素。在此之后调用 next() 仍然返回 2。
 * 最后一次调用 next() 返回 3，末尾元素。在此之后调用 hasNext() 应该返回 false。
 * 进阶：你将如何拓展你的设计？使之变得通用化，从而适应所有的类型，而不只是整数型？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/peeking-iterator
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode284 {

    class PeekingIterator implements Iterator<Integer> {
        private Iterator<Integer> iterator;
        private Integer peek;

        public PeekingIterator(Iterator<Integer> iterator) {
            // initialize any member here.
            this.iterator = iterator;
            peek = iterator.next();
        }

        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {
            return peek;
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {
            int temp = peek;
            if (iterator.hasNext()) {
                peek = iterator.next();
            } else {
                peek = null;
            }
            return temp;
        }


        @Override
        public boolean hasNext() {
            return peek != null;
        }
    }

    class PeekingIterator1 implements Iterator<Integer> {

        private int pos;
        private List<Integer> list;

        public PeekingIterator1(Iterator<Integer> iterator) {
            // initialize any member here.
            list = new ArrayList<Integer>();
            while (iterator.hasNext()) {
                list.add(iterator.next());
            }
        }

        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {
            if (list == null || list.isEmpty() || pos >= list.size()) {
                return Integer.MIN_VALUE;
            }
            return list.get(pos);
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {
            int result = Integer.MIN_VALUE;
            if (pos < list.size()) {
                result = list.get(pos++);
            }
            return result;
        }

        @Override
        public boolean hasNext() {
            return pos < list.size();
        }
    }

    public static void main(String[] args) {

    }
}
