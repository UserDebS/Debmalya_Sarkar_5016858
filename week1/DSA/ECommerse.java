//Question 2 Data Structure

//Big O notation is a notation that describes the upper-bound or the worst-case scenario of the complexity of the algorithm. It provides a standardized way to express how to performance of an algorithm scales as the size of input grows.

//This Big O notation can be used to analyse how to efficiently the algorithm will work, as the input size increases. This will help to decide the best data structure to implement, and the best algorithm to manipulate or arrange data in a particular order.

//Describe the best, average and worst case scenario for search operations

/*
 * A search operation's job is to find a particular element or data from a given collection. For example, from a list of product, we need to find a product with an id, 10.
 * 
 * In case of complexity analysis for search operations,
 * 
 * =>Best Case: When the minimum number of operation will be needed to find the required element from a collection.
 * =>Average Case: When the average number of operation will ne needed to find the required element from a collection.
 * =>Worst Case: When the maximum number of operation will be needed to find the required element from a collection.
 */

import java.util.Arrays;
import java.util.Scanner;

public class ECommerse {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        for(int i = 0; i < inventory.maxsize; i++) {
            inventory.products[i] = new Product( "product" + String.valueOf(i + 1), "category" + String.valueOf(i));
        }
        inventory.print();
        Scanner sc = new Scanner(System.in);
        System.out.print("Search id using linear search : ");
        int id = sc.nextInt();
        int index = inventory.linearSearch(id);
        inventory.products[index].show();
        System.out.print("Search id using binary search : ");
        id = sc.nextInt();
        index = inventory.binarySeach(id, 0, inventory.maxsize);
        inventory.products[index].show();
    }
}

class Product {
    private static int autoGenID = 0;
    int product_id;
    String product_name, category;
    Product(String product_name, String category) {
        this.product_id = ++autoGenID;
        this.product_name = product_name;
        this.category = category;
    }

    public void show() {
        System.out.printf("{\n\tProduct ID : %d,\n\tProduct Name : %s,\n\tCatagory : %s,\n},\n",
                this.product_id, this.product_name, this.category);
    }
}

class Inventory {
    Product[] products;
    int maxsize = 10;// For this program, the maximum element, this array can hold is 10.

    Inventory() {
        products = new Product[maxsize];
    }

    int linearSearch(int id) {// Linear search traverse the entire list till we find the required element. So the time complexity will be O(n)
        for (int i = 0; i < this.products.length; i++) {
            if(products[i].product_id == id) return i;
        }
        return -1;
    }

    int binarySeach(int id, int low, int high) { // Binary search follows divide & conquer approach to find the element. The time complexity will be O(logn)
        if (low > high) return -1;
        int mid = (low + high) / 2;
        if ( products[mid].product_id == id) return mid;
        if( products[mid].product_id > id) return binarySeach(id, low, mid);
        return binarySeach(id, mid, high);
    }

    //Unlike linear search which traverse the entire array to search for an element, binary search divides the entire array into two. Then based on the middle element, it then again divides the subarray into two subarrays. Thus the process continues until the targeted element is found. If the array is already sorted then binary search will be the optimal method to search for an element. As linear search's time complexity is O(n) and binary search's time complexity is O(logn).

    //For this program, as the autoGenId will be generated an incremented id for every instanciation, there is no need to sort the array. So binary search will be more efficient in this case.

    void print() {//O(n) complexity to print the elements.
        for(Product product : products) {
            product.show();
        }
    }
}


