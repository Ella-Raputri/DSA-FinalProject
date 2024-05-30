import java.io.*;
import java.util.Scanner;

public class Benchmark6 {
    //initialize String arrays for questions and answers
    static String[] questions = new String[1000];
    static String[] answers = new String[1000];

    static int count = 0; //to track count of questions when inputting
    static Scanner scanner = new Scanner(System.in); //scanner for user input

    public static void inputQuestion() {
        try {
            //read the ExampleQuestions file
            FileReader fr =new FileReader("src/ExampleQuestions.txt");    
            BufferedReader reader = new BufferedReader(fr);
            String eachLine;
            //while reading each line
            while ((eachLine = reader.readLine()) != null) {
                //split each line by the '_' symbol
                String[] part = eachLine.split("_");
                questions[count] = part[0]; //the first part is the question
                answers[count] = part[1]; //the second part is the answer
                count++;
            }
            reader.close();

        } catch (IOException e) {
            //exception if file is not available
            System.out.println("Cannot find file. Please try again.");
        }
    }


    static void getTime(long startTime, long endTime) {
        //get runtime by substracting endtime with startime
        double nanoSeconds = endTime - startTime;
        //convert it to miliseconds
        double milliSeconds = nanoSeconds/1000000;

        //print the result
        System.out.println("Time used: " + milliSeconds + " milliseconds");
        System.out.println();
    }

    static void getSpace(){
        //get space by first access the total memory and free memory
        Runtime rt = Runtime.getRuntime();
        long total_mem = rt.totalMemory();
        long free_mem = rt.freeMemory();
        //count the used memory
        long used_mem = total_mem - free_mem;

        //print the result
        String str = String.format("%,d", used_mem);
        System.out.println("Amount of used memory: " + str);
    }


    public void add(StackBenchmark stack){
        int n;
        long startTime, endTime;
        //ask user to input how many question numbers that user wants to test
        System.out.print("Enter number of questions: ");
        n = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nADDING QUESTIONS");
        System.out.println("Stack");
        startTime = System.nanoTime(); //get start time

        //perform n times adding Questions based on user input
        for (int i = 0; i < n; i++) {
            stack.addQuestion(questions[i], answers[i]);
        }
        endTime = System.nanoTime(); //get end time
        getTime(startTime, endTime); //get time used
        getSpace(); //get space used

    }


    public void delete(StackBenchmark stack){
        int n;
        long startTime, endTime;
        //ask user to input how many question numbers that user wants to test
        System.out.print("Enter number of questions: ");
        n = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nDELETING QUESTIONS");
        System.out.println("Stack");
        startTime = System.nanoTime(); //get start time

        //perform n times deleting Questions based on user input
        int iterate = 0;
        while (iterate < n) {
            stack.deleteQuestion(stack.quiz.get(0).getQuestionID());
            iterate++;
        }
        endTime = System.nanoTime(); //get end time
        getTime(startTime, endTime); //get time used
        getSpace(); //get space used

    }


    public void edit(StackBenchmark stack){
        int n;
        long startTime, endTime;
        //ask user to input how many question numbers that user wants to test
        System.out.print("Enter number of questions: ");
        n = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nEDITING QUESTIONS");
        System.out.println("Stack");
        startTime = System.nanoTime(); //get start time
        
        //perform n times editing Questions based on user input
        for (int i = 0; i < n; i++) {
            stack.editQuestion(stack.quiz.get(i).getQuestionID(), "y", "a", "y", "a");
        }
        endTime = System.nanoTime(); //get end time
        getTime(startTime, endTime); //get time used
        getSpace(); //get space used

    }


    public void changeOrder(StackBenchmark stack){
        int n, randomNumber;
        long startTime, endTime;

        //ask user to input how many question numbers that user wants to test
        System.out.print("Enter number of questions: ");
        n = scanner.nextInt();
        scanner.nextLine();

        //ask user to input which number that user wants to change all the number to it
        System.out.print("Enter random number (1-250): ");
        randomNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nCHANGING ORDER OF QUESTIONS");
        System.out.println("Stack");
        startTime = System.nanoTime(); //get start time
        
        //perform n times changing Questions order based on user input
        for (int i = 0; i < n; i++) {
            stack.changeOrder(stack.quiz.get(i).getQuestionID(), randomNumber);
        }
        endTime = System.nanoTime(); //get end time
        getTime(startTime, endTime); //get time used
        getSpace(); //get space used
    }


    public void print(StackBenchmark stack){
        long startTime, endTime;
        int n;

        //ask user to input how many times they want to print the questions
        System.out.print("How many times you want to print all the questions? ");
        n = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nPRINTING QUESTIONS");
        startTime = System.nanoTime(); //get start time
        //perform n times printing Questions based on user input
        for (int i = 0; i < n; i++) {
            stack.printQuestions();
        }
        endTime = System.nanoTime(); //get end time

        System.out.println("Stack");
        getTime(startTime, endTime); //get time used
        getSpace(); //get space used
    }


    public void search(StackBenchmark stack){
        long startTime, endTime;

        //ask user to input the string to be searched
        System.out.print("Search for string: ");
        String str = scanner.nextLine();

        System.out.println("\nSEARCHING A QUESTION");
        System.out.println("Stack");

        startTime = System.nanoTime(); //get start time
        stack.questionSearch(str); //search for string
        endTime = System.nanoTime(); //get end time

        getTime(startTime, endTime); //get time used
        getSpace(); //get space used

    }

    
    public static void main(String[] args) {
        //initialize benchmark6 to benchmark Stack
        Benchmark6 benchmark = new Benchmark6();
        inputQuestion(); //input all questions and answers to the String arrays

        StackBenchmark stack = new StackBenchmark();
        //add all questions to the Stack
        for (int i=0; i<1000 && i<count; i++) {
            stack.addQuestion(questions[i], answers[i]);
        }
        
        //print the menus
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
        
        //ask user to input a command to be counted
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