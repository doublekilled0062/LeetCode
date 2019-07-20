/**
 * 174. 地下城游戏
 *
 * 一些恶魔抓住了公主（P）并将她关在了地下城的右下角。地下城是由 M x N 个房间组成的二维网格。
 * 我们英勇的骑士（K）最初被安置在左上角的房间里，他必须穿过地下城并通过对抗恶魔来拯救公主。
 * 骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。
 *
 * 有些房间由恶魔守卫，因此骑士在进入这些房间时会失去健康点数（若房间里的值为负整数，则表示骑士将损失健康点数）；
 * 其他房间要么是空的（房间里的值为 0），要么包含增加骑士健康点数的魔法球（若房间里的值为正整数，则表示骑士将增加健康点数）。
 *
 * 为了尽快到达公主，骑士决定每次只向右或向下移动一步。
 * 编写一个函数来计算确保骑士能够拯救到公主所需的最低初始健康点数。
 *
 * 例如，考虑到如下布局的地下城，如果骑士遵循最佳路径 右 -> 右 -> 下 -> 下，则骑士的初始健康点数至少为 7。
 *
 * -2 (K)	-3	  3
 * -5	   -10	  1
 * 10	    30	 -5 (P)
 *  
 * 说明:
 *
 * 骑士的健康点数没有上限。
 * 任何房间都可能对骑士的健康点数造成威胁，也可能增加骑士的健康点数，包括骑士进入的左上角房间以及公主被监禁的右下角房间。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/dungeon-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode174 {
    /**
     * 动态规划 一开始想复杂了 如果从左上开始算 则遇到正值时就需要计算正值的盈余 因为需要累计往下计算
     * 如果从右下开始往上算 遇到正的盈余就不用接着往下累计 直接更新成0就好
     * @param dungeon
     * @return
     */
    public int calculateMinimumHP(int[][] dungeon) {
        if(dungeon == null || dungeon.length == 0 || dungeon[0].length == 0){
            return 1;
        }
        int row = dungeon.length;
        int col = dungeon[0].length;
        int[][] dp = new int[row][col];
        dp[row - 1][col - 1] = dungeon[row - 1][col - 1] >= 0 ? 0 : -dungeon[row - 1][col - 1];
        for(int i = row - 2; i >= 0; i--){
            dp[i][col - 1] = Math.max(0, dp[i + 1][col - 1] - dungeon[i][col - 1]);
        }
        for(int i = col - 2; i >= 0; i--){
            dp[row - 1][i] = Math.max(0, dp[row - 1][i + 1] - dungeon[row - 1][i]);
        }
        for(int i = row - 2; i >= 0; i--){
            for(int j = col - 2; j >= 0; j--){
                dp[i][j] = Math.max(0, Math.min(dp[i + 1][j], dp[i][j+1]) - dungeon[i][j]);
            }
        }
        return dp[0][0] + 1;
    }

    /**
     * 二维可以转化成一维
     * @param dungeon
     * @return
     */
    public int calculateMinimumHP1(int[][] dungeon) {
        if(dungeon == null || dungeon.length == 0 || dungeon[0].length == 0){
            return 1;
        }
        int row = dungeon.length;
        int col = dungeon[0].length;

        //第一层循环从行开始 dp就用列长度，从列开始就用行长度
        int[] dp = new int[col];

        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 0; j--) {
                if (i == row - 1 && j == col - 1) {
                    dp[j] = dungeon[i][j] >= 0 ? 0 : -dungeon[i][j];
                }else if(i == row - 1){
                    dp[j] = Math.max(0, dp[j + 1] - dungeon[i][j]);
                }else if(j == col - 1){
                    dp[j] = Math.max(0, dp[j] - dungeon[i][j]);
                }else {
                    dp[j] = Math.max(0, Math.min(dp[j], dp[j+1]) - dungeon[i][j]);
                }
            }
        }
        return dp[0] + 1;
    }
}
