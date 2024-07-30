public class StrategyPatternExample {
    public static void main(String[] args) {
        Test test = new Test();
        test.test();
    }
}

class Test {
    void test() {
        PaymentContext context = new PaymentContext(new CreditCardPayment());
        context.execute(123, 10000);
        context.changeStrategy(new PayPalPayment());
        context.execute(123, 10000);
    }
}

interface PaymentStrategy {
    void pay(int id, double amount);
}


class CreditCardPayment implements PaymentStrategy {
    public void pay(int accID, double amount) {
        System.out.println("Paying + " + amount + "Rs. to Acc. ID-" + accID + " using Credit Card");
    }
}

class PayPalPayment implements PaymentStrategy {
    public void pay(int accID, double amount) {
        System.out.println("Paying + " + amount + "Rs. to Acc. ID-" + accID + " using PayPal");
    }
}

class PaymentContext {
    private PaymentStrategy strategy;

    PaymentContext(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    void changeStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    void execute(int accID, double amount) {
        this.strategy.pay(accID, amount);
    }
}