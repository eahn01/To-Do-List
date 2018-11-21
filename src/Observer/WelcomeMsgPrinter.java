package Observer;

import Model.ReadWebPage;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class WelcomeMsgPrinter implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        try {
            ReadWebPage.WelcomeMsg();
        } catch (IOException e) {
            System.out.println("Exception caught?");
        }
    }
}
