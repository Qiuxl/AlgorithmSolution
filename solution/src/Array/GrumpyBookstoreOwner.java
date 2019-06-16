package Array;

public class GrumpyBookstoreOwner {



    public static int maxSatisfied(int[] customers, int[] grumpy, int X) {

        int current = 0;
        for(int i = 0; i < customers.length; i ++){
            if(grumpy[i] == 0){
                current += customers[i];
            }
        }
        int currentInc = 0;
        for(int i = 0; i < X; i ++){
            if(grumpy[i] == 1){
                currentInc += customers[i];
            }
        }
        int maxInc = currentInc;
        for(int i = X; i < customers.length; i ++){
            if(grumpy[i-X] == 1){
                currentInc -= customers[i-X];
            }
            if(grumpy[i] == 1){
                currentInc += customers[i];
            }
            maxInc = Math.max(currentInc,maxInc);
        }
        return maxInc + current;
    }

    public static void main(String[] args) {
        int[] customers = new int[]{1,0,1,2,1,1,7,5};
        int[] grumpy = new int[]{0,1,0,1,0,1,0,1};
        System.out.println(maxSatisfied(customers,grumpy,3));
    }
}
