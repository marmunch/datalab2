package Openhash;

public class Hash {

    final int N = 50; // члены общества
    String[] hash;  // таблица

    // конструктор
    public Hash() {
        hash = new String[N/10];
    }

    // является ли x элементом A
    public boolean memeber(String x) {
        return ifMember(x);
    }

    // обнулить
    public void makenull() {
        /*
        для каждого элемента таблицы
            сделать ячейку пустой нодой
         */
    }

    // удалить x
    public void delete(String x) {
        /*
        для каждого
         */
    }

    // вставить x
    public void insert(String x) {

    }

    // является ли x элементом A
    private boolean ifMember(String x) {
        /*
        до конца таблицы
            пока не достигли конца списка
                если элемент == x вернуть true
        вернуть false
         */
        return false;
    }

    // функция хеширования
    private int h(String x) {
        int code = 0;
        for (int i = 0; i < x.length(); i++) {
            code += x.charAt(i);
        }
        return code % (N/10);
    }
}
