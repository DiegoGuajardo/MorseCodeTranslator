package morsecodetranslatorapplication;

import javax.swing.SwingUtilities;

public class MainApplication {

	public static void main(String[] args) {

		// The invokeLater() method ensures that our GUI is created and updated
		// in a thread-safe manner.
		SwingUtilities.invokeLater(new Runnable() {

			// Implements the run() method from the Runnable interface.
			@Override
			public void run() {

				// Creates a new instance of the MainFrame class and makes the
				// frame visible by invoking the setVisible(true) method on it.
				new MainFrame().setVisible(true);

			} // End of the run() method.

		}); // End of the invokeLater() method.

	} // End of the main() method.

} // End of our MainApplication program.