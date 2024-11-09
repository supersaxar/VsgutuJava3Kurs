import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Calendar;
import java.util.stream.Collectors;

public class Student {
    private static List<Student> allStudents = new ArrayList<>();

    private int id;
    private String lastName;
    private String firstName;
    private String middleName;
    private Date birthDate;
    private String address;
    private String phone;
    private String faculty;
    private int course;
    private String group;

    public Student() {
        allStudents.add(this);
    }

    public Student(int id, String lastName, String firstName, String middleName) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        allStudents.add(this);
    }

    public Student(int id, String lastName, String firstName, String middleName,
                   Date birthDate, String address, String phone, String faculty,
                   int course, String group) {
        this(id, lastName, firstName, middleName);
        this.birthDate = birthDate;
        this.address = address;
        this.phone = phone;
        this.faculty = faculty;
        this.course = course;
        this.group = group;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getMiddleName() { return middleName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }

    public Date getBirthDate() { return birthDate; }
    public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getFaculty() { return faculty; }
    public void setFaculty(String faculty) { this.faculty = faculty; }

    public int getCourse() { return course; }
    public void setCourse(int course) { this.course = course; }

    public String getGroup() { return group; }
    public void setGroup(String group) { this.group = group; }


    public static List<Student> getStudentsByFaculty(String faculty) {
        return allStudents.stream()
                .filter(s -> s.getFaculty().equals(faculty))
                .collect(Collectors.toList());
    }

    public static Map<String, Map<Integer, List<Student>>> getStudentsByFacultyAndCourse() {
        return allStudents.stream()
                .collect(Collectors.groupingBy(
                        Student::getFaculty,
                        Collectors.groupingBy(Student::getCourse)
                ));
    }

    public static List<Student> getStudentsBornAfterYear(int year) {
        Calendar calendar = Calendar.getInstance();
        return allStudents.stream()
                .filter(s -> {
                    calendar.setTime(s.getBirthDate());
                    return calendar.get(Calendar.YEAR) > year;
                })
                .collect(Collectors.toList());
    }

    public static List<Student> getStudentsByGroup(String group) {
        return allStudents.stream()
                .filter(s -> s.getGroup().equals(group))
                .collect(Collectors.toList());
    }

    public static void printStudents(List<Student> students) {
        students.forEach(System.out::println);
    }

    @Override
    public String toString() {
        return String.format("Student{id=%d, lastName='%s', firstName='%s', middleName='%s', " +
                        "birthDate=%s, faculty='%s', course=%d, group='%s'}",
                id, lastName, firstName, middleName, birthDate, faculty, course, group);
    }

    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();

        cal.set(2000, Calendar.JANUARY, 15);
        new Student(1, "Иванов", "Иван", "Иванович",
                cal.getTime(), "ул. Ленина 1", "+7900111111",
                "Программная инженерия", 2, "Б-762-1");

        cal.set(2001, Calendar.MAY, 20);
        new Student(2, "Петров", "Петр", "Петрович",
                cal.getTime(), "ул. Пушкина 2", "+7900222222",
                "Программная инженерия", 2, "Б-762-1");

        cal.set(1999, Calendar.MARCH, 10);
        new Student(3, "Сидоров", "Сидор", "Сидорович",
                cal.getTime(), "ул. Гоголя 3", "+7900333333",
                "Программная инженерия", 3, "Б-762-2");

        System.out.println("=== Список студентов факультета Программная инженерия ===");
        printStudents(getStudentsByFaculty("Программная инженерия"));

        System.out.println("\n=== Список студентов группы Б-762-1 ===");
        printStudents(getStudentsByGroup("Б-762-1"));

        System.out.println("\n=== Список студентов, родившихся после 2000 года ===");
        printStudents(getStudentsBornAfterYear(2000));

        System.out.println("\n=== Списки студентов по факультетам и курсам ===");
        Map<String, Map<Integer, List<Student>>> groupedStudents = getStudentsByFacultyAndCourse();
        groupedStudents.forEach((faculty, courseMap) -> {
            System.out.println("\nФакультет: " + faculty);
            courseMap.forEach((course, students) -> {
                System.out.println("Курс " + course + ":");
                students.forEach(student ->
                        System.out.println("  - " + student.getLastName() + " " +
                                student.getFirstName()));
            });
        });
    }
}