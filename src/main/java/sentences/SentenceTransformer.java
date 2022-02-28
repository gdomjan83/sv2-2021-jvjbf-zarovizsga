package sentences;

public class SentenceTransformer {
    public String shortenSentence(String sentence) {
        checkIfSentence(sentence);
        String[] words = sentence.split(" ");
        if (words.length >= 5) {
            return words[0] + " ... " + words[words.length - 1];
        }
        return sentence;
    }

    private void checkIfSentence(String sentence) {
        char[] letters = sentence.toCharArray();
        if (!('A' <= letters[0] && 'Z' >= letters[0])) {
            throw new IllegalArgumentException("Must start with capital letter!");
        }
        if (!(".!?".contains(String.valueOf(letters[letters.length - 1])))) {
            throw new IllegalArgumentException("Must end with . ! or ?");
        }
    }
}
