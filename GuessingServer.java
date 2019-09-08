import java.io.PrintWriter;

import java.net.ServerSocket;

import java.net.Socket;

import java.util.Scanner;


public class GuessingServer {
	
public static void main(String [] args ){

		ServerSocket server;

		Socket client;
		
try{
			server = new ServerSocket(5150);

			System.out.println("server connected");

			boolean serverRun = true;
			
while(serverRun){
				
client = server.accept();

PrintWriter toCli = new PrintWriter(client.getOutputStream(), true);

Scanner scan = new Scanner(client.getInputStream());

String list = scan.nextLine();
				

			 
if(list.equals("SHUT DOWN")){
				 
serverRun = false;
					
scan.close();
				 	
toCli.close();
				 	
client.close();
					
server.close();
				
}
			 
else{
					
Scanner str = new Scanner(list);
					
int low = str.nextInt();
					
int high = str.nextInt();
					
int middle = (low + high) / 2;
					
toCli.println(middle);
					
str.close();
					

					
int i =0;
					
while(i < 9){
						
String findHigh = scan.next();
						
if(findHigh.equals("high")){
							
high = middle;
							
middle = (low + high) / 2;
							
toCli.println(middle);
						
}
						
else if(findHigh.equals("low")){
							
low = middle;
							
middle = (low + high) / 2;
							
toCli.println(middle);
						
}
						
else if(findHigh.equals("won")){
							
scan.close();
							
toCli.close();
							
client.close();
							
break;
						
}
						
else if(findHigh.equals("lost")){
							
scan.close();
							
toCli.close();
							
client.close();
							
break;
						
}
						
i++;
					
}
					
				
}
			
}
			
		
}
		
catch(Exception e){
			
		
}
		
		
	
}
}
