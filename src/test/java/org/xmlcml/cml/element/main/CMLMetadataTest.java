package org.xmlcml.cml.element.main;

import nu.xom.Attribute;

import org.junit.Before;
import org.junit.Test;

/**
 * test for metadata.
 * 
 * @author pm286
 * 
 */

public class CMLMetadataTest {

	/**
	 * Test method for
	 * 'org.xmlcml.cml.element.CMLMetadata.addAttribute(Attribute)'
	 */
	@Test
	public void testAddAttributeAttribute() {
		CMLMetadata metadata = new CMLMetadata();
		metadata.addAttribute(new Attribute("foo", "bar"));
	}

}