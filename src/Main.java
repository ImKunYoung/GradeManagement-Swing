import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class Main implements ActionListener {

    private MyPanel1 myPanel1;
    private MyPanel2 myPanel2;
    private MyPanel3 myPanel3;
    private MyPanel4 myPanel4;


    public Main() {
        JFrame frame = new JFrame("MyPanel");

        // init panel
        myPanel1 = new MyPanel1();
        myPanel2 = new MyPanel2();
        myPanel3 = new MyPanel3();
        myPanel4 = new MyPanel4();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);

        frame.getContentPane().add(myPanel1, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout(1, 2)); // Create a new panel with a BorderLayout with two rows
        centerPanel.add(myPanel2, BorderLayout.NORTH); // Add MyPanel2 to the NORTH position
        centerPanel.add(myPanel3, BorderLayout.SOUTH); // Add MyPanel3 to the SOUTH position
        frame.getContentPane().add(centerPanel, BorderLayout.CENTER); // Add the centerPanel to the CENTER position

        JScrollPane scrollPane = new JScrollPane(myPanel4);
        myPanel4.setPreferredSize(new Dimension(frame.getSize().width, frame.getSize().height * 3 / 4));
        frame.getContentPane().add(scrollPane, BorderLayout.SOUTH);


        frame.pack();
        frame.setVisible(true);

        // Add action listeners to the buttons
        myPanel2.getJcomp1().addActionListener(this);
        myPanel2.getJcomp2().addActionListener(this);
        myPanel2.getJcomp3().addActionListener(this);
        myPanel2.getJcomp4().addActionListener(this);
        myPanel2.getJcomp5().addActionListener(this);
        myPanel3.getJcomp3().addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == myPanel2.getJcomp1()) {
            // Insert a new record
            String name = myPanel1.getTextField1().getText();
            String email = myPanel1.getTextField2().getText();
            String phone = myPanel1.getTextField3().getText();
            // Insert the new record into the database
            insertRecord(name, email, phone);
            // Clear the text fields
            myPanel1.getTextField1().setText("");
            myPanel1.getTextField2().setText("");
            myPanel1.getTextField3().setText("");
            // completed message
            myPanel4.setJcomp1("입력 완료");
        } else if (e.getSource() == myPanel2.getJcomp2()) {
            // Handle output button action
            String output = "";
            // Retrieve the data from display/file.txt
            output = retrieveRecords();
            // Output the data in the text area
            myPanel4.setJcomp1(output);
        } else if (e.getSource() == myPanel2.getJcomp3()) {
            // Handle modify button action
            // Get the text from the text fields
            String name = myPanel1.getTextField1().getText();
            String email = myPanel1.getTextField2().getText();
            String phone = myPanel1.getTextField3().getText();
            // Update the record in the database
            updateRecord(name, email, phone);
            // Update the table with the new data
            updateTable();
            // completed message
            myPanel4.setJcomp1("수정 완료");
        } else if (e.getSource() == myPanel2.getJcomp4()) {
            // Handle delete button action
            // Get the text from the text fields
            String name = myPanel1.getTextField1().getText();
            String email = myPanel1.getTextField2().getText();
            String phone = myPanel1.getTextField3().getText();
            // Delete the record from the database
            try {
                deleteRecord(name, email, phone);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            // Update the table with the new data
            updateTable();
            // completed message
            myPanel4.setJcomp1("삭제 완료");
        } else if (e.getSource() == myPanel2.getJcomp5()) {
            // Handle save button action
            // Get the text from the text area
            String name = myPanel1.getTextField1().getText();
            String email = myPanel1.getTextField2().getText();
            String phone = myPanel1.getTextField3().getText();
            // Save the text to the file
            saveRecords(name, email, phone);
            // completed message
            myPanel4.setJcomp1("저장 완료");
        } else if (e.getSource() == myPanel3.getJcomp3()) {
            // Handle find button action
            String name = myPanel3.getTextField4().getText();
            String foundRecords = findRecords(name);
            // Output the found records in the text area
            myPanel4.setJcomp1(foundRecords);
        }
    }

    private String findRecords(String name) {
        StringBuilder records = new StringBuilder();
        try {
            File file = new File("./display/file.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(name)) {
                    records.append(line).append("\n");
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records.toString();
    }

    private void updateTable() {
        String data = retrieveRecords(); // retrieve records from display/file.txt
        myPanel4.setJcomp1(data); // set the data in the text area
        myPanel4.repaint(); // repaint the text area to display the updated data
    }

    private void deleteRecord(String name, String pid, String score) throws IOException {
        // Open the file
        File file = new File("./display/file.txt");
        Scanner scanner = null;
        scanner = new Scanner(file);
        StringBuilder sb = new StringBuilder();

        // Read the file line by line
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] fields = line.split(",");

            // Check if the record matches the specified name, email, or phone
            if (!fields[0].equals(name) && !fields[1].equals(pid) && !fields[2].equals(score)) {
                sb.append(line).append("\n");
            }
        }

        // Close the file
        scanner.close();

        // Write the updated data to the file
        FileWriter fw = new FileWriter(file);
        fw.write(sb.toString());
        fw.close();

    }

    private void saveRecords(String name, String pid, String score) {
        try {
            // Open the file to write the records to
            FileWriter fileWriter = new FileWriter("./display/file.txt", true); // true specifies that we are appending to the file
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Write the record to the file
            bufferedWriter.write(name + "," + pid + "," + score);
            bufferedWriter.newLine(); // Add a new line after writing the record

            // Close the file
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateRecord(String name, String pid, String score) {
        this.updateTable();
        try {
            // Read the file line by line
            File file = new File("./display/file.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            StringBuilder updatedFile = new StringBuilder();
            boolean found = false;
            // Loop through each line in the file
            while ((line = reader.readLine()) != null) {
                String[] record = line.split(",");
                // If the PID matches the one we are looking for, update the score
                if (record[0].equals(pid)) {
                    found = true;
                    record[2] = score;
                }
                // Append the updated line to the StringBuilder
                updatedFile.append(String.join(",", record)).append("\n");
            }
            reader.close();
            // If the record was found, write the updated file to disk
            if (found) {
                FileWriter writer = new FileWriter(file);
                writer.write(updatedFile.toString());
                writer.close();
            } else {
                System.out.println("Record with PID " + pid + " was not found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String retrieveRecords() {
        StringBuilder records = new StringBuilder();
        try {
            File file = new File("./display/file.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                records.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records.toString();
    }

    private void insertRecord(String name, String pid, String phone) {
        // Insert a new record into display/file.txt
        File file = new File("./display/file.txt");
        try (FileWriter fw = new FileWriter(file, true)) { // true for append mode
            String newRecord = name + "," + pid + "," + phone + "\n"; // create a new record in the desired format
            fw.write(newRecord); // write the new record to the file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}