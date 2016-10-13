package Exceptions.Practice;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by Денис on 9/24/16.
 */
public class Lesson1 {
    private static Scanner scanner;
    private static Calculate calculator;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        String operation;
        int number = 0;
        ArrayList<Integer> numbers = new ArrayList<>();
        int result = 0;
        while(true){
            if(numbers.size() < 2){
                try {
                    number = scanner.nextInt();
                    numbers.add(number);
                }catch (InputMismatchException ex){
                    //ex.printStackTrace();
                    scanner.next();
                    System.out.println("Введите число");
                }

            }else{
                operation = scanner.next();
                calculator = new Calculate();
                try {
                    result = calculator.calculate(numbers, operation);
                }catch (DivByZerroExeption ex){
                    System.out.println("Деление на ноль");
                }catch (NumberNotInRangeException ex){
                    System.out.println("Числа не лежат в диапазоне -1000...1000");
                }
                System.out.println(result);
                scanner.close();
                break;
            }


        }

    }

}
