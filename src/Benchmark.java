import java.io.*;
import java.util.Scanner;


public class Benchmark {
    static String[] questions = new String[250];
    static String[] answers = new String[250];
    static int count = 0;

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
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        inputQuestion();

        ArrayListBenchmark arrayList = new ArrayListBenchmark();
        for (int i=0; i<250 && i<count; i++) {
            arrayList.addQuestion(questions[i], answers[i]);
        }

        // HashmapBenchmark hashMap = new HashmapBenchmark();
        // for (int i=0; i<150 && i<courseCount; i++) {
        //     hashMap.addCourse(courseName[i], courseId[i], courseDay[i], courseStartTime[i], courseEndTime[i], lecturerName[i]);
        // }
        // BinarysearchtreeBenchmark binarySearchTree = new BinarysearchtreeBenchmark();
        // for (int i=0; i<150 && i<courseCount; i++) {
        //     binarySearchTree.addCourse(courseName[i], courseId[i], courseDay[i], courseStartTime[i], courseEndTime[i], lecturerName[i]);
        // }
        // LinkedlistBenchmark linkedList = new LinkedlistBenchmark();
        // for (int i=0; i<150 && i<courseCount; i++) {
        //     linkedList.addCourse(courseName[i], courseId[i], courseDay[i], courseStartTime[i], courseEndTime[i], lecturerName[i], null);
        // }

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
                System.out.print("Enter number of courses: ");
                int n = scanner.nextInt();
                scanner.nextLine();

                System.out.println("\nADDING COURSES");

                //Array List
                System.out.println("Array List");
                long startTime = System.nanoTime();
                for (int i = 0; i < n && n <= count; i++) {
                    arrayList.addQuestion(questions[i], answers[i]);
                }
                long endTime = System.nanoTime();
                getTime(startTime, endTime);
                

                // //Hashmap
                // System.out.println("\nHash Map: ");
                // System.out.print("Adding course: ");
                // startTime2 = System.nanoTime();
                // for (int i = 0; i < n && n <= courseCount; i++) {
                //     hashMap.addCourse(courseName[i], courseId[i], courseDay[i], courseStartTime[i], courseEndTime[i], lecturerName[i]);
                // }
                // endTime2 = System.nanoTime();
                // getTime(startTime2, endTime2);

                // //Binary Search Tree
                // System.out.println("\nBinary Search Tree: ");
                // System.out.print("Adding course: ");
                // startTime3 = System.nanoTime();
                // for (int i = 0; i < n && n <= courseCount; i++) {
                //     binarySearchTree.addCourse(courseName[i], courseId[i], courseDay[i], courseStartTime[i], courseEndTime[i], lecturerName[i]);
                // }
                // endTime3 = System.nanoTime();
                // getTime(startTime3, endTime3);

                // //Linked list
                // System.out.println("\nLinkedList: ");
                // System.out.print("Adding course: ");
                // startTime4 = System.nanoTime();
                // for (int i = 0; i < n && n <= courseCount; i++) {
                //     linkedList.addCourse(courseName[i], courseId[i], courseDay[i], courseStartTime[i], courseEndTime[i], lecturerName[i], null);
                // }
                // endTime4 = System.nanoTime();
                // getTime(startTime4, endTime4);
                // break;

            /*********************
             * 2 - Remove Course *
             *********************/
    //         case "2":
    //             Scanner read2 = new Scanner(System.in);
    //             System.out.println("\nPerformance of different data structures for removing course method");
    //             System.out.print("Enter a number of courses (n) to be removed to stimulate the speed: ");
    //             n = read2.nextInt();
    //             //ArrayList
    //             System.out.println("\nArrayList: ");
    //             System.out.print("Removing course: ");
    //             startTime1 = System.nanoTime();
    //             for (int i = 0; i < n && n <= courseCount; i++) {
    //                 arrayList.removeCourse(courseName[i]);
    //             }
    //             endTime1 = System.nanoTime();
    //             getTime(startTime1, endTime1);

    //             //HashMap
    //             System.out.println("\nHash Map: ");
    //             System.out.print("Removing course: ");
    //             startTime2 = System.nanoTime();
    //             for (int i = 0; i < n && n <= courseCount; i++) {
    //                 hashMap.removeCourse(courseName[i]);
    //             }
    //             endTime2 = System.nanoTime();
    //             getTime(startTime2, endTime2);

    //             //Binary Search Tree
    //             System.out.println("\nBinary Search Tree: ");
    //             System.out.print("Removing course: ");
    //             startTime3 = System.nanoTime();
    //             for (int i = 0; i < n && n <= courseCount; i++) {
    //                 binarySearchTree.removeCourse(courseName[i]);
    //             }
    //             endTime3 = System.nanoTime();
    //             getTime(startTime3, endTime3);

    //             //Linked List
    //             System.out.println("\nLinked List: ");
    //             System.out.print("Removing course: ");
    //             startTime4 = System.nanoTime();
    //             for (int i = 0; i < n && n <= courseCount; i++) {
    //                 linkedList.removeCourse(courseName[i]);
    //             }
    //             endTime4 = System.nanoTime();
    //             getTime(startTime4, endTime4);
    //             break;

    //         /*********************
    //          * 3 - Modify course *
    //          *********************/
    //         case "3":
    //             Scanner read3 = new Scanner(System.in);
    //             System.out.println("\nPerformance of different data structures for modifying course method");
    //             System.out.print("Enter a number of courses (n) to be modified to stimulate the speed: ");
    //             n = read3.nextInt();
    //             //ArrayList
    //             System.out.println("\nArrayList: ");
    //             System.out.print("Modifying course: ");
    //             startTime1 = System.nanoTime();
    //             for (int i = 0; i < n && n <= courseCount; i++) {
    //                 arrayList.modifyCourse(courseName[i], "Thursday", "12.00", "14.00", "Auber");
    //             }
    //             endTime1 = System.nanoTime();
    //             getTime(startTime1, endTime1);

    //             //Hashmap
    //             System.out.println("\nHash Map: ");
    //             System.out.print("Modifying course: ");
    //             startTime2 = System.nanoTime();
    //             for (int i = 0; i < n && n <= courseCount; i++) {
    //                 hashMap.modifyCourse(courseName[i], "Thursday", "12.00", "14.00", "Auber");
    //             }
    //             endTime2 = System.nanoTime();
    //             getTime(startTime2, endTime2);

    //             //Binary Search Tree
    //             System.out.println("\nBinary Search Tree: ");
    //             System.out.print("Modifying course: ");
    //             startTime3 = System.nanoTime();
    //             for (int i = 0; i < n && n <= courseCount; i++) {
    //                 binarySearchTree.modifyCourse(courseName[i], "Thursday", "12.00", "14.00", "Aubert");
    //             }
    //             endTime3 = System.nanoTime();
    //             getTime(startTime3, endTime3);

    //             //Linked list
    //             System.out.println("\nLinkedList: ");
    //             System.out.print("Modifying course: ");
    //             startTime4 = System.nanoTime();
    //             for (int i = 0; i < n && n <= courseCount; i++) {
    //                 linkedList.modifyCourse(courseName[i], "Thursday", "12.00", "14.00", "Aubert");
    //             }
    //             endTime4 = System.nanoTime();
    //             getTime(startTime4, endTime4);
    //             break;

    //         /*********************
    //          * 4 - Search course *
    //          *********************/
    //         case "4":
    //             Scanner read4 = new Scanner(System.in);
    //             System.out.println("\nPerformance of different data structures for searching course method");

    //             //Array List
    //             System.out.println("\nArrayList: ");
    //             System.out.print("Searching course: ");
    //             startTime1 = System.nanoTime();
    //             arrayList.searchCoursebyName("Topology");
    //             endTime1 = System.nanoTime();
    //             getTime(startTime1, endTime1);

    //             //Hashmap
    //             System.out.println("\nHash Map: ");
    //             System.out.print("Searching course: ");
    //             startTime2 = System.nanoTime();
    //             hashMap.searchCoursebyName("Topology");
    //             endTime2 = System.nanoTime();
    //             getTime(startTime2, endTime2);

    //             //Binary Search Tree
    //             System.out.println("\nBinary Search Tree: ");
    //             System.out.print("Searching course: ");
    //             startTime3 = System.nanoTime();
    //             binarySearchTree.searchCoursebyName("Topology");
    //             endTime3 = System.nanoTime();
    //             getTime(startTime3, endTime3);

    //             //Linked list
    //             System.out.println("\nLinkedList: ");
    //             System.out.print("Searching course: ");
    //             startTime4 = System.nanoTime();
    //             linkedList.searchCoursebyName("Topology");
    //             endTime4 = System.nanoTime();
    //             getTime(startTime4, endTime4);

    //             break;

    //         /*******************
    //          * 5 - Add student *
    //          *******************/
    //         case "5":
    //             Scanner read5 = new Scanner(System.in);
    //             System.out.println("\nPerformance of different data structures for adding students method");
    //             System.out.print("Enter a number of students (n) to be added to stimulate the speed: ");
    //             n = read5.nextInt();
    //             //ArrayList
    //             System.out.println("\nArrayList: ");
    //             System.out.print("Adding students to course: ");
    //             startTime1 = System.nanoTime();
    //             for (int i=0; i<courseCount; i++) {
    //                 for (int s=0; s<n && n<=studentCount; s++){
    //                     arrayList.addStudent(studentName[s], studentId[s], courseName[i]);
    //                 }
    //             }
    //             endTime1 = System.nanoTime();
    //             getTime(startTime1, endTime1);

    //             //HashMap
    //             System.out.println("\nHashMap: ");
    //             System.out.print("Adding students to course: ");
    //             startTime2 = System.nanoTime();
    //             for (int i=0; i<courseCount; i++) {
    //                 for (int s=0; s<n && n<=studentCount; s++){
    //                     hashMap.addStudent(studentName[s], studentId[s], courseName[i]);
    //                 }
    //             }
    //             endTime2 = System.nanoTime();
    //             getTime(startTime2, endTime2);

    //             //Binary Search Tree
    //             System.out.println("\nBinary Search Tree: ");
    //             System.out.print("Adding students to course: ");
    //             startTime3 = System.nanoTime();
    //             for (int i=0; i<courseCount; i++) {
    //                 for (int s=0; s<n && n<=studentCount; s++){
    //                     binarySearchTree.addStudent(studentName[s], studentId[s], courseName[i]);
    //                 }
    //             }
    //             endTime3 = System.nanoTime();
    //             getTime(startTime3, endTime3);

    //             //Linked List
    //             System.out.println("\nLinked List: ");
    //             System.out.print("Adding students to course: ");
    //             startTime4 = System.nanoTime();
    //             for (int i=0; i<courseCount; i++) {
    //                 for (int s=0; s<n && n<=studentCount; s++){
    //                     linkedList.addStudent(studentName[s], studentId[s], courseName[i]);
    //                 }
    //             }
    //             endTime4 = System.nanoTime();
    //             getTime(startTime4, endTime4);

    //             break;

    //         /**********************
    //          * 6 - Remove student *
    //          **********************/
    //         case "6":
    //             Scanner read6 = new Scanner(System.in);
    //             System.out.println("\nPerformance of different data structures for removing students method");
    //             System.out.print("Enter a number of students (n) to be added to stimulate the speed: ");
    //             n = read6.nextInt();
    //             //ArrayList
    //             System.out.println("\nArrayList: ");
    //             System.out.print("Removing students to course: ");
    //             startTime1 = System.nanoTime();
    //             for (int i=0; i<courseCount; i++) {
    //                 for (int s=0; s<n && n<=studentCount; s++){
    //                     arrayList.removeStudent(courseName[i], studentId[s]);
    //                 }
    //             }
    //             endTime1 = System.nanoTime();
    //             getTime(startTime1, endTime1);

    //             //HashMap
    //             System.out.println("\nHashMap: ");
    //             System.out.print("Removing students to course: ");
    //             startTime2 = System.nanoTime();
    //             for (int i=0; i<courseCount; i++) {
    //                 for (int s=0; s<n && n<=studentCount; s++){
    //                     hashMap.removeStudent(courseName[i], studentId[s]);
    //                 }
    //             }
    //             endTime2 = System.nanoTime();
    //             getTime(startTime2, endTime2);

    //             //Binary Search Tree
    //             System.out.println("\nBinary Search Tree: ");
    //             System.out.print("Removing students to course: ");
    //             startTime3 = System.nanoTime();
    //             for (int i=0; i<courseCount; i++) {
    //                 for (int s=0; s<n && n<=studentCount; s++){
    //                     binarySearchTree.removeStudent(courseName[i], studentId[s]);
    //                 }
    //             }
    //             endTime3 = System.nanoTime();
    //             getTime(startTime3, endTime3);

    //             //Linked List
    //             System.out.println("\nLinked List: ");
    //             System.out.print("Removing students to course: ");
    //             startTime4 = System.nanoTime();
    //             for (int i=0; i<courseCount; i++) {
    //                 for (int s=0; s<n && n<=studentCount; s++){
    //                     linkedList.removeStudent(courseName[i], studentId[s]);
    //                 }
    //             }
    //             endTime4 = System.nanoTime();
    //             getTime(startTime4, endTime4);
    //             break;
        }

        scanner.close();
    }

    

    

}