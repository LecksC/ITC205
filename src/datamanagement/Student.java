package datamanagement;

public class Student
implements IStudent
{
    private Integer studentId_;
    private String firstName_;
    private String lastName_;
    private StudentUnitRecordList studentUnitRecords_; // of StudentUnitRecord

    public Student(Integer studentId, String firstName, String lastName, StudentUnitRecordList studentUnitRecordList)
    {
        studentId_ = studentId;
        setFirstName(firstName);
        setLastName(lastName);
        if (studentUnitRecordList == null)
            studentUnitRecords_ = new StudentUnitRecordList();
        else {
            studentUnitRecords_ = studentUnitRecordList;
        }
    }



    public Integer getId() 
    {
        return studentId_;
    }



    public String getFirstName()
    {
        return firstName_;
    }



    public void setFirstName(String firstName)
    {
        firstName_ = firstName;
    }



    public String getLastName()
    {
        return lastName_;
    }



    public void setLastName(String lastName)
    {
        lastName_ = lastName;
    }



    public void addUnitRecord(IStudentUnitRecord studentUnitRecord)
    {
        studentUnitRecords_.add(studentUnitRecord);
    }



    public IStudentUnitRecord getUnitRecord(String unitCode)
    {
        for (IStudentUnitRecord record : studentUnitRecords_) {
            if (record.getUnitCode().equals(unitCode)) {
                return record;
            }
        }
        return null;
    }



    public StudentUnitRecordList getUnitRecords()
    {
        return studentUnitRecords_;
    }
}
