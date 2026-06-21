package Task4_OnlineExamination;


import java.util.ArrayList;
import java.util.List;

public class ExamEngine {

    private List<Question> questions;
    private int score;
    private static final int TIME_LIMIT_SECONDS = 60; // total exam time

    public ExamEngine() {
        this.questions = new ArrayList<>();
        this.score = 0;
        loadQuestions();
    }

    private void loadQuestions() {
        questions.add(new Question(
                "Which language runs on the JVM?",
                "Python", "Java", "C", "Ruby", 'B'));
        questions.add(new Question(
                "Which keyword is used to inherit a class in Java?",
                "implements", "extends", "inherits", "instanceof", 'B'));
        questions.add(new Question(
                "Which collection class allows key-value pairs?",
                "ArrayList", "LinkedList", "HashMap", "HashSet", 'C'));
        questions.add(new Question(
                "Which method is the entry point of a Java program?",
                "start()", "run()", "main()", "init()", 'C'));
        questions.add(new Question(
                "Which keyword is used to handle exceptions in Java?",
                "throw", "catch", "raise", "except", 'B'));
    }

    public void startExam() {
        System.out.println("\n--- EXAM STARTED ---");
        System.out.println("You have " + TIME_LIMIT_SECONDS + " seconds to complete " + questions.size() + " questions.");
        System.out.println("Enter your answer as A, B, C, or D for each question.\n");

        long examEndTime = System.currentTimeMillis() + (TIME_LIMIT_SECONDS * 1000L);
        int answered = 0;
        boolean timeUp = false;

        for (int i = 0; i < questions.size(); i++) {
            long remainingMillis = examEndTime - System.currentTimeMillis();

            if (remainingMillis <= 0) {
                timeUp = true;
                break;
            }

            Question question = questions.get(i);
            question.display(i + 1);
            System.out.print("Your answer: ");

            String input = InputReader.readLineWithTimeout(remainingMillis);

            if (input == null) {
                timeUp = true;
                System.out.println("\n\nTime's up! Auto-submitting your exam...");
                break;
            }

            input = input.trim();

            if (input.length() == 1 && "ABCDabcd".contains(input)) {
                char answer = input.charAt(0);
                if (question.isCorrect(answer)) {
                    score++;
                }
                answered++;
            } else {
                System.out.println("Invalid option entered, marked as skipped.");
                answered++;
            }
        }

        submitExam(answered);
    }

    private void submitExam(int answered) {
        System.out.println("\n--- EXAM SUBMITTED ---");
        System.out.println("Questions Answered: " + answered + "/" + questions.size());
        System.out.println("Your Score: " + score + "/" + questions.size());

        double percentage = (score * 100.0) / questions.size();
        System.out.println("Percentage: " + percentage + "%");

        if (percentage >= 40) {
            System.out.println("Result: PASS");
        } else {
            System.out.println("Result: FAIL");
        }
    }
}