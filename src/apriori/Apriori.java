

package apriori;
import java.io.*;
import java.util.*;
import aprioripreprocess.AprioriPreProcess;

public class Apriori {

    public static void main(String[] args) {
        AprioriCalculation ap = new AprioriCalculation();

        ap.aprioriProcess();
    }
}

class AprioriCalculation
{
    Vector<String> candidates=new Vector<String>(); //the current candidates
    String configFile="C:\\Users\\ACER\\Documents\\NetBeansProjects\\Apriori\\src\\apriori\\config.txt"; //configuration file
    String transaFile="C:\\Users\\ACER\\Documents\\NetBeansProjects\\Apriori\\src\\apriori\\transa.txt"; //transaction file
    String outputFile="C:\\Users\\ACER\\Documents\\NetBeansProjects\\Apriori\\src\\apriori\\apriori-output.txt";//output file
    int numItems;
    int numTransactions; 
    double minSup; 
    String oneVal[]; 
    String itemSep = " "; 
 
    public void aprioriProcess()
    {
      
        int itemsetNumber=0; 
        //get config
        getConfig();

        System.out.println("Apriori algorithm has started.\n");

     

      
        do
        {
        
            itemsetNumber++;

            
            generateCandidates(itemsetNumber);

          
           
           calculateFrequentItemsets(itemsetNumber);
           
            if(candidates.size()!=0)
            {
                System.out.println("Frequent " + itemsetNumber + "-itemsets");
                System.out.println(candidates);
            }
      
        }while(candidates.size()>1);


    }


    public static String getInput()
    {
        String input="";
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

       
        try
        {
            input = reader.readLine();
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
        return input;
    }

  
    private void getConfig()
    {
        FileWriter fw;
        BufferedWriter file_out;

        String input="";
        
        System.out.println("Default Configuration: ");
        System.out.println("\tRegular transaction file with '" + itemSep + "' item separator.");
        System.out.println("\tConfig File: " + configFile);
        System.out.println("\tTransa File: " + transaFile);
        System.out.println("\tOutput File: " + outputFile);
        System.out.println("\nPress 'C' to change the item separator, configuration file and transaction files");
        System.out.print("or any other key to continue.  ");
        input=getInput();

        if(input.compareToIgnoreCase("c")==0)
        {
            System.out.print("Enter new transaction filename (return for '"+transaFile+"'): ");
            input=getInput();
            if(input.compareToIgnoreCase("")!=0)
                transaFile=input;

            System.out.print("Enter new configuration filename (return for '"+configFile+"'): ");
            input=getInput();
            if(input.compareToIgnoreCase("")!=0)
                configFile=input;

            System.out.print("Enter new output filename (return for '"+outputFile+"'): ");
            input=getInput();
            if(input.compareToIgnoreCase("")!=0)
                outputFile=input;

            System.out.println("Filenames changed");

            System.out.print("Enter the separating character(s) for items (return for '"+itemSep+"'): ");
            input=getInput();
            if(input.compareToIgnoreCase("")!=0)
                itemSep=input;


        }

        try
        {
             FileInputStream file_in = new FileInputStream(configFile);
             BufferedReader data_in = new BufferedReader(new InputStreamReader(file_in));
             //number of items
             numItems=Integer.parseInt(data_in.readLine());

             //number of transactions
             numTransactions=Integer.parseInt(data_in.readLine());

             //minsup
             minSup=(Double.parseDouble(data_in.readLine()));

             //output config info to the user
             System.out.print("\nInput configuration: "+numItems+" items, "+numTransactions+" transactions, ");
             System.out.println("minsup = "+minSup+"%");
             System.out.println();
             minSup/=100.0;

            oneVal = new String[numItems];
          
          
                for(int i=0; i<oneVal.length; i++)
                    oneVal[i]="1";

             //output file
            fw= new FileWriter(outputFile);
            file_out = new BufferedWriter(fw);
            
            file_out.write(numTransactions + "\n");
            file_out.write(numItems + "\n******\n");
            file_out.close();
        }
       
        catch(IOException e)
        {
            System.out.println(e);
        }
    }

   
    private void generateCandidates(int n)
    {
        Vector<String> tempCandidates = new Vector<String>(); 
        String str1, str2; 
        StringTokenizer st1, st2; 

      
        if(n==1)
        {
            for(int i=1; i<=numItems; i++)
            {
                tempCandidates.add(Integer.toString(i));
               // System.out.println(" temporary "+ tempCandidates);
            }
            
        }
        
    
        else if(n==2) 
        {
           
            
            for(int i=0; i<candidates.size(); i++)
            {
                st1 = new StringTokenizer(candidates.get(i));
                str1 = st1.nextToken();
                for(int j=i+1; j<candidates.size(); j++)
                {
                    st2 = new StringTokenizer(candidates.elementAt(j));
                    str2 = st2.nextToken();
                    tempCandidates.add(str1 + " " + str2);
                }
            }
        }
        else
        {
            
            for(int i=0; i<candidates.size(); i++)
            {
                
                for(int j=i+1; j<candidates.size(); j++)
                {
                    
                    str1 = new String();
                    str2 = new String();
                    //create the tokenizers
                    st1 = new StringTokenizer(candidates.get(i));
                    st2 = new StringTokenizer(candidates.get(j));

                    //string of the first n-2 tokens of the strings
                    for(int s=0; s<n-2; s++)
                    {
                        str1 = str1 + " " + st1.nextToken();
                        str2 = str2 + " " + st2.nextToken();
                    }

                    //same n-2 tokens, add them together
                    if(str2.compareToIgnoreCase(str1)==0)
                        tempCandidates.add((str1 + " " + st1.nextToken() + " " + st2.nextToken()).trim());
                }
            }
        }
        //clear the old candidates
        candidates.clear();
        //set the new ones
        candidates = new Vector<String>(tempCandidates);
        
        tempCandidates.clear();
    }


    private void calculateFrequentItemsets(int n)
    {
        Vector<String> frequentCandidates = new Vector<String>(); 
        Vector<String> frequentDataSet = new Vector<String>();
        
        // Getting the DataList from the pre-processing class
        List<String> dataList = AprioriPreProcess.getDataList();
        
        FileInputStream file_in; 
        BufferedReader data_in; 
        FileWriter fw;
        BufferedWriter file_out;

        StringTokenizer st, stFile, stData; 
        boolean match; 
        boolean trans[] = new boolean[numItems]; 
        int count[] = new int[candidates.size()];
        //int count2[]=new int[candidates.size()];
        //System.out.println("Candidates size" + candidates.size());
        try
        {
                
                fw= new FileWriter(outputFile, true);
                file_out = new BufferedWriter(fw);
                //load the transaction file
                file_in = new FileInputStream(transaFile);
                data_in = new BufferedReader(new InputStreamReader(file_in));
            
                //for each transaction
           
                for(int i=0; i<numTransactions; i++)        //9835 times
                {
          
                    stFile = new StringTokenizer(data_in.readLine(), itemSep); 
                    
                    for(int j=0; j<numItems; j++)
                    {
                        trans[j]=(stFile.nextToken().compareToIgnoreCase(oneVal[j])==0);    //if it is not a 0, assign the value to true
                        
                    }

                    //check each candidate
                   
                    for(int c=0; c<candidates.size(); c++)
                    {
                        match = false; 
                                                
                     
                        st = new StringTokenizer(candidates.get(c));
                        
                      
                     
                      
                        while(st.hasMoreTokens())
                        {
                            match = (trans[Integer.valueOf(st.nextToken())-1]);
                            
                            
                            if(!match) 
                                break;
                        }
                        if(match) 
                            count[c]++;  
                      
                    }

                }
                
               
                for(int i=0; i<candidates.size(); i++)
                {
                    
                    if((count[i]/(double)numTransactions)>=minSup)
                    {
                        stData = new StringTokenizer(candidates.get(i), itemSep);
                        while(stData.hasMoreTokens())
                        {
                            frequentDataSet.add(dataList.get(Integer.parseInt(stData.nextToken())-1));
                        }
                        frequentCandidates.add(candidates.get(i));
                      
                       
                        file_out.write(frequentDataSet + "\t ,\t" + count[i]/(double)numTransactions + "\n");
                        
                    }
                    frequentDataSet.clear();
                }
                file_out.write("-\n");
                file_out.close();
        }
    
        catch(IOException e)
        {
            System.out.println(e);
        }
        
        candidates.clear();
     
        candidates = new Vector<String>(frequentCandidates);
        frequentCandidates.clear();
    }
}
