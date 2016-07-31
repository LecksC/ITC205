package datamanagement;

import org.jdom.Element;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StudentUnitRecordManager
{
    private static StudentUnitRecordManager instance_ = null;

    private Map<String, IStudentUnitRecord> recordByStudentIdAndUnitCode_;
    private Map<String, List<IStudentUnitRecord>> recordsByUnitCode_;
    private Map<Integer, List<IStudentUnitRecord>> recordsByStudentId_;



    private StudentUnitRecordManager()
    {
        recordByStudentIdAndUnitCode_ =
                                      new HashMap<String, IStudentUnitRecord>();
        recordsByUnitCode_ =
                           new HashMap<String, List<IStudentUnitRecord>>();
        recordsByStudentId_ =
                            new HashMap<Integer, List<IStudentUnitRecord>>();
    }



    public static StudentUnitRecordManager getInstance()
    {
        if (instance_ == null) {
            instance_ = new StudentUnitRecordManager();
        }
        return instance_;
    }



    public IStudentUnitRecord getStudentUnitRecord(Integer studentId,
                                                   String unitCode)
    {
        String hash = getRecordHash(studentId, unitCode);
        IStudentUnitRecord record = recordByStudentIdAndUnitCode_.get(hash);
        if (record != null) {
            return record;
        }

        return loadStudentUnitRecord(studentId, unitCode);
    }



    public List<IStudentUnitRecord> getRecordsByStudent(Integer studentId)
    {
        List<IStudentUnitRecord> records = recordsByStudentId_.get(studentId);
        if (records != null) {
            return records;
        }

        return loadRecordsByStudent(studentId);
    }



    public List<IStudentUnitRecord> getRecordsByUnit(String unitCode)
    {
        List<IStudentUnitRecord> record = recordsByUnitCode_.get(unitCode);
        if (record != null) {
            return record;
        }

        return loadRecordsByUnit(unitCode);
    }



    public void saveRecord(IStudentUnitRecord record)
    {
        List<Element> databaseRecords = findRecords(record.getStudentID(),
                                                    record.getUnitCode());
        if (databaseRecords.size() == 0) {
            throw new RuntimeException("DBMD: saveRecord : no such student"
                                       + " record in data");

        }
        Element databaseRecord = databaseRecords.get(0);

        databaseRecord.setAttribute("asg1",
                                    Float.toString(record.getAssignment1Mark()));
        databaseRecord.setAttribute("asg2",
                                    Float.toString(record.getAssignment2Mark()));
        databaseRecord.setAttribute("exam",
                                    Float.toString(record.getExamMark()));

        XMLManager.getXML().saveDocument();
        return;
    }



    private IStudentUnitRecord loadStudentUnitRecord(Integer studentId,
                                                     String unitCode)
    {
        List<Element> databaseRecords = findRecords(studentId, unitCode);
        if (databaseRecords.size() == 0) {
            throw new RuntimeException("DBMD: createStudentUnitRecord : student unit record not in file");
        }

        Element databaseRecord = databaseRecords.get(0);
        IStudentUnitRecord newRecord =
                                     new StudentUnitRecord(studentId, unitCode,
                                                           new Float(databaseRecord.getAttributeValue("asg1")),
                                                           new Float(databaseRecord.getAttributeValue("asg2")),
                                                           new Float(databaseRecord.getAttributeValue("exam")));

        String hash = getRecordHash(studentId, unitCode);
        recordByStudentIdAndUnitCode_.put(hash, newRecord);

        return newRecord;
    }



    private List<IStudentUnitRecord> loadRecordsByUnit(String unitCode)
    {
        List<IStudentUnitRecord> unitRecords =
                                             new ArrayList<IStudentUnitRecord>();
        List<Element> databaseRecords = findRecords(null, unitCode);

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



    private List<IStudentUnitRecord> loadRecordsByStudent(Integer studentId)
    {
        List<IStudentUnitRecord> records =
                                              new ArrayList<IStudentUnitRecord>();
        List<Element> databaseRecords = findRecords(studentId, null);

        if (databaseRecords.size() == 0) {
            return records;
        }
        for (Element databaseRecord : databaseRecords) {
            String unitCode = databaseRecord.getAttributeValue("uid");
            records.add(new StudentUnitRecordProxy(studentId, unitCode));
        }

        recordsByStudentId_.put(studentId, records);

        return records;
    }



    private List<Element> findRecords(Integer studentId, String unitCode)
    {
        List<Element> matchingRecords = new ArrayList<Element>();
        Element[] databaseRecords =
                                  getDatabaseRecords("studentUnitRecordTable");
        for (Element databaseRecord : databaseRecords) {

            if (studentId != null) {
                Integer databaseStudentId =
                                          new Integer(databaseRecord.getAttributeValue("sid"));
                if (!studentId.equals(databaseStudentId)) {
                    continue;
                }
            }

            if (unitCode != null) {
                String databaseUnitCode =
                                        databaseRecord.getAttributeValue("uid");
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



    // This should be in XMLManager
    private Element[] getDatabaseRecords(String tableName)
    {
        Object[] records = XMLManager.getXML().getDocument().getRootElement()
                                     .getChild(tableName).getChildren("record")
                                     .toArray();

        Element[] elementArray = Arrays.copyOf(records, records.length,
                                               Element[].class);

        return elementArray;
    }
}
