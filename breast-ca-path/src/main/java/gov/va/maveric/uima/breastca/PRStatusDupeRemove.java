/**
 * Created on Dec 5, 2012, 4:10:34 PM
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

package gov.va.maveric.uima.breastca;

import java.util.Iterator;
import java.util.List;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import com.google.common.collect.Lists;

/**
 * <p>
 * Removes overlapping PR Status annotations mostly due to the text "ER/PR Status ..."
 * causing two annotations.
 * </p>
 */
public class PRStatusDupeRemove extends JCasAnnotator_ImplBase
{
    @SuppressWarnings("unchecked")
    private static <A extends Annotation> List<A> getAnnotations(JCas jcas){
        List<A> output = Lists.newArrayList();
        FSIndex<A> index = (FSIndex<A>) jcas.getAnnotationIndex(ProgesteroneReceptorStatus.type);
        Iterator<A> it = index.iterator();
        while(it.hasNext()){
            A anno = it.next();
            output.add(anno);
        }
        return output;
    }
    
    @Override
    public void process(JCas jcas) throws AnalysisEngineProcessException
    {
        List<ProgesteroneReceptorStatus> annos = getAnnotations(jcas);
        // Brute force overlap check
        for (ProgesteroneReceptorStatus anno : annos) {
            for (ProgesteroneReceptorStatus other : annos) {
                if (anno != other) {
                    if (anno.getBegin() >= other.getBegin() && anno.getEnd() <= other.getEnd()) {
                        anno.removeFromIndexes();
                        break;
                    }
                }
            }
        }
    }
}
