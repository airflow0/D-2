import java.util.HashSet;

public class Main
{
    public static void main(String[] args)
    {
        GradesDB grade = new GradesDB("DB/GradesDatabase.xlsx");
        System.out.println("Number of Students: " + grade.getNumStudents());
        System.out.println("Number of Assignemnts " + grade.getNumAssignments());
        System.out.println("Number of Projects " + grade.getNumProjects());
        HashSet<Student> students = grade.getStudents();
        System.out.println("Number of Students in Hashset: " + students.size());

        for( Student s : students)
        {
            System.out.println("Name: " + s.getName() + ", ID: " + s.getId());
        }
        Student k = grade.getStudentByName("Freddie Catlay");
        Student j = grade.getStudentByName("Christine Schaeffer");
        Student e = grade.getStudentByID("1234501");
    }
}
