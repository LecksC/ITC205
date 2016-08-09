package datamanagement;

import java.util.HashMap;

public class ListUnitsControl
{
    private UnitManager unitManager_;



    public ListUnitsControl()
    {
        unitManager_ = UnitManager.getInstance();
    }



    public void listUnits(IUnitLister uitLister)
    {
        uitLister.clearUnits();
        HashMap<String, IUnit> unitsByUnitCode = unitManager_.getUnits();

        for (String unitCode : unitsByUnitCode.keySet()) {
            uitLister.addUnit(unitsByUnitCode.get(unitCode));
        }
    }
}
