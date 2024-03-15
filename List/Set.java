package List;

public class Set {

    private Node tail;

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

    public Set() {
        tail = null;
    }

    // копирующий
    public Set(Set copy) {
        this.tail = new Node(copy.tail.element, copy.tail.next);
    }

    // объединение множеств
    public Set union(Set A, Set B) {
        /*
        создаем множество результата
        пока не дойдет до хвоста
            если элемент А меньше либо равен В - добавить
            иначе добавить В
        в цикле добавить хвосты (если остались)
         */

        Set C = new Set();

        Node curA = A.tail.next;
        Node curB = B.tail.next;
        while (curA != A.tail && curB != B.tail) {

            if (curA.element <= curB.element) {
                C.addElement(curA.element);
                curA = curA.next;
                continue;
            }
            C.addElement(curB.element);
            curB = curB.next;
        }
        while (curA != A.tail.next) {
            C.addElement(curA.element);
            curA = curA.next;
        }
        while (curB != B.tail.next) {
            C.addElement(curB.element);
            curB = curB.next;
        }
        // сортировка? что делать с tail

        return new Set(C);
    }

    // пересечение множеств
    public Set intersection(Set A, Set B) {

        Set C = new Set();

        Node curA = A.tail.next;
        Node curB = B.tail.next;
        while (curA != A.tail && curB != B.tail) {

            if (curA.element == curB.element) {
                C.addElement(curA.element);
                curA = curA.next;
                continue;
            }
            C.addElement(curB.element);
            curB = curB.next;
        }
        if (curA.element == curB.element) {
            C.addElement(curA.element);
        }
        else C.addElement(curB.element);

        return new Set(C);

    }

    // разница множеств
    public Set difference(Set A, Set B) {

        Set C = new Set();

        Node curA = A.tail.next;
        Node curB = B.tail.next;
        while (curA != A.tail && curB != B.tail) {

            if (curA.element < curB.element) {
                C.addElement(curA.element);
                curA = curA.next;
                continue;
            }
            curB = curB.next;
        }
        while (curA != A.tail.next) {
            C.addElement(curA.element);
            curA = curA.next;
        }

        return new Set(C);
    }

    // объединение множеств, не имеющих общих элементов
    public Set merge(Set A, Set B) {

        if ((A&B) != 0) {
            throw new IllegalArgumentException();
        }

        return A|B;
    }

    // находится ли x в A
    public boolean member(Set A, Set x) {

    }

    // опустошить
    public void makenull() {

    }

    // вставить элемент в множество
    public void insert(Set A, int x) {
        /*

         */

    }

    // удалить элемент из множества
    public void delete(Set A, int x) {
        /*

         */

    }

    // B присвоить A
    public void assign(Set A, Set B) {

    }

    // равенство (те же элементы)
    public boolean equal(Set A, Set B) {

    }

    // определить мин и макс и создать множество
    private Set createSet(Set B) {
        /*
        объединить 2 множества

        return new Set(min, max);
         */
        Set C = new Set();

        Node curA = this.tail.next;
        Node curB = B.tail.next;
        while (curA != this.tail && curB != B.tail) {

            if (curA.element <= curB.element) {
                C.addElement(curA.element);
                curA = curA.next;
                continue;
            }

            curA = curA.next;
            curB = curB.next;
        }
    }

    // добавить элемент
    private void addElement(int el) {

        // вставка в пустой список
        if (tail == null) {
            tail = new Node(el, null);
            tail.next = tail;
            return;
        }
        // общий случай
        Node head = tail.next;
        tail.next = new Node(el, head);
        tail = tail.next;
    }
}
