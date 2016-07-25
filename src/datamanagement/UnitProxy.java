package datamanagement;

public class UnitProxy implements IUnit
{
	private String unitCode_, unitName_;
	private UnitManager unitManager_;

	public UnitProxy(String unitCode, String unitName )
	{
		unitCode_ = unitCode;
		unitName_ = unitName;
		unitManager_ = UnitManager.UM();
	}
	
	@Override
	public String getUnitCode()
	{ 
		return unitCode_;
	}
	
	@Override
	public String getUnitName()
	{
		return unitName_;
	}
	
	@Override
    public float getAeCutoff()
    {
    	return unitManager_.getUnit(unitCode_).getAeCutoff();
    }
	
	@Override
	public void setAeCutoff(float cutoff)
    {
    	unitManager_.getUnit(unitCode_).setAeCutoff(cutoff);
    }
	
	@Override
	public float getPsCutoff()
	{
		return unitManager_.getUnit(unitCode_).getPsCutoff();
	}
	
	@Override
	public void setPsCutoff(float cutoff)
	{
		unitManager_.getUnit(unitCode_).setPsCutoff(cutoff);
	}
	
	@Override
    public float getCrCutoff()
    {
    	return unitManager_.getUnit(unitCode_).getCrCutoff();
    }
	
	@Override
    public void setCrCutoff(float cutoff)
    {
    	unitManager_.getUnit(unitCode_).setCrCutoff(cutoff);
    }
	
	@Override
    public float getDiCuttoff()
    {
    	return unitManager_.getUnit(unitCode_).getDiCuttoff();
    }
	
	@Override
    public void setDiCutoff(float cutoff)
    {
    	unitManager_.getUnit(unitCode_).setDiCutoff(cutoff);
    }
	
	@Override
    public float getHdCutoff()
    {
    	return unitManager_.getUnit(unitCode_).getHdCutoff();
    }
	
	@Override
    public void setHdCutoff(float cutoff)
    {
    	unitManager_.getUnit(unitCode_).setHdCutoff(cutoff);
    }
	
	@Override
    public int getAsg1Weight()
    {
    	return unitManager_.getUnit(unitCode_).getAsg1Weight();
    }
	
	@Override
    public int getAsg2Weight()
    {
    	return unitManager_.getUnit(unitCode_).getAsg2Weight();
    }
	
	@Override
    public int getExamWeight()
    {
    	return unitManager_.getUnit(unitCode_).getExamWeight();
    }
	
	@Override
    public void setAssessmentWeights(int asg1Wgt, int asg2Wgt, int examWgt)
    {
    	unitManager_.getUnit(unitCode_).setAssessmentWeights(asg1Wgt, asg2Wgt, examWgt);
    }
	
	@Override
    public String getGrade(float asg1Mark, float asg2Mark, float exam1Mark)
    {
    	return unitManager_.getUnit(unitCode_).getGrade(asg1Mark, asg2Mark, exam1Mark);
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