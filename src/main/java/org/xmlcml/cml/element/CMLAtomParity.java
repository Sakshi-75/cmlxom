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

import java.util.List;

import nu.xom.Element;
import nu.xom.Node;

import org.xmlcml.cml.base.CMLElement;

/**
 * user-modifiable class supporting atomParity. * autogenerated from schema use
 * as a shell which can be edited
 *
 */
public class CMLAtomParity extends AbstractAtomParity {

	/** namespaced element name.*/
	public final static String NS = C_E+TAG;
    /** */
    public final static int TWODIM = 1;

    /** */
    public final static int THREEDIM = 2;

    /** minimum improper torsion angle to be regarded as chiral */
    public final static double MINRAD = 0.15;

    /** minimum acceptable absolute value for parity, else assumed 0 */
    public final static double EPSILON = 0.0001;

    /**
     * maximum acceptable absolute value for planarity, else assumed 0
     * (Experimental)
     */
    public final static double PLANARITYLIMIT = 0.4;

    /** */
    public double minChiralDeterminant;

    /**
     * constructor.
     */
    public CMLAtomParity() {
        super();

    }

    /**
     * constructor.
     *
     * @param old
     */
    public CMLAtomParity(CMLAtomParity old) {
        super((AbstractAtomParity) old);

    }

    /**
     * copy node .
     *
     * @return Node
     */
    public Element copy() {
        return new CMLAtomParity(this);

    }

    /**
     * create new instance in context of parent, overridable by subclasses.
     *
     * @param parent
     *            parent of element to be constructed (ignored by default)
     * @return CMLAtom
     */
    public CMLElement makeElementInContext(Element parent) {
        return new CMLAtomParity();

    }

    /**
     * sets atoms and their references. convenience method, calls
     * setAtomRefs4(String[])
     *
     * @param atoms4
     * @throws RuntimeException
     *             wrong number of atoms
     */
    public void setAtomRefs4(CMLAtom[] atoms4) throws RuntimeException {
        if (atoms4 == null || atoms4.length != 4) {
            throw new RuntimeException("Must give 4 atoms");
        }
        this.setAtomRefs4(new String[] { atoms4[0].getId(), atoms4[1].getId(),
                atoms4[2].getId(), atoms4[3].getId(), });
    }

    /**
     * Rearranges the atomRefs4 into the new order given and swaps the parity if
     * required.
     *
     * @param newAtomsRefs4
     *            the original atomRefs4, but in a new order
     *
     * @throws RuntimeException
     *             the 4 atoms do not correspond or there are duplicates
     */
    public void rearrangeAtomRefs4(String[] newAtomsRefs4) throws RuntimeException {
        String[] oldAtomsRefs4 = this.getAtomRefs4();
        if (newAtomsRefs4.length != 4) {
            throw new RuntimeException("atomRefs4 must have 4 atoms");
        }
        int parity = 0;
        for (int i = 0; i < 4; i++) {
            boolean found = false;
            for (int j = i; j < 4; j++) {
                if (newAtomsRefs4[i].equals(oldAtomsRefs4[j])) {
                    if (i != j) {
                        String temp = oldAtomsRefs4[i];
                        oldAtomsRefs4[i] = oldAtomsRefs4[j];
                        oldAtomsRefs4[j] = temp;
                        parity = 1 - parity;
                    }
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new RuntimeException(
                        "newAtomRefs4 is not a rearrangement of the original set!");
            }
        }
        this.setXMLContent(this.getXMLContent() * (1 - 2 * parity));
    }

    /**
     * get value as signed integer.
     *
     * @return +1, 0 or -1
     */
    public int getIntegerValue() {
        int i = 0;
        double value = this.getXMLContent();
        if (Math.abs(value) > MINRAD) {
            i = (value < 0) ? -1 : 1;
        }
        return i;
    }

    /**
     * gets atomRefs4 as array of atoms.
     *
     * uses the value in <atomParity> element
     *
     * @param molecule
     * @return the atoms (null if no atomRefs4)
     */
    public CMLAtom[] getAtomRefs4(CMLMolecule molecule) {
        String[] atomIds = this.getAtomRefs4();
        CMLAtom[] atoms = null;
        if (atomIds != null && atomIds.length == 4) {
            atoms = new CMLAtom[4];
            for (int i = 0; i < 4; i++) {
                atoms[i] = molecule.getAtomById(atomIds[i]);
            }
        }
        return atoms;
    }

    /**
     * utility method to create atomRefs4 attribute
     *
     * @param atoms
     * @return string array or null
     */
    public static String[] createAtomRefs4(List<CMLAtom> atoms) {
    	if (atoms == null || atoms.size() != 4) {
    		throw new RuntimeException("Bad atom list argument.");
    	}
    	String[] atomRefs4 = new String[4];
    	int i = 0;
    	for (CMLAtom atom : atoms) {
    		atomRefs4[i++] = atom.getId();
    	}
    	return atomRefs4;
    }

    /**
     * is the value zero.
     *
     * @return is (close to) zero
     */
    public boolean isZero() {
        double value = this.getXMLContent();
        return (Math.abs(value) < MINRAD);
    }

}
