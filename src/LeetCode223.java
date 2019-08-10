/**
 * 223. 矩形面积
 *
 * 在二维平面上计算出两个由直线构成的矩形重叠后形成的总面积。
 *
 * 每个矩形由其左下顶点和右上顶点坐标表示，如图所示。
 *
 *                 |
 *                 |
 *                 |
 *                 |
 *           ——————————————（3，4）
 *          |      |       |
 *          |      |————————————————————————（9，2）
 *          |      |       |                |
 *          |      |       |                |
 *   （-3，0）——————|————————————————————————|———————
 *                 |                        |
 *           (0，-1)————————————————————————|
 *                 |
 * 示例:
 *
 * 输入: -3, 0, 3, 4, 0, -1, 9, 2
 * 输出: 45
 * 说明: 假设矩形面积不会超出 int 的范围。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rectangle-area
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode223 {

    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        //分情况讨论 1套2 返回1的面积
        if(A <= E && B <= F && C >= G && D >= H){
            return (C - A) * (D - B);
        }
        //2套1 返回2的面积
        if(A >= E && B >= F && C <= G && D <= H){
            return (G - E) * (H - F);
        }

        int total = (C - A) * (D - B) + (G - E) * (H - F);
        if(C <= E || G <= A || B >= H || D <= F){
            return total;
        }
        //计算重复面积
        int x1 = Math.max(A, E);
        int y1 = Math.max(B, F);
        int x2 = Math.min(C, G);
        int y2 = Math.min(D, H);
        int delta = (x2 - x1) * (y2 - y1);
        return total - delta;
    }

    public static void main(String[] args) {
        LeetCode223 leetCode223 = new LeetCode223();
        System.out.println(leetCode223.computeArea(-3, 0, 3, 4, 0, -1, 9, 2));
    }
}
