package datamanagement;

import java.util.ArrayList;

public interface IUnit
{
    public String getUnitCode();



    public String getUnitName();



    public float getAdditionalExaminationCutoff();



    public void setAdditionalExaminationCutoff(float additionalExaminationCutoff);



    public float getPassCutoff();



    public void setPassCutoff(float passCutoff);



    public float getCreditCutoff();



    public void setCreditCutoff(float creditCutoff);



    public float getDistinctionCutoff();



    public void setDistinctionCutoff(float distinctionCutoff);



    public float getHighDistinctionCutoff();



    public void setHighDistinctionCutoff(float highDistinctionCutoff);



    public int getAssignment1Weight();



    public int getAssignment2Weight();



    public int getExamWeight();



    public void setAssessmentWeights(int assignment1Weight, int assignment2Weight, int examWeight);



    public String getGrade(float assignment1Grade, float assignment2Grade, float examGrade);



    public void addStudentRecord(IStudentUnitRecord studentUnitRecord);



    public IStudentUnitRecord getStudentRecord(int studentID);



    public ArrayList<IStudentUnitRecord> listStudentRecords();
}
