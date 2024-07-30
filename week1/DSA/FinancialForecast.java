import java.util.Scanner;

public class FinancialForecast {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FinancialForecast fc = new FinancialForecast();
        double presentVal, rate;
        int tenure;
        System.out.print("Enter Present Value : ");
        presentVal = sc.nextDouble();
        System.out.print("Enter Rate : ");
        rate = sc.nextDouble();
        System.out.print("Enter Tenure : ");
        tenure = sc.nextInt();
        System.out.println(presentVal * fc.ForeCast(rate, tenure));
    }

    double ForeCast(double rate, int year) {
        if(year == 0) return 1;
        return (1 + rate) * ForeCast(rate, year - 1);
    }
}
