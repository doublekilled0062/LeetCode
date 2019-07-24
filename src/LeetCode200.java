import java.util.LinkedList;

/**
 * 200. 岛屿数量
 *
 * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。
 * 一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。
 *
 * 示例 1:
 * 输入:
 * 11110
 * 11010
 * 11000
 * 00000
 * 输出: 1
 *
 * 示例 2:
 * 输入:
 * 11000
 * 11000
 * 00100
 * 00011
 * 输出: 3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-islands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode200 {
    /**
     * 对1进行广度优先遍历染色
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;

        //节点1是否遍历过
        int res = 0;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(grid[i][j] == '0'){
                    continue;
                }
                //找到一个没有连通过的1
                res++;
                grid[i][j] = '0';
                LinkedList<Integer> queue = new LinkedList<>();
                queue.offer(col * i + j);
                while (!queue.isEmpty()){
                    int value = queue.poll();
                    int r = value/col;
                    int c = value%col;
                    //判断其上下左右
                    if(r - 1 >= 0 && grid[r-1][c] == '1'){
                        queue.offer(col * (r - 1) + c);
                        grid[r-1][c] = '0';
                    }

                    if(r + 1 < row && grid[r+1][c] == '1'){
                        queue.offer(col * (r + 1) + c);
                        grid[r+1][c] = '0';
                    }

                    if(c - 1 >= 0 && grid[r][c-1] == '1'){
                        queue.offer(col * r + c - 1);
                        grid[r][c-1] = '0';
                    }

                    if(c + 1 < col && grid[r][c+1] == '1'){
                        queue.offer(col * r + c + 1);
                        grid[r][c + 1] = '0';
                    }
                }
            }
        }
        return res;
    }

    public int numIslands1(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;

        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    grid = dfs(grid, i, j, row, col);
                    res++;
                }
            }
        }
        return res;
    }

    private char[][] dfs(char[][] grid, int i, int j, int row, int col) {
        grid[i][j] = '0';
        //对上下左右四个方向做搜索。
        if (i - 1 >= 0 && grid[i - 1][j] == '1') {
            dfs(grid, i - 1, j, row, col);
        }
        if (i + 1 < row && grid[i + 1][j] == '1') {
            dfs(grid, i + 1, j, row, col);
        }
        if (j - 1 >= 0 && grid[i][j - 1] == '1') {
            dfs(grid, i, j - 1, row, col);
        }
        if (j + 1 < col && grid[i][j + 1] == '1') {
            dfs(grid, i, j + 1, row, col);
        }
        return grid;
    }
}
