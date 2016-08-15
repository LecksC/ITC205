package datamanagement;

public interface IStudentUnitRecord 
{
    public Integer getStudentID();
    
    
    
    public String getUnitCode();

    
    
    public void setAssignment1Mark(float assignment1Mark);
    
    
    
    public float getAssignment1Mark();
    
    

    public void setAssignment2Mark(float assignment2Mark);
    
    
    
    public float getAssignment2Mark();
    
    

    public void setExamMark(float examMark);
    
    
    
    public float getExamMark();

    
    
    public float getTotalMark();
}
