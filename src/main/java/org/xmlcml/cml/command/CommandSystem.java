package org.xmlcml.cml.command;

import java.util.Map;

public class CommandSystem {

	private Map<String, ElementNG> elementMap;
	
	public CommandSystem(Map<String, ElementNG> elementMap) {
		this.elementMap = elementMap;
	}
	
	public void execute(JumboCommand command) {
		
	}
	
	public static void test1() {
		CommandSystem commandSystem = new CommandSystem(ElementNG.ELEMENTMAP);

//		String xml1 = "<atom id='a1' formalCharge='1'/>"; 
//		atomHandle = getHandleFromXML(xml1, "/"); // second arg is xpath
//		assert.nonNull(atomHandle); 
//		assert.equals("atom", getLocalName(atomHandle)); 
//		assert.equals(1, getAttribute(atomHandle, "formalCharge")); 
//		setAttribute(atomHandle, "formalCharge", plus(getAttribute(atomHandle, "formalCharge"), 1)) assert.equals(1,
//		getAttribute(atomHandle, "formalCharge")); 
//		xml2 = getXMLFromHandle(atomHandle); 
//		assert.equalsCanonically("<atom id='a1' formalCharge='2'/>", xml2);
//		assert.equalsCanonically("<atom id='a1' formalCharge='2'/>", atomHandle)
	}
}