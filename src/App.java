import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class App {
	private static Stage stage = new Stage();
	private static boolean Labellock;
	private static boolean buttonlock;

	public static Button CustomButton(String btName, int hight, int width) {
		Button customButton = new Button(btName);
		customButton.setPrefSize(hight, width);
		buttonlock = false;

		DropShadow glow = new DropShadow();
		glow.setWidth(25);
		glow.setHeight(25);
		glow.setRadius(25);
		glow.setSpread(0.4);
		glow.setColor(Color.CYAN); // Neon color

		customButton.setStyle("-fx-font-size: 18px; " + "-fx-text-fill:cyan; " + "-fx-background-color:#7f7f7f; "
				+ "-fx-padding: 10px; " + "-fx-background-radius: 20px; "
				+ "-fx-border-color: cyan;-fx-border-radius:20");

		customButton.setOnMouseEntered(e -> {
			if (buttonlock == false) {
				customButton.setStyle("-fx-font-size: 18px; " + "-fx-text-fill: black; "
						+ "-fx-background-color: cyan; " + "-fx-padding: 10px; " + "-fx-background-radius: 20px; "
						+ "-fx-border-color: cyan;-fx-border-radius:20");
				customButton.setEffect(glow);
			}

		});

		customButton.setOnMouseExited(e -> {
			if (buttonlock == false) {
				customButton.setStyle("-fx-font-size: 18px; " + "-fx-text-fill: cyan; "
						+ "-fx-background-color: #7f7f7f; " + "-fx-padding: 10px; " + "-fx-background-radius: 20px; "
						+ "-fx-border-color: cyan;-fx-border-radius:20");
				customButton.setEffect(null);
			}
		});
		customButton.setOnMouseClicked(e -> {
			if (e.getButton().equals(MouseButton.SECONDARY) && buttonlock == false) {
				buttonlock = true;
				customButton.setStyle("-fx-font-size: 18px; " + "-fx-text-fill: black; "
						+ "-fx-background-color: cyan; " + "-fx-padding: 10px; " + "-fx-background-radius: 20px; "
						+ "-fx-border-color: cyan;-fx-border-radius:20");
				customButton.setEffect(glow);

			} else if (e.getButton().equals(MouseButton.SECONDARY) && buttonlock == true) {
				buttonlock = false;
				customButton.setStyle("-fx-font-size: 18px; " + "-fx-text-fill: cyan; "
						+ "-fx-background-color: #7f7f7f; " + "-fx-padding: 10px; " + "-fx-background-radius: 20px; "
						+ "-fx-border-color: cyan;-fx-border-radius:20");
				customButton.setEffect(null);
			}

		});
		return customButton;

	}

	public static void alert(String title, String header, String contentText, AlertType alerttype) {
		Alert alert = new Alert(alerttype);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(contentText);
		alert.show();
	}

	public static Label customLabel(String text, String color) {

		Labellock = false;

		Label label = new Label(text);
		DropShadow glow = new DropShadow();
		glow.setWidth(25);
		glow.setHeight(25);
		glow.setRadius(25);
		glow.setSpread(0.4);
		glow.setColor(Color.CYAN); // Neon color
		label.setEffect(glow);

		label.setStyle("-fx-font-size: 30px; " + "-fx-text-fill:" + color + "; " + "-fx-padding: 10px; "
				+ "-fx-border-radius: 5px; " + "-fx-font-weight: bold;");
		label.setOnMouseEntered(e -> {
			if (Labellock == false)
				label.setStyle("-fx-font-size: 30px; " + "-fx-text-fill:" + "cyan" + "; " + "-fx-padding: 10px; "
						+ "-fx-border-radius: 5px; " + "-fx-font-weight: bold;");
		});
		label.setOnMouseExited(e -> {
			if (Labellock == false)
				label.setStyle("-fx-font-size: 30px; " + "-fx-text-fill:" + color + "; " + "-fx-padding: 10px; "
						+ "-fx-border-radius: 5px; " + "-fx-font-weight: bold;");
		});
		label.setOnMouseClicked(e -> {
			if (Labellock == false) {
				Labellock = true;
				label.setStyle("-fx-font-size: 30px; " + "-fx-text-fill:" + "cyan" + "; " + "-fx-padding: 10px; "
						+ "-fx-border-radius: 5px; " + "-fx-font-weight: bold;");
			} else {
				Labellock = false;
				label.setStyle("-fx-font-size: 30px; " + "-fx-text-fill:" + color + "; " + "-fx-padding: 10px; "
						+ "-fx-border-radius: 5px; " + "-fx-font-weight: bold;");
			}
		});

		return label;

	}

	public static Stage getMainStage() {
		return stage;
	}
}
