package Game;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

import java.time.LocalDateTime;
import Core.Terminal;
import Core.TerminalScheduleUpdate;
public class Main{
	public static int x = 0;
	public static int y = 0;
	public static char boy = '☺';
	public static char matrix[][];
	public static char map[][];
	public static String a[][];
	public static int b =20;
	public static int n = b*24;
	public static int counta = 10;
	public static int countb = 0;
	public static int coords[];
	public static LocalDateTime time;
	public static Terminal t = new Terminal(n, n);
	
	public static void Border() {
		Scanner scan = new Scanner(System.in);
		Random r = new Random();
		
		for (int i = 0; i<matrix.length; i++) {
			for (int j = 0; j<matrix[0].length; j++) {
				if (i == 0 || i == matrix.length-1) {
					matrix[i][j] = '―';
				} else if (j == 0) {
					matrix[i][j] = '⎸';
				} else if (j == matrix.length-1) {
					matrix[i][j] = '⎹';
				} else {
					matrix[i][j] = ' ';
				}
				//System.out.println(i + " : " + j);
			}
		}
	}
	public static void coords(int tx, int ty) {
		matrix[x][y] = ' ';
		matrix[0][0] = '─';
		//System.out.println("slojeno");
		
		x += tx;
		y += ty;
		if (matrix[x][y] == '―' || matrix[x][y] == '⎸' || matrix[x][y] == '⎹') {
			x -= tx;
			y -= ty;
		}
		System.out.println(x + "; " + y);
		//t.printMatrix();
		count.count();
		count.pr();
	}
	public static void apple() {
		Random rand = new Random();
		int max = matrix.length-1;
		int min = 1;
		int v = rand.nextInt(max - min) + min;
		int b = rand.nextInt(max - min) + min;
        if (v == 4 || v == 15 || v == 8 || v == 19 || b == 4 || b == 15 || b == 8 || b == 19) {
           matrix[v][b] = '―';
        } else {
           matrix[v][b] = '*';
           counta--;
        }
	}
	public class count {
		public static void count() {
			System.out.println("Wliza");
			if (matrix[x][y] == '*') {
				counta++;
				countb++;
			}
			matrix[x][y] = boy;
			t.printMatrix(matrix);
		}
		public static void pr() {
			t.print("Общо: " + countb);
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
	     matrix = new char[b][b];
	     a = new String[20][20];
	     Border();
	     //Map();
	     
	     
	     t.setFont("Courier New");
	     t.setMatrix(matrix);
	     t.setEditable(false);
	     coords(1, 1);
	     t.addKeyListener(new Keys());
	     time = LocalDateTime.now();
	     t.run();
	     
	     while (true) {
	    	    Thread.sleep(1000);
				if ((LocalDateTime.now().getSecond() - time.getSecond()) <= 2) {
					apple();
				 	t.printMatrix(matrix, true);
				 	count.pr();	
				} else {
					JOptionPane.showMessageDialog(t.getFrame(), "Общо точки: " + countb);
					t.stop();
					break;
				}
			}
	}
	
	public static class Keys extends KeyAdapter {
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W) {
			t.clear();
			coords(-1, 0);
			
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			t.clear();
			coords(0, -1);
			
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			t.clear();
			coords(1, 0);
			
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			t.clear();
			coords(0, 1);
			
		}
		time = LocalDateTime.now();
	  }
   }
}
