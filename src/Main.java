import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        double[] a = new double[5];
        System.out.println("A房房主:");
        a[0] = scanner.nextDouble();
        System.out.println("B房房主:");
        a[1] = scanner.nextDouble();
        System.out.println("C房房主:");
        a[2] = scanner.nextDouble();
        System.out.println("D房房主:");
        a[3]  = scanner.nextDouble();
        System.out.println("房客");
        a[4] = scanner.nextDouble();
        double sum = a[0] + a[1] + a[2] + a[3] + a[4];
        System.out.println("总共花了:" + sum);
        double each = sum / 4.5;
        System.out.println("每个房主花了:" + each);
        System.out.println("房客花了:" + each * 0.5);
        for (int i = 0; i < 4; i++) {
            a[i] = a[i] - each;
        }
        a[4] = a[4] - each * 0.5;
        System.out.println("A房房主:" + a[0] + "\nB房房主:" + a[1] + "\nC房房主:" + a[2] + "\nD房房主:" + a[3] + "\n房客:" + a[4]);
    }
}
