package lab3;
import lab3.model.Course;
import lab3.model.Student;
import lab3.model.Teacher;
import lab3.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationSystemTests {
    @Test
    void RegisterTest(){
        //list of courses for students
        List<Course> coursesStudent1 = new ArrayList<Course>();
        List<Course> coursesStudent2 = new ArrayList<Course>();
        List<Course> coursesStudent3 = new ArrayList<Course>();

        //students
        Student student1 = new Student("Larisa", "Pargea", 1, 30, coursesStudent1);
        Student student2 = new Student("Maria", "Papuc", 2, 20, coursesStudent2);
        Student student3 = new Student("Mihai", "Oancea", 3, 5, coursesStudent3);

        //list of courses for teacher
        List<Course> coursesTeacher = new ArrayList<Course>();

        //teacher
        Teacher teacher = new Teacher("Diana","Cristea",1,coursesTeacher);

        //list of students for courses
        List<Student> studentsForCourse1 = new ArrayList<Student>();
        List<Student> studentsForCourse2 = new ArrayList<Student>();

        //courses
        Course course1 = new Course("BD",teacher,1,studentsForCourse1,5,1);
        Course course2 = new Course("SGBD",teacher,20,studentsForCourse2,5,2);

        //add the courses to the teacher
        coursesTeacher.add(course1);
        coursesTeacher.add(course2);

        //repo for courses
        List<Course> courses = new ArrayList<Course>();
        CourseRepository courseRepo = new CourseRepository(courses);
        courseRepo.getcourseList().add(course1);
        courseRepo.getcourseList().add(course2);

        //registrationSystem
        RegistrationSystem registSyst = new RegistrationSystem(courseRepo);

        //valid student and course
        registSyst.register(course1,student2);
        assertTrue(course1.getStudentsEnrolled().get(0) == student2);
        assertFalse(course1.getStudentsEnrolled().get(0) == student1);

        //valid course but student with too many credits
        registSyst.register(course2,student1);
        assertTrue(course2.getStudentsEnrolled().size() == 0);
        assertFalse(course2.getStudentsEnrolled().size() == 1);

        //valid student but no more free places for the course
        registSyst.register(course1,student3);
        assertTrue(student3.getEnrolledCourses().size() == 0);
        assertFalse(student3.getEnrolledCourses().size() == 1);
    }

    @Test
    void retrieveCoursesWithFreePlacesTest(){
        List<Course> coursesStudent = new ArrayList<Course>();
        Student student = new Student("Mihai", "Oancea", 3, 5, coursesStudent);

        List<Course> coursesTeacher = new ArrayList<Course>();
        Teacher teacher = new Teacher("Diana","Cristea",1,coursesTeacher);

        //list of students for courses
        List<Student> studentsForCourse1 = new ArrayList<Student>();
        List<Student> studentsForCourse2 = new ArrayList<Student>();

        //courses
        Course course1 = new Course("BD",teacher,1,studentsForCourse1,5,1);
        Course course2 = new Course("SGBD",teacher,20,studentsForCourse2,5,2);

        //add the courses to the teacher
        coursesTeacher.add(course1);
        coursesTeacher.add(course2);

        //repo for courses
        List<Course> courses = new ArrayList<Course>();
        CourseRepository courseRepo = new CourseRepository(courses);
        courseRepo.getcourseList().add(course1);
        courseRepo.getcourseList().add(course2);

        //registrationSystem
        RegistrationSystem registSyst = new RegistrationSystem(courseRepo);

        assertTrue(registSyst.retrieveCoursesWithFreePlaces().size()==2);
        assertFalse(registSyst.retrieveCoursesWithFreePlaces().size()==0);

        registSyst.register(course1,student);
        assertTrue(registSyst.retrieveCoursesWithFreePlaces().size()==1);
        assertFalse(registSyst.retrieveCoursesWithFreePlaces().size()==2);
    }

    @Test
    void retrieveStudentsEnrolledForACourse(){
        List<Course> coursesStudent1 = new ArrayList<Course>();
        List<Course> coursesStudent2 = new ArrayList<Course>();
        Student student1 = new Student("Mihai", "Oancea", 3, 5, coursesStudent1);
        Student student2 = new Student("Maria", "Papuc", 2, 20, coursesStudent2);

        List<Course> coursesTeacher = new ArrayList<Course>();
        Teacher teacher = new Teacher("Diana","Cristea",1,coursesTeacher);

        //list of students for courses
        List<Student> studentsForCourse1 = new ArrayList<Student>();
        List<Student> studentsForCourse2 = new ArrayList<Student>();

        //courses
        Course course1 = new Course("BD",teacher,1,studentsForCourse1,5,1);
        Course course2 = new Course("SGBD",teacher,20,studentsForCourse2,5,2);

        //add the courses to the teacher
        coursesTeacher.add(course1);
        coursesTeacher.add(course2);

        //repo for courses
        List<Course> courses = new ArrayList<Course>();
        CourseRepository courseRepo = new CourseRepository(courses);
        courseRepo.getcourseList().add(course1);
        courseRepo.getcourseList().add(course2);

        //registrationSystem
        RegistrationSystem registSyst = new RegistrationSystem(courseRepo);

        assertTrue(registSyst.retrieveStudentsEnrolledForACourse(course1).size()==0);
        assertFalse(registSyst.retrieveStudentsEnrolledForACourse(course1).size()==10);

        registSyst.register(course2,student1);
        registSyst.register(course2,student2);
        assertTrue(registSyst.retrieveStudentsEnrolledForACourse(course2).size()==2);
        assertFalse(registSyst.retrieveStudentsEnrolledForACourse(course2).size()==0);
    }

    @Test
    void getAllCoursesTest(){
        List<Course> coursesTeacher = new ArrayList<Course>();
        Teacher teacher = new Teacher("Diana","Cristea",1,coursesTeacher);

        //list of students for courses
        List<Student> studentsForCourse1 = new ArrayList<Student>();
        List<Student> studentsForCourse2 = new ArrayList<Student>();

        //courses
        Course course1 = new Course("BD",teacher,1,studentsForCourse1,5,1);
        Course course2 = new Course("SGBD",teacher,20,studentsForCourse2,5,2);

        //add the courses to the teacher
        coursesTeacher.add(course1);
        coursesTeacher.add(course2);

        //repo for courses
        List<Course> courses = new ArrayList<Course>();
        CourseRepository courseRepo = new CourseRepository(courses);
        courseRepo.getcourseList().add(course1);
        courseRepo.getcourseList().add(course2);

        //registrationSystem
        RegistrationSystem registSyst = new RegistrationSystem(courseRepo);

        assertTrue(registSyst.getAllCourses().size()==2);
        assertFalse(registSyst.getAllCourses().size()==10);
    }
}
