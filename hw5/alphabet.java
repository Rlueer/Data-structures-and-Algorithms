import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Iterator;

public class alphabet {
	// Set to store the English alphabet
    private Set<Character> english_alphabet = new LinkedHashSet<Character>();
	// Map to store the Vigenère cipher table
	private Map<Character, Map<Character, Character>> map = new HashMap<Character,  Map<Character, Character>>();
	
    public alphabet() {
		// do not edit this method
        fill_english_alphabet();
        fill_map();
    }

    private void fill_english_alphabet() {
		// do not edit this method
        for (char c : "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()) {
            english_alphabet.add(c);
        }
    }

    private void fill_map() {
		// Initialize the map with rows and shifted alphabets
		for (char rowIndicator : english_alphabet) {
			Map<Character, Character> rowMap = new HashMap<>();
			Iterator<Character> shiftedIterator = english_alphabet.iterator();
	
			// Skip characters to shift the alphabet
			for (int i = 0; i < rowIndicator - 'A'; i++) {
				shiftedIterator.next();
			}
	
			// Fill the row map with shifted alphabets
			for (char colIndicator : english_alphabet) {
				if (!shiftedIterator.hasNext()) {
					// Reset iterator for circular shifting
					shiftedIterator = english_alphabet.iterator(); // Reset iterator for circular shifting
				}
				char shiftedChar = shiftedIterator.next();
				rowMap.put(colIndicator, shiftedChar);
			}
			// Add the row map to the main map
			map.put(rowIndicator, rowMap);
		}
	}

    public void print_map() {
		// Print the Vigenère cipher table
        System.out.println("*** Vigenère Cipher ***\n\n");
        System.out.println("    " + english_alphabet);
        System.out.print("    ------------------------------------------------------------------------------");
        for (Character k : map.keySet()) {
            System.out.print("\n" + k + " | ");
            System.out.print(map.get(k).values());
        }
        System.out.println("\n");
    }
	// Getter for the map
	public Map<Character, Map<Character, Character>> get_map() {
		return map;
	}

}
