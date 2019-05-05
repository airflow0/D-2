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
    public Student(String name, String id, GradesDB db)
    {
        this.studentName = name;
        this.studentID = Integer.parseInt(id);
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + getName().hashCode();
        result = prime * result + String.valueOf(getStudentID()).hashCode();
        return result;
    }

    @Override
    public boolean equals(Object object) {
        if(object instanceof Student) {
            Student student = (Student) object;
            if (this.getName().equals(student.getName()) && this.getId().equals(student.getId())) {
                return true;
            }
        }

        return false;
    }


}
