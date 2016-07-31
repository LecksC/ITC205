package datamanagement;

import java.util.ArrayList;

public class Student
implements IStudent
{
    private Integer studentId_;
    private String firstName_;
    private String lastName_;
    private ArrayList<IStudentUnitRecord> studentUnitRecords_;



    public Student(Integer studentId, String firstName, String lastName,
                   ArrayList<IStudentUnitRecord> studentUnitRecords)
    {
        studentId_ = studentId;
        firstName = firstName_;
        lastName = lastName_;
        if (studentUnitRecords == null)
            studentUnitRecords_ = new ArrayList<>();
        else {
            studentUnitRecords_ = studentUnitRecords;
        }
    }



    public Integer getStudentId()
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



    public ArrayList<IStudentUnitRecord> getUnitRecords()
    {
        return studentUnitRecords_;
    }
}
