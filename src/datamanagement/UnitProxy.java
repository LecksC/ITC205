package datamanagement;

public class UnitProxy
	implements IUnit
{
	private String		unitCode_, unitName_;
	private UnitManager unitManager_;

	public UnitProxy(String unitCode, String unitName )
	{
		unitCode_	 = unitCode;
		unitName_	 = unitName;
		unitManager_ = UnitManager.unitManager();
	}

	
	
	@Override
	public String getUnitCode()
	{ 
		return unitCode_;
	}

	
	
	@Override
	public void setUnitCode(String unitCode)
	{
		unitManager_.getUnit(unitCode_).setUnitCode(unitCode);
	}
	
	
	
	@Override
	public String getUnitName()
	{
		return unitName_;
	}
	
	
	
	@Override
	public void setUnitName(String unitName)
	{
		unitManager_.getUnit(unitCode_).setUnitName(unitName);
	}
	
	
	
	@Override
	public float getAdditionalExaminationCutoff()
	{
		return unitManager_.getUnit(unitCode_).getAdditionalExaminationCutoff();
	}

	
	
	@Override
	public void setAdditionalExaminationCutoff(float cutoff)
	{
		unitManager_.getUnit(unitCode_).setAdditionalExaminationCutoff(cutoff);
	}
	
	
	
	@Override
	public float getPassCutoff()
	{
		return unitManager_.getUnit(unitCode_).getPassCutoff();
	}

	
	
	@Override
	public void setPassCutoff(float cutoff)
	{
		unitManager_.getUnit(unitCode_).setPassCutoff(cutoff);
	}
	
	
	
	@Override
	public float getCreditCutoff()
	{
		return unitManager_.getUnit(unitCode_).getCreditCutoff();
	}

	
	
	@Override
	public void setCreditCutoff(float cutoff)
	{
		unitManager_.getUnit(unitCode_).setCreditCutoff(cutoff);
	}
	
	
	
	@Override
	public float getDistinctionCutoff()
	{
		return unitManager_.getUnit(unitCode_).getDistinctionCutoff();
	}

	
	
	@Override
	public void setDistinctionCutoff(float cutoff)
	{
		unitManager_.getUnit(unitCode_).setDistinctionCutoff(cutoff);
	}
	
	
	
	@Override
	public float getHighDistinctionCutoff()
	{
		return unitManager_.getUnit(unitCode_).getHighDistinctionCutoff();
	}

	
	
	@Override
	public void setHighDistinctionCutoff(float cutoff)
	{
		unitManager_.getUnit(unitCode_).setHighDistinctionCutoff(cutoff);
	}
	
	
	
	@Override
	public int getAssignment1Weight()
	{
		return unitManager_.getUnit(unitCode_).getAssignment1Weight();
	}

	
	
	@Override
	public int getAssignment2Weight()
	{
		return unitManager_.getUnit(unitCode_).getAssignment2Weight();
	}
	
	
	
	@Override
	public int getExamWeight()
	{
		return unitManager_.getUnit(unitCode_).getExamWeight();
	}

	
	
	@Override
	public void setAssessmentWeights(int assignment1Weight, int assignment2Weight, int examWgt)
	{
		unitManager_.getUnit(unitCode_).setAssessmentWeights(assignment1Weight, assignment2Weight, examWgt);
	}
	

	
	@Override
	public String getGrade(float assignment1Mark, float assignment2Mark, float exam1Mark)
	{
		return unitManager_.getUnit(unitCode_).getGrade(assignment1Mark, assignment2Mark, exam1Mark);
	}
	

	
	@Override
	public void addStudentRecord(IStudentUnitRecord record)
	{
		unitManager_.getUnit(unitCode_).addStudentRecord(record);
	}

	
	
	@Override
	public IStudentUnitRecord getStudentRecord(int s)
	{
		return unitManager_.getUnit(unitCode_).getStudentRecord(s);
	}

	
	
	@Override
	public StudentUnitRecordList listStudentRecords()
	{
		return unitManager_.getUnit(unitCode_).listStudentRecords();
	}
}