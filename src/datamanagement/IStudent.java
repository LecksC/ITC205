package datamanagement;

public interface IStudent
{
    public Integer getStudentId();

    public String getFirstName();
    public void setFirstName(String firstName);

    public String getLastName();
    public void setLastName(String lastName);

    public IStudentUnitRecord getUnitRecord(String unitCode);
    public void addUnitRecord(IStudentUnitRecord studentUnitRecord);

    public StudentUnitRecordList getUnitRecords();
}
