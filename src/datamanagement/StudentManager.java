package datamanagement;

import org.jdom.Element;

import java.util.HashMap;
import java.util.List;

public class StudentManager
{
    private static StudentManager instance_ = null;
    private HashMap<Integer, IStudent> studentsByStudentId_;
    private HashMap<String, HashMap<Integer, IStudent>> studentsByUnitCode_;

    private StudentManager()
    {
    	studentsByStudentId_ = new HashMap<>();
    	studentsByUnitCode_ = new HashMap<>();
    }



    public static StudentManager getInstance()
    {
        if (instance_ == null) {
        	instance_ = new StudentManager();
        }
        return instance_;
    }



    public IStudent getStudent(Integer studentId)
    {
        IStudent student = studentsByStudentId_.get(studentId);
        if (student != null) {
            return student;
        }
        return createStudent(studentId);		
    }
    
    private Element getRootElement() {
    	return XMLManager.getInstance().getDocument().getRootElement();
    }



    private Element getStudentElement(Integer studentId)
    {
		List<Element> elements = (List<Element>) getRootElement().getChild("studentTable").getChildren("student");
        for (Element element : elements) {
            if (studentId.toString().equals(element.getAttributeValue("sid"))) {
                return element;
            }
        }
        return null;
    }



    private IStudent createStudent(Integer id)
    {
        Element element = getStudentElement(id);
        if (element != null) {
            StudentUnitRecordList studentRecordList = StudentUnitRecordManager.instance().getRecordsByStudent(id);
            
            Integer studentId = new Integer(element.getAttributeValue("sid"));
            String firstName = element.getAttributeValue("fname");
            String lastName = element.getAttributeValue("lname");
            
            IStudent student = new Student(studentId,
            		                       firstName,
            		                       lastName,
            		                       studentRecordList);
            
            studentsByStudentId_.put(student.getStudentId(), student);
            return student;
        }
        throw new RuntimeException("DBMD: createStudent : student not in file");
    }



    private IStudent createStudentProxy(Integer studentId)
    {
        Element element = getStudentElement(studentId);
        if (element != null) {
            return new StudentProxy(studentId,
            		                element.getAttributeValue("fname"),
            		                element.getAttributeValue("lname"));
        }
        throw new RuntimeException("DBMD: createStudent : student not in file");
    }



    public HashMap<Integer, IStudent> getStudentsByUnit(String unitCode)
    {
    	HashMap<Integer, IStudent> studentMap = studentsByUnitCode_.get(unitCode);
        if (studentMap != null) {
            return studentMap;
        }
        
        studentMap = new HashMap<Integer, IStudent>();
        StudentUnitRecordList studentUnitRecords = StudentUnitRecordManager.instance().getRecordsByUnit(unitCode);

        for (IStudentUnitRecord studentUnitRecord : studentUnitRecords) {
        	IStudent student = createStudentProxy(new Integer(studentUnitRecord.getStudentID()));
        	studentMap.put(student.getStudentId(), student);
        }

        studentsByUnitCode_.put(unitCode, studentMap);
        return studentMap;
	}
}
