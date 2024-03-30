import java.io.*;
import java.util.Scanner;


public class Benchmark4 {
    static String[] questions = new String[250];
    static String[] answers = new String[250];
    static int count = 0;
    static Scanner scanner = new Scanner(System.in);

    public static void inputQuestion() {
        try {
            FileReader fr =new FileReader("src/ExampleQuestions.txt");    
            BufferedReader reader = new BufferedReader(fr);
            String eachLine;
            while ((eachLine = reader.readLine()) != null) {
                String[] part = eachLine.split("_");
                questions[count] = part[0];
                answers[count] = part[1];
                count++;
            }
            reader.close();

        } catch (IOException e) {
            System.out.println("Cannot find file. Please try again.");
        }
    }


    static void getTime(long startTime, long endTime, int n) {
        double nanoSeconds = endTime - startTime;
        double milliSeconds = nanoSeconds/1000000;

        // if(nullCount != 0){
        //     milliSeconds = ((nullCount/n) * milliSeconds) + milliSeconds;
        // }
        
        System.out.println("Time used: " + milliSeconds + " milliseconds");
        System.out.println();
    }

    static void getSpace(){
        Runtime rt = Runtime.getRuntime();
        long total_mem = rt.totalMemory();
        long free_mem = rt.freeMemory();
        long used_mem = total_mem - free_mem;

        String str = String.format("%,d", used_mem);
        System.out.println("Amount of used memory: " + str);
    }


    public void add(TreeRedBlackBenchmark tree){
        int n;
        long startTime, endTime;
        System.out.print("Enter number of questions: ");
        n = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nADDING QUESTIONS");

        //Red Black Tree
        System.out.println("Red Black Tree");
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            tree.addQuestion(questions[i], answers[i]);
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime, n);
        getSpace();
    }


    public void delete(TreeRedBlackBenchmark tree){
        int n;
        long startTime, endTime;
        System.out.print("Enter number of questions: ");
        n = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nDELETING QUESTIONS");

        //Red Black Tree
        System.out.println("Red Black Tree");
        startTime = System.nanoTime();
        int iterate = 0;
        while (iterate < n) {
            TreeNode treenode = null;
            treenode = tree.quiz.searchNodeBasedonNumber(tree.quiz.getRoot(), 1, treenode);
            String id = treenode.data.getQuestionID();
            tree.deleteQuestion(id);
            iterate++;
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime,n);
        getSpace();
    }


    public void edit(TreeRedBlackBenchmark tree){
        int n;
        long startTime, endTime;
        System.out.print("Enter number of questions: ");
        n = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nEDITING QUESTIONS");

        //Red Black Tree
        System.out.println("Red Black Tree");
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            TreeNode treenode = null;
            treenode = tree.quiz.searchNodeBasedonNumber(tree.quiz.getRoot(), i+1,treenode);
            String id = treenode.data.getQuestionID();
            tree.editQuestion(id, "y", "a", "y", "a");
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime,n);
        getSpace();
        
    }


    public void changeOrder(TreeRedBlackBenchmark tree){
        int n, randomNumber;
        long startTime, endTime;

        System.out.print("Enter number of questions: ");
        n = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter random number (1-250): ");
        randomNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nCHANGING ORDER OF QUESTIONS");

        //Red Black Tree
        //int nullCount=0;
        System.out.println("Red Black Tree");
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            TreeNode treenode = null;
            treenode = tree.quiz.searchNodeBasedonNumber(tree.quiz.getRoot(), i+1, treenode);
            //if(treenode != null){
                String id = treenode.data.getQuestionID();
                tree.changeOrder(id, randomNumber);
            //}
            // else{
            //     nullCount++;
            //     System.out.println("null");
            // }
            
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime, n);
        getSpace();
        
    }


    public void print(TreeRedBlackBenchmark tree){
        long startTime, endTime;
        int n;

        System.out.print("How many times you want to print all the questions? ");
        n = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nPRINTING QUESTIONS");

        //Red Black Tree
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            tree.printQuestions();
        }
        endTime = System.nanoTime();

        System.out.println("Red Black Tree");
        getTime(startTime, endTime, n);
        getSpace();
    }


    public void search(TreeRedBlackBenchmark tree){
        long startTime, endTime;
        int n = 250;

        System.out.print("Search for a string: ");
        String str = scanner.nextLine();

        System.out.println("\nSEARCHING A QUESTION");

        //Red Black Tree
        System.out.println("Red Black Tree");
        startTime = System.nanoTime();
        tree.questionSearch(str);
        endTime = System.nanoTime();
        getTime(startTime, endTime,  n);
        getSpace();
    }

    
    public static void main(String[] args) {
        Benchmark4 benchmark = new Benchmark4();
        inputQuestion();

        TreeRedBlackBenchmark tree = new TreeRedBlackBenchmark();
        for (int i=0; i<250 && i<count; i++) {
            tree.addQuestion(questions[i], answers[i]);
        }

        
        System.out.println("\n************************************");
        System.out.println("\nSpeed Testing");
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
        command = command.toLowerCase();

        switch (command) {
            //add question
            case "a":
                benchmark.add(tree);
                break;
    
            //delete question
            case "d":
                benchmark.delete(tree);
                break;
            
            //edit question
            case "e":
                benchmark.edit(tree);
                break;
            
            //change order
            case "c":
                benchmark.changeOrder(tree);
                break;
            
            //printing all questions
            case "p":
                benchmark.print(tree);
                break;
            
            //searching a particular question
            case "s":
                benchmark.search(tree);
                break;
            
            //quit the program
            case "q":
                return;

            //invalid command
            default:
                System.out.println("Invalid command. Please try again");
                break;
        }

        
        
    }

    

    

}