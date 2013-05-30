/**
 * Created on Dec 5, 2012, 5:12:46 PM
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

import com.google.common.collect.Sets;

/**
 * <p>
 * If specified values are in the list then a single value is presented
 * </p>
 */
public class CombineRule implements RollUpRule
{
    private final Set<String> wanted;
    private final String result;
    
    /**
     * 
     * @param wanted Set of values to combine
     * @param result Result value when all in wanted are found
     */
    public CombineRule(Set<String> wanted, String result)
    {
        this.wanted = wanted;
        this.result = result;
    }

    @Override
    public String getResult(Set<String> values)
    {
        if (wanted.size() == Sets.intersection(wanted, values).size()) return result;
        return null;
    }

}
