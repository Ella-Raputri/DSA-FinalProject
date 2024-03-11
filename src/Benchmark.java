import java.io.*;
import java.util.Random;
import java.util.Scanner;


public class Benchmark {
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


    public void add(ArrayListBenchmark arrayList, LinkedListBenchmark linkedList){
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


        //Linked List
        System.out.println("Linked List");
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            linkedList.addQuestion(questions[i], answers[i]);
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime);

    }


    public void delete(ArrayListBenchmark arrayList, LinkedListBenchmark linkedList){
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


        //Linked List
        System.out.println("Linked List");
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            linkedList.deleteQuestion(linkedList.quiz.get(i).getQuestionID());
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime);
    }


    public void edit(ArrayListBenchmark arrayList, LinkedListBenchmark linkedList){
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


        //Linked List
        System.out.println("Linked List");
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            linkedList.editQuestion(linkedList.quiz.get(i).getQuestionID(), "y", "a", "y", "a");
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime);
    }


    public void changeOrder(ArrayListBenchmark arrayList, LinkedListBenchmark linkedList){
        int n;
        long startTime, endTime;

        System.out.print("Enter number of questions: ");
        n = scanner.nextInt();
        scanner.nextLine();

        Random rand = new Random();

        System.out.println("\nCHANGING ORDER OF QUESTIONS");

        //Array List
        System.out.println("Array List");
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            int randomNumber = rand.nextInt(n);
            arrayList.changeOrder(arrayList.quiz.get(i).getQuestionID(), randomNumber);
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime);


        //Linked List
        System.out.println("Linked List");
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            int randomNumber = rand.nextInt(n);
            linkedList.changeOrder(linkedList.quiz.get(i).getQuestionID(), randomNumber);
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime);
    }


    public void print(ArrayListBenchmark arrayList, LinkedListBenchmark linkedList){
        long startTime1, endTime1, startTime2, endTime2, startTime3, endTime3, startTime4, endTime4;
        int n;

        System.out.print("How many times you want to print all the questions? ");
        n = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nPRINTING QUESTIONS");

        //Array List
        startTime1 = System.nanoTime();
        for (int i = 0; i < n; i++) {
            arrayList.printQuestions();
        }
        endTime1 = System.nanoTime();

        //Linked List
        startTime2 = System.nanoTime();
        for (int i = 0; i < n; i++) {
            linkedList.printQuestions();
        }
        endTime2 = System.nanoTime();


        System.out.println("Array List");
        getTime(startTime1, endTime1);
        System.out.println("Linked List");
        getTime(startTime2, endTime2);
    }


    public void search(ArrayListBenchmark arrayList, LinkedListBenchmark linkedList){
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

        //Linked List
        System.out.println("Linked List");
        startTime = System.nanoTime();
        linkedList.questionSearch(linkedList.quiz.get(testingNumber-1).getQuestionID());
        endTime = System.nanoTime();
        getTime(startTime, endTime);
    }

    
    public static void main(String[] args) {
        Benchmark benchmark = new Benchmark();
        inputQuestion();

        ArrayListBenchmark arrayList = new ArrayListBenchmark();
        for (int i=0; i<250 && i<count; i++) {
            arrayList.addQuestion(questions[i], answers[i]);
        }

        LinkedListBenchmark linkedList = new LinkedListBenchmark();
        for (int i=0; i<250 && i<count; i++) {
            linkedList.addQuestion(questions[i], answers[i]);
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
                benchmark.add(arrayList, linkedList);
                break;
    
            //delete question
            case "d":
                benchmark.delete(arrayList, linkedList);
                break;
            
            //edit question
            case "e":
                benchmark.edit(arrayList, linkedList);
                break;
            
            //change order
            case "c":
                benchmark.changeOrder(arrayList, linkedList);
                break;
            
            //printing all questions
            case "p":
                benchmark.print(arrayList, linkedList);
                break;
            
            //searching a particular question
            case "s":
                benchmark.search(arrayList, linkedList);
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