package bankingApp;

import java.io.*;

public class TransactionIdUtils {


    public static int getTransactionId() throws IOException {
        File file = new File("transactionsIds.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        int id = 0;
        String line;
        while((line = reader.readLine())!=null){
            id = Integer.parseInt(line.trim());
        }
        return id;
    }

    public static void saveTransactionId(int id) throws IOException {
        File file = new File("transactionsIds.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        writer.write(id+"\n");
        writer.close();
    }

}
