/**
 *    Copyright 2011 Peter Murray-Rust et. al.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.xmlcml.cml.element;

import nu.xom.Element;
import nu.xom.Node;

import org.xmlcml.cml.base.CMLElement;
import org.xmlcml.euclid.Angle;
import org.xmlcml.euclid.EuclidRuntimeException;
import org.xmlcml.euclid.Util;
import org.xmlcml.euclid.Vector3;

/**
 * user-modifiable class supporting vector3. * autogenerated from schema use as
 * a shell which can be edited
 *
 */
public class CMLVector3 extends AbstractVector3 {

	/** namespaced element name.*/
	public final static String NS = C_E+TAG;

    /**
     * default. do not use
     * public for reflection
     */
    public CMLVector3() {
    }

    /**
     * contructor.
     *
     * @param old
     */
    public CMLVector3(CMLVector3 old) {
        super((AbstractVector3) old);

    }

    /**
     * copy node .
     *
     * @return Node
     */
    public Element copy() {
        return new CMLVector3(this);

    }

    /**
     * create new instance in context of parent, overridable by subclasses.
     *
     * @param parent
     *            parent of element to be constructed (ignored by default)
     * @return CMLVector3
     */
    public CMLElement makeElementInContext(Element parent) {
        return new CMLVector3();

    }

    /**
     * check vector is OK. must have 3 double components
     *
     * @param parent
     *            element
     * @throws RuntimeException
     *             parsing error
     */
    public void finishMakingElement(Element parent) throws RuntimeException {
        double[] array = this.getXMLContent();
        if (array == null) {
            throw new RuntimeException("vector must not be empty");
        } else if (array.length != 3) {
            throw new RuntimeException("vector must have 3 double components");
        }
    }

    // =========================== additional constructors
    // ========================

    /**
     * create from primitive components. this can be used with
     * jumbo.euclid.Vector3.getArray()
     *
     * @param array
     *            the 3-components
     */
    public CMLVector3(double[] array) {
        this.setXYZ3(array);
    }

    /**
     * create from Vector3.
     *
     * @param v
     *            the vector (can be zero length)
     */
    public CMLVector3(Vector3 v) {
        this();
        this.setXYZ3(v.getArray());
    }

    /**
     * create from origin to CMLPoint3. vector is from origin to point owning
     * document is taken from CMLPoint3
     *
     * @param p
     *            the point
     */
    public CMLVector3(CMLPoint3 p) {
        Vector3 v = new Vector3(p.getEuclidPoint3());
        this.setXYZ3(v.getArray());
    }

    /**
     * create from components.
     *
     * @param x
     * @param y
     * @param z
     */
    public CMLVector3(double x, double y, double z) {
        this();
        this.setXYZ3(new double[] { x, y, z });
    }

    // ====================== housekeeping methods =====================

    // convenience routine
    /*--
     static Vector3 getEuclidVector3(CMLVector3 vector) {
     return (vector == null) ? null : vector.getEuclidVector3();
     }
     --*/

    /**
     * create new CMLVector3 from Vector3.
     *
     * @param vector
     *            Vector3 to create from
     * @return the vector
     */
    static CMLVector3 createCMLVector3(Vector3 v) {
        CMLVector3 vnew = new CMLVector3();
        vnew.setXMLContent(v.getArray());
        return vnew;
    }

    // assumes vector3 != null
    /**
     * get euclid primitive.
     *
     * @return vector3
     */
    public Vector3 getEuclidVector3() {
        // does CMLVector contain array elements?
        return new Vector3(this.getXMLContent());
    }

    // ====================== subsidiary accessors =====================

    /**
     * sets components.
     *
     * @param xyz3
     *            3 components
     * @throws org.xmlcml.cml.base.RuntimeException
     *             xyz3 must be of length 3
     */
    public void setXYZ3(double[] xyz3) throws RuntimeException {
        if (xyz3.length != 3) {
            throw new RuntimeException("xyz3 must be of length 3; found: "
                    + xyz3.length);
        }
        this.setXMLContent(xyz3);
    }

    /**
     * gets components.
     *
     * @return 3-component array
     */
    public double[] getXYZ3() {
        return this.getXMLContent();
    }

    // ====================== functionality =====================

    /**
     * are two vectors equal. uses EPS to test all differences. vectors are NOT
     * normalized
     *
     * @param v
     *            vector to test
     * @return true if equal within error
     */
    public boolean isEqualTo(CMLVector3 v) {
        return isEqualTo(v, EPS);
    }

    /**
     * are two vectors equal. vectors are NOT normalized
     *
     * @param v
     *            vector to test
     * @param eps
     *            maximum absolute distance between vector components
     * @return true if equal within error
     */
    public boolean isEqualTo(CMLVector3 v, double eps) {
        boolean ok = true;
        for (int i = 0; i < 3; i++) {
            if (Math.abs(this.getXYZ3()[i] - v.getXYZ3()[i]) > eps) {
                ok = false;
                break;
            }
        }
        return ok;
    }

    /**
     * gets length.
     *
     * @return double the length
     */
    public double getLength() {
        Vector3 veucl3 = this.getEuclidVector3();
        return (veucl3 == null) ? Double.NaN : veucl3.getLength();
    }

    /**
     * gets cross product.
     *
     * @param v3
     *            vector
     * @return the cross product
     */
    public CMLVector3 getCrossProduct(CMLVector3 v3) {
        Vector3 veucl3 = this.getEuclidVector3();
        Vector3 v = veucl3.cross(v3.getEuclidVector3());
        return (v == null) ? null : CMLVector3.createCMLVector3(v);
    }

    /**
     * is one vector longer than another.
     *
     * @param v
     *            vector to compare
     * @return true if this > vector
     */
    public boolean longerThan(CMLVector3 v) {
        Vector3 veucl3 = this.getEuclidVector3();
        return (veucl3 == null) ? false : veucl3.longerThan(v
                .getEuclidVector3());
    }

    /**
     * scalar multiplication. create new vector vector = this*f does not alter this
     *
     * @param f
     *            multiplier for all components
     * @return scaled vector
     */
    public CMLVector3 multiplyBy(double f) {
        Vector3 veucl3 = this.getEuclidVector3();
        Vector3 v = (veucl3 == null) ? null : veucl3.multiplyBy(f);
        return (v == null) ? null : CMLVector3.createCMLVector3(v);
    }

    /**
     * vector addition. create new vector result = this + v3 does not alter this
     *
     * @param v3
     *            vector to add
     * @return resultant vector
     */
    public CMLVector3 plus(CMLVector3 v3) {
        Vector3 veucl3 = this.getEuclidVector3();
        Vector3 v = (veucl3 == null) ? null : veucl3
                .plus(v3.getEuclidVector3());
        return (v == null) ? null : CMLVector3.createCMLVector3(v);
    }

    /**
     * vector subtraction. create new vector result = this - v3 does not alter
     * this
     *
     * @param v3
     *            vector to subtract
     * @return resultant vector
     */
    public CMLVector3 subtract(CMLVector3 v3) {
        Vector3 veucl3 = this.getEuclidVector3();
        Vector3 v = (veucl3 == null) ? null : veucl3.subtract(v3
                .getEuclidVector3());
        return (v == null) ? null : CMLVector3.createCMLVector3(v);
    }

    /**
     * get component.
     *
     * @param n
     *            the zero-based index
     * @return the n'th component
     * @throws RuntimeException
     */
    public double elementAt(int n) throws RuntimeException {
        Util.check(n, 0, 2);
        Vector3 veucl3 = this.getEuclidVector3();
        return (veucl3 == null) ? Double.NaN : veucl3.elementAt(n);
    }

    /**
     * set component.
     *
     * @param n
     *            the zero-based index
     * @param f
     *            component value
     * @throws EuclidRuntimeException
     */
    public void setElementAt(int n, double f) throws EuclidRuntimeException {
        Util.check(n, 0, 2);
        Vector3 veucl3 = this.getEuclidVector3();
        veucl3.setElementAt(n, f);
        this.setXMLContent(veucl3.getArray());
    }

    /**
     * is vector of zero length. uses Real.isEqual
     *
     * @return if zero within tolerance
     */
    public boolean isZero() {
        Vector3 veucl3 = this.getEuclidVector3();
        return (veucl3 == null) ? false : veucl3.isZero();
    }

    /**
     * create transformed vector. does not alter this.
     *
     * @param t
     *            transform
     * @return tranformed vector
     */
    public CMLVector3 transform(CMLTransform3 t) {
        Vector3 veucl3 = this.getEuclidVector3();
        Vector3 v = (veucl3 == null) ? null : veucl3.transform(t
                .getEuclidTransform3());
        return (v == null) ? null : CMLVector3.createCMLVector3(v);
    }

    /**
     * normalize vector. alters this this = this.normalize() if zero vector
     * throws CMLRuntim
     *
     * @return vector of unit length
     * @throws RuntimeException
     *             zero vector
     */
    public CMLVector3 normalize() throws RuntimeException {
        if (this.isZero()) {
            throw new RuntimeException("Cannot normalize zero vector");
        }
        Vector3 veucl3 = this.getEuclidVector3();
        if (veucl3 != null) {
            veucl3.normalize();
            setXMLContent(veucl3.getArray());
        }
        return this;
    }

    /**
     * create dot product. result = this . v3 does not alter this.
     *
     * @param v3
     *            vector to multiply
     * @return dot product
     */
    public double dot(CMLVector3 v3) {
        Vector3 veucl3 = this.getEuclidVector3();
        return (veucl3 == null) ? Double.NaN : veucl3
                .dot(v3.getEuclidVector3());
    }

    /**
     * calculate unsigned angle between vectors result = angle between this and
     * v2 uses acos(this.dot.v2) so angle is unsigned does not alter this.
     *
     * @param v2
     *            vector to multiply
     * @return angle in radians or Double.NaN if either vector is zero
     */
    public Angle getAngleMadeWith(CMLVector3 v2) {
        Vector3 veucl3 = this.getEuclidVector3();
        return (veucl3 == null) ? null : veucl3.getAngleMadeWith(v2
                .getEuclidVector3());
    }

    /**
     * calculate scalar triple product between vectors. result = this.(v2 x v3)
     * does not alter this.
     *
     * @param v2
     *            vector to multiply
     * @param v3
     *            vector to multiply
     * @return stp
     */
    public double getScalarTripleProduct(CMLVector3 v2, CMLVector3 v3) {
        Vector3 veucl3 = this.getEuclidVector3();
        return (veucl3 == null) ? null : veucl3.getScalarTripleProduct(v2
                .getEuclidVector3(), v3.getEuclidVector3());
    }

    /**
     * projection of this onto vector. does not alter this. result = vector.norm() *
     * (this.norm() dot vector.norm())
     *
     * @param v
     *            vector to project onto
     * @return projected vector (null if zero length vectors)
     */
    public CMLVector3 projectOnto(CMLVector3 v) {
        Vector3 veucl3 = this.getEuclidVector3();
        Vector3 vv = null;
        try {
            vv = (veucl3 == null) ? null : veucl3.projectOnto(v
                    .getEuclidVector3());
        } catch (EuclidRuntimeException je) {
        }
        return (vv == null) ? null : CMLVector3.createCMLVector3(vv);
    }

    /**
     * are two vectors colinear. also returns true if one or more vectors are
     * zero
     *
     * @param v
     *            vector to test with this
     * @return true if cross product isZero()
     */
    public boolean isColinearVector(CMLVector3 v) {
        Vector3 veucl3 = this.getEuclidVector3();
        return (veucl3 == null) ? false : veucl3.isColinearVector(v
                .getEuclidVector3());
    }

    /**
     * get any vector not colinear with this. returns any axis which is not
     * colinear with this
     *
     * @return either XV or YV (even if this is zero)
     */
    public CMLVector3 getNonColinearVector() {
        Vector3 v = this.getEuclidVector3().getNonColinearVector();
        return (v == null) ? null : CMLVector3.createCMLVector3(v);
    }

    /**
     * get any vector perpendicular to this. useful for creating cartesian axes
     * containing this
     *
     * @return vector perpendicular to this (zero if this is zero)
     */
    public CMLVector3 getPerpendicularVector() {
        Vector3 v = this.getEuclidVector3().getPerpendicularVector();
        return (v == null) ? null : CMLVector3.createCMLVector3(v);
    }

    /**
     * get string.
     *
     * @return string
     */
    public String getString() {
        return this.getEuclidVector3().toString();
    }
}
