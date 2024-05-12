import java.util.Map;
import java.util.Iterator;

public class decryptor {
    private Map<Character, Map<Character, Character>> map;
    private String key;
    private String keystream = "";
    private String plainText = "";
    private String cipherText;

    public decryptor(Map<Character, Map<Character, Character>> _map, String _key, String text) {
        // Initialize the map, key, and ciphertext
		this.map = _map;
        this.key = _key;
        this.cipherText = text;
    }

    public void decrypt() {
		// do not edit this method
        generate_keystream();
        generate_plain_text();
    }

    private void generate_keystream() {
		int keyLength = key.length();
		int textLength = cipherText.length();

		// Generate the keystream based on the length of the ciphertext
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < textLength; i++) {
			char keyChar = key.charAt(i % keyLength); // Wrap around the key characters
			sb.append(keyChar);
		}
		keystream = sb.toString();
	}

    private void generate_plain_text() {
		// You must use map.get(x).keySet() with an iterator in this method
		int textLength = cipherText.length();
	
		// Iterate over each ciphertext character
		for (int i = 0; i < textLength; i++) {
			char cipherChar = cipherText.charAt(i);
			char keyChar = keystream.charAt(i);
			boolean found = false;
	
			// Reset iterator at the start of each outer loop iteration
			Iterator<Character> keyIterator = map.keySet().iterator();
	
			// Iterate over row indicators in the Vigenère map
			while (keyIterator.hasNext()) {
				char rowIndicator = keyIterator.next();
				Map<Character, Character> rowMap = map.get(rowIndicator);
	
				// Check if the current row's column contains the ciphertext character
				if (rowMap.get(keyChar) == cipherChar) {
					plainText += rowIndicator;
					found = true;
					break;
				}
			}
			// If no match was found, reset the iterator
			if (!found) {
				keyIterator = map.keySet().iterator();
			}
		}
	}

    public String get_keystream() {
        return keystream;
    }

    public String get_plain_text() {
        return plainText;
    }

}
