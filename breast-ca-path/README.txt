  1. Modify src/main/resources/gov/va/maveric/uima/breastca/BreastCancerAnnotator.xml to have full path of rules.xml
  
~~~~
<configurationParameterSettings>
  <nameValuePair>
    <name>rulesURL</name>
    <value>
      <string>c:/path/to/breast-ca-path/src/main/resources/gov/va/maveric/uima/breastca/rules.xml</string>
    </value>
  </nameValuePair>
</configurationParameterSettings>
~~~~

  2. Run gov.va.maveric.uima.breastca.RunAndOutput with thre arguments:
     * c:/path/to/breast-ca-path/src/main/resources/gov/va/maveric/uima/breastca/BreastCancerAnnotator.xml
     * c:/path/to/folder/with/text/documents
     * c:/path/to/output/folder
     
