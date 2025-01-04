package Closehash;

import java.util.Arrays;

public class Hash {

    final int N = 10; // члены общества
    char[][] hash;  // таблица

    // конструктор
    public Hash() {
        hash = new char[N][];
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

        // если элемента нет - ничего не делать
        if (!ifMember(x)) return;

        // найти элемент, если он -1 то значит его там нет
        int hashedName = searchEl(x);
        if (hashedName == -1) return;

        // удаление элемента
        hash[hashedName][0] = Character.MIN_VALUE;
    }

    // вставить x
    public void insert(char[] x) {

        // найти элемент, в который ранее ничего вставлено не было
        int i = 0;
        int code = hashCount(x);
        int hashedName = h(code);
        int free = -1;

        while (hash[hashedName] != null) {

            // сравнение есть ли такой элемент
            if (equal(hash[hashedName], x)) {
                return;
            }

            // сохранить удаленный
            if (hash[hashedName][0] == Character.MIN_VALUE && free == -1) {
                free = hashedName;
            }

            i++;
            hashedName = h(code + i);

            // прошли по кругу
            if (hashedName == h(code)) {
                break;
            }
        }

        // вставка в удаленный
        if (free != -1) {
            hash[free] = x;
            return;
        }

        // если нет ни удаленных ни пустых мест
        if (hashedName == h(code) && hash[hashedName] != null) {
            return;
        }

        // вставка
        hash[hashedName] = x;
    }

    // поиск вставки
    private int searchEl(char[] x) {
        /*
        хеширование
        если элемент присутствует
            пока не достигли конца списка в элементе
                если элемент == x вернуть хеш
        вернуть deleted если хеш найден не был
         */

        int i = 0;
        int code = hashCount(x);
        int hashedName = h(code);
        int deleted = -1;

        while (hash[hashedName] != null) {

            if (hash[hashedName][0] == Character.MIN_VALUE) deleted = hashedName;

            if (equal(hash[hashedName], x)) return hashedName;
            i++;
            hashedName = h(code + i);

            if (hashedName == h(code)) break;
        }
        //System.out.println(deleted);

        return deleted;
        // вставка в удаленную позицию
        // Метод посимвольного сравнения
    }

    // является ли x элементом A
    private boolean ifMember(char[] x) {

        int hashedName = searchEl(x);

        if (hashedName == -1) return false;

        return equal(hash[hashedName], x);
    }

    // функция хеширования
    private int h(int x) {

        return x % (N);
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
        for (int i = 0; i < N; i++) {
            if (hash[i] != null)
                System.out.println(hash[i]);
        }
    }
}
