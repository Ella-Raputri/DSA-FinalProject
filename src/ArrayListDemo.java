import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListDemo {

    ArrayList<Question> quiz = new ArrayList<Question>();
    static Scanner scanner = new Scanner(System.in);

    public Question getQuestionfromID(String id){
        for(Question i:quiz){
            if(i.getQuestionID().equals(id)){
                return i;
            }
        }
        return null;
    }

    public void addQuestion(){
        System.out.print("Enter question: ");
        String question = scanner.nextLine();

        System.out.print("Enter correct answer: ");
        String correctAnswer = scanner.nextLine();
        
        Question q1 = new Question(correctAnswer, question);
        quiz.add(q1);
        q1.setNumberID(quiz.indexOf(q1)+1);
        System.out.println("Added successfully");
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

            Question i = getQuestionfromID(id);
            if(i!= null){
                int j = quiz.indexOf(i);
                quiz.remove(i);
                System.out.println("Deleted successfully.");

                while(j < quiz.size()){
                    quiz.get(j).setQuestionNumber(j+1);
                    j++;
                }
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

            Question i = getQuestionfromID(id);
            if(i != null){
                System.out.println("Current question: " + i.getQuestion());
                System.out.println("Current answer: " + i.getCorrectAnswer());

                System.out.print("Do you want to change the question? (Y/N) ");
                String questionChange = scanner.nextLine();
                questionChange = questionChange.toLowerCase();
                if(questionChange.equals("y")){
                    System.out.print("New Question: ");
                    String newQuestion = scanner.nextLine();
                    i.setQuestion(newQuestion);
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
                    i.setCorrectAnswer(newAnswer);
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
        else{
            printQuestions();
            System.out.print("What question ID do you want to change the order? ");
            String id = scanner.nextLine();

            Question i = getQuestionfromID(id);

            if(i!=null){
                System.out.println("Current question number: " + i.getQuestionNumber());

                System.out.print("Change to question number: ");
                int newNumber = scanner.nextInt();
                scanner.nextLine();
                
                if(i.getQuestionNumber() == newNumber){
                    System.out.println("Question number is not changed.");
                    return;
                }

                else if(newNumber <= quiz.size() && newNumber > 0){
                    int indexOfi = quiz.indexOf(i);
                    int currentNumber = i.getQuestionNumber();

                    //setting the new number
                    if(currentNumber > newNumber){
                        quiz.add(newNumber-1, i);
                        quiz.remove(indexOfi+1);
                    }
                    else{
                        if(newNumber+1 > quiz.size()){
                            quiz.add(i);
                        }
                        else{
                            quiz.add(newNumber, i);
                        }
                        quiz.remove(indexOfi);
                    }

                    
                    //setting all the question number
                    for(int j=0; j<quiz.size(); j++){
                        quiz.get(j).setQuestionNumber(j+1);
                    }

                    System.out.println("Question number has been changed successfully.");
                }
                    

                else if(newNumber > quiz.size() || newNumber < 0) {
                    System.out.println("The number is out of list");
                    return;
                }

                return;
            }
            System.out.println("ID invalid. Please try again.");
        }
    }


    public void printQuestions(){
        if(quiz.isEmpty()){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else{
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
        if(quiz.isEmpty()){
            System.out.println("Your current quiz is empty.");
            return;
        }
        else{
            System.out.print("Search for question ID: ");
            String id = scanner.nextLine();

            Question i = getQuestionfromID(id);
            
            if(i!= null){
                System.out.println("Question " + i.getQuestionNumber());
                System.out.println("Question ID: "+ i.getQuestionID());
                System.out.println("Question: "+ i.getQuestion());
                System.out.println("Answer: "+ i.getCorrectAnswer());
                System.out.println();
                return;
            }
            System.out.println("ID invalid. Please try again.");

        }
    }


    public static void main(String[] args) {
         
        ArrayListDemo demo = new ArrayListDemo();

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
