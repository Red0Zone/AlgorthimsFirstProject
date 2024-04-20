
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class Main extends Application {
	private Stage stage = App.getMainStage();
	private boolean logoLock;

	public static void main(String[] args) {
//		String s1 = "ABCBDAB";
//		String s2 = "BDCABA";
//		LCS tt = new LCS(s1, s2);
//		System.out.println(tt.print_LCS());
//		int[][] c = tt.getDBtable();
//		char[][] b = tt.getArrows();

//		for (int i = 0; i < c.length; i++) {
//			for (int j = 0; j < c[i].length; j++) {
//				System.out.print(c[i][j] + " ");
//			}
//			System.out.println(" ");
//		}
//	
//		for (int i = 0; i < b.length; i++) {
//			for (int j = 0; j < b[i].length; j++) {
//				System.out.print(b[i][j] + " ");
//			}
//			System.out.println(" ");
//		}
		launch(args);

	}

	@Override
	public void start(Stage s) throws Exception {
		Label header = App.customLabel("<< LCS Led Simulator  >>", "black");
		header.setAlignment(Pos.TOP_CENTER);
		VBox mainPage = new VBox(20);
		mainPage.setAlignment(Pos.CENTER);
		mainPage.setStyle("-fx-background-image:url('file:cover4.jpg');-fx-background-size:cover");
		Button btMain = App.CustomButton("Start", 150, 50);
		btMain.setAlignment(Pos.CENTER);

		mainPage.getChildren().addAll(header, getLogo(), btMain);

		Scene mainScene = new Scene(mainPage, 600, 600);
		stage = App.getMainStage();
		stage.setResizable(false);
		stage.setScene(mainScene);
		stage.show();

		btMain.setOnAction(e -> {
			First_Screen fs = new First_Screen();
			Scene secondScene = new Scene(fs, 600, 600);
			stage.setScene(secondScene);
			stage.setResizable(true);
		});

	}

	private ImageView getLogo() {
		logoLock = false;

		ImageView image = new ImageView();
		image.setImage(new javafx.scene.image.Image("file:cover1.png"));
		image.setFitWidth(350);
		image.setFitHeight(350);

		DropShadow glow = new DropShadow();
		glow.setWidth(25);
		glow.setHeight(25);
		glow.setRadius(25);
		glow.setSpread(0.8);
		glow.setColor(Color.CYAN); // Neon color

		image.setOnMouseEntered(e -> {
			if (logoLock == false)
				image.setEffect(glow);
		});
		image.setOnMouseExited(e -> {
			if (logoLock == false)
				image.setEffect(null);
		});
		image.setOnMouseClicked(e -> {
			if (logoLock == false) {
				image.setEffect(glow);
				logoLock = true;
			} else {
				logoLock = false;
				image.setEffect(null);
			}
		});
		return image;
	}

}
