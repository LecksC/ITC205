package datamanagement;

import java.util.ArrayList;

public interface IStudent {

    public Integer getID();

    public String getFirstName();
    public void setFirstName(String firstName);

    public String getLastName();
    public void setLastName(String lastName);

    public void addUnitRecord( IStudentUnitRecord record );
    public IStudentUnitRecord getUnitRecord( String unitCode );

    public ArrayList<IStudentUnitRecord> getUnitRecords();

}
