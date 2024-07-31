public class AdapterPatternExample {
    public static void main(String[] args) {
        Test test = new Test();
        test.test();
    }
}  

class Test {
    void test() {
        PaymentProcessor paypalpaymentProcessor = new PaypalAdapter(new PayPal());
        PaymentProcessor googlepaymentProcessor = new GooglePayAdapter(new GooglePay());
        paypalpaymentProcessor.processPayment(123, 45000);
        googlepaymentProcessor.processPayment(456, 100000);
    }
}

interface PaymentProcessor {
    void processPayment(int upi, double amount);
}

class PayPal {
    void sendMoney(int upi, double amount) {
        System.out.println("Sending " + amount + "Rs. to PayPal UPI-" + upi);
    }
}

class GooglePay {
    void sendMoney(int upi, double amount) {
        System.out.println("Sending " + amount + "Rs. to GooglePay UPI-" + upi);
    }
}

class PaypalAdapter implements PaymentProcessor{
    private PayPal payPal;

    PaypalAdapter(PayPal payPal){
        this.payPal = payPal;
    }

    public void processPayment(int upi, double amount) {
        this.payPal.sendMoney(upi, amount);
    }
}

class GooglePayAdapter implements PaymentProcessor {
    private GooglePay googlePay;

    GooglePayAdapter(GooglePay googlePay) {
        this.googlePay = googlePay;
    }

    public void processPayment(int upi, double amount){
        this.googlePay.sendMoney(upi, amount);
    }
}