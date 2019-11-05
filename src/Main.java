import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    static String defaultConfig = "config/default.txt";
    public static void main(String args[]) {
        // TODO Create menu
        // TODO Advanced cmdline options with tab selection
        // TODO Able to add and adjust tenant and save to configuration file
        // TODO Database and plotting
        // TODO Better program structure
        ArrayList tenants;
        try {
            tenants = loadConfig(args.length < 1 ? defaultConfig : args[1]);
        } catch (FileNotFoundException err) {
            System.out.printf("Not found\n");
            return;
        }
        calculate(tenants);
    }

    public static ArrayList loadConfig(String configFileDir) throws FileNotFoundException {
        File configFp;
        configFp = new File(configFileDir);
        Scanner sc = new Scanner(configFp);

        ArrayList tenants = new ArrayList<Tenant>();

        while (sc.hasNextLine()) {
            Tenant tenant = new Tenant(sc.next(), sc.nextDouble());
            tenants.add(tenant);
        }
        return tenants;
    }

    public static void saveConfig(ArrayList<Tenant> tenants, String configFileDir) throws IOException {
        File configFp;
        configFp = new File(configFileDir);
        FileWriter configWriter = new FileWriter(configFp);
        for (Tenant tenant:
             tenants) {
            configWriter.write(tenant.toString());
        }
        return;
    }

    public static void calculate(ArrayList<Tenant> tenants) {
        Scanner stdsc = new Scanner(System.in);
        double totalWeight = 0;
        double totalExpenditure = 0;
        Tenant maxPayee = tenants.get(0);
        double maxExpenditure = 0;
        for (Tenant tenant:
             tenants) {
            System.out.printf("%s 开销: \n", tenant.getName());
            double expenditure = stdsc.nextDouble();
            tenant.setExpenditure(expenditure);
            maxExpenditure = maxExpenditure > expenditure ? maxExpenditure : expenditure;
            maxPayee = maxExpenditure > expenditure ? maxPayee : tenant;

            totalExpenditure += expenditure;
            totalWeight += tenant.getWeight();
        }

        System.out.printf("本月开销: %.2f\n", totalExpenditure);

        // TODO Multiple people with same expenditure
        for (Tenant tenant:
             tenants) {
            if (tenant.getName().compareTo(maxPayee.getName()) == 0) {
                // Skip
                 continue;
            }
            double overallExpenditure = totalExpenditure / totalWeight * tenant.getWeight() - tenant.getExpenditure();
            System.out.printf("%s 需支付: %.2f 给 %s\n", tenant.getName(), overallExpenditure, maxPayee.getName());
        }

        return;
    }
}

class Tenant {
    String name;
    double weight;
    double expenditure = 0;

    public Tenant (String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(double expenditure) {
        this.expenditure = expenditure;
    }

    @Override
    public String toString() {
        return String.format("%s %f\n", name, weight);
    }
}
