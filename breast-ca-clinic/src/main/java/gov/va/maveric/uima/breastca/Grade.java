

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
public class Grade extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Grade.class);
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
  protected Grade() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Grade(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Grade(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Grade(JCas jcas, int begin, int end) {
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
  //* Feature: grade

  /** getter for grade - gets 
   * @generated */
  public String getGrade() {
    if (Grade_Type.featOkTst && ((Grade_Type)jcasType).casFeat_grade == null)
      jcasType.jcas.throwFeatMissing("grade", "gov.va.maveric.uima.breastca.Grade");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Grade_Type)jcasType).casFeatCode_grade);}
    
  /** setter for grade - sets  
   * @generated */
  public void setGrade(String v) {
    if (Grade_Type.featOkTst && ((Grade_Type)jcasType).casFeat_grade == null)
      jcasType.jcas.throwFeatMissing("grade", "gov.va.maveric.uima.breastca.Grade");
    jcasType.ll_cas.ll_setStringValue(addr, ((Grade_Type)jcasType).casFeatCode_grade, v);}    
  }

    