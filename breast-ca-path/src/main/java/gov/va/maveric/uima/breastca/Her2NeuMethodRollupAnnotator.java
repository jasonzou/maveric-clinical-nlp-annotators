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

import gov.va.maveric.uima.post.*;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import com.google.common.collect.Sets;

/**
 * <p>
 * Creates a document level annotation based on other annotations in the same document.
 * </p>
 */
public class Her2NeuMethodRollupAnnotator extends JCasAnnotator_ImplBase
{
    private final RollUpper<Her2NeuDetectionMethod> ru;
    
    /**
     * 
     */
    public Her2NeuMethodRollupAnnotator() {
        ru = RollUpper.create(
            Her2NeuDetectionMethod.type, 
            new ValueReader<Her2NeuDetectionMethod>() {
                @Override
                public String read(Her2NeuDetectionMethod anno) { return anno.getValue(); }
            }
        );
        ru.addRule(new CombineRule(Sets.newHashSet("IHC", "FISH"), "Both"));
        ru.addRule(new SingleRule());
    }
    
    @Override
    public void process(JCas jcas) throws AnalysisEngineProcessException
    {
        String result = ru.rollup(jcas);
        if (null != result) {
            Her2NeuDetectionMethod ann = new Her2NeuDetectionMethod(jcas, -1, -1);
            ann.setValue(result);
            ann.addToIndexes();
        }
    }
}
