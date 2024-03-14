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
            }
            
            quiz.setQuestionNumberForward(quiz.getRoot(), number);
            // System.out.println("amount:" + nodeCount);
            // System.out.println(quiz.toString());
            System.out.println("Deleted successfully.");
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
    

    public void changeOrder(){
        if (nodeCount == 0){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else{
            printQuestions();
            System.out.print("What question ID do you want to change the order? ");
            String answer = scanner.nextLine();
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
                    TreeNode nodeAfter = quiz.searchNodeBasedonNumber(quiz.getRoot(), newNumber);
                    int ori = want_to_change.data.getQuestionNumber();
                    //forward
                    quiz.resetQuestionNumber(quiz.getRoot(), want_to_change, nodeAfter, ori, newNumber);

                    //backward
                    if (ori < newNumber) {
                        Question temp1 = quiz.searchNode(want_to_change.data.getQuestionID()).data;
                        quiz.setQuestionNumberForward(quiz.getRoot(), ori);
                        quiz.deleteNode(want_to_change.data.getQuestionID());

                        quiz.setQuestionNumberBackward(quiz.getRoot(), newNumber-1);
                        temp1.setQuestionNumber(newNumber);
                        quiz.insertNode(temp1);
                    }
                    System.out.println("Changed successfully");
                }

            }


        }
    }


    public void printQuestions(){
        if (nodeCount == 0){
            System.out.println("Your quiz is empty.");
        }else{
            System.out.println("Your current quiz: ");
            quiz.printFullNode(quiz.getRoot(), nodeCount);
        }
    }


    public void questionSearch(){
        if(nodeCount == 0){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else{
            System.out.print("Search for question ID: ");
            String answer = scanner.nextLine();

            TreeNode result = quiz.searchNode(answer);

            if (result == null){
                System.out.println("ID invalid. Please try again.");
                return;
            }else{
                System.out.println("Question " + result.data.getQuestionNumber());
                System.out.println("Question ID: "+ result.data.getQuestionID());
                System.out.println("Question: "+ result.data.getQuestion());
                System.out.println("Answer: "+ result.data.getCorrectAnswer());
                System.out.println();
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




