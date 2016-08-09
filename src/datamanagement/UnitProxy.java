package datamanagement;

import java.util.ArrayList;

public class UnitProxy
implements IUnit
{
    private String unitCode_, unitName_;
    private UnitManager unitManager_;

    public UnitProxy(String unitCode, String unitName)
    {
        unitCode_    = unitCode;
        unitName_    = unitName;
        unitManager_ = UnitManager.getInstance();
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
        return unitManager_.getUnit(unitCode_).getAdditionalExaminationCutoff();
    }



    public void setAdditionalExaminationCutoff(float additionalExaminationCutoff)
    {
        unitManager_.getUnit(unitCode_).setAdditionalExaminationCutoff(additionalExaminationCutoff);
    }



    public float getPassCutoff()
    {
        return unitManager_.getUnit(unitCode_).getPassCutoff();
    }



    public void setPassCutoff(float passCutoff)
    {
        unitManager_.getUnit(unitCode_).setPassCutoff(passCutoff);
    }



    public float getCreditCutoff()
    {
        return unitManager_.getUnit(unitCode_).getCreditCutoff();
    }



    public void setCreditCutoff(float creditCutoff)
    {
        unitManager_.getUnit(unitCode_).setCreditCutoff(creditCutoff);
    }



    public float getDistinctionCutoff()
    {
        return unitManager_.getUnit(unitCode_).getDistinctionCutoff();
    }



    public void setDistinctionCutoff(float distinctionCutoff)
    {
        unitManager_.getUnit(unitCode_).setDistinctionCutoff(distinctionCutoff);
    }



    public float getHighDistinctionCutoff()
    {
        return unitManager_.getUnit(unitCode_).getHighDistinctionCutoff();
    }



    public void setHighDistinctionCutoff(float highDistinctionCutoff)
    {
        unitManager_.getUnit(unitCode_).setHighDistinctionCutoff(highDistinctionCutoff);
    }



    public int getAssignment1Weight()
    {
        return unitManager_.getUnit(unitCode_).getAssignment1Weight();
    }



    public int getAssignment2Weight()
    {
        return unitManager_.getUnit(unitCode_).getAssignment2Weight();
    }



    public int getExamWeight()
    {
        return unitManager_.getUnit(unitCode_).getExamWeight();
    }



    public void setAssessmentWeights(int assignment1Weight, int assignment2Weight, int examWeight)
    {
        unitManager_.getUnit(unitCode_).setAssessmentWeights(assignment1Weight, assignment2Weight, examWeight);
    }



    public String getGrade(float assignment1Mark, float assignment2Mark, float exam1Mark)
    {
        return unitManager_.getUnit(unitCode_).getGrade(assignment1Mark, assignment2Mark, exam1Mark);
    }



    public void addStudentRecord(IStudentUnitRecord studentUnitRecord)
    {
        unitManager_.getUnit(unitCode_).addStudentRecord(studentUnitRecord);
    }



    public IStudentUnitRecord getStudentRecord(int studentId)
    {
        return unitManager_.getUnit(unitCode_).getStudentRecord(studentId);
    }



    public ArrayList<IStudentUnitRecord> listStudentRecords()
    {
        return unitManager_.getUnit(unitCode_).listStudentRecords();
    }
}
