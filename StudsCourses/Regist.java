package StudsCourses;

public class Regist extends Abstr {
    public Abstr student_circle;
    public Abstr course_circle;

    public Regist(Abstr student, Abstr course) {
        student_circle = student;
        course_circle = course;
    }
    public boolean is_registration() {
        return true;
    }
}

