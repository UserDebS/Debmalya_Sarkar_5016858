//Question 1 Data Structure
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//Explain why data structure and algorithms are essential in handling large inventories.
/*
 * Data structure and algorithms is a process to store or structure data in an efficient way, that can ensure easy access, updation and deletion of data so that there won't be any waste of space and time.
 *  In a large inventory the amount of data will be huge. To efficiently access and manipulate them, we need a proper structure to store data so that the data can be maintained easily. If we don't structure them properly, due to the huge size of the data, it will take a lot of time to search, create, update and delete them.
*/

//Suitable data structure for this will be hashmap for faster access and easy manipulation of data.

public class InventoryMS {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventory inventory = new Inventory();
        int choice, id, quantity;
        double price;
        String name;
        while (true) {
            System.out.println(
                    "Press,\n1, to add a new product\n2, to update a product\n3, to delete a product\n4, to exit");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter product name : ");
                    name = scanner.next();
                    System.out.print("Enter product quantity : ");
                    quantity = scanner.nextInt();
                    System.out.print("Enter product price : ");
                    price = scanner.nextDouble();
                    inventory.add(name, quantity, price);
                    break;
                case 2:
                    System.out.print("Enter product ID : ");
                    id = scanner.nextInt();
                    System.out.print("Enter new product name : ");
                    name = scanner.next();
                    System.out.print("Enter new product quantity : ");
                    quantity = scanner.nextInt();
                    System.out.print("Enter new product price : ");
                    price = scanner.nextDouble();
                    inventory.update(id, name, quantity, price);
                    break;
                case 3:
                    System.out.print("Enter product ID : ");
                    id = scanner.nextInt();
                    inventory.delete(id);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Enter a valid choice");
            }
            inventory.print();
        }
    }
}

class Inventory {
    private static int autoGenID = 0;// This will be used to set the id automatically

    private class Product {
        // This product class will contain the details of the product. This represents a
        // single product.
        private int product_id;
        private String product_name;
        private int quantity;
        private double price;

        Product(String p_name, int qnt, double prc) {
            this.product_id = ++autoGenID;
            this.product_name = p_name;
            this.quantity = qnt;
            this.price = prc;
        }

        public void show() {
            System.out.printf("{\n\tProduct ID : %d,\n\tProduct Name : %s,\n\tQuantity : %d,\n\tPrice : %f\n},\n",
                    this.product_id, this.product_name, this.quantity, this.price);
        }

        public void update(String product_name, int qnt, double price) {
            this.product_name = product_name;
            this.quantity = qnt;
            this.price = price;
        }
    }

    private HashMap<Integer, Product> map;// map can access/search each element in constant complexity which here, will help to access data. Later we can easily update and delete the products.

    Inventory() {
        this.map = new HashMap<Integer, Product>();
    }

    void add(String product_name, int quantity, double price) {
        //This will simply create a new product and due to that the autoGenID will be incremented, then we will simply map the autoGenID to the new Product. This will be done in constant time complexity O(1)
        Product product = new Product(product_name, quantity, price);
        map.put(autoGenID, product);
    }

    void update(int product_id, String product_name, int quantity, double price) {
        //This will find the product using product_id. If it exists then it will be updated else the else block will be executed. To access the product, the time complexity will be O(1)
        if (map.containsKey(product_id)) {
            map.get(product_id).update(product_name, quantity, price);
        } else {
            System.out.println("Product doesn't exist.");
        }
    }

    void delete(int product_id) {
        //This will find the product using product_id. If it exists then it will be updated else the else block will be executed. To access the product, the time complexity will be O(1)
        if (map.containsKey(product_id)) {
            map.remove(product_id);
        } else {
            System.out.println("Product doesn't exist.");
        }
    }

    void print() {//O(n) complexity to print the elements.
        for (Map.Entry<Integer, Product> element : map.entrySet()) {
            element.getValue().show();
        }
    }
}