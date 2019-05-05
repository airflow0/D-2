import java.util.HashMap;
import java.util.HashSet;

public class Main
{
    public static void main(String[] args)
    {
        GradesDB grade = new GradesDB();
        grade.loadSpreadsheet("DB/GradesDatabase.xlsx");
        Assignment assign = new Assignment(grade);

        //assign.setStudent(grade.populateByStudent(3));
        //assign.printByStudent();
        Project proj = new Project(grade);
        //proj.setProjectHash(grade.populate(5));
        proj.setContributionHash(grade.populate(4));
        //proj.setStudentCont(grade.populateByStudent(4));
        //proj.setStudentProject(grade.populateByStudent(5));
        proj.print();
        proj.addContribution("PROJECT 4");
        proj.addContributionGrade("PROJECT 4", 99.2);
        if(proj.checkContribution("PROJECT 4", 99.1))
        {
            System.out.println("True!");
        }
        proj.print();

    }
}
