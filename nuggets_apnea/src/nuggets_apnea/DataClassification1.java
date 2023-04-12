/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nuggets_apnea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.SparseInstance;
import weka.filters.unsupervised.attribute.StringToWordVector;

/**
 *
 * @author seabirds
 */
public class DataClassification1 
{
    String  inputdata[];
    String  inputClasses[];
    String testdata[];
    
    
    String classString="";

    Attribute  classAttribute  = null;
     Attribute  textAttribute   = null;
     FastVector attributeInfo   = null;
     Instances  instances       = null;
     Classifier classifier      = null;
     Instances  filteredData    = null;
     Evaluation evaluation      = null;
     Set        modelWords      = null;
     String     delimitersStringToWordVector = "\\s.,:'\\\"()?!";
     
     ArrayList resultCls;//=new ArrayList();
        
    DataClassification1(String g1[],String g2[],String g3[])
    {
        inputdata=g1;
        inputClasses=g2;
        testdata=g3;
        resultCls=new ArrayList();
    }
    public ArrayList Testclassifier()
    {
        try
        {
            System.out.println("Data Size "+inputdata.length+" : "+inputClasses.length+" : "+testdata.length);
            //String thisClassString = "weka.classifiers.lazy.IBk";
            String thisClassString ="weka.classifiers.trees.J48";
            HashSet classSet = new HashSet(Arrays.asList(inputClasses));
            classSet.add("?");
            String[] classValues = (String[])classSet.toArray(new String[0]);

            FastVector classAttributeVector = new FastVector();
            for (int i = 0; i < classValues.length; i++)
            {
                classAttributeVector.addElement(classValues[i]);
            }
            Attribute thisClassAttribute = new Attribute("class", classAttributeVector);

            //
                // create text attribute
             //
            FastVector inputTextVector = null;  // null -> String type
            Attribute thisTextAttribute = new Attribute("text", inputTextVector);

            /*System.out.println("------ inputdata "+inputdata.length);
            for(int i=0;i<inputdata.length;i++)
                System.out.println(inputdata[i]);*/



            for (int i = 0; i < inputdata.length; i++)
            {
                thisTextAttribute.addStringValue(inputdata[i]);
            }

            for (int i = 0; i <testdata.length; i++)
            {
                thisTextAttribute.addStringValue(testdata[i]);
            }

            //
            // create the attribute information
            //
            FastVector thisAttributeInfo = new FastVector(2);
            thisAttributeInfo.addElement(thisTextAttribute);
            thisAttributeInfo.addElement(thisClassAttribute);
            classString=thisClassString;
            attributeInfo=thisAttributeInfo;
            textAttribute=thisTextAttribute;
            classAttribute=thisClassAttribute;
            


           // System.out.println("DATA SET:\n");
            System.out.println(classify(thisClassString));

          //  System.out.println("NEW CASES:\n");
            System.out.println(classifyNewCases(testdata));
            StringBuffer sb=classifyNewCases(testdata);
            String str=new String(sb);
          //  System.out.println("final str "+str);
           // String sd=str.substring(str.indexOf("predicted:"),str.length() );
            String sd[]=str.split("predicted:");//.substring(str.indexOf("predicted:"),str.length() );
            
            //String ss=sd[1].substring(sd[1].indexOf("'")+1,sd[1].indexOf("("));
            //System.out.println("class "+ss);

            for(int i=1;i<sd.length;i++)
            {
                String ss=sd[i].substring(sd[i].indexOf("'")+1,sd[i].indexOf("("));
              //  System.out.println("class "+ss);
                resultCls.add(ss.trim());
            }
            
            
           /* JOptionPane.showMessageDialog(new JFrame(),"The given Document is classified to  "+ss);
            File ff=new File(cf.jTextField1.getText());
            String se=ff.getName();
            cf.jLabel3.setText("The Document "+ se +" is classified to  "+ss);*/
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("----------------- resultClass "+resultCls);
        return resultCls;
    } //end of classifier


    public StringBuffer classify()
    {

        if (classString == null || "".equals(classString))
        {
            return(new StringBuffer());
        }

        return classify(classString);

    } // end classify()


    public StringBuffer classify(String classString)
    {

        this.classString = classString;

        StringBuffer result = new StringBuffer();

        // creates an empty instances set
        instances = new Instances("data set", attributeInfo, 100);

        // set which attribute is the class attribute
        instances.setClass(classAttribute);


        try {

            instances = populateInstances(inputdata, inputClasses, instances, classAttribute, textAttribute);
            result.append("DATA SET:\n" + instances + "\n");

            // make filtered SparseData
            filteredData = filterText(instances);

            // create Set of modelWords
            modelWords = new HashSet();
            Enumeration enumx = filteredData.enumerateAttributes();
            while (enumx.hasMoreElements()) {
                Attribute att = (Attribute)enumx.nextElement();
                String attName = att.name().toLowerCase();
                modelWords.add(attName);

            }

            //
            // Classify and evaluate data
            //
            classifier = Classifier.forName(classString,null);

            classifier.buildClassifier(filteredData);
            evaluation = new Evaluation(filteredData);
            evaluation.evaluateModel(classifier, filteredData);




            result.append(printClassifierAndEvaluation(classifier, evaluation) + "\n");

            // check instances
            int startIx = 0;
            result.append(checkCases(filteredData, classifier, classAttribute, inputdata, "not test", startIx)  + "\n");


        } catch (Exception e) {
            e.printStackTrace();
            result.append("\nException (sorry!):\n" + e.toString());
        }

        return result;

    } // end classify

    public StringBuffer classifyNewCases(String[] tests) {

        StringBuffer result = new StringBuffer();

        //
        // first copy the old instances,
        // then add the test words
        //

        Instances testCases = new Instances(instances);
        testCases.setClass(classAttribute);


        //
        // since some classifiers cannot handle unknown words (i.e. words not
        // a 'model word'), we filter these unknowns out.
        // Maybe this should be done only for those classifiers?
        // E.g. Naive Bayes have prior probabilities which may be used?
        //
        // Here we split each test line and check each word
        //
        //System.out.println("----- new case "+tests.length);
        String[] testsWithModelWords = new String[tests.length];
        int gotModelWords = 0; // how many words will we use?

        for (int i = 0; i < tests.length; i++)
        {
            // the test string to use
            StringBuffer acceptedWordsThisLine = new StringBuffer();

            // split each line in the test array
            String[] splittedText = tests[i].split("["+delimitersStringToWordVector+"]");
            // check if word is a model word
            for (int wordIx = 0; wordIx < splittedText.length; wordIx++)
            {
                String sWord = splittedText[wordIx];
                if (modelWords.contains((String)sWord))
                {
                    gotModelWords++;
                    acceptedWordsThisLine.append(sWord + " ");
                }
            }
            testsWithModelWords[i] = acceptedWordsThisLine.toString();
        }


        // should we do do something if there is no modelWords?
        if (gotModelWords == 0) {
            result.append("\nWarning!\nThe text to classify didn't contain a single\nword from the modelled words. This makes it hard for the classifier to\ndo something usefull.\nThe result may be weird.\n\n");
        }

        try {

            // add the ? class for all test cases
            String[] tmpClassValues = new String[tests.length];
            for (int i = 0; i < tmpClassValues.length; i++) {
                tmpClassValues[i] = "?";
            }

            testCases = populateInstances(testsWithModelWords, tmpClassValues, testCases, classAttribute, textAttribute);
          // System.out.println("---- testcase "+testCases.numInstances());
            

            // result.append("TEST CASES before filter:\n" + testCases + "\n");

            Instances filteredTests = filterText(testCases);

            // result.append("TEST CASES:\n" + filteredTests + "\n");

            //
            // check
            //
            int startIx = instances.numInstances();
           // System.out.println("--- classify new case"+startIx+" : "+filteredTests.numInstances());
            result.append(checkCases(filteredTests, classifier, classAttribute, tests, "newcase", startIx) + "\n");

        } catch (Exception e) {
            e.printStackTrace();
            result.append("\nException (sorry!):\n" + e.toString());
        }

        return result;

    } //  end classifyNewCases


    //
    //  from empty instances populate with text and class arrays
    //
    public static Instances populateInstances(String[] theseInputTexts, String[] theseInputClasses, Instances theseInstances, Attribute classAttribute, Attribute textAttribute) {

        for (int i = 0; i < theseInputTexts.length; i++) {
            Instance inst = new Instance(2);
            inst.setValue(textAttribute,theseInputTexts[i]);
            if (theseInputClasses != null && theseInputClasses.length > 0) {
                inst.setValue(classAttribute, theseInputClasses[i]);
            }
            theseInstances.add(inst);
        }
//System.out.println("---- populate instacnec "+theseInstances.numInstances());
        return theseInstances;


    } // populateInstances


    //
    // check instances (full set or just test cases)
    //
    public static StringBuffer checkCases(Instances theseInstances, Classifier thisClassifier, Attribute thisClassAttribute, String[] texts, String testType, int startIx) {

        StringBuffer result = new StringBuffer();


        try {

            result.append("\nCHECKING ALL THE INSTANCES:\n");

            Enumeration enumClasses = thisClassAttribute.enumerateValues();
            result.append("Class values (in order): ");
            while (enumClasses.hasMoreElements()) {
                String classStr = (String)enumClasses.nextElement();
                result.append("'" + classStr + "'  ");
            }
            result.append("\n");

            // startIx is a fix for handling text cases
           // System.out.println("------------check case "+startIx+" : "+theseInstances.numInstances());
            for (int i = startIx; i < theseInstances.numInstances(); i++) {

                SparseInstance sparseInst = new SparseInstance(theseInstances.instance(i));
                sparseInst.setDataset(theseInstances);

                result.append("\nTesting: '" + texts[i-startIx] + "'\n");
                // result.append("SparseInst: " + sparseInst + "\n");

                double correctValue = (double)sparseInst.classValue();
                double predictedValue = thisClassifier.classifyInstance(sparseInst);

                String pString = thisClassAttribute.value((int)predictedValue) + " (" + predictedValue + ")";
                result.append("predicted: '" + pString);
                //String sg1=pString.substring(pString.indexOf("'")+1,pString.indexOf("("));
                
                  
                //resultCls.add(sg1);
                // print comparison if not new case
                if (!"newcase".equals(testType)) {
                    String correctString = thisClassAttribute.value((int)correctValue) + " (" + correctValue + ")";
                    String testString = ((predictedValue == correctValue) ? "OK!" : "NOT OK!") + "!";
                    result.append("' real class: '" + correctString +  "' ==> " +  testString);
                }
                result.append("\n");

                /*
                if (thisClassifier instanceof Distribution) {
                double[] dist = ((Distribution)thisClassifier).distributionForInstance(sparseInst);

                    // weight the levels into a spamValue
                    double weightedValue = 0; // experimental
                    result.append("probability distribution:\n");
                    NumberFormat nf = NumberFormat.getInstance();
                    nf.setMaximumFractionDigits(3);
                    for (int j = 0; j < dist.length; j++) {
                        result.append(nf.format(dist[j]) + " ");
                        weightedValue += 10*(j+1)*dist[j];
                        if (j < dist.length -1) {
                            result.append(",  ");
                        }
                    }
                    result.append("\nWeighted Value: " + nf.format(weightedValue) + "\n");
                }
                */

                result.append("\n");
                // result.append(thisClassifier.dumpDistribution());
                // result.append("\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
            result.append("\nException (sorry!):\n" + e.toString());
        }

        return result;

    } // end checkCases


    //
    // take instances in normal format (strings) and convert to Sparse format
    //
    public static Instances filterText(Instances theseInstances) {

        //System.out.println("---filertext "+theseInstances.numInstances());
        StringToWordVector filter = null;
        // default values according to Java Doc:
        int wordsToKeep = 1000;

        Instances filtered = null;

        try {

            filter = new StringToWordVector(wordsToKeep);
            // we ignore this for now...
            // filter.setDelimiters(delimitersStringToWordVector);
            filter.setOutputWordCounts(true);
            filter.setSelectedRange("1");

            filter.setInputFormat(theseInstances);

            filtered = weka.filters.Filter.useFilter(theseInstances,filter);
            // System.out.println("filtered:\n" + filtered);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return filtered;

    } // end filterText


    //
    // information about classifier and evaluation
    //
    public static StringBuffer printClassifierAndEvaluation(Classifier thisClassifier, Evaluation thisEvaluation) {

        StringBuffer result = new StringBuffer();

        try {
            result.append("\n\nINFORMATION ABOUT THE CLASSIFIER AND EVALUATION:\n");
            result.append("\nclassifier.toString():\n" + thisClassifier.toString() + "\n");
            result.append("\nevaluation.toSummaryString(title, false):\n" + thisEvaluation.toSummaryString("Summary",false)  + "\n");
            result.append("\nevaluation.toMatrixString():\n" + thisEvaluation.toMatrixString()  + "\n");
            result.append("\nevaluation.toClassDetailsString():\n" + thisEvaluation.toClassDetailsString("Details")  + "\n");
            result.append("\nevaluation.toCumulativeMarginDistribution:\n" + thisEvaluation.toCumulativeMarginDistributionString()  + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            result.append("\nException (sorry!):\n" + e.toString());
        }

        return result;

    } // end printClassifierAndEvaluation



    //
    // setter for the classifier _string_
    //
    public void setClassifierString(String classString) {
        this.classString = classString;
    }
    

}
