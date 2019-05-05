import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class GradesDB
{

    public XSSFRow row;
    public XSSFSheet sheet;
    public DataFormatter format;
    public XSSFWorkbook workbook;

    public GradesDB()
    {

    }

    public void loadSpreadsheet(String db)
    {
        try
        {
            File file = new File(db);
            FileInputStream stream = new FileInputStream(file);
            workbook = new XSSFWorkbook(stream);
            stream.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
    public Student getStudentByID(String id)
    {
        Student stu = new Student();
        sheet = workbook.getSheetAt(0);
        format = new DataFormatter();
        String name = "";
        for(int i = 1; i <= sheet.getLastRowNum(); i++)
        {
            row = sheet.getRow(i);
            String idend = format.formatCellValue(row.getCell(1));
            //stu.setID(Integer.par);
            if(idend.compareTo(id) == 0)
            {
                name = row.getCell(0).toString();
                stu.setID(Integer.parseInt(idend));
                stu.setName(name);
            }
        }
        if(name.compareTo("") == 0)
        {
            System.out.println("Could not find student! ");
            System.exit(1);
        }
        sheet = workbook.getSheetAt(2);
        for(int i = 1; i <= sheet.getLastRowNum(); i++)
        {
            row = sheet.getRow(i);
            if(row.getCell(0).toString().compareTo(name) == 0)
            {
                int att = Integer.parseInt(format.formatCellValue(row.getCell(1)));
                stu.setStudentAttendance(att);

            }
        }
        return stu;
    }
    public Student getStudentByName(String n)
    {
        Student stu = new Student();
        sheet = workbook.getSheetAt(0);
        format = new DataFormatter();

        for(int i = 1; i <= sheet.getLastRowNum(); i++)
        {
            row = sheet.getRow(i);
            String name = row.getCell(0).toString();
            //System.out.println(name);
            int id = Integer.parseInt(format.formatCellValue(row.getCell(1)));
            if(name.compareTo(n) == 0)
            {
                stu.setName(name);
                stu.setID(id);
                //System.out.println("Found student " + stu.getName());
            }
        }
        sheet = workbook.getSheetAt(2);
        for(int i = 1; i <= sheet.getLastRowNum(); i++)
        {
            row = sheet.getRow(i);
            if(row.getCell(0).toString().compareTo(n) == 0)
            {
                int att = Integer.parseInt(format.formatCellValue(row.getCell(1)));
                stu.setStudentAttendance(att);
                //System.out.println("Attendance: " + att);
            }
        }
        return stu;
    }
    public HashSet getStudents()
    {
        sheet = workbook.getSheetAt(0);
        HashSet<Student> students = new HashSet<>();
        format = new DataFormatter();
        Student stud;

        for(int i = 1; i <= sheet.getLastRowNum(); i++)
        {
            row = sheet.getRow(i);
            String name = row.getCell(0).toString();
            int id = Integer.parseInt(format.formatCellValue(row.getCell(1)));
            stud = new Student(name, id);
            students.add(stud);
        }
        return students;

    }
    public int getNumStudents()
    {
        sheet = workbook.getSheetAt(0);
        int numOfStudents = 0; //Sheets start a 1 not 0

        for(int i = 1; i < sheet.getPhysicalNumberOfRows(); i++)
        {
            numOfStudents++;
        }
        return numOfStudents;
    }

    public int getNumAssignments()
    {
        sheet = workbook.getSheetAt(3);
        int numOfAssignments = 0;
        for(int i = 1; i < sheet.getRow(0).getLastCellNum(); i++)
        {
            numOfAssignments++;
        }
        return numOfAssignments;
    }
    public int getNumProjects()
    {
        sheet = workbook.getSheetAt(5);
        int projects = 0;
        for(int i = 1; i < sheet.getRow(0).getLastCellNum(); i++)
        {
            projects++;
        }
        return projects;
    }

    public Map<String,ArrayList<Double>> populate(int pageIndex)
    {
        Map<String, ArrayList<Double>> temp = new HashMap<>();
        sheet = workbook.getSheetAt(pageIndex);
        XSSFRow row = sheet.getRow( 0);
        XSSFCell cell = null;
        for(int i = 1; i < row.getLastCellNum(); i++)
        {
            cell = row.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            temp.put(cell.toString(), getScores(i));
        }
        return temp;
    }

    public ArrayList<Double> getScores( int loc )
    {
        ArrayList<Double> temp = new ArrayList<>();
        XSSFRow row = null;
        XSSFCell cell = null;
        for(int i = 1; i < sheet.getLastRowNum() + 1; i++)
        {
            row = sheet.getRow(i);
            cell = row.getCell(loc, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            if(cell != null)
            {
                double data = Double.parseDouble(cell.toString());
                temp.add(data);
            }
        }
        return temp;
    }
    public Map<String,ArrayList<Double>> populateByStudent(int pageIndex)
    {
        Map<String, ArrayList<Double>> temp = new HashMap<>();
        sheet = workbook.getSheetAt(pageIndex);
        XSSFRow row;
        XSSFCell cell = null;
        for(int i = 1; i < sheet.getLastRowNum() + 1; i++)
        {
            row = sheet.getRow(i);

            cell = row.getCell(0, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);

            if (cell != null)
            {
                //System.out.println(cell.toString());
                temp.put(cell.toString(), getScoresByStudent(i));
            }
        }
        return temp;
    }

    public ArrayList<Double> getScoresByStudent( int loc )
    {
        ArrayList<Double> temp = new ArrayList<>();
        XSSFRow row = sheet.getRow(loc);
        XSSFCell cell = null;
        for(int i = 1; i < row.getLastCellNum(); i++)
        {
            cell = row.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            if(cell != null)
            {
                double data = Double.parseDouble(cell.toString());
                temp.add(data);
            }
        }
        return temp;
    }
}
