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

            // System.out.println(question2.getQuestionID());    

            // System.out.println(demo.getQuestionNumberfromNode("002"));
            // demo.deleteNode("002");
            // demo.setQuestionNumberForward(demo.getRoot(), 2);
    
            // System.out.println(demo.toString());

            // demo.insertNode(question6);
            // System.out.println(demo.toString());

            int count = demo.count_nodes(demo.getRoot());
        
            // System.out.println("The total number of nodes in the given complete binary tree are: "+count);

            System.out.println("================================================");
            // demo.printFullNode(demo.getRoot());
            // System.out.println(demo.toString());
            
            // TreeNode temp_node1 = demo.searchNode("004");
            // Question temp1 = temp_node1.data;

            // demo.deleteNode("004");
            // // System.out.println(demo.getRoot().data.getQuestionID());
            // demo.setQuestionNumberForward(demo.getRoot(), 4);
            // System.out.println(demo.toString());


            // System.out.println("--------------------------------");    
            // demo.printFullNode(demo.getRoot());

            // TreeNode temp_node2 = demo.searchNode("001");
            // Question temp2 = temp_node2.data;

            // demo.deleteNode("001");
            // // System.out.println(demo.getRoot().data.getQuestionID());
            // demo.setQuestionNumberBackward(demo.getRoot(), 1);
            // System.out.println(demo.toString());

            // System.out.println("--------------------------------");    
            // demo.printFullNode(demo.getRoot());
            
            // // temp1.setQuestionNumber(2); //ori
            // // temp2.setQuestionNumber(1); //after
            
            // // System.out.println("temp1 id: " + temp1.getQuestionID() + " number: "+ temp1.getQuestionNumber());
            // // System.out.println("temp2 id: " + temp2.getQuestionID() + " number: "+ temp2.getQuestionNumber());
            
            // // demo.insertNode(temp1);
            // // demo.insertNode(temp2);
            
            TreeNode ori = demo.searchNode("001");
            int ori_number = ori.data.getQuestionNumber();
            System.out.println("ori number: " + ori_number);

            TreeNode after = demo.searchNode("004");
            int after_number = after.data.getQuestionNumber();
            System.out.println("after_number: " + after_number);

            demo.resetQuestionNumber(demo.getRoot(), ori, after, ori_number, after_number);

            demo.setQuestionNumberBackward(demo.getRoot(), after_number-1);
            // question4.setQuestionNumber(after_number);
            // demo.insertNode(question4);

            demo.printFullNode(demo.getRoot(), count);
            System.out.println(demo.toString());
            System.out.println(demo.searchNodeBasedonNumber(demo.getRoot(), 4).data.getQuestionID());

    }
}
