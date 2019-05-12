package sort;

import java.util.Random;

public class SortTest {


    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();

        InsertSort insertSort = new InsertSort();

        int[] testArr = { 1, 3, 5, 4, 6, 10, 7, 100, 99, 102, 1000};

       //  insertSort.sortBiggerFirst(testArr);


        QuickSort quickSort = new QuickSort();
        int[] toBeSorted = genRandomInt(50);

        System.out.println(quickSort.quickSelectKBiggest(testArr, 11));
        quickSort.quickSort(toBeSorted,0, toBeSorted.length - 1);

        for(int i = 0; i < toBeSorted.length; i ++){
            System.out.print(" " + toBeSorted[i]);
        }
    }

    public static int[] genRandomInt(int size) {
        Random random = new Random();
        int[] intArr = new int[size];
        int i = 0;
        while ( i < size){
            intArr[ i++ ] = random.nextInt(2000);
        }
        return intArr;
    }
}
