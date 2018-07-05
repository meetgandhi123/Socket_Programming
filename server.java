import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.File;
 
public class server
{
	private static final String FILENAME = "server.txt";
    private static Socket socket;
 
    public static void main(String[] args)
    {
		BufferedWriter bw = null;
		FileWriter fw = null;

    	try
        {
            int port = 25003;
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("S : Server Started and listening to the port:"+port);
            //Server is running always. This is done using this while(true) loop
            while(true)
            {
                //Reading the message from the client
                socket = serverSocket.accept();
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String information = br.readLine();
                System.out.println("S : Message received from client is "+information);
                String returnMessage=information;
                //Sending the response back to the client.
                OutputStream os = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
//-----------------------
                try {
                        File file = new File(FILENAME);
                        if (!file.exists()) {
                            file.createNewFile();
                        }
                        // true = append file
                        fw = new FileWriter(file.getAbsoluteFile(), true);
                        bw = new BufferedWriter(fw);
                        bw.write(information);
                        bw.newLine();
                        System.out.println("Done");
                    }
                catch (IOException e) 
                    {
                        e.printStackTrace();
                    }

                finally 
                {
                    try {
                        if (bw != null)
                            bw.close();
                        if (fw != null)
                            fw.close();
                        } 
                    catch (IOException ex) 
                        {
                        ex.printStackTrace();
                        }
                }                
      
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                socket.close();
            }
            catch(Exception e){}
        }
    }
}