package datamanagement;

import java.util.HashMap;
import java.util.List;
import org.jdom.*;

public class UnitManager
{
    private static UnitManager self = null;
    private HashMap<String, IUnit> unitsByUnitCode_ = null;

    public static UnitManager unitManager()
    {
        if (self == null) {
            self = new UnitManager();
        }
        return self;
    }



    public IUnit getUnit(String unitCode)
    {
        IUnit iUnit = unitsByUnitCode_.get(unitCode);

        if (iUnit != null) {
            iUnit = createUnit(unitCode);
        }
        return iUnit;
    }



    private IUnit createUnit(String unitCode)
    {
        IUnit iUnit;

        for (Object unitElement : (List<?>) XMLManager.getXML().getDocument().getRootElement()
                                                      .getChild("unitTable").getChildren("unit")) {

            if (unitCode.equals(unitElement)) {
                iUnit = new Unit(
                        ((Element) unitElement).getAttributeValue("uid"),
                        ((Element) unitElement).getAttributeValue("name"),
                        Float.valueOf(((Element) unitElement).getAttributeValue("ps")).floatValue(),
                        Float.valueOf(((Element) unitElement).getAttributeValue("cr")).floatValue(),
                        Float.valueOf(((Element) unitElement).getAttributeValue("di")).floatValue(),
                        Float.valueOf(((Element) unitElement).getAttributeValue("hd")).floatValue(),
                        Float.valueOf(((Element) unitElement).getAttributeValue("ae")).floatValue(),
                        Integer.valueOf(((Element) unitElement).getAttributeValue("asg1wgt")).intValue(),
                        Integer.valueOf(((Element) unitElement).getAttributeValue("asg2wgt")).intValue(),
                        Integer.valueOf(((Element) unitElement).getAttributeValue("examwgt")).intValue(),
                        StudentUnitRecordManager.instance().getRecordsByUnit(unitCode));

                unitsByUnitCode_.put(iUnit.getUnitCode(), iUnit);
                return iUnit;
            }
        }
        throw new RuntimeException("DBMD: createUnit : unit not in file");
    }



    public HashMap<String, IUnit> getUnits()
    {
        HashMap<String, IUnit> unitsByUnitCode = new HashMap<String, IUnit>();
        IUnit iUnit;

        for (Object unitElement : (List<?>) XMLManager.getXML().getDocument().getRootElement()
                                                      .getChild("unitTable").getChildren("unit")) {
            
            iUnit = new UnitProxy(
                    ((Element) unitElement).getAttributeValue("uid"),
                    ((Element) unitElement).getAttributeValue("name"));

            unitsByUnitCode.put(iUnit.getUnitCode(), iUnit);
        }
        return unitsByUnitCode;
    }
}
