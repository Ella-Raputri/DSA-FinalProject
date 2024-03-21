import java.io.*;
import java.util.Scanner;


public class Benchmark2 {
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


    public void add(HashMapBenchmark hashMap){
        int n;
        long startTime, endTime;
        System.out.print("Enter number of questions: ");
        n = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nADDING QUESTIONS");

        //Hashmap
        System.out.println("Hash Map");
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            hashMap.addQuestion(questions[i], answers[i]);
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime);
        getSpace();


    }


    public void delete(HashMapBenchmark hashMap){
        int n;
        long startTime, endTime;
        System.out.print("Enter number of questions: ");
        n = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nDELETING QUESTIONS");

        //Hashmap
        System.out.println("Hash Map");
        startTime = System.nanoTime();
        Question[] setofkey = hashMap.returnKeySet();
        for (int i = 0; i < n; i++) {
            Question key = setofkey[i];
            hashMap.deleteQuestion(key.getQuestionID());
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime);
        getSpace();
    }


    public void edit(HashMapBenchmark hashMap){
        int n;
        long startTime, endTime;
        System.out.print("Enter number of questions: ");
        n = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nEDITING QUESTIONS");

        //Hashmap
        System.out.println("Hash Map");
        startTime = System.nanoTime();
        Question[] setofkey = hashMap.returnKeySet();
        for (int i = 0; i < n; i++) {
            Question key = setofkey[i];
            hashMap.editQuestion(key.getQuestionID(), "y", "a", "y", "a");
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime);
        getSpace();

    }


    public void changeOrder(HashMapBenchmark hashMap){
        int n, randomNumber;
        long startTime, endTime;

        System.out.print("Enter number of questions: ");
        n = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter random number (1-250): ");
        randomNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nCHANGING ORDER OF QUESTIONS");

        //Hashmap
        System.out.println("Hash Map");
        startTime = System.nanoTime();
        Question[] setofkey = hashMap.returnKeySet();
        for (int i = 0; i < n; i++) {
            Question key = setofkey[i];
            hashMap.changeOrder(key.getQuestionID(), randomNumber);
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime);
        getSpace();


    }


    public void print(HashMapBenchmark hashMap){
        long startTime, endTime;
        int n;

        System.out.print("How many times you want to print all the questions? ");
        n = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nPRINTING QUESTIONS");

        //Hashmap
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            hashMap.printQuestions();
        }
        endTime = System.nanoTime();

        
        System.out.println("Hash Map");
        getTime(startTime, endTime);
        getSpace();
    }


    public void search(HashMapBenchmark hashMap){
        long startTime, endTime;

        System.out.print("Search question number: ");
        int testingNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nSEARCHING A QUESTION");

        //Hashmap
        System.out.println("Hash Map");
        startTime = System.nanoTime();
        String id = hashMap.returnID(testingNumber);
        hashMap.questionSearch(id);
        endTime = System.nanoTime();
        getTime(startTime, endTime);
        getSpace();

    }

    
    public static void main(String[] args) {
        Benchmark2 benchmark = new Benchmark2();
        inputQuestion();

        HashMapBenchmark hashMap = new HashMapBenchmark();
        for (int i=0; i<250 && i<count; i++) {
            hashMap.addQuestion(questions[i], answers[i]);
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
                benchmark.add(hashMap);
                break;
    
            //delete question
            case "d":
                benchmark.delete(hashMap);
                break;
            
            //edit question
            case "e":
                benchmark.edit(hashMap);
                break;
            
            //change order
            case "c":
                benchmark.changeOrder(hashMap);
                break;
            
            //printing all questions
            case "p":
                benchmark.print(hashMap);
                break;
            
            //searching a particular question
            case "s":
                benchmark.search(hashMap);
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