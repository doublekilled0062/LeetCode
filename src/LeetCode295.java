import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 295. 数据流的中位数
 *
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 *
 * 例如，
 * [2,3,4] 的中位数是 3
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 *
 * 设计一个支持以下两种操作的数据结构：
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 *
 * 示例：
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 *
 * 进阶:
 * 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
 * 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-median-from-data-stream
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode295 {
    /**
     * 用红黑树(任意平衡树或者跳表)和双指针就可以 大小堆也可以
     * 因为java里没有好的实现跳表和红黑的类 所以代码先用堆实现 其他的写下算法过程
     * 存两个指针 更新树的时候动态更新指针 p1 size/2 和 p2 size/2 + 1
     * 还有一个size 表明最后一个是用一个数还是两个数的平均
     * 算法过程
     * 1.1个数 初始化p1
     * 2.2个数 初始化p2 p1 一定比 p2小
     * 3.3个数及以上 看插入值的大小
     * 3-1 如果插入数比p1小或者等于 p1变成p2， p1的前驱节点为p1
     * 3-2 如果插入的数比p2大或者等于 p2变p1，p2的后继变p2
     * 3-3 如果插入的数介于p1和p2之间 需要判断size 如果size为奇数 p1不变 p2变成新插入的数
     *     如果size为偶数p2不变 p1变成新插入的数
     * 输出结果 size为奇数 值为p1 size为偶数 值为p1+p2/2
     */
    class MedianFinder {
        private PriorityQueue<Integer> maxHeap;
        private PriorityQueue<Integer> minHeap;

        /** initialize your data structure here. */
        public MedianFinder() {
            this.minHeap = new PriorityQueue<Integer>();
            this.maxHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });
        }

        public void addNum(int num) {
            maxHeap.add(num);
            minHeap.add(maxHeap.poll());
            if(maxHeap.size() < minHeap.size()){
                maxHeap.add(minHeap.poll());
            }
        }

        public double findMedian() {
            if(maxHeap.size() == minHeap.size()) {
                return (maxHeap.peek() + minHeap.peek())/2d;
            }
            return maxHeap.peek();
        }
    }
/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
}
