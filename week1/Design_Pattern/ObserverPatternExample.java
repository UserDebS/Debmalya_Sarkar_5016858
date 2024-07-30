import java.util.ArrayList;

public class ObserverPatternExample {
    public static void main(String[] args) {
        Test test = new Test();
        test.test();
    }
}

class Test{
    void test() {
        Stock stock = new StockMarket();
        Observer observer1 = new MobileApp(),
                 observer2 = new WebApp();
        stock.register(observer1);
        stock.register(observer2);
        stock.setPrice(10000);
        stock.setPrice(10500);
    }
}

interface Stock {
    void register(Observer observer);
    void deregister(Observer observer);
    void notifyOb();
    void setPrice(double price);
}

interface Observer {
    void update(double price);
}

class StockMarket implements Stock{
    private ArrayList<Observer> list = new ArrayList<Observer>();
    private double price;

    public void register(Observer observer) {
        list.add(observer);
    }
    public void deregister(Observer observer) {
        list.remove(observer);
    }
    public void notifyOb() {
        for(Observer observer : list) observer.update(price);
    }

    public void setPrice(double price) {
        this.price = price;
        notifyOb();
    }
}

class MobileApp implements Observer {
    private double price;
    public void update(double price) {
        this.price = price;
        System.out.println("MobileApp : " + this.price);
    }

}

class WebApp implements Observer {
    private double price;
    public void update(double price) {
        this.price = price;
        System.out.println("WebApp : " + this.price);
    }
}
