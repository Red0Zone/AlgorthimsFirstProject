 import javafx.scene.control.Alert.AlertType;

public class LCS {
	public String[] getX() {
		return x;
	}

	public String[] getY() {
		return y;
	}

	private int c[][];
	private char b[][];
	private String[] x;
	private String[] y;
	private int size;

	public LCS(String s1, String s2) {

		x = s1.split(" ");
		y = s2.split(" ");

		int m = x.length;
		int n = y.length;

		c = new int[m + 1][n + 1];
		b = new char[m + 1][n + 1];

		for (int i = 0; i <= m; i++) {
			c[i][0] = 0;
			b[i][0] = '"';
		}

		for (int i = 0; i <= n; i++) {
			c[0][i] = 0;
			b[0][i] = '"';

		}

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (x[i - 1].equals(y[j - 1])) {
					c[i][j] = c[i - 1][j - 1] + 1;
					b[i][j] = 'D';

				} else if (c[i][j - 1] > c[i - 1][j]) {
					c[i][j] = c[i][j - 1];
					b[i][j] = 'L';
				} else {
					c[i][j] = c[i - 1][j];
					b[i][j] = 'U';
				}

			}
		}
//		for (int i = 0; i <= m; i++) {
//			for (int j = 0; j <= n; j++) {
//				System.out.print(c[i][j] + " ");
//			}
//			System.out.println("");
//		}
//		for (int i = 0; i <= m; i++) {
//			for (int j = 0; j <= n; j++) {
//				System.out.print(b[i][j] + " ");
//			}
//			System.out.println("");
//		}

	}

	public char[][] getArrows() {
		return b;
	}

	public int[][] getDBtable() {
		return c;
	}

	public String print_LCS() {
		try {
			return print_LCS(b, x, x.length, y.length);
		} catch (StackOverflowError e) {
			App.alert("Stack over flow", "The program cant handle this amount of lights", "", AlertType.WARNING);
		}
		return "cant calculate !!!";
	}

	private String print_LCS(char[][] b, String[] x, int i, int j) throws StackOverflowError {
//		System.out.println(b[i][j]);
		if (i == 0 || j == 0)
			return "";
		else if (b[i][j] == 'D') {
			return print_LCS(b, x, i - 1, j - 1) + x[i - 1] + " ";

		} else if (b[i][j] == 'U') {
			return print_LCS(b, x, i - 1, j);

		} else {
			return print_LCS(b, x, i, j - 1);

		}

	}

	private int getSize() {
		return x.length;
	}
}
