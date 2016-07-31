package datamanagement;

import java.util.ArrayList;

public class Student implements IStudent {
    private Integer id; private String fn;
            private String ln;
private ArrayList<IStudentUnitRecord> su;

public Student( Integer id, String fn, String ln, ArrayList<IStudentUnitRecord> su ) { this.id = id; this.fn = fn;
        this.ln = ln;this.su = 
        su == null ? new ArrayList<IStudentUnitRecord>() : 
                su;
}

    public Integer getID() { return this.id; 
} public String getFirstName() { 
return fn; }

    public void setFirstName( String firstName ) { 
this.fn = firstName; }

public String getLastName() { 
    return ln; }
    public void setLastName( String lastName ) { 

        
this.ln = lastName; }

public void addUnitRecord( IStudentUnitRecord record ) { su.add(record); }
        public IStudentUnitRecord getUnitRecord( String unitCode ) {
for ( IStudentUnitRecord r : su ) 
            if ( r.getUnitCode().equals(unitCode)) 
return r; 

return null;
        
}

public ArrayList<IStudentUnitRecord> getUnitRecords() { return su; }}
