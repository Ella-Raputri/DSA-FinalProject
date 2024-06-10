import java.util.LinkedList;
import java.util.Scanner;

public class TreeRedBlackDemo {

    //initialize a red black tree to save the Questions
    TreeRedBlack quiz = new TreeRedBlack();
    //static scanner for user input
    static Scanner scanner = new Scanner(System.in);
    //total nodes in the tree
    static int nodeCount = 0;

    public void addQuestion(){
        //prompt user to input the question and answer
        System.out.print("Enter question: ");
        String question = scanner.nextLine();
        System.out.print("Enter correct answer: ");
        String correctAnswer = scanner.nextLine();
        
        //create a new Question based on the answer and question
        Question q1 = new Question(correctAnswer, question);
        //set the ID number of the Question
        q1.setNumberID(nodeCount+1);
        //insert the Question as data of the node into the tree
        quiz.insertNode(q1);

        //increment the node count
        nodeCount += 1;
        System.out.println("Question number: "+q1.getQuestionNumber()+" question Id: "+q1.getQuestionID());
        System.out.println("Added successfully");
    }


    public void deleteQuestion(){
        //if the tree is empty, then print the error message
        if(nodeCount == 0){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else{
            //print all the Questions
            printQuestions();
            //ask user to choose which Question to delete
            System.out.print("What question ID do you want to delete? ");
            String answer = scanner.nextLine();

            //get the current question number of the to be deleted node
            int number = quiz.getQuestionNumberfromNode(answer);
            //delete the node
            quiz.deleteNode(answer);

            //decrement the node count
            if (number > 0) {
               nodeCount -= 1; 
               System.out.println("Deleted successfully.");
            }
            //set the question number of all other nodes
            quiz.inOrderBackward(quiz.getRoot(), nodeCount+2, number);
        }
    }


    public void editQuestion(){
        //if the tree is empty, then print the error message
        if(nodeCount == 0){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else{
            //print all the Questions
            printQuestions();
            //ask user to choose which Question to edit
            System.out.print("What question ID do you want to edit? ");
            String answer = scanner.nextLine();

            //search node based on the given ID
            TreeNode result = quiz.searchNode(answer);
            //if there exists such a node
            if (result != null){
                //display current question and answer
                System.out.println("Current question: "+result.data.getQuestion());
                System.out.println("Current answer: "+result.data.getCorrectAnswer());

                //ask user whether they want to change the question or not
                System.out.print("Do you want to change the question? (Y/N) ");
                String questionChange = scanner.nextLine();
                questionChange = questionChange.toLowerCase();

                //if user wants to change the Question
                if(questionChange.equals("y")){
                    //ask user to input new question
                    System.out.print("New Question: ");
                    String newQuestion = scanner.nextLine();

                    //set the Question based on the new question
                    result.data.setQuestion(newQuestion);
                    System.out.println("Question has been changed successfully.");
                }
                //invalid command
                else if(!(questionChange.equals("y")) && !(questionChange.equals("n"))){
                    System.out.println("Invalid input.");
                    return;
                }

                //ask user whether they want to change the answer
                System.out.print("Do you want to change the answer? (Y/N) ");
                String answerChange = scanner.nextLine();
                answerChange = answerChange.toLowerCase();

                //if user wants to change the answer
                if(answerChange.equals("y")){
                    //ask user to input the new answer
                    System.out.print("New Answer: ");
                    String newAnswer = scanner.nextLine();
                    
                    //set the answer based on the new answer
                    result.data.setCorrectAnswer(newAnswer);
                    System.out.println("Answer has been changed successfully.");
                }
                //invalid command
                else if(!(answerChange.equals("y")) && !(answerChange.equals("n"))){
                    System.out.println("Invalid input.");
                    return;
                }
                
                System.out.println("Returning to the main menu...");
                return;

            }else{ //if ID is not valid, print error message
                System.out.println("ID invalid. Please try again.");
            }
        }
    }


    public void changeOrder(){
        //if the tree is empty, then print the error message
        if (nodeCount == 0){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else{
            //print all Questions
            printQuestions();
            //ask user which Question they want to change order
            System.out.print("What question ID do you want to change the order? ");
            String answer = scanner.nextLine();
            
            //if the new number is not valid, then return
            if(!quiz.isNumeric(answer)){
                System.out.println("Invalid. Please try again.");
                return;
            }
            //search the want to change node based on the given ID
            TreeNode want_to_change = quiz.searchNode(answer);

            //if the want to change node is not null
            if(want_to_change != null){
                //display current question number
                System.out.println("Current question number: " + want_to_change.data.getQuestionNumber());

                //ask user for the new number
                System.out.print("Change to question number: ");
                int newNumber = scanner.nextInt();
                scanner.nextLine();
                
                //if the old number is the same as the new number, then return              
                if(want_to_change.data.getQuestionNumber() == newNumber){
                    System.out.println("Question number is not changed.");
                    return;

                //if the new number is valid
                }else if(newNumber <= nodeCount && newNumber > 0){
                    //search for the original one
                    int ori = want_to_change.data.getQuestionNumber();
                    //forward case
                    if (ori > newNumber){
                        //set the question number
                        quiz.inOrderForward(quiz.getRoot(), newNumber-1, ori);
                        want_to_change.data.setQuestionNumber(newNumber);
                    }

                    //backward case
                    if (ori < newNumber) {
                        //set the question number
                        quiz.inOrderBackward(quiz.getRoot(), newNumber+1, ori);
                        want_to_change.data.setQuestionNumber(newNumber);
                    }
                    System.out.println("Changed successfully");
                }

            }else{
                //invalid
                System.out.println("Invalid. Please try again.");
            }


        }
    }


    public void printQuestions(){
        //if the tree is empty, then print the error message
        if (nodeCount == 0){
            System.out.println("Your quiz is empty.");
        }else{
            //print all nodes in the tree
            System.out.println("Your current quiz: ");
            quiz.printFullNode(quiz.getRoot(), nodeCount);
        }
    }


    public void questionSearch(){
        //if the tree is empty, then print the error message
        if(nodeCount == 0){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else{
            //ask user to input the string they want to search
            System.out.print("Search for string or question ID: ");
            String answer = scanner.nextLine();
            
            //search the whether there is a node with the ID of user input
            TreeNode IDNode = quiz.searchNode(answer);

            //if not, then it is possible that the user input is the substring
            if (IDNode == null){
                //linked list to save the results
                LinkedList<TreeNode> results = new LinkedList<TreeNode>();
                //search the tree with the string
                results = quiz.searchNodeString(quiz.getRoot(), answer, results);
                //sort the results based on the question number
                quiz.quickSort(results, 0, results.size()-1);

                //if there is no result, then return
                if (results == null || results.size() == 0){
                    System.out.println("No result found. Please try again.");
                    return;
                }else{
                    //for every treenode inside the results linked list
                    for (TreeNode result : results){
                        //print the information of the node data
                        System.out.println("Question " + result.data.getQuestionNumber());
                        System.out.println("Question ID: "+ result.data.getQuestionID());
                        System.out.println("Question: "+ result.data.getQuestion());
                        System.out.println("Answer: "+ result.data.getCorrectAnswer());
                        System.out.println();                 
                    }
                    return;
                }

            }else{
                //print the node information with the corresponding ID
                System.out.println("Question " + IDNode.data.getQuestionNumber());
                System.out.println("Question ID: "+ IDNode.data.getQuestionID());
                System.out.println("Question: "+ IDNode.data.getQuestion());
                System.out.println("Answer: "+ IDNode.data.getCorrectAnswer());
                System.out.println(); 
                return;
            } 
        }
    }


    public static void main(String[] args) {
         //set up the demo
        TreeRedBlackDemo demo = new TreeRedBlackDemo();

        while(true){
            //print the menu
            System.out.println("\n************************************");
            System.out.println("Question Editing");
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
                //print all Questions
                case "P":
                    demo.printQuestions();
                    break;
                //search a string
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




