package StudsCourses;

public class Student {

    public StudentR[] array;

    public Student (int len) {
        array = new StudentR[len];
    }

    // посчитать хэш
    private int count_hash (char[] x) {
        int result = 0;
        for (int i = 0; i < x.length; i++) {
            result += (int)x[i];
        }
        return result;
    }

    // хэш-функция
    private int hash_function (int x) {
        return x % array.length;
    }

    // сравнение имен
    private boolean isNameEqual (char[] name1, char[] name2) {
        for (int i = 0; i < (Math.min(name1.length, name2.length)); i++)
            if (name1[i] != name2[i])
                return false;
        return true;
    }

    // вставка
    public void insert (char[] name) {

        int h = count_hash(name);
        int start = hash_function(h);

        int find = start;

        int i = 0;
        // пока не найдется место
        while (array[find] != null) {

            // повторно идем по списку - вставки нет
            if (find == start && i != 0)
                return;

            // если имя есть - не вставлять
            if (isNameEqual(name, array[find].name))
                return;

            i++;
            find = hash_function(h + i);
        }
        // если нашли свободное место
        if (array[find] == null)
            array[find] = new StudentR(name);
    }

    // получить объект
    public StudentR get_student (char[] name) {

        int hash = count_hash(name);
        int start = hash_function(hash);
        int find = start;

        int i = 0;
        while (array[find] != null) {

            // повторно идем по списку - вставки нет
            if (find == start && i != 0)
                return null;

            // если имя есть - не вставлять
            if (isNameEqual(name, array[find].name))
                return array[find];

            i++;
            find = hash_function(hash + i);
        }

        return null;
    }

    // вывод
    public void print() {
        for (int i = 0; i < array.length; i++)
            if (array[i] != null)
                System.out.println(array[i].name);
    }
}
