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

/** fragment normally contains molecules.
*
*
* \n \nfragment is a container for a molecule, potentially to be joined
*  to other fragments. In addition there may be fragmentLists which
*   represent branches from the molecule. There may also be a join
*    child which is normally only found if there is a @countExpression.
*
* user-modifiable class autogenerated from schema if no class exists
* use as a shell which can be edited
* the autogeneration software will not overwrite an existing class file
*
* Current thinking on Fragment:
* (a) create one or more fragments by processing single child fragment
*   fragmentList
*       fragment
*
*   fragmentList
*       fragment
*       fragment
*       fragment
*
*  or by using a countExpression:
*   fragmentList countExpression()
*       fragment
*
* (b) list of fragments to be joined:
*   fragment
*       fragment
*       join
*       fragment
*       join
*       fragment
*       ...
*
* creates a single fragment
*
* (c) fragmentList can hold a list of fragments as above but also can hold
*   a single fragment which can be expanded through countExpression().
*     It must have exactly this format
*
*     fragmentList @countExpression
*         join
*         fragment
*
* produces:
*     fragment
*         fragment
*         join
*         fragment
*         join
*         fragment
*     ...
*
* which is the same as (b) abd cab be processed to a single fragment
*
* (d) branching can only occur from molecules and has the format:
*     molecule
*         fragmentList
*             join
*             fragment
*             join
*             fragment
* each fragment must be preceeded by a join , and fragmentList is required
* even if there is only one fragment
*
* (e) fragments usually contain one molecule:
*     fragment
*         molecule
*
*/
public class CMLFragment extends AbstractFragment {

	/** namespaced element name.*/
	public final static String NS = C_E+TAG;

	/** constructor.
	 */
    public CMLFragment() {
    }
    /** must give simple documentation.
    *
    * @param old CMLFragment to copy

    */

    public CMLFragment(CMLFragment old) {
        super((org.xmlcml.cml.element.AbstractFragment) old);
    }

    /** copy node .
    * shallow copy
    * @return Node
    */
    public Element copy() {
        return new CMLFragment(this);
    }
    /** create new instance in context of parent, overridable by subclasses.
    *
    * @param parent parent of element to be constructed (ignored by default)
    * @return CMLFragment
    */
    public CMLElement makeElementInContext(Element parent) {
        return new CMLFragment();
    }

    /** ensure integrity between list and children.
     * @return CMLFragmentList.class
     */
    public Class<?> getIndexableListClass() {
    	return CMLFragmentList.class;
    }
}
