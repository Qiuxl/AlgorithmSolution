package DesignMode;

public class SingleMode {

    public SingleMode() {
    }


    // 这里最好加上易变的，否则其他线程循环读取缓存的时候，很有可能就有问题了
    private volatile static SingleMode singleMode;


    int num;


    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    /**
     * JVM内部的机制能够保证当一个类被加载的时候，这个类的加载过程是线程互斥的。这样当我们第一次调用getInstance的时候，
     * JVM能够帮我们保证instance只被创建一次，并且会保证把赋值给instance的内存初始化完毕，这样我们就不用担心双重检查锁定的问题。
     *  此外该方法也只会在第一次调用的时候使用互斥机制
     *
     */
    private static class SingletonHolder {
        private static SingleMode instance = new SingleMode();
    }

    public class Inner {

        public void mPrint(){
            System.out.println(num);
        }
    }


    public static SingleMode getInstance(){

        if(singleMode == null){
            synchronized (SingleMode.class){
                if(singleMode == null){
                    singleMode = new SingleMode();
                }
            }
        }
        return singleMode;
    }

    /**
     * 这种单例模式比双重检查锁定的方式更好
     * @return
     */
    public static SingleMode getInstance_ver2(){
        return SingletonHolder.instance;
    }

    public static void main(String[] args) {
        SingleMode singleMode = SingleMode.getInstance();
        singleMode.setNum(10);
        Inner inner = singleMode.new Inner();
        inner.mPrint();
    }

}
