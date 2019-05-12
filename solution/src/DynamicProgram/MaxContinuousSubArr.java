package DynamicProgram;

public class MaxContinuousSubArr {

    public int maxSubArray(int[] nums) {

        if(nums.length == 1){
            return nums[0];
        }
        // 每一个数，记住往左边看最大的值, 这里实际上也是动态规划的思想
        // 这里还是需要寻找到关于动归的子问题，以 i 为结尾的连续数组的最大和，可以被下一个数使用
        // dp[i] = max(a[i], a[i] + dp[i -1])
        int[] max2Left = new int[nums.length + 1];
        max2Left[0] = 0;
        int maxSum = nums[0];
        for(int i = 0; i < nums.length; i++){
            max2Left[i + 1] = nums[i] > nums[i] + max2Left[i]  ?  nums[i] : nums[i] + max2Left[i];
            if(max2Left[i + 1] > maxSum){
                maxSum = max2Left[i +1];
            }
        }
        return maxSum;
    }

    public int maxProfit(int[] prices) {
        // 每个数，记住左边最小的值
        if( prices.length <= 1){
            return 0;
        }
        int min = prices[0];
        int maxProfit = 0;
        for( int price : prices){
            if (min > price){
                min = price;
            }
            if( price - min > maxProfit){
                maxProfit =  price - min;
            }
        }
        return maxProfit;
    }

    public boolean divisorGame(int N) {

        boolean[] win = new boolean[N + 2];
        win[1] = false;
        win[2] = true;
        for(int i = 3; i <= N; i++){
            if(!win[i-2]){
                win[i] = true;
                continue;
            }
            for(int j = 1; j <= i/2 && i%j==0; j++){
                if(!win[i-j]){
                    win[i] = true;
                    break;
                }
            }
            win[i] = false;
        }
        return win[N];
    }

    public static void main(String[] args) {
        int[] testArr = { 8, -19, 5, -4, 20};
        System.out.println(new MaxContinuousSubArr().divisorGame(3));
    }
}
