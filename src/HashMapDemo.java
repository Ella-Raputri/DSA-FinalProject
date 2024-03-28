import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

public class HashMapDemo {
    static HashMap<Question, String> quiz = new HashMap<Question, String>();
    static Scanner scanner = new Scanner(System.in);

    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Entry<T, E> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static String getIDfromNumber(int num){
        for(Question i: quiz.keySet()){
            if(i.getQuestionNumber() == num){
                return i.getQuestionID();
            }
        }
        return null;
    }

    static int partition(ArrayList<Question> arr, int low, int high){
        // Choosing the pivot
        int pivot = arr.get(high).getQuestionNumber();

        // Index of smaller element and indicates
        // the right position of pivot found so far
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {

            // If current element is smaller than the pivot
            if (arr.get(j).getQuestionNumber() < pivot) {

                // Increment index of smaller element
                i++;
                Collections.swap(arr, i, j);
            }
        }
        Collections.swap(arr, i + 1, high);
        return (i + 1);
    }
    
    static void quickSort(ArrayList<Question> arr, int low, int high){
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public void addQuestion(){
        System.out.print("Enter question: ");
        String question = scanner.nextLine();

        System.out.print("Enter correct answer: ");
        String correctAnswer = scanner.nextLine();
        
        Question q1 = new Question(correctAnswer, question);
        q1.setNumberID(quiz.size()+1);

        quiz.put(q1, q1.getQuestionID());
        System.out.println("Added successfully");
    }


    public void deleteQuestion(){
        if(quiz.isEmpty()){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else{
            printQuestions();
            System.out.print("What question ID do you want to delete? ");
            String id = scanner.nextLine();

            if(quiz.containsValue(id)){
                quiz.remove(getKeyByValue(quiz, id));
                System.out.println("Deleted successfully.");

                int j=1;
                for(Question i: quiz.keySet()){
                    i.setQuestionNumber(j);
                    quiz.put(i, i.getQuestionID());
                    j++;
                }
                return;
            }
            System.out.println("ID invalid. Please try again.");
        }
    }


    public void editQuestion(){
        if(quiz.isEmpty()){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else{
            printQuestions();
            System.out.print("What question ID do you want to edit? ");
            String id = scanner.nextLine();

            if(quiz.containsValue(id)){
                Question key = getKeyByValue(quiz, id);
                System.out.println("Current question: " + key.getQuestion());
                System.out.println("Current answer: " + key.getCorrectAnswer());

                System.out.print("Do you want to change the question? (Y/N) ");
                String questionChange = scanner.nextLine();
                questionChange = questionChange.toLowerCase();

                if(questionChange.equals("y")){
                    System.out.print("New Question: ");
                    String newQuestion = scanner.nextLine();
                    key.setQuestion(newQuestion);
                    System.out.println("Question has been changed successfully.");
                }
                else if(!(questionChange.equals("y")) && !(questionChange.equals("n"))){
                    System.out.println("Invalid input.");
                    return;
                }

                System.out.print("Do you want to change the answer? (Y/N) ");
                String answerChange = scanner.nextLine();
                answerChange = answerChange.toLowerCase();
                if(answerChange.equals("y")){
                    System.out.print("New Answer: ");
                    String newAnswer = scanner.nextLine();
                    getKeyByValue(quiz, id).setCorrectAnswer(newAnswer);;
                    System.out.println("Answer has been changed successfully.");
                }
                else if(!(questionChange.equals("y")) && !(questionChange.equals("n"))){
                    System.out.println("Invalid input.");
                    return;
                }
                
                System.out.println("Returning to the main menu...");
                return;
            }

            System.out.println("ID invalid. Please try again.");
        }
    }


    public void changeOrder(){
        if(quiz.isEmpty()){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else{
            printQuestions();
            System.out.print("What question ID do you want to change the order? ");
            String id = scanner.nextLine();

            if(quiz.containsValue(id)){
                Question currentkey = getKeyByValue(quiz, id);
                int currentNumber = currentkey.getQuestionNumber();
                System.out.println("Current question number: " + currentNumber);

                System.out.print("Change to question number: ");
                int newNumber = scanner.nextInt();
                scanner.nextLine();

                String newNumid = getIDfromNumber(newNumber);
                Question oldkey = getKeyByValue(quiz, newNumid);
                

                if(currentNumber == newNumber){
                    System.out.println("Question number is not changed.");
                    return;
                }

                else if(newNumber <= quiz.size() && newNumber > 0){                    
                    if(currentNumber < newNumber){
                        oldkey.setQuestionNumber(oldkey.getQuestionNumber()-1);
                        for(Question i: quiz.keySet()){
                            if(i.getQuestionNumber() < newNumber && i.getQuestionNumber()>currentNumber && i!=oldkey){
                                i.setQuestionNumber(i.getQuestionNumber()-1);
                            }
                        }
                    }
                    else{
                        oldkey.setQuestionNumber(oldkey.getQuestionNumber()+1);
                        for(Question i: quiz.keySet()){
                            if(i.getQuestionNumber() > newNumber && i.getQuestionNumber()<currentNumber && i!=oldkey){
                                i.setQuestionNumber(i.getQuestionNumber()+1);
                            }
                        }
                    }

                    currentkey.setQuestionNumber(newNumber);
                    System.out.println("Question number has been changed successfully.");
                }


                else if(newNumber > quiz.size() || newNumber < 0){
                    System.out.println("The number is out of list");
                    return;
                }
                return;
            }
            System.out.println("ID invalid. Please try again.");

        }
    }


    public void printQuestions(){
        if(quiz.isEmpty()){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else{
            System.out.println("\nYour current quiz: ");

            for(int i=1; i<=quiz.size(); i++){
                for(Question key: quiz.keySet()){
                    if(key.getQuestionNumber() == i){
                        System.out.println("Question " + key.getQuestionNumber());
                        System.out.println("Question ID: "+ key.getQuestionID());
                        System.out.println("Question: "+ key.getQuestion());
                        System.out.println("Answer: "+ key.getCorrectAnswer());
                        System.out.println();

                    }
                }
            }
           
        }
    }


    public void questionSearch(){
        if(quiz.isEmpty()){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else{
            System.out.print("Search for string: ");
            String str = scanner.nextLine();
            boolean track = false;

            ArrayList<Question> al = new ArrayList<>();
            al.addAll(quiz.keySet());
            quickSort(al, 0, al.size()-1);

            for(Question i:al){
                String question = i.getQuestion();
                String answer = i.getCorrectAnswer();

                if(question.contains(str) || answer.contains(str)){
                    System.out.println("Question " + i.getQuestionNumber());
                    System.out.println("Question ID: "+ i.getQuestionID());
                    System.out.println("Question: "+ i.getQuestion());
                    System.out.println("Answer: "+ i.getCorrectAnswer());
                    System.out.println();
                    track = true;
                }
            }             

            if(track){
                return;
            }
            else{
                System.out.println("No question or answer with such string.");
            }

        }
    }


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
                case "E":
                    demo.editQuestion();
                    break;
                case "C":
                    demo.changeOrder();
                    break;
                case "P":
                    demo.printQuestions();
                    break;
                case "S":
                    demo.questionSearch();
                    break;
                case "Q":
                    return;
                default:
                    System.out.println("Invalid command. Please try again.");
                    break;
            }
        }


    

    }
}


