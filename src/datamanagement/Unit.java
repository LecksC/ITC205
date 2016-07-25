package datamanagement;

public class Unit implements IUnit
{
	private String unitCode_, unitName_;
	private float aeCutoff_, crCutoff_, psCutoff_, diCutoff_, hdCutoff_;
	private int asg1Weight_, asg2Weight_, examWeight_;
	
	private StudentUnitRecordList studentUnitRecordList_;

	public Unit (String unitCode, String unitName,
				 float aeCutoff, float psCutoff, float crCutoff, float diCutoff, float hdCutoff,
				 int asg1Weight, int asg2Weight, int examWeight,
				 StudentUnitRecordList studentUnitRecordList)
	{

		unitCode_ = unitCode;
		unitName_ = unitName;

		aeCutoff_ = aeCutoff;
		psCutoff_ = psCutoff;
		crCutoff_ = crCutoff;
		diCutoff_ = diCutoff;
		hdCutoff_ = hdCutoff;

		this.setAssessmentWeights (asg1Weight, asg2Weight, examWeight);
		
		studentUnitRecordList_ = studentUnitRecordList == null ? new StudentUnitRecordList() : studentUnitRecordList;
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
		return aeCutoff_;
	}
	
	@Override
	public void  setAeCutoff(float cutoff)
	{
		aeCutoff_ = cutoff;
	}

	@Override
	public float getPsCutoff()
	{
		return this.psCutoff_;
	}
	
	@Override
	public void  setPsCutoff(float cutoff)
	{
		psCutoff_ = cutoff;
	}
	
	@Override
	public float getCrCutoff()
	{
		return crCutoff_;
	}
	
	@Override
	public void  setCrCutoff(float cutoff)
	{
		crCutoff_ = cutoff;
	}

	@Override
	public float getDiCuttoff()
	{
		return diCutoff_;
	}
	
	@Override
	public void  setDiCutoff(float cutoff)
	{
		diCutoff_ = cutoff;
	}

	@Override
	public float getHdCutoff()
	{
		return hdCutoff_;
	}
	
	@Override
	public void  setHdCutoff(float cutoff)
	{
		hdCutoff_ = cutoff;
	}

	@Override
	public int getAsg1Weight()
	{
		return asg1Weight_;
	}
	
	@Override
	public int getAsg2Weight()
	{
		return asg2Weight_;
	}
	
	@Override
	public int getExamWeight()
	{
		return examWeight_;
	}

	@Override
	public void setAssessmentWeights(int asg1Weight, int asg2Weight, int examWeight)
	{
		if (asg1Weight < 0 || asg1Weight > 100 ||
			asg2Weight < 0 || asg2Weight > 100 ||
			examWeight < 0 || examWeight > 100 )
				throw new RuntimeException ("Assessment weights cant be less than zero or greater than 100");

		
		if (asg1Weight + asg2Weight + examWeight != 100)
			throw new RuntimeException ("Assessment weights must add to 100");
		
		asg1Weight_ = asg1Weight;
		asg2Weight_ = asg2Weight;
		examWeight_ = examWeight;			
	}
	
	@Override
	public void addStudentRecord(IStudentUnitRecord record)
	{
		studentUnitRecordList_.add(record);
	}
	
	@Override
	public IStudentUnitRecord getStudentRecord(int studentID)
	{
		for (IStudentUnitRecord r : studentUnitRecordList_)
			if (r.getStudentID() == studentID)
				return r;
		
		return null;
	}
	
	@Override
	public StudentUnitRecordList listStudentRecords()
	{
		return studentUnitRecordList_;
	}
	
	@Override
	public String getGrade(float asg1Mark, float asg2Mark, float exam1Mark)
	{
		float total = asg1Mark + asg2Mark + exam1Mark;

		if (asg1Mark  < 0 || asg1Mark  > asg1Weight_ ||
			asg2Mark  < 0 || asg2Mark  > asg2Weight_ ||
			exam1Mark < 0 || exam1Mark > examWeight_ )
			throw new RuntimeException("marks cannot be less than zero or greater than assessment weights");
		
		if		(total < aeCutoff_)	return "FL";
		else if (total < psCutoff_)	return "AE";
		else if (total < crCutoff_)	return "PS";
		else if (total < diCutoff_) return "CR";
		else if (total < hdCutoff_) return "DI";
		else						return "HD";
	}
}