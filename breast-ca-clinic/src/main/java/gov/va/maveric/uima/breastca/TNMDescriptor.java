

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
public class TNMDescriptor extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(TNMDescriptor.class);
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
  protected TNMDescriptor() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public TNMDescriptor(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public TNMDescriptor(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public TNMDescriptor(JCas jcas, int begin, int end) {
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
  //* Feature: t

  /** getter for t - gets 
   * @generated */
  public String getT() {
    if (TNMDescriptor_Type.featOkTst && ((TNMDescriptor_Type)jcasType).casFeat_t == null)
      jcasType.jcas.throwFeatMissing("t", "gov.va.maveric.uima.breastca.TNMDescriptor");
    return jcasType.ll_cas.ll_getStringValue(addr, ((TNMDescriptor_Type)jcasType).casFeatCode_t);}
    
  /** setter for t - sets  
   * @generated */
  public void setT(String v) {
    if (TNMDescriptor_Type.featOkTst && ((TNMDescriptor_Type)jcasType).casFeat_t == null)
      jcasType.jcas.throwFeatMissing("t", "gov.va.maveric.uima.breastca.TNMDescriptor");
    jcasType.ll_cas.ll_setStringValue(addr, ((TNMDescriptor_Type)jcasType).casFeatCode_t, v);}    
   
    
  //*--------------*
  //* Feature: tSub

  /** getter for tSub - gets 
   * @generated */
  public String getTSub() {
    if (TNMDescriptor_Type.featOkTst && ((TNMDescriptor_Type)jcasType).casFeat_tSub == null)
      jcasType.jcas.throwFeatMissing("tSub", "gov.va.maveric.uima.breastca.TNMDescriptor");
    return jcasType.ll_cas.ll_getStringValue(addr, ((TNMDescriptor_Type)jcasType).casFeatCode_tSub);}
    
  /** setter for tSub - sets  
   * @generated */
  public void setTSub(String v) {
    if (TNMDescriptor_Type.featOkTst && ((TNMDescriptor_Type)jcasType).casFeat_tSub == null)
      jcasType.jcas.throwFeatMissing("tSub", "gov.va.maveric.uima.breastca.TNMDescriptor");
    jcasType.ll_cas.ll_setStringValue(addr, ((TNMDescriptor_Type)jcasType).casFeatCode_tSub, v);}    
   
    
  //*--------------*
  //* Feature: n

  /** getter for n - gets 
   * @generated */
  public String getN() {
    if (TNMDescriptor_Type.featOkTst && ((TNMDescriptor_Type)jcasType).casFeat_n == null)
      jcasType.jcas.throwFeatMissing("n", "gov.va.maveric.uima.breastca.TNMDescriptor");
    return jcasType.ll_cas.ll_getStringValue(addr, ((TNMDescriptor_Type)jcasType).casFeatCode_n);}
    
  /** setter for n - sets  
   * @generated */
  public void setN(String v) {
    if (TNMDescriptor_Type.featOkTst && ((TNMDescriptor_Type)jcasType).casFeat_n == null)
      jcasType.jcas.throwFeatMissing("n", "gov.va.maveric.uima.breastca.TNMDescriptor");
    jcasType.ll_cas.ll_setStringValue(addr, ((TNMDescriptor_Type)jcasType).casFeatCode_n, v);}    
   
    
  //*--------------*
  //* Feature: nSub

  /** getter for nSub - gets 
   * @generated */
  public String getNSub() {
    if (TNMDescriptor_Type.featOkTst && ((TNMDescriptor_Type)jcasType).casFeat_nSub == null)
      jcasType.jcas.throwFeatMissing("nSub", "gov.va.maveric.uima.breastca.TNMDescriptor");
    return jcasType.ll_cas.ll_getStringValue(addr, ((TNMDescriptor_Type)jcasType).casFeatCode_nSub);}
    
  /** setter for nSub - sets  
   * @generated */
  public void setNSub(String v) {
    if (TNMDescriptor_Type.featOkTst && ((TNMDescriptor_Type)jcasType).casFeat_nSub == null)
      jcasType.jcas.throwFeatMissing("nSub", "gov.va.maveric.uima.breastca.TNMDescriptor");
    jcasType.ll_cas.ll_setStringValue(addr, ((TNMDescriptor_Type)jcasType).casFeatCode_nSub, v);}    
   
    
  //*--------------*
  //* Feature: m

  /** getter for m - gets 
   * @generated */
  public String getM() {
    if (TNMDescriptor_Type.featOkTst && ((TNMDescriptor_Type)jcasType).casFeat_m == null)
      jcasType.jcas.throwFeatMissing("m", "gov.va.maveric.uima.breastca.TNMDescriptor");
    return jcasType.ll_cas.ll_getStringValue(addr, ((TNMDescriptor_Type)jcasType).casFeatCode_m);}
    
  /** setter for m - sets  
   * @generated */
  public void setM(String v) {
    if (TNMDescriptor_Type.featOkTst && ((TNMDescriptor_Type)jcasType).casFeat_m == null)
      jcasType.jcas.throwFeatMissing("m", "gov.va.maveric.uima.breastca.TNMDescriptor");
    jcasType.ll_cas.ll_setStringValue(addr, ((TNMDescriptor_Type)jcasType).casFeatCode_m, v);}    
  }

    