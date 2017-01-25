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

// this code will do the ssh and will take 3 parameters
public class mysshconnector {
	static Logger log = Logger.getLogger(mysshconnector.class.getName());

	public static void main(String[] args) {

		try {
			if (args.length < 3) {
				log.error("please enter valid usage");
				System.exit(1);
			}
			String hostname = args[0];
			String username = args[1];
			String password = args[2];
			// log.debug("Hello this is a debug message");
			// log.info("Hello this is an info message");
			JSch jsch = new JSch();
			log.info("Connecting to server");
			Session session = jsch.getSession(username, hostname, 22);
			session.setPassword(password);
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
			log.info("Connected to the server :::" + hostname);

			ChannelExec channel = (ChannelExec) session.openChannel("exec");
			BufferedReader in = new BufferedReader(new InputStreamReader(channel.getInputStream()));
			// printing the present working directory after logging into the host
			channel.setCommand("pwd;");
			channel.connect();

			String msg = null;
			while ((msg = in.readLine()) != null) {
				System.out.println(msg);
			}

			channel.disconnect();
			session.disconnect();
		}
		// catch(JSchException ex){
		// log.error(ex);

		// }
		catch (Exception allex) {
			log.error(allex.getMessage());

		}
	}
}
