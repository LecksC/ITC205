package datamanagement;

public class Unit
	implements IUnit
{
	private String	unitCode_, unitName_;
	private float	additionalExaminationCutoff_, passCutoff_, creditCutoff_, distinctionCutoff_, highDistinctionCutoff_;
	private int		assignment1Weight_, assignment2Weight_, examWeight_;
	private StudentUnitRecordList studentUnitRecordList_;

	
	
	public Unit (String unitCode, String unitName,
				 float additionalExaminationCutoff, float passCutoff, float creditCutoff, float distinctionCutoff, float highDistinctionCutoff,
				 int assignment1Weight, int assignment2Weight, int examWeight,
				 StudentUnitRecordList studentUnitRecordList)
	{

		unitCode_ = unitCode;
		unitName_ = unitName;

		additionalExaminationCutoff_	= additionalExaminationCutoff;
		passCutoff_						= passCutoff;
		creditCutoff_					= creditCutoff;
		distinctionCutoff_				= distinctionCutoff;
		highDistinctionCutoff_			= highDistinctionCutoff;

		this.setAssessmentWeights (assignment1Weight, assignment2Weight, examWeight);
		
		studentUnitRecordList_ = studentUnitRecordList == null ? new StudentUnitRecordList() : studentUnitRecordList;
	}
	
	
	
	@Override
	public String getUnitCode()
	{
		return unitCode_;
	}
	
	
	
	@Override
	public void setUnitCode(String unitCode)
	{
		unitCode_ = unitCode;
	}
	
	
	
	@Override
	public String getUnitName()
	{
		return unitName_;
	}
	
	
	
	@Override
	public void setUnitName(String unitName)
	{
		unitName_ = unitName;
	}
	
	
	
	@Override
	public float getAdditionalExaminationCutoff()
	{
		return additionalExaminationCutoff_;
	}
	
	
	
	@Override
	public void  setAdditionalExaminationCutoff(float cutoff)
	{
		additionalExaminationCutoff_ = cutoff;
	}

	
	
	@Override
	public float getPassCutoff()
	{
		return this.passCutoff_;
	}
	
	
	
	@Override
	public void  setPassCutoff(float cutoff)
	{
		passCutoff_ = cutoff;
	}
	
	
	
	@Override
	public float getCreditCutoff()
	{
		return creditCutoff_;
	}
	
	
	
	@Override
	public void  setCreditCutoff(float cutoff)
	{
		creditCutoff_ = cutoff;
	}

	
	
	@Override
	public float getDistinctionCutoff()
	{
		return distinctionCutoff_;
	}
	
	
	
	@Override
	public void  setDistinctionCutoff(float cutoff)
	{
		distinctionCutoff_ = cutoff;
	}

	
	
	@Override
	public float getHighDistinctionCutoff()
	{
		return highDistinctionCutoff_;
	}
	
	
	
	@Override
	public void  setHighDistinctionCutoff(float cutoff)
	{
		highDistinctionCutoff_ = cutoff;
	}

	
	
	@Override
	public int getassignment1Weight()
	{
		return assignment1Weight_;
	}
	
	
	
	@Override
	public int getassignment2Weight()
	{
		return assignment2Weight_;
	}
	
	
	
	@Override
	public int getExamWeight()
	{
		return examWeight_;
	}

	
	
	@Override
	public void setAssessmentWeights(int assignment1Weight, int assignment2Weight, int examWeight)
	{
		boolean assignment1INotInRange	= assignment1Weight < 0 || assignment1Weight > 100;
		boolean assignment2IsNotInRange	= assignment2Weight < 0 || assignment2Weight > 100;
		boolean examIsNotInRange		= examWeight		< 0 || examWeight		 > 100;
		
		if (assignment1INotInRange || assignment2IsNotInRange || examIsNotInRange )
				throw new RuntimeException ("Assessment weights cant be less than zero or greater than 100");

		
		if (assignment1Weight + assignment2Weight + examWeight != 100)
			throw new RuntimeException ("Assessment weights must add to 100");
		
		assignment1Weight_	= assignment1Weight;
		assignment2Weight_	= assignment2Weight;
		examWeight_			= examWeight;			
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
	public String getGrade(float assignment1Mark, float assignment2Mark, float exam1Mark)
	{
		float total = assignment1Mark + assignment2Mark + exam1Mark;

		boolean assignment1INotInRange	= assignment1Mark	< 0	|| assignment1Mark  > assignment1Weight_;
		boolean assignment2IsNotInRange	= assignment2Mark	< 0	|| assignment2Mark  > assignment2Weight_;
		boolean examIsNotInRange		= exam1Mark			< 0	|| exam1Mark		> examWeight_;
		
		if (assignment1INotInRange || assignment2IsNotInRange || examIsNotInRange)
			throw new RuntimeException("marks cannot be less than zero or greater than assessment weights");
		
		if		(total < additionalExaminationCutoff_)	return "FL";
		else if (total < passCutoff_)					return "AE";
		else if (total < creditCutoff_)					return "PS";
		else if (total < distinctionCutoff_)			return "CR";
		else if (total < highDistinctionCutoff_)		return "DI";
		else											return "HD";
	}
}