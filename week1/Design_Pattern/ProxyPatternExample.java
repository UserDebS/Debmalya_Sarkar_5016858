public class ProxyPatternExample {
    public static void main(String[] args) {
        Test test = new Test();
        test.test();
    }
} 

class Test{
    void test() {
        Image image = new ProxyImage("Bird Image");
        image.display();
        image.display();// At the very 1st time caching has been done, so from next time it won't load anymore.
    }
}

interface Image {
    void display();
}

class RealImage implements Image {
    String imagedata;

    RealImage(String data) {
        System.out.println("Loading Image");
        this.imagedata = data;
    }

    public void display() {
        System.out.println(this.imagedata);
    }
}

class ProxyImage implements Image {
    RealImage realImage = null;
    String imagedata;

    ProxyImage(String imagedata) {
        this.imagedata = imagedata;
    }

    public void display() {
        if(this.realImage == null) {
            realImage = new RealImage(imagedata);
        }
        realImage.display();
    }
}

