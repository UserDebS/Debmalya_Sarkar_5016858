//Question 4
//An array is a linear data structure where elements of same data type are stored in memory in a contiguous manner. It uses the 0th indexed memory location to relatively address the other elements of the array.

//Array is efficient when it comes to accessing an element from the collection because of index addressing.

import java.util.Scanner;
 
public class EmployeeMS {
    public static void main(String[] args) {
        EmployeeMSystem eMSystem = new EmployeeMSystem();
        Scanner scanner = new Scanner(System.in);
        int choice, id;
        String name, position;
        double salary;
        while(true) {
            System.out.println(
                    "Press,\n1, to add an employee\n2, to search an employee\n3, to traverse\n4, to delete an employee\n5, to exit");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter employee name : ");
                    name = scanner.next();
                    System.out.print("Enter employee position : ");
                    position = scanner.next();
                    System.out.print("Enter employee salary : ");
                    salary = scanner.nextDouble();
                    eMSystem.add(name, position, salary);
                    break;
                case 2:
                    System.out.print("Enter employee ID : ");
                    id = scanner.nextInt();
                    eMSystem.employees[eMSystem.search(id)].show();
                    break;
                case 3:
                    eMSystem.traverse();
                    break;
                case 4:
                    System.out.print("Enter employee ID : ");
                    id = scanner.nextInt();
                    eMSystem.delete(id);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Enter a valid choice");
            }
        }
    }
}

class Employee {
    int employeeId;
    String name, position;
    double salary;

    Employee(String name, String position, double salary) {
        this.employeeId = (int) (Math.random() * 100) + 1;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    void show() {
        System.out.printf("Employee ID : %d, Name : %s, Position : %s, Salary : %f\n", this.employeeId, this.name, this.position, this.salary);
    }
}

class EmployeeMSystem {
    Employee[] employees;
    int maxsize = 100;
    int currentsize;
    EmployeeMSystem() {
        employees = new Employee[maxsize];
        currentsize = -1;
    }

    void add(String name, String position, double salary) {//The time complexity of this operation will be O(1) as it needs to just increment the currentsize element to address the next index and update that.
        if(currentsize == maxsize - 1) return;
        employees[++currentsize] = new Employee(name, position, salary);
    }

    int search(int id) {//This method is using linear search as, the id will be random and not in sorted order. So linear search will need to be used here. The time complexity will be O(n)
        for(int i = 0; i <= currentsize; i++) {
            if(employees[i].employeeId == id) return i;
        }
        return -1;
    }

    void traverse() {//The time complexity of this operation will be O(n) as it linearly traverse through entire array.
        for(int i = 0; i <= currentsize; i++) employees[i].show();
    }

    void delete(int id) {// The general time complexity of this operation will be O(n) as it will need to left shift all the elemenets at left side to fill the deleted element.
        int ind = search(id);
        if(ind == currentsize) {
            employees[currentsize--] = null;
            return;
        }
        for(int i = ind; i < currentsize; i++) employees[i] = employees[i + 1];
        employees[currentsize--] = null;
    }
    //Arrays are only good for accessing elements from a collection. But the array is static in size(That means that only a certain number of elements can be stored here, not more than that), it has a size limit. If we try to make a dynamic sized array, that will be pretty hectic and inefficient. Other than that, deleting an element is also inefficient in array as we need to left shift all the right side elements, whose complexity will be O(n). For these reasons, arrays should only be used where there is only fixed number of elements required to store and they are read only.
}
