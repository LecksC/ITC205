package datamanagement;

public interface IStudentUnitRecord {

    public Integer getStudentID();
    public String getUnitCode();

    public void setAssignment1Mark(float mark);
    public float getAssignment1Mark();

    public void setAssignment2Mark(float mark);
    public float getAssignment2Mark();

    public void setExamMark(float mark);
    public float getExamMark();

    public float getTotalMark();
}
