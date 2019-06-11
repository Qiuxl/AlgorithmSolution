package bit;

public class CountingBits {

    public static int[] countBits(int num) {

        int[] result = new int[num + 1];
        if(num == 0){
            return result;
        }
        result[1] = 1;
        int temp;
        for(int i = 2; i <= num; i++){
            temp = i;
            while (( temp & 1 ) == 0){
                temp = temp >> 1;
            }
            temp = temp >> 1;
            result[i] = result[temp] + 1;
        }
        return result;
    }

    public static void main(String[] args) {

        int[] result = countBits(5);
        for(int item : result) {
            System.out.println(item);
        }
    }
}
