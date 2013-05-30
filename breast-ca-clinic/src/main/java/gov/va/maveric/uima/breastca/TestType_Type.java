
/* First created by JCasGen Tue Oct 16 14:01:31 EDT 2012 */
package gov.va.maveric.uima.breastca;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Tue Oct 16 14:01:31 EDT 2012
 * @generated */
public class TestType_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (TestType_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = TestType_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new TestType(addr, TestType_Type.this);
  			   TestType_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new TestType(addr, TestType_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = TestType.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("gov.va.maveric.uima.breastca.TestType");
 
  /** @generated */
  final Feature casFeat_testType;
  /** @generated */
  final int     casFeatCode_testType;
  /** @generated */ 
  public String getTestType(int addr) {
        if (featOkTst && casFeat_testType == null)
      jcas.throwFeatMissing("testType", "gov.va.maveric.uima.breastca.TestType");
    return ll_cas.ll_getStringValue(addr, casFeatCode_testType);
  }
  /** @generated */    
  public void setTestType(int addr, String v) {
        if (featOkTst && casFeat_testType == null)
      jcas.throwFeatMissing("testType", "gov.va.maveric.uima.breastca.TestType");
    ll_cas.ll_setStringValue(addr, casFeatCode_testType, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public TestType_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_testType = jcas.getRequiredFeatureDE(casType, "testType", "uima.cas.String", featOkTst);
    casFeatCode_testType  = (null == casFeat_testType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_testType).getCode();

  }
}



    