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

        this.tail = new Node(copy.tail.element, copy.tail.next);
        Node currentCopy = copy.tail.next;
        Node current = tail;
        while (currentCopy != copy.tail) {
            current.next = new Node(currentCopy.element, currentCopy);
            currentCopy = currentCopy.next;
            current = current.next;
        }
    }

    // объединение множеств
    public Set union(Set B) {
        /*
        использовать 2 объекта

        создаем множество результата
        пока не дойдет до головы
            если элемент А меньше либо равен В - добавить
            иначе добавить В
        пока А не null
            добавить А
        пока B не null
            добавить B
        вернуть результат
         */
    }

    // пересечение множеств
    public Set intersection(Set B) {
        /*
        использовать 2 объекта

        создаем множество результата
        пока не дойдет до головы
            если элемент А равен В - добавить A
        вернуть результат
         */

    }

    // разница множеств
    public Set difference(Set B) {
        /*
        использовать 2 объекта

        создаем множество результата
        пока не дойдет до головы
            если элемент А < В - добавить A, A = A.next
            иначе B = B.next
        пока А не null
            добавить А
        вернуть результат
         */
    }

    // объединение множеств, не имеющих общих элементов
    public Set merge(Set B) {
        /*
        проверка на пересечения
            выбросить искллючение если они есть

        создать множество результат
        пока не дойдет до головы
            если элемент А меньше либо равен В - добавить
            иначе добавить В
        пока А не null
            добавить А
        пока B не null
            добавить B
        вернуть результат
         */

    }

    // находится ли x в A
    public boolean member(Set x) {
        /*
        пока не дойдет до головы
            если элемент А == x вернуть true
        вернуть false
         */
    }

    // опустошить
    public void makenull() {
        tail = null;
    }

    // вставить элемент в множество
    public void insert(int x) {
        /*
        проверка на пустоту
            добавить в tail
        поиск места
        пока не дойдет до головы
            проверка элемента на присутствие
                если x > элемента сделать addElement(x, thisnode)
                сохранять предыдущий
         */

    }

    // удалить элемент из множества
    public void delete(int x) {
        /*
        проверка на последний элемент
            tail = null
        поиск места
        пока не дойдет до головы
            если элемент присутствует
            если x == элемент сделать node.next = node.next.next
            return
         */

    }

    // B присвоить A
    public void assign(Set B) {
        /*
        проверка на неодинаковость элементов

        копирующий конструктор
         */
    }

    // равенство (те же элементы)
    public boolean equal(Set B) {
        /*
        пока не дойдет до головы
            если a != b return false
        return true
         */
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
        if (node == tail) {
            Node head = tail.next;
            tail.next = new Node(el, head);
            tail = tail.next;
        }
        // общий случай
        node = new Node(el, node);
    }
}
