import java.util.LinkedList;
import java.util.Scanner;

public class TreeRedBlackDemo {

    TreeRedBlack quiz = new TreeRedBlack();
    static Scanner scanner = new Scanner(System.in);
    static int nodeCount = 0;

    public void addQuestion(){
        System.out.print("Enter question: ");
        String question = scanner.nextLine();

        System.out.print("Enter correct answer: ");
        String correctAnswer = scanner.nextLine();
        
        Question q1 = new Question(correctAnswer, question);
        q1.setNumberID(nodeCount+1);
        quiz.insertNode(q1);

        nodeCount += 1;
        // System.out.println("amount:"+nodeCount);
        // System.out.println(quiz.toString());

        System.out.println("Question number: "+q1.getQuestionNumber()+" question Id: "+q1.getQuestionID());
        System.out.println("Added successfully");
    }


    public void deleteQuestion(){
        if(nodeCount == 0){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else{
            printQuestions();
            System.out.print("What question ID do you want to delete? ");
            String answer = scanner.nextLine();

            int number = quiz.getQuestionNumberfromNode(answer);
            quiz.deleteNode(answer);

            if (number > 0) {
               nodeCount -= 1; 
               System.out.println("Deleted successfully.");
            }
            quiz.inOrderBackward(quiz.getRoot(), nodeCount+2, number);
            //System.out.println("amount:" + nodeCount);
            // System.out.println(quiz.toString());
            
        }
    }


    public void editQuestion(){
        if(nodeCount == 0){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else{
            printQuestions();
            System.out.print("What question ID do you want to edit? ");
            String answer = scanner.nextLine();

            TreeNode result = quiz.searchNode(answer);
            if (result != null){
                System.out.println("Current question: "+result.data.getQuestion());
                System.out.println("Current answer: "+result.data.getCorrectAnswer());

                //chanhe question
                System.out.print("Do you want to change the question? (Y/N) ");
                String questionChange = scanner.nextLine();
                questionChange = questionChange.toLowerCase();

                if(questionChange.equals("y")){
                    System.out.print("New Question: ");
                    String newQuestion = scanner.nextLine();
                    result.data.setQuestion(newQuestion);
                    System.out.println("Question has been changed successfully.");
                }
                else if(!(questionChange.equals("y")) && !(questionChange.equals("n"))){
                    System.out.println("Invalid input.");
                    return;
                }

                //change answer
                System.out.print("Do you want to change the answer? (Y/N) ");
                String answerChange = scanner.nextLine();
                answerChange = answerChange.toLowerCase();

                if(answerChange.equals("y")){
                    System.out.print("New Answer: ");
                    String newAnswer = scanner.nextLine();
                    result.data.setCorrectAnswer(newAnswer);
                    System.out.println("Answer has been changed successfully.");
                }
                else if(!(answerChange.equals("y")) && !(answerChange.equals("n"))){
                    System.out.println("Invalid input.");
                    return;
                }
                
                System.out.println("Returning to the main menu...");
                return;

            }else{ //if result is null, then there is no such node
                System.out.println("ID invalid. Please try again.");
            }
        }
    }
    

    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public void changeOrder(){
        if (nodeCount == 0){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else{
            printQuestions();
            System.out.print("What question ID do you want to change the order? ");
            String answer = scanner.nextLine();
            if(!isNumeric(answer)){
                System.out.println("Invalid. Please try again.");
                return;
            }
            TreeNode want_to_change = quiz.searchNode(answer);

            if(want_to_change != null){
                System.out.println("Current question number: " + want_to_change.data.getQuestionNumber());

                System.out.print("Change to question number: ");
                int newNumber = scanner.nextInt();
                scanner.nextLine();
                
                if(want_to_change.data.getQuestionNumber() == newNumber){
                    System.out.println("Question number is not changed.");
                    return;

                }else if(newNumber <= nodeCount && newNumber > 0){
                    //search for the original one
                    int ori = want_to_change.data.getQuestionNumber();
                    //forward
                    if (ori > newNumber){
                        quiz.inOrderForward(quiz.getRoot(), newNumber-1, ori);
                        want_to_change.data.setQuestionNumber(newNumber);
                    }

                    //backward
                    if (ori < newNumber) {
                        quiz.inOrderBackward(quiz.getRoot(), newNumber+1, ori);
                        want_to_change.data.setQuestionNumber(newNumber);
                    }
                    System.out.println("Changed successfully");
                }

            }else{
                System.out.println("Invalid. Please try again.");
            }


        }
    }


    public void printQuestions(){
        if (nodeCount == 0){
            System.out.println("Your quiz is empty.");
        }else{
            System.out.println("Your current quiz: ");
            quiz.printFullNode(quiz.getRoot(), nodeCount);
            // System.out.println(quiz.toString());
            // quiz.inOrder(quiz.getRoot());
        }
    }


    public LinkedList<TreeNode> bubbleSort(LinkedList<TreeNode> al){
        int i, j;
        boolean swapped;

        for (i = 0; i < al.size() - 1; i++) {
            swapped = false;
            for (j = 0; j < al.size() - i - 1; j++) {
                if (al.get(j).data.getQuestionNumber() > al.get(j+1).data.getQuestionNumber()) {
                    TreeNode temp = al.get(j);
                    al.set(j, al.get(j+1));
                    al.set(j+1, temp);
                    // Collections.swap(al, j, j+1);
                    swapped = true;
                }
            }
 
            if (swapped == false){
                break;
            }
        }
        return al;
    }

    public void questionSearch(){
        if(nodeCount == 0){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else{
            System.out.print("Search for string: ");
            String answer = scanner.nextLine();

            LinkedList<TreeNode> results = new LinkedList<TreeNode>();
            results = quiz.searchNodeString(quiz.getRoot(), answer, results);
            results = bubbleSort(results);

           // System.out.println(results);
            if (results == null || results.size() == 0){
                System.out.println("Invalid. Please try again.");
                return;
            }else{
                for (TreeNode result : results){
                    System.out.println("Question " + result.data.getQuestionNumber());
                    System.out.println("Question ID: "+ result.data.getQuestionID());
                    System.out.println("Question: "+ result.data.getQuestion());
                    System.out.println("Answer: "+ result.data.getCorrectAnswer());
                    System.out.println();                 
                }
                return;
            }

        }
    }


    public static void main(String[] args) {
         
        TreeRedBlackDemo demo = new TreeRedBlackDemo();

        while(true){
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




