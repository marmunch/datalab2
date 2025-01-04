import StudsCourses.*;

public class Main4 {

    public static void main(String[] args) {

        /*
        все студенты
        все курсы

        добавить студента на несколько курсов
        студент: курсы
        добавить на курс несколько студентов
        курсы: студенты

        удалить 1 студента с курса
        удалить 2 курс у студента

        удалить всех студентов с курса
        удалить все курсы у студента
         */
        // конструктор преобразует String в char toCharArray()

        // создать список студентов
        Student students = new Student(20);
        students.insert(new char[]{'A', 'l', 'i', 'n', 'a'});
        students.insert(new char[]{'M', 'a', 'r', 'i', 'a'});
        students.insert(new char[]{'V', 'a', 'n', 'y', 'a'});
        students.insert(new char[]{'A', 'l', 'e', 'x'});
        students.insert(new char[]{'L', 'i', 'a'});
        students.insert(new char[]{'M', 'i', 'a'});
        students.insert(new char[]{'O', 'l', 'y', 'a'});
        students.insert(new char[]{'S', 'e', 'r', 'g', 'e'});

        // создать список курсов
        Course courses = new Course(20);
        courses.insert(1);
        courses.insert(2);
        courses.insert(3);
        courses.insert(6);
        courses.insert(16);
        courses.insert(8);
        courses.insert(10);
        courses.insert(41);
        courses.insert(15);

        System.out.println("Студенты: ");
        students.print();
        System.out.println("Курсы: ");
        courses.print();

        // добавление студента на курс
        ManyToMany Set = new ManyToMany(courses, students);
        Set.addStudentToCourse(new char[]{'A', 'l', 'i', 'n', 'a'}, 10);
        Set.addStudentToCourse(new char[]{'S', 'e', 'r', 'g', 'e'}, 10);
        Set.addStudentToCourse(new char[]{'A', 'l', 'i', 'n', 'a'}, 1);
        Set.addStudentToCourse(new char[]{'A', 'l', 'e', 'x'}, 8);
        Set.addStudentToCourse(new char[]{'A', 'l', 'e', 'x'}, 10);
        Set.addStudentToCourse(new char[]{'S', 'e', 'r', 'g', 'e'}, 41);
        Set.addStudentToCourse(new char[]{'O', 'l', 'y', 'a'}, 2);
        Set.addStudentToCourse(new char[]{'M', 'i', 'a'}, 15);
        Set.addStudentToCourse(new char[]{'O', 'l', 'y', 'a'}, 15);
        Set.addStudentToCourse(new char[]{'S', 'e', 'r', 'g', 'e'}, 15);

        // печатаем всех студентов с 15 курса
        System.out.println();
        System.out.print("Студенты на 15 курсе");
        Set.printStudentsOfCourse(15);
        System.out.println("");

        // удалим одного студента
        System.out.println();
        System.out.print("Удаление Ольги с 15 курса");
        Set.printCoursesOfStudent(new char[]{'O', 'l', 'y', 'a'});
        Set.removeStudentFromCourse(new char[]{'O', 'l', 'y', 'a'}, 15);
        Set.printCoursesOfStudent(new char[]{'O', 'l', 'y', 'a'});
        Set.printStudentsOfCourse(15);
        System.out.println("");

        // удалим курсы у студента
        System.out.println();
        System.out.print("Удаление курсов Сергея");
        Set.printCoursesOfStudent(new char[]{'S', 'e', 'r', 'g', 'e'});
        Set.printStudentsOfCourse(10);
        Set.removeStudent(new char[]{'S', 'e', 'r', 'g', 'e'});
        //Set.printCoursesOfStudent(new char[]{'A', 'l', 'i', 'n', 'a'});
        Set.printCoursesOfStudent(new char[]{'S', 'e', 'r', 'g', 'e'});
        Set.printStudentsOfCourse(10);
        System.out.println();

        // печатаем всех студентов с 10 курса
        System.out.println();
        System.out.print("Удаление всех студентов с 10 курса");
        Set.printStudentsOfCourse(10);
        // удалим всех студентов с 10 курса
        Set.removeCourse(10);
        Set.printStudentsOfCourse(10);
    }
}
