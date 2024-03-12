import java.util.Scanner;

public class main {
        public static void main(String[] args){
        System.out.println("Hello");

        Question question1 = new Question("1", "3x4");
        Question question2 = new Question("2", "3x4");
        Question question3 = new Question("3", "3x4");
        Question question4 = new Question("4", "3x4");
        Question question5 = new Question("4", "3x4");
        Question question6 = new Question("4", "3x4");

        question1.setNumberID(1);
        question2.setNumberID(2);
        question3.setNumberID(3);
        question4.setNumberID(4);
        question5.setNumberID(5);
        question6.setNumberID(6);
        // System.out.println(question1.getQuestionID());

        TreeRedBlack demo = new TreeRedBlack();
        demo.insertNode(question1); 
        demo.insertNode(question2);
        demo.insertNode(question3);
        demo.insertNode(question4);
        demo.insertNode(question5);

        System.out.println(demo.toString());

        // Scanner scanner = new Scanner(System.in);
        // System.out.print("What question ID do you want to delete? ");
        // String answer = scanner.nextLine();

        System.out.println(question2.getQuestionID());    

        System.out.println(demo.getQuestionNumberfromNode("002"));
        demo.deleteNode("002");
  
        System.out.println(demo.toString());

        demo.insertNode(question6);
        System.out.println(demo.toString());

        int count = demo.count_nodes(demo.getRoot());
      
        System.out.println("The total number of nodes in the given complete binary tree are: "+count);
    }
}
