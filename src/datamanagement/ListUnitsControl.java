package datamanagement;

import java.util.HashMap;

public class ListUnitsControl
{
    private UnitManager unitManager_;



    public ListUnitsControl()
    {
        unitManager_ = UnitManager.getInstance();
    }



    public void listUnits(IUnitLister unitLister)
    {
        unitLister.clearUnits();
        HashMap<String, IUnit> unitsByUnitCode = unitManager_.getUnits();

        for (String unitCode : unitsByUnitCode.keySet()) {
            unitLister.addUnit(unitsByUnitCode.get(unitCode));
        }
    }
}
