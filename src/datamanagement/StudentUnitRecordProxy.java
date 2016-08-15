package datamanagement;

public class StudentUnitRecordProxy
implements IStudentUnitRecord
{
    private Integer studentId_;
    private String unitCode_;
    private StudentUnitRecordManager studentUnitRecordManager_;



    public StudentUnitRecordProxy(Integer studentId, String unitCode)
    {
        studentId_ = studentId;
        unitCode_ = unitCode;
        studentUnitRecordManager_ = StudentUnitRecordManager.getInstance();
    }



    public Integer getStudentID()
    {
        return studentId_;
    }



    public String getUnitCode()
    {
        return unitCode_;
    }



    public void setAssignment1Mark(float assignment1Mark)
    {
        getRecord().setAssignment1Mark(assignment1Mark);
    }



    public float getAssignment1Mark()
    {
        return getRecord().getAssignment1Mark();
    }



    public void setAssignment2Mark(float assignment2Mark)
    {
        getRecord().setAssignment2Mark(assignment2Mark);
    }



    public float getAssignment2Mark()
    {
        return getRecord().getAssignment2Mark();
    }



    public void setExamMark(float examMark)
    {
        getRecord().setExamMark(examMark);
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
        return studentUnitRecordManager_.getStudentUnitRecord(studentId_, unitCode_);
    }
}
