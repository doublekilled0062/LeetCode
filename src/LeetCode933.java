/**
 * 933. 最近的请求次数
 *
 * 写一个 RecentCounter 类来计算最近的请求。
 * 它只有一个方法：ping(int t)，其中 t 代表以毫秒为单位的某个时间。
 * 返回从 3000 毫秒前到现在的 ping 数。
 * 任何处于 [t - 3000, t] 时间范围之内的 ping 都将会被计算在内，包括当前（指 t 时刻）的 ping。
 * 保证每次对 ping 的调用都使用比之前更大的 t 值。
 *
 * 示例：
 * 输入：inputs = ["RecentCounter","ping","ping","ping","ping"],
 * inputs = [[],[1],[100],[3001],[3002]]
 * 输出：[null,1,2,3,3]
 *
 * 提示：
 * 每个测试用例最多调用 10000 次 ping。
 * 每个测试用例会使用严格递增的 t 值来调用 ping。
 * 每次调用 ping 都有 1 <= t <= 10^9。
 */
public class LeetCode933 {
    /**
     * Your RecentCounter object will be instantiated and called as such:
     * RecentCounter obj = new RecentCounter();
     * int param_1 = obj.ping(t);
     */
    class RecentCounter {
        private int[] list = new int[10000];
        private int index = 0;
        public RecentCounter() {

        }

        /**
         * 二分查找法 每次调用把时间存到数组，然后找第一个大于等于t - 3000的索引
         * @param t
         * @return
         */
        public int ping(int t) {
            list[index++] = t;
            int start = 0;
            int end = index - 1;
            int value = t - 3000;
            while (start <= end){
                int mid = start + ((end - start) >> 1);
                if(list[mid] == value){
                    //直接找到了
                    return index - mid;
                }else if(list[mid] < value){
                    start = mid + 1;
                }else {
                    if(mid == 0 || list[mid - 1] < value){
                        return index - mid;
                    }else {
                        end = mid - 1;
                    }
                }
            }
            return 0;
        }
    }
}
