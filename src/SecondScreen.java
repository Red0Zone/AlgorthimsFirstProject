
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class SecondScreen extends VBox {
	TextArea tx1 = new TextArea();
	TextArea tx2 = new TextArea();
	Label l1 = App.customLabel("Num", "red");
	Label l2 = App.customLabel("Arrow", "red");
	Label header = App.customLabel("DB Table", "red");
	VBox vb1 = new VBox(10);
	VBox vb2 = new VBox(10);
	HBox hb = new HBox(60);
	int[][] a;
	char[][] b;
	String[] x;
	String[] y;

	public SecondScreen(int[][] a, char[][] b, String[] x, String[] y) {

		this.a = a;
		this.b = b;
		this.x = x;
		this.y = y;

		tx1.setPrefSize(400, 400);
		tx1.setEditable(false);
		tx2.setPrefSize(400, 400);
		tx2.setEditable(false);
		l1.setAlignment(Pos.CENTER);
		l2.setAlignment(Pos.CENTER);
		vb1.setPadding(new Insets(20, 20, 20, 20));
		vb1.setAlignment(Pos.CENTER);
		vb2.setAlignment(Pos.CENTER);
		hb.setAlignment(Pos.CENTER);
		vb2.setPadding(new Insets(20, 20, 20, 20));
		hb.setPadding(new Insets(20, 20, 20, 20));
		this.setPadding(new Insets(20, 20, 20, 20));

		fillText();

		header.setAlignment(Pos.TOP_CENTER);

		vb1.getChildren().addAll(l1, tx1);
		vb2.getChildren().addAll(l2, tx2);
		hb.getChildren().addAll(vb1, vb2);
		this.getChildren().addAll(header, hb);
		this.setAlignment(Pos.CENTER);
		this.setStyle("-fx-background-image:url('file:cover4.jpg');-fx-background-size:cover");

	}

	private void fillText() {
		StringBuilder s1 = new StringBuilder();
		StringBuilder s2 = new StringBuilder();
		s1.append("\n\n     \n      ");
		s2.append("\n\n     \n      ");

		for (int i = 0; i < x.length; i++)
			
			s1.append("(" + x[i] + ") ");
		s1.append("\n    \n        ");
		for (int i = 0; i < a.length; i++) {
			if(i>0)
			s1.append("  (" + (i) + ")  ");
			for (int j = 0; j < a[i].length; j++) {
				s1.append(a[i][j] + "  ");
			}
			s1.append("\n");
		}
		tx1.setText(s1.toString());

		for (int i = 0; i < x.length; i++)
				s2.append("(" + x[i] + ") ");
		s2.append("\n    \n        ");
		for (int i = 0; i < b.length; i++) {
			if(i>0)
				s2.append("  (" + (i) + ")  ");
			for (int j = 0; j < b[i].length; j++) {
				s2.append(b[i][j] + "  ");
			}

			s2.append("\n");
		}
		tx2.setText(s2.toString());

	}
}
