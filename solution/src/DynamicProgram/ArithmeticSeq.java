package DynamicProgram;

public class ArithmeticSeq {

    /**
     * 等差子序列问题，但是需要注意的是这里是连续的，保证子序列在数组中是连续的，方案O(N^2)
     * @param A
     * @return
     */
    public static int numberOfArithmeticSlices(int[] A) {
        int count = 0;
        for (int s = 0; s < A.length - 2; s++) {
            int d = A[s + 1] - A[s];
            for (int e = s + 2; e < A.length; e++) {
                if (A[e] - A[e - 1] == d)
                    count++;
                else
                    break;
            }
        }
        return count;
    }

    // 4 -> 3, 5 -> 5 - 2 + 5 - 3 + 5 - 4
    public static int numberOfArithmeticSlicesBigOnMethod(int[] A){
        if(A.length <= 2){
            return 0;
        }
        int count = 0;
        int begin = 0;
        int end = 1;
        int j = 0;
        for( j = end + 1; j < A.length; j++){
            int d = A[end] - A[begin];
            if(A[j] - A[j-1] == d ){
                continue;
            }
            // 如果不相等了，则需要看看是不是有连续三个的等差序列了
            if(j - begin > 2 ){
                // 计算个数
                int length = j - begin;
                count += (length - 1) * length - (length + 2) *(length - 1 ) / 2 ;
            }
            begin = j - 1;
            end = j;
        }

        if(j - begin > 2){
            // 计算个数
            int length = j - begin ;
            count += (length - 1) * length - (length + 2) *(length - 1 ) / 2 ;
        }
        return count;
    }




    public static void main(String[] args) {
        System.out.println(ArithmeticSeq.numberOfArithmeticSlices(new int[]{1,2,3,8,9,10}));

        System.out.println(ArithmeticSeq.numberOfArithmeticSlicesBigOnMethod(new int[]{1,2,3,8,9,10}));
    }

}
