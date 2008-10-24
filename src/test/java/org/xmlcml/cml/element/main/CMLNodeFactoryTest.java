package org.xmlcml.cml.element.main;

import static org.xmlcml.cml.base.BaseTest.parseValidString;
import static org.xmlcml.cml.base.CMLConstants.CML1;
import static org.xmlcml.cml.base.CMLConstants.CML_NS;
import static org.xmlcml.cml.base.CMLConstants.CML_XMLNS;
import static org.xmlcml.euclid.test.EuclidTestBase.neverThrow;
import nu.xom.Element;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.xmlcml.cml.base.CMLBuilder;
import org.xmlcml.cml.base.CMLElement;

/**
 * test OldNodeFactory.
 * 
 * @author pmr
 * 
 */
public class CMLNodeFactoryTest {

	/**
	 * Test method for
	 * 'org.xmlcml.cml.element.OldNodeFactory.startMakingElement(String,
	 * String)' * DOES NOT TEST THIS ROUTINE!!!!!!!
	 */
	@Test
	public void testStartMakingElementStringString() {
		String s1 = "<cml " + CML_XMLNS + "/>";
		CMLElement cmlElement = (CMLElement) parseValidString(s1);
		String namespace = cmlElement.getNamespaceURI();
		Assert.assertEquals("ok namespace", CML_NS, namespace);

		// guess namespace
		s1 = "<cml xmlns='" + CML1 + "'/>";
		try {
			cmlElement = (CMLElement) new CMLBuilder().parseString(s1);
		} catch (Exception e) {
			neverThrow(e);
		}
		Assert.assertTrue("is CMLElement", CMLElement.class
				.isAssignableFrom(cmlElement.getClass()));
		namespace = cmlElement.getNamespaceURI();
		Assert.assertEquals("old namespace -> new", CML_NS, namespace);

		Element element = null;
		// cannot guess namespace
		s1 = "<cml xmlns='http://foo'/>";
		try {
			element = (Element) new CMLBuilder().parseString(s1);
		} catch (Exception e) {
			neverThrow(e);
		}
		Assert.assertFalse("is CMLElement", CMLElement.class
				.isAssignableFrom(element.getClass()));
		namespace = element.getNamespaceURI();
		Assert.assertEquals("other namespace", "http://foo", namespace);
	}

	/**
	 * Test method for
	 * 'org.xmlcml.cml.element.OldNodeFactory.makeAttribute(String, String,
	 * String, Type)'
	 */
	@Ignore
	@Test
	public void testMakeAttributeStringStringStringType() {
		// String s1 = "<cml " + CML_XMLNS + "/>";
		// CMLElement cmlElement = (CMLElement) parseValidString(s1);
	}

	/**
	 * Test method for 'org.xmlcml.cml.element.OldNodeFactory.makeText(String)'
	 */
	@Ignore
	@Test
	public void testMakeTextString() {
		// TODO Auto-generated method stub
	}

}