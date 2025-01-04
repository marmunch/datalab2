package StudsCourses;

public class ManyToMany {

    public Course courses;
    public Student students;

    public ManyToMany(Course c, Student s) {
        courses = c;
        students = s;
    }

    // возвращает предыдущий элемент в кольце курсов
    private Abstr prev_course_in_circle (StudentR student, CourseR course) {

        Abstr prev_course = course;
        Abstr current = course.course_circle;

        while (current.is_registration()) {
            Regist r = (Regist) current;
            Abstr s = r.student_circle;
            while (s.is_registration()) {
                s = ((Regist) s).student_circle;
            }
            if (s == student)
                break;
            prev_course = current;
            current = r.course_circle;
        }
        return prev_course;
    }

    // возвращает предыдущий элемент в кольце студентов
    private Abstr prev_student_in_circle (StudentR student, CourseR course) {

        Abstr prev_student = student;
        Abstr current = student.student_circle;

        while (current.is_registration()) {
            Regist r = (Regist) current;
            Abstr с = r.course_circle;
            while (с.is_registration()) {
                с = ((Regist) с).course_circle;
            }
            if (с == course)
                break;
            prev_student = current;
            current = r.student_circle;
        }
        return prev_student;
    }

    // разделить на boolean и на поиск конкретной записи
    // наличие связи между курсом и студентом, вернуть объект
    private Regist connection (StudentR s, CourseR c) {

        // на таком курсе нет студентов или у такого студента нет курсов
        if (s.student_circle == null || c.course_circle == null)
            return null;

        // идти по студенту и искать переданный курсс
        Abstr current = s.student_circle;
        Abstr j;
        Regist r;

        // пока не пройдем круг по кольцу
        while (current.is_registration()) {
            r = (Regist) current;
            j = r.course_circle;
            if (studentCircle(j).name == c.name)
                return r;
            current = r.student_circle;
        }

        return null;
    }

    private CourseR studentCircle(Abstr j) {
        while (j.is_registration())
            j = ((Regist) j).course_circle;

        return (CourseR) j;
    }

    // есть ли такая связь, вернуть true false
    private boolean isConnection (StudentR s, CourseR c) {

        // на таком курсе нет студентов или у такого студента нет курсов
        if (s.student_circle == null || c.course_circle == null)
            return false;

        // идти по студенту и искать переданный курсс
        Abstr current = s.student_circle;
        Abstr j;
        Regist r;

        // пока не пройдем круг по кольцу
        while (current.is_registration()) {
            r = (Regist) current; // преобразуем тип
            j = r.course_circle;
            if (studentCircle(j).name == c.name)
                return true;
            current = r.student_circle;
        }

        return false;
    }

    // добавить студента s на курс c
    public void addStudentToCourse (char[] s, int c) {

        // получить ссылки на студента и курс
        StudentR student = students.get_student(s);
        CourseR course = courses.get_course(c);

        // если их нет - вернуться
        if (student == null || course == null)
            return;

        // если у такого студента и курса еще нет связи
        if (!isConnection(student, course)) {
            // создать связь
            Regist temp = new Regist(student.student_circle, course.course_circle);

            // если у студента еще нет курсов
            if (student.student_circle == null)
                temp.student_circle = student;
            student.student_circle = temp;

            // если на курсе еще нет студентов
            if (course.course_circle == null)
                temp.course_circle = course;
            course.course_circle = temp;
        }
    }

    // удалить студента s c курса c
    public void removeStudentFromCourse (char[] s, int c) {

        // получить ссылки на студента и курс
        StudentR student = students.get_student(s);
        CourseR course = courses.get_course(c);

        // если их нет - вернуться
        if (student == null || course == null)
            return;

        // если у такого студента нет курсов
        Regist connection = connection(student, course);

        if (isConnection(student, course)) {

            Abstr prev_student, prev_course;

            prev_student = prev_student_in_circle(student, course);
            // если у студента один курс
            if (prev_student == connection.student_circle)
                student.student_circle = null;
            else {
                if (prev_student.is_registration())
                    ((Regist) prev_student).student_circle = connection.student_circle;
                else
                    ((StudentR) prev_student).student_circle = connection.student_circle;
            }

            prev_course = prev_course_in_circle(student, course);
            // если у курса один студент
            if (prev_course == connection.course_circle)
                course.course_circle = null;
            else {
                if (prev_course.is_registration())
                    ((Regist) prev_course).course_circle = connection.course_circle;
                else
                    ((CourseR) prev_course).course_circle = connection.course_circle;
            }
        }
    }

    // удалить студента s со всех курсов
    public void removeStudent (char[] s) {

        StudentR student = students.get_student(s);

        // если такой студент есть
        if (student != null) {

            Abstr prev_course; // ссылка на предыдущий в кольце курсов

            // пока не удалим все кольцо
            while (student.student_circle != null) {

                Abstr r = student.student_circle;
                while (r.is_registration()) {
                    r = ((Regist) r).course_circle;
                }
                CourseR course = (CourseR) r;
                Regist connect = (Regist) student.student_circle; // удалить первую запись в кольце студента
                prev_course = prev_course_in_circle(student, course);

                // если курс у студента единственный
                if (connect.student_circle == student)
                    student.student_circle = null;
                else
                    student.student_circle = connect.student_circle;

                // если студент на курсе был единственный
                if (prev_course == course)
                    course.course_circle = null;
                else {
                    if (prev_course.is_registration())
                        ((Regist) prev_course).course_circle = connect.course_circle;
                    else
                        ((CourseR) prev_course).course_circle = connect.course_circle;
                }
            }
        }
    }

    // удалить всех студентов с курса c
    public void removeCourse (int c) {

        CourseR course = courses.get_course(c);

        // если такой курс есть
        if (course != null) {

            Abstr prev_student; // ссылка на предыдущий в кольце студентов

            // пока не удалим все кольцо
            while (course.course_circle != null) {

                Abstr r = course.course_circle;
                while (r.is_registration()) {
                    r = ((Regist) r).student_circle;
                }
                StudentR student = (StudentR) r;
                Regist connect = (Regist) course.course_circle; // удалить первую запись в кольце курса
                prev_student = prev_student_in_circle(student, course);

                // если студент на курсе единсnвенный
                if (connect.course_circle == course)
                    course.course_circle = null;
                else
                    course.course_circle = connect.course_circle;

                // если курс у студента был единсnвенный
                if (prev_student == student)
                    student.student_circle = null;
                else {
                    if (prev_student.is_registration())
                        ((Regist) prev_student).student_circle = connect.student_circle;
                    else
                        ((StudentR) prev_student).student_circle = connect.student_circle;
                }
            }
        }
    }

    // вывести список всех студентов посещающих курс c
    public void printStudentsOfCourse (int c) {

        CourseR course = courses.get_course(c);

        // если такой курс есть
        if (course != null) {

            System.out.println();
            System.out.print(course.name);
            System.out.print(": ");

            Abstr i = course.course_circle;
            Abstr j;
            while (i != null && i.is_registration()) {
                j = ((Regist) i).student_circle;

                while (j.is_registration())
                    j = ((Regist) j).student_circle;

                System.out.print(((StudentR)j).name);
                System.out.print(" ");
                i = ((Regist)i).course_circle;
            }
        }
    }

    // вывести список курсов, посещаемых студентом
    public void printCoursesOfStudent (char[] s) {

        StudentR student = students.get_student(s);

        // если такой студент есть
        if (student != null) {

            System.out.println();
            System.out.print(student.name);
            System.out.print(": ");

            Abstr i = student.student_circle;
            Abstr j;
            while (i != null && i.is_registration()) {
                j = ((Regist) i).course_circle;

                while (j.is_registration())
                    j = ((Regist) j).course_circle;

                System.out.print(((CourseR)j).name);
                System.out.print(" ");
                i = ((Regist)i).student_circle;
            }
        }
    }
}
