

/* First created by JCasGen Fri Sep 07 07:16:52 EDT 2012 */
package gov.va.maveric.uima.breastca;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Fri Sep 07 07:16:52 EDT 2012
 * XML source: N:/java/projects/breast-ca/src/test/resources/desc/TypeSystem.xml
 * @generated */
public class EstrogenReceptorStatus extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(EstrogenReceptorStatus.class);
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
  protected EstrogenReceptorStatus() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public EstrogenReceptorStatus(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public EstrogenReceptorStatus(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public EstrogenReceptorStatus(JCas jcas, int begin, int end) {
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
  //* Feature: category

  /** getter for category - gets 
   * @generated */
  public String getCategory() {
    if (EstrogenReceptorStatus_Type.featOkTst && ((EstrogenReceptorStatus_Type)jcasType).casFeat_category == null)
      jcasType.jcas.throwFeatMissing("category", "gov.va.maveric.uima.breastca.EstrogenReceptorStatus");
    return jcasType.ll_cas.ll_getStringValue(addr, ((EstrogenReceptorStatus_Type)jcasType).casFeatCode_category);}
    
  /** setter for category - sets  
   * @generated */
  public void setCategory(String v) {
    if (EstrogenReceptorStatus_Type.featOkTst && ((EstrogenReceptorStatus_Type)jcasType).casFeat_category == null)
      jcasType.jcas.throwFeatMissing("category", "gov.va.maveric.uima.breastca.EstrogenReceptorStatus");
    jcasType.ll_cas.ll_setStringValue(addr, ((EstrogenReceptorStatus_Type)jcasType).casFeatCode_category, v);}    
   
    
  //*--------------*
  //* Feature: value

  /** getter for value - gets 
   * @generated */
  public String getValue() {
    if (EstrogenReceptorStatus_Type.featOkTst && ((EstrogenReceptorStatus_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "gov.va.maveric.uima.breastca.EstrogenReceptorStatus");
    return jcasType.ll_cas.ll_getStringValue(addr, ((EstrogenReceptorStatus_Type)jcasType).casFeatCode_value);}
    
  /** setter for value - sets  
   * @generated */
  public void setValue(String v) {
    if (EstrogenReceptorStatus_Type.featOkTst && ((EstrogenReceptorStatus_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "gov.va.maveric.uima.breastca.EstrogenReceptorStatus");
    jcasType.ll_cas.ll_setStringValue(addr, ((EstrogenReceptorStatus_Type)jcasType).casFeatCode_value, v);}    
   
    
  //*--------------*
  //* Feature: ruleID

  /** getter for ruleID - gets 
   * @generated */
  public String getRuleID() {
    if (EstrogenReceptorStatus_Type.featOkTst && ((EstrogenReceptorStatus_Type)jcasType).casFeat_ruleID == null)
      jcasType.jcas.throwFeatMissing("ruleID", "gov.va.maveric.uima.breastca.EstrogenReceptorStatus");
    return jcasType.ll_cas.ll_getStringValue(addr, ((EstrogenReceptorStatus_Type)jcasType).casFeatCode_ruleID);}
    
  /** setter for ruleID - sets  
   * @generated */
  public void setRuleID(String v) {
    if (EstrogenReceptorStatus_Type.featOkTst && ((EstrogenReceptorStatus_Type)jcasType).casFeat_ruleID == null)
      jcasType.jcas.throwFeatMissing("ruleID", "gov.va.maveric.uima.breastca.EstrogenReceptorStatus");
    jcasType.ll_cas.ll_setStringValue(addr, ((EstrogenReceptorStatus_Type)jcasType).casFeatCode_ruleID, v);}    
  }

    