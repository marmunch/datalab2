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

        int max = copy.end/32;
        mass = new int[max];
        for(int i=0; i<max; i++) {
            mass[i] = 0;
        }
    }

    // объединение множеств
    public Set union(Set B) {
        /*
        условное выражение для определения start и end
        int[] A = copyMove()
        до копирование
        до конца массива
            С[i] = A[i] | B[i]
        после копирование
        вернуть множество С
         */
    }

    // пересечение множеств
    public Set intersection(Set B) {
        /*
        создать множество(функция)
        int[] A = copyMove()
        выделить общую часть
        до конца массива
            С[i] = A[i] & B[i]
        вернуть множество С
         */
    }

    // разница множеств
    public Set difference(Set B) {
        /*
        создать множество(функция)
        int[] A = copyMove()
        скопировать до и после
        в общей части
        до конца массива
            С[i] = A[i] & (~B[i])
        вернуть множество С
         */
    }

    // объединение множеств, не имеющих общих элементов
    public Set merge(Set B) {
        /*
        проверка на пересечения
            выбросить исключение если они есть

        создать множество результат
        int[] A = copyMove()
        до копирование
        до конца массива
            С[i] = A[i] | B[i]
        после копирование
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
            скорректировать 0 (0/32 = 0 всегда), если x = 0 то прибавлять к pos +1
            pos = (abs(this.start) + x)/32
        mass[pos] = mass[pos] | x
         */
    }

    // удалить элемент из множества
    public void delete(int x) {
        /*
        найти элемент массива, в который вставить
            скорректировать 0 (0/32 = 0 всегда), если x = 0 то прибавлять к pos +1
            pos = (abs(this.start) + x)/32
        mass[pos] = mass[pos] & (~x)
         */
    }

    // this присвоить B
    public void assign(Set B) {
        /*
        проверить что this и B не один объект
        проверить размеры
            выделить память
        присвоить новые значения старт и энд
        цикл с 0 до конца
            перенести каждое число с This в B

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
        вернуть - если элемента нет нигде
         */
    }

    // проверка на пересечение множеств
    public boolean isInter(Set B) {
        /*
        если длина не равна, то множества заведомо разные, вернуть false
        совместить нули
        при равной длине проверка на совпадение чисел в цикле с 0 до конца
            если this != B вернуть false
        вернуть true
         */
    }

    // удалить
    private Set createSet(Set B) {
        /*
        минимум и максимум сделать равными как у B
        сравнить их с this и если надо поменять
        вернуть Set(min, max)
         */
    }

    // метод, для проверки нахождения в множестве
    private boolean ifMember(int x) {
        /*
        если x за границами, вернуть false

        найти элемент массива, в который
            скорректировать 0 (0/32 = 0 всегда), если x = 0 то прибавлять к pos +1
            pos = (abs(this.start) + x)/32
        вернуть побитовое И между x и mass[pos]
         */
    }

    // создание копии массива
    private int[] copyMove() {
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

    }
}
