import java.util.Scanner;

public class LinkedlistDemo {
    static Scanner scanner = new Scanner(System.in);
    Linkedlist quiz = new Linkedlist();

    public void addQuestion(){      
        System.out.print("Enter question: ");
        String question = scanner.nextLine();

        System.out.print("Enter correct answer: ");
        String correctAnswer = scanner.nextLine();
        
        Question q1 = new Question(correctAnswer, question);
        quiz.addNode(q1);

        int tracker = 1;
        q1.setNumberID(tracker);
        quiz.resetNumber();
        System.out.println("Added successfully");
        tracker++;
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

            quiz.deleteNode(id);
            quiz.resetNumber();
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

            quiz.editNode(id, scanner);
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
            quiz.changeNodeOrder(id, scanner);
        }
    }


    public void printQuestions(){
        if(quiz.isEmpty()){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else{
            quiz.printLinkedList();
        }
    }


    public void questionSearch(){
        if(quiz.isEmpty()){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else{
            System.out.print("Search for question ID: ");
            String id = scanner.nextLine();

            quiz.searchNode(id);
        }
    }


    public static void main(String[] args) {
         
        LinkedlistDemo demo = new LinkedlistDemo();

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
