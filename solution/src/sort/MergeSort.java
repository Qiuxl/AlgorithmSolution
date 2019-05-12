package sort;

public class MergeSort implements ISort{

    // 归并排序实现排序算法，大的在前
    @Override
    public void sortIntLargestFirst(int[] arr) {
        // 迭代法实现归并排序
        // 每次处理两个cell，然后是4个cell，
        if(arr.length <= 1){
            return;
        }
        int length = 1;
        // 每次处理 start start + 2* length-1 之间的序列
        for(length = 1; length < arr.length; length *= 2){
            int start = 0;
            while ( start < arr.length ){
                int end = Math.min(start + 2* length - 1, arr.length -1 );
                int mid = (start + end + 1)/2;
                mergeSort(arr,start, mid, end);
                start += length * 2;
            }
        }
        if (arr.length > length /2 && arr.length < length){
            mergeSort(arr,0, length / 2, arr.length -1 );
        }
    }

    public void mergeSort_recursion(int[] arr){
        megre_sort_recursion(arr, 0, arr.length - 1);
    }


    private void megre_sort_recursion(int [] arr, int start, int end){

        if(start < end){
            int mid = (start + end + 1) / 2;
            megre_sort_recursion(arr, start, mid-1);
            megre_sort_recursion(arr, mid, end);
            mergeSort(arr, start, mid, end);
        }
    }

    // start和and是下标位置
    void mergeSort(int[] arr, int start, int mid, int end){
        if(end == start){
            return;
        }
        int[] temp = new int[end - start + 1];
        int i = start, j = mid;
        int count = 0;
        while (i < mid && j <= end){
            if(arr[i] >= arr[j]){
                temp[count ++] = arr[i++];
            }else {
                temp[count ++] = arr[j++];
            }
        }
        while ( i < mid ){
            temp[count ++] = arr[i++];
        }
        while ( j <= end){
            temp[count ++] = arr[j++];
        }
        // 复制数据过去
        count = 0;
        while (start <= end){
            arr[start ++] = temp[count ++];
        }
    }
}
