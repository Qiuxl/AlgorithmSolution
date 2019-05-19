package DynamicProgram;

public class MaxProfit {

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

    /**
     * 可以参与尽可能多的交易，买入之前不能持有东西
     * Input: [7,1,5,3,6,4]
     * Output: 7
     * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
     *              Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
     *
     * @param prices
     * @return
     */
    public int maxProfit_ver2(int[] prices) {
        if(prices.length == 0){
            return 0;
        }
        int profit = 0;
        int i = 0;
        while (i < prices.length){
            for( ; i < prices.length && i+1 < prices.length && prices[i+1] <= prices[i] ; i++){}
            if(i == prices.length - 1){
                break;
            }
            int bought = prices[i];
            // 往右边找递增的
            i ++;
            for( ;  i < prices.length && i+1 < prices.length && prices[i+1] >= prices[i]; i++) {}
            profit += prices[i] - bought;
            i ++;
        }
        return profit;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5};
        System.out.println(new MaxProfit().maxProfit_ver2(arr));
    }
}
