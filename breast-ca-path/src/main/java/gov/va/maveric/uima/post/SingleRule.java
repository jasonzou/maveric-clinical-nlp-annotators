/**
 * Created on Dec 5, 2012, 5:13:45 PM
 * 
 * Copyright (C) 2012  Leonard D'Avolio
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package gov.va.maveric.uima.post;

import java.util.Set;

/**
 * <p>
 * If values has one item then return the item
 * </p>
 */
public class SingleRule implements RollUpRule
{

    @Override
    public String getResult(Set<String> values)
    {
        if (values.size() == 1) return values.iterator().next();
        return null;
    }

}
