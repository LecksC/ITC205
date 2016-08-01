package datamanagement;

public class CheckGradesControl
{
    private CheckGradesUserInterface userInterface_;
    private String currentUnitCode_ = null;
    private Integer currentStudentID_ = null;
    private boolean changed_ = false;



    public CheckGradesControl()
    {
    }



    public void execute()
    {
        userInterface_ = new CheckGradesUserInterface(this);
        userInterface_.setUnitComboBoxEnabledAndClearError(false);
        userInterface_.setStudentComboEnabledAndClearError(false);
        userInterface_.setCheckGradeButtonEnabled(false);
        userInterface_.setChangeButtonEnabled(false);
        userInterface_.setMarksEditable(false);
        userInterface_.setSaveEnabled(false);
        userInterface_.clearStudentUnitRecords();

        ListUnitsCTL unitListControl = new ListUnitsCTL();
        unitListControl.listUnits(userInterface_);
        userInterface_.setVisible(true);
        userInterface_.setUnitComboBoxEnabledAndClearError(true);
    }



    public void unitSelected(String code)
    {

        if (code.equals("NONE")) {
            userInterface_.setStudentComboEnabledAndClearError(false);
        }
        else {
            ListStudentsCTL studentListControl = new ListStudentsCTL();
            studentListControl.listStudents(userInterface_, code);
            currentUnitCode_ = code;
            userInterface_.setStudentComboEnabledAndClearError(true);
        }
        
        userInterface_.setCheckGradeButtonEnabled(false);
    }



    public void studentSelected(Integer studentId)
    {
        currentStudentID_ = studentId;
        
        if (currentStudentID_.intValue() == 0) {
            userInterface_.clearStudentUnitRecords();
            userInterface_.setCheckGradeButtonEnabled(false);
            userInterface_.setChangeButtonEnabled(false);
            userInterface_.setMarksEditable(false);
            userInterface_.setSaveEnabled(false);
        }
        else {
            IStudent student = StudentManager.get().getStudent(studentId);
            IStudentUnitRecord record = student.getUnitRecord(currentUnitCode_);

            userInterface_.setStudentUnitRecord(record);
            userInterface_.setCheckGradeButtonEnabled(true);
            userInterface_.setChangeButtonEnabled(true);
            userInterface_.setMarksEditable(false);
            userInterface_.setSaveEnabled(false);
            changed_ = false;

        }
    }



    public String checkGrade(float assignment1Mark, float assignment2Mark, float assignment3Mark)
    {
        IUnit unit = UnitManager.UM().getUnit(currentUnitCode_);
        String gradeText = unit.getGrade(assignment1Mark, assignment2Mark, assignment3Mark);
        
        userInterface_.setChangeButtonEnabled(true);
        userInterface_.setMarksEditable(false);
        if (changed_) {
            userInterface_.setSaveEnabled(true);
        }
        return gradeText;
    }



    public void enableChangeMarks()
    {
        userInterface_.setChangeButtonEnabled(false);
        userInterface_.setSaveEnabled(false);
        userInterface_.setMarksEditable(true);
        changed_ = true;
    }



    public void saveGrade(float assignment1Mark, float assignment2Mark, float examMark)
    {
        IStudent student = StudentManager.get().getStudent(currentStudentID_);
        IStudentUnitRecord record = student.getUnitRecord(currentUnitCode_);
        
        record.setAssignment1Mark(assignment1Mark);
        record.setAssignment2Mark(assignment2Mark);
        record.setExamMark(examMark);
        
        StudentUnitRecordManager.getInstance().saveRecord(record);
        
        userInterface_.setChangeButtonEnabled(true);
        userInterface_.setMarksEditable(false);
        userInterface_.setSaveEnabled(false);
    }
}
