import java.io.*;
import java.util.Scanner;


public class Benchmark3 {
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


    public void add(LinkedlistBenchmark linkedList){
        int n;
        long startTime, endTime;
        System.out.print("Enter number of questions: ");
        n = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nADDING QUESTIONS");

        //Linked list
        System.out.println("Linked List");
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            linkedList.addQuestion(questions[i], answers[i]);
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime);
        getSpace();

    }


    public void delete(LinkedlistBenchmark linkedlist){
        int n;
        long startTime, endTime;
        System.out.print("Enter number of questions: ");
        n = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nDELETING QUESTIONS");

        //Linked List
        System.out.println("Linked List");
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            linkedlist.deleteQuestion(linkedlist.quiz.getIDFromNumber(i+1));
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime);
        getSpace();


    }


    public void edit(LinkedlistBenchmark linkedlist){
        int n;
        long startTime, endTime;
        System.out.print("Enter number of questions: ");
        n = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nEDITING QUESTIONS");

        //Linked List
        System.out.println("Linked List");
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            linkedlist.editQuestion(linkedlist.quiz.getIDFromNumber(i), "y", "a", "y", "a");
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime);
        getSpace();
        
    }


    public void changeOrder(LinkedlistBenchmark linkedlist){
        int n, randomNumber;
        long startTime, endTime;

        System.out.print("Enter number of questions: ");
        n = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter random number (1-250): ");
        randomNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nCHANGING ORDER OF QUESTIONS");

        //Linked List 
        System.out.println("Linked List");
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            // int randomNumber = rand.nextInt(n);
            linkedlist.changeOrder(linkedlist.quiz.getIDFromNumber(i), randomNumber);
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime);
        getSpace();
        
    }


    public void print(LinkedlistBenchmark linkedlist){
        long startTime, endTime;
        int n;

        System.out.print("How many times you want to print all the questions? ");
        n = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nPRINTING QUESTIONS");

        //Linked List
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            linkedlist.printQuestions();
        }
        endTime = System.nanoTime();
        
        System.out.println("Linked List");
        getTime(startTime, endTime);
        getSpace();
        
    }


    public void search(LinkedlistBenchmark linkedlist){
        long startTime, endTime;

        System.out.print("Search question number: ");
        int testingNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nSEARCHING A QUESTION");

        //Linked List
        System.out.println("Linked List");
        startTime = System.nanoTime();
        linkedlist.questionSearch(linkedlist.quiz.getIDFromNumber(testingNumber));
        endTime = System.nanoTime();
        getTime(startTime, endTime);
        getSpace();
        
    }

    
    public static void main(String[] args) {
        Benchmark3 benchmark = new Benchmark3();
        inputQuestion();

        LinkedlistBenchmark linkedlist = new LinkedlistBenchmark();
        for (int i=0; i<250 && i<count; i++) {
            linkedlist.addQuestion(questions[i], answers[i]);
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
                benchmark.add(linkedlist);
                break;
    
            //delete question
            case "d":
                benchmark.delete(linkedlist);
                break;
            
            //edit question
            case "e":
                benchmark.edit(linkedlist);
                break;
            
            //change order
            case "c":
                benchmark.changeOrder(linkedlist);
                break;
            
            //printing all questions
            case "p":
                benchmark.print(linkedlist);
                break;
            
            //searching a particular question
            case "s":
                benchmark.search(linkedlist);
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