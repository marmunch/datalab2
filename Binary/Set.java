package Binary;

public class Set {

    private int start;  // область определения множества
    private int end;
    private int[] mass;  // массив для хранения
    private int SIZE;  // размер массива
    final private int MASK = 1 << 31;  // маска

    // конструктор
    public Set (int start, int end) {

        this.start = start;
        this.end = end;

        SIZE = (end - start) / 32 + 1; // условное выражение на +1

        mass = new int[SIZE];
        for (int i=0; i<SIZE; i++) {
            mass[i] = 0;
        }
    }

    // копирующий конструктор
    public Set (Set copy) {

        this.start = copy.start;
        this.end = copy.end;
        this.SIZE = copy.SIZE;

        this.mass = new int[copy.SIZE];
        for (int i=0; i<copy.SIZE; i++) {
            this.mass[i] = copy.mass[i];
        }
    }

    // min
    public int Min() {

        int add = Math.round((float)start/32);

        for (int i = 0; i < SIZE; i++) {
            if (mass[i] != 0) {
                int cur = mass[i];
                int count = 0;
                while (Math.abs(cur) != 1) {
                    cur >>= 1;
                    count++;
                    //System.out.println(cur);
                }
                //System.out.println(count);
                return (i + add + 1) * 32 - 1 - count;
            }
        }
        return 0;
    }

    // max
    public int Max() {

        int add = Math.round((float)start/32);

        for (int i = SIZE-1; i >= 0; i--) {
            if (mass[i] != 0) {
                int cur = mass[i];
                int count = 0;
                while (cur != MASK) {
                    cur <<= 1;
                    count++;
                }
                return count + (i+add)*32;
            }
        }
        return 0;
    }

    // объединение множеств
    public Set union(Set B) {

        // проверка на пустые множества и на совпадение множеств
        if (this.isEmpty() || B.isEmpty()) return new Set(this);

        if (this == B) return new Set(this);

        // указатель Аset на this и Bset на B
        //      если b.start < a.start поменять указатели
        Set Aset = this, Bset = B;
        if (Bset.start < Aset.start) {
            Aset = B;
            Bset = this;
        }

        Set C = new Set(Aset.start, Math.max(Bset.end, Aset.end)); //создать множество результат Set C = new Set(min, max)
        copy(Aset, C); // копия Aset в C

        // определить позиции начала Bset и конца самого большого
        int pos1 = position(Aset, Bset.start);
        int pos2 = position(Aset, Bset.end)+1;

        //System.out.println(pos1 + " " + pos2);
        // общая часть
        for (int i = pos1; i < pos2; i++) {
            C.mass[i] |= Bset.mass[i-pos1];
        }

        return C;
    }

    // пересечение множеств
    public Set intersection(Set B) {

        // проверка на пустые множества и на совпадение множеств
        if (this.isEmpty() || B.isEmpty()) return new Set(this.start, this.end);

        if (this == B) return new Set(this.start, this.end);

        // указатель Аset на this и Bset на B
        //      если b.start < a.start поменять указатели
        Set Aset = this, Bset = B;
        if (Bset.start < Aset.start) {
            Aset = B;
            Bset = this;
        }

        // если границы не пересекаются вернуть пустое множество
        if (Aset.end < Bset.start) return new Set(this.start, this.end);

        Set C = new Set(Bset.start, Math.min(Bset.end, Aset.end)); //создать множество результат Set C = new Set(min, max)

        // определить позиции начала Bset и конца самого малого
        int pos1 = position(Aset, Bset.start);

        //System.out.println(C.mass.length + " " + Bset.mass.length);
        // общая часть
        for (int i = 0; i < C.mass.length; i++) {
            //System.out.println(i-pos1 + " " + i);
            //System.out.println(SV0(movedA[i]));
            //System.out.println(SV0(Bset.mass[i]));
            C.mass[i] = Aset.mass[pos1 + i] & Bset.mass[i];
        }

        return C;
    }

    // разница множеств
    public Set difference(Set B) {

        // проверка на пустые множества и на совпадение множеств
        if (this.isEmpty() || B.isEmpty()) return new Set(this);

        if (this == B) return new Set(this);

        // указатель Аset на this и Bset на B
        Set Aset = this, Bset = B;

        Set C = new Set(this); // создать множество результат копию Aset

        // определить позиции начала Bset
        int pos1 = Math.max(position(Aset, Bset.start), 0);

        // общая часть
        for (int i = pos1; i < Aset.mass.length; i++) {
            //System.out.println(SV0(C.mass[i]));
            //System.out.println(SV0(movedB[i]));
            C.mass[i] &= (~Bset.mass[i-pos1]);
        }

        return C;
    }

    // объединение множеств, не имеющих общих элементов
    public Set merge(Set B) {

        // проверка на пустые множества и на совпадение множеств
        if (this.isEmpty() || B.isEmpty()) return new Set(this);

        if (this == B) return new Set(this);

        // указатель Аset на this и Bset на B
        //      если b.start < a.start поменять указатели
        Set Aset = this, Bset = B;
        if (Bset.start < Aset.start) {
            Aset = B;
            Bset = this;
        }

        Set C = new Set(Aset.start, Math.max(Bset.end, Aset.end)); //создать множество результат Set C = new Set(min, max)
        copy(Aset, C); // копия Aset в C

        // определить позиции начала Bset и конца самого большого
        int pos1 = position(Aset, Bset.start);
        int pos2 = position(Aset, Bset.end)+1;

        //System.out.println(pos1 + " " + pos2);
        // общая часть
        for (int i = pos1; i < pos2; i++) {
            C.mass[i] |= Bset.mass[i-pos1];
        }

        return C;
    }

    // находится ли x в A
    public boolean member(int x) {

        return ifMember(x);
    }

    // опустошить
    public void makenull() {
        /*
        с начала до конца массива сделать все числа 0
         */

        for (int i = 0; i < mass.length; i++) {
            mass[i] = 0;
        }
    }

    // вставить элемент в множество
    public void insert(int x) {

        // проверка выхода за границы
        if (x < this.start || x > this.end) return;

        // определение позиции и вставка в нее
        int pos = position(this, x);
        mass[pos] |= move(x - 32*pos); /// сделать вывод
        //x - this.start - 32*pos
    }

    // удалить элемент из множества
    public void delete(int x) {

        // проверка выхода за границы
        if (x < this.start || x > this.end) return;

        // определение позиции и удаление числа
        int pos = position(this, x);
        mass[pos] &= ~move(x - 32*pos);
    }

    // this присвоить B
    public void assign(Set B) {

        // проверка на пустые множества и на совпадение множеств
        if (this.isEmpty() || B.isEmpty() || this == B) return;

        // присвоить новые значения старт и энд
        if (this.start != B.start || this.end != B.end) {
            this.start = B.start;
            this.end = B.end;
        }

        // проверить размеры
        // выделить память для this если они не равны
        if (this.mass.length != B.mass.length) {
            this.mass = new int[B.mass.length];
        }

        // перенести число с B в This
        for (int i = 0; i < this.mass.length; i++) {
            this.mass[i] = B.mass[i];
        }
    }

    // равенство (те же элементы)
    public boolean equal(Set B) {
        /*
        если длины не равны вернуть false
        цикл с 0 до конца
            если элементы не равны, вернуть false
        вернуть true
         */

        // проверка на пустые множества и на совпадение множеств
        if (this.isEmpty() || B.isEmpty()) return false;

        if (this == B) return true;

        for (int i = 0; i < mass.length; i++) {
            if (this.mass[i] != B.mass[i]) return false;
        }

        return true;
    }

    // вернуть имя множества в котором есть x
    public String find(Set B, int x) {
        /*
        проверить, что множества не пересекаются
        ifMember(A) вернуть А
        ifMember(В) вернуть В
        вернуть "-" если элемента нет нигде
         */

        // проверка на пустые множества и на совпадение множеств
        if (this.isEmpty() || B.isEmpty() || this == B) return "Ошибка";

        if(this.ifMember(x)) return "A";
        if(B.ifMember(x)) return "B";
        else return "-";
    }

    // проверка на пересечение множеств
    public boolean isInter(Set B) {
        /*
        проверить границы
        если у меньшего множества граница конца не заходит за начало второго
            вернуть false
        вернуть true
         */

        Set Aset = this, Bset = B;
        if (Bset.start < Aset.start) {
            Aset = B;
            Bset = this;
        }

        if (Aset.end < Bset.start) return false;
        return true;
    }

    // метод, для проверки нахождения в множестве
    private boolean ifMember(int x) {
        /*
        если x за границами, вернуть false

        найти элемент массива, в который вставить
        Position pos = new Position(x)
        вернуть побитовое И между x и mass[pos.pos] != 0
         */

        if (x > this.end || x < this.start) return false;

        int pos = position(this, x);
        //System.out.println(SV0(move(x + this.start)));
        //System.out.println(SV0(mass[pos]));
        //System.out.println(SV0(move(x + this.start) & mass[pos]));
        return (move(x - 32*pos) & mass[pos]) != 0;
    }

    // создание копии массива
    private void copy(Set A, Set C) {

        for (int i = 0; i < A.mass.length; i++) {
            C.mass[i] = A.mass[i];
        }
    }

    // вычисление позиции
    private int position(Set set, int x) {

        // определение значения индекса массива
        double pos = Math.floor((double)(x - set.start) / 32);
        int add = Math.abs((int)Math.floor((double) start/32));

        // если ищем 0, то добавляем отрицательные блоки
        if (x == 0) {
            //System.out.println(pos);
            return (int)pos + add;
        }
        //System.out.println(pos);
        return (int)pos; // иначе возвращаем
    }

    // маска
    private int move(int x) {
        return MASK >>> x;
    }

    private static String SV0(int a) {
        String binaryString = Integer.toBinaryString(a);
        return String.format("%32s", binaryString).replace(' ', '0');
    }

    // проверка на наличие элементов
    private boolean isEmpty() {

        for (int i = 0; i < SIZE; i++) {
            if (mass[i] != 0) return false;
        }

        return true;
    }

    //вывод
    public void print() {

        System.out.println("START:  " + this.start + "  END:  " + this.end);
        int add = Math.round((float)start/32);

        for (int i = 0; i < mass.length; i++) {
            System.out.printf("%-6s", (i + add) * 32);
            System.out.printf(SV0(mass[i]));
            System.out.printf("%6s", (i + add + 1) * 32 - 1);
            System.out.println();
        }

        System.out.println();
    }
}