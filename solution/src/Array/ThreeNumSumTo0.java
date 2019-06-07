package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 *
 * Note:
 *
 * The solution set must not contain duplicate triplets.
 *
 * Example:
 *
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 *
 * A solution set is:
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 */

// 暴力法的双指针改进
public class ThreeNumSumTo0 {

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        // -1 0 1
        for(int i  = 0; i < nums.length - 2; i ++ ){
            while (i - 1 >= 0 && i < nums.length - 2 && nums[i - 1] == nums[i]){
                i ++;
            }
            int front = i + 1;
            int end = nums.length - 1;
            int target = 0 - nums[i];
            if(nums[front] > target) {
                continue;
            }
            while (front < end){
                if(nums[front] + nums[end] == target){
                    List<Integer> tmp = Arrays.asList(nums[i], nums[front], nums[end]);
                    front ++;
                    end --;
                    while (front <= end && nums[front] == nums[front - 1]){
                        front ++;
                    }
                    while (front <= end && nums[end] == nums[end + 1]){
                        end --;
                    }
                    ans.add(tmp);
                }
                else if( nums[front] + nums[end] < target){
                    front ++;
                }else {
                    end --;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-1, -1, 2, 0, 1};
        System.out.println(threeSum(nums));
    }
}