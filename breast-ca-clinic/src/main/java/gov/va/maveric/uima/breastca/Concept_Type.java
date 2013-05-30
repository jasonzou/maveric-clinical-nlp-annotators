
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
public class Concept_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Concept_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Concept_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Concept(addr, Concept_Type.this);
  			   Concept_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Concept(addr, Concept_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Concept.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("gov.va.maveric.uima.breastca.Concept");
 
  /** @generated */
  final Feature casFeat_features;
  /** @generated */
  final int     casFeatCode_features;
  /** @generated */ 
  public int getFeatures(int addr) {
        if (featOkTst && casFeat_features == null)
      jcas.throwFeatMissing("features", "gov.va.maveric.uima.breastca.Concept");
    return ll_cas.ll_getRefValue(addr, casFeatCode_features);
  }
  /** @generated */    
  public void setFeatures(int addr, int v) {
        if (featOkTst && casFeat_features == null)
      jcas.throwFeatMissing("features", "gov.va.maveric.uima.breastca.Concept");
    ll_cas.ll_setRefValue(addr, casFeatCode_features, v);}
    
   /** @generated */
  public int getFeatures(int addr, int i) {
        if (featOkTst && casFeat_features == null)
      jcas.throwFeatMissing("features", "gov.va.maveric.uima.breastca.Concept");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_features), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_features), i);
	return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_features), i);
  }
   
  /** @generated */ 
  public void setFeatures(int addr, int i, int v) {
        if (featOkTst && casFeat_features == null)
      jcas.throwFeatMissing("features", "gov.va.maveric.uima.breastca.Concept");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_features), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_features), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_features), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Concept_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_features = jcas.getRequiredFeatureDE(casType, "features", "uima.cas.FSArray", featOkTst);
    casFeatCode_features  = (null == casFeat_features) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_features).getCode();

  }
}



    