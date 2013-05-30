
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
public class Grade_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Grade_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Grade_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Grade(addr, Grade_Type.this);
  			   Grade_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Grade(addr, Grade_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Grade.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("gov.va.maveric.uima.breastca.Grade");
 
  /** @generated */
  final Feature casFeat_grade;
  /** @generated */
  final int     casFeatCode_grade;
  /** @generated */ 
  public String getGrade(int addr) {
        if (featOkTst && casFeat_grade == null)
      jcas.throwFeatMissing("grade", "gov.va.maveric.uima.breastca.Grade");
    return ll_cas.ll_getStringValue(addr, casFeatCode_grade);
  }
  /** @generated */    
  public void setGrade(int addr, String v) {
        if (featOkTst && casFeat_grade == null)
      jcas.throwFeatMissing("grade", "gov.va.maveric.uima.breastca.Grade");
    ll_cas.ll_setStringValue(addr, casFeatCode_grade, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Grade_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_grade = jcas.getRequiredFeatureDE(casType, "grade", "uima.cas.String", featOkTst);
    casFeatCode_grade  = (null == casFeat_grade) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_grade).getCode();

  }
}



    