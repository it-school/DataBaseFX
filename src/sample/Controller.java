package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.ArrayList;

public class Controller {
    @FXML
    public TextField FieldText;

    public void on_ButtonClick()
    {
       if(Main.db.Connect())
       {
           Main.st.setTitle("Connected!!!");
       }  
       else
       {
           Main.st.setTitle("ERROR!!!");
       }
    }

    public void Search()
    {
        String searchText = FieldText.getText();
        try {
            ArrayList<String> list = Main.db.Search(searchText);
            if(!list.isEmpty()) {
                for (String word : list) {
                    System.out.println(word);
                }
                FieldText.setText(searchText + list.get(0).charAt(searchText.length()));
                FieldText.positionCaret(searchText.length() + 1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
