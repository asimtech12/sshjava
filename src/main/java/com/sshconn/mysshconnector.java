package com.sshconn;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.*;
import org.apache.log4j.Logger;

/**
 * Hello world!
 *
 */
public class mysshconnector {
	static Logger log = Logger.getLogger(mysshconnector.class.getName());

	public static void main( String[] args )
    {
     
try{
	System.out.println( "Hello World!" );
    // log.debug("Hello this is a debug message");
    // log.info("Hello this is an info message");
     JSch jsch=new JSch();
     log.info("Connecting to server");
     Session session=jsch.getSession("masim", "192.168.0.102", 22);
     session.setPassword("Welcome#1234");
     Properties config = new Properties();
     config.put("StrictHostKeyChecking", "no");
     session.setConfig(config);
     session.connect();
//this is asim

     ChannelExec channel=(ChannelExec) session.openChannel("exec");
     BufferedReader in=new BufferedReader(new InputStreamReader(channel.getInputStream()));
     channel.setCommand("pwd;");
     channel.connect();

     String msg=null;
     while((msg=in.readLine())!=null){
       System.out.println(msg);
     }

     channel.disconnect();
     session.disconnect();
}
//catch(JSchException  ex){
//	log.error(ex);
	
//}
catch(Exception allex){
	log.error(allex);
	
}
    }
}
