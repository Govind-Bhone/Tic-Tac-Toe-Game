/**
 * Created by govind.bhone on 6/5/2017.
 */
public class Solution {
    public static void main(String args[]) {
        String str = "Ip-34 hh 7 kk 8";

        String tempOutput = "";
        int sum = 0;

        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                tempOutput += c;
            } else {
                if (tempOutput.length() > 0) {
                    sum += Integer.parseInt(tempOutput);
                }
                tempOutput = "";
            }

        }

        //for boundary condition
        if (tempOutput.length() > 0)
            sum += Integer.parseInt(tempOutput);
        System.out.println(sum);


        //approach 2 -considering spaces

        String str1 = "12 abc 67 hhhds 45";
        int sum2 = 0;
        String[] arr = str1.split("\\s+");

        for (String s : arr) {
            if (s.matches("-?\\d+(\\.\\d+)?")) {
                sum2 += Integer.parseInt(s);
            }
        }
        System.out.println(sum2);
    }
}
