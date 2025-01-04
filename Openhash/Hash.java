package Openhash;

public class Hash {

    final int N = 100; // члены общества
    Node[] hash;  // таблица

    // конструктор
    public Hash() {
        hash = new Node[N/10];
    }

    // является ли x элементом A
    public boolean member(char[] x) {
        return ifMember(x);
    }

    // обнулить
    public void makenull() {
        /*
        для каждого элемента таблицы
            сделать ячейку пустым списком
         */

        for (int i = 0; i < hash.length; i++) {
            hash[i] = null;
        }
    }

    // удалить x
    public void delete(char[] x) {
        /*
        хеширование
        если элемент присутствует (ноды там не null)
            удалить
        если элемент остался один и это нужный нам элемент
            сделать пустой список
         */

        if (!ifMember(x)) return;

        int hashedName = h(hashCount(x));

        // 1 элемент
        if (hash[hashedName].next == null) {
            hash[hashedName] = null;
            return;
        }

        // удаление головы
        if (equal(hash[hashedName].name, x)) {
            hash[hashedName] = hash[hashedName].next;
            return;
        }

        // общий случай
        Node last = searchEl(x);
        Node cur = last.next;

        // не нашелся элемент
        if (cur == null) {
            return;
        }

        // удаление последнего
        if (cur.next == null) {
            //System.out.println(last.name);
            //System.out.println(last.next.name);
            last.next = null;
            return;
        }

        last.next = cur.next;
    }

    // вставить x
    public void insert(char[] x) {
        /*
        хеширование
        если элемент присутствует (ноды там не null)
            вставка в конец
        если элемента нет
            создать ноду
         */

        if (ifMember(x)) return;

        int hashedName = h(hashCount(x));

        if (hash[hashedName] != null) {

            Node last = findLast(hashedName);
            last.next = new Node(x, null);
            //System.out.println(hash[hashedName].name);
            //System.out.println(hash[hashedName].next.name);
        }
        else {
            hash[hashedName] = new Node(x, null);
        }
    }

    // поиск последнего
    private Node findLast(int hashedName) {

        Node cur = hash[hashedName];
        Node last = hash[hashedName];

        while (cur != null) {
            last = cur;
            cur = cur.next;
        }

        return last;
    }

    // поиск элемента
    private Node searchEl(char[] x) {
        /*
        хеширование
        вернуть голову если элемента нет
        если элемент присутствует
            пока не достигли конца списка в элементе
                если элемент == x вернуть
        вернуть null
         */

        int hashedName = h(hashCount(x));

        if (hash[hashedName] == null) return hash[hashedName];

        Node cur = hash[hashedName];
        Node last = hash[hashedName];

        while (cur != null) {
            if (equal(cur.name, x)) return last;
            last = cur;
            cur = cur.next;
        }

        return last;
    }

    // является ли x элементом A
    private boolean ifMember(char[] x) {
        /*
        хеширование
        если элемент присутствует (ноды там не null)
            пока не достигли конца списка в элементе
                если элемент equals x вернуть true
        вернуть false
         */

        int hashedName = h(hashCount(x));

        if (hash[hashedName] != null) {

            //обработка головы
            if (equal(x, hash[hashedName].name)) return true;

            Node cur = searchEl(x);
            cur = cur.next;  // находится предыдущий элемент, поэтому переходим на следующий
            if (cur == null) return false;

            return equal(cur.name, x);
        }

        return false;
    }

    // функция хеширования
    public int h(int x) {

        return x % (N/10);
    }

    // хэш значение
    private int hashCount(char[] x) {
        int code = 0;
        for (int i = 0; i < x.length; i++) {
            code += x[i];
        }
        return code;
    }

    // сравнение массивов
    private boolean equal(char[] ch1, char[] ch2) {

        if (ch1.equals(ch2)) return true;

        if (ch1.length != ch2.length) return false;

        for (int i = 0; i < ch1.length; i++) {
            if (ch1[i] != ch2[i]) {
                return false;
            }
        }

        return true;
    }

    // вывод
    public void print() {

        for (int i = 0; i < hash.length; i++) {

            if (hash[i] != null) {

                Node cur = hash[i];

                while (cur != null) {
                    System.out.println(cur.name);
                    cur = cur.next;
                }
            }
        }
    }

    private class Node {

        char[] name;
        Node next;

        // конструктор
        private Node(char[] n, Node next) {
            name = new char[n.length];
            for (int i = 0; i < n.length; i++) {
                name[i] = n[i];
            }
            this.next = next;
        }

        private Node(Node copy) {
            name = new char[copy.name.length];
            for (int i = 0; i < name.length; i++) {
                name[i] = copy.name[i];
            }
            this.next = copy.next;
        }
    }
}
