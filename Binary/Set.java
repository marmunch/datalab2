package Binary;

public class Set {

    private int start;  // область определения множества
    private int end;
    private int[] mass;  // массив для хранения
    final private int SIZE = 10;  // размер массива

    // конструктор
    public Set (int start, int end) {

        this.start = start;
        this.end = end;

        mass = new int[SIZE];
        for (int i=0; i<SIZE; i++) {
            mass[i] = 0;
        }
    }

    // копирующий конструктор
    public Set (Set copy) {

        this.start = copy.start;
        this.end = copy.end;

        mass = new int[SIZE];
        for (int i=0; i<SIZE; i++) {
            mass[i] = copy.mass[i];
        }
    }

    // объединение множеств
    public Set union(Set B) {
        /*
        указатель Аset на this и Bset на B
        если b.start < a.start поменять указатели

        создать множество результат Set C = new Set(min, max)
        копия Aset с коррекцией по 0

        с 0 до позиции Bset.start в С или до конца Aset
            копировать в C Aset

        с позиции Bset.start в С до позиции Math.min(end) в С
            С[i] = Aset_copy[i-Bset.start] | Bset[i-Bset.start]

        если b.end > a.end
            с Math.min(end) до B.length копировать в С
        иначе
            с Math.min(end) до А.length копировать в С

        вернуть множество С
         */

        Set C = new Set(Math.min(this.start, B.start), Math.max(this.end, B.end));

        return C;
    }

    // пересечение множеств
    public Set intersection(Set B) {
        /*
        указатель Аset на this и Bset на B
        если b.start < a.start поменять указатели

        создать множество результат Set C = new Set(min, max)
        копия Aset с коррекцией по 0

        с Aset.start до Math.min(end)
            С[i] = Aset_copy[i] & Bset[i]

        вернуть множество С
         */

        Set C = new Set(Math.min(this.start, B.start), Math.max(this.end, B.end));

        return C;
    }

    // разница множеств
    public Set difference(Set B) {
        /*
        указатель Аset на this и Bset на B
        если b.start < a.start поменять указатели

        создать множество результат Set C = new Set(min, max)
        копия Aset с коррекцией по 0

        с 0 до позиции Bset.start в С или до конца Aset
            копировать в C Aset

        с позиции Bset.start в С до позиции Math.min(end) в С
            С[i] = Aset_copy[i-Bset.start] & (~Bset[i-Bset.start])

        с Math.min(end) до А.length копировать в С

        вернуть множество С
         */
        
        Set C = new Set(Math.min(this.start, B.start), Math.max(this.end, B.end));
        C.mass = this.copyMove();

        for (int i = 0; i < B.mass.length(); i++) {
            C.mass[i] = C.mass[i] & (~B.mass[i]);
        }

        return C;
    }

    // объединение множеств, не имеющих общих элементов
    public Set merge(Set B) {
        /*
        проверка на пересечения
            выбросить исключение если они есть

        указатель Аset на this и Bset на B
        если b.start < a.start поменять указатели

        создать множество результат Set C = new Set(min, max)
        копия Aset с коррекцией по 0

        с 0 до конца Aset
            копировать в C Aset

        если b.end > a.end
            с Math.min(end) до B.length копировать в С
        иначе
            с Math.min(end) до А.length копировать в С

        вернуть множество С
         */
    }

    // находится ли x в A
    public boolean member(int x) {
        /*
        если x за границами области, вернуть false

        ifMember()
         */
    }

    // опустошить
    public void makenull() {
        /*
        с начала до конца массива сделать все числа 0
         */
    }

    // вставить элемент в множество
    public void insert(int x) {
        /*
        найти элемент массива, в который вставить
        Position pos = new Position(x)
        mass[pos.pos] = mass[pos.pos] | x
         */
    }

    // удалить элемент из множества
    public void delete(int x) {
        /*
        найти элемент массива, в который вставить
        Position pos = new Position(x)
        mass[pos.pos] = mass[pos.pos] & (~x)
         */
    }

    // this присвоить B
    public void assign(Set B) {
        /*
        проверить что this и B не один объект
        проверить размеры
            выделить память для this если они не равны
        присвоить новые значения старт и энд
        цикл с 0 до конца
            перенести число с B в This

        копирующий конструктор
         */
    }

    // равенство (те же элементы)
    public boolean equal(Set B) {
        /*
        если длины не равны вернуть false
        совместить нули
        цикл с 0 до конца
            если элементы не равны, вернуть false
        вернуть true
         */
    }

    // вернуть имя множества в котором есть x
    public String find(Set B, int x) {
        /*
        проверить, что множества не пересекаются
        ifMember(A) вернуть А
        ifMember(В) вернуть В
        вернуть "-" если элемента нет нигде
         */
    }

    // проверка на пересечение множеств
    public boolean isInter(Set B) {
        /*
        совместить нули
        проверка на совпадение чисел в цикле с 0 до конца
            если this != B вернуть false
        вернуть true
         */
    }

    // метод, для проверки нахождения в множестве
    private boolean ifMember(int x) {
        /*
        если x за границами, вернуть false

        найти элемент массива, в который вставить
        Position pos = new Position(x)
        вернуть побитовое И между x и mass[pos.pos]
         */
    }

    // создание копии массива со сдвигом (скорректировать 0)
    private int[] copyMove(Set B) {
        /*
        копируем This в A
        temp = 0
        до конца массива A
            копируем темп в i элемент
            A[i] сдвигаем влево на модуль (A.start - B.start)
            temp = this[i] >> модуль(32 - (A.start - B.start))
        вернуть A
         */
    }

    //вывод
    public void print() {

        for (int j : mass) {
            System.out.println(Integer.toBinaryString(j));
        }
    }

    private class Position {

        int pos;

        private Position(Set set, int position) {
            /*
            скорректировать 0 (0/32 = 0 всегда), если x = 0 то прибавлять к pos +1
            set.pos = (abs(set.start) + x)/32
             */
        }
    }
}
