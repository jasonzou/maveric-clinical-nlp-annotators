

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
public class Stage extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Stage.class);
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
  protected Stage() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Stage(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Stage(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Stage(JCas jcas, int begin, int end) {
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
  //* Feature: valueMain

  /** getter for valueMain - gets 
   * @generated */
  public String getValueMain() {
    if (Stage_Type.featOkTst && ((Stage_Type)jcasType).casFeat_valueMain == null)
      jcasType.jcas.throwFeatMissing("valueMain", "gov.va.maveric.uima.breastca.Stage");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Stage_Type)jcasType).casFeatCode_valueMain);}
    
  /** setter for valueMain - sets  
   * @generated */
  public void setValueMain(String v) {
    if (Stage_Type.featOkTst && ((Stage_Type)jcasType).casFeat_valueMain == null)
      jcasType.jcas.throwFeatMissing("valueMain", "gov.va.maveric.uima.breastca.Stage");
    jcasType.ll_cas.ll_setStringValue(addr, ((Stage_Type)jcasType).casFeatCode_valueMain, v);}    
   
    
  //*--------------*
  //* Feature: valueModifier

  /** getter for valueModifier - gets 
   * @generated */
  public String getValueModifier() {
    if (Stage_Type.featOkTst && ((Stage_Type)jcasType).casFeat_valueModifier == null)
      jcasType.jcas.throwFeatMissing("valueModifier", "gov.va.maveric.uima.breastca.Stage");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Stage_Type)jcasType).casFeatCode_valueModifier);}
    
  /** setter for valueModifier - sets  
   * @generated */
  public void setValueModifier(String v) {
    if (Stage_Type.featOkTst && ((Stage_Type)jcasType).casFeat_valueModifier == null)
      jcasType.jcas.throwFeatMissing("valueModifier", "gov.va.maveric.uima.breastca.Stage");
    jcasType.ll_cas.ll_setStringValue(addr, ((Stage_Type)jcasType).casFeatCode_valueModifier, v);}    
  }

    