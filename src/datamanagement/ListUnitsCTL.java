package datamanagement;

public class ListUnitsCTL
{
	private UnitManager unitManager_;
	
	public ListUnitsCTL()
	{
		unitManager_ = UnitManager.unitManager();
	}
	
	public void listUnits( IUnitLister lister )
	{
		lister.clearUnits();
		UnitMap units = unitManager_.getUnits();
		
		for (String s : units.keySet() )
			lister.addUnit(units.get(s));
	}
}