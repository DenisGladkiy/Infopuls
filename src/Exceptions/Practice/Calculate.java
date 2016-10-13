package Exceptions.Practice;

import java.util.ArrayList;

/**
 * Created by Денис on 9/24/16.
 */
public class Calculate {

    public int calculate(ArrayList<Integer> numbers, String operation) throws DivByZerroExeption, NumberNotInRangeException {
        int result = 0;
        int a = numbers.get(0);
        int b = numbers.get(1);
        if(a < -1000 || a > 1000){
            throw new NumberNotInRangeException();
        }else if(b < -1000 || b > 1000){
            throw new NumberNotInRangeException();
        }
        if(operation.equals("+")){
            result = a + b;
        }else if(operation.equals("-")){
            result = a - b;
        }else if(operation.equals("*")){
            result = a * b;
        }else if(operation.equals("/")){
            if(b <= 0){
                throw  new DivByZerroExeption();
            }
            result = a / b;
        }else{
            System.out.println("Unknown operation");
        }
        return result;
    }
}
