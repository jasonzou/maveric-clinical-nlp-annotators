

/* First created by JCasGen Tue Oct 16 14:01:31 EDT 2012 */
package gov.va.maveric.uima.breastca;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Tue Oct 16 14:01:31 EDT 2012
 * XML source: N:/java/projects/breast-ca-clinic/src/test/resources/desc/TypeSystem.xml
 * @generated */
public class ConceptFeature extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(ConceptFeature.class);
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
  protected ConceptFeature() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public ConceptFeature(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public ConceptFeature(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public ConceptFeature(JCas jcas, int begin, int end) {
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
  //* Feature: name

  /** getter for name - gets 
   * @generated */
  public String getName() {
    if (ConceptFeature_Type.featOkTst && ((ConceptFeature_Type)jcasType).casFeat_name == null)
      jcasType.jcas.throwFeatMissing("name", "gov.va.maveric.uima.breastca.ConceptFeature");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ConceptFeature_Type)jcasType).casFeatCode_name);}
    
  /** setter for name - sets  
   * @generated */
  public void setName(String v) {
    if (ConceptFeature_Type.featOkTst && ((ConceptFeature_Type)jcasType).casFeat_name == null)
      jcasType.jcas.throwFeatMissing("name", "gov.va.maveric.uima.breastca.ConceptFeature");
    jcasType.ll_cas.ll_setStringValue(addr, ((ConceptFeature_Type)jcasType).casFeatCode_name, v);}    
   
    
  //*--------------*
  //* Feature: value

  /** getter for value - gets 
   * @generated */
  public String getValue() {
    if (ConceptFeature_Type.featOkTst && ((ConceptFeature_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "gov.va.maveric.uima.breastca.ConceptFeature");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ConceptFeature_Type)jcasType).casFeatCode_value);}
    
  /** setter for value - sets  
   * @generated */
  public void setValue(String v) {
    if (ConceptFeature_Type.featOkTst && ((ConceptFeature_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "gov.va.maveric.uima.breastca.ConceptFeature");
    jcasType.ll_cas.ll_setStringValue(addr, ((ConceptFeature_Type)jcasType).casFeatCode_value, v);}    
  }

    