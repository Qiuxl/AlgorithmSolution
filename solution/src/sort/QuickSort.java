package sort;

public class QuickSort implements ISort {


    // 手动实现快速排序
    // 快速排序是非稳定的，最坏情况可以达到 O(N^2), 归并排序是稳定的

    @Override
    public void sortIntLargestFirst(int[] arr) {

    }



    public void quickSort(int[] arr, int start, int end){

        // 如果在三个以下，用插入排序
        if( end <=  start + 2){
            InsertSort.sortBiggerFirst(arr);
            return;
        }
        // 插入排序
        int pivot = (start + end + 1) / 2;
        // 处理头中尾三个数字的顺序
        if(arr[start] < arr[pivot]){
           swap(arr, start, pivot);
        }
        if(arr[start] < arr[end]){
            swap(arr, start, end);
        }
        if(arr[pivot] < arr[end]) {
            swap(arr, pivot, end);
        }
        int pivotVal = arr[pivot];
        swap(arr, end - 1, pivot);
        int i = start + 1;
        int j = end - 2;
        while (i < j){
            while ( arr[i] >= pivotVal && i <= j ) { i ++;}
            while ( arr[j] < pivotVal && j >= i) { j --;}
            if( i < j ) {
                swap(arr,i , j );
            }
        }
        // 将基准返回原来位置，可以证明 arr[i] < pivotVal
        swap(arr, i, end - 1);
        int mid = (start + end -1 ) / 2;
        // 这里实际上是尾递归
        quickSort(arr, start, mid);
        quickSort(arr, mid + 1, end);
    }


    /**
     * 快速选择算法，从数组中选出第k大的值
     * @param arr
     * @param k
     * @return
     */

    public int quickSelectKBiggest(int[] arr, int k){

        if(arr.length < k){
            return -1;
        }
        int left = 0;
        int right = arr.length - 1;
        return quickSelect(arr, left, right, k);
    }


    // 保证待选择的数据k落在左边和右边之间
    int quickSelect(int[] arr, int left, int right, int k){
        // 间隔小于3，采用插入排序
        if(right - left <= 2){
            InsertSort.sortBiggerFirst(arr, left, right);
            return arr[k - 1];
        }
        // 快速选择
        int pivot = (left + right + 1) / 2;
        // 处理头中尾三个数字的顺序
        if(arr[left] < arr[pivot]){
            swap(arr, left, pivot);
        }
        if(arr[left] < arr[right]){
            swap(arr, left, right);
        }
        if(arr[pivot] < arr[right]) {
            swap(arr, pivot, right);
        }
        int pivotVal = arr[pivot];
        swap(arr, right - 1, pivot);
        int i = left + 1;
        int j = right - 2;
        while (i < j){
            while ( arr[i] >= pivotVal && i <= j ) { i ++;}
            while ( arr[j] < pivotVal && j >= i) { j --;}
            if( i < j ) {
                swap(arr,i , j );
            }
        }
        swap(arr, i, right - 1);
        // 最后i的位置就是第 i + 1大的数字
        if( i + 1 == k){
            return pivotVal;
        }
        if( i + 1 < k){
            return quickSelect(arr, i +1, right, k);
        }else {
            return quickSelect(arr, left, i -1, k);
        }
    }


    private void swap(int[] arr, int index1, int index2){
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

}
