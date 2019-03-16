/**
 * 278. 第一个错误的版本
 *
 * 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。
 * 由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
 * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
 * 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。
 * 实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
 *
 * 示例:
 * 给定 n = 5，并且 version = 4 是第一个错误的版本。
 * 调用 isBadVersion(3) -> false
 * 调用 isBadVersion(5) -> true
 * 调用 isBadVersion(4) -> true
 * 所以，4 是第一个错误的版本。
 */
public class LeetCode278 {

    /**
     * 传统二分 这个时间并不优秀
     * @param n
     * @return
     */
    public int firstBadVersion(int n) {
        int start = 1;
        int end = n;
        while (start <= end){
            int mid = start + ((end - start) >> 1);
            if(isBadVersion(mid)){
                if(mid <= 0){
                    //都错了
                    return -1;
                }else if(!isBadVersion(mid - 1)){
                    //找到了
                    return mid;
                }else {
                    end = mid - 1;
                }
            }else {
                if(mid == n){
                    //全对
                    return n + 1;
                }else if(isBadVersion(mid + 1)){
                    //找到了
                    return mid + 1;
                }else {
                    start = mid + 1;
                }
            }
        }
        return 0;
    }

    /**
     * 每次二分只调用一次
     * @param n
     * @return
     */
    public int firstBadVersion2(int n) {
        int start = 1;
        int end = n;
        int res = 1;
        while (start <= end){
            int mid = start + ((end - start) >> 1);
            if(isBadVersion(mid)){
                res = mid;
                end = mid - 1;
            }else {
                start = mid + 1;
            }
        }
        return res;
    }

    /**
     * 占坑编译不报错
     * @param version
     * @return
     */
    private boolean isBadVersion(int version){
        return version >= 823161944;
    }

    public static void main(String[] args) {
        LeetCode278 leetCode278 = new LeetCode278();
        System.out.println(leetCode278.firstBadVersion(921239930));
    }

}
