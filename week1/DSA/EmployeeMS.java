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

    void add(String name, String position, double salary) {
        if(currentsize == maxsize - 1) return;
        employees[++currentsize] = new Employee(name, position, salary);
    }

    int search(int id) {
        for(int i = 0; i <= currentsize; i++) {
            if(employees[i].employeeId == id) return i;
        }
        return -1;
    }

    void traverse() {
        for(int i = 0; i <= currentsize; i++) employees[i].show();
    }

    void delete(int id) {
        int ind = search(id);
        if(ind == currentsize) {
            employees[currentsize--] = null;
            return;
        }
        for(int i = ind; i < currentsize; i++) employees[i] = employees[i + 1];
        employees[currentsize--] = null;
    }
}