package datamanagement;

import java.util.HashMap;
import java.util.List;
import org.jdom.*;

public class UnitManager
{
    private static UnitManager self = null;
    private HashMap<String, IUnit> unitsByUnitCode_ = new HashMap<String, IUnit>();
    
    public static UnitManager unitManager()
    {
        if (self == null) {
            self = new UnitManager();
        }
        return self;
    }



    public IUnit getUnit(String unitCode)
    {
        IUnit unit = unitsByUnitCode_.get(unitCode);

        if (unit == null) {
            unit = createUnit(unitCode);
        }
        return unit;
    }


    
    private IUnit createUnit(String unitCode)
    {
    	IUnit unit;

		for (Object unitElement : (List<?>) XMLManager.getInstance().getDocument().getRootElement()
				                                      .getChild("unitTable").getChildren("unit")) {
			
			if (unitCode.equals(((Element) unitElement).getAttributeValue("uid"))) {
				unit = new Unit(((Element) unitElement).getAttributeValue("uid"),
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
				
				unitsByUnitCode_.put(unit.getUnitCode(), unit);
				return unit;
			}
		}
		throw new RuntimeException("DBMD: createUnit : unit not in file");
	}


    public HashMap<String, IUnit> getUnits()
    {
        HashMap<String, IUnit> unitsByUnitCode = new HashMap<String, IUnit>();
        IUnit unit;

        for (Object unitElement : (List<?>) XMLManager.getInstance().getDocument().getRootElement()
                                                      .getChild("unitTable").getChildren("unit")) {
            
            unit = new UnitProxy(((Element) unitElement).getAttributeValue("uid"),
                                 ((Element) unitElement).getAttributeValue("name"));

            unitsByUnitCode.put(unit.getUnitCode(), unit);
        }
        return unitsByUnitCode;
    }
}
