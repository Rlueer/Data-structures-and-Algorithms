public class preprocessor {
    private String initial_string;
    private String preprocessed_string;

    public preprocessor(String str) throws IllegalArgumentException {
        this.initial_string = str;
        this.preprocessed_string = "";
    }

    public void preprocess() {
		// do not edit this method
        capitalize();
        clean();
    }

    private void capitalize() {
        // Capitalize the initial string
        this.initial_string = this.initial_string.toUpperCase(java.util.Locale.ENGLISH);
    }

    private void clean() {
		// Remove all non-alphabetic characters from the preprocessed string
        StringBuilder cleaned = new StringBuilder();
        for (char c : this.initial_string.toCharArray()) {
            if(c=='ı'){
                continue;
            }
            if (Character.isLetter(c)) {
                cleaned.append(c);
            }
        }
        this.preprocessed_string = cleaned.toString();
        this.preprocessed_string = this.preprocessed_string.replaceAll("[^A-Zı]", "");
    }

    public String get_preprocessed_string() {
        return this.preprocessed_string;
    }

}
