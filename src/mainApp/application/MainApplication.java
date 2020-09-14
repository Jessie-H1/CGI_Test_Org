package mainApp.application;

import mainApp.controller.Controller;
import mainApp.model.Model;

public class MainApplication {

    public MainApplication(){
        Controller controller = new Controller(new Model());
    }

    public static void main(String[] args){
        new MainApplication();
    }
}
