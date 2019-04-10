/**
 * 42. 接雨水
 *
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * |
 * |
 * |
 * |                            ___
 * |                           |   |
 * |            ___            |   |___     ___
 * |           |   |***********|   |   |***|   |
 * |    ___  0 |   |___  0  ___|   |   |___|   |___
 * | 0 | 1 |***| 2 | 1 |***| 1 | 3 | 2 | 1 | 2 | 1 |
 * |---|---|---|---|---|---|---|---|---|---|---|---|---|------------
 *   0   1   2   3   4   5   6   7   8   9   10  11  12
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * 感谢 Marcos 贡献此图。
 * 示例:
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 */
public class LeetCode42 {
    /**
     * 两头找
     * 实际是遍历3次
     * @param height
     * @return
     */
    public int trap(int[] height) {
        if(height == null){
            return 0;
        }
        int len = height.length;
        if(len == 0){
            return 0;
        }
        int[] result = new int[len];
        int i = 0;
        //找第一个不为0的从这里开始
        while (i < len){
            if(height[i] == 0){
                i++;
                continue;
            }else {
                break;
            }
        }
        int j = 0;
        while (i < len){
            j = i + 1;
            //找第一个大于height[i]的值 如果j-i>=2 则能放水
            while (j < len){
                if(height[j] >= height[i]){
                    break;
                }
                j++;
            }
            if(j < len){
                if(j - i >= 2){
                    //找到这样一个j
                    for(int k = i + 1; k < j; k++){
                        result[k] = Math.max(0, height[i] - height[k]);
                    }
                }
                i = j;
                continue;
            }
            break;
        }
        if(i < len){
            //表示没找到大于i的j 需要倒着来一次
            j = len - 1;
            //先找第一个大于0的j当墙
            while (j >= i){
                if(height[i] == 0){
                    j--;
                    continue;
                }
                break;
            }
            int k = 0;
            while (j > i){
                k = j - 1;
                while (k >= i){
                    if(height[k] >= height[j]){
                        break;
                    }
                    k--;
                }
                if(k >= i){
                    if(j - k >= 2){
                        for(int v = k; v < j; v++){
                            result[v] = Math.max(0, height[j] - height[v]);
                        }
                    }
                    j = k;
                    continue;
                }
                break;
            }

        }
        int sum = 0;
        for(int v = 0; v < len; v++){
            sum += result[v];
        }
        return sum;
    }

    /**
     * 感觉记两头的动态规划好
     * 需要遍历3次
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        if(height == null){
            return 0;
        }
        int len = height.length;
        if(len == 0){
            return 0;
        }
        //left表示当前索引下之前最高的墙 right表示当前索引下之后最高的墙
        // 如果height[i] < Math.min(left[i], right[i])则能放水 Math.min(left[i], right[i]) - height[i]
        int[] left = new int[len];
        int[] right = new int[len];
        for(int i = 1; i < len; i++){
            left[i] = Math.max(left[i - 1], height[i-1]);
        }
        for(int i = len - 2; i >= 0; i--){
            right[i] = Math.max(right[i + 1], height[i + 1]);
        }
        int sum = 0;
        for(int i = 1; i <= len - 1; i++){
            if(height[i] < Math.min(left[i], right[i])){
                sum += Math.min(left[i], right[i]) - height[i];
            }
        }
        return sum;
    }

    /**
     * 看了2ms的 好像只遍历2次
     * @param height
     * @return
     */
    public int trap3(int[] height) {
        if(height == null){
            return 0;
        }
        int len = height.length;
        if(len == 0){
            return 0;
        }
        int sum = 0;
        int max = 0;            //最高点
        int maxPos = -1;        //最高点索引 两头找
        for (int i = 0; i < len; i++) {
            if (height[i] > max) {
                max = height[i];
                maxPos = i;
            }
        }

        int tempMax = height[0]; //临时最高点
        for (int i = 1; i < maxPos; i++) {
            if (height[i] < tempMax) {
                sum += tempMax - height[i];
            } else {
                tempMax = height[i];
            }
        }

        tempMax = height[len - 1];
        for (int i = len - 2; i > maxPos; i--) {
            if (height[i] < tempMax) {
                sum += tempMax - height[i];
            } else {
                tempMax = height[i];
            }
        }
        return sum;
    }
    public static void main(String[] args) {
        LeetCode42 leetCode42 = new LeetCode42();
        System.out.println(leetCode42.trap3(new int[]{1,0,0,2,1,0,1,3,2,1,2,1}));
        System.out.println(leetCode42.trap3(new int[]{4, 2, 3}));
    }
}
