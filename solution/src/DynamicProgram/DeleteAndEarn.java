package DynamicProgram;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array nums of integers, you can perform operations on the array.
 *
 * In each operation, you pick any nums[i] and delete it to earn nums[i] points. After,
 * you must delete every element equal to nums[i] - 1 or nums[i] + 1.
 *
 * You start with 0 points. Return the maximum number of points you can earn by applying such operations.
 *
 * Example 1:
 *
 * Input: nums = [3, 4, 2]
 * Output: 6
 * Explanation:
 * Delete 4 to earn 4 points, consequently 3 is also deleted.
 * Then, delete 2 to earn 2 points. 6 total points are earned.
 *
 * The length of nums is at most 20000.
 * Each element nums[i] is an integer in the range [1, 10000].
 */
public class DeleteAndEarn {

    public static int deleteAndEarn(int[] nums) {
        Map<Integer,Integer> numCountMap = new HashMap<>();
        for(int num : nums){
            if(numCountMap.containsKey(num)){
                numCountMap.put(num, numCountMap.get(num) + num);
            }else {
                numCountMap.put(num, num);
            }
        }
        int pre = -1;
        int a1 = 0;
        int a2 = 0;
        int a3 = 0;
      //  Object[] objects = numCountMap.keySet().toArray();
       // Arrays.sort(objects);
        for(Object o : numCountMap.keySet()){
            Integer i = (Integer) o;
            if( i - 1 == pre){
                a3 = Math.max(a1 + numCountMap.get(i), a2);
            }else {
                a3 = a2 + numCountMap.get(i);
            }
            pre = i;
            a1 = a2;
            a2 = a3;
        }
        return a3;
    }


    /**
     * 事实证明，对象的构造和析构远比数学运算慢得多
     * @param nums
     * @return
     */
    public static int deleteAndEarnVer2(int[] nums) {

        int[] count = new int[10001];
        for(int num : nums){
            count[num] ++;
        }
        int pre = -1;
        int a1 = 0;
        int a2 = 0;
        int a3 = 0;
        for(int i = 1; i <= 10000; i ++){
            if( count[i] > 0) {
                if (i - 1 == pre) {
                    a3 = Math.max(a1 + i * count[i], a2);
                } else {
                    a3 = a2 + i * count[i];
                }
                pre = i;
                a1 = a2;
                a2 = a3;
            }
        }
        return a3;
    }

    // 研究一下Integer的自增会不会重新构建对象
    public static void main(String[] args) {

        Integer integer = 300;
        System.out.println(System.identityHashCode(integer));
        integer ++;
        System.out.println(System.identityHashCode(integer));

        System.out.println(deleteAndEarn(new int[]{ 3 , 5, 8 }));
        System.out.println(deleteAndEarnVer2(new int[]{ 3 , 5, 8 }));
    }
}
