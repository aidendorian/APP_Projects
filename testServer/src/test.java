import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Arithmetic {

    public Integer sum(Integer[] ints) {
        int totalSum = 0;
        for (Integer num : ints) {
            totalSum += num;
        }
        return totalSum;
    }

    public String sum(String[] strings) {
        StringBuilder concatenatedString = new StringBuilder();
        for (String str : strings) {
            concatenatedString.append(str);
        }
        return concatenatedString.toString();
    }
}

public class test {
    public static void main(String[] args ) throws Exception {
        Scanner rx = new Scanner(System.in);
        String a = rx.nextLine();

    }
}