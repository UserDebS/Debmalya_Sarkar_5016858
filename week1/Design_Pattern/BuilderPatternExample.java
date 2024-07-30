public class BuilderPatternExample {
    public static void main(String[] args) {
        Test test = new Test();
        test.test();
    }
}

class Test {
    void test() {
        Computer.Builder builder = new Computer.Builder();
        builder.setCPU("Foxin");
        builder.setRAM("8GB");
        builder.setStorage("200GB");
        Computer computer = builder.build();
        computer.getdetails();
    }
}

class Computer {
    private String CPU, RAM, Storage;

    private Computer(Builder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.Storage = builder.storage;
        System.out.println("Computer has been built");
    }

    void getdetails() {
        System.out.printf("CPU : %s, RAM : %s, Storage : %s\n", this.CPU, this.RAM, this.Storage);
    }

    static class Builder {
        String CPU, RAM, storage;
        void setCPU(String cpu) {
            this.CPU = cpu;
            System.out.println("CPU has been set");
        }
    
        void setRAM(String ram) {
            this.RAM = ram;
            System.out.println("RAM has been set");
        }
    
        void setStorage(String storage) {
            this.storage = storage;
            System.out.println("Storage has been set");
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}