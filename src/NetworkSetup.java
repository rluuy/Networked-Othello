
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * 
 * @author Ryan Luu and Denny Ho
 * 
 * NetWorkSetup pops open a UI box that asks the user for what information they need 
 * in order to establish a Server-Client Connection game of Reversi. 
 * 
 * The Four things we need are: 
 * 1. Do they want to be a server or client 
 * 2. Are they a Human or Computer Player 
 * 3. What is the Server name 
 * 4. What is the Port Number 
 *
 */
public class NetworkSetup extends Stage {

	private boolean isServer;
	private boolean isHuman;
	private String server;
	private String port;

	public NetworkSetup() {
		makeSetupBox();
	}

	/**
	 * makeSetupBox is the JavaFX box that accepts information about the user to fill 
	 * out the above marked fields needed to play a network-game of Reversi. 
	 */
	private void makeSetupBox() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Network Setup");
		window.setHeight(250);
		window.setWidth(500);

		VBox root = new VBox();
		root.setSpacing(20);
		root.setPadding(new Insets(15, 20, 10, 10));

		// First Row Options (Choose Server or Client)
		HBox firstRow = new HBox();
		firstRow.setSpacing(10);
		ToggleGroup tg1 = new ToggleGroup();
		Label createLabel = new Label("Create: ");
		RadioButton serverButton = new RadioButton("Server");
		RadioButton clientButton = new RadioButton("Client");
		serverButton.setToggleGroup(tg1);
		clientButton.setToggleGroup(tg1);
		firstRow.getChildren().addAll(createLabel, serverButton, clientButton);

		// Second Row Options (Choose Human or Computer)
		HBox secondRow = new HBox();
		secondRow.setSpacing(10);
		ToggleGroup tg2 = new ToggleGroup();
		Label playAsLabel = new Label("Play as: ");
		RadioButton human = new RadioButton("Human");
		RadioButton computer = new RadioButton("Computer");
		human.setToggleGroup(tg2);
		computer.setToggleGroup(tg2);
		secondRow.getChildren().addAll(playAsLabel, human, computer);

		// Third Row Options (User Input Server and Port)
		HBox thirdRow = new HBox();
		Label serverLabel = new Label("Server");
		Label portLabel = new Label("Port");
		TextField serverTextField = new TextField("localhost");
		TextField portTextField = new TextField("4000");
		thirdRow.setSpacing(10);
		thirdRow.getChildren().addAll(serverLabel, serverTextField, portLabel, portTextField);
		thirdRow.setAlignment(Pos.BOTTOM_LEFT);

		// Fourth Row Options (Okay and Close)
		HBox fourthRow = new HBox();
		fourthRow.setSpacing(10);
		Button okButton = new Button("Ok");
		okButton.setOnAction(s -> {
			RadioButton selectedRow1 = (RadioButton) tg1.getSelectedToggle();
			RadioButton selectedRow2 = (RadioButton) tg2.getSelectedToggle();
			if (selectedRow1.getText().equals("Server")) { // Sets Values for First Row
				this.isServer = true;
			} else {
				this.isServer = false; // Client is Selected
			}
			if (selectedRow2.getText().equals("Human")) { // Sets Values for Second Row
				this.isHuman = true;
			} else {
				this.isHuman = false; // Computer is Selected
			}
			if (serverTextField.getText().equals("localhost"))
				this.server = "127.0.0.1";
			else
				this.server = serverTextField.getText();
			this.port = portTextField.getText();
			window.close();

		});
		Button closeButton = new Button("Cancel");
		closeButton.setOnAction(e -> window.close());
		fourthRow.getChildren().addAll(okButton, closeButton);

		root.getChildren().addAll(firstRow, secondRow, thirdRow, fourthRow);
		Scene scene = new Scene(root);
		window.setScene(scene);
		window.showAndWait();
	}

	/**
	 * @return: True if server. False if Client
	 */
	public boolean getServerOrClient() {
		return isServer;
	}

	/**
	 * @return: True if Human. False if Computer
	 */
	public boolean getHumanOrComputer() {
		return isHuman;
	}

	/**
	 * @return: String Representation of Port
	 */
	public int getPort() {
		if (port != null) {
			return Integer.parseInt(port);
		} else {
			return -900;
		}
	}

	/**
	 * @return String Representation of Server
	 */
	public String getServer() {
		return server;
	}

}
