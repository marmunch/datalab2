import Openhash.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main2 {

    public static void main(String[] args) {

        Hash good = new Hash();
        Hash bad = new Hash();

        /*good.insert("Masha");
        System.out.println(good.member("Masha"));
        good.delete("Masha");
        System.out.println(good.member("Masha"));*/

        try {
            File file = new File("src/file.txt");
            Scanner scan = new Scanner(file);

            String str = scan.nextLine();

            while (scan.hasNextLine() && str.charAt(0) != 'E') {
                //System.out.println(str);
                char[] name = str.substring(2).toCharArray();
                //System.out.println(array);
                switch (str.charAt(0)) {
                    case 'U':
                        if (good.member(name)) {
                            good.delete(name);
                        }
                        if (!bad.member(name)) {
                            bad.insert(name);
                        }
                        break;
                    case 'F':
                        if (bad.member(name)) {
                            bad.delete(name);
                        }
                        if (!good.member(name)) {
                            good.insert(name);
                        }
                        break;
                    case '?':
                        if (good.member(name)) {
                            System.out.println(str.substring(2) + " is a good guy");
                        } else if (bad.member(name)) {
                            System.out.println(str.substring(2) + " is a bad guy");
                        } else {
                            System.out.println(str.substring(2) + " is ?");
                        }
                        break;
                }
                str = scan.nextLine();
                System.out.println("good: ");
                good.print();
                System.out.println();
                System.out.println("bad: ");
                bad.print();
                System.out.println("/////////");
            }
            scan.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File Not Found!");
        }
        System.out.println("good: ");
        good.print();
        System.out.println();
        System.out.println("bad: ");
        bad.print();
        System.out.println("/////////");
    }
}
