
public class CustomerOrders {
    public static void main(String[] args) {
        OrderManager orderManager = new OrderManager();
        orderManager.print();
        orderManager.bubblesort();
        System.out.println("Bubble sorted array");
        orderManager.print();
        System.out.println("Initializing new array");
        orderManager = new OrderManager();
        orderManager.print();
        System.out.println("Quick sorted array");
        orderManager.quicksort(0, orderManager.maxsize - 1);
        orderManager.print();
    }
}

class Order {
    int orderId;
    String customerName;
    double totalPrice;

    Order(String customername, double totalprice) {
        this.orderId = (int)(Math.random() * 100) + 1;
        this.customerName = customername;
        this.totalPrice = totalprice;
    }

    void show() {
        System.out.printf("{\n\tOrder ID : %d,\n\tCustomer Name : %s,\n\tTotal Price : %f\n},\n", this.orderId, this.customerName, this.totalPrice);
    }
}

class OrderManager {
    Order[] orders;
    int maxsize = 10;// For this program, the maximum element, this array can hold is 10.

    OrderManager() {
        orders = new Order[maxsize];
        for(int i = 0; i < maxsize; i++) {
            orders[i] = new Order("order" + String.valueOf(i), Math.random() * 1000 + 1000);
        }
    }

    private void swap(int i, int j) {
        Order temp = orders[i];
        orders[i] = orders[j];
        orders[j] = temp;
    }

    void bubblesort() {
        for(int i = 0; i < maxsize; i++) {
            for(int j = 1; j < maxsize - i; j++) {
                if(orders[j - 1].totalPrice > orders[j].totalPrice) {
                    swap(j - 1, j);
                }
            }
        }
    }

    int partition(int low, int high) {
        int pivot = low, i = low + 1, j = high;
        do {
            while(i < high && orders[i].totalPrice < orders[pivot].totalPrice) ++i;
            while(j > low && orders[j].totalPrice > orders[pivot].totalPrice) --j;
            if(i < j) {
                swap(i, j);
            }
        } while (i < j);
        swap(j, pivot);
        return j;
    }

    void quicksort(int low, int high) {
        if(low < high) {
            int p = partition(low, high);
            quicksort(low, p - 1);
            quicksort(p + 1, high);
        }
    }

    void print() {//O(n) complexity to print the elements.
        for(Order order : orders) {
            order.show();
        }
    }
}
