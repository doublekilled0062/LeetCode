import java.util.HashSet;
import java.util.Set;

/**
 * 有一个数组，[3,5,4,6]，如果当前数和前面的数能够组成连续的数组，
 * 则返回true，否则false。那么上面数组的输出结果是［true,false,true,true］
 */
public class ContinuousNumber {
    public boolean[] continuousNumber(int[] num){
        if(num == null){
            return null;
        }
        if(num.length == 0){
            return new boolean[0];
        }
        boolean[] res = new boolean[num.length];
        res[0] = true;
        Set<Integer> set = new HashSet<>();
        set.add(num[0]);
        int min = num[0];
        int max = num[0];
        int i = 1;
        while (i < num.length){
            if(!set.add(num[i])){
                //有重复的 直接退出 从i往后全是false;
                break;
            }
            //填到集合 更新 min和max
            min = Math.min(min, num[i]);
            max = Math.max(max, num[i]);

            //比较数量 max-min == i
            if(max - min + 1 == i + 1){
                res[i] = true;
            }else {
                res[i] = false;
            }
            i++;
        }
        for(int j = i; j < num.length; j++){
            res[j] = false;
        }
        return res;

    }

    public static void main(String[] args) {
        ContinuousNumber continuousNumber = new ContinuousNumber();
        boolean res1[] = continuousNumber.continuousNumber(new int[]{3,5,4,6});
        boolean res2[] = continuousNumber.continuousNumber(new int[]{2,3,2,4,5,6,7,8});
        System.out.println();
    }
}
