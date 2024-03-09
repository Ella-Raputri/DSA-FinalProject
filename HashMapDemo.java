import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Map.Entry;

public class HashMapDemo {
    Map<Question, String> quiz = new LinkedHashMap<Question, String>();
    static Scanner scanner = new Scanner(System.in);

    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Entry<T, E> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }


    public void addQuestion(){
        System.out.print("Enter question: ");
        String question = scanner.nextLine();

        System.out.print("Enter correct answer: ");
        String correctAnswer = scanner.nextLine();
        
        Question q1 = new Question(correctAnswer, question);
        q1.setQuestionNumber(quiz.size()+1);

        quiz.put(q1, q1.getQuestionID());
        System.out.println("Added successfully" + q1.getQuestionID());
    }


    public void deleteQuestion(){
        if(quiz.isEmpty()){
            System.out.println("Your current quiz is empty.");
        }
        else{
            printQuestions();
            System.out.print("What question ID do you want to delete? ");
            String answer = scanner.nextLine();

            if(quiz.containsValue(answer)){
                quiz.remove(getKeyByValue(quiz, answer));
                System.out.println("Deleted successfully.");

                int j=1;
                for(Question i: quiz.keySet()){
                    i.setQuestionNumber(j);
                    quiz.put(i, i.getQuestionID());
                    j++;
                    System.out.println(i.getQuestion() + quiz.get(i));
                }
                return;
            }
            System.out.println("ID invalid. Please try again.");
        }
    }


    // public void editQuestion(){
    //     if(quiz.isEmpty()){
    //         System.out.println("Your current quiz is empty.");
    //     }
    //     else{
    //         printQuestions();
    //         System.out.print("What question ID do you want to edit? ");
    //         String answer = scanner.nextLine();

    //         if(quiz.containsKey(answer)){
    //             System.out.println("Current question: " + quiz.get(answer).getQuestion());
    //             System.out.println("Current answer: " + quiz.get(answer).getCorrectAnswer());

    //             System.out.print("Do you want to change the question? (Y/N) ");
    //             String questionChange = scanner.nextLine();
    //             questionChange = questionChange.toLowerCase();

    //             if(questionChange.equals("y")){
    //                 System.out.print("New Question: ");
    //                 String newQuestion = scanner.nextLine();
    //                 quiz.get(answer).setQuestion(newQuestion);
    //                 System.out.println("Question has been changed successfully.");
    //             }
    //             else if(!(questionChange.equals("y")) && !(questionChange.equals("n"))){
    //                 System.out.println("Invalid input.");
    //                 return;
    //             }

    //             System.out.print("Do you want to change the answer? (Y/N) ");
    //             String answerChange = scanner.nextLine();
    //             answerChange = answerChange.toLowerCase();
    //             if(answerChange.equals("y")){
    //                 System.out.print("New Answer: ");
    //                 String newAnswer = scanner.nextLine();
    //                 quiz.get(answer).setCorrectAnswer(newAnswer);
    //                 System.out.println("Answer has been changed successfully.");
    //             }
    //             else if(!(questionChange.equals("y")) && !(questionChange.equals("n"))){
    //                 System.out.println("Invalid input.");
    //                 return;
    //             }

    //             return;
    //         }

    //         System.out.println("ID invalid. Please try again.");
    //     }
    // }


    // public void changeOrder(){
    //     if(quiz.isEmpty()){
    //         System.out.println("Your current quiz is empty.");
    //     }
    //     else{
    //         printQuestions();
    //         System.out.print("What question ID do you want to change the order? ");
    //         String answer = scanner.nextLine();

    //         if(quiz.containsKey(answer)){
    //             System.out.println("Current question number: " + quiz.get(answer).getQuestionNumber());

    //             System.out.print("Change to question number: ");
    //             int newNumber = scanner.nextInt();
    //             scanner.nextLine();

    //             if(newNumber <= quiz.size()){
    //                 for(String i:quiz.keySet()){
    //                     if(quiz.get(i).getQuestionNumber() == newNumber){
    //                         quiz.get(i).setQuestionNumber(quiz.get(answer).getQuestionNumber());
    //                         break;
    //                     }
    //                 }
    //                 quiz.get(answer).setQuestionNumber(newNumber);
    //                 System.out.println("Question number has been changed successfully.");
    //             }
    //             else{
    //                 System.out.println("The number is out of list");
    //                 return;
    //             }
    //             return;
    //         }
    //         System.out.println("ID invalid. Please try again.");

    //     }
    // }


    public void printQuestions(){
        if(quiz.isEmpty()){
            System.out.println("Your current quiz is empty.");
        }
        else{
            System.out.println("\nYour current quiz: ");
            for(Question i: quiz.keySet()){
                System.out.println("Question " + i.getQuestionNumber());
                System.out.println("Question ID: "+ i.getQuestionID());
                System.out.println("Question: "+ i.getQuestion());
                System.out.println("Answer: "+ i.getCorrectAnswer());
                System.out.println();
            }
        }
    }


    // public void questionSearch(){
    //     if(quiz.isEmpty()){
    //         System.out.println("Your current quiz is empty.");
    //     }
    //     else{
    //         System.out.print("Search for question ID: ");
    //         String answer = scanner.nextLine();

    //         if(quiz.containsKey(answer)){
    //             System.out.println("Question " + quiz.get(answer).getQuestionNumber());
    //             System.out.println("Question ID: "+ quiz.get(answer).getQuestionID());
    //             System.out.println("Question: "+ quiz.get(answer).getQuestion());
    //             System.out.println("Answer: "+ quiz.get(answer).getCorrectAnswer());
    //             System.out.println();
    //             return;
    //         }

    //         System.out.println("ID invalid. Please try again.");

    //     }
    // }


    public static void main(String[] args) {
         
        HashMapDemo demo = new HashMapDemo();

        while(true){
            System.out.println("\n************************************");
            System.out.println("\nQuestion Editing");
            System.out.println("(A)dd");
            System.out.println("(D)elete");
            System.out.println("(E)dit Question");
            System.out.println("(C)hange Question's Order");
            System.out.println("(P)rint List of Questions");
            System.out.println("(S)earch");
            System.out.println("(Q)uit");
            System.out.println("************************************");
            System.out.print("Please enter a command: ");
            String command = scanner.nextLine();
            command = command.toUpperCase();

            switch (command) {
                case "A":
                    demo.addQuestion();
                    break;
                case "D":
                    demo.deleteQuestion();
                    break;
                // case "E":
                //     demo.editQuestion();
                //     break;
                // case "C":
                //     demo.changeOrder();
                    // break;
                case "P":
                    demo.printQuestions();
                    break;
                // case "S":
                //     demo.questionSearch();
                //     break;
                case "Q":
                    return;
                default:
                    System.out.println("Invalid command. Please try again.");
                    break;
            }
        }


    

    }
}


