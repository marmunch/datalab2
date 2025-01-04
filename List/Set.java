package List;

public class Set {

    private Node tail; // хвост

    private class Node {

        private int element; // объект
        private Node next; // ссылка на следующий

        // конструктор по Set
        private Node(int element, Node next) {

            this.element = element;
            this.next = next;
        }

        // печать
        private void print() {

            if (element != 0) System.out.println(element);
            else System.out.println("Set is null");
        }
    }

    // конструктор
    public Set() {
        tail = null;
    }

    // копирующий
    public Set(Set copy) {

        this.tail = new Node(copy.tail.element, null);
        Node currentCopy = this.tail;
        Node current = copy.tail.next;

        while (current != copy.tail) {
            //System.out.println(currentCopy.element);
            currentCopy.next = new Node(current.element, this.tail);
            currentCopy = currentCopy.next;
            current = current.next;
        }
    }

    // min
    public int Min() {

        if (tail == null || tail.next == null) return 0;

        return tail.next.element;
    }

    // max
    public int Max() {

        if (tail == null) return 0;

        return tail.element;
    }

    //вывод
    public void print() {

        if (tail == null) {
            System.out.println("Set is EMPTY");
            return;
        }

        Node current = tail.next;

        while (current != tail) {
            System.out.print(current.element + " ");
            current = current.next;
        }
        System.out.println(tail.element);

        System.out.println();
    }

    // объединение множеств
    public Set union(Set B) {

        // проверка на пустые множества и на совпадение множеств
        if (this.tail == null || B.tail == null) return this;

        if (this == B) return this;

        // ставим указатель на меньшее
        Set Aset = this, Bset = B;
        if (this.tail.next.element > B.tail.next.element) {
            Bset = this;
            Aset = B;
        }

        // создаем множество результата
        Set C = new Set(Aset);

        // пока не дойдет до головы A
        Node cur1 = Bset.tail.next;

        while (cur1 != Bset.tail) {
            if (!C.ifMember(cur1.element)) {
                C.addElement(cur1.element, C.searchEl(cur1.element));
                //System.out.println("ADD!");
            }
            cur1 = cur1.next;
        }

        // вставка хвостового элемента B
        if (!C.ifMember(cur1.element)) {
            C.addElement(Bset.tail.element, C.searchEl(Bset.tail.element));
            //System.out.println("ADD!");
        }

        return C;
    }

    // пересечение множеств
    public Set intersection(Set B) {

        // проверка на пустые множества и на совпадение множеств
        if (this.tail == null || B.tail == null) return new Set();

        if (this == B) return this;

        // создаем множество результата
        Set C = new Set();

        // пока не дойдет до головы A
        Node cur1 = this.tail.next;
        Node cur2 = B.tail.next;

        //C.print();

        while (cur1 != this.tail && cur2 != B.tail) {

            // вставка всегда в хвост, первая вставка в пустой список
            if (cur1.element == cur2.element) {
                //System.out.println("YES");
                C.addElement(cur1.element, C.tail);
            }

            //System.out.println(cur1.element + " " + cur2.element + " " + (C.tail==null?null:C.tail.element));

            // смена указателя в зависимости от значения
            if (cur1.element < cur2.element) {
                cur1 = cur1.next;
            }
            else {
                cur2 = cur2.next;
            }
        }

        // обработка хвоста
        // если в 1 списке остались элементы
        while (cur1 != this.tail) {

            if (cur1.element == cur2.element) {
                //System.out.println("YES");
                C.addElement(cur1.element, C.tail);
            }

            cur1 = cur1.next;
        }

        // если во 2 списке остались элементы
        while (cur2 != B.tail) {

            if (cur1.element == cur2.element) {
                //System.out.println("YES");
                C.addElement(cur2.element, C.tail);
            }

            cur2 = cur2.next;
        }

        // сравнение хвостов
        if (cur1.element == cur2.element) {
            //System.out.println("YES");
            C.addElement(cur2.element, C.tail);
        }

        return C;
    }

    // разница множеств
    public Set difference(Set B) {

        // проверка на пустые множества и на совпадение множеств
        if (this.tail == null || B.tail == null) return this;

        if (this == B) return this;

        // создаем множество результата
        Set C = new Set();

        // пока не дойдет до головы A
        Node cur1 = this.tail.next;

        while (cur1 != this.tail) {

            //System.out.println(cur1.element + " " + cur2.element + " " + (C.tail==null?null:C.tail.element));

            // вставка только тех элементов, которых нет в множестве B
            if (!B.ifMember(cur1.element)) {
                C.addElement(cur1.element, C.searchEl(cur1.element));
            }

            cur1 = cur1.next;
        }

        // вставка хвостового элемента
        if (!B.ifMember(this.tail.element)) {
            C.addElement(this.tail.element, C.searchEl(this.tail.element));
            //System.out.println("ADD!");
        }


        return C;
    }

    // объединение множеств, не имеющих общих элементов
    public Set merge(Set B) {

        // не проверяются одинаковые элементы

        // проверка на пустые множества и на совпадение множеств
        if (this.tail == null || B.tail == null) return this;

        if (this == B) return this;

        // ставим указатель на меньшее
        Set Aset = this, Bset = B;
        if (this.tail.next.element > B.tail.next.element) {
            Bset = this;
            Aset = B;
        }

        // создаем множество результата
        Set C = new Set(Aset);

        // пока не дойдет до головы A
        Node cur1 = Bset.tail.next;

        while (cur1 != Bset.tail) {
            C.addElement(cur1.element, C.searchEl(cur1.element));
            //System.out.println("ADD!");
            cur1 = cur1.next;
        }

        // вставка хвостового элемента B
        C.addElement(Bset.tail.element, C.searchEl(Bset.tail.element));

        return C;
    }

    // находится ли x в A
    public boolean member(int x) {

        return ifMember(x);
    }

    // опустошить
    public void makenull() {
        tail = null;
    }

    // вставить элемент в множество
    public void insert(int x) {

        // добавить в tail если список пуст
        if (tail == null) {
            tail = new Node(x, null);
            tail.next = tail;
            return;
        }

        // если голова или хвост уже есть в списке
        if (x == tail.element || x == tail.next.element) return;
        /*
        // 1 элемент в списке
        if (tail == tail.next) {
            // добавление элемента справа от tail
            if (x > tail.next.element) {
                tail.next = new Node(x, tail.next);
                tail = tail.next;
                //System.out.println(2);
                return;
            }


        }*/

        // вставка в голову
        if (x < tail.next.element) {
            tail.next = new Node(x, tail.next);
            return;
        }

        // вставка в хвост
        if (x > tail.element) {
            tail.next = new Node(x, tail.next);
            tail = tail.next;
            //System.out.println("В хвост");
            //System.out.println(tail.element);
            return;
        }

        //System.out.println(tail.next.element + " " + tail.next.next.element);

        // общий случай
        addElement(x, searchEl(x));
        //System.out.println("Общий");
    }

    // удалить элемент из множества
    public void delete(int x) {

        // если список пуст - удалять нечего
        if (tail == null) return;

        // если 1 элемент
        if (tail.next == tail && tail.element == x) {
            tail = null;
            return;
        }

        // удаление хвоста
        if (x == tail.element) {
            Node cur = findPrev();  // метод поиска последнего
            cur.next = tail.next;
            tail = cur;
            return;
        }

        // удаление элемента в списке
        Node lastnode = searchEl(x);
        Node cur = lastnode.next;

        lastnode.next = cur.next;
    }

    // A присвоить B
    public void assign(Set B) {

        // проверка на пустые множества и на совпадение множеств
        if (this.tail == null || B.tail == null || this == B) return;

        this.tail = new Node(B.tail.element, null);
        Node currentCopy = this.tail;
        Node current = B.tail.next;

        while (current != B.tail) {
            //System.out.println(currentCopy.element);
            currentCopy.next = new Node(current.element, this.tail);
            currentCopy = currentCopy.next;
            current = current.next;
        }
    }

    // равенство (те же элементы)
    public boolean equal(Set B) {
        /*
        пока не дойдет до головы
            если a != b return false
        return true
         */

        // проверка на пустые множества и на совпадение множеств
        if (this.tail == null || B.tail == null) return false;

        if (this == B) return true;

        Node current1 = tail.next;
        Node current2 = B.tail.next;

        while (current1 != tail || current2 != B.tail) {
            if (current1.element != current2.element) {
                //System.out.println("FALSE");
                return false;
            }
            current1 = current1.next;
            current2 = current2.next;
        }

        // проверить остались ли элементы после конца цикла
        // проверить оба хвосты и совпадают
        if (tail.element == B.tail.element) return true;

        return false;
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
        if (this.tail == null || B.tail == null || this == B) return "Ошибка";

        if(this.ifMember(x)) return "A";
        if(B.ifMember(x)) return "B";
        else return "-";
    }

    private boolean ifMember(int x) {

        // проверка хвоста
        if (x == tail.element) return true;

        // поиск элемента в списке
        Node cur = tail.next;

        while (cur != tail) {
            if (cur.element == x) return true;
            cur = cur.next;
        }

        return false;
    }

    // проверка на пересечение множеств
    public boolean isInter(Set B) {
        /*
        пока A не голова
            если A == B return true
            если A > B A.next Acount++
            иначе B.next Bcount++
        return (Acount == Bcount)
         */

        Node current1 = tail.next;
        Node current2 = B.tail.next;

        while (current1 != tail && current2 != B.tail) {

            //System.out.println(current1.element + " " + current2.element);

            if (current1.element == current2.element) return true;

            if (current1.element < current2.element) {
                current1 = current1.next;
            }
            else {
                current2 = current2.next;
            }
        }

        // прооверить хвосты
        if (current1.element == current2.element) return true;

        return false;
    }

    // добавить элемент
    private void addElement(int el, Node node) {

        // вставка в пустой список
        if (tail == null) {
            tail = new Node(el, null);
            tail.next = tail;
            return;
        }
        // в хвост
        if (node.element == tail.element) {
            //System.out.println("to tail");
            Node head = tail.next;
            tail.next = new Node(el, head);
            tail = tail.next;
            return;
        }
        // общий случай
        node.next = new Node(el, node.next);
        //System.out.println(el + " " + node.element);
    }

    // найти предпоследний
    private Node findPrev() {

        Node last = tail;
        Node cur = tail.next;

        while (cur != tail) {
            last = last.next;
            cur = cur.next;
        }

        return last;
    }

    // поиск элемента для вставки
    private Node searchEl(int x) {

        if (this.tail == null) return this.tail;

        // если элемент больше хвоста - вставить в хвост
        if (x > this.tail.element) return this.tail;

        // если нет - искать вставку
        Node cur = this.tail.next;
        Node last = this.tail.next;

        while (cur != tail) {
            if (cur.element >= x) return last;
            last = cur;
            cur = cur.next;
        }

        // вставка на место предпоследнего (предшественник tail)
        return last;
    }
}