package stack;

import java.util.Stack;

/**
 * Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days you would have to wait until a warmer temperature.
 *
 * If there is no future day for which this is possible, put 0 instead.
 *
 * For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].
 *
 * Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].
 */
public class DailyTemperatures {


    public static int[] dailyTemperatures(int[] T) {

        int[] result =  new int[T.length];
        // 存的是下标
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < T.length; i ++){

            if(stack.empty() || T[stack.peek()] >= T[i]){
                stack.push(i);
            }else {
                // 每次弹出去一个的时候需要更新底下的年龄
                while (!stack.empty() && T[stack.peek()] < T[i]){
                    int index = stack.pop();
                    result[index] ++;
                    if(!stack.empty()){
                        result[stack.peek()] += result[index] ;
                    }
                }
                stack.push(i);
            }
        }
        if(!stack.isEmpty()){
            while (!stack.isEmpty()){
                result[stack.pop()] = 0;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] testArr = new int[]{4,3,2,1,4};
        int[] result = dailyTemperatures(testArr);
        for (int item : result){
            System.out.print(item + " ");
        }
    }
}
