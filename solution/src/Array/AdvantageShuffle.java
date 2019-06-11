package Array;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * Given two arrays A and B of equal size, the advantage of A with respect to B is the
 *  number of indices i for which A[i] > B[i].
 *
 * Return any permutation of A that maximizes its advantage with respect to B.
 *
 *
 *
 * Example 1:
 *
 * Input: A = [2,7,11,15], B = [1,10,4,11]
 * Output: [2,11,7,15]
 */
public class AdvantageShuffle {

    /**
     * 对于每一个B中的每一个数，在A中寻找上界
     * @param A
     * @param B
     * @return
     */
    public int[] advantageCount(int[] A, int[] B) {


        //Add each number to the map along with count
        Arrays.sort(A);
        int[] used = new int[A.length];
        int[] result = new int[A.length];

        for(int i = 0; i < B.length; i ++) {
            result[i] = binarySearch(A, used, B[i]);
        }
        return result;
    }

    /**
     * 找到没有被使用的
     * @param A
     * @param used
     * @param k
     * @return
     */

    // 寻找没有被使用过的上界，属于某个数字的上界
    public int binarySearch(int[] A, int[] used,int k){
        int start = 0;
        int end = A.length-1;
        while(start < end){
            int mid = (start + end)/2;

            if(A[mid] <= k){
                start = mid +1;
            }else{
                end = mid;
            }
        }

        if(A[start]>k){
            while(start<A.length && used[start] == 1){
                start ++;
            }
            if(start < A.length){
                used[start] = 1;
                return A[start];
            }
        }

        start = 0;
        while(start<A.length && used[start] == 1){
            start ++;
        }
        used[start] = 1;
        return A[start];

    }

    private int findBestMatch(int target, TreeMap<Integer, Integer> map) {
        // See if there exists a number higher than the target
        Integer res = map.higherKey(target);

        // If a number higher than target does not exist, use the smalles available number
        if (res == null) res = map.firstKey();

        //Update the TreeMap, remove the key if the number has 0 remaining occurences
        map.put(res, map.get(res) - 1);
        if (map.get(res) == 0) map.remove(res);

        return res;
    }
}
