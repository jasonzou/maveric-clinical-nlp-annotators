/**
 * Created on Aug 6, 2012, 10:08:00 AM
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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.Document;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;

/**
 * <p>
 * Run the analysis engine on some files and output to csv files
 * </p>
 */
public class RunAndOutput
{
    private static final String NL = System.getProperty("line.separator");
    
    public static AnalysisEngine loadAnalysisEngine(File descriptor) 
    throws IOException, InvalidXMLException, ResourceInitializationException
    {
        XMLInputSource in = new XMLInputSource(descriptor);
        ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);
        
        return UIMAFramework.produceAnalysisEngine(specifier);
    }
    
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
    
    private interface Parser<A extends Annotation> {
        String getValue(A anno);
    }
    
    private static void setupFile(File outfile) throws IOException {
        Writer w = new BufferedWriter(new FileWriter(outfile, true));
        try {
            w.append("doc_id\tstart\tend\tvalue\tstring").append(NL);
        }
        finally {
            try { w.close(); } catch (IOException e) { /*ignore*/ }
        }
    }
    
    private static <A extends Annotation> void output(List<A> annos, String docid, File outfile, Parser<A> parser) throws IOException {
        Writer w = new BufferedWriter(new FileWriter(outfile, true));
        try {
            for (A anno : annos) 
            {
                String value = parser.getValue(anno);
                String normString = anno.getCoveredText().replaceAll("\r\n|\r|\n", " ").replaceAll("\\s+", " ").trim();
                int start = anno.getBegin();
                int end = anno.getEnd();
                w.append(docid + "\t" + start + "\t" + end + "\t" + value + "\t" + normString).append(NL);
            }
        }
        finally {
            try { w.close(); } catch (IOException e) { /*ignore*/ }
        }
    }
    
    private static void writeTStages(JCas jcas, String id, File output) throws IOException {
        List<TumorStage> tumorStages = getAnnotations(jcas, TumorStage.type);
        output(
            tumorStages, id, new File(output, "tumor_stage.csv"),
            new Parser<TumorStage>() {
                @Override
                public String getValue(TumorStage anno) { return anno.getValue(); }
            }
        );
    }
    
    private static void writeERStatuses(JCas jcas, String id, File output) throws IOException {
        List<EstrogenReceptorStatus> annos = getAnnotations(jcas, EstrogenReceptorStatus.type);
        output(
            annos, id, new File(output, "er_status.csv"),
            new Parser<EstrogenReceptorStatus>() {
                @Override
                public String getValue(EstrogenReceptorStatus anno) { return anno.getValue(); }
            }
        );
    }
    
    private static void writeHer2NeuMethods(JCas jcas, String id, File output) throws IOException {
        List<Her2NeuDetectionMethod> annos = getAnnotations(jcas, Her2NeuDetectionMethod.type);
        // Only write out document annotation from roll-up
        for (int i = annos.size() - 1; i >= 0; --i) {
            Her2NeuDetectionMethod anno = annos.get(i);
            if (anno.getBegin() == -1 || anno.getEnd() == -1) {
                annos.remove(i);
            }
        }
        output(
            annos, id, new File(output, "her2neu_method.csv"),
            new Parser<Her2NeuDetectionMethod>() {
                @Override
                public String getValue(Her2NeuDetectionMethod anno) { return anno.getValue(); }
            }
        );
    }
    
    private static void writeHer2NeuValues(JCas jcas, String id, File output) throws IOException {
        List<Her2NeuValue> annos = getAnnotations(jcas, Her2NeuValue.type);
        output(
            annos, id, new File(output, "her2neu_value.csv"),
            new Parser<Her2NeuValue>() {
                @Override
                public String getValue(Her2NeuValue anno) { return anno.getValue(); }
            }
        );
    }
    
    private static void writeNStages(JCas jcas, String id, File output) throws IOException {
        List<NodalStage> annos = getAnnotations(jcas, NodalStage.type);
        output(
            annos, id, new File(output, "nodal_stage.csv"),
            new Parser<NodalStage>() {
                @Override
                public String getValue(NodalStage anno) { return anno.getValue(); }
            }
        );
    }
    
    private static void writePRStatuses(JCas jcas, String id, File output) throws IOException {
        List<ProgesteroneReceptorStatus> annos = getAnnotations(jcas, ProgesteroneReceptorStatus.type);
        output(
            annos, id, new File(output, "pr_status.csv"),
            new Parser<ProgesteroneReceptorStatus>() {
                @Override
                public String getValue(ProgesteroneReceptorStatus anno) { return anno.getValue(); }
            }
        );
    }

    private static void writeGrade(JCas jcas, String id, File output) throws IOException {
        List<Grade> annos = getAnnotations(jcas, Grade.type);
        output(
            annos, id, new File(output, "grade.csv"),
            new Parser<Grade>() {
                @Override
                public String getValue(Grade anno) { return "G" + anno.getValue(); }
            }
        );
    }
    
    /**
     * @param args [0] = UIMA breast cancer annotator;
     *             [1] = input documents;
     *             [2] = output folder path
     */
    public static void main(String[] args)
    {
        int aidx = 0;
        File uimaxml = new File(args[aidx++]);
        File input = new File(args[aidx++]);
        File output = new File(args[aidx++]);
        
        AnalysisEngine ae = null;
        JCas jcas = null;
        
        try {
            ae = loadAnalysisEngine(uimaxml);
            jcas = ae.newJCas();
            
            File[] docs = input.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File f, String n) { return n.endsWith(".txt"); }
            });
            
            setupFile(new File(output, "er_status.csv"));
            setupFile(new File(output, "her2neu_method.csv"));
            setupFile(new File(output, "her2neu_value.csv"));
            setupFile(new File(output, "nodal_stage.csv"));
            setupFile(new File(output, "pr_status.csv"));
            setupFile(new File(output, "tumor_stage.csv"));
            setupFile(new File(output, "grade.csv"));
            
            DefaultEditorKit ekit = new DefaultEditorKit();
            for (File doc : docs) 
            {
                String id = doc.getName();
                System.out.println("Processing " + id);
                Reader dr = new BufferedReader(new FileReader(doc));
                Document edoc = ekit.createDefaultDocument();
                try {
                    ekit.read(dr, edoc, 0);
                }
                catch (BadLocationException e)
                {
                    throw new IOException("Bad values for " + id + ": " + e.getMessage(), e);
                }
                finally {
                    try { dr.close(); } catch (IOException e) { /*ignore*/ }
                }
                
                try
                {
                    String text = edoc.getText(0, edoc.getLength());
                    jcas.setDocumentText(text);
                    ae.process(jcas);
                }
                catch (BadLocationException e)
                {
                    throw new IOException("Bad values for " + id + ": " + e.getMessage(), e);
                }
                catch (AnalysisEngineProcessException e)
                {
                    System.err.println("##############################\nCould not process " + doc.getName() + "\n##############################");
                    throw e;
                }
                
                writeERStatuses(jcas, id, output);
                writeHer2NeuMethods(jcas, id, output);
                writeHer2NeuValues(jcas, id, output);
                writeNStages(jcas, id, output);
                writePRStatuses(jcas, id, output);
                writeTStages(jcas, id, output);
                writeGrade(jcas, id, output);
                
                jcas.reset();
            }
        }
        catch (AnalysisEngineProcessException e)
        {
            e.printStackTrace();
            System.err.println("Error processing document");
            System.err.println(e.getMessage());
        }
        catch (InvalidXMLException e)
        {
            e.printStackTrace();
            System.err.println("Error loading UIMA analysis engine");
            System.err.println(e.getMessage());
        }
        catch (ResourceInitializationException e)
        {
            e.printStackTrace();
            System.err.println("Error loading UIMA analysis engine");
            System.err.println(e.getMessage());
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.err.println("Error loading UIMA analysis engine");
            System.err.println(e.getMessage());
        }
        finally {
            if (null != jcas) jcas.release();
            if (null != ae) ae.destroy();
        }
    }

}
