/**
* Author:Pravalika Gunti
* Brief Description: This is client program which sends magic packet in order to wake up
sleeping computer*/
/*Importing packages*/
import java.net.*;
/*start of class*/
public class WOLClient{
	 

/*Destination port is assigned as '7' and it cannot be modified as it is declared with keyword
'final'*/
private static final int DEST_PORT=7;
	 

/*Server name is set as '255.255.255.255' and it cannot be modified as it is declared with
keyword 'final'*/
private static final String SERVER_NAME="255.255.255.255";
	 

/*Start of main method*/
public static void main(String[] args){ 	 	 

try
{	 

	 

/*Whether an argument is given or not during program execution is checked*/	 

if(args.length==1)	 	 	 

{
/*Here MAC_ID from argument*/
String MAC_ID=args[0];
/*creating byte array which is used to store mac address*/
byte[] mac_addr = new byte[6];
/*User gives input of mac address as 'AA:BB:CC:DD:EE:FF'. As ':' is not used in hexadecimal
representation and packet. In order to split mac address split function is used */
String[] split_addr = MAC_ID.split(":");
/*Mac address should be exactly of 6 bytes. Checking provided mac address is 6 bytes of
length or not */
if(split_addr.length==6)
{
/*If given 
/*and the MAC address of the sleeping computer (6 bytes) repeated sixteen times for a total of
102 bytes.*/
/*creating new byte array with size of 102 bytes*/
byte[] s_c_address= new byte[102];	 	 	 	
/*The frame body consists of the Ethernet broadcast address (FF:FF:FF:FF:FF:FF) */
/*and it is stored in first 6 bytes*/
String[] ff={"ff","ff","ff","ff","ff","ff"};
for(int i=0;i<6;i++)
{
s_c_address[i]=(byte)Integer.parseInt(ff[i],16);
}
int i=6;
/*the MAC address of the sleeping computer (6 bytes) repeated sixteen times*/
/* so storing remaining 96 bytes in order to complete frame body*/
// This method is used to copy values from one array to another array
while(i<s_c_address.length)
{
// increasing array length by six times after copying elements into array
for(int j=0;j<6;j++)
{
s_c_address[i]=mac_addr[j];
i=i+1;
}
}
//Create IP address object from IP address of destination
InetAddress dstIP;
dstIP = InetAddress.getByName(SERVER_NAME);
// Creating UDP socket and UDP packet.
//to establish connection between the devices, the destination address
// and port number is specified in the packet.
DatagramPacket sendPkt = new
DatagramPacket(s_c_address,s_c_address.length,dstIP,DEST_PORT);//(byte[] buf, int length,
InetAddress address, int port)
DatagramSocket clientSocket = new DatagramSocket();
//Send packet to server
clientSocket.send(sendPkt);
//Closing socket before exiting
System.out.println("Wake up packet is send to the limited broadcast address:
"+SERVER_NAME+" and Mac address: "+MAC_ID+" using destination port:"+DEST_PORT);
clientSocket.close();
}
else
{
System.out.println("give input as 'java FileName MacAddress'");
}
}	 	 	 

else
{
System.out.println("Incorrect Mac Address and Mac address format should be
AA:BB:CC:DD:EE:FF");	 	 	 	 }
}	 	 

catch (Exception e)
{
e.printStackTrace();
}
}