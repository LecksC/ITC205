package datamanagement;

public class Unit
implements IUnit
{
    private String unitCode_, unitName_;
    private float additionalExaminationCutoff_, passCutoff_, creditCutoff_, distinctionCutoff_, highDistinctionCutoff_;
    private int	assignment1Weight_, assignment2Weight_, examWeight_;
    private StudentUnitRecordList studentUnitRecordList_;



    public Unit(String unitCode, String unitName,
                float additionalExaminationCutoff, float passCutoff, float creditCutoff,
                float distinctionCutoff, float highDistinctionCutoff,
                int assignment1Weight, int assignment2Weight, int examWeight,
                StudentUnitRecordList studentUnitRecordList)
    {

        unitCode_ = unitCode;
        unitName_ = unitName;

        additionalExaminationCutoff_ = additionalExaminationCutoff;
        passCutoff_                  = passCutoff;
        creditCutoff_                = creditCutoff;
        distinctionCutoff_           = distinctionCutoff;
        highDistinctionCutoff_       = highDistinctionCutoff;

        this.setAssessmentWeights(assignment1Weight, assignment2Weight, examWeight);

        if (studentUnitRecordList == null) {
            studentUnitRecordList = new StudentUnitRecordList();
        } else {
            studentUnitRecordList_ = studentUnitRecordList;
        }
    }



    public String getUnitCode()
    {
        return unitCode_;
    }



    public String getUnitName()
    {
        return unitName_;
    }



    public float getAdditionalExaminationCutoff()
    {
        return additionalExaminationCutoff_;
    }



    public void setAdditionalExaminationCutoff(float additionalExaminationCutoff)
    {
        additionalExaminationCutoff_ = additionalExaminationCutoff;
    }



    public float getPassCutoff()
    {
        return this.passCutoff_;
    }



    public void setPassCutoff(float passCutoff)
    {
        passCutoff_ = passCutoff;
    }



    public float getCreditCutoff()
    {
        return creditCutoff_;
    }



    public void setCreditCutoff(float creditCutoff)
    {
        creditCutoff_ = creditCutoff;
    }



    public float getDistinctionCutoff()
    {
        return distinctionCutoff_;
    }



    public void setDistinctionCutoff(float distinctionCutoff)
    {
        distinctionCutoff_ = distinctionCutoff;
    }



    public float getHighDistinctionCutoff()
    {
        return highDistinctionCutoff_;
    }



    public void setHighDistinctionCutoff(float highDistinctionCutoff)
    {
        highDistinctionCutoff_ = highDistinctionCutoff;
    }



    public int getAssignment1Weight()
    {
        return assignment1Weight_;
    }



    public int getAssignment2Weight()
    {
        return assignment2Weight_;
    }



    public int getExamWeight()
    {
        return examWeight_;
    }



    public void setAssessmentWeights(int assignment1Weight, int assignment2Weight, int examWeight)
    {
        boolean assignment1IsValid = assignment1Weight < 0 || assignment1Weight > 100;
        boolean assignment2IsValid = assignment2Weight < 0 || assignment2Weight > 100;
        boolean examIsValide       = examWeight        < 0 || examWeight        > 100;

        if (!assignment1IsValid || !assignment2IsValid || !examIsValide) {
            throw new RuntimeException ("Assessment weights cant be less than zero or greater than 100");
        }


        if (assignment1Weight + assignment2Weight + examWeight != 100) {
            throw new RuntimeException ("Assessment weights must add to 100");
        }

        assignment1Weight_ = assignment1Weight;
        assignment2Weight_ = assignment2Weight;
        examWeight_        = examWeight;			
    }



    public void addStudentRecord(IStudentUnitRecord studentUnitRecord)
    {
        studentUnitRecordList_.add(studentUnitRecord);
    }



    public IStudentUnitRecord getStudentRecord(int studentId)
    {
        for (IStudentUnitRecord record : studentUnitRecordList_) {
            if (record.getStudentID() == studentId) {
                return record;
            }
        }
        return null;
    }



    public StudentUnitRecordList listStudentRecords()
    {
        return studentUnitRecordList_;
    }



    public String getGrade(float assignment1Mark, float assignment2Mark, float exam1Mark)
    {
        float total = assignment1Mark + assignment2Mark + exam1Mark;

        boolean assignment1IsValid = assignment1Mark >= 0 || assignment1Mark <= assignment1Weight_;
        boolean assignment2IsValid = assignment2Mark >= 0 || assignment2Mark <= assignment2Weight_;
        boolean examIsValid        = exam1Mark       >= 0 || exam1Mark       <= examWeight_;

        if (!assignment1IsValid || !assignment2IsValid || !examIsValid) {
            throw new RuntimeException("marks cannot be less than zero or greater than assessment weights");
        }

        if (total < additionalExaminationCutoff_) {
            return "FL";
        }
        else if (total < passCutoff_) {
            return "AE";
        }
        else if (total < creditCutoff_) {
            return "PS";
        }
        else if (total < distinctionCutoff_) {
            return "CR";
        }
        else if (total < highDistinctionCutoff_) {
            return "DI";
        }
        else {
            return "HD";
        }
    }
}