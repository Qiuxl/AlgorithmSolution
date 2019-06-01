package DynamicProgram;

/**
 * 01背包问题
 */
public class ZeroOnePackProblem {

    /**
     * 对于每一个物品，可以选择拿，也可以选择不拿。如果从这一点入手，那么可以计算出 max(F(n,w-1), F(n-w[i],w-1) + value[i]), 拿的时候的，以及不拿时候的价值取最大
     * @param totalWeight 背包的容量
     * @param numOfGold 物品的数量
     * @param valueOfEachGold 物品的价值
     * @param weightOfEachGold 物品的重量
     * @return
     */
    public static int getMaxGold(int totalWeight, int numOfGold, int[] valueOfEachGold, int[] weightOfEachGold){

        int[][] dp = new int[numOfGold+1][totalWeight+1];
        for(int i = 0; i <= totalWeight; i ++){
            dp[0][i] = 0;
        }
        for(int i = 1; i <= numOfGold; i ++){
            for(int j =1; j <= totalWeight; j ++){
                // 目前的重量还放不下第i个物体
                if(j < weightOfEachGold[i-1]){
                    dp[i][j] = dp[i][j-1];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - weightOfEachGold[i-1]] + valueOfEachGold[i-1]);
                }
            }
        }
        return dp[numOfGold][totalWeight];
    }

    // 优化版，由于每一次只需要查看上一行（没有第j物体的时候）的状态即可，因此我们不需要存放整个表格

    public static int getMaxGold(int totalWeight, int[] valueOfEachGold, int[] weightOfEachGold){

        int[] maxValueWhenWithoutLastGold = new int[totalWeight+1];
        int[] maxValueWhenWithLastGold = new int[totalWeight+1];

        // 初始化只有一个物体的时候的状态
        for(int i = 1; i <= totalWeight; i ++){
            if(i < weightOfEachGold[0]){
                maxValueWhenWithoutLastGold[i] = 0;
            }else {
                maxValueWhenWithoutLastGold[i] = valueOfEachGold[0];
            }
        }
        for( int i = 1; i < weightOfEachGold.length; i++){
            for(int j = 1; j <= totalWeight; j++){
                if(j < weightOfEachGold[i]){
                    maxValueWhenWithLastGold[j] = maxValueWhenWithoutLastGold[j];
                }else {
                    maxValueWhenWithLastGold[j] = Math.max(maxValueWhenWithoutLastGold[j],maxValueWhenWithoutLastGold[j-weightOfEachGold[i]] + valueOfEachGold[i]);
                }
            }
            int[] temp = maxValueWhenWithoutLastGold;
            maxValueWhenWithoutLastGold = maxValueWhenWithLastGold;
            maxValueWhenWithLastGold = temp;
        }
        return maxValueWhenWithoutLastGold[totalWeight];
    }

    public static void main(String[] args) {
        int[] weightOfEachGold = new int[]{5, 5, 3, 4, 3};
        int[] valueOfEachGold = new int[]{400, 500, 200, 300, 350};

        System.out.println(getMaxGold(10,5,valueOfEachGold,weightOfEachGold));

        System.out.println(getMaxGold(10,valueOfEachGold,weightOfEachGold));
    }
}
