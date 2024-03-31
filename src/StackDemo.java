//import Java APIs
import java.util.Stack;
import java.util.Scanner;

public class StackDemo {
    //initialise a stack for the Questions
    Stack<Question> quiz = new Stack<Question>();
    //scanner for user input
    static Scanner scanner = new Scanner(System.in);

    public Question getQuestionfromID(String id){   
        for(Question i: quiz){
            //check for all Questions in the stack
            //if its ID is the same as the searched one
            if(i.getQuestionID().equals(id)){
                //return the Question
                return i; 
            }
        }
        return null; //if no Question with the same ID is found
    }


    public void resetNumber(){
        int tracker = 1;
        //for all Questions in the stack, reset the number
        for(Question i: quiz){
            //renumber it based on the order 
            i.setQuestionNumber(tracker);
            tracker++;
        }
    }


    public void addQuestion(){
        //prompt user to inputhe question and correct answer
        System.out.print("Enter question: ");
        String question = scanner.nextLine();
        System.out.print("Enter correct answer: ");
        String correctAnswer = scanner.nextLine();
        
        //create a new Question object based on the given question and answer
        Question q1 = new Question(correctAnswer, question);
        //add the Question to the stack
        quiz.add(q1);
        //set the ID number of the Question
        q1.setNumberID(quiz.indexOf(q1)+1);
        System.out.println("Added successfully");
    }


    public void deleteQuestion(){
        //if the stack is empty then print the error message
        if(quiz.isEmpty()){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else{
            //print all the Questions
            printQuestions();
            //ask user for which Question they want to delete
            System.out.print("What question ID do you want to delete? ");
            String id = scanner.nextLine();

            Question i = getQuestionfromID(id);
            //if there exists such a Question
            if(i!= null){
                //remove that Question
                quiz.remove(i);
                System.out.println("Deleted successfully.");
                //reset all the Question number
                resetNumber();
                return;
            }                    
            //if the ID is not valid, then print the error message
            System.out.println("ID invalid. Please try again.");
        }
    }


    public void editQuestion(){
        //if the stack is empty then print the error message
        if(quiz.isEmpty()){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else{
            //print all the Questions
            printQuestions();
            //ask user for which Question they want to edit
            System.out.print("What question ID do you want to edit? ");
            String id = scanner.nextLine();

            Question i = getQuestionfromID(id);
            //if there exists such a Question
            if(i != null){
                //display current question and answer
                System.out.println("Current question: " + i.getQuestion());
                System.out.println("Current answer: " + i.getCorrectAnswer());

                //ask user if they want to change the question or not
                System.out.print("Do you want to change the question? (Y/N) ");
                String questionChange = scanner.nextLine();
                questionChange = questionChange.toLowerCase();
                
                //if user wants to change the question
                if(questionChange.equals("y")){
                    //ask user to input the new question
                    System.out.print("New Question: ");
                    String newQuestion = scanner.nextLine();
                    //set the question based on the new question
                    i.setQuestion(newQuestion);
                    System.out.println("Question has been changed successfully.");
                }
                //if the input is invalid, then return
                else if(!(questionChange.equals("y")) && !(questionChange.equals("n"))){
                    System.out.println("Invalid input.");
                    return;
                }

                //ask user if they want to change the answer or not
                System.out.print("Do you want to change the answer? (Y/N) ");
                String answerChange = scanner.nextLine();
                answerChange = answerChange.toLowerCase();

                //if user wants to change the answer
                if(answerChange.equals("y")){
                    //ask user to input the new answer
                    System.out.print("New Answer: ");
                    String newAnswer = scanner.nextLine();
                    //set the answer based on the new given answer
                    i.setCorrectAnswer(newAnswer);
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
            //if the ID is invalid, return
            System.out.println("ID invalid. Please try again.");
        }
    }


    public void changeOrder(){
        //if the stack is empty then print the error message
        if(quiz.isEmpty()){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else{
            //print all the Questions
            printQuestions();
            //ask user for which Question they want to change its order
            System.out.print("What question ID do you want to change the order? ");
            String id = scanner.nextLine();

            Question i = getQuestionfromID(id);

            //if there exists such a question 
            if(i!=null){
                //display current question number
                System.out.println("Current question number: " + i.getQuestionNumber());

                //ask user for the new number
                System.out.print("Change to question number: ");
                int newNumber = scanner.nextInt();
                scanner.nextLine();
                
                //if the new number is the same as the current number, return
                if(i.getQuestionNumber() == newNumber){
                    System.out.println("Question number is not changed.");
                    return;
                }

                //if the new number is valid
                else if(newNumber <= quiz.size() && newNumber > 0){                    
                    //get the current number                   
                    int currentNumber = i.getQuestionNumber();
                    //if the current number is less than the new number
                    if(currentNumber < newNumber){
                        //insert the Question into the new number
                        quiz.insertElementAt(i, newNumber);
                        //remove the old Question
                        quiz.remove(currentNumber-1);
                    }
                    //if the current number is greater than the new number
                    else if (currentNumber > newNumber){
                        //insert the Question at new number -1
                        quiz.insertElementAt(i, newNumber-1);
                        //remove the old Question
                        quiz.remove(currentNumber);
                    }

                    //reset all the question number
                    resetNumber();
                    System.out.println("Question number has been changed successfully.");
                }

                //if new number is not valid, then return
                else if(newNumber > quiz.size() || newNumber < 0) {
                    System.out.println("The number is out of list");
                    return;
                }

                return;
            }
            //if the ID is not valid, then return
            System.out.println("ID invalid. Please try again.");
        }
    }


    public void printQuestions(){
        //if the stack is empty then print the error message
        if(quiz.isEmpty()){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else{
            System.out.println("\nYour current quiz: ");
            for(Question i: quiz){
                //print all the attributes of each Question
                System.out.println("Question " + i.getQuestionNumber());
                System.out.println("Question ID: "+ i.getQuestionID());
                System.out.println("Question: "+ i.getQuestion());
                System.out.println("Answer: "+ i.getCorrectAnswer());
                System.out.println();
            }
        }
    }


    public void questionSearch(){
        //if the stack is empty then print the error message
        if(quiz.isEmpty()){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else{
            //ask user to input string that they want to search
            System.out.print("Search for string: ");
            String str = scanner.nextLine();
            //variable to track whether exists a search result
            boolean track = false;
            
            for(Question i: quiz){
                //get the question and answer
                String question = i.getQuestion();
                String answer = i.getCorrectAnswer();

                //if the question or answer contains the searched string
                if(question.contains(str) || answer.contains(str)){
                    //print that Question attributes
                    System.out.println("Question " + i.getQuestionNumber());
                    System.out.println("Question ID: "+ i.getQuestionID());
                    System.out.println("Question: "+ i.getQuestion());
                    System.out.println("Answer: "+ i.getCorrectAnswer());
                    System.out.println();
                    track = true; //set track to true
                }
            }

            //if track is true then return
            if(track){
                return;
            }
            //if false, then there is no search result, print the error message
            else{
                System.out.println("No question or answer with such string.");
            }
        }
    }


    public static void main(String[] args) {
        //setting up a demo stack
        StackDemo demo = new StackDemo();

        while(true){
            //print the commands
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
            
            //ask user to input a command
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
                //change order
                case "C":
                    demo.changeOrder();
                    break;
                //print all questions
                case "P":
                    demo.printQuestions();
                    break;
                //search for a string in the questions or answer
                case "S":
                    demo.questionSearch();
                    break;
                //quit
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
