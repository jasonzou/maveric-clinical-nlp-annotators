
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
public class TNMDescriptor_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (TNMDescriptor_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = TNMDescriptor_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new TNMDescriptor(addr, TNMDescriptor_Type.this);
  			   TNMDescriptor_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new TNMDescriptor(addr, TNMDescriptor_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = TNMDescriptor.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("gov.va.maveric.uima.breastca.TNMDescriptor");
 
  /** @generated */
  final Feature casFeat_t;
  /** @generated */
  final int     casFeatCode_t;
  /** @generated */ 
  public String getT(int addr) {
        if (featOkTst && casFeat_t == null)
      jcas.throwFeatMissing("t", "gov.va.maveric.uima.breastca.TNMDescriptor");
    return ll_cas.ll_getStringValue(addr, casFeatCode_t);
  }
  /** @generated */    
  public void setT(int addr, String v) {
        if (featOkTst && casFeat_t == null)
      jcas.throwFeatMissing("t", "gov.va.maveric.uima.breastca.TNMDescriptor");
    ll_cas.ll_setStringValue(addr, casFeatCode_t, v);}
    
  
 
  /** @generated */
  final Feature casFeat_tSub;
  /** @generated */
  final int     casFeatCode_tSub;
  /** @generated */ 
  public String getTSub(int addr) {
        if (featOkTst && casFeat_tSub == null)
      jcas.throwFeatMissing("tSub", "gov.va.maveric.uima.breastca.TNMDescriptor");
    return ll_cas.ll_getStringValue(addr, casFeatCode_tSub);
  }
  /** @generated */    
  public void setTSub(int addr, String v) {
        if (featOkTst && casFeat_tSub == null)
      jcas.throwFeatMissing("tSub", "gov.va.maveric.uima.breastca.TNMDescriptor");
    ll_cas.ll_setStringValue(addr, casFeatCode_tSub, v);}
    
  
 
  /** @generated */
  final Feature casFeat_n;
  /** @generated */
  final int     casFeatCode_n;
  /** @generated */ 
  public String getN(int addr) {
        if (featOkTst && casFeat_n == null)
      jcas.throwFeatMissing("n", "gov.va.maveric.uima.breastca.TNMDescriptor");
    return ll_cas.ll_getStringValue(addr, casFeatCode_n);
  }
  /** @generated */    
  public void setN(int addr, String v) {
        if (featOkTst && casFeat_n == null)
      jcas.throwFeatMissing("n", "gov.va.maveric.uima.breastca.TNMDescriptor");
    ll_cas.ll_setStringValue(addr, casFeatCode_n, v);}
    
  
 
  /** @generated */
  final Feature casFeat_nSub;
  /** @generated */
  final int     casFeatCode_nSub;
  /** @generated */ 
  public String getNSub(int addr) {
        if (featOkTst && casFeat_nSub == null)
      jcas.throwFeatMissing("nSub", "gov.va.maveric.uima.breastca.TNMDescriptor");
    return ll_cas.ll_getStringValue(addr, casFeatCode_nSub);
  }
  /** @generated */    
  public void setNSub(int addr, String v) {
        if (featOkTst && casFeat_nSub == null)
      jcas.throwFeatMissing("nSub", "gov.va.maveric.uima.breastca.TNMDescriptor");
    ll_cas.ll_setStringValue(addr, casFeatCode_nSub, v);}
    
  
 
  /** @generated */
  final Feature casFeat_m;
  /** @generated */
  final int     casFeatCode_m;
  /** @generated */ 
  public String getM(int addr) {
        if (featOkTst && casFeat_m == null)
      jcas.throwFeatMissing("m", "gov.va.maveric.uima.breastca.TNMDescriptor");
    return ll_cas.ll_getStringValue(addr, casFeatCode_m);
  }
  /** @generated */    
  public void setM(int addr, String v) {
        if (featOkTst && casFeat_m == null)
      jcas.throwFeatMissing("m", "gov.va.maveric.uima.breastca.TNMDescriptor");
    ll_cas.ll_setStringValue(addr, casFeatCode_m, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public TNMDescriptor_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_t = jcas.getRequiredFeatureDE(casType, "t", "uima.cas.String", featOkTst);
    casFeatCode_t  = (null == casFeat_t) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_t).getCode();

 
    casFeat_tSub = jcas.getRequiredFeatureDE(casType, "tSub", "uima.cas.String", featOkTst);
    casFeatCode_tSub  = (null == casFeat_tSub) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_tSub).getCode();

 
    casFeat_n = jcas.getRequiredFeatureDE(casType, "n", "uima.cas.String", featOkTst);
    casFeatCode_n  = (null == casFeat_n) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_n).getCode();

 
    casFeat_nSub = jcas.getRequiredFeatureDE(casType, "nSub", "uima.cas.String", featOkTst);
    casFeatCode_nSub  = (null == casFeat_nSub) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_nSub).getCode();

 
    casFeat_m = jcas.getRequiredFeatureDE(casType, "m", "uima.cas.String", featOkTst);
    casFeatCode_m  = (null == casFeat_m) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_m).getCode();

  }
}



    