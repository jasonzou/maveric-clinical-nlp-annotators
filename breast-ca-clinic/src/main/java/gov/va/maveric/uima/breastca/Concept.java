

/* First created by JCasGen Tue Oct 16 14:01:31 EDT 2012 */
package gov.va.maveric.uima.breastca;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Tue Oct 16 14:01:31 EDT 2012
 * XML source: N:/java/projects/breast-ca-clinic/src/test/resources/desc/TypeSystem.xml
 * @generated */
public class Concept extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Concept.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated  */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Concept() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Concept(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Concept(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Concept(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: features

  /** getter for features - gets 
   * @generated */
  public FSArray getFeatures() {
    if (Concept_Type.featOkTst && ((Concept_Type)jcasType).casFeat_features == null)
      jcasType.jcas.throwFeatMissing("features", "gov.va.maveric.uima.breastca.Concept");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Concept_Type)jcasType).casFeatCode_features)));}
    
  /** setter for features - sets  
   * @generated */
  public void setFeatures(FSArray v) {
    if (Concept_Type.featOkTst && ((Concept_Type)jcasType).casFeat_features == null)
      jcasType.jcas.throwFeatMissing("features", "gov.va.maveric.uima.breastca.Concept");
    jcasType.ll_cas.ll_setRefValue(addr, ((Concept_Type)jcasType).casFeatCode_features, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for features - gets an indexed value - 
   * @generated */
  public ConceptFeature getFeatures(int i) {
    if (Concept_Type.featOkTst && ((Concept_Type)jcasType).casFeat_features == null)
      jcasType.jcas.throwFeatMissing("features", "gov.va.maveric.uima.breastca.Concept");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Concept_Type)jcasType).casFeatCode_features), i);
    return (ConceptFeature)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Concept_Type)jcasType).casFeatCode_features), i)));}

  /** indexed setter for features - sets an indexed value - 
   * @generated */
  public void setFeatures(int i, ConceptFeature v) { 
    if (Concept_Type.featOkTst && ((Concept_Type)jcasType).casFeat_features == null)
      jcasType.jcas.throwFeatMissing("features", "gov.va.maveric.uima.breastca.Concept");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Concept_Type)jcasType).casFeatCode_features), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Concept_Type)jcasType).casFeatCode_features), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    