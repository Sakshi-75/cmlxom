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

/**
 * 
 * @author nwe23
 *
 */
public class CMLDefinition extends AbstractHTMLContainer {
    public final static String TAG = "definition";
    public final static String NS = C_E + TAG;
    
    /**
     * copy constructor. deep copy using XOM copy()
     * 
     * @param old
     *            element to copy
     */
    public CMLDefinition(CMLDefinition old) {
        super((AbstractHTMLContainer) old);
    }
    public CMLDefinition(){
        super(TAG);
    }
    
    /**
     * copy node .
     *
     * @return Node
     */
    public Element copy() {
        return new CMLDefinition(this);

    }

    /**
     * create new instance in context of parent, overridable by subclasses.
     *
     * @param parent
     *            parent of element to be constructed (ignored by default)
     * @return CMLDefinition
     */
    public CMLElement makeElementInContext(Element parent) {
        return new CMLDefinition();

    }
 
    
}
