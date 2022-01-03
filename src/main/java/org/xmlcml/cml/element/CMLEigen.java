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

import org.xmlcml.cml.base.CMLConstants;
import org.xmlcml.cml.base.CMLElement;
import org.xmlcml.euclid.RealArray;
import org.xmlcml.euclid.RealMatrix;
import org.xmlcml.euclid.Util;

/**
 * user-modifiable class supporting eigen. * autogenerated from schema use as a
 * shell which can be edited
 *
 */
public class CMLEigen extends AbstractEigen {

	/** namespaced element name.*/
	public final static String NS = C_E+TAG;

    /** orientation of matrix. */
    public enum Orientation {
        /** values down */
        VALUES_ROWS("rowVectors", "eigenvalues correspond to rows"),
        /** values across */
        VALUES_COLS("columnVectors", "eigenvalues correspond to columns");
        /** this should corespond to schema enumeration */
        public String value;

        /** a description */
        public String desc;

        private Orientation(String value, String desc) {
            this.desc = desc;
            this.value = value;
        }
    }

    /**
     * constructor.
     */
    public CMLEigen() {
    }

    /**
     * constructor.
     *
     * @param old
     */
    public CMLEigen(CMLEigen old) {
        super((AbstractEigen) old);

    }

    /**
     * copy node .
     *
     * @return Node
     */
    public Element copy() {
        return new CMLEigen(this);

    }

    /**
     * create new instance in context of parent, overridable by subclasses.
     *
     * @param parent
     *            parent of element to be constructed (ignored by default)
     * @return CMLEigen
     */
    public CMLElement makeElementInContext(Element parent) {
        CMLEigen eigen = new CMLEigen();
        return eigen;
    }

    /**
     * check child array and matrix and update.
     *
     * @param parent
     *            element
     */
    public void finishMakingElement(Element parent) {
        CMLArray eigenvalues = this.getEigenvalues();
        CMLMatrix eigenvectors = this.getEigenvectors();
        if (eigenvalues != null && eigenvectors != null) {
            if (eigenvectors.getRowsAttribute() == null
                    || eigenvectors.getColumnsAttribute() == null) {
                throw new RuntimeException(
                        "must give rows and columns attributes on eigenvectors");
            }
            String orientation = this.getOrientation();
            if (!Orientation.VALUES_COLS.value.equals(orientation)
                    && !Orientation.VALUES_ROWS.value.equals(orientation)) {
                throw new RuntimeException(
                        "must give valid orientation on eigenvectors: "
                                + orientation);
            }
        }
    }

    /**
     * create from matched eigenvectors and eigenvalues. must be of matched
     * size.
     *
     * @param eigenvectors
     * @param eigenvalues
     * @param orient
     *            orientation
     * @throws CMLException
     */
    public CMLEigen(CMLMatrix eigenvectors, CMLArray eigenvalues,
            Orientation orient) {
        if (eigenvectors == null && eigenvalues == null) {
            throw new RuntimeException("null eigen argument(s)");
        }
        if (eigenvectors.getRows() != eigenvectors.getColumns()) {
            throw new RuntimeException("eigenvector matrix must be square: rows("
                    + eigenvectors.getRows() + ") columns ("
                    + eigenvectors.getColumns() + CMLConstants.S_RBRAK);
        }
        if (eigenvalues.getSize() != eigenvectors.getColumns()) {
            throw new RuntimeException("eigenvector matrix ("
                    + eigenvectors.getColumns()
                    + ") incompatible with eigenvalues ("
                    + eigenvalues.getSize() + CMLConstants.S_RBRAK);
        }
        if (!(XSD_DOUBLE.equals(eigenvalues.getDataType()))) {
            throw new RuntimeException("eigenvalue matrix must be real numbers");
        }
        if (!(XSD_DOUBLE.equals(eigenvectors.getDataType()))) {
            throw new RuntimeException("eigenvector matrix must be real numbers");
        }
        this.appendChild(eigenvalues);
        this.appendChild(eigenvectors);
        this.setOrientation(orient.value);
        // RealMatrix rm = eigenvectors.getEuclidRealMatrix();
    }

    /**
     * return size of matrix and array.
     *
     * @return the size (or 0 if no valid matrix)
     */
    public int getSize() {
        int size = 0;
        CMLArray eigenvalues = this.getEigenvalues();
        if (eigenvalues != null) {
            size = eigenvalues.getDoubles().length;
        }
        return size;
    }

    /**
     * gets eigenvectors. note that the orientation is not normalized
     *
     * @return eigenvectors
     */
    public CMLMatrix getEigenvectors() {
        CMLMatrix eigenvectors = (this.getMatrixElements().size() == 1) ? this
                .getMatrixElements().get(0) : null;
        if (eigenvectors != null
                && !XSD_DOUBLE.equals(eigenvectors.getDataType())) {
            throw new RuntimeException("eigenvectors array must be of type double");
        }
        return eigenvectors;
    }

    /**
     * gets eigenvectors. note that the orientation is not normalized
     *
     * @return eigenvectors
     */
    public CMLArray getEigenvalues() {
        CMLArray eigenvalues = (this.getArrayElements().size() == 1) ? this
                .getArrayElements().get(0) : null;
        if (eigenvalues != null
                && !XSD_DOUBLE.equals(eigenvalues.getDataType())) {
            throw new RuntimeException("eigenvalues array must be of type double");
        }
        return eigenvalues;
    }

    /**
     * gets given eigenvector. convenience method. Inefficient if many
     * eigenvectors are required in which case a CMLMatrix should be extracted
     * and processed.
     *
     * @param serial
     * @return eigenvector or null if none
     * @throws RuntimeException
     *             if serial is out of range
     */
    public RealArray getEigenvector(int serial) throws RuntimeException {
        RealArray array = null;
        if (serial < 0) {
            throw new RuntimeException("bad index: " + serial);
        }
        CMLMatrix matrix = getEigenvectors();
        if (matrix != null) {
            if (serial >= matrix.getRows()) {
                throw new RuntimeException("bad index: " + serial);
            }
            if (matrix != null && serial < matrix.getRows()
                    && XSD_DOUBLE.equals(matrix.getDataType())) {
                RealMatrix mat = matrix.getEuclidRealMatrix();
                if (Orientation.VALUES_COLS.value.equals(this.getOrientation())) {
                    array = mat.extractColumnData(serial);
                } else if (Orientation.VALUES_ROWS.value.equals(this
                        .getOrientation())) {
                    Util.println("ROW");
                    array = mat.extractRowData(serial);
                } else {
                    throw new RuntimeException("unknown orientation: "
                            + this.getOrientation());
                }
            }
        }
        return array;
    }
}
