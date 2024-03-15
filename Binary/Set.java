package Binary;

public class Set {

    protected int start;
    protected int end;
    protected int[] mass;
    final int SIZE = 10;

    // конструктор
    public Set(int start, int end) {

        this.start = start;
        this.end = end;

        mass = new int[SIZE];
        for (int i=0; i<SIZE; i++) {
            mass[i] = 0;
        }
    }

    // копирующий конструктор
    public Set(Set copy) {
        this.start = copy.start;
        this.end = copy.end;

        int max = copy.end/32;
        mass = new int[max];
        for(int i=0; i<max; i++) {
            mass[i] = 0;
        }
    }

    // объединение множеств
    public Set union(Set B) {
        /*
        создать множество(функция)
        до конца массива
            С = A|B
        вернуть множество С
         */

        Set C = this.createSet(B);
        for (int i=0; i < C.mass.length; i++) {
            C.mass[i] = this.mass[i]|B.mass[i];
        }

        return new Set(C);
    }

    // пересечение множеств
    public Set intersection(Set B) {
        /*
        создать множество(функция)
        до конца массива
            С = A&B
        вернуть множество С
         */

        Set C = this.createSet(B);
        for (int i=0; i < C.mass.length; i++) {
            C.mass[i] = this.mass[i]&B.mass[i];
        }

        return new Set(C);
    }

    // разница множеств
    public Set difference(Set B) {
        /*
        создать множество(функция)
        до конца массива
            С = A&(~B)
        вернуть множество С
         */

        Set C = this.createSet(B);
        for (int i=0; i < C.mass.length; i++) {
            C.mass[i] = this.mass[i]&(~B.mass[i]);
        }

        return new Set(C);
    }

    // объединение множеств, не имеющих общих элементов
    public Set merge(Set B) {
        /*
        проверка на пересечения
            выбросить искллючение если они есть
        создать множество результат
        в цикле объединение, аналогичное в UNION
        вернуть множество
         */

        if (!this.isInter(B)) {
            throw new IllegalArgumentException();
        }

        Set C = this.createSet(B);
        for (int i=0; i < C.mass.length; i++) {
            C.mass[i] = this.mass[i]|B.mass[i];
        }

        return new Set(C);
    }

    // находится ли x в A
    public boolean member(int x) {
        /*
        если x за границами области, то его там очевидно нет
        делаем переменную со сдвигом
        если при операции И получется та же переменная, число есть
        иначе нет
         */

        if (x < this.start || x > this.end) return false;

        int bit = 1<<(Math.abs(x-this.start));

        return (bit & this.mass[x / 32]) == bit;
    }

    // опустошить
    public void makenull() {
        /*
        с начала до конца массива сделать все числа 0
         */

        int max = this.end/32;
        for(int i=0; i<max; i++) {
            this.mass[i] = 0;
        }
    }

    // вставить элемент в множество
    public void insert(int x) {
        /*
        если элемент есть в множестве, ничего не делать
        найти элемент массива, в который вставить
        C = A|x
         */

        // элемент уже в множестве
        if (this.ifMember(x)) return;

        // общий случай
        int pos = x/32;
        this.mass[pos] = this.mass[pos] | x;
    }

    // удалить элемент из множества
    public void delete(int x) {
        /*
        если элемента нет в множестве, ничего не делать
        найти элемент массива, в котором x
        C = A&(~x)
         */

        if (!this.ifMember(x)) return;

        // общий случай
        int pos = x/32;
        this.mass[pos] = this.mass[pos] & (~x);
    }

    // this присвоить B
    public void assign(Set B) {
        /*
        проверить что this и B не один объект
        присвоить новые значения старт и энд
        цикл с 0 до конца
            перенести каждое число с В в This
         */

        if (this == B) return;

        this.start = B.start;
        this.end = B.end;

        int max = B.end/32;
        this.mass = new int[max];
        for(int i=0; i<max; i++) {
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

        if (this.mass.length != B.mass.length) return false;

        for (int i=0; i<this.mass.length; i++) {
            if (this.mass[i] != B.mass.length) return false;
        }

        return true;
    }

    // вернуть имя множества в котором есть x
    public String find(Set B, int x) {
        /*
        проверить, что множества не пересекаются
        ifMember(A) вернуть А
        ifMember(В) вернуть В
         */

        if (this.isInter(B)) return "Неверные данные";

        if (this.ifMember(x)) return "A";
        if (B.ifMember(x)) return "B";

        return "-";
    }

    // проверка на пересечение множеств
    private boolean isInter(Set B) {

        // если длина не равна, то множества заведомо разные
        if (this.mass.length != B.mass.length) return false;

        // при равной длине проверка на совпадение чисел
        for (int i=0; i < this.mass.length; i++) {
            if (this.mass[i] != B.mass[i]) return false;
        }

        return true;
    }

    // определить мин и макс и создать множество
    private Set createSet(Set B) {
        /*
        определить минимум и максимум
        вернуть новое множество с min и max
         */

        int min = B.start, max = B.start;
        if(this.start < B.start) min = this.start;
        if(this.end > B.end) max = B.end;

        return new Set(min, max);
    }

    // метод, для проверки нахождения в множестве
    private boolean ifMember(int x) {
        /*
        если x за границами, то в множество не входит
        найти элемент массива
        если побитовое И между x и элементом массива == x, то вернуть true
         */

        if (this.start > x || this.end < x) return false;

        int pos = x/32;
        return (this.mass[pos] & x) == x;
    }

    //вывод бинарный через printf
}
