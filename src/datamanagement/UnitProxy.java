package datamanagement;

public class UnitProxy
	implements IUnit
{
	private String unitCode_, unitName_;
	private UnitManager unitManager_;

	public UnitProxy(String unitCode, String unitName )
	{
		unitCode_    = unitCode;
		unitName_    = unitName;
		unitManager_ = UnitManager.unitManager();
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

	

	public void setAdditionalExaminationCutoff(float cutoff)
	{
		unitManager_.getUnit(unitCode_).setAdditionalExaminationCutoff(cutoff);
	}
	
	
	
	public float getPassCutoff()
	{
		return unitManager_.getUnit(unitCode_).getPassCutoff();
	}

	
	
	public void setPassCutoff(float cutoff)
	{
		unitManager_.getUnit(unitCode_).setPassCutoff(cutoff);
	}
	
	

	public float getCreditCutoff()
	{
		return unitManager_.getUnit(unitCode_).getCreditCutoff();
	}

	

	public void setCreditCutoff(float cutoff)
	{
		unitManager_.getUnit(unitCode_).setCreditCutoff(cutoff);
	}
	
	

	public float getDistinctionCutoff()
	{
		return unitManager_.getUnit(unitCode_).getDistinctionCutoff();
	}

	

	public void setDistinctionCutoff(float cutoff)
	{
		unitManager_.getUnit(unitCode_).setDistinctionCutoff(cutoff);
	}
	
	

	public float getHighDistinctionCutoff()
	{
		return unitManager_.getUnit(unitCode_).getHighDistinctionCutoff();
	}

	

	public void setHighDistinctionCutoff(float cutoff)
	{
		unitManager_.getUnit(unitCode_).setHighDistinctionCutoff(cutoff);
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

	

	public void setAssessmentWeights(int assignment1Weight, int assignment2Weight, int examWgt)
	{
		unitManager_.getUnit(unitCode_).setAssessmentWeights(assignment1Weight, assignment2Weight, examWgt);
	}
	


	public String getGrade(float assignment1Mark, float assignment2Mark, float exam1Mark)
	{
		return unitManager_.getUnit(unitCode_).getGrade(assignment1Mark, assignment2Mark, exam1Mark);
	}
	


	public void addStudentRecord(IStudentUnitRecord record)
	{
		unitManager_.getUnit(unitCode_).addStudentRecord(record);
	}

	

	public IStudentUnitRecord getStudentRecord(int studentId)
	{
		return unitManager_.getUnit(unitCode_).getStudentRecord(studentId);
	}

	

	public StudentUnitRecordList listStudentRecords()
	{
		return unitManager_.getUnit(unitCode_).listStudentRecords();
	}
}