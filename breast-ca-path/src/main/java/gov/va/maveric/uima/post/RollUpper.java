/**
 * Created on Dec 5, 2012, 4:39:36 PM
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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * <p>
 * Creates a single value based on other values.
 * </p>
 * 
 * @param <A>
 */
public class RollUpper<A extends Annotation>
{
    private final int targetTypeIndex;
    private final ValueReader<A> reader;
    private final List<RollUpRule> rules = Lists.newArrayList();
    
    /**
     * 
     * @param targetTypeIndex Annotation index to read from JCas
     * @param reader Reader to pull out values from annotation
     */
    public RollUpper(int targetTypeIndex, ValueReader<A> reader) {
        this.targetTypeIndex = targetTypeIndex;
        this.reader = reader;
    }
    
    /**
     * @param targetTypeIndex Annotation.type
     * @param reader
     * @return roll upper tool
     */
    public static <T extends Annotation> RollUpper<T> create(int targetTypeIndex, ValueReader<T> reader) {
        return new RollUpper<T>(targetTypeIndex, reader);
    }
    
    /**
     * Rules are evaluated in order with first taking precedence.
     * 
     * @param rule 
     */
    public void addRule(RollUpRule rule) {
        rules.add(rule);
    }
    
    @SuppressWarnings("unchecked")
    private static <A extends Annotation> List<A> getAnnotations(JCas jcas, int type){
        List<A> output = new ArrayList<A>();
        FSIndex<A> index = (FSIndex<A>) jcas.getAnnotationIndex(type);
        Iterator<A> it = index.iterator();
        while(it.hasNext()){
            A anno = it.next();
            output.add(anno);
        }
        return output;
    }
    
    Set<String> getValues(JCas jcas) {
        Set<String> values = Sets.newHashSet();
        List<A> annos = getAnnotations(jcas, targetTypeIndex);
        for (A anno : annos) {
            String value = reader.read(anno);
            if (null != value) {
                values.add(value);
            }
        }
        return values;
    }
    
    /**
     * @param values Set of values to run against rules
     * @return (nullable) Result from rules or null if none match
     */
    public String rollup(Set<String> values) {
        for (RollUpRule rule : rules) {
            String result = rule.getResult(values);
            if (null != result) return result;
        }
        return null;
    }
    
    /**
     * @param jcas  
     * @return (nullable) Result from rules on all annotations in the jcas.
     */
    public String rollup(JCas jcas) {
        return rollup(getValues(jcas));
    }
}
