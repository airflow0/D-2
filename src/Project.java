import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Project extends GradesDB
{
    Map<String, ArrayList<Double>> cont;
    Map<String, ArrayList<Double>> project;
    Map<String, ArrayList<Double>> studentCont;
    Map<String, ArrayList<Double>> studentProject;
    XSSFWorkbook book;

    public Project(GradesDB db)
    {
        cont = new HashMap<>();
        project = new HashMap<>();
        this.book = db.workbook;
    }
    public boolean checkContribution(String contr, Double grade)
    {
        if(cont.get(contr) == null)
        {
            return false;
        }
        else
        {
            if(cont.get(contr).contains(grade))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }
    public boolean addContributionGrade(String contr, Double grade)
    {
        if(cont.get(contr) == null)
        {
            cont.put(contr, new ArrayList<Double>());
            cont.get(contr).add(grade);

        }
        else
        {
            cont.get(contr).add(grade);
        }

        return true;

    }
    public boolean addContribution(String assign)
    {
        if(cont.entrySet().contains(assign))
        {
            return false;
        }
        else
        {
            cont.put(assign, new ArrayList<Double>());
            return true;
        }
    }
    public void print()
    {
        if(cont != null)
        {
            System.out.println("[Project Contribution]");
            for(Map.Entry<String, ArrayList<Double>> element : cont.entrySet())
            {
                String key = element.getKey();
                ArrayList<Double> values = element.getValue();
                System.out.println(key + " " + values);
            }
        }
        if(project != null)
        {
            System.out.println("[Projects]");
            for(Map.Entry<String, ArrayList<Double>> element : project.entrySet())
            {
                String key = element.getKey();
                ArrayList<Double> values = element.getValue();
                System.out.println(key + " " + values);
            }
        }

        if(studentCont != null)
        {
            System.out.println("[By Students Contribution]");
            for(Map.Entry<String, ArrayList<Double>> element : studentCont.entrySet())
            {
                String key = element.getKey();
                ArrayList<Double> values = element.getValue();
                System.out.println(key + " " + values);
            }
        }
        if(studentProject != null)
        {
            System.out.println("[By Team Project]");
            for(Map.Entry<String, ArrayList<Double>> element : studentProject.entrySet())
            {
                String key = element.getKey();
                ArrayList<Double> values = element.getValue();
                System.out.println(key + " " + values);
            }
        }

    }
    public Map<String, ArrayList<Double>> getProjectHash()
    {
        return project;
    }
    public Map<String,ArrayList<Double>> getContributionHash()
    {
        return cont;
    }
    public Map<String,ArrayList<Double>> getStudentCont()
    {
        return studentCont;
    }
    public Map<String,ArrayList<Double>> getStudentProject()
    {
        return studentProject;
    }
    public void setProjectHash(Map<String, ArrayList<Double>> input)
    {
        this.project = input;
    }
    public void setContributionHash(Map<String, ArrayList<Double>> input)
    {
        this.cont = input;
    }
    public void setStudentCont(Map<String, ArrayList<Double>> input)
    {
        this.studentCont = input;
    }
    public void setStudentProject(Map<String, ArrayList<Double>> input)
    {
        this.studentProject = input;
    }
}
