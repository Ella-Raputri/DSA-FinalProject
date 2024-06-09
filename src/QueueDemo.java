//import APIs
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class QueueDemo {

    //initialize a queue that was implemented using linked list
    Queue<Question> quiz = new LinkedList<Question>();
    //scanner for user input
    static Scanner scanner = new Scanner(System.in);

    public Question getQuestionfromID(String id){   
        //check for all Questions, if the question ID is the same
        for(Question i: quiz){
            if(i.getQuestionID().equals(id)){
                //then return the Question object
                return i;
            }
        }
        return null;//if there is no such Question
    }


    public void resetNumber(){
        int tracker = 1;
        //reset the question number in order
        for(Question i: quiz){
            i.setQuestionNumber(tracker);
            tracker++;
        }
    }


    public void addQuestion(){
        //ask user to input the question and answer
        System.out.print("Enter question: ");
        String question = scanner.nextLine();
        System.out.print("Enter correct answer: ");
        String correctAnswer = scanner.nextLine();
        
        //create a new Question object based on the inputted question and answer
        Question q1 = new Question(correctAnswer, question);
        //add the Question to the queue
        quiz.add(q1);
        //set the ID number of the Question
        q1.setNumberID(quiz.size());
        System.out.println("Added successfully");
    }


    public void deleteQuestion(){
        //if the queue is empty then print the error message
        if(quiz.isEmpty()){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else{
            //print all the questions
            printQuestions();
            //ask user to input the Question that they want to delete
            System.out.print("What question ID do you want to delete? ");
            String id = scanner.nextLine();

            Question i = getQuestionfromID(id);
            //if there exists such a Question
            if(i!= null){
                //remove or delete the Question
                quiz.remove(i);
                System.out.println("Deleted successfully.");
                //reset all the question number
                resetNumber();
                return;
            }                    
            //if the ID is not valid, print the error message
            System.out.println("ID invalid. Please try again.");
        }
    }


    public void editQuestion(){
        //if the queue is empty then print the error message
        if(quiz.isEmpty()){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else{
            //print all the Questions
            printQuestions();
            //ask user to choose which Question that they want to edit
            System.out.print("What question ID do you want to edit? ");
            String id = scanner.nextLine();

            Question i = getQuestionfromID(id);
            //if there exists such a Question
            if(i != null){
                //display current question and answer
                System.out.println("Current question: " + i.getQuestion());
                System.out.println("Current answer: " + i.getCorrectAnswer());

                //ask user whether they want to change the question or not
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
                //if the input is invalid, return
                else if(!(questionChange.equals("y")) && !(questionChange.equals("n"))){
                    System.out.println("Invalid input.");
                    return;
                }

                //ask user whether they want to change the answer or not
                System.out.print("Do you want to change the answer? (Y/N) ");
                String answerChange = scanner.nextLine();
                answerChange = answerChange.toLowerCase();

                if(answerChange.equals("y")){
                    //ask user to input the new answer
                    System.out.print("New Answer: ");
                    String newAnswer = scanner.nextLine();
                    //set the answer based on the inputted answer
                    i.setCorrectAnswer(newAnswer);
                    System.out.println("Answer has been changed successfully.");
                }
                //if the input is invalid, return
                else if(!(questionChange.equals("y")) && !(questionChange.equals("n"))){
                    System.out.println("Invalid input.");
                    return;
                }
                
                System.out.println("Returning to the main menu...");
                return;
            }
            //if the ID is not valid, print the error message
            System.out.println("ID invalid. Please try again.");
        }
    }


    public void changeOrder(){
        //if the queue is empty then print the error message
        if(quiz.isEmpty()){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else{
            //print all the questions
            printQuestions();
            //ask user to input question that they want to change order
            System.out.print("What question ID do you want to change the order? ");
            String id = scanner.nextLine();

            Question i = getQuestionfromID(id);

            //if there exists such a Question
            if(i!=null){
                //display current number
                System.out.println("Current question number: " + i.getQuestionNumber());

                //ask user for new number
                System.out.print("Change to question number: ");
                int newNumber = scanner.nextInt();
                scanner.nextLine();
                
                //if the old number and new number are the same, return
                if(i.getQuestionNumber() == newNumber){
                    System.out.println("Question number is not changed.");
                    return;
                }

                //if the new number is valid
                else if(newNumber <= quiz.size() && newNumber > 0){
                    //get current number
                    int currentNumber = i.getQuestionNumber();

                    //add all the Question in the queue to a temporary ArrayList
                    ArrayList<Question> temp = new ArrayList<>();
                    temp.addAll(quiz);
                    //clear the queue
                    quiz.clear();

                    for(Question question:temp){
                        //if the Question is not the one that want to change
                        if(!(question.equals(i))){
                            //if that Question has the number of new number
                            if(temp.indexOf(question)+1 == newNumber){
                                //if new number is greater than current number, then
                                if(currentNumber < newNumber){
                                    //add that Question first before the want to change one
                                    quiz.add(question);
                                    quiz.add(i);
                                }
                                else if (currentNumber > newNumber){
                                    //else, add the want to change Question first
                                    quiz.add(i);
                                    quiz.add(question);
                                }  
                            }
                            //if the Question doesn't have the number of new number
                            else{
                                //then simply add it to the queue
                                quiz.add(question);
                            } 
                        }
                    }

                    //reset all the Question number based on their order
                    resetNumber();
                    System.out.println("Question number has been changed successfully.");
                }

                //if new number is not valid, return
                else if(newNumber > quiz.size() || newNumber < 0) {
                    System.out.println("The number is out of list");
                    return;
                }

                return;
            }
            //if the ID is not valid, return
            System.out.println("ID invalid. Please try again.");
        }
    }


    public void printQuestions(){
        //if the queue is empty then print the error message
        if(quiz.isEmpty()){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else{
            System.out.println("\nYour current quiz: ");
            //print all the attributes for every Question in the queue
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
        //if the queue is empty then print the error message
        if(quiz.isEmpty()){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else{
            //ask user for the string to be searched
            System.out.print("Search for string or question ID: ");
            String str = scanner.nextLine();

            //variable to track whether there exists a result for the search
            boolean track = false;
            
            for(Question i: quiz){
                //get question and answer of the Question
                String question = i.getQuestion();
                String answer = i.getCorrectAnswer();

                //if the question or answer contains the searched string
                if(question.contains(str) || answer.contains(str) || i.getQuestionID().equals(str)){
                    //print the information
                    System.out.println("Question " + i.getQuestionNumber());
                    System.out.println("Question ID: "+ i.getQuestionID());
                    System.out.println("Question: "+ i.getQuestion());
                    System.out.println("Answer: "+ i.getCorrectAnswer());
                    System.out.println();
                    //set the track to true
                    track = true;
                }
            }

            //if the track is true, return
            if(track){
                return;
            }
            //if track is false (no search result), print error message
            else{
                System.out.println("No question or answer with such string.");
            }

        }
    }


    public static void main(String[] args) {
        //set up the demo queue
        QueueDemo demo = new QueueDemo();

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
            
            //ask user to input their desired command
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
                //search a particular string
                case "S":
                    demo.questionSearch();
                    break;
                //quit the program
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
