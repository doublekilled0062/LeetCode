/**
 * 各类排序算法练习
 */
public class Sort {

    /**
     * 冒泡
     * @param nums
     */
    public void bubbleSort(int[] nums){
        if(nums == null || nums.length <= 1){
            return;
        }
        for(int i = 0; i < nums.length; i++){
            boolean sortFlag = false;
            for(int j = 0; j < nums.length - i - 1; j++){
                if(nums[j] > nums[j + 1]){
                    int temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                    sortFlag = true;
                }
            }
            if(!sortFlag){
                break;
            }
        }
    }

    /**
     * 插入排序
     * @param nums
     */
    public void insertSort(int[] nums){
        if(nums == null || nums.length <= 1){
            return;
        }
        for(int i = 1; i < nums.length; i++){
            int value = nums[i];
            int j = i - 1;
            for(; j >= 0; j--){
                if(nums[j] > value){
                    nums[j+1] = nums[j];
                }else {
                    break;
                }
            }
            nums[j + 1] = value;
        }
    }

    /**
     * 选择排序
     * @param nums
     */
    public void selectSort(int[] nums){
        if(nums == null || nums.length <= 1){
            return;
        }
        for(int i = 0; i < nums.length - 1; i++){
            int minIndex = i;
            for(int j = i; j < nums.length - 1; j++){
                if(nums[j] <= nums[minIndex]){
                    minIndex = j;
                }
            }
            if(minIndex != i){
                int temp = nums[i];
                nums[i] = nums[minIndex];
                nums[minIndex] = temp;
            }
        }
    }

    public void quickSort(int[] num){

    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 0, 5, 8};
        int[] nums2 = {3, 2, 0, 5, 8};
        int[] nums3 = {3, 2, 0, 5, 8};
        Sort sort = new Sort();
        sort.bubbleSort(nums);
        sort.insertSort(nums2);
        sort.insertSort(nums3);
        System.out.println("冒泡");
        for(int i = 0; i < nums.length; i++){
            System.out.print(nums[i] + " ");
        }
        System.out.println();
        System.out.println("插入");
        for(int i = 0; i < nums2.length; i++){
            System.out.print(nums2[i] + " ");
        }
        System.out.println();
        System.out.println("选择");
        for(int i = 0; i < nums3.length; i++){
            System.out.print(nums2[i] + " ");
        }
    }
}
