package net.mizobo.namegen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class GeneratorController {

	private EnumSex selected = EnumSex.MALE;

	@FXML
	private Button genButton;

	@FXML
	private Button genderButton;

	@FXML
	private Button clearButton;

	@FXML
	private TextArea nameArea;

	@FXML
	private CheckBox checkBocGendered;

	@FXML
	private Button copyButton;

	@FXML
	private Slider numSlider;


    @FXML
    void generate(ActionEvent event) {
    	clear(event);
    	int numberToGen = numSlider.valueProperty().intValue();
    	for(int i = numberToGen; i > 0; i--){
    		this.nameArea.appendText(Main.generator.generateSexedName(checkBocGendered.selectedProperty().get() ? selected : EnumSex.NO_PREFERENCE) + "\n");
    	}
    	if(!event.isConsumed()) event.consume();
    }

    @FXML
    void clear(ActionEvent event) {
    	event.consume();
    	this.nameArea.clear();
    }

    @FXML
    void copy(ActionEvent event) {
    	event.consume();
    	Clipboard cb = Clipboard.getSystemClipboard();
    	ClipboardContent content = new ClipboardContent();
    	content.putString(this.nameArea.getText());
    	cb.setContent(content);
    }

	@FXML
	void genderedBoxAction(ActionEvent event) {
    	event.consume();
	}

	@FXML
	void genderButtonPress(ActionEvent event) {
		if(genderButton.getText().equalsIgnoreCase("male")){
			genderButton.setText("Female");
			selected = EnumSex.FEMALE;
		}else if(genderButton.getText().equalsIgnoreCase("female")){
			genderButton.setText("Male");
			selected = EnumSex.MALE;
		}
	}

	@FXML
    void initialize() {
    	assert Main.generator != null : "Generator was not instantiated. This program will not function.";
        assert genButton != null : "fx:id=\"genButton\" was not injected: check your FXML file 'generator.fxml'.";
		assert genderButton != null : "fx:id=\"genderButton\" was not injected: check your FXML file 'generator.fxml'.";
        assert clearButton != null : "fx:id=\"clearButton\" was not injected: check your FXML file 'generator.fxml'.";
        assert nameArea != null : "fx:id=\"nameArea\" was not injected: check your FXML file 'generator.fxml'.";
		assert checkBocGendered != null : "fx:id=\"checkBocGendered\" was not injected: check your FXML file 'generator.fxml'.";
        assert copyButton != null : "fx:id=\"copyButton\" was not injected: check your FXML file 'generator.fxml'.";
        assert numSlider != null : "fx:id=\"numSlider\" was not injected: check your FXML file 'generator.fxml'.";

        genderButton.disableProperty().bind(checkBocGendered.selectedProperty().not());
    }
}
