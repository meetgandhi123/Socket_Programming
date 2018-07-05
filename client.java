import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.io.FileReader;
import java.io.IOException;

class hey{
   public static Socket socket;
   
   void sendhere(String HH)
   {
	
	System.out.println("reached"+HH);
 	try
    	{
            String host = "localhost";
            int port = 25003;
            InetAddress address = InetAddress.getByName(host);
            socket = new Socket(address, port);
  
            //Send the message to the server
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
 
//            String information = "00100        151       546    68468        68 46 846    684 68 46  ";
            String sendMessage = HH + "\n";
            bw.write(sendMessage);
            bw.flush();
            System.out.println("C : Message sent to the server : "+sendMessage);
 
            //Get the return message from the server
/*          InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String message = br.readLine();
            System.out.println("C : Message received from the server : " +message); */

        }
	catch (Exception exception)
        {
            exception.printStackTrace();
        }
    finally
        {
            //Closing the socket
            try
            {
                socket.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }


    }
 }


public class client
{
//    public static final String FILENAME = "../../MF LOGS/one.txt"; 
    public static final String FILENAME = "one.txt"; 
    public static void main(String args[])
    {
            BufferedReader brz = null;
            FileReader frz = null;

			try {

            	//br = new BufferedReader(new FileReader(FILENAME));
            	frz = new FileReader(FILENAME);
            	brz = new BufferedReader(frz);

            	String CurrentLine;

	            while ((CurrentLine = brz.readLine()) != null) 
		            {
	    	            System.out.println("1");
	        	        hey H = new hey();
	            	    H.sendhere(CurrentLine);
	                	System.out.println(CurrentLine);
	//                	information=CurrentLine;
	            	}
        		} 
        	catch (IOException e) 
        	{
            	e.printStackTrace();
        	} 
        	finally 
        	{
            	try {
	                if (brz != null)
	                    brz.close();
	                if (frz != null)
	                    frz.close();
		            } 
		        catch (IOException ex) 
		        	{
	                ex.printStackTrace();
		            }
	        }
    }
}