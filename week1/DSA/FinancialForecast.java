//Question 7

//Recursion is a process where a function or method calls itself until a condition is satisfied. This recursion must have a terminating condition and a way to reach the terminating condition to avoid infinte recursion.
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
        //The recurrence relation for this method will be T(n) = T(n - 1) + 1 until n = 0
        /*
            T(n - 1) = T(n - 2) + 1
            ...
            T(n) = T(n - 2) + 2
            ...
            T(n) = T(n - k) + k
            we need n = 0
            n - k = 0
            n = k
            T(n) = T(n - n) + n = T(0) + n
            so, T(n) = O(n), this will be our time complexity for this recursive operation.
         */
        if(year == 0) return 1;
        return (1 + rate) * ForeCast(rate, year - 1);
    }

    //Recursion is mostly used in divide and conquer where we divide a bigger problem into smaller subproblems and then solve/ conquer it separately. Then we merge them. This will certainly reduce the computation process by working on smaller problems rather than one big problem which will need more computation power. Thus recursion helps in reduction of computation power. 
}
