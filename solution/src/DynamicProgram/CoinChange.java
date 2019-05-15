package DynamicProgram;

import java.util.*;

public class CoinChange {
    /**
     * You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins
     *  that you need to make up that amount.If that amount of money cannot be made up by any combination of the coins, return -1.
     *
     * Example 1:
     *
     * Input: coins = [1, 2, 5], amount = 11
     * Output: 3
     * Explanation: 11 = 5 + 5 + 1
     * Example 2:
     *
     * Input: coins = [2], amount = 3
     * Output: -1
     */

    // 第一种做法 f(n) = min(f(i) + f(n-1)) for i = 0 to n/2 超时了，重复了太多计算量
    public static int coinChange1(int[] coins, int amount) {
        if(coins.length == 0){
            return -1;
        }
        Set<Integer> coinSet = new HashSet<>();
        for(int coin : coins){
            coinSet.add(coin);
        }
        int[] result = new int[amount + 1];
        for(int i =1; i <= amount; i++){
            result[i] = -1;
        }
        for(int subAmount = 1; subAmount <= amount; subAmount ++){
            if(coinSet.contains(subAmount)){
                result[subAmount] = 1;
                continue;
            }
            for(int i = 0; i <= subAmount /2; i ++ ){
                if( result[subAmount] == -1 && result[i] != -1 && result[subAmount - i] != -1){
                    result[subAmount] = result[i] + result[subAmount - i];
                }else if(result[subAmount] != -1 && result[i] != -1 && result[subAmount - i] != -1){
                    result[subAmount] = Math.min(result[subAmount], result[i] + result[subAmount - i]);
                }
            }
        }
        return result[amount];
    }

    // 第二种做法，从硬币本身出发
    public static int coinChange2(int[] coins, int amount) {
        if(coins.length == 0){
            return -1;
        }
        // 快速找到一个的情况
        Set<Integer> coinSet = new HashSet<>();
        for(int coin : coins){
            coinSet.add(coin);
        }
        // 为了加速处理
        Arrays.sort(coins);
        int[] result = new int[amount + 1];
        for(int i =1; i <= amount; i++){
            result[i] = -1;
        }
        for(int subAmount = 1; subAmount <= amount; subAmount ++){
            if(coinSet.contains(subAmount)){
                result[subAmount] = 1;
                continue;
            }
            for(int index = coins.length - 1; index >= 0; index --){
                if(subAmount < coins[index]){
                    continue;
                }
                if(result[subAmount - coins[index]] != -1){
                    if(result[subAmount] == -1){
                        result[subAmount] = 1 + result[subAmount -  coins[index]];
                    }else {
                        result[subAmount] = Math.min(result[subAmount], 1 + result[subAmount -  coins[index]]);
                    }
                }
            }
        }
        return result[amount];
    }

    public static void main(String[] args) {
        int[] coins = new int[]{1, 2, 5};
        System.out.println(CoinChange.coinChange2(coins,11));
    }
}
