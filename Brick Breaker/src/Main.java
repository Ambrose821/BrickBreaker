
import javax.swing.JFrame;
public class Main {
	public static void main(String [] args) {
		 
		JFrame screen = new JFrame();
		
		Play game = new Play();
		screen.setBounds(10, 10, 700, 600);
		screen.setTitle("Lets Break Some Eggs");
		screen.setResizable(false);
		screen.setVisible(true);
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.add(game);
	}
}
