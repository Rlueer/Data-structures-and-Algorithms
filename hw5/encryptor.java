import java.util.Map;

public class encryptor {
    private Map<Character, Map<Character, Character>> map;
    private String key;
    private String plainText;
    private String cipherText = "";
    private String keystream = "";

    public encryptor(Map<Character, Map<Character, Character>> _map, String _key, String text) {
        // Initialize the map, key, and plaintext
        this.map = _map;
        this.key = _key;
        this.plainText = text;
    }

    public void encrypt() {
        // do not edit this method
		generate_keystream();
		generate_cipher_text();
    }

    private void generate_keystream() {
        int keyLength = key.length();
        int textLength = plainText.length();

        // Generate the keystream based on the length of the plaintext
        if (textLength <= keyLength) {
            // Case 1: Text is shorter than key
            keystream = key.substring(0, textLength);
        } else {
            // Case 2: Text is longer than key
            StringBuilder sb = new StringBuilder();
            int index = 0;
            while (sb.length() < textLength) {
                // Wrap around key characters
                sb.append(key.charAt(index));
                index = (index + 1) % keyLength; 
            }
            keystream = sb.toString();
        }
    }

    private void generate_cipher_text() {
        // Encrypt plaintext using the generated keystream and Vigenère table
        int textLength = plainText.length();
        for (int i = 0; i < textLength; i++) {
            char plainChar = plainText.charAt(i);
            char keyChar = keystream.charAt(i);
            // Get the corresponding ciphertext character from the Vigenère table
            char cipherChar = map.get(plainChar).get(keyChar);
            cipherText += cipherChar;
        }
    }
    // Getter for the keystream
    public String get_keystream() {
        return keystream;
    }
    // Getter for the ciphertext
    public String get_cipher_text() {
        return cipherText;
    }

}
