/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aprioripreprocess;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author ACER
 */
public class AprioriPreProcess {

    /**
     * @param args the command line arguments
     */
    static List<String> dataList;
    
    public static List<String> getDataList() {
        dataList = Arrays.asList("citrus fruit", "tropical fruit", "whole milk", "pip fruit", "other vegetables", 
                "rolls/buns", "pot plants", "citrus fruit", "beef", "frankfurter", "chicken", "butter", "fruit/vegetable juice",
                "packaged fruit/vegetables", "chocolate", "specialty bar", "butter milk", "bottled water", "yogurt", "sausage", 
                "brown bread", "hamburger meat", "root vegetables", "pork", "pastry", "canned beer", "berries", "coffee", 
                "misc. beverages", "ham", "turkey", "curd cheese", "red/blush wine", "frozen potato products", "flour", 
                "sugar", "frozen meals", "herbs", "soda", "detergent", "grapes", "processed cheese", "fish", "sparkling wine",
                "newspapers", "curd", "pasta", "popcorn", "finished products", "beverages", "bottled beer", "dessert", 
                "dog food", "specialty chocolate", "condensed milk", "cleaner", "white wine", "meat", "ice cream", 
                "hard cheese", "cream cheese", "liquor", "pickled vegetables", "liquor (appetizer)", "UHT-milk", 
                "candy", "onions", "hair spray", "photo/film", "domestic eggs", "margarine", "shopping bags", "salt", 
                "oil", "whipped/sour cream", "frozen vegetables", "sliced cheese", "dish cleaner", "baking powder",
                "specialty cheese", "salty snack", "Instant food products", "pet care", "white bread", "female sanitary products",
                "cling film/bags", "soap", "frozen chicken", "house keeping products", "spread cheese", "decalcifier", "frozen dessert",
                "vinegar", "nuts/prunes", "potato products", "frozen fish", "hygiene articles", "artif. Sweetener", "light bulbs", 
                "canned vegetables", "chewing gum", "canned fish", "cookware", "semi-finished bread", "cat food", "bathroom cleaner",
                "Prosecco", "liver loaf", "zwieback", "canned fruit", "frozen fruits", "brandy", "baby cosmetics", "spices", "napkins", 
                "waffles", "sauces", "rum", "chocolate marshmallow", "long life bakery product", "bags", "sweet spreads", 
                "soups", "mustard", "specialty fat", "instant coffee", "snack products", "organic sausage", "soft cheese", 
                "mayonnaise", "dental care", "roll products", "kitchen towels", "flower soil/fertilizer", "cereals",
                "meat spreads", "dishes", "male cosmetics", "candles", "whisky", "tidbits", "cooking chocolate", "seasonal products",
                "liqueur", "abrasive cleaner", "syrup", "ketchup", "cream", "skin care", "rubbing alcohol", "nut snack", "cocoa drinks", 
                "softener", "organic products", "cake bar", "honey", "jam", "kitchen utensil", "flower (seeds)", "rice", "tea", 
                "salad dressing", "specialty vegetables", "ready soups", "toilet cleaner", "pudding powder");
        return dataList;
    }
    
    public static void main(String[] args) throws FileNotFoundException {

        getDataList();
        
       
 String inputFile = "C:\\Users\\ACER\\Documents\\NetBeansProjects\\AprioriPreProcess\\src\\groceries.csv"; //configuration file
  //      String outputFile = "C:\\Users\\ACER\\Documents\\NetBeansProjects\\AprioriPreProcess\\src\\output.txt";//output file 

        //Column name in preprocess output file
//        List<String> dataList = Arrays.asList("citrus fruit", "semi-finished bread", "margarine", "ready soups", "tropical fruit", "yogurt", 
//                                              "coffee", "whole milk", "pip fruit", "cream cheese", "meat spreads", "other vegetables", 
//                                              "condensed milk", "long life bakery product", "butter", "rice", "abrasive cleaner", "rolls/buns", 
//                                              "UHT-milk", "bottled beer", "liquor (appetizer)", "pot plants", "curd", "butter", "snack products", 
//                                              "brown bread", , "dishes", "beef", "frankfurter", "chicken", "sugar", "fruit/vegetable juice",
//                                              "packaged fruit/vegetables", "specialty bar", "newspapers", "butter milk", "pastry", "processed cheese", 
//                                              "detergent", "frozen dessert", "root vegetables", "sweet spreads", "salty snack", "waffles", "candy", 
//                                              "bathroom cleaner", "canned beer", "sausage", "soda", "hygiene articles", "napkins", "hamburger meat",
//                                              "spices", "berries", "whipped/sour cream", "artif. sweetener", "pasta", "onions", "roll products", 
//                                              "cat food", "shopping bags", "white wine");
        String itemSep = ",";
        String line;
//        List <String> zaroorat = new ArrayList<>();

        try {
            FileInputStream file_in = new FileInputStream(inputFile);
            BufferedReader data_in = new BufferedReader(new InputStreamReader(file_in));

            // 32, 166
            while ((line = data_in.readLine()) != null) {
               // int flag = 0;
                //int size = 0;
                String zaroorat[] = new String[dataList.size()];
                for (int i = 0; i < dataList.size(); i++) {
                    zaroorat[i] = "0";
                }
                String values[] = line.split(itemSep);
                for (String value : values) {
                    for (int i = 0; i < dataList.size(); i++) {
                        if (dataList.get(i).equalsIgnoreCase(value)) {
                            zaroorat[i] = "1";
                        //    size++;
                          //  flag = 1;
                            break;
                        }
                    }
//                    if(flag != 1) {
//                        zaroorat.add("0");
//                    }
                }
                for (int i = 0; i < dataList.size(); i++) {
                    System.out.print(zaroorat[i] + " ");
                }
                System.out.println();
            }
        } //if there is an error, print the message
        catch (IOException e) {
            System.out.println(e);
        }
//        try {
//            fw = new FileWriter(outputFile);
//            file_out = new BufferedWriter(fw);
//            for(String z : zaroorat) {
//                file_out.write(z + " ");
//            }
//            file_out.close();
//        }
//        catch(IOException e)
//        {
//            System.out.println(e);
//        }
    }

}
