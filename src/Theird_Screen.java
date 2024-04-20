import java.util.ArrayList;
import java.util.Arrays;

import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

public class Theird_Screen extends VBox {
	private LCS lcs;
	private String[] orders;
	private FlowPane fp;
	private ScrollPane sp;
	private ArrayList<String> lighted = new ArrayList<String>();
	private ArrayList lights = new ArrayList();
	private ArrayList powers = new ArrayList();

	public Theird_Screen(LCS lcs) {

		this.lcs = lcs;
		String[] s = lcs.print_LCS().split(" ");
		for (int i = 0; i < s.length; i++) {
			lighted.add(s[i]);
		}

		this.orders = lcs.getX();

		sp = new ScrollPane();
		sp.setMinHeight(400);

		Label label = App.customLabel("<< The lights UI >>", "black");
		label.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(20, 20, 20, 20));

		this.getChildren().addAll(label, sp);
		this.setPadding(new Insets(30, 30, 30, 30));
		this.setAlignment(Pos.CENTER);
		this.setStyle("-fx-background-image:url('file:cover4.jpg');-fx-background-size:cover");
		this.setSpacing(30);
		fillPane();

	}

	private DropShadow effect() {
		DropShadow glow = new DropShadow();
		glow.setWidth(25);
		glow.setHeight(25);
		glow.setRadius(25);
		glow.setSpread(0.8);
		glow.setColor(Color.GOLD); // Neon color
		return glow;
	}

	private void fillPane() {

		int a = -1;
		Image image = new Image("file:icon2.png");
		Image image2 = new Image("file:icon1.png");

		Pane root = new Pane();

		int height = 400;
		int width = 0;
		for (int i = 0; i < orders.length; i++) {
			a = lighted.indexOf(orders[i]);

			ImageView imageView = new ImageView(image);
			imageView.setFitHeight(100);
			imageView.setFitWidth(100);

			if (a != -1)
				imageView.setEffect(effect());
			else
				imageView.setEffect(null);

			Label l1 = new Label(orders[i]);
			l1.setAlignment(Pos.CENTER);
			l1.setStyle("-fx-text-fill:cyan;-fx-font-size:25px");
			VBox vb1 = new VBox(5, l1, imageView);
			vb1.setAlignment(Pos.CENTER);
			lights.add(vb1);
			vb1.setLayoutX(width);

			if (a != -1)
				vb1.setId(orders[i] + " true");
			else
				vb1.setId(orders[i] + "");

			ImageView iv2 = new ImageView(image2);
			iv2.setFitHeight(100);
			iv2.setFitWidth(100);
			Label l2 = new Label(i + 1 + "");
			l2.setAlignment(Pos.CENTER);
			l2.setStyle("-fx-text-fill:cyan;-fx-font-size:25px");
			VBox vb2 = new VBox(5, iv2, l2);
			vb2.setAlignment(Pos.CENTER);
			vb2.setId(i + 1 + "");
			vb2.setLayoutX(width);
			vb2.setLayoutY(height);
			powers.add(vb2);

			root.getChildren().addAll(vb1, vb2);

			width += 100;
		}

		root.setPadding(new Insets(20, 20, 20, 20));
		root.setStyle("-fx-background-image:url('file:cover4.jpg');-fx-background-size:cover");

		int index = 0;
		for (int i = 0; i < orders.length; i++) {
			Node node = (Node) lights.get(i);
			String[] s = node.getId().split(" ");

			if (s.length == 2) {
				Bounds n1 = node.localToParent(getBoundsInLocal());

				int a1 = Integer.parseInt(s[0]);

				Node node2 = (Node) powers.get(a1 - 1);
				Bounds t1 = node2.localToParent(getBoundsInLocal());

				Line line = new Line();
				line.setStartX(n1.getCenterX() + 48);
				line.setStartY(n1.getCenterY() + 130);
				line.setEndX(t1.getCenterX() + 48);
				line.setEndY(t1.getCenterY() + 25);
				line.setOpacity(0.8);
				line.setStyle("-fx-stroke-width: 3px; -fx-stroke: cyan;");
				DropShadow glow = new DropShadow();
				glow.setWidth(15);
				glow.setHeight(15);
				glow.setRadius(15);
				glow.setSpread(0.73);
				glow.setColor(Color.CYAN); // Neon color
				line.setEffect(glow);
				line.toFront();
				root.getChildren().add(line);

			}

		}

		sp.setContent(root);

	}

//	private Polygon createLeftArrowShape(double size) {
//		Polygon arrow = new Polygon();
//		arrow.getPoints().addAll(0.0, 0.0, size, size / 2.0, 0.0, size);
//		arrow.setFill(javafx.scene.paint.Color.CYAN);
//		arrow.setOnMouseEntered(e -> {
//			arrow.setEffect(effect());
//		});
//		arrow.setOnMouseExited(e -> {
//			arrow.setEffect(null);
//
//		});
//		return arrow;
//	}
//
//	private Polygon createRightArrowShape(double size) {
//		Polygon arrow = new Polygon();
//		arrow.getPoints().addAll(size, 0.0, 0.0, size / 2.0, size, size);
//		arrow.setFill(javafx.scene.paint.Color.CYAN);
//		arrow.setOnMouseEntered(e -> {
//			arrow.setEffect(effect());
//		});
//		arrow.setOnMouseExited(e -> {
//			arrow.setEffect(null);
//
//		});
//		return arrow;
//	}
}
