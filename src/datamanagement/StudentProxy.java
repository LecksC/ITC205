package datamanagement;

public class StudentProxy
implements IStudent
{
    private Integer studentId_;
    private String firstName_;
    private String lastName_;
    private StudentManager studentManager_;

    public StudentProxy(Integer studentId, String firstName, String lastName)
    {
        studentId_ = studentId;
        firstName_ = firstName;
        lastName_ = lastName;
        studentManager_ = StudentManager.getInstance();
    }



    public Integer getId()
    {
        return studentId_;
    }



    public String getFirstName()
    {
        return firstName_;
    }



    public String getLastName()
    {
        return lastName_;
    }



    public void setFirstName(String firstName)
    {
        studentManager_.getStudent(studentId_).setFirstName(firstName);
    }



    public void setLastName(String lastName)
    {
        studentManager_.getStudent(studentId_).setLastName(lastName);
    }



    public void addUnitRecord(IStudentUnitRecord record)
    {
        studentManager_.getStudent(studentId_).addUnitRecord(record);
    }



    public IStudentUnitRecord getUnitRecord(String unitCode)
    {
        return studentManager_.getStudent(studentId_).getUnitRecord(unitCode);
    }



    public StudentUnitRecordList getUnitRecords()
    {
        return studentManager_.getStudent(studentId_).getUnitRecords();
    }
}
