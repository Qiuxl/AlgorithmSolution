package Array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array A of integers, return the number of (contiguous, non-empty) subarrays
 *  that have a sum divisible by K.
 *
 *
 *
 * Example 1:
 *
 * Input: A = [4,5,0,-2,-3,1], K = 5
 * Output: 7
 * Explanation: There are 7 subarrays with a sum divisible by K = 5:
 * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 */
public class SubarraySumsDivisiblebyK {

    /**
     *  sum[i] 表示以i为结尾的数组的和，
     *  ( sum[j] - sum[i] ) % k == 0 for i = 0 -> j - 1
     *   so how many sum[i] that make sum ( sum[j] - sum[i] ) % k == 0
     *   换句话说，需要知道 多少个以 从 0 - i之间的和，使得 上述条件成立，因此在求和的时候可以缓存下来
     * @param A
     * @param K
     * @return
     */
    public static int subarraysDivByK(int[] A, int K) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int count = 0, sum = 0;
        for(int a : A) {
            sum = (sum + a) % K;
            if(sum < 0) sum += K;  // Because -1 % 5 = -1, but we need the positive mod 4
            count += map.getOrDefault(sum, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3};
        System.out.println(subarraysDivByK(arr,5));
    }
}

