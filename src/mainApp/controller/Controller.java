package mainApp.controller;

import mainApp.model.BusinessCard;
import mainApp.view.GUI;
import mainApp.model.Model;

import java.util.ArrayList;

public class Controller {
    private Model model;
    private GUI gui;

    public Controller(Model model){
        this.model = model;
        gui = new GUI(this);
    }

    public void newCard(String name, String surName, String telephone, String email) {
        model.createCard(name, surName, telephone, email);
    }

    public void removeCard(String email){
        model.deleteCard(email);
    }

    public void updateCards(ArrayList<String> nList, ArrayList<String> sList,
                            ArrayList<String> tList, ArrayList<String> eList) {
        model.updateCard(nList, sList, tList, eList);
    }

    public ArrayList<BusinessCard> getCards(){
        return model.getCards();
    }

    public void saveCards() {
        model.saveCards();
    }
}
