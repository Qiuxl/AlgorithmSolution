package sort;

public class InsertSort {

    // 插入排序

    public static void sortBiggerFirst(int[] arr){
        if(arr.length <= 1){
            return;
        }
        // 从后往前扫描,前面的已经是有序的了
        for(int i = 1; i < arr.length; i++){
            int j = i -1;
            int toBeSorted = arr[i];
            while ( j >= 0 && arr[j] < toBeSorted){
                // 前面是有序的，找到第一个比这个大的就可以退出来了
                // 一个个往后挪
                arr[j + 1] = arr[j];
                j --;
            }
            arr[j + 1] = toBeSorted;
        }
    }

    public static void sortBiggerFirst(int[] arr,int left, int right){

        if(arr.length <= 1 || right < left
                || left < 0 || right < 0 ||
                left >= arr.length || right >= arr.length){
            return;
        }
        // 从后往前扫描,前面的已经是有序的了
        for(int i = left + 1; i <= right; i++){
            int j = i -1;
            int toBeSorted = arr[i];
            while ( j >= left && arr[j] < toBeSorted){
                // 前面是有序的，找到第一个比这个大的就可以退出来了
                // 一个个往后挪
                arr[j + 1] = arr[j];
                j --;
            }
            arr[j + 1] = toBeSorted;
        }
    }

}
