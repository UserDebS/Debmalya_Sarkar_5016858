public class MVCPatternExample {
    public static void main(String[] args) {
        Test test = new Test();
        test.test();
    }
}

class Test {
    void test() {
        Student student = new Student(100, "Maggy Masala", "A");
        StudentController studentController = new StudentController(student);
        studentController.display();
        studentController.update(120, "Maggy Soup", "O");
        studentController.display();
    }
}

class Student {
    int id;
    String name, grade;

    Student(int id, String name, String grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }
}

class StudentView {
    private Student student;

    StudentView(Student student) {
        this.student = student;
    }

    void displayStudentDetails(){
        System.out.printf("Student ID : %d, Student Name : %s, Student Grade : %s\n", this.student.id, this.student.name, this.student.grade);
    }
}

class StudentController {
    private Student model;
    private StudentView view;

    StudentController(Student student) {
        this.model = student;
        this.view = new StudentView(student);
    }  

    void update(int id, String name, String grade) {
        this.model.id = id;
        this.model.name = name;
        this.model.grade = grade;
    }

    void display() {
        this.view.displayStudentDetails();
    }
}