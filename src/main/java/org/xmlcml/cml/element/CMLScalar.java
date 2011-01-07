package org.xmlcml.cml.element;

import java.text.ParseException;

import nu.xom.Element;
import nu.xom.Node;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.xmlcml.cml.attribute.DictRefAttribute;
import org.xmlcml.cml.attribute.NamespaceRefAttribute;
import org.xmlcml.cml.base.CMLConstants;
import org.xmlcml.cml.base.CMLElement;
import org.xmlcml.cml.base.CMLType;
import org.xmlcml.cml.interfacex.HasDataType;
import org.xmlcml.cml.interfacex.HasDictRef;
import org.xmlcml.cml.interfacex.HasScalar;
import org.xmlcml.cml.interfacex.HasUnits;
import org.xmlcml.euclid.JodaDate;
import org.xmlcml.euclid.Util;

/**
 * user-modifiable class supporting scalar. * autogenerated from schema use as a
 * shell which can be edited
 * 
 */
public class CMLScalar extends AbstractScalar implements HasUnits, HasScalar, HasDictRef, HasDataType {
	@SuppressWarnings("unused")
	private static Logger LOG = Logger.getLogger(CMLScalar.class);

	/** namespaced element name. */
	public final static String NS = C_E + TAG;


	/**
	 * default constructor. NOTE creates a CMLScalar with dataType = XSD_STRING
	 * and content CMLConstants.S_EMPTY.
	 * 
	 */
	public CMLScalar() {
		init();
	}

	void init() {
		// setXMLContent(S_EMPTY);
	}

	/**
	 * contructor.
	 * 
	 * @param old
	 */
	public CMLScalar(CMLScalar old) {
		super((AbstractScalar) old);

	}

	/**
	 * copy node .
	 * 
	 * @return Node
	 */
	public Node copy() {
		return new CMLScalar(this);

	}

	/**
	 * create new instance in context of parent, overridable by subclasses.
	 * 
	 * @param parent
	 *            parent of element to be constructed (ignored by default)
	 * @return CMLScalar
	 */
	public CMLElement makeElementInContext(Element parent) {
		return new CMLScalar();

	}

	/**
	 * check scalar is OK.
	 * 
	 * @param parent
	 *            element
	 * @throws RuntimeException
	 *             parsing error
	 */
	public void finishMakingElement(Element parent) throws RuntimeException {
		String dataType = this.getDataType();
		if (dataType.equals(XSD_STRING)) {
		} else if (dataType.equals(XSD_BOOLEAN)) {
			this.getBoolean();
		} else if (XSD_DOUBLE.equals(CMLType.getNormalizedValue(dataType))) {
			this.getDouble();
		} else if (dataType.equals(XSD_INTEGER)) {
			this.getInt();
		} else if (dataType.equals(XSD_DATE)) {
			this.getDate();
		} else {
			throw new RuntimeException("scalar does not support dataType: "
					+ dataType);
		}
	}

	// =========================== additional constructors
	// ========================

	/**
	 * formed from components. sets dataType to xsd:boolean
	 * 
	 * @param scalar
	 */
	public CMLScalar(Boolean scalar) {
		this.setValue(scalar);
	}

	/**
	 * formed from components. sets dataType to xsd:date
	 * 
	 * @param scalar
	 */
	public CMLScalar(DateTime scalar) {
		this.setValue(scalar);
	}

	/**
	 * formed from components. sets dataType to xsd:string
	 * 
	 * @param scalar
	 */
	public CMLScalar(String scalar) {
		this.setValue(scalar);
	}

	/**
	 * formed from components. sets dataType to xsd:double
	 * 
	 * @param scalar
	 */
	public CMLScalar(double scalar) {
		this.setValue(scalar);
	}

	/**
	 * formed from components. sets dataType to xsd:integer
	 * 
	 * @param scalar
	 */
	public CMLScalar(int scalar) {
		this.setValue(scalar);
	}

	/**
	 * gets boolean. dataType must be XSD_BOOLEAN.
	 * 
	 * @return the value (null if not set)
	 */
	public Boolean getBoolean() {
		Boolean result = null;
		if (getDataType().equals(XSD_BOOLEAN)) {
			String content = getXMLContent();
			if (content != null) {
				result = new Boolean(content);
			}
		}
		return result;
	}

	/**
	 * gets real value. dataType must be XSD_DATE
	 * 
	 * @return the value (NaN if not set)
	 */
	public DateTime getDate() {
		DateTime result = null;
		if (getDataType().equals(XSD_DATE)) {
			String content = getXMLContent();
			if (content != null) {
				try {
					result = JodaDate.parseDate(content);
				} catch (Exception e) {
					throw new RuntimeException("bad date", e);
				}
			}
		}
		return result;
	}

	/**
	 * gets real value. dataType must be XSD_DOUBLE.
	 * 
	 * @return the value (NaN if not set)
	 */
	public double getDouble() {
		double result = Double.NaN;
		if (getDataType().equals(XSD_DOUBLE)) {
			String content = getXMLContent();
			if (content != null) {
				try {
					result = (Util.parseFlexibleDouble(content));
				} catch (ParseException e) {
					throw new RuntimeException("Bad double :" + content, e);
				}
			}
		}
		return result;
	}

	/**
	 * gets String value. dataType must be XSD_STRING.
	 * 
	 * @return the value (null if not set)
	 */
	public String getString() {
		String result = null;
		if (getDataType().equals(XSD_STRING)) {
			result = getXMLContent();
		}
		return result;
	}

	/**
	 * gets int value. dataType must be XSD_INTEGER.
	 * 
	 * @return the value
	 * @throws RuntimeException
	 *             if different type
	 */
	public int getInt() {
		int result = Integer.MIN_VALUE;
		if (getDataType().equals(XSD_INTEGER)) {
			String content = getXMLContent();
			if (content != null && !content.trim().equals(S_EMPTY)) {
				try {
					result = Integer.parseInt(content);
				} catch (NumberFormatException e) {
					throw new RuntimeException("bad integer content: "
							+ content);
				}
			}
		} else {
			throw new RuntimeException("wrong dataType for int "
					+ getDataType());
		}
		return result;
	}

	// ====================== subsidiary accessors =====================

	/**
	 * sets value to boolean.. updates dataType.
	 * 
	 * @param scalar
	 */
	public void setValue(Boolean scalar) {
		setXMLContent(S_EMPTY + scalar);
		super.setDataType(XSD_BOOLEAN);
	}

	/**
	 * sets value to date. updates dataType.
	 * 
	 * @param scalar
	 */
	public void setValue(DateTime scalar) {
		String date = JodaDate.formatDate(scalar);
		setXMLContent(date);
		super.setDataType(XSD_DATE);
	}

	/**
	 * sets value to String.. updates dataType.
	 * TRIMS value
	 * @param scalar no action if null
	 */
	public void setValue(String scalar) {
		if (scalar != null) {
			setXMLContent(scalar);
			super.setDataType(XSD_STRING);
		}
	}

	/**
	 * sets value to String.. updates dataType.
	 * does NOT trim value or normalize whitespace
	 * @param scalar no action if null
	 */
	public void setValueNoTrim(String scalar) {
		if (scalar != null) {
			this.removeChildren();
			this.appendChild(scalar);
			this.setDataType(XSD_STRING);
		}
	}

	/**
	 * sets value to double. updates dataType.
	 * 
	 * @param scalar
	 */
	public void setValue(double scalar) {
		setXMLContent(S_EMPTY + scalar);
		super.setDataType(XSD_DOUBLE);
	}

	/**
	 * sets value to int.. updates dataType.
	 * 
	 * @param scalar
	 */
	public void setValue(int scalar) {
		setXMLContent(S_EMPTY + scalar);
		super.setDataType(XSD_INTEGER);
	}

	/**
	 * get dataType. if attribute not set, reset to String.
	 * 
	 * @return dataType (default XSD_STRING)
	 */
	public String getDataType() {
		String dataType = super.getDataType();
		if (dataType == null) {
			dataType = XSD_STRING;
			super.setDataType(dataType);
		}
		return CMLType.getNormalizedValue(dataType);
	}

	/**
	 * get class. if attribute not set, reset to String.
	 * 
	 * @return class, default String.class
	 */
	public Class<?> getDataTypeClass() {
		Class<?> clazz = null;
		String dataType = getDataType();
		if (XSD_STRING.equals(dataType)) {
			clazz = String.class;
		} else if (XSD_DOUBLE.equals(dataType)) {
			clazz = Double.class;
		} else if (XSD_INTEGER.equals(dataType)) {
			clazz = Integer.class;
		} else if (XSD_BOOLEAN.equals(dataType)) {
			clazz = Boolean.class;
		} else if (XSD_DATE.equals(dataType)) {
			clazz = DateTime.class;
		} else {
			
		}
		return clazz;
	}

	/**
	 * set dataType. this may not be set independently of the value, so simply
	 * throws CMLRuntime only place it is usable is within copy constructor
	 * 
	 * @param dType
	 * @throws RuntimeException
	 *             attempt to reset datatype
	 */
	public void setDataType(String dType) {
		if (this.getAttributeValue("dataType") == null) {
			super.setDataType(dType);
		} else {
			throw new RuntimeException(
					"Must not reset dataType; use SetValue(...)");
		}
	}

	// ====================== functionality =====================

	/**
	 * can two scalars be used for arithmetic. checks that both scalars are
	 * numeric and of same dataType and of same size
	 * 
	 * @param scalar
	 *            the scalar to test; can have different owner
	 * @throws CMLException
	 *             if not of same numeric data type
	 */
	void checkNumericConformability(CMLScalar scalar) {
		if (!this.getDataType().equals(scalar.getDataType())) {
			throw new RuntimeException(
					"Unsuitable dataTypes for numeric operations / "
							+ this.getDataType() + CMLConstants.S_SLASH
							+ scalar.getDataType());
		}
	}

	/**
	 * subtract an scalar from this..
	 * 
	 * result = this - scalar, owner document = this does not alter this only
	 * works if both scalars are numeric and of same dataType
	 * 
	 * @param scalar
	 *            the scalar to subtract; can have different owner
	 * @throws CMLException
	 *             inappropriate dataTypes
	 * @return new scalar
	 */
	public CMLScalar subtract(CMLScalar scalar) {
		checkNumericConformability(scalar);
		CMLScalar resultScalar = null;
		if (this.getDataType().equals(XSD_DOUBLE)) {
			resultScalar = new CMLScalar(this.getDouble() - scalar.getDouble());
		} else if (this.getDataType().equals(XSD_INTEGER)) {
			resultScalar = new CMLScalar(this.getInt() - scalar.getInt());
		}
		return resultScalar;
	}

	/**
	 * subtract an scalar from this..
	 * 
	 * this -= scalar, owner document = this alters this only works if both
	 * scalars are numeric and of same dataType
	 * 
	 * @param scalar
	 *            the scalar to subtract; can have different owner
	 * @throws CMLException
	 *             inappropriate dataTypes, unequal scalars
	 */
	public void subtractEquals(CMLScalar scalar) {
		checkNumericConformability(scalar);
		if (this.getDataType().equals(XSD_DOUBLE)) {
			this.setValue(this.getDouble() - scalar.getDouble());
		} else if (this.getDataType().equals(XSD_INTEGER)) {
			this.setValue(this.getInt() - scalar.getInt());
		}
	}

	/**
	 * add a scalar to this..
	 * 
	 * result = this + scalar does not alter this only works if both scalars are
	 * numeric and of same dataType
	 * 
	 * @param scalar
	 *            the scalar to add;
	 * @throws CMLException
	 *             inappropriate dataTypes
	 * @return new scalar
	 */
	public CMLScalar plus(CMLScalar scalar) {
		checkNumericConformability(scalar);
		CMLScalar resultScalar = null;
		if (this.getDataType().equals(XSD_DOUBLE)) {
			resultScalar = new CMLScalar(this.getDouble() + scalar.getDouble());
		} else if (this.getDataType().equals(XSD_INTEGER)) {
			resultScalar = new CMLScalar(this.getInt() + scalar.getInt());
		}
		return resultScalar;
	}

	/**
	 * subtract an scalar from this..
	 * 
	 * this += scalar, owner document = this alters this only works if both
	 * scalars are numeric and of same dataType
	 * 
	 * @param scalar
	 *            the scalar to subtract;
	 * @throws CMLException
	 *             inappropriate dataTypes, unequal scalars
	 */
	public void plusEquals(CMLScalar scalar) {
		checkNumericConformability(scalar);
		if (this.getDataType().equals(XSD_DOUBLE)) {
			this.setValue(this.getDouble() + scalar.getDouble());
		} else if (this.getDataType().equals(XSD_INTEGER)) {
			this.setValue(this.getInt() + scalar.getInt());
		}
	}

	/**
	 * gets dictRef OR from parent. see
	 * DictRefAttribute.getDictRefFromElementOrParent()
	 * 
	 * @return the attribute or null
	 */
	public DictRefAttribute getDictRefFromElementOrParent() {
		return DictRefAttribute.getDictRefFromElementOrParent(this);
	}

	/**
	 * sets units attribute. requires namespace for unit to be in scope.
	 * 
	 * @param prefix
	 *            for namespace
	 * @param id
	 *            for unit
	 * @param namespaceURI
	 *            sets units namespace if not present already
	 */
	public void setUnits(String prefix, String id, String namespaceURI) {
		NamespaceRefAttribute.setUnits((HasUnits) this, prefix, id,
				namespaceURI);
	}
	
    /** null
	    * @param value title value
	    * THIS MENDS A BUG IN AUTOGENERATION OF AbstractScalar
	    */
	@Override
    public void setUnitType(String value) throws RuntimeException {
    	NamespaceRefAttribute att = null;
        if (_att_unittype == null) {
            _att_unittype = new NamespaceRefAttribute("unitType", value);
            if (_att_unittype == null) {
                throw new RuntimeException("BUG: cannot process attributeGroupName : unitType probably incompatible attributeGroupName and attributeName");
            }
        }
        att = new NamespaceRefAttribute(_att_unittype);
        super.addRemove(att, value);
    }
}
