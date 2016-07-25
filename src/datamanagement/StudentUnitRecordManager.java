package datamanagement;

import org.jdom.*;
import java.util.List;

public class StudentUnitRecordManager {

	private static StudentUnitRecordManager instance_ = null;
    private StudentUnitRecordMap studentUnitRecords_;
    private java.util.HashMap<String,StudentUnitRecordList> studentUnitRecordsByUnit_;
    private java.util.HashMap<Integer,StudentUnitRecordList> studentUnitRecordsByStudent_;
    public static StudentUnitRecordManager getInstance() {
        if (instance_ == null ) 
        	instance_ = new StudentUnitRecordManager(); 
        return instance_;
    }
    
    
    
    private StudentUnitRecordManager() {
    	studentUnitRecords_ = new StudentUnitRecordMap();
    	studentUnitRecordsByUnit_ = new java.util.HashMap<>();
    	studentUnitRecordsByStudent_ = new java.util.HashMap<>();
	}
    
    
    
    public IStudentUnitRecord getStudentUnitRecord( Integer studentId, String unitCode ) {
			IStudentUnitRecord ir = studentUnitRecords_.get(studentId.toString()+unitCode);
			return ir != null ? ir : createStudentUnitRecord(studentId, unitCode);
    }

    
    
    private List<Element> getDbRecords()
    {
    	return XMLManager.getXML().getDocument().getRootElement().getChild("studentUnitRecordTable").getChildren("record");
    }
    
    
    private IStudentUnitRecord createStudentUnitRecord( Integer studentId, String unitCode ) {
    	List<Element> dbRecords = getDbRecords();
    	String studentDbId = studentId.toString();
        for (Element dbRecord : dbRecords) {
	        if (studentDbId.equals(dbRecord.getAttributeValue("sid")) && unitCode.equals(dbRecord.getAttributeValue("uid"))) {
	        	IStudentUnitRecord newRecord = 
	        			new StudentUnitRecord( studentId, unitCode, 
	        					new Float(dbRecord.getAttributeValue("asg1")),
	        					new Float(dbRecord.getAttributeValue("asg2")),
	        					new Float(dbRecord.getAttributeValue("exam")));
	        	String hash = studentDbId+unitCode;
                studentUnitRecords_.put(hash, newRecord);
                return newRecord;
	        }
	    }
        throw new RuntimeException("DBMD: createStudent : student unit record not in file");
    }
    
    
    
    public StudentUnitRecordList getRecordsByUnit( String unitCode ) {
    	StudentUnitRecordList unitRecords = studentUnitRecordsByUnit_.get(unitCode);
	    if ( unitRecords != null ) 
	    	return unitRecords; 
	    unitRecords = new StudentUnitRecordList();
	    List<Element> dbRecords = getDbRecords();
	    
        for (Element el : dbRecords) {
		    if (unitCode.equals(el.getAttributeValue("uid")))
		    	unitRecords.add(new StudentUnitRecordProxy( new Integer(el.getAttributeValue("sid")), unitCode));
        }
        if ( unitRecords.size() > 0 ) 
            studentUnitRecordsByUnit_.put(unitCode, unitRecords); // be careful - this could be empty
        return unitRecords;
    }

	public StudentUnitRecordList getRecordsByStudent( Integer studentId ) {
	    StudentUnitRecordList studentRecords = studentUnitRecordsByStudent_.get(studentId);
	    if ( studentRecords != null ) 
	    	return studentRecords; 
	    studentRecords = new StudentUnitRecordList();
	    for (Element el : (List<Element>) XMLManager.getXML().getDocument().getRootElement().getChild("studentUnitRecordTable").getChildren("record")) 
	        if (studentId.toString().equals(el.getAttributeValue("sid"))) 
	            studentRecords.add(new StudentUnitRecordProxy( new Integer(el.getAttributeValue("sid")), el.getAttributeValue("uid")));
	    if ( studentRecords.size() > 0 ) 
	        studentUnitRecordsByStudent_.put(studentId, studentRecords); // be careful - this could be empty
	    return studentRecords;
    }

    public void saveRecord( IStudentUnitRecord irec ) {
        for (Element el : (List<Element>) XMLManager.getXML().getDocument().getRootElement().getChild("studentUnitRecordTable").getChildren("record")) {
            if (irec.getStudentID().toString().equals(el.getAttributeValue("sid")) && irec.getUnitCode().equals(el.getAttributeValue("uid"))) {
                el.setAttribute("asg1", new Float(irec.getAsg1()).toString());

        el.setAttribute("asg2", new Float(irec.getAsg2()).toString());
        el.setAttribute("exam", new Float(irec.getExam()).toString());
        XMLManager.getXML().saveDocument(); //write out the XML file for continuous save
        return;
}}
        
        
        
        
    
        
        
        
        
        
        
        
        
        
        
        
        
                        throw new RuntimeException("DBMD: saveRecord : no such student record in data");}}
