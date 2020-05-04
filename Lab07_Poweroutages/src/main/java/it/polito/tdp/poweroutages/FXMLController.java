/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;
import it.polito.tdp.poweroutages.model.Blackout;
import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;


public class FXMLController {
	Model model;
		
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<Nerc> boxNerc;

    @FXML
    private TextField txtYears;

    @FXML
    private TextField txtHours;

    @FXML
    private Button btnWCA;

    @FXML
    private TextArea txtResult;

    @FXML
    void doAnalysis(ActionEvent event) {
    	txtResult.clear();
    	int anni=Integer.parseInt(this.txtYears.getText());
    	int ore=Integer.parseInt(this.txtHours.getText());
    	Nerc nerc=this.boxNerc.getValue();
    	if(model.Soluzione(nerc, anni, ore)==null) {
    		this.txtResult.setText("Nessun valore trovato");
    	}
    	List<Blackout>lista=new ArrayList<>(model.Soluzione(nerc, anni, ore));
    	this.txtResult.appendText("Numero massimo persone: "+model.numParziale(lista)+"\n");
    	this.txtResult.appendText("Numero totale ore: "+model.ore(lista)+"\n");

    	for(Blackout b:lista)
    	this.txtResult.appendText(b.toString()+"\n");
    }

    @FXML
    void initialize() {
        assert boxNerc != null : "fx:id=\"boxNerc\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtYears != null : "fx:id=\"txtYears\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtHours != null : "fx:id=\"txtHours\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnWCA != null : "fx:id=\"btnWCA\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        
    }

	public void setModel(Model model) {
		this.model=model;
		List<Nerc> n=model.getNercList();
		ObservableList<Nerc> lista=FXCollections.observableArrayList(n);
		this.boxNerc.setItems(lista);
	}
}
