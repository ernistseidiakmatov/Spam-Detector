import java.util.*;


public class Main {
    public static Map<String, String> spamDetector(Set<String> args) {

        List<String> emailList = new ArrayList<>(args);
        Map<String, String> result_Dictionary = new HashMap<>();

        // iterating through the set
        for (int i = 0; i < args.size(); i++) {
            Set<String> wordSet = new HashSet<>();

            Set<String> othersSet = new HashSet<>();
            for (int j = 0; j < args.size(); j++) {
                if (j != i) {
                    // considering only set of words in other sets of email excluding current
                    othersSet.add(emailList.get(j));
                }
            }

            // Convert sentences to words
            for (String sentence : othersSet) {
                String[] words = sentence.split(" ");
                wordSet.addAll(Arrays.asList(words));
            }

            int count = 0;
            String[] sent1 = emailList.get(i).split(" ");
            List<String> wordList = Arrays.asList(sent1);

            for (String word : wordList) {
                if (wordSet.contains(word)){
                    count += 1;
                }
            }
            String isSpam = "Ham";
            // calculating the ration of appearing words and setting it
            // to spam if ration is greater than 0.5
            if ((double) count / wordList.size() > 0.5) {
                isSpam = "Spam";
            }

            result_Dictionary.put(emailList.get(i), isSpam);
        }

        return result_Dictionary;
    }

    public static void main(String[] args) {

        List<String> emailList = Arrays.asList("Email one for lowest price",
                "Email two for best price!",
                "Email three for good price.",
                "This is regular email that is not a spam");

        List<String> cleanedEmailList = new ArrayList<>();

        for (String email : emailList) {
            String cleanedEmail = email.toLowerCase();
            cleanedEmail = cleanedEmail.replace(".", "");
            cleanedEmail = cleanedEmail.replace("!", "");
            cleanedEmail = cleanedEmail.replace(",", "");
            cleanedEmail = cleanedEmail.replace(":", "");
            cleanedEmail = cleanedEmail.replace(";", "");
            cleanedEmailList.add(cleanedEmail);
        }

        Set<String> email_Set = new HashSet<>(cleanedEmailList);

        Map<String, String> output = spamDetector(email_Set);

        for (Map.Entry<String, String> entry : output.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
