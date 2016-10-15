package Serialization;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Денис on 10/15/16.
 */
public class ReadFile {
    static List<Payment> payments;
    static String[] fields;

    public static void main(String[] args) {
        File file = new File("E:/java/payments.txt");
        payments = new ArrayList<>();
        try (
            FileInputStream inputStream = new FileInputStream(file);
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(streamReader);){
            String line = bufferedReader.readLine();
            while(null != line){
                fields = line.split("\\|");
               // System.out.println(Arrays.asList(fields));
                Payment payment = new Payment(fields[0], fields[1], fields[2]);
                payments.add(payment);
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        System.out.println(payments);
        serialize(payments, "Payment");
    }

    public static void serialize(List<Payment> payments, String fileName){
        int i = 0;
        for(Payment payment : payments){
            try(ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(fileName + String.valueOf(i) + ".dat"));
            ) {out.writeObject(payment);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            i++;
        }
    }

}
