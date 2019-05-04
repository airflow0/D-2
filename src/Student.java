import java.util.HashSet;

public class Student
{
    private String studentName;
    private int studentID;
    private int studentAttendance;

    public Student()
    {

    }
    public Student(String name, int id)
    {
        this.studentName = name;
        this.studentID = id;
    }
    public void setName( String name )
    {
        this.studentName = name;
    }
    public String getName()
    {
        return this.studentName;
    }
    public void setID( int id )
    {
        this.studentID = id;
    }
    public int getStudentID()
    {
        return this.studentID;
    }
    public String getId()
    {
        return Integer.toString(this.studentID);
    }
    public void setStudentAttendance( int attendance)
    {
        this.studentAttendance = attendance;
    }
    public int getAttendance( )
    {
        return this.studentAttendance;
    }
}
