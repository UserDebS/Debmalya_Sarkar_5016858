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

    void add(String taskname, String status) {
        if(head == null) {
            head = new Node(new Task(taskname, status));
            tail = head;
        } else {
            tail.next = new Node(new Task(taskname, status));
            tail = tail.next;
        }
    }

    void search(int id) {
        Node trav = head;
        while(trav != null) {
            if(trav.val.taskId == id) {
                trav.show();
                return;
            }
            trav = trav.next;
        }
    }

    void traverse() {
        Node trav = head;
        while(trav != null) {
            trav.show();
            trav = trav.next;
        }
    }

    void delete(int id) {
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
}