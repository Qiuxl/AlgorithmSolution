package Array;

/**
 * Given an array nums of n integers where n > 1,  return an array output such that output[i]
 *  is equal to the product of all the elements of nums except nums[i].
 *
 * Example:
 *
 * Input:  [1,2,3,4]
 * Output: [24,12,8,6]
 * Note: Please solve it without division and in O(n).
 *
 * Follow up:
 * Could you solve it with constant space complexity? (The output array does not count as
 *  extra space for the purpose of space complexity analysis.)
 */
public class ProductOfArrayExceptSelf {

    /**
     * 不使用除法运算解决此题目，直接在原来的数组上计算
     *
     * @param nums
     * @return
     */
    public static int[] productExceptSelf(int[] nums) {

        if(nums.length <= 1){
            return nums;
        }
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];
        left[0] = 1;
        right[nums.length - 1] = 1;
        for(int i = 1; i < nums.length; i ++){
            left[i] = left[i - 1] * nums[i-1];
            right[nums.length - i - 1] = nums[nums.length - i] * right[nums.length-i];
        }
        for(int i = 0; i < nums.length; i++){
            nums[i] = left[i] * right[i];
        }
        return nums;
    }



    /**
     * 不使用除法运算解决此题目，直接在原来的数组上计算
     *
     *  从左往右乘一次，从右往左算一次
     * @param nums
     * @return
     */
    public static int[] productExceptSelf_ver2(int[] nums) {

        if(nums.length <= 1){
            return nums;
        }
        int[] result = new int[nums.length];
        result[0] = 1;

        for(int i = 1; i < nums.length; i ++){
            result[i] = result[i - 1] * nums[i-1];
        }
        // 从右往左算一次
        int tmp = 1;
        for(int i = nums.length - 1 -1 ; i >= 0; i--){
            tmp *= nums[i+1];
            result[i] = result[i] * tmp;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{9,0,-2};
        for(int i : productExceptSelf_ver2(arr)){
            System.out.println(i);
        }
    }
}
