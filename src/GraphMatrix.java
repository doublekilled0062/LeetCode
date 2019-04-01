/**
 * 邻接矩阵
 */
public class GraphMatrix extends Graph{
    public int[][] matrix;

    public GraphMatrix(int v){
        this.v = v;
        matrix = new int[v][v];
    }

    /**
     * 数组和顶点数初始化
     * @param nums
     * @param v
     */
    public GraphMatrix(int[] nums, int v){
        this.v = v;
        matrix = new int[v][v];
        for(int k = 0; k < nums.length; k++){
            int  i = k / v;
            int  j = k % v;
            matrix[i][j] = nums[k];
        }
    }


    /**
     * 无向图
     * @param s
     * @param t
     * @param w
     */
    public void addNoDirection(int s, int t, int w){
        matrix[s][t] = w;
        matrix[t][s] = w;
    }

    /**
     * 有向图
     * @param s
     * @param t
     * @param w
     */
    public void addByDirection(int s, int t, int w){
        matrix[s][t] = w;
    }

    public void printMatriix(){
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(j == 0){
                    System.out.print("| ");
                }
                System.out.print(matrix[i][j] + " ");
                if(j == matrix[0].length - 1){
                    System.out.println(" |");
                }
            }
        }
    }

    public void printList(){
        int[] nums = new int[v * v];
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                nums[i * v + j] = matrix[i][j];
            }
        }
        for(int i = 0; i < nums.length; i++){
            if(i == 0){
                System.out.print("{ ");
            }
            if(i != nums.length - 1){
                System.out.print(nums[i] + ", ");
            }
            if(i == nums.length - 1){
                System.out.print(nums[i]);
                System.out.println(" }");
            }
        }
    }

    public static void main(String[] args) {
        GraphMatrix graphNoDirection = new GraphMatrix(6);
        graphNoDirection.addNoDirection(0, 1, 1);
        graphNoDirection.addNoDirection(0, 2, 5);
        graphNoDirection.addNoDirection(0, 3, 6);
        graphNoDirection.addNoDirection(1, 3, 3);
        graphNoDirection.addNoDirection(2, 3, 7);
        graphNoDirection.addNoDirection(2, 4, 1);
        graphNoDirection.addNoDirection(2, 5, 2);
        graphNoDirection.addNoDirection(3, 4, 4);
        graphNoDirection.addNoDirection(3, 5, 4);
        graphNoDirection.addNoDirection(4, 5, 8);

        GraphMatrix graphByDirection = new GraphMatrix(6);
        graphByDirection.addByDirection(0, 1, 1);
        graphByDirection.addByDirection(0, 2, 5);
        graphByDirection.addByDirection(0, 3, 6);
        graphByDirection.addByDirection(2, 3, 7);
        graphByDirection.addByDirection(2, 4, 1);
        graphByDirection.addByDirection(2, 5, 2);
        graphByDirection.addByDirection(3, 1, 3);
        graphByDirection.addByDirection(4, 3, 4);
        graphByDirection.addByDirection(4, 5, 8);
        graphByDirection.addByDirection(5, 3, 4);

        graphNoDirection.printMatriix();
        System.out.println();

        graphByDirection.printMatriix();
        System.out.println();

        graphNoDirection.printList();

        GraphMatrix graphNoDirection1 = new GraphMatrix(new int[]{
                0, 1, 5, 6, 0, 0,
                1, 0, 0, 3, 0, 0,
                5, 0, 0, 7, 1, 2,
                6, 3, 7, 0, 4, 4,
                0, 0, 1, 4, 0, 8,
                0, 0, 2, 4, 8, 0 }, 6);
        graphNoDirection1.printMatriix();

    }
}
