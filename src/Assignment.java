import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

public class Assignment extends GradesDB
{
    XSSFWorkbook book;
    XSSFSheet sheet;
    Map<String, ArrayList<Double>> assignment;
    Map<String, ArrayList<Double>> student;
    public Assignment(GradesDB db)
    {
        this.book = db.workbook;
        assignment = new HashMap<>();
    }

    public boolean addSingleGrade(String assign, Double grade)
    {
        if(assignment.get(assign) == null)
        {
            assignment.put(assign, new ArrayList<Double>());
            assignment.get(assign).add(grade);

        }
        else
        {
            assignment.get(assign).add(grade);
        }

        return true;

    }
    public boolean addAssignment(String assign)
    {
        if(assignment.entrySet().contains(assign))
        {
            return false;
        }
        else
        {
            assignment.put(assign, new ArrayList<Double>());
            return true;
        }
    }
    public Double getAverage()
    {
        Double avg = 0.0;
        Double numOfList = 0.0;
        for(Map.Entry<String, ArrayList<Double>> element : assignment.entrySet())
        {
            String key = element.getKey();
            ArrayList<Double> values = element.getValue();
            for(int i = 0; i < values.size(); i++)
            {
                avg = avg + values.get(i);
                numOfList++;
            }
        }
        return avg / numOfList;
    }
    public Double getAverageStudent()
    {
        Double avg = 0.0;
        Double numOfList = 0.0;
        for(Map.Entry<String, ArrayList<Double>> element : student.entrySet())
        {
            String key = element.getKey();
            ArrayList<Double> values = element.getValue();
            for(int i = 0; i < values.size(); i++)
            {
                avg = avg + values.get(i);
                numOfList++;
            }
        }
        return avg / numOfList;
    }
    public void printByStudent()
    {
        System.out.println("[Student/Grades]");
        for(Map.Entry<String, ArrayList<Double>> element : student.entrySet())
        {
            String key = element.getKey();
            ArrayList<Double> values = element.getValue();
            System.out.println(key + " " + values);
        }
        System.out.println("Average by Student List: " + getAverageStudent());
    }
    public void print()
    {
        System.out.println("[Assignments/Grades]");
        for(Map.Entry<String, ArrayList<Double>> element : assignment.entrySet())
        {
            String key = element.getKey();
            ArrayList<Double> values = element.getValue();
            System.out.println(key + " " + values);
        }
        System.out.println("Average: " + getAverage());
    }
    public Map<String,ArrayList<Double>> getAssignment()
    {
        return assignment;
    }
    public void setAssignment(Map<String, ArrayList<Double>> input)
    {
        this.assignment = input;
    }
    public Map<String,ArrayList<Double>> getStudent()
    {
        return student;
    }
    public void setStudent(Map<String, ArrayList<Double>> input)
    {
        this.student = input;
    }

}
/*
    public Assignment(GradesDB db)
    {
        this.book = db.workbook;
        scores = new ArrayList<>();
        assignments = new ArrayList<>();
    }
    public void addAssignment()
    {

    }
    //public void add
    public int numberOfStudents()
    {
        return sheet.getLastRowNum();
    }
    public int getNumberOfAssignments()
    {
        XSSFRow row = sheet.getRow(0);
        return row.getLastCellNum();
    }
    public void print()
    {
        System.out.println("Score Average: " + getScoreAverage());
    }
    public double getScoreAverage()
    {
        int calc = numberOfStudents() * (getNumberOfAssignments()-1);
        double assimulate = 0;
        for(int i = 0; i < calc; i++)
        {
            assimulate = scores.get(i) + assimulate;
        }
        return assimulate/calc;
    }
    public void printAssignments()
    {
        for(int i = 0; i < assignments.size(); i++)
        {
            System.out.println(assignments.get(i));
        }
    }
    public void populateAssignmentScore ()
    {
        sheet = book.getSheetAt(3);
        XSSFRow row = sheet.getRow( 0);
        XSSFRow scoreRow = null;
        XSSFCell cell = null;
        XSSFCell rowCell = null;
        for(int i = 1; i < row.getLastCellNum(); i++)
        {
            cell = row.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            String a = cell.toString();
            assignments.add(a);
            for(int j = 1; j < sheet.getLastRowNum() + 1; j++)
            {
                scoreRow = sheet.getRow(j);
                rowCell = scoreRow.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);

                String b = rowCell.toString();
                //System.out.println(a + " " + b);
                scores.add(Double.parseDouble(b));



            }
        }
    }
}*/
    /*public double getAverage(){
        double total = 0;
        for(int i = 0; i < grades.size(); i++){
            total += grades.get(i);
        }

        return total / getNumGrades();
    }

    public int getNumGrades(){
        return grades.size();
    }

    public void addGrade(double grade){
        grades.add(grade);
    }*/
    /*public int getNonBlankCell()
    {
        XSSFSheet sheet = workbook.getSheetAt(3);
        XSSFRow row = sheet.getRow(sheet.getFirstRowNum());
        int j =0;
        for(int i = 0; i < row.getLastCellNum(); i++)
        {
            XSSFCell cell = row.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            if(cell != null)
            {
                j++;
            }
        }

        return j;
    }
    public String getLastAssignmentNumber()
    {
        XSSFSheet sheet = workbook.getSheetAt(3);
        DataFormatter format = new DataFormatter();
        XSSFRow row = sheet.getRow(sheet.getFirstRowNum());

        String lastColumn = format.formatCellValue(row.getCell(getNonBlankCell() - 1));
        //System.out.println("Last Column" + lastColumn);
        int columnNum = Integer.parseInt(lastColumn.replaceAll("\\D+", "")) + 1;
        return "ASSIGNMENT " + columnNum;
    }
    public void addAssignment()
    {
        String a = getLastAssignmentNumber();
        //System.out.println(a);
        try
        {
            OutputStream os = new FileOutputStream("DB/GradesDatabase.xlsx");
            DataFormatter format = new DataFormatter();
            XSSFSheet sheet = workbook.getSheetAt(3);
            XSSFRow row = sheet.getRow(0);
            XSSFCell cell = row.createCell(getNonBlankCell());
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            font.setFontName("ARIAL");
            font.setFontHeight(10);
            XSSFCellStyle style = workbook.createCellStyle();
            style.setFont(font);
            cell.setCellStyle(style);
            cell.setCellValue(a);
            workbook.write(os);
        }
        catch(FileNotFoundException ex)
        {

        }
        catch(  IOException rx_)
        {

        }
    }*/

