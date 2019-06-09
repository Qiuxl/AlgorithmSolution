package Array;

public class MinimumNum {

    /**
     *
     * 这题的关键在于理解循环递增数组的由来，是由一个有序递增的数组，从右边截取一部分，放到左边形成的
     *
     * 给出循环递增数组，找出最小元素，特别的，最小的元素很有可能出现在最中间
     *
     * @param arr
     * @return
     */


    public static int findMinimum(int[] arr){
        int left = 0;
        int right = arr.length-1;
        while (left < right){
            int mid = (left + right + 1) / 2;
            if(arr[mid] < arr[right]){
                right = mid;
            }else if(arr[mid] > arr[right]){
                // 中间元素还处于递增阶段
                left = mid + 1;
            }else {
                // 相等的情况下
                right--;
            }
        }
        return arr[left];
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 5, 6, 7, 1, 3, 4};
        System.out.println(findMinimum(arr));

    }

}
