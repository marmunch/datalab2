package Chum;
public class Set {
    private Element head; // ссылка на первый элемент

    private class Element extends Child {

        private int num; // уникальный узел
        private int count; // количество предшественников

        public Element(int n, int c) {
            num = n;
            count = c;
            next = null;
            child = null;
        }
        public Element(int n, int c, Element nx) {
            num = n;
            count = c;
            next = nx;
            child = null;
        }
    }
    private class Child {
        public Element next; // ссылка на элемент, который следует за данным
        public Child child; // ссылка на следующий

        public Child(Element el, Child t){
            next = el;
            child = t;
        }
        public Child(Element el){
            next = el;
            child = null;
        }
        public Child() {
            next = null;
            child = null;
        }
    }

    // конструктор
    public Set() {
        head = null;
    }

    public boolean initialization (Pair[] pairs) {

        // проверить на иррефлексивность
        if (pairs[0].prev == pairs[0].next)
            return false;

        // добавить первую пару
        this.head = new Element(pairs[0].prev, 0);
        head.next = new Element(pairs[0].next, 1);
        head.child = new Child(head.next);

        Element temp_prev, temp_next;

        // в цикле идти по массиву пар
        for (int i=1; i < pairs.length; i++) {

            // проверить на иррефлексивность
            if (pairs[i].prev == pairs[i].next)
                return false;

            // проверка на наличие в списке
            temp_prev = isInList(pairs[i].prev);
            temp_next = isInList(pairs[i].next);

            if (temp_prev == null) {
                temp_prev = new Element(pairs[i].prev, 0, this.head);
                this.head = temp_prev;
            }
            if (temp_next == null) {
                temp_next = new Element(pairs[i].next, 0, this.head);
                this.head = temp_next;
            }

            // связать элементы child
            temp_prev.child = new Child(temp_next, temp_prev.child);
            temp_next.count++;
        }
        return true;
    }
    private Element isInList(int el) {
        Element current = head;
        while (current != null) {
            // если совпал num, вернуть сслыку на элемент
            if (current.num == el)
                return current;
            else
                current = current.next;
        }
        return null;
    }
    // закрытый метода поиска элемента с count 0, вернет ссылку на предыдущий или null
    private Element findCount() {
        Element current = head;
        Element previous = null;
        while (current != null) {
            if (current.count == 0) {
                return previous;
            }
            previous = current;
            current = current.next;
        }
        return null;
    }

    // метод, который убирает child у перенесенного элемента
    // ему передается предыдущий
    private void clearChild(Element t) {
        Child current = t.child;
        while (current != null) {
            current.next.count--;
            current = current.child;
        }
    }

    // метод топологической сортировки
    public boolean sort() {

        Element current; // указатель на предыдущий от нужного

        // если у головы count 0
        if (this.head.count == 0)
            current = this.head;
        // иначе найти с count 0
        else {
            current = findCount();
            // null - сортировка невозможна
            if (current == null)
                return false;
        }

        // ссылка на начало отсортированного списка
        Element result = current;
        Element result_current = result;

        // очищаем child
        clearChild(current);

        // удалить элемент или сдвинуть head, next обнулить
        if (this.head.count == 0)
            this.head = this.head.next;
        else
            current.next = current.next.next;

        result_current.count = 0;
        result_current.next = null;
        result_current.child = null;

        // пока head не ноль
        while (head != null) {

            //System.out.println(current.count);
            // если у головы count 0
            if (this.head.count == 0) {
                result_current.next = this.head;
                clearChild(this.head);
                this.head = this.head.next;
            }
            // иначе найти с count 0
            else {
                current = findCount();
                // null - сортировка невозможна
                if (current == null)
                    return false;

                result_current.next = current.next;
                clearChild(current.next);
                current.next = current.next.next;
            }
            result_current.child = null;
            result_current = result_current.next;
        }
        this.head = result;
        return true;
    }

    // печать
    public void print() {

        Element current = this.head;
        String result = "";

        while (current != null) {

            result += " ";
            result += current.num;

            if (current.next != null)
                result += ",";
            current = current.next;
        }

        System.out.println(result);
    }
}