import java.io.*;
import java.util.Scanner;


public class Benchmark1 {
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


    public void add(ArrayListBenchmark arrayList){
        int n;
        long startTime, endTime;
        System.out.print("Enter number of questions: ");
        n = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nADDING QUESTIONS");

        //Array List
        System.out.println("Array List");
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            arrayList.addQuestion(questions[i], answers[i]);
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime);
        getSpace();

    }


    public void delete(ArrayListBenchmark arrayList){
        int n;
        long startTime, endTime;
        System.out.print("Enter number of questions: ");
        n = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nDELETING QUESTIONS");

        //Array List
        System.out.println("Array List");
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            arrayList.deleteQuestion(arrayList.quiz.get(i).getQuestionID());
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime);
        getSpace();

    }


    public void edit(ArrayListBenchmark arrayList){
        int n;
        long startTime, endTime;
        System.out.print("Enter number of questions: ");
        n = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nEDITING QUESTIONS");

        //Array List
        System.out.println("Array List");
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            arrayList.editQuestion(arrayList.quiz.get(i).getQuestionID(), "y", "a", "y", "a");
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime);
        getSpace();

    }


    public void changeOrder(ArrayListBenchmark arrayList){
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
            arrayList.changeOrder(arrayList.quiz.get(i).getQuestionID(), randomNumber);
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime);
        getSpace();
    }


    public void print(ArrayListBenchmark arrayList){
        long startTime, endTime;
        int n;

        System.out.print("How many times you want to print all the questions? ");
        n = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nPRINTING QUESTIONS");

        //Array List
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            arrayList.printQuestions();
        }
        endTime = System.nanoTime();

        
        System.out.println("Array List");
        getTime(startTime, endTime);
        getSpace();
    }


    public void search(ArrayListBenchmark arrayList){
        long startTime, endTime;

        System.out.print("Search question number: ");
        int testingNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nSEARCHING A QUESTION");

        //Array List
        System.out.println("Array List");
        startTime = System.nanoTime();
        arrayList.questionSearch(arrayList.quiz.get(testingNumber-1).getQuestionID());
        endTime = System.nanoTime();
        getTime(startTime, endTime);
        getSpace();

    }

    
    public static void main(String[] args) {
        Benchmark1 benchmark = new Benchmark1();
        inputQuestion();

        ArrayListBenchmark arrayList = new ArrayListBenchmark();
        for (int i=0; i<250 && i<count; i++) {
            arrayList.addQuestion(questions[i], answers[i]);
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
                benchmark.add(arrayList);
                break;
    
            //delete question
            case "d":
                benchmark.delete(arrayList);
                break;
            
            //edit question
            case "e":
                benchmark.edit(arrayList);
                break;
            
            //change order
            case "c":
                benchmark.changeOrder(arrayList);
                break;
            
            //printing all questions
            case "p":
                benchmark.print(arrayList);
                break;
            
            //searching a particular question
            case "s":
                benchmark.search(arrayList);
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