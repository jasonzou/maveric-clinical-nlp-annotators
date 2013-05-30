  1. Modify src/main/resources/desc/RegexAnnotator.xml to have full path of rules.xml
  
~~~~
<configurationParameterSettings>
  <nameValuePair>
    <name>rulesURL</name>
    <value>
      <string>c:/path/to/breast-ca-clinic/src/main/resources/desc/rules.xml</string>
    </value>
  </nameValuePair>
</configurationParameterSettings>
~~~~

  2. Run gov.va.maveric.uima.breastca.RunAndOutput with thre arguments:
     * c:/path/to/breast-ca-clinic/src/main/resources/desc/BreastCancerAnalysisEngine.xml
     * c:/path/to/folder/with/text/documents
     * c:/path/to/output/folder
     
