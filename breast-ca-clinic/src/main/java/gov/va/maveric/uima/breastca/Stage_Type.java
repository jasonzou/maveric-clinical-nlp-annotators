
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
public class Stage_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Stage_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Stage_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Stage(addr, Stage_Type.this);
  			   Stage_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Stage(addr, Stage_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Stage.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("gov.va.maveric.uima.breastca.Stage");
 
  /** @generated */
  final Feature casFeat_valueMain;
  /** @generated */
  final int     casFeatCode_valueMain;
  /** @generated */ 
  public String getValueMain(int addr) {
        if (featOkTst && casFeat_valueMain == null)
      jcas.throwFeatMissing("valueMain", "gov.va.maveric.uima.breastca.Stage");
    return ll_cas.ll_getStringValue(addr, casFeatCode_valueMain);
  }
  /** @generated */    
  public void setValueMain(int addr, String v) {
        if (featOkTst && casFeat_valueMain == null)
      jcas.throwFeatMissing("valueMain", "gov.va.maveric.uima.breastca.Stage");
    ll_cas.ll_setStringValue(addr, casFeatCode_valueMain, v);}
    
  
 
  /** @generated */
  final Feature casFeat_valueModifier;
  /** @generated */
  final int     casFeatCode_valueModifier;
  /** @generated */ 
  public String getValueModifier(int addr) {
        if (featOkTst && casFeat_valueModifier == null)
      jcas.throwFeatMissing("valueModifier", "gov.va.maveric.uima.breastca.Stage");
    return ll_cas.ll_getStringValue(addr, casFeatCode_valueModifier);
  }
  /** @generated */    
  public void setValueModifier(int addr, String v) {
        if (featOkTst && casFeat_valueModifier == null)
      jcas.throwFeatMissing("valueModifier", "gov.va.maveric.uima.breastca.Stage");
    ll_cas.ll_setStringValue(addr, casFeatCode_valueModifier, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Stage_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_valueMain = jcas.getRequiredFeatureDE(casType, "valueMain", "uima.cas.String", featOkTst);
    casFeatCode_valueMain  = (null == casFeat_valueMain) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_valueMain).getCode();

 
    casFeat_valueModifier = jcas.getRequiredFeatureDE(casType, "valueModifier", "uima.cas.String", featOkTst);
    casFeatCode_valueModifier  = (null == casFeat_valueModifier) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_valueModifier).getCode();

  }
}



    