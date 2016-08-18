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
    	    if (unitCode.equals(((Element) unitElement).getAttributeValue("uid"))) {
    	        IUnit unit = new Unit(unitElement.getAttributeValue("uid"),
    	                              unitElement.getAttributeValue("name"),
    	                              new Float(unitElement.getAttributeValue("ps")),
    	                              new Float(unitElement.getAttributeValue("cr")),
    	                              new Float(unitElement.getAttributeValue("di")),
    	                              new Float(unitElement.getAttributeValue("hd")),
    	                              new Float(unitElement.getAttributeValue("ae")),
    	                              new Integer(unitElement.getAttributeValue("asg1wgt")),
    	                              new Integer(unitElement.getAttributeValue("asg2wgt")),
    	                              new Integer(unitElement.getAttributeValue("examwgt")),
    	                              StudentUnitRecordManager.getInstance().getRecordsByUnit(unitCode));
				
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
            IUnit unit = new UnitProxy(unitElement.getAttributeValue("uid"),
                                       unitElement.getAttributeValue("name"));

            unitsByUnitCode.put(unit.getUnitCode(), unit);
        }
        return unitsByUnitCode;
    }
}
