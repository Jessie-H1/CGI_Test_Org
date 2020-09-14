package mainApp.view;

import mainApp.controller.Controller;
import mainApp.model.BusinessCard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI extends JFrame{
    private ActionListener createListener, updateListener, deleteListener, saveListener;

    private JButton updateB = new JButton("Update Cards"), saveB = new JButton("Save Cards"),
            createB = new JButton("Create Card"), deleteB = new JButton("Delete Card");

    private JPanel mainPanel = new JPanel(new GridLayout(0, 1)),
            buttonPanel = new JPanel(new GridLayout(0, 3));

    private ArrayList<JPanel> cardPanList = new ArrayList<>();

    private ArrayList<JTextField> nameFieldList = new ArrayList<>(),
            surNameFieldList = new ArrayList<>(), telFieldList = new ArrayList<>(),
            emailFieldList = new ArrayList<>();

    private ArrayList<JButton> removeBList = new ArrayList<>();


    private Controller controller;

    public GUI(Controller controller){
        this.controller = controller;

        setTitle("CGI Test - Business Card");
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 250));

        createView(controller.getCards());
        createListener();
    }

    // Skapar huvudpanelen med tillhörande element, knappar etc.
    public void createView(ArrayList<BusinessCard> cards){
        nameFieldList.clear();
        surNameFieldList.clear();
        telFieldList.clear();
        emailFieldList.clear();
        removeBList.clear();

        for (int i = 0; i < cards.size(); i++) {
            createPanel();
            createFields(cards.get(i), i);
        }

        buttonPanel.add(createB);
        buttonPanel.add(updateB);
        buttonPanel.add(saveB);

        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.add(buttonPanel);
        add(mainPanel);

        pack();
        validate();
        mainPanel.validate();
    }

    private void createPanel(){
        JPanel panel = new JPanel(new GridLayout(2, 5));
        cardPanList.add(panel);
    }

    // Skapar varje fält per visitkort.
    private void createFields(BusinessCard card, int currentCard){
        JPanel dataPan = new JPanel(new GridLayout(0, 5)),
                headPan = new JPanel(new GridLayout(0, 4));

        headPan.add(new JLabel("First Name:"));
        headPan.add(new JLabel("Surname:"));
        headPan.add(new JLabel("Telephone:"));
        headPan.add(new JLabel("Email:"));

        nameFieldList.add(new JTextField(card.getName()));
        surNameFieldList.add(new JTextField(card.getSurName()));
        telFieldList.add(new JTextField(card.getTelephone()));
        emailFieldList.add(new JTextField(card.getEmail()));

        dataPan.add(nameFieldList.get(nameFieldList.size() - 1));
        dataPan.add(surNameFieldList.get(surNameFieldList.size() - 1));
        dataPan.add(telFieldList.get(telFieldList.size() - 1));
        dataPan.add(emailFieldList.get(emailFieldList.size() - 1));

        JButton removeB = new JButton("Remove Card");
        String email = emailFieldList.get(currentCard).getText();
        removeB.setName(email);
        removeB.addActionListener(e -> {
            System.out.println(email);
            controller.removeCard(email);

            mainPanel.removeAll();
            getContentPane().removeAll();
            createView(controller.getCards());
        });
        removeBList.add(removeB);
        dataPan.add(removeB);


        cardPanList.get(cardPanList.size() - 1).add(headPan);
        cardPanList.get(cardPanList.size() - 1).add(dataPan);

        mainPanel.add(cardPanList.get(cardPanList.size() - 1));
    }

    // Skapar frame vid skapandet av nya visitkort.
    private void createCardView(){
        JFrame tempFrame = new JFrame();
        tempFrame.setTitle("Create Business Card");
        tempFrame.setSize(1000, 600);

        JPanel tempPan = new JPanel(new GridLayout(9, 0));

        JLabel nameLab = new JLabel("Enter first name below: ");
        JLabel surNameLab = new JLabel("Enter surname below: ");
        JLabel telephoneLab = new JLabel("Enter telephone number below: ");
        JLabel emailLab = new JLabel("Enter email below: ");

        JTextField entName = new JTextField("");
        JTextField entSurName = new JTextField("");
        JTextField entTelephone = new JTextField("");
        JTextField entEmail = new JTextField("");
        JButton saveCard = new JButton("Save Card");

        tempPan.add(nameLab);
        tempPan.add(entName);

        tempPan.add(surNameLab);
        tempPan.add(entSurName);

        tempPan.add(telephoneLab);
        tempPan.add(entTelephone);

        tempPan.add(emailLab);
        tempPan.add(entEmail);

        tempPan.add(saveCard);

        tempFrame.add(tempPan);
        tempFrame.setVisible(true);
        tempFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        saveCard.addActionListener(e -> {
            cardPanList = new ArrayList<>();

            controller.newCard(entName.getText(), entSurName.getText(),
                    entTelephone.getText(), entEmail.getText());

            tempFrame.setVisible(false);
            tempFrame.dispose();

            mainPanel.removeAll();
            getContentPane().removeAll();
            createView(controller.getCards());
        });
    }

    // Lyssnare som hanterar events.
    public void createListener(){
        saveListener = e -> {
          controller.saveCards();
        };
        saveB.addActionListener(saveListener);

        createListener = e -> {
            createCardView();
        };
        createB.addActionListener(createListener);

        deleteListener = e -> controller.removeCard(null);
        deleteB.addActionListener(deleteListener);

        updateListener = e -> {
            ArrayList<String> nList = new ArrayList<>();
            ArrayList<String> sList = new ArrayList<>();
            ArrayList<String> tList = new ArrayList<>();
            ArrayList<String> eList = new ArrayList<>();

            System.out.println(nameFieldList.size());
            for (int i = 0; i < nameFieldList.size(); i++){
                nList.add(nameFieldList.get(i).getText());
                sList.add(surNameFieldList.get(i).getText());
                tList.add(telFieldList.get(i).getText());
                eList.add(emailFieldList.get(i).getText());
            }

            controller.updateCards(nList, sList, tList, eList);
        };
        updateB.addActionListener(updateListener);
    }
}
