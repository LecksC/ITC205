package datamanagement;

import java.util.HashMap;

public class ListUnitsCTL
{
    private UnitManager unitManager_;

    public ListUnitsCTL()
    {
        unitManager_ = UnitManager.unitManager();
    }



    public void listUnits(IUnitLister lister)
    {
        lister.clearUnits();
        HashMap<String,IUnit> unitsByUnitCode = unitManager_.getUnits();

        for (String unitCode : unitsByUnitCode.keySet()) {
            lister.addUnit(unitsByUnitCode.get(unitCode));
        }
    }
}