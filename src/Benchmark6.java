import java.io.*;
import java.util.Scanner;

public class Benchmark6 {
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


    static void getTime(long startTime, long endTime) {
        double nanoSeconds = endTime - startTime;
        double milliSeconds = nanoSeconds/1000000;
        
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


    public void add(StackBenchmark stack){
        int n;
        long startTime, endTime;
        System.out.print("Enter number of questions: ");
        n = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nADDING QUESTIONS");

        //Stack
        System.out.println("Stack");
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            stack.addQuestion(questions[i], answers[i]);
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime);
        getSpace();

    }


    public void delete(StackBenchmark stack){
        int n;
        long startTime, endTime;
        System.out.print("Enter number of questions: ");
        n = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nDELETING QUESTIONS");

        //Stack
        System.out.println("Stack");
        startTime = System.nanoTime();

        int iterate = 0;
        while (iterate < n) {
            stack.deleteQuestion(stack.quiz.get(0).getQuestionID());
            iterate++;
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime);
        getSpace();

    }


    public void edit(StackBenchmark stack){
        int n;
        long startTime, endTime;
        System.out.print("Enter number of questions: ");
        n = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nEDITING QUESTIONS");

        //Stack
        System.out.println("Stack");
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            stack.editQuestion(stack.quiz.get(i).getQuestionID(), "y", "a", "y", "a");
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime);
        getSpace();

    }


    public void changeOrder(StackBenchmark stack){
        int n, randomNumber;
        long startTime, endTime;

        System.out.print("Enter number of questions: ");
        n = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter random number (1-250): ");
        randomNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nCHANGING ORDER OF QUESTIONS");

        //Array List
        System.out.println("Array List");
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            stack.changeOrder(stack.quiz.get(i).getQuestionID(), randomNumber);
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime);
        getSpace();
    }


    public void print(StackBenchmark stack){
        long startTime, endTime;
        int n;

        System.out.print("How many times you want to print all the questions? ");
        n = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nPRINTING QUESTIONS");

        //Stack
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            stack.printQuestions();
        }
        endTime = System.nanoTime();

        
        System.out.println("Array List");
        getTime(startTime, endTime);
        getSpace();
    }


    public void search(StackBenchmark stack){
        long startTime, endTime;

        System.out.print("Search for string: ");
        String str = scanner.nextLine();

        System.out.println("\nSEARCHING A QUESTION");

        //Stack
        System.out.println("Stack");
        startTime = System.nanoTime();
        stack.questionSearch(str);
        endTime = System.nanoTime();
        getTime(startTime, endTime);
        getSpace();

    }

    
    public static void main(String[] args) {
        Benchmark6 benchmark = new Benchmark6();
        inputQuestion();

        StackBenchmark stack = new StackBenchmark();
        for (int i=0; i<250 && i<count; i++) {
            stack.addQuestion(questions[i], answers[i]);
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
                benchmark.add(stack);
                break;
    
            //delete question
            case "d":
                benchmark.delete(stack);
                break;
            
            //edit question
            case "e":
                benchmark.edit(stack);
                break;
            
            //change order
            case "c":
                benchmark.changeOrder(stack);
                break;
            
            //printing all questions
            case "p":
                benchmark.print(stack);
                break;
            
            //searching a particular question
            case "s":
                benchmark.search(stack);
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