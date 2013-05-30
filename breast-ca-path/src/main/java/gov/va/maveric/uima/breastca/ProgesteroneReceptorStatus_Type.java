
/* First created by JCasGen Fri Sep 07 07:16:52 EDT 2012 */
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
 * Updated by JCasGen Fri Sep 07 07:16:52 EDT 2012
 * @generated */
public class ProgesteroneReceptorStatus_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (ProgesteroneReceptorStatus_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = ProgesteroneReceptorStatus_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new ProgesteroneReceptorStatus(addr, ProgesteroneReceptorStatus_Type.this);
  			   ProgesteroneReceptorStatus_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new ProgesteroneReceptorStatus(addr, ProgesteroneReceptorStatus_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = ProgesteroneReceptorStatus.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("gov.va.maveric.uima.breastca.ProgesteroneReceptorStatus");
 
  /** @generated */
  final Feature casFeat_category;
  /** @generated */
  final int     casFeatCode_category;
  /** @generated */ 
  public String getCategory(int addr) {
        if (featOkTst && casFeat_category == null)
      jcas.throwFeatMissing("category", "gov.va.maveric.uima.breastca.ProgesteroneReceptorStatus");
    return ll_cas.ll_getStringValue(addr, casFeatCode_category);
  }
  /** @generated */    
  public void setCategory(int addr, String v) {
        if (featOkTst && casFeat_category == null)
      jcas.throwFeatMissing("category", "gov.va.maveric.uima.breastca.ProgesteroneReceptorStatus");
    ll_cas.ll_setStringValue(addr, casFeatCode_category, v);}
    
  
 
  /** @generated */
  final Feature casFeat_value;
  /** @generated */
  final int     casFeatCode_value;
  /** @generated */ 
  public String getValue(int addr) {
        if (featOkTst && casFeat_value == null)
      jcas.throwFeatMissing("value", "gov.va.maveric.uima.breastca.ProgesteroneReceptorStatus");
    return ll_cas.ll_getStringValue(addr, casFeatCode_value);
  }
  /** @generated */    
  public void setValue(int addr, String v) {
        if (featOkTst && casFeat_value == null)
      jcas.throwFeatMissing("value", "gov.va.maveric.uima.breastca.ProgesteroneReceptorStatus");
    ll_cas.ll_setStringValue(addr, casFeatCode_value, v);}
    
  
 
  /** @generated */
  final Feature casFeat_ruleID;
  /** @generated */
  final int     casFeatCode_ruleID;
  /** @generated */ 
  public String getRuleID(int addr) {
        if (featOkTst && casFeat_ruleID == null)
      jcas.throwFeatMissing("ruleID", "gov.va.maveric.uima.breastca.ProgesteroneReceptorStatus");
    return ll_cas.ll_getStringValue(addr, casFeatCode_ruleID);
  }
  /** @generated */    
  public void setRuleID(int addr, String v) {
        if (featOkTst && casFeat_ruleID == null)
      jcas.throwFeatMissing("ruleID", "gov.va.maveric.uima.breastca.ProgesteroneReceptorStatus");
    ll_cas.ll_setStringValue(addr, casFeatCode_ruleID, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public ProgesteroneReceptorStatus_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_category = jcas.getRequiredFeatureDE(casType, "category", "uima.cas.String", featOkTst);
    casFeatCode_category  = (null == casFeat_category) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_category).getCode();

 
    casFeat_value = jcas.getRequiredFeatureDE(casType, "value", "uima.cas.String", featOkTst);
    casFeatCode_value  = (null == casFeat_value) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_value).getCode();

 
    casFeat_ruleID = jcas.getRequiredFeatureDE(casType, "ruleID", "uima.cas.String", featOkTst);
    casFeatCode_ruleID  = (null == casFeat_ruleID) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_ruleID).getCode();

  }
}



    