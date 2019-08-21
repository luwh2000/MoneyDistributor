import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        double[] a = new double[4];
        System.out.println("焦子腾:");
        a[0] = scanner.nextDouble();
        System.out.println("Richard:");
        a[1] = scanner.nextDouble();
        System.out.println("William:");
        a[2] = scanner.nextDouble();
        System.out.println("卢文昊:");
        a[3]  = scanner.nextDouble();
        double sum = a[0] + a[1] + a[2] + a[3];
        double each = sum / 4;
        for (int i = 0; i < 4; i++) {
            a[i] = a[i] - each;
        }
        System.out.println("焦子腾:" + a[0] + "\nRichard:" + a[1] + "\nWilliam:" + a[2] + "\n卢文昊:" + a[3]);
    }
}
