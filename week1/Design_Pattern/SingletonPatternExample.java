public class SingletonPatternExample {
    public static void main(String[] args) {
        Test test = new Test();
        test.test();
    }
}

class Test {
    void test() {
        Logger logger = Logger.getInstance(); 
    }
} 

class Logger{
    private static Logger instance = new Logger();

    private Logger(){}

    public static Logger getInstance(){
        return instance;
    }
}