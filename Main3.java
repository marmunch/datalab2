import Chum.*;

public class Main3 {

    public static void main(String[] args) {

        Pair[] pairs = new Pair[] {
                new Pair(1, 2),
                new Pair(2, 4),
                new Pair(2, 10),
                new Pair(1, 3),

                new Pair(6, 3),
                new Pair(4, 6),
                new Pair(4, 8),
                new Pair(9, 4),

                new Pair(5, 8),
                new Pair(7, 9),
                new Pair(7, 5),
                new Pair(9, 10),

                new Pair(3, 5)
        };

        Set L = new Set();
        L.initialization(pairs);
        System.out.print("Несортированное");
        L.print();
        boolean s = L.sort();
        System.out.println("Можно ли отсортировать: " + s);
        if (s) {
            System.out.print("Сортированное");
            L.print();
        }
    }
}