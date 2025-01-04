import Binary.*;

public class Main {

    public static void main(String[] args) {

        //вывести все public методы, сделать красиво
        Set A = new Set(-30, 60);
        //Set A = new Set();

        System.out.print("A: ");
        //A.print();
        A.insert(-30);
        A.insert(0);
        A.insert(3);
        A.insert(5);
        A.insert(15);
        A.insert(25);
        A.insert(-4);
        A.insert(445);
        A.insert(55);
        A.insert(60);
        A.print();
        System.out.println("MAX: " + A.Max());
        System.out.println("MIN: " + A.Min());
        System.out.println();

        Set B = new Set(0, 80);
        //Set B = new Set();

        System.out.print("B: ");
        //B.print();
        B.insert(3);
        B.insert(5);
        B.insert(50);
        B.insert(55);
        B.insert(33);
        B.print();
        System.out.println("MAX: " + B.Max());
        System.out.println("MIN: " + B.Min());
        System.out.println();

        Set C = A.union(B);
        System.out.print("UNION A U B: ");
        C.print();

        System.out.println("Пересекаются?: " + A.isInter(B));
        if (A.isInter(B)) {
            C = A.intersection(B);
            System.out.print("INTERSECTION A ∩ B: "); // 0 3 5 55
            C.print();
        }

        C = A.difference(B);
        System.out.print("DIFFERENCE A/B: "); // 0 3 5 55
        C.print();

        System.out.println("Пересекаются?: " + A.isInter(B));
        if (!A.isInter(B)) {
            C = A.merge(B);
            System.out.print("MERGE: ");
            C.print();
        }

        System.out.print("MEMBER A U B: ");
        System.out.println(A.member(15));
        System.out.println();

        System.out.println("Пересекаются?: " + A.isInter(B));
        if (!A.isInter(B)) {
            System.out.print("FIND 50 в A или B: ");
            System.out.println(A.find(B, 50));
            System.out.println();
        }

        System.out.print("DELETE 3 и 33 из B: ");
        B.print();
        B.delete(3);
        B.delete(33);
        B.print();

        System.out.print("EQUAL A == B: ");
        System.out.println(A.equal(B));
        System.out.println();

        System.out.print("ASSIGN A = B: ");
        A.print();
        B.print();
        A.assign(B);
        A.print();

        System.out.print("EQUAL A == B: ");
        System.out.println(A.equal(B));
        System.out.println();

        System.out.print("MAKENULL A: ");
        A.print();
        A.makenull();
        A.print();
    }
}