package Thread;

public class ThreadTest extends Thread {



    private static Runnable runnable = new Runnable() {
        @Override
        public void run() {
            System.out.println("this is runnable");
        }
    };


    public static void main(String[] args) {
        Thread thread = new Thread(runnable);

        thread.start();
    }
}
