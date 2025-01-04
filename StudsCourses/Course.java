package StudsCourses;

public class Course {

    public CourseR[] array;

    // конструктор
    public Course (int len) {
        array = new CourseR[len];
    }

    // хеш-функция
    private int hash_function (int x) {
        return x % array.length;
    }

    // вставка
    public void insert (int c) {

        int start = hash_function(c);
        int find = start;

        int i = 0;
        // поиск места вставки
        while (array[find] != null) {

            // если повторно начинаем идти по списку - значит вставки нет
            if (find == start && i != 0)
                return;

            // если такая вставка уже есть - ничего не вставлять
            if (c == array[find].name)
                return;

            i++;
            find = hash_function(c + i);
        }

        // если нашли свободное место
        if (array[find] == null)
            array[find] = new CourseR(c);

    }

    // получить номер курса
    public CourseR get_course (int n) {

        int start = hash_function(n);
        int find = start;

        int i = 0;
        // пока не дойдем до конца
        while (array[find] != null) {

            // если повторно начинаем идти по списку - значит курса нет
            if (find == start && i != 0)
                return null;

            // если нашли курс
            if (n == array[find].name)
                return array[find];

            i++;
            find = hash_function(n + i);
        }

        return null;
    }

    // вывод всех курсов
    public void print() {
        for (int i = 0; i < array.length; i++)
            if (array[i] != null)
                System.out.println(array[i].name);
    }
}