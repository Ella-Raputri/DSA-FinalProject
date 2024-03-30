//import ArrayList and Scanner API
import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListDemo {
    //Initialize a new ArrayList and scanner
    ArrayList<Question> quiz = new ArrayList<Question>();
    static Scanner scanner = new Scanner(System.in);

    public Question getQuestionfromID(String id){ 
        //get the question from the ID  
        for(Question i: quiz){
            //check for all questions in the ArrayList
            //if the ID is the same as the inputted one, then return the Question object
            if(i.getQuestionID().equals(id)){
                return i;
            }
        }
        //if there is no Question that matches the ID, then return null
        return null;
    }

    public void addQuestion(){
        //prompt user to input question
        System.out.print("Enter question: ");
        String question = scanner.nextLine();

        //prompt user to input answer
        System.out.print("Enter correct answer: ");
        String correctAnswer = scanner.nextLine();
        
        //initialize a new Question object based on the given question and correct answer
        Question q1 = new Question(correctAnswer, question);
        //add the Question to the ArrayList
        quiz.add(q1);
        //set the ID number for the Question
        q1.setNumberID(quiz.indexOf(q1)+1);
        System.out.println("Added successfully");
    }


    public void deleteQuestion(){
        //if ArrayList is empty, print the error message
        if(quiz.isEmpty()){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else{
            //print all the questions and prompt user to input the ID of the 
            //question that wants to be deleted
            printQuestions();
            System.out.print("What question ID do you want to delete? ");
            String id = scanner.nextLine();

            //get the Question based on the inputted ID
            Question i = getQuestionfromID(id);
            //if there exists such a Question
            if(i!= null){
                //remove that Question from ArrayList
                int j = quiz.indexOf(i);
                quiz.remove(i);
                System.out.println("Deleted successfully.");

                //reset all the Question number in ArrayList
                while(j < quiz.size()){
                    quiz.get(j).setQuestionNumber(j+1);
                    j++;
                }
                return;
            }                    
            //print error message if ID is invalid
            System.out.println("ID invalid. Please try again.");
        }
    }


    public void editQuestion(){
        //If ArrayList is empty, print error message
        if(quiz.isEmpty()){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else{
            //print all the questions and prompt user to input the ID of the 
            //question that wants to be edited    
            printQuestions();
            System.out.print("What question ID do you want to edit? ");
            String id = scanner.nextLine();

            //get the Question based on the inputted ID
            Question i = getQuestionfromID(id);
            //if there exists such a Question
            if(i != null){
                //print the current question and answer
                System.out.println("Current question: " + i.getQuestion());
                System.out.println("Current answer: " + i.getCorrectAnswer());

                //ask if the user wants to change the question or not
                System.out.print("Do you want to change the question? (Y/N) ");
                String questionChange = scanner.nextLine();
                questionChange = questionChange.toLowerCase();

                //if user wants to change the question, then set the new question
                if(questionChange.equals("y")){
                    System.out.print("New Question: ");
                    String newQuestion = scanner.nextLine();
                    i.setQuestion(newQuestion);
                    System.out.println("Question has been changed successfully.");
                }
                //if user inputs not the string 'y' or 'n', then return because it is invalid
                else if(!(questionChange.equals("y")) && !(questionChange.equals("n"))){
                    System.out.println("Invalid input.");
                    return;
                }

                //ask if the user wants to change the answer or nor
                System.out.print("Do you want to change the answer? (Y/N) ");
                String answerChange = scanner.nextLine();
                answerChange = answerChange.toLowerCase();

                //if user wants to change the answer, then set the new answer
                if(answerChange.equals("y")){
                    System.out.print("New Answer: ");
                    String newAnswer = scanner.nextLine();
                    i.setCorrectAnswer(newAnswer);
                    System.out.println("Answer has been changed successfully.");
                }
                //if user inputs not the string 'y' or 'n', then return because it is invalid
                else if(!(questionChange.equals("y")) && !(questionChange.equals("n"))){
                    System.out.println("Invalid input.");
                    return;
                }
                //print message that signals all process has been completed
                //and the system will return to the main menu
                System.out.println("Returning to the main menu...");
                return;
            }
            //print error message if ID is invalid
            System.out.println("ID invalid. Please try again.");
        }
    }


    public void changeOrder(){
        //If ArrayList is empty, print error message
        if(quiz.isEmpty()){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else{
            //print all the questions
            printQuestions();
            //ask the user for the question that they want to change the order
            System.out.print("What question ID do you want to change the order? ");
            String id = scanner.nextLine();

            //get Question object from inputted ID
            Question i = getQuestionfromID(id);

            //if there exists such a Question
            if(i!=null){
                System.out.println("Current question number: " + i.getQuestionNumber());

                //ask user for the new number for the question
                System.out.print("Change to question number: ");
                int newNumber = scanner.nextInt();
                scanner.nextLine();

                //if the new number that user wants to change is the same as old number, return
                if(i.getQuestionNumber() == newNumber){
                    System.out.println("Question number is not changed.");
                    return;
                }

                //if the new number is valid (lesser than the ArrayList size and greater than 0)
                else if(newNumber <= quiz.size() && newNumber > 0){
                    int indexOfi = quiz.indexOf(i); //index of the want to change question
                    int currentNumber = i.getQuestionNumber(); //number of the want to change question

                    //setting the new number
                    if(currentNumber > newNumber){
                        //if it is forward case or current number wants to move forward
                        //then add the want to change Question to index newNumber-1 
                        //and remove it from its old position 
                        quiz.add(newNumber-1, i);
                        quiz.remove(indexOfi+1);
                    }
                    else{
                        //if it is backward case
                        if(newNumber+1 > quiz.size()){
                            //if the new number is the last number in the ArrayList,
                            //then add the want to change Question to the last
                            quiz.add(i);
                        }
                        else{
                            //if new number is not the last number, then add
                            //the want to change Question to the desired position
                            quiz.add(newNumber, i);
                        }
                        //remove it from its old position
                        quiz.remove(indexOfi);
                    }

                    //resetting all the question number
                    for(int j=0; j<quiz.size(); j++){
                        quiz.get(j).setQuestionNumber(j+1);
                    }

                    System.out.println("Question number has been changed successfully.");
                }
                    
                else if(newNumber > quiz.size() || newNumber < 0) {
                //if new number is not valid, then return
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
        //If ArrayList is empty, print the error message
        if(quiz.isEmpty()){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else{
            //print all the Question attributes for each Question in the ArrayList
            System.out.println("\nYour current quiz: ");
            for(Question i: quiz){
                System.out.println("Question " + i.getQuestionNumber());
                System.out.println("Question ID: "+ i.getQuestionID());
                System.out.println("Question: "+ i.getQuestion());
                System.out.println("Answer: "+ i.getCorrectAnswer());
                System.out.println();
            }
        }
    }


    public void questionSearch(){
        //If ArrayList is empty, print the error message
        if(quiz.isEmpty()){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else{
            //prompt user to input a string to be searched
            System.out.print("Search for string: ");
            String str = scanner.nextLine();

            //variable to track whether exists a result for the search
            boolean track = false;
            
            for(Question i: quiz){
                //check all Question in the ArrayList
                String question = i.getQuestion();
                String answer = i.getCorrectAnswer();
                
                //if the question or correct answer contains the searched string, then
                //print the Question information out and set the track to true
                if(question.contains(str) || answer.contains(str)){
                    System.out.println("Question " + i.getQuestionNumber());
                    System.out.println("Question ID: "+ i.getQuestionID());
                    System.out.println("Question: "+ i.getQuestion());
                    System.out.println("Answer: "+ i.getCorrectAnswer());
                    System.out.println();
                    track = true;
                }
            }
            //if track is true, then return
            if(track){
                return;
            }
            //if false, then there is no result
            else{
                System.out.println("No question or answer with such string.");
            }
        }
    }


    public static void main(String[] args) {
        //Initialize a demo ArrayList
        ArrayListDemo demo = new ArrayListDemo();

        //while user doesn't exit the program
        while(true){
            //print the available command choices
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

            //prompt user to input their choice
            System.out.print("Please enter a command: ");
            String command = scanner.nextLine();
            command = command.toUpperCase();

            //case for each available option
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
                
                //change order of question
                case "C":
                    demo.changeOrder();
                    break;
                
                //print all questions
                case "P":
                    demo.printQuestions();
                    break;
                
                //search particular question
                case "S":
                    demo.questionSearch();
                    break;
                
                //quit
                case "Q":
                    return;

                //if the command is invalid, then it will print the error message and loop back
                //to ask for user input
                default:
                    System.out.println("Invalid command. Please try again.");
                    break;
            }
        }


    

    }
}
