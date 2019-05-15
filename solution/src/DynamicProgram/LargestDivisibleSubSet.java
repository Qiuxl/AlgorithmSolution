package DynamicProgram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LargestDivisibleSubSet {

    /**
     * 给出集合 [1,2,3]，找出最大子集，使得子集中两两可mod为0, 类似于最长递增子序列，但是需要保持另外一个性质，可mod
     * @param nums
     * @return
     */

    public List<Integer> largestDivisibleSubset(int[] nums) {

        if(nums.length == 0){
            return Collections.emptyList();
        }
        if(nums.length == 1){
            return Arrays.asList(nums[0]);
        }
        // 正确的做法是每个数记录这以它为结尾的最大集合，并且指向上一个坐标
        Arrays.sort(nums);
        int[] previous = new int[nums.length];
        int[] setLength = new int[nums.length];
        for(int i = 0; i < nums.length; i ++){
            previous[i] = i;
            setLength[i] = 1;
        }
        int maxLength = 0;
        int maxStart = 0;
        for(int i = 1; i < nums.length; i ++){
            for(int j = 0; j < i; j ++){
                if( nums[i] % nums[j] == 0 && setLength[j] + 1 > setLength[i]){
                    previous[i] = j;
                    setLength[i] = setLength[j] + 1;
                }
            }
            if(setLength[i] > maxLength){
                maxStart = i;
                maxLength = setLength[i];
            }
        }
        List<Integer> result = new ArrayList<>();
        while (maxStart != previous[maxStart]){
            result.add(nums[maxStart]);
            maxStart = previous[maxStart];
        }
        result.add(nums[maxStart]);
        Collections.reverse(result);
        return result;
    }

    public static void main(String[] args) {

        LargestDivisibleSubSet largestDivisibleSubSet = new LargestDivisibleSubSet();
        System.out.println(largestDivisibleSubSet.largestDivisibleSubset(new int[]{1,3,9,18,54,90,108,180,360,540,720}));
    }
}
