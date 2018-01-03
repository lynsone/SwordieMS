package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created on 1/3/2018.
 */
public class MakeHeaders {

    public static void main(String[] args) {
        File file = new File("D:\\SwordieMS\\Swordie\\src\\headerText.txt");
        try {
            Scanner scanner = new Scanner(file);
            String s = "";
            int num = 0;
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(line.contains("case") && line.contains(":")) {
                    String s2 = line.replace(":", "");
                    String s3 = s2.replaceAll(" ", "");
                    String s4 = s3.replace("case", "");

                    num = Integer.parseInt(s4);
                } else if(line.contains("CWvsContext")) {
                    System.out.println(line);
                    String op = line.split("[(|:]")[2].replace(":", "");
                    StringBuilder newOp = new StringBuilder();
                    for(char c : op.toCharArray()) {
                        if(Character.isUpperCase(c)) {
                            newOp.append("_").append(c);
                        } else {
                            newOp.append(c);
                        }
                    }
                    String fin = newOp.toString().toUpperCase().substring(1).replace("ON_","");
                    s += fin + "(" + num + "), \r\n";
                }
            }
            System.out.println(s);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
