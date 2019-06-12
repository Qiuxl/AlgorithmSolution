package Array;

/**
 * Given an array A of non-negative integers, return the maximum sum of elements in two
 *  non-overlapping (contiguous) subarrays, which have lengths L and M.
 *  (For clarification, the L-length subarray could occur before or after the M-length subarray.)
 *
 * Formally, return the largest V for which V = (A[i] + A[i+1] + ... + A[i+L-1]) +
 *  (A[j] + A[j+1] + ... + A[j+M-1]) and either:
 *
 * 0 <= i < i + L - 1 < j < j + M - 1 < A.length, or
 * 0 <= j < j + M - 1 < i < i + L - 1 < A.length.
 *
 *
 * Example 1:
 *
 * Input: A = [0,6,5,2,2,5,1,9,4], L = 1, M = 2
 * Output: 20
 * Explanation: One choice of subarrays is [9] with length 1, and [6,5] with length 2.
 */
public class MaximumSumofTwoNonOverlappingSubarray {

    public static int maxSumTwoNoOverlap(int[] A, int L, int M) {
        int[] sum = new int[A.length];
        sum[0] = A[0];
        for(int i = 1; i < A.length; i ++ ){
            sum[i] += sum[i-1] + A[i];
        }
        //维护两个数组
        int[] leftMaxForLenL = new int[A.length - L + 1];
        int[] leftMaxForLenM = new int[A.length - M + 1];
        leftMaxForLenL[0] = sum[L - 1];
        leftMaxForLenM[0] = sum[M - 1];
        for(int i = 0; i < A.length; i ++){
            if(i >= L ){
                leftMaxForLenL[i- L + 1] = Math.max(leftMaxForLenL[ i - L], sum[i] - sum[i - L]);
            }
            if(i >= M ){
                leftMaxForLenM[i - M + 1] = Math.max(leftMaxForLenM[ i - M], sum[i] - sum[i - M]);
            }
        }
        int max = 0;
        for(int i = L + M -1 ; i < A.length; i ++){
            max = Math.max(sum[i] - sum[i - L] + leftMaxForLenM[i - L - M + 1],max);
            max = Math.max(sum[i] - sum[i - M] + leftMaxForLenL[i - L - M + 1],max);
        }
        return max;
    }

    public static void main(String[] args) {

        int[] arr = new int[]{3,8,1,3,2,1,8,9,0};
        System.out.println(maxSumTwoNoOverlap(arr,3,2));
    }
}
