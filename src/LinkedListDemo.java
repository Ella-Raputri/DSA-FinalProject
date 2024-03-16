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

            Linkedlist.Node current = quiz.getNode(id);

            if(current!= null){
                quiz.deleteNode(current);
                System.out.println("Deleted successfully.");
                quiz.resetNumber();
                return;
            }
            System.out.println("ID invalid. Please try again.");
            
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

            Linkedlist.Node current = quiz.getNode(id);
            if (current != null){
                System.out.println("Current question: " + current.data.getQuestion());
                System.out.println("Current answer: " + current.data.getCorrectAnswer());
                System.out.print("Do you want to change the question? (Y/N) ");
                String questionChange = scanner.nextLine();
                questionChange = questionChange.toLowerCase();
    
                if(questionChange.equals("y")){
                    System.out.print("New Question: ");
                    String newQuestion = scanner.nextLine();
                    current.data.setQuestion(newQuestion);
                    System.out.println("Question has been changed successfully.");
                }
                else if(!(questionChange.equals("y")) && !(questionChange.equals("n"))){
                    System.out.println("Invalid input.");
                    return;
                }
    
                System.out.print("Do you want to change the answer? (Y/N) ");
                String answerChange = scanner.nextLine();
                answerChange = answerChange.toLowerCase();
                if(answerChange.equals("y")){
                    System.out.print("New Answer: ");
                    String newAnswer = scanner.nextLine();
                    current.data.setCorrectAnswer(newAnswer);
                    System.out.println("Answer has been changed successfully.");
                }
                else if(!(questionChange.equals("y")) && !(questionChange.equals("n"))){
                    System.out.println("Invalid input.");
                    return;
                }
    
                System.out.println("Returning to the main menu...");
                return;
            }
            System.out.println("ID invalid. Please try again.");

        }
    }


    public void changeOrder(){
        if(quiz.isEmpty()){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else if(quiz.head.equals(quiz.tail)){
            System.out.println("Your current quiz only contains one number.");
            return;
        }
        else{
            printQuestions();
            System.out.print("What question ID do you want to change the order? ");
            String id = scanner.nextLine();

            Linkedlist.Node current = quiz.getNode(id);
            if(current != null){
                int currentNumber = current.data.getQuestionNumber();

                System.out.println("Current question number: " + current.data.getQuestionNumber());
                System.out.print("Change to question number: ");
                int newNumber = scanner.nextInt();
                scanner.nextLine();

                if(currentNumber == newNumber){
                    System.out.println("Question number is not changed.");
                    return;
                }

                else if(newNumber <= Linkedlist.nodeAmount && newNumber > 0){
                    quiz.changeNodeOrder(newNumber, current, currentNumber);
                    quiz.resetNumber();
                    System.out.println("Question number has been changed successfully.");
                }

                else if(newNumber > Linkedlist.nodeAmount || newNumber < 0){
                    System.out.println("The number is out of list");
                    return;
            }

            return;
        }
        System.out.println("Invalid ID number. Please try again.");

        }
    }


    public void printQuestions(){
        if(quiz.isEmpty()){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else{
            Linkedlist.Node current = quiz.head;
            System.out.println("\nYour current quiz: ");
            while(current!=null){
                System.out.println("Question " + current.data.getQuestionNumber());
                System.out.println("Question ID: "+ current.data.getQuestionID());
                System.out.println("Question: "+ current.data.getQuestion());
                System.out.println("Answer: "+ current.data.getCorrectAnswer());
                System.out.println();
                current = current.next;
            }
            
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

            Linkedlist.Node current = quiz.getNode(id);
        
            if(current != null){
                System.out.println("Question " + current.data.getQuestionNumber());
                System.out.println("Question ID: "+ current.data.getQuestionID());
                System.out.println("Question: "+ current.data.getQuestion());
                System.out.println("Answer: "+ current.data.getCorrectAnswer());
                System.out.println();
                return;
            }
            System.out.println("ID invalid. Please try again.");
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
