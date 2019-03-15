/**
 * 小猫寻路
 * 实际上是一个递归
 */
public class CatRoad {
    public class CatNode{
        public int prei;
        public int prej;
        public int maxValue;

        public CatNode(int i, int j, int maxValue){
            this.prei = i;
            this.prej = j;
            this.maxValue = maxValue;
        }
    }

    public void findMaxRoad(int[][] nums){
        int leni = nums.length;
        int lenj = nums[0].length;
        CatNode[][] resultNode = new CatNode[leni][lenj];

        for(int i = 0; i < leni; i++){
            for(int j = 0; j < leni; j++){
                if(i == 0 && j == 0){
                    resultNode[i][j] = new CatNode(-1, -1, nums[0][0]);
                }else if(i == 0){
                    resultNode[i][j] = new CatNode(i, j - 1, resultNode[i][j - 1].maxValue + nums[i][j]);
                }else if(j == 0){
                    resultNode[i][j] = new CatNode(i - 1, j, resultNode[i - 1][j].maxValue + nums[i][j]);
                }else {
                    if(resultNode[i - 1][j].maxValue >= resultNode[i][j - 1].maxValue){
                        resultNode[i][j] = new CatNode(i - 1, j, resultNode[i - 1][j].maxValue + nums[i][j]);
                    }else {
                        resultNode[i][j] = new CatNode(i , j - 1, resultNode[i][j - 1].maxValue + nums[i][j]);
                    }
                }
            }
        }
        //最大值
        System.out.println("最大值****");
        System.out.println(resultNode[leni - 1][lenj - 1].maxValue);
        int i = leni - 1;
        int j = lenj - 1;
        System.out.println("路径****");
        while (i >= 0 && j >= 0){
            System.out.println("坐标**** " + i + " " + j + "");
            System.out.println("当前值 " + nums[i][j] + " ");
            int tempi = resultNode[i][j].prei;
            int tempj = resultNode[i][j].prej;
            i = tempi;
            j = tempj;
        }
    }

    public static void main(String[] args) {
        int[][] nums = {{9, 8, 9, 5, 6},{8, 1, 8, 5, 5},{5, 4, 4, 9, 7},{8, 1, 9, 9, 8},{3, 2, 1, 3, 1}};
        CatRoad c = new CatRoad();
        c.findMaxRoad(nums);
    }
}
