public class CommandPatternExample {
    public static void main(String[] args) {
        Test test = new Test();
        test.test();
    }
}

class Test {
    void test() {
        Light light = new Light();
        RemoteControl remoteControl = new RemoteControl(new LightOnCommand(light));
        remoteControl.execute();
        remoteControl.setCommand(new LightOffCommand(light));
        remoteControl.execute();
    }
}

interface Command {
    void execute();
}

class LightOnCommand implements Command{
    private Light light;

    LightOnCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        this.light.turnon();
    }
}

class LightOffCommand implements Command{
    private Light light;

    LightOffCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        this.light.turnoff();
    }
}

class RemoteControl {
    private Command command;

    RemoteControl(Command command) {
        this.command = command;
    }

    void setCommand(Command command) {
        this.command = command;
    }

    void execute() {
        this.command.execute();
    }
}

class Light {
    void turnon() {
        System.out.println("Light in on");
    }

    void turnoff() {
        System.out.println("Light is off");
    }
}