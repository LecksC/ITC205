package datamanagement;

public class StudentUnitRecord
implements IStudentUnitRecord
{
    private Integer studentId_;
    private String unitCode_;
    private float assignment1Mark_, assignment2Mark_, examMark_;



    public StudentUnitRecord(Integer studentId, String unitCode, float assignment1Mark, float assignment2Mark, float examMark)
    {
        studentId_ = studentId;
        unitCode_ = unitCode;
        setAssignment1Mark(assignment1Mark);
        setAssignment2Mark(assignment2Mark);
        setExamMark(examMark);
    }



    public Integer getStudentId()
    {
        return studentId_;
    }



    public String getUnitCode()
    {
        return unitCode_;
    }



    public void setAssignment1Mark(float assignment1Mark)
    {
        boolean isMarkInvalid = assignment1Mark < 0 || assignment1Mark > getUnit().getAssignment1Weight();
        if (isMarkInvalid) {
            throw new RuntimeException("Mark cannot be less than zero or greater than assessment weight");
        }
        assignment1Mark_ = assignment1Mark;
    }



    public float getAssignment1Mark()
    {
        return assignment1Mark_;
    }



    public void setAssignment2Mark(float assignment2Mark)
    {
        boolean isMarkInvalid = assignment2Mark < 0 || assignment2Mark > getUnit().getAssignment2Weight();
        if (isMarkInvalid) {
            throw new RuntimeException("Mark cannot be less than zero or greater than assessment weight");
        }
        assignment2Mark_ = assignment2Mark;
    }



    public float getAssignment2Mark()
    {
        return assignment2Mark_;
    }



    public void setExamMark(float examMark)
    {
        boolean isMarkInvalid = examMark < 0 || examMark > getUnit().getExamWeight();
        if (isMarkInvalid) {
            throw new RuntimeException("Mark cannot be less than zero or greater than assessment weight");
        }
        examMark_ = examMark;
    }



    public float getExamMark()
    {
        return examMark_;
    }



    public float getTotalMark()
    {
        return getAssignment1Mark() + getAssignment2Mark() + getExamMark();
    }



    private IUnit getUnit()
    {
        UnitManager unitManager = UnitManager.getInstance();
        return unitManager.getUnit(unitCode_);
    }
}
