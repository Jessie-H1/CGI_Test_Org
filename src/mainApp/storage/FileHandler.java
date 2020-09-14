package mainApp.storage;

import mainApp.model.BusinessCard;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class FileHandler {
    private File file;
    private PrintWriter writer;

    // Sparar data till lokalfil
    public void saveFile(ArrayList<BusinessCard> cards){
        makeFile();

        try {
            writer = new PrintWriter(file);
            for(BusinessCard card : cards){
                writer.println(card.getName() + " " + card.getSurName() + " " +
                        card.getTelephone() + " " + card.getEmail());
            }
            writer.close();
        } catch (Exception e){
            System.err.println(e);
        }
    }

    // Läser data från lokal fil.
    public HashMap<String, ArrayList<String>> readFile(){
        HashMap<String, ArrayList<String>> sMap = new HashMap();
        ArrayList<String> nList = new ArrayList<>(), sList = new ArrayList<>(),
                tList = new ArrayList<>(), eList = new ArrayList<>();
        try {

            FileInputStream fileStream = new FileInputStream("Fields.txt");
            InputStreamReader inputReader = new InputStreamReader(fileStream);
            BufferedReader bufferedReader = new BufferedReader(inputReader);

            String line;
            while ((line = bufferedReader.readLine()) != null){
                System.out.println(line);
                String[] fileArr = line.split(" ");
                nList.add(fileArr[0]);
                sList.add(fileArr[1]);
                tList.add(fileArr[2]);
                eList.add(fileArr[3]);
            }
            sMap.put("name", nList);
            sMap.put("surName", sList);
            sMap.put("telephone", tList);
            sMap.put("email", eList);

        } catch (Exception e){
            System.err.println(e);
        }
        return sMap;
    }

    // Skapar fil
    private void makeFile(){
        try {
            file = new File("Fields.txt");
        } catch (Exception e){
            System.err.println(e);
        }
    }
}
