package Eternity.GUI.EquationManagement.EquationCell;

import Eternity.Logic.Equation.EternityEquation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class EquationCell extends ListCell<EternityEquation> {
    @FXML private Label equationName;
    @FXML private Label equation;

    public EquationCell(){
        loadFXML();
    }

    /**
     * Creates the cell format from the FXML
     */
    private void loadFXML(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("equation_cell.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * What happens when an item from the list view is selected
     * @param item the item that is selected
     * @param empty if the selection is empty
     */
    @Override
    protected void updateItem(EternityEquation item, boolean empty){
        super.updateItem(item, empty);

        if(empty){
            setText(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }
        else{
            equationName.setText(item.getEquationName());
            equation.setText(item.getDisplayEquation());
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }
}
