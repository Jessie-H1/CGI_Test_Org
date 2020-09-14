package mainApp.model;

import mainApp.storage.FileHandler;

import java.util.ArrayList;
import java.util.HashMap;

public class Model {
    private ArrayList<BusinessCard> cardList = new ArrayList<>();
    private FileHandler fileHandler = new FileHandler();

    // Skapar visitkort om data existerar i filen.
    public Model(){
        HashMap<String, ArrayList<String>> sMap = fileHandler.readFile();
        if (!sMap.isEmpty()){
            for (int i = 0; i < sMap.get("name").size(); i++){
                createCard(sMap.get("name").get(i), sMap.get("surName").get(i),
                        sMap.get("telephone").get(i), sMap.get("email").get(i));
            }
        }

    }

    public void createCard(String name, String surName, String telephone, String email){
        BusinessCard card = new BusinessCard(name, surName, telephone, email);
        cardList.add(card);
    }

    public ArrayList<BusinessCard> getCards(){
        return cardList;
    }

    public void updateCard(ArrayList<String> nList, ArrayList<String> sList,
                           ArrayList<String> tList, ArrayList<String> eList){

        for (int i = 0; i < cardList.size(); i++) {
            cardList.get(i).setName(nList.get(i));
            cardList.get(i).setSurName(sList.get(i));
            cardList.get(i).setTelephone(tList.get(i));
            cardList.get(i).setEmail(eList.get(i));
        }
    }

    public void deleteCard(String email){

        for (BusinessCard card : cardList) {
            if (card.getEmail().equals(email)) {
                cardList.remove(card);
                break;
            }
        }
    }

    public void saveCards() {

        fileHandler.saveFile(getCards());
    }
}
