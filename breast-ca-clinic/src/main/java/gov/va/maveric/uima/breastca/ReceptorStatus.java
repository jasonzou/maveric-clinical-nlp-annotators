

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
public class ReceptorStatus extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(ReceptorStatus.class);
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
  protected ReceptorStatus() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public ReceptorStatus(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public ReceptorStatus(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public ReceptorStatus(JCas jcas, int begin, int end) {
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
  //* Feature: receptorType

  /** getter for receptorType - gets 
   * @generated */
  public String getReceptorType() {
    if (ReceptorStatus_Type.featOkTst && ((ReceptorStatus_Type)jcasType).casFeat_receptorType == null)
      jcasType.jcas.throwFeatMissing("receptorType", "gov.va.maveric.uima.breastca.ReceptorStatus");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ReceptorStatus_Type)jcasType).casFeatCode_receptorType);}
    
  /** setter for receptorType - sets  
   * @generated */
  public void setReceptorType(String v) {
    if (ReceptorStatus_Type.featOkTst && ((ReceptorStatus_Type)jcasType).casFeat_receptorType == null)
      jcasType.jcas.throwFeatMissing("receptorType", "gov.va.maveric.uima.breastca.ReceptorStatus");
    jcasType.ll_cas.ll_setStringValue(addr, ((ReceptorStatus_Type)jcasType).casFeatCode_receptorType, v);}    
   
    
  //*--------------*
  //* Feature: receptorStatus

  /** getter for receptorStatus - gets 
   * @generated */
  public String getReceptorStatus() {
    if (ReceptorStatus_Type.featOkTst && ((ReceptorStatus_Type)jcasType).casFeat_receptorStatus == null)
      jcasType.jcas.throwFeatMissing("receptorStatus", "gov.va.maveric.uima.breastca.ReceptorStatus");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ReceptorStatus_Type)jcasType).casFeatCode_receptorStatus);}
    
  /** setter for receptorStatus - sets  
   * @generated */
  public void setReceptorStatus(String v) {
    if (ReceptorStatus_Type.featOkTst && ((ReceptorStatus_Type)jcasType).casFeat_receptorStatus == null)
      jcasType.jcas.throwFeatMissing("receptorStatus", "gov.va.maveric.uima.breastca.ReceptorStatus");
    jcasType.ll_cas.ll_setStringValue(addr, ((ReceptorStatus_Type)jcasType).casFeatCode_receptorStatus, v);}    
  }

    