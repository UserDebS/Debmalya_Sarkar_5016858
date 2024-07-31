public class DecoratorPatternExample {
    public static void main(String[] args) {
        Test test = new Test();
        test.test();
    }
}
  
class Test {
    void test() {
        Notifier emailNotifier = new SMSNotifierDecorator(new EmailNotifier());
        Notifier smsNotifier = new SlackNotifierDecorator(new EmailNotifier());
        emailNotifier.send();
        smsNotifier.send();
    }
}

interface Notifier {
    void send();
}

class EmailNotifier implements Notifier {
    public void send() {
        System.out.println("An Email has been received.");
    }
}

abstract class NotifierDecorator implements Notifier{
    protected Notifier notifier;

    NotifierDecorator(Notifier notifier) {
        this.notifier = notifier;
    }

    public void send() {
        this.notifier.send();
    }
}

class SMSNotifierDecorator extends NotifierDecorator{
    
    SMSNotifierDecorator(Notifier notifier) {
        super(notifier);
    }

    public void send() {
        System.out.println("<>SMS Decorator<>");
        notifier.send();
        System.out.println("<>SMS Decorator<>");
    }
}

class SlackNotifierDecorator extends NotifierDecorator{
    SlackNotifierDecorator(Notifier notifier) {
        super(notifier);
    }

    public void send() {
        System.out.println("-Slack Decorator-");
        notifier.send();
        System.out.println("-Slack Decorator-");
    }
}