package datamanagement;

public interface IUnit
{
	public String getUnitCode();
	public void	  setUnitCode(String unitCode);
	
	public String getUnitName();
	public void   setUnitName(String unitName);
	
	public float getAdditionalExaminationCutoff();
	public void  setAdditionalExaminationCutoff(float cutoff);

	public float getPassCutoff();
	public void  setPassCutoff(float cutoff);

	public float getCreditCutoff();
	public void  setCreditCutoff(float cutoff);

	public float getDistinctionCutoff();
	public void  setDistinctionCutoff(float cutoff);

	public float getHighDistinctionCutoff();
	public void  setHighDistinctionCutoff(float cutoff);

	public int  getassignment1Weight();
	public int  getassignment2Weight();
	public int  getExamWeight();
	public void setAssessmentWeights(int asg1Weight, int asg2Weight, int examWeight);

	public String getGrade(float asg1Grade, float asg2Grade, float examGrade);

	public void addStudentRecord(IStudentUnitRecord record);
	public IStudentUnitRecord getStudentRecord(int studentID);

	public StudentUnitRecordList listStudentRecords();
	
	
	
}