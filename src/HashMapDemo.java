//import Java APIs
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

public class HashMapDemo {
    //initialize hashmap to contain the Questions
    static HashMap<Question, String> quiz = new HashMap<Question, String>();
    //scanner for user input
    static Scanner scanner = new Scanner(System.in);

    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        //return the key of the map by using its value
        for (Entry<T, E> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static String getIDfromNumber(int num){
        //get the Question ID using the question number
        for(Question i: quiz.keySet()){
            if(i.getQuestionNumber() == num){
                return i.getQuestionID();
            }
        }
        return null; //if there is no such Question with the question number
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
        //perform quick sort by first partition it first
        if (low < high) {
            int pi = partition(arr, low, high);

            //then recur on the range before and range after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public void addQuestion(){
        //prompt user to input the question and answer
        System.out.print("Enter question: ");
        String question = scanner.nextLine();
        System.out.print("Enter correct answer: ");
        String correctAnswer = scanner.nextLine();
        
        //create a new Question object based on the question and answer
        Question q1 = new Question(correctAnswer, question);
        //set the ID number of the Question
        q1.setNumberID(quiz.size()+1);

        //put the Question inside the hashmap as key and ID as value
        quiz.put(q1, q1.getQuestionID());
        System.out.println("Added successfully");
    }


    public void deleteQuestion(){
        //if hashmap is empty, then print the error message
        if(quiz.isEmpty()){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else{
            //print all the Questions and their information
            printQuestions();
            //ask user about which Question that they want to delete
            System.out.print("What question ID do you want to delete? ");
            String id = scanner.nextLine();

            //if there exists a Question that has the same ID as the inputted 
            if(quiz.containsValue(id)){
                //remove that Question
                quiz.remove(getKeyByValue(quiz, id));
                System.out.println("Deleted successfully.");

                //reset all the question number
                int j=1;
                for(Question i: quiz.keySet()){
                    i.setQuestionNumber(j);
                    quiz.put(i, i.getQuestionID());
                    j++;
                }
                return;
            }
            //if ID is invalid, print error message
            System.out.println("ID invalid. Please try again.");
        }
    }


    public void editQuestion(){
        //if hashmap is empty, then print the error message
        if(quiz.isEmpty()){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else{
            //print all Questions
            printQuestions();
            //ask user which Question that they want to edit
            System.out.print("What question ID do you want to edit? ");
            String id = scanner.nextLine();

            //if there exists a Question that has the same ID as the inputted
            if(quiz.containsValue(id)){
                //print the current question and answer of the Question
                Question key = getKeyByValue(quiz, id);
                System.out.println("Current question: " + key.getQuestion());
                System.out.println("Current answer: " + key.getCorrectAnswer());

                //ask user whether they want to change the question or not
                System.out.print("Do you want to change the question? (Y/N) ");
                String questionChange = scanner.nextLine();
                questionChange = questionChange.toLowerCase();

                //if user wants to change the question
                if(questionChange.equals("y")){
                    //ask user to input the new question
                    System.out.print("New Question: ");
                    String newQuestion = scanner.nextLine();
                    key.setQuestion(newQuestion);
                    System.out.println("Question has been changed successfully.");
                }
                //if the input is invalid, then return
                else if(!(questionChange.equals("y")) && !(questionChange.equals("n"))){
                    System.out.println("Invalid input.");
                    return;
                }

                //ask user whether they want to change the answer or not
                System.out.print("Do you want to change the answer? (Y/N) ");
                String answerChange = scanner.nextLine();
                answerChange = answerChange.toLowerCase();
                
                //if user wants to change the answer
                if(answerChange.equals("y")){
                    //ask user to input the new answer
                    System.out.print("New Answer: ");
                    String newAnswer = scanner.nextLine();
                    getKeyByValue(quiz, id).setCorrectAnswer(newAnswer);;
                    System.out.println("Answer has been changed successfully.");
                }
                //if the input is invalid, then return
                else if(!(questionChange.equals("y")) && !(questionChange.equals("n"))){
                    System.out.println("Invalid input.");
                    return;
                }
                
                System.out.println("Returning to the main menu...");
                return;
            }
            //if the ID is invalid, then return
            System.out.println("ID invalid. Please try again.");
        }
    }


    public void changeOrder(){
        //if hashmap is empty, then print the error message
        if(quiz.isEmpty()){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else{
            //print all the Questions
            printQuestions();
            //ask user which Question they want to change its order
            System.out.print("What question ID do you want to change the order? ");
            String id = scanner.nextLine();

            //if there exists a Question with the inputted ID
            if(quiz.containsValue(id)){
                //get current Question and its number
                Question currentkey = getKeyByValue(quiz, id);
                int currentNumber = currentkey.getQuestionNumber();
                System.out.println("Current question number: " + currentNumber);

                //ask user for the new number
                System.out.print("Change to question number: ");
                int newNumber = scanner.nextInt();
                scanner.nextLine();

                //get the ID number and Question of the original new number Question
                String newNumid = getIDfromNumber(newNumber);
                Question oldkey = getKeyByValue(quiz, newNumid);

                //if current number is the same as new number, return
                if(currentNumber == newNumber){
                    System.out.println("Question number is not changed.");
                    return;
                }

                //if the new number is valid
                else if(newNumber <= quiz.size() && newNumber > 0){                    
                    //if current number lesser than new number (move backwards)                
                    if(currentNumber < newNumber){
                        //set the old new number Question to move forward by 1
                        oldkey.setQuestionNumber(oldkey.getQuestionNumber()-1);
                        for(Question i: quiz.keySet()){
                            //set all the Question greater than current number and lesser than new number forward by 1
                            if(i.getQuestionNumber() < newNumber && i.getQuestionNumber()>currentNumber && i!=oldkey){
                                i.setQuestionNumber(i.getQuestionNumber()-1);
                            }
                        }
                    }
                    else{//current number greater than new number (move forwards)
                        //set the old new number Question to move backwards by 1
                        oldkey.setQuestionNumber(oldkey.getQuestionNumber()+1);
                        for(Question i: quiz.keySet()){
                            //set all the Question greater than new number and lesser than current number backwards by 1
                            if(i.getQuestionNumber() > newNumber && i.getQuestionNumber()<currentNumber && i!=oldkey){
                                i.setQuestionNumber(i.getQuestionNumber()+1);
                            }
                        }
                    }

                    //set the want to change Question to new number
                    currentkey.setQuestionNumber(newNumber);
                    System.out.println("Question number has been changed successfully.");
                }

                //if new number is not valid, return
                else if(newNumber > quiz.size() || newNumber < 0){
                    System.out.println("The number is out of list");
                    return;
                }
                return;
            }
            //if ID is not valid, return
            System.out.println("ID invalid. Please try again.");

        }
    }


    public void printQuestions(){
        //if hashmap is empty, then print the error message
        if(quiz.isEmpty()){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else{
            System.out.println("\nYour current quiz: ");

            for(int i=1; i<=quiz.size(); i++){ //to print the Questions in order
                for(Question key: quiz.keySet()){ //to access all the Questions in the key set of hashmap
                    //if the question number is the same as the current print order
                    if(key.getQuestionNumber() == i){
                        //print all the attributes of the Question
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
        //if hashmap is empty, then print the error message
        if(quiz.isEmpty()){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else{
            //ask user to input the string that they want to search
            System.out.print("Search for string: ");
            String str = scanner.nextLine();

            //to track whether there exists a search result
            boolean track = false;

            //temporary array list to store all the keys (Questions)
            ArrayList<Question> al = new ArrayList<>();
            al.addAll(quiz.keySet());
            //sort the array list based on the question number
            quickSort(al, 0, al.size()-1);

            for(Question i:al){
                //get the question and answer
                String question = i.getQuestion();
                String answer = i.getCorrectAnswer();

                //if the question or answer contains the searched string
                if(question.contains(str) || answer.contains(str)){
                    //print information of the Question
                    System.out.println("Question " + i.getQuestionNumber());
                    System.out.println("Question ID: "+ i.getQuestionID());
                    System.out.println("Question: "+ i.getQuestion());
                    System.out.println("Answer: "+ i.getCorrectAnswer());
                    System.out.println();
                    track = true; //set the track to true
                }
            }             

            //if track is true, return
            if(track){
                return;
            }
            //if track is false (no search result), then print the error message
            else{
                System.out.println("No question or answer with such string.");
            }

        }
    }


    public static void main(String[] args) {
        //setting up the demo hashmap
        HashMapDemo demo = new HashMapDemo();

        while(true){
            //print the menu
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
            
            //ask user to input the command
            System.out.print("Please enter a command: ");            
            String command = scanner.nextLine();
            command = command.toUpperCase();

            switch (command) {
                //add question
                case "A":
                    demo.addQuestion();
                    break;
                //delete question
                case "D":
                    demo.deleteQuestion();
                    break;
                //edit question
                case "E":
                    demo.editQuestion();
                    break;
                //change the question order
                case "C":
                    demo.changeOrder();
                    break;
                //print all questions
                case "P":
                    demo.printQuestions();
                    break;
                //search question that contains some strings
                case "S":
                    demo.questionSearch();
                    break;
                //quit program
                case "Q":
                    return;
                //invalid command
                default:
                    System.out.println("Invalid command. Please try again.");
                    break;
            }
        }


    

    }
}


