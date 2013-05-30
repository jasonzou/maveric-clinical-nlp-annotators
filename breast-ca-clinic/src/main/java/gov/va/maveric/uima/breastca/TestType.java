

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
public class TestType extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(TestType.class);
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
  protected TestType() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public TestType(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public TestType(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public TestType(JCas jcas, int begin, int end) {
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
  //* Feature: testType

  /** getter for testType - gets 
   * @generated */
  public String getTestType() {
    if (TestType_Type.featOkTst && ((TestType_Type)jcasType).casFeat_testType == null)
      jcasType.jcas.throwFeatMissing("testType", "gov.va.maveric.uima.breastca.TestType");
    return jcasType.ll_cas.ll_getStringValue(addr, ((TestType_Type)jcasType).casFeatCode_testType);}
    
  /** setter for testType - sets  
   * @generated */
  public void setTestType(String v) {
    if (TestType_Type.featOkTst && ((TestType_Type)jcasType).casFeat_testType == null)
      jcasType.jcas.throwFeatMissing("testType", "gov.va.maveric.uima.breastca.TestType");
    jcasType.ll_cas.ll_setStringValue(addr, ((TestType_Type)jcasType).casFeatCode_testType, v);}    
  }

    