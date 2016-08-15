package datamanagement;

import org.jdom.Element;

import java.util.ArrayList;
import java.util.HashMap;


public class StudentUnitRecordManager
{
    private static StudentUnitRecordManager instance_ = new StudentUnitRecordManager();

    private HashMap<String, IStudentUnitRecord> recordByStudentIdAndUnitCode_;
    private HashMap<String, ArrayList<IStudentUnitRecord>> recordsByUnitCode_;
    private HashMap<Integer, ArrayList<IStudentUnitRecord>> recordsByStudentId_;



    private StudentUnitRecordManager()
    {
        recordByStudentIdAndUnitCode_ = new HashMap<String, IStudentUnitRecord>();
        recordsByUnitCode_ = new HashMap<String, ArrayList<IStudentUnitRecord>>();
        recordsByStudentId_ = new HashMap<Integer, ArrayList<IStudentUnitRecord>>();
    }



    public static StudentUnitRecordManager getInstance()
    {
        return instance_;
    }



    public IStudentUnitRecord getStudentUnitRecord(Integer studentId, String unitCode)
    {
        String recordHash = getRecordHash(studentId, unitCode);
        IStudentUnitRecord studentUnitRecord = recordByStudentIdAndUnitCode_.get(recordHash);
        if (studentUnitRecord != null) {
            return studentUnitRecord;
        }

        return loadRecord(studentId, unitCode);
    }



    public ArrayList<IStudentUnitRecord> getRecordsByStudent(Integer studentId)
    {
        ArrayList<IStudentUnitRecord> studentUnitRecords = recordsByStudentId_.get(studentId);
        if (studentUnitRecords != null) {
            return studentUnitRecords;
        }

        return loadRecordsByStudent(studentId);
    }



    public ArrayList<IStudentUnitRecord> getRecordsByUnit(String unitCode)
    {
        ArrayList<IStudentUnitRecord> studentUnitRecord = recordsByUnitCode_.get(unitCode);
        if (studentUnitRecord != null) {
            return studentUnitRecord;
        }

        return loadRecordsByUnit(unitCode);
    }



    public void saveRecord(IStudentUnitRecord studentUnitRecord)
    {
        ArrayList<Element> databaseRecords = findRecords(studentUnitRecord.getStudentID(), studentUnitRecord.getUnitCode());
        if (databaseRecords.size() == 0) {
            throw new RuntimeException("DBMD: saveRecord : no such student record in data");

        }
        Element databaseRecord = databaseRecords.get(0);

        databaseRecord.setAttribute("asg1", Float.toString(studentUnitRecord.getAssignment1Mark()));
        databaseRecord.setAttribute("asg2", Float.toString(studentUnitRecord.getAssignment2Mark()));
        databaseRecord.setAttribute("exam", Float.toString(studentUnitRecord.getExamMark()));

        XMLManager.getInstance().saveDocument();
        return;
    }



    private IStudentUnitRecord loadRecord(Integer studentId, String unitCode)
    {
        ArrayList<Element> databaseRecords = findRecords(studentId, unitCode);
        if (databaseRecords.size() == 0) {
            throw new RuntimeException("DBMD: loadStudentUnitRecord : student unit record not in file");
        }

        Element databaseRecord = databaseRecords.get(0);
        IStudentUnitRecord newRecord = new StudentUnitRecord(studentId, unitCode,
                                                             new Float(databaseRecord.getAttributeValue("asg1")),
                                                             new Float(databaseRecord.getAttributeValue("asg2")),
                                                             new Float(databaseRecord.getAttributeValue("exam")));

        String recordHash = getRecordHash(studentId, unitCode);
        recordByStudentIdAndUnitCode_.put(recordHash, newRecord);

        return newRecord;
    }



    private ArrayList<IStudentUnitRecord> loadRecordsByUnit(String unitCode)
    {
        ArrayList<IStudentUnitRecord> unitRecords = new ArrayList<IStudentUnitRecord>();
        ArrayList<Element> databaseRecords = findRecords(null, unitCode);

        if (databaseRecords.size() == 0) {
            return unitRecords;
        }
        for (Element el : databaseRecords) {
            Integer studentId = new Integer(el.getAttributeValue("sid"));
            unitRecords.add(new StudentUnitRecordProxy(studentId, unitCode));
        }

        recordsByUnitCode_.put(unitCode, unitRecords);

        return unitRecords;
    }



    private ArrayList<IStudentUnitRecord> loadRecordsByStudent(Integer studentId)
    {
        ArrayList<IStudentUnitRecord> studentRecords = new ArrayList<IStudentUnitRecord>();
        ArrayList<Element> databaseRecords = findRecords(studentId, null);

        if (databaseRecords.size() == 0) {
            return studentRecords;
        }
        for (Element databaseRecord : databaseRecords) {
            String unitCode = databaseRecord.getAttributeValue("uid");
            studentRecords.add(new StudentUnitRecordProxy(studentId, unitCode));
        }

        recordsByStudentId_.put(studentId, studentRecords);

        return studentRecords;
    }



    private ArrayList<Element> findRecords(Integer studentId, String unitCode)
    {
        ArrayList<Element> matchingRecords = new ArrayList<Element>();
        Element[] databaseRecords = XMLManager.getInstance().getDatabaseRecords("studentUnitRecordTable", "record");
        for (Element databaseRecord : databaseRecords) {

            if (studentId != null) {
                Integer databaseStudentId = new Integer(databaseRecord.getAttributeValue("sid"));
                if (!studentId.equals(databaseStudentId)) {
                    continue;
                }
            }

            if (unitCode != null) {
                String databaseUnitCode = databaseRecord.getAttributeValue("uid");
                if (!unitCode.equals(databaseUnitCode)) {
                    continue;
                }
            }

            matchingRecords.add(databaseRecord);
        }

        return matchingRecords;
    }



    private String getRecordHash(Integer studentId, String unitCode)
    {
        return studentId.toString() + unitCode;
    }
}
