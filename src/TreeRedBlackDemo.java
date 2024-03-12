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
        System.out.println("amount:"+nodeCount);
        System.out.println(quiz.toString());

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
            nodeCount -= 1;
            quiz.setQuestionNumberfromNode(quiz.getRoot(), number);
        }
    }


    public void editQuestion(){
        System.out.println("edit");
    }


    public void changeOrder(){
        System.out.println("chanfe");
    }


    public void printQuestions(){
        System.out.println("print");
    }


    public void questionSearch(){
        System.out.println("search");
    }


    public static void main(String[] args) {
         
        TreeRedBlackDemo demo = new TreeRedBlackDemo();

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




