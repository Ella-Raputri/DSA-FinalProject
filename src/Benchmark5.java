import java.io.*;
import java.util.Scanner;


public class Benchmark5 {
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


    public void add(QueueBenchmark queue){
        int n;
        long startTime, endTime;
        System.out.print("Enter number of questions: ");
        n = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nADDING QUESTIONS");

        //Queue
        System.out.println("Queue");
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            queue.addQuestion(questions[i], answers[i]);
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime);
        getSpace();


    }


    public void delete(QueueBenchmark queue){
        int n;
        long startTime, endTime;
        System.out.print("Enter number of questions: ");
        n = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nDELETING QUESTIONS");

        //Queue
        System.out.println("Queue");
        startTime = System.nanoTime();
        Question[] questionSet = queue.returnQuestions();
        for (int i = 0; i < n; i++) {
            Question question = questionSet[i];
            queue.deleteQuestion(question.getQuestionID());
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime);
        getSpace();
    }


    public void edit(QueueBenchmark queue){
        int n;
        long startTime, endTime;
        System.out.print("Enter number of questions: ");
        n = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nEDITING QUESTIONS");

        //Queue
        System.out.println("Queue");
        startTime = System.nanoTime();
        Question[] questionSet = queue.returnQuestions();
        for (int i = 0; i < n; i++) {
            Question question = questionSet[i];
            queue.editQuestion(question.getQuestionID(), "y", "a", "y", "a");
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime);
        getSpace();

    }


    public void changeOrder(QueueBenchmark queue){
        int n, randomNumber;
        long startTime, endTime;

        System.out.print("Enter number of questions: ");
        n = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter random number (1-250): ");
        randomNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nCHANGING ORDER OF QUESTIONS");

        //Queue
        System.out.println("Queue");
        startTime = System.nanoTime();
        Question[] questionSet = queue.returnQuestions();
        for (int i = 0; i < n; i++) {
            Question question = questionSet[i];
            queue.changeOrder(question.getQuestionID(), randomNumber);
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime);
        getSpace();


    }


    public void print(QueueBenchmark queue){
        long startTime, endTime;
        int n;

        System.out.print("How many times you want to print all the questions? ");
        n = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nPRINTING QUESTIONS");

        //Queue
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            queue.printQuestions();
        }
        endTime = System.nanoTime();

        
        System.out.println("Hash Map");
        getTime(startTime, endTime);
        getSpace();
    }


    public void search(QueueBenchmark queue){
        long startTime, endTime;

        System.out.print("Search for string: ");
        String str = scanner.nextLine();

        System.out.println("\nSEARCHING A QUESTION");

        //Queue
        System.out.println("Queue");
        startTime = System.nanoTime();
        queue.questionSearch(str);
        endTime = System.nanoTime();
        getTime(startTime, endTime);
        getSpace();

    }

    
    public static void main(String[] args) {
        Benchmark5 benchmark = new Benchmark5();
        inputQuestion();

        QueueBenchmark queue = new QueueBenchmark();
        for (int i=0; i<250 && i<count; i++) {
            queue.addQuestion(questions[i], answers[i]);
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
                benchmark.add(queue);
                break;
    
            //delete question
            case "d":
                benchmark.delete(queue);
                break;
            
            //edit question
            case "e":
                benchmark.edit(queue);
                break;
            
            //change order
            case "c":
                benchmark.changeOrder(queue);
                break;
            
            //printing all questions
            case "p":
                benchmark.print(queue);
                break;
            
            //searching a particular question
            case "s":
                benchmark.search(queue);
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