package Task4_OnlineExamination;

public class Question {

    private String questionText;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private char correctOption;

    public Question(String questionText, String optionA, String optionB,
                     String optionC, String optionD, char correctOption) {
        this.questionText = questionText;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctOption = correctOption;
    }

    public void display(int number) {
        System.out.println("\nQ" + number + ". " + questionText);
        System.out.println("   A) " + optionA);
        System.out.println("   B) " + optionB);
        System.out.println("   C) " + optionC);
        System.out.println("   D) " + optionD);
    }

    public boolean isCorrect(char selectedOption) {
        return Character.toUpperCase(selectedOption) == correctOption;
    }
}