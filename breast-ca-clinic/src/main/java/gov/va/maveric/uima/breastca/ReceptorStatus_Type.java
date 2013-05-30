
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
public class ReceptorStatus_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (ReceptorStatus_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = ReceptorStatus_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new ReceptorStatus(addr, ReceptorStatus_Type.this);
  			   ReceptorStatus_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new ReceptorStatus(addr, ReceptorStatus_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = ReceptorStatus.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("gov.va.maveric.uima.breastca.ReceptorStatus");
 
  /** @generated */
  final Feature casFeat_receptorType;
  /** @generated */
  final int     casFeatCode_receptorType;
  /** @generated */ 
  public String getReceptorType(int addr) {
        if (featOkTst && casFeat_receptorType == null)
      jcas.throwFeatMissing("receptorType", "gov.va.maveric.uima.breastca.ReceptorStatus");
    return ll_cas.ll_getStringValue(addr, casFeatCode_receptorType);
  }
  /** @generated */    
  public void setReceptorType(int addr, String v) {
        if (featOkTst && casFeat_receptorType == null)
      jcas.throwFeatMissing("receptorType", "gov.va.maveric.uima.breastca.ReceptorStatus");
    ll_cas.ll_setStringValue(addr, casFeatCode_receptorType, v);}
    
  
 
  /** @generated */
  final Feature casFeat_receptorStatus;
  /** @generated */
  final int     casFeatCode_receptorStatus;
  /** @generated */ 
  public String getReceptorStatus(int addr) {
        if (featOkTst && casFeat_receptorStatus == null)
      jcas.throwFeatMissing("receptorStatus", "gov.va.maveric.uima.breastca.ReceptorStatus");
    return ll_cas.ll_getStringValue(addr, casFeatCode_receptorStatus);
  }
  /** @generated */    
  public void setReceptorStatus(int addr, String v) {
        if (featOkTst && casFeat_receptorStatus == null)
      jcas.throwFeatMissing("receptorStatus", "gov.va.maveric.uima.breastca.ReceptorStatus");
    ll_cas.ll_setStringValue(addr, casFeatCode_receptorStatus, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public ReceptorStatus_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_receptorType = jcas.getRequiredFeatureDE(casType, "receptorType", "uima.cas.String", featOkTst);
    casFeatCode_receptorType  = (null == casFeat_receptorType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_receptorType).getCode();

 
    casFeat_receptorStatus = jcas.getRequiredFeatureDE(casType, "receptorStatus", "uima.cas.String", featOkTst);
    casFeatCode_receptorStatus  = (null == casFeat_receptorStatus) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_receptorStatus).getCode();

  }
}



    