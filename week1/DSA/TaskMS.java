//Question 5

//Linked list is a linear data structure. It is a collection of nodes where each nodes hold a value and the link/connection to the next node.

//A singly linked list is a type of linked list where each nodes holds a data variable and the link/connection to the next node. Here, traversal to one direction/ to end of the list is possible only.

//A doublely linked list is a type of linked list where each nodes holds a data variable and the link/connection to the previous node and the next node. Here traversal is bi-directional. That means we can traverse both left and right from any node.
import java.util.Scanner;

public class TaskMS {
    static int autoGenId = 100;
    public static void main(String[] args) {
        TaskLinkedList ll = new TaskLinkedList();
        Scanner scanner = new Scanner(System.in);
        int choice, id;
        String name, status;
        while(true) {
            System.out.println(
                    "Press,\n1, to add a task\n2, to search a task\n3, to traverse\n4, to delete a task\n5, to exit");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter task name : ");
                    name = scanner.next();
                    System.out.print("Enter task status : ");
                    status = scanner.next();
                    ll.add(name, status);
                    break;
                case 2:
                    System.out.print("Enter task ID : ");
                    id = scanner.nextInt();
                    ll.search(id);
                    break;
                case 3:
                    ll.traverse();
                    break;
                case 4:
                    System.out.print("Enter task ID : ");
                    id = scanner.nextInt();
                    ll.delete(id);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Enter a valid choice");
            }
        }
    }    
}

class Task {
    int taskId;
    String taskName, status;
    
    Task(String taskname, String status) {
        this.taskId = TaskMS.autoGenId++;
        this.taskName = taskname;
        this.status = status;
    }

    void show() {
        System.out.printf("Task ID : %d, Task Name : %s, Task Status : %s\n", this.taskId, this.taskName, this.status);
    }
}

class TaskLinkedList {
    private class Node {
        Task val;
        Node next;
        Node(Task val) {
            this.val = val;
            this.next = null;
        }

        void show() {
            this.val.show();
        }
    }

    Node head, tail;
    TaskLinkedList() {
        this.head = null;
        this.tail = head;
    }

    void add(String taskname, String status) {//As we are using a tail node, the time complexity for this operation will be O(1)
        if(head == null) {
            head = new Node(new Task(taskname, status));
            tail = head;
        } else {
            tail.next = new Node(new Task(taskname, status));
            tail = tail.next;
        }
    }

    void search(int id) {//As we need to linearly traverse the linked list, the time complexity for this operation will be O(n)
        Node trav = head;
        while(trav != null) {
            if(trav.val.taskId == id) {
                trav.show();
                return;
            }
            trav = trav.next;
        }
    }

    void traverse() {//As binary search is not possible in linked list, we need to linearly search the linked list, the time complexity for this operation will be O(n)
        Node trav = head;
        while(trav != null) {
            trav.show();
            trav = trav.next;
        }
    }

    void delete(int id) {// As we need to first find the element, the time complexity for this operation will be O(n)
        if(head == null) return;
        if(head.val.taskId == id) {
            head = head.next;
            return;
        }
        Node trav = head;
        while(trav.next != null) {
            if(trav.next.val.taskId == id) {
                trav.next = trav.next.next;
                if(trav.next == null) tail = trav;
                return;
            }
            trav = trav.next;
        }
    }
    //Unlike array linked list can store dynamic data, as it allocates memory for a node in run time then set the value of the node. But in array is was not possible. That's why there won't be any waste of memory here, which was present in array by allocating huge size of memory in compile time and not using the entire space.
}   