package datamanagement;

public class StudentUnitRecord
implements IStudentUnitRecord
{
    private Integer studentId_;
    private String unitCode_;
    private float assignment1Mark_, assignment2Mark_, examMark_;



    public StudentUnitRecord(Integer studentId, String unitCode, float assignment1Mark, float assignment2Mark, float examMark)
    {
        this.studentId_ = studentId;
        this.unitCode_ = unitCode;
        this.setAssignment1Mark(assignment1Mark);
        this.setAssignment2Mark(assignment2Mark);
        this.setExamMark(examMark);
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
        boolean isMarkInvalid = mark < 0 || mark > getUnit().getAsg1Weight();
        if (isMarkInvalid) {
            throw new RuntimeException("Mark cannot be less than zero or greater than assessment weight");
        }
        this.assignment1Mark_ = mark;
    }



    public float getAssignment1Mark()
    {
        return assignment1Mark_;
    }



    public void setAssignment2Mark(float mark)
    {
        boolean isMarkInvalid = mark < 0 || mark > getUnit().getAsg2Weight();
        if (isMarkInvalid) {
            throw new RuntimeException("Mark cannot be less than zero or greater than assessment weight");
        }
        this.assignment2Mark_ = mark;
    }



    public float getAssignment2Mark()
    {
        return assignment2Mark_;
    }



    public void setExamMark(float mark)
    {
        boolean isMarkInvalid = mark < 0 || mark > getUnit().getExamWeight();
        if (isMarkInvalid) {
            throw new RuntimeException("Mark cannot be less than zero or greater than assessment weight");
        }
        this.examMark_ = mark;
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
        UnitManager unitManager = UnitManager.UM();
        return unitManager.getUnit(unitCode_);
    }
}
