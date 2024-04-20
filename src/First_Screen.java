
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class First_Screen extends VBox {
	private TextArea tx;
	private int flag = 1;
	private TextField tx1;
	private TextField tx2;
	private boolean lock = false;
	private LCS lcs;
	private Stage stageTable = new Stage();
	private Stage stageAnimation = new Stage();

	public First_Screen() {
		HBox hb = new HBox(20);
		Button manualBT = App.CustomButton("Manual", 150, 50);
		Button fileBT = App.CustomButton("File", 150, 50);
		Button randomlBT = App.CustomButton("Random", 150, 50);
		Button run = App.CustomButton("Run Simulation", 150, 50);
		Button animation = App.CustomButton("Ui", 150, 50);
		Button table = App.CustomButton("DB Table", 150, 50);

		tx = new TextArea();
		tx.setEditable(false);
		tx.setPrefSize(400, 400);

		hb.setAlignment(Pos.CENTER);
		hb.getChildren().addAll(manualBT, fileBT, randomlBT);
		HBox hb2 = new HBox(50);
		hb2.getChildren().addAll(animation, table);
		hb2.setAlignment(Pos.CENTER);

		// A listener added to textProperty() that filters out non-numeric characters by
		// using this expression (\\d*). This ensures that only digits from 0-9 are
		// allowed in the textfield.
		// The replaceAll("[^\\d]", "") method removes any characters that are not
		// digits from the input.

		tx1 = new TextField();
		tx1.setPromptText("Enter number of leds");
		tx1.setStyle("-fx-prompt-text-fill:green");
		tx1.setAlignment(Pos.CENTER);
		tx1.setMaxSize(300, 30);

		tx1.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\d*")) {
				tx1.setText(newValue.replaceAll("[^\\d]", ""));
			}
		});

		tx2 = new TextField();
		tx2.setPromptText("Enter the order of leds");
		tx2.setStyle("-fx-prompt-text-fill:green");
		tx2.setAlignment(Pos.CENTER);
		tx2.setMaxSize(300, 30);

		tx2.textProperty().addListener((observable, oldValue, newValue) -> {

			if (!newValue.matches("^[\\d\\s]*$") && !newValue.matches(" ")) {

//				if (flag == 2){
//					App.alert("Data Input Error", "Your file contains data of mismatched data types.",
//							"Please ensure that all data in the file consists of numeric values arranged correctly.",
//							AlertType.WARNING);
//				tx2.setText("");
				// }

			}
		});

		VBox vb = new VBox(20);
		vb.setAlignment(Pos.CENTER);
		vb.getChildren().addAll(hb, tx1, tx2, run, tx, hb2);
		vb.setPadding(new Insets(20, 20, 20, 20));
		this.setStyle("-fx-background-image:url('file:cover4.jpg');-fx-background-size:cover");
		this.getChildren().add(vb);
		BorderPane.setAlignment(vb, Pos.CENTER);
		this.setPadding(new Insets(20, 20, 20, 20));

		manualBT.setOnAction(e -> {
			flag = 1;
			tx2.setVisible(true);
			tx1.setText("");
			tx2.setText("");
			tx1.setEditable(true);
			tx2.setEditable(true);

		});
		fileBT.setOnAction(e -> {
			flag = 2;
			tx2.setVisible(true);
			tx1.setEditable(false);
			tx2.setEditable(false);
			tx1.setText("");
			tx2.setText("");
			File_Chooser();
		});
		randomlBT.setOnAction(e -> {
			flag = 3;
			tx2.setVisible(false);
			tx1.setEditable(true);
			tx1.setText("");
			tx2.setText("");

		});

		run.setOnAction(e -> {
			if (dataChecker() != false) {
				textAreaFill();
				lock = true;
				stageTable.close();
			} else
				lock = false;
		});

		table.setOnAction(e -> {
			if (lock == true) {

				int a = 0;
				try {
					a = Integer.valueOf(tx1.getText());
				} catch (NumberFormatException e1) {
					// TODO: handle exception
				}
				if (a <= 3000) {
					stageTable.setScene(
							new Scene(new SecondScreen(lcs.getDBtable(), lcs.getArrows(), lcs.getX(), lcs.getY())));
					stageTable.show();
				} else
					App.alert("Sorry!!", "DBtable cant handle more than 3000 light", "", AlertType.WARNING);

			} else
				App.alert("Error!!", "Run the smulatiom first", getAccessibleHelp(), AlertType.WARNING);
		});

		animation.setOnAction(e -> {
			if (lock == true) {
				int a = Integer.valueOf(tx1.getText());
				if (a <= 1500) {
					stageAnimation.setScene(new Scene(new Theird_Screen(lcs), 1000, 800));
					stageAnimation.show();
				} else
					App.alert("Sorry!!", "Ui cant handle more than 1500 light", "", AlertType.WARNING);

			} else
				App.alert("Error!!", "Run the smulatiom first", getAccessibleHelp(), AlertType.WARNING);

		});

	}

	private boolean dataChecker() {
		String s1 = tx1.getText();
		String s2 = tx2.getText();
		if ((flag == 1 || flag == 2) && (tx1.getText().isBlank() || tx2.getText().isBlank())) {
			App.alert("Warning!!", "Dont leave it Empty",
					"Fill the first field with the number of leds and the second one with the order of leds!",
					AlertType.INFORMATION);
			return false;
		} else if (tx1.getText().isBlank()) {
			App.alert("Warning!!", "Dont leave it Empty", "Fill the first field with the number of leds!",
					AlertType.INFORMATION);
			return false;
		} else {
			int c = Integer.valueOf(s1);

			if (c > 4000) {
				App.alert("Sorry!!", "This app cant handle more than 4000 light", "", AlertType.WARNING);
				return false;
			}
			s2 = s2.trim();
			s2 = s2.replaceAll("\\s+", " ");
			String[] checkNumber = s2.split(" ");
			// String errorMassege = "";

			if ((flag == 1 || flag == 2) && (checkNumber.length != c)) {
				App.alert("Wrong input", "The number of LEDs does not match the order", "", AlertType.ERROR);
				return false;
			}
			// sort the array to make it faster to check duplicates; Quick sort takes nLogn
			Arrays.sort(checkNumber);
			int s = 0;

			// this loop for checking the data input if it is ok or not
			if ((flag == 1 || flag == 2)) {
				for (int i = 0; i < checkNumber.length - 1; i++) {
					s = Integer.valueOf(checkNumber[i]);

					// this if statment for checking the duplicate;
					if (checkNumber[i].equals(checkNumber[i + 1])) {
						App.alert("Wrong input", "The number [" + checkNumber[i] + "] is duplicated!!", "",
								AlertType.ERROR);
						return false;

					}
					// for checking the range of the number from 1 - c(the biggest order)
					else if (s <= 0 || s > c) {
						App.alert("Wrong input", "The number [" + checkNumber[i] + "] is out of range!!", "",
								AlertType.ERROR);
						return false;
					}

				}
				// unique case for checking the range(last index)
				s = Integer.valueOf(checkNumber[c - 1]);
				if (s <= 0 || s > c) {
					App.alert("Wrong input", "The number [" + checkNumber[c - 1] + "] is out of range!!", "",
							AlertType.ERROR);
					return false;
				}
			}

			return true;
		}

	}

	private void textAreaFill() {

		int num = Integer.valueOf(tx1.getText());
		String s1 = "", s2 = tx2.getText();
		for (int i = 0; i < num; i++)
			s1 += (i + 1) + " ";

		if (flag == 1 || flag == 2) {
			lcs = new LCS(s2, s1);
			String s = "The number of LEDs is : " + num + "\n\nthe order of LEDs is : ";
			s2 = s2.replaceAll(" ", ",");
			s += s2;
			String[] c = lcs.print_LCS().split(" ");
			s += "\n\nThe expected number of LEDs to light is : " + c.length + "\n\nthe expected LEDs to light are : ";
			s += lcs.print_LCS().replaceAll(" ", ",");

			tx.setText(s);

		} else {
			s2 = randomArrayArengment(num);
			lcs = new LCS(s2, s1);
			String s = "The number of LEDs is : " + num + "\n\nthe order of LEDs is : ";
			s2 = s2.replaceAll(" ", ",");
			s += s2;
			String[] c = lcs.print_LCS().split(" ");
			s += "\n\nThe expected number of LEDs to light is : " + c.length + "\n\nthe expected LEDs to light are : ";

			s += lcs.print_LCS().replaceAll(" ", ",");

			tx.setText(s);

		}
	}

	private String randomArrayArengment(int num) {
		ArrayList arr = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			arr.add(i + 1);
		}

		Collections.shuffle(arr);
		String s = "";
		for (int i = 0; i < arr.size(); i++) {
			s += arr.get(i) + " ";
		}
		return s;
	}

	private void File_Chooser() {
		Stage stage = new Stage();
		FileChooser fileChooser = new FileChooser();
		ExtensionFilter ef = new ExtensionFilter("Text Files(*.txt,*.csv)", "*.txt", "*.csv");
		fileChooser.getExtensionFilters().add(ef);
		File fileChoosed = fileChooser.showOpenDialog(stage);
		if (fileChoosed != null && fileChoosed.canRead()) {
			readFromFile(fileChoosed);
		} else
			App.alert("Warning!!", "No file choosed!!", "try again", AlertType.WARNING);
	}

	private void readFromFile(File file) {
		try (Scanner scan = new Scanner(file)) {
			String s1 = scan.nextLine().trim();
			String s2 = "";
			while (scan.hasNextLine()) {
				s2 += scan.nextLine().trim() + " ";
			}
//			System.out.println(s2);
			tx1.setText(s1);
			tx2.setText(s2);

		} catch (FileNotFoundException | NoSuchElementException e) {
			App.alert("Warning!!", "No lines or wrong data Arrangment !!", "check your file", AlertType.WARNING);
		}
	}

}
