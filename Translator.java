package morsecodetranslatorapplication;

import java.util.HashMap;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

// Our Translator program will handle the logic for our Morse code translator application.
public class Translator {

	/**
	 * We will use a HashMap to translate plain text into Morse code, and vice
	 * versa. In Java, a HashMap is a data structure that stores the data in
	 * (Key, Value) pairs, and you can access them by an index of another type
	 * (e.g., Character, Integer, String).
	 * 
	 * In our Morse code translator application, we will use the letters,
	 * numbers, and special characters as the keys and the sequence of signal
	 * durations as the values. This way, we can easily look up the sequence of
	 * signal durations (i.e., value) for any given character by using the
	 * letter, number, or special character as the key.
	 * 
	 * In our Morse code translator application, we will use the sequence of
	 * signal durations as the keys and the letters, numbers, and special
	 * characters as the values. This way, we can easily look up the letter,
	 * number, or special character (i.e., value) for any given String by using
	 * the sequence of signal durations as the key.
	 */

	// Creates a new HashMap to have a key-value pair of "Character" and
	// "String"; that is, "Character" keys and "String" values.
	private HashMap<Character, String> morseCodeHashMap;

	// Creates a new HashMap to have a key-value pair of "String" and
	// "Character"; that is, "String" keys and "Character" values.
	private HashMap<String, Character> plainTextHashMap;

	/**
	 * Purpose of Method: Creates the Translator() constructor method.
	 * 
	 * This constructor method sets the "Character" keys and "String" values for
	 * each letter, number, and special character in the morseCodeHashMap
	 * HashMap. This constructor method sets the "String" keys and "Character"
	 * values for each sequence of signal durations in the plainTextHashMap
	 * HashMap.
	 */
	public Translator() {

		// Creates a new instance of the HashMap class to store Morse code
		// mappings.
		morseCodeHashMap = new HashMap<>();

		// Creates a new instance of the HashMap class to store plain text
		// mappings.
		plainTextHashMap = new HashMap<>();

		// Uppercase letters: plain text to Morse code
		morseCodeHashMap.put('A', ".-");
		morseCodeHashMap.put('B', "-...");
		morseCodeHashMap.put('C', "-.-.");
		morseCodeHashMap.put('D', "-..");
		morseCodeHashMap.put('E', ".");
		morseCodeHashMap.put('F', "..-.");
		morseCodeHashMap.put('G', "--.");
		morseCodeHashMap.put('H', "....");
		morseCodeHashMap.put('I', "..");
		morseCodeHashMap.put('J', ".---");
		morseCodeHashMap.put('K', "-.-");
		morseCodeHashMap.put('L', ".-..");
		morseCodeHashMap.put('M', "--");
		morseCodeHashMap.put('N', "-.");
		morseCodeHashMap.put('O', "---");
		morseCodeHashMap.put('P', ".--.");
		morseCodeHashMap.put('Q', "--.-");
		morseCodeHashMap.put('R', ".-.");
		morseCodeHashMap.put('S', "...");
		morseCodeHashMap.put('T', "-");
		morseCodeHashMap.put('U', "..-");
		morseCodeHashMap.put('V', "...-");
		morseCodeHashMap.put('W', ".--");
		morseCodeHashMap.put('X', "-..-");
		morseCodeHashMap.put('Y', "-.--");
		morseCodeHashMap.put('Z', "--..");

		// Lowercase letters: plain text to Morse code
		morseCodeHashMap.put('a', ".-");
		morseCodeHashMap.put('b', "-...");
		morseCodeHashMap.put('c', "-.-.");
		morseCodeHashMap.put('d', "-..");
		morseCodeHashMap.put('e', ".");
		morseCodeHashMap.put('f', "..-.");
		morseCodeHashMap.put('g', "--.");
		morseCodeHashMap.put('h', "....");
		morseCodeHashMap.put('i', "..");
		morseCodeHashMap.put('j', ".---");
		morseCodeHashMap.put('k', "-.-");
		morseCodeHashMap.put('l', ".-..");
		morseCodeHashMap.put('m', "--");
		morseCodeHashMap.put('n', "-.");
		morseCodeHashMap.put('o', "---");
		morseCodeHashMap.put('p', ".--.");
		morseCodeHashMap.put('q', "--.-");
		morseCodeHashMap.put('r', ".-.");
		morseCodeHashMap.put('s', "...");
		morseCodeHashMap.put('t', "-");
		morseCodeHashMap.put('u', "..-");
		morseCodeHashMap.put('v', "...-");
		morseCodeHashMap.put('w', ".--");
		morseCodeHashMap.put('x', "-..-");
		morseCodeHashMap.put('y', "-.--");
		morseCodeHashMap.put('z', "--..");

		// Numbers: plain text to Morse code
		morseCodeHashMap.put('0', "-----");
		morseCodeHashMap.put('1', ".----");
		morseCodeHashMap.put('2', "..---");
		morseCodeHashMap.put('3', "...--");
		morseCodeHashMap.put('4', "....-");
		morseCodeHashMap.put('5', ".....");
		morseCodeHashMap.put('6', "-....");
		morseCodeHashMap.put('7', "--...");
		morseCodeHashMap.put('8', "---..");
		morseCodeHashMap.put('9', "----.");

		// Special characters: plain text to Morse code
		morseCodeHashMap.put(' ', "/");
		morseCodeHashMap.put('&', ".-...");
		morseCodeHashMap.put('\'', ".----.");
		morseCodeHashMap.put('@', ".--.-.");
		morseCodeHashMap.put(')', "-.--.-");
		morseCodeHashMap.put('(', "-.--.");
		morseCodeHashMap.put(':', "---...");
		morseCodeHashMap.put(',', "--..--");
		morseCodeHashMap.put('=', "-...-");
		morseCodeHashMap.put('!', "-.-.--");
		morseCodeHashMap.put('.', ".-.-.-");
		morseCodeHashMap.put('-', "-....-");
		morseCodeHashMap.put('x', "-..-");
		morseCodeHashMap.put('%', "------..-.-----");
		morseCodeHashMap.put('+', ".-.-.");
		morseCodeHashMap.put('"', ".-..-.");
		morseCodeHashMap.put('?', "..--..");
		morseCodeHashMap.put('/', "-..-.");

		// Uppercase letters: Morse code to plain text
		plainTextHashMap.put(".-", 'A');
		plainTextHashMap.put("-...", 'B');
		plainTextHashMap.put("-.-.", 'C');
		plainTextHashMap.put("-..", 'D');
		plainTextHashMap.put(".", 'E');
		plainTextHashMap.put("..-.", 'F');
		plainTextHashMap.put("--.", 'G');
		plainTextHashMap.put("....", 'H');
		plainTextHashMap.put("..", 'I');
		plainTextHashMap.put(".---", 'J');
		plainTextHashMap.put("-.-", 'K');
		plainTextHashMap.put(".-..", 'L');
		plainTextHashMap.put("--", 'M');
		plainTextHashMap.put("-.", 'N');
		plainTextHashMap.put("---", 'O');
		plainTextHashMap.put(".--.", 'P');
		plainTextHashMap.put("--.-", 'Q');
		plainTextHashMap.put(".-.", 'R');
		plainTextHashMap.put("...", 'S');
		plainTextHashMap.put("-", 'T');
		plainTextHashMap.put("..-", 'U');
		plainTextHashMap.put("...-", 'V');
		plainTextHashMap.put(".--", 'W');
		plainTextHashMap.put("-..-", 'X');
		plainTextHashMap.put("-.--", 'Y');
		plainTextHashMap.put("--..", 'Z');

		// Lowercase letters: Morse code to plain text
		plainTextHashMap.put(".-", 'a');
		plainTextHashMap.put("-...", 'b');
		plainTextHashMap.put("-.-.", 'c');
		plainTextHashMap.put("-..", 'd');
		plainTextHashMap.put(".", 'e');
		plainTextHashMap.put("..-.", 'f');
		plainTextHashMap.put("--.", 'g');
		plainTextHashMap.put("....", 'h');
		plainTextHashMap.put("..", 'i');
		plainTextHashMap.put(".---", 'j');
		plainTextHashMap.put("-.-", 'k');
		plainTextHashMap.put(".-..", 'l');
		plainTextHashMap.put("--", 'm');
		plainTextHashMap.put("-.", 'n');
		plainTextHashMap.put("---", 'o');
		plainTextHashMap.put(".--.", 'p');
		plainTextHashMap.put("--.-", 'q');
		plainTextHashMap.put(".-.", 'r');
		plainTextHashMap.put("...", 's');
		plainTextHashMap.put("-", 't');
		plainTextHashMap.put("..-", 'u');
		plainTextHashMap.put("...-", 'v');
		plainTextHashMap.put(".--", 'w');
		plainTextHashMap.put("-..-", 'x');
		plainTextHashMap.put("-.--", 'y');
		plainTextHashMap.put("--..", 'z');

		// Numbers: Morse code to plain text
		plainTextHashMap.put("-----", '0');
		plainTextHashMap.put(".----", '1');
		plainTextHashMap.put("..---", '2');
		plainTextHashMap.put("...--", '3');
		plainTextHashMap.put("....-", '4');
		plainTextHashMap.put(".....", '5');
		plainTextHashMap.put("-....", '6');
		plainTextHashMap.put("--...", '7');
		plainTextHashMap.put("---..", '8');
		plainTextHashMap.put("----.", '9');

		// Special characters: Morse code to plain text
		plainTextHashMap.put("/", ' ');
		plainTextHashMap.put(".-...", '&');
		plainTextHashMap.put(".----.", '\'');
		plainTextHashMap.put(".--.-.", '@');
		plainTextHashMap.put("-.--.-", ')');
		plainTextHashMap.put("-.--.", '(');
		plainTextHashMap.put("---...", ':');
		plainTextHashMap.put("--..--", ',');
		plainTextHashMap.put("-...-", '=');
		plainTextHashMap.put("-.-.--", '!');
		plainTextHashMap.put(".-.-.-", '.');
		plainTextHashMap.put("-....-", '-');
		plainTextHashMap.put("-..-", 'x');
		plainTextHashMap.put("------..-.-----", '%');
		plainTextHashMap.put(".-.-.", '+');
		plainTextHashMap.put(".-..-.", '"');
		plainTextHashMap.put("..--..", '?');
		plainTextHashMap.put("-..-.", '/');

	} // End of the Translator() constructor method.

	/**
	 * Purpose of Method: Creates the translateToMorseCode() method. This method
	 * translates each letter, number, and special character into its sequence
	 * of signal durations.
	 */
	public String translateToMorseCode(String plainTextToTranslate) {

		// Creates a new StringBuilder object with no characters in it and an
		// initial capacity of 16 characters.
		StringBuilder translatedPlainText = new StringBuilder();

		String[] plainTextLine = plainTextToTranslate.split("\\n");
		// for each line
		for (String line : plainTextLine) {
			// Iterates through each character in the plainTextToTranslate
			// String.
			for (Character character : line.toCharArray()) {

				// Translates any given character using the morseCodeHashMap
				// HashMap, and afterwards, appends the translated code to the
				// StringBuilder object.
				translatedPlainText
						.append(morseCodeHashMap.get(character) + " ");

			}
			translatedPlainText.append("\n");
		}
		// Returns the Morse code translation of any given character in String
		// format.
		return translatedPlainText.toString().trim(); // The trim() method
														// removes any
														// leading/trailing
														// whitespaces in the
														// Morse code
														// translation.

	} // End of the translateToMorseCode() method.

	/**
	 * Purpose of Method: Creates the translateToPlainText() method. This method
	 * translates a sequence of signal durations into its corresponding letter,
	 * number, or special character.
	 */
	public String translateToPlainText(String morseCodeToTranslate) {

		// Creates a new StringBuilder object with no characters in it and an
		// initial capacity of 16 characters.
		StringBuilder translatedMorseCode = new StringBuilder();

		String[] morseCodeLine = morseCodeToTranslate.split("\\n");

		// for each line
		for (String line : morseCodeLine) {

			// Splits a sequence of signal durations into individual signal
			// durations.
			String[] morseCodeArray = line.split(" ");

			// Iterates through each signal duration in the morseCodeArray
			// String array.
			for (String signalDuration : morseCodeArray) {

				if (plainTextHashMap.containsKey(signalDuration)) {

					translatedMorseCode
							.append(plainTextHashMap.get(signalDuration));

				} else {

					translatedMorseCode.append(
							"\nInvalid input. Please enter only valid Morse code characters.");

				}
			}

			translatedMorseCode.append("\n");

		}

		// Returns the plain text translation of any given sequence of signal
		// durations in String format.
		return translatedMorseCode.toString().toUpperCase(); // The
																// toUpperCase()
																// method
																// ensures the
																// plain text
																// translation
																// is in
																// uppercase
																// casing.

	} // End of the translateToPlainText() method.

	/**
	 * Purpose of Method: Creates the playSound() method. This method plays an
	 * audible representation of Morse code messages.
	 */
	public void playSound(String[] message)
			throws LineUnavailableException, InterruptedException {

		// Creates a new AudioFormat object with parameters: sample rate of
		// 44100 hertz (Hz), 16-bit sample size, 1 channel (mono), signed PCM
		// encoding, and little-endian byte order.
		AudioFormat audioFormat = new AudioFormat(44100, 16, 1, true, false);

		// Creates a new DataLine.Info object stating that we want a source data
		// line with the specified AudioFormat object.
		DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class,
				audioFormat);

		// Obtains a SourceDataLine object from the AudioSystem class based on
		// the specified DataLine.Info object.
		SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem
				.getLine(dataLineInfo);

		// Opens the SourceDataLine object with the specified AudioFormat
		// object.
		sourceDataLine.open(audioFormat);

		// Starts the SourceDataLine object, making it ready to receive audio
		// data for playback.
		sourceDataLine.start();

		// Sets the duration of each sound that is to be played.
		int dotDuration = 200;
		int dashDuration = (int) (2.0 * dotDuration);
		int slashDuration = 2 * dashDuration;

		// Iterates through each Morse code pattern in the message String.
		for (String pattern : message) {

			System.out.println(pattern); // Prints the current Morse code
											// pattern.

			// Iterates through each character in the Morse code pattern.
			for (char c : pattern.toCharArray()) {

				if (c == '.') {

					// Plays a beep sound for a dot and pauses for the dot
					// duration.
					playBeep(sourceDataLine, dotDuration);
					Thread.sleep(dotDuration);

				} else if (c == '-') {

					// Plays a beep sound for a dash and pauses for the dot
					// duration.
					playBeep(sourceDataLine, dashDuration);
					Thread.sleep(dotDuration);

				} else if (c == '/') {

					// Pauses for the slash duration.
					Thread.sleep(slashDuration);

				}

			}

			// Pauses for the dot duration after each Morse code pattern.
			Thread.sleep(dotDuration);

		}

		// Drains, stops, and closes the SourceDataLine object after playing all
		// the Morse code patterns.
		sourceDataLine.drain();
		sourceDataLine.stop();
		sourceDataLine.close();

	} // End of the playSound() method.

	/**
	 * Purpose of Method: Creates the playBeep() method. This method generates
	 * and plays a beep sound with the specified duration using a sine wave.
	 */
	private void playBeep(SourceDataLine line, int duration) {

		// Allocates a byte array to store audio data based on the specified
		// duration and sample rate, i.e., 44100 Hz.
		byte[] data = new byte[duration * 44100 / 1000];

		// Generates audio data for the specified duration using a sine wave.
		for (int i = 0; i < data.length; i++) {

			// Calculates the angle for the sine wave based on the current index
			// and the frequency, i.e., 440 Hz.
			double angle = i / (44100.0 / 440) * 2.0 * Math.PI;

			// Converts the sine wave value to a byte and stores it in the data
			// byte array.
			data[i] = (byte) (Math.sin(angle) * 127.0);

		}

		// Writes the generated audio data to the audio output line.
		line.write(data, 0, data.length);

	} // End of the playBeep() method.

} // End of our Translator program.