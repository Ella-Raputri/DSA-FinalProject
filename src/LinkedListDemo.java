//import scanner
import java.util.Scanner;

public class LinkedListDemo {
    //static scanner for user input
    static Scanner scanner = new Scanner(System.in);
    //linked list for the Questions
    Linkedlist quiz = new Linkedlist();

    public void addQuestion(){  
        //ask user to input the question and answer    
        System.out.print("Enter question: ");
        String question = scanner.nextLine();
        System.out.print("Enter correct answer: ");
        String correctAnswer = scanner.nextLine();
        
        //create a new Question object based on the given question and answer   
        Question q1 = new Question(correctAnswer, question);
        //add the Question object to the linked list
        quiz.addNode(q1);

        //set the ID number of the Question
        int tracker = 1;
        q1.setNumberID(tracker);
        //reset all the question number
        quiz.resetNumber();
        System.out.println("Added successfully");
        tracker++;
    }


    public void deleteQuestion(){
        //if linked list is empty, print the error message
        if(quiz.isEmpty()){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else{
            //print all the Questions
            printQuestions();
            //ask user which Question they want to delete
            System.out.print("What question ID do you want to delete? ");
            String id = scanner.nextLine();

            //get the node that want to be deleted
            Linkedlist.Node current = quiz.getNode(id);

            //if there exists such a node
            if(current!= null){
                //delete that node and reset the question number
                quiz.deleteNode(current);
                System.out.println("Deleted successfully.");
                quiz.resetNumber();
                return;
            }
            //if the ID is not valid, return
            System.out.println("ID invalid. Please try again.");
            
        }
    }


    public void editQuestion(){
        //if linked list is empty, print the error message
        if(quiz.isEmpty()){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else{
            //print all Questions
            printQuestions();
            //ask user for question that they want to edit
            System.out.print("What question ID do you want to edit? ");
            String id = scanner.nextLine();

            //get the want to be edited node
            Linkedlist.Node current = quiz.getNode(id);
            //if there exists such a node
            if (current != null){
                //print the current question and answer of the node
                System.out.println("Current question: " + current.data.getQuestion());
                System.out.println("Current answer: " + current.data.getCorrectAnswer());
                
                //ask user whether they want to change the question or not
                System.out.print("Do you want to change the question? (Y/N) ");
                String questionChange = scanner.nextLine();
                questionChange = questionChange.toLowerCase();
    
                //if user wants to change the question
                if(questionChange.equals("y")){
                    //ask user for the new question
                    System.out.print("New Question: ");
                    String newQuestion = scanner.nextLine();
                    
                    //set the question based on the new question
                    current.data.setQuestion(newQuestion);
                    System.out.println("Question has been changed successfully.");
                }
                //invalid user input
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
                    //ask user for the new answer
                    System.out.print("New Answer: ");
                    String newAnswer = scanner.nextLine();

                    //set the answer based on the new answer
                    current.data.setCorrectAnswer(newAnswer);
                    System.out.println("Answer has been changed successfully.");
                }
                //invalid user input
                else if(!(questionChange.equals("y")) && !(questionChange.equals("n"))){
                    System.out.println("Invalid input.");
                    return;
                }
    
                System.out.println("Returning to the main menu...");
                return;
            }
            //if ID is not valid, then return
            System.out.println("ID invalid. Please try again.");

        }
    }


    public void changeOrder(){
        //if linked list is empty, print the error message
        if(quiz.isEmpty()){
            System.out.println("Your current quiz is empty.");
            return;
        }
        //if there is only one node, return
        else if(quiz.head.equals(quiz.tail)){
            System.out.println("Your current quiz only contains one number.");
            return;
        }
        else{
            //print all questions
            printQuestions();
            //ask the user to choose which Question they want to change order
            System.out.print("What question ID do you want to change the order? ");
            String id = scanner.nextLine();

            //get the node that its order want to be changed 
            Linkedlist.Node current = quiz.getNode(id);
            //if there exists such a node
            if(current != null){
                //get the node's current number
                int currentNumber = current.data.getQuestionNumber();

                //display the current number
                System.out.println("Current question number: " + current.data.getQuestionNumber());
                //ask user for the new number
                System.out.print("Change to question number: ");
                int newNumber = scanner.nextInt();
                scanner.nextLine();

                //if the node's number is the same as the new number, return
                if(currentNumber == newNumber){
                    System.out.println("Question number is not changed.");
                    return;
                }

                //if the new number is valid
                else if(newNumber <= Linkedlist.nodeAmount && newNumber > 0){
                    //change the node order and reset all of the question number
                    quiz.changeNodeOrder(newNumber, current, currentNumber);
                    quiz.resetNumber();
                    System.out.println("Question number has been changed successfully.");
                }

                //if new number is not valid, return
                else if(newNumber > Linkedlist.nodeAmount || newNumber < 0){
                    System.out.println("The number is out of list");
                    return;
            }

            return;
        }
        //if the ID is not valid, return
        System.out.println("Invalid ID number. Please try again.");

        }
    }


    public void printQuestions(){
        //if linked list is empty, print the error message
        if(quiz.isEmpty()){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else{
            //set the current node to be the head
            Linkedlist.Node current = quiz.head;
            System.out.println("\nYour current quiz: ");
            
            //print all of the nodes information
            while(current!=null){
                System.out.println("Question " + current.data.getQuestionNumber());
                System.out.println("Question ID: "+ current.data.getQuestionID());
                System.out.println("Question: "+ current.data.getQuestion());
                System.out.println("Answer: "+ current.data.getCorrectAnswer());
                System.out.println();
                current = current.next; //proceed to the next node
            }
            
        }
    }


    public void questionSearch(){
        //if linked list is empty, print the error message
        if(quiz.isEmpty()){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else{
            //ask user to input the string they want to search
            System.out.print("Search for string: ");
            String str = scanner.nextLine();
            //to track whether exists a search result
            boolean track = false;

            //start the search from the head node
            Linkedlist.Node current = quiz.head;

            while(current!=null){
                //get the current node question and answer
                String question = current.data.getQuestion();
                String answer = current.data.getCorrectAnswer();

                //if the question or answer contains the searched string
                if(question.contains(str) || answer.contains(str)){
                    //print the information of the current node data
                    System.out.println("Question " + current.data.getQuestionNumber());
                    System.out.println("Question ID: "+ current.data.getQuestionID());
                    System.out.println("Question: "+ current.data.getQuestion());
                    System.out.println("Answer: "+ current.data.getCorrectAnswer());
                    System.out.println();
                    track = true; //set the track to true
                }
                //proceed to the next node
                current = current.next;
            }
            
            //if track is true, then return
            if(track){
                return;
            }
            //if track is false (no result), then print the error message
            else{
                System.out.println("No question or answer with such string.");
            }
        }
    }


    public static void main(String[] args) {
        //set up the demo
        LinkedListDemo demo = new LinkedListDemo();

        while(true){
            //print the menus
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
                //search a string
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
