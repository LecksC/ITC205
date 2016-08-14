package datamanagement;

public class StudentUnitRecordProxy
implements IStudentUnitRecord
{
    private Integer studentId_;
    private String unitCode_;
    private StudentUnitRecordManager manager_;



    public StudentUnitRecordProxy(Integer id, String code)
    {
        this.studentId_ = id;
        this.unitCode_ = code;
        this.manager_ = StudentUnitRecordManager.getInstance();
    }



    public Integer getStudentID()
    {
        return studentId_;
    }



    public String getUnitCode()
    {
        return unitCode_;
    }



    public void setAssignment1Mark(float mark)
    {
        getRecord().setAssignment1Mark(mark);
    }



    public float getAssignment1Mark()
    {
        return getRecord().getAssignment1Mark();
    }



    public void setAssignment2Mark(float mark)
    {
        getRecord().setAssignment2Mark(mark);
    }



    public float getAssignment2Mark()
    {
        return getRecord().getAssignment2Mark();
    }



    public void setExamMark(float mark)
    {
        getRecord().setExamMark(mark);
    }



    public float getExamMark()
    {
        return getRecord().getExamMark();
    }



    public float getTotalMark()
    {
        return getRecord().getTotalMark();
    }



    private IStudentUnitRecord getRecord()
    {
        return manager_.getStudentUnitRecord(studentId_, unitCode_);
    }
}
