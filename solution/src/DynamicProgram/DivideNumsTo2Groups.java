package DynamicProgram;

/**
 * We have a collection of rocks, each rock has a positive integer weight.
 *
 * Each turn, we choose any two rocks and smash them together.  Suppose the stones have weights x and y with x <= y.
 *  The result of this smash is:
 *
 * If x == y, both stones are totally destroyed;
 * If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.
 * At the end, there is at most 1 stone left.  Return the smallest possible weight of this stone (the weight is 0 if there are no stones left.)
 *
 *
 *
 * Example 1:
 *
 * Input: [2,7,4,1,8,1]
 * Output: 1
 * Explanation:
 * We can combine 2 and 4 to get 2 so the array converts to [2,7,1,8,1] then,
 * we can combine 7 and 8 to get 1 so the array converts to [2,1,1,1] then,
 * we can combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
 * we can combine 1 and 1 to get 0 so the array converts to [1] then that's the optimal value.
 *
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 100
 */
public class DivideNumsTo2Groups {

    // 实际上是求将数组分为两部分，使得两部分的和差值最小

    public static  int lastStoneWeightII(int[] stones) {

        boolean[] sumCanArchive = new boolean[3001];
        int currentMaxSum = 0;
        sumCanArchive[0] = true;
        for(int stone : stones){
            for(int j = currentMaxSum; j >= 0; j --) {
                if(sumCanArchive[j]) {
                    sumCanArchive[j + stone] = true;
                    if (j == currentMaxSum) {
                        currentMaxSum = currentMaxSum + stone;
                    }
                }
            }
        }
        for(int j = currentMaxSum / 2; j >= 0; j --){
            if(sumCanArchive[j]){
               return currentMaxSum - 2*j;
            }
        }
        return currentMaxSum;
    }

    public static void main(String[] args) {
        System.out.println(lastStoneWeightII(new int[]{8, 2, 4, 4, 8}));
    }
}
