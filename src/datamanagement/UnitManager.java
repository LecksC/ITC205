package datamanagement;

import java.util.HashMap;
import org.jdom.Element;

public class UnitManager
{
    private HashMap<String, IUnit> unitsByUnitCode_ = new HashMap<String, IUnit>();
    private final static UnitManager instance_ = new UnitManager();
    
    public static UnitManager getInstance()
    {
        return instance_;
    }



    public IUnit getUnit(String unitCode)
    {
        IUnit unit = unitsByUnitCode_.get(unitCode);

        if (unit != null) {
            return unit;
        }
        return createUnit(unitCode);
    }


    
    private IUnit createUnit(String unitCode)
    {
        Element[] elements = XMLManager.getInstance().getDatabaseRecords("unitTable", "unit");
    	for (Element unitElement : elements) {
    	    IUnit unit;
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

        Element[] elements = XMLManager.getInstance().getDatabaseRecords("unitTable", "unit");
        for (Element unitElement : elements) {
            IUnit unit;
            unit = new UnitProxy(((Element) unitElement).getAttributeValue("uid"),
                                 ((Element) unitElement).getAttributeValue("name"));

            unitsByUnitCode.put(unit.getUnitCode(), unit);
        }
        return unitsByUnitCode;
    }
}
