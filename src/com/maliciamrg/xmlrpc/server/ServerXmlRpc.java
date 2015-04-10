package com.maliciamrg.xmlrpc.server;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;

public class ServerXmlRpc {

	private static Logger rootLogger;
	private static final int port = 8080;

	public void testcommunication() {
		System.out.println("testcommunication Ok");
	}

	public Integer sum(int x, int y) {
		System.out.println("sum:x=" + x + " y=" + y);
		return new Integer(x + y);
	}

	public static void main(String[] args) {
		log();
		try {
			System.out.println("Attempting to start XML-RPC Server...");
			Thread.sleep(3000);

			WebServer webServer = new WebServer(port);
			XmlRpcServer xmlRpcServer = webServer.getXmlRpcServer();

			PropertyHandlerMapping phm = new PropertyHandlerMapping();
			/*
			 * Load handler definitions from a property file. The property file
			 * might look like: Calculator=org.apache.xmlrpc.demo.Calculator
			 * org.
			 * apache.xmlrpc.demo.proxy.Adder=org.apache.xmlrpc.demo.proxy.AdderImpl
			 */
			phm.load(Thread.currentThread().getContextClassLoader(),
					"MyHandlers.properties");
			//phm.addHandler("SXR",ServerXmlRpc.class);
			
			/*
			 * You may also provide the handler classes directly, like this:
			 * phm.addHandler("Calculator",
			 * org.apache.xmlrpc.demo.Calculator.class);
			 * phm.addHandler(org.apache
			 * .xmlrpc.demo.proxy.Adder.class.getName(),
			 * org.apache.xmlrpc.demo.proxy.AdderImpl.class);
			 */
			xmlRpcServer.setHandlerMapping(phm);

			XmlRpcServerConfigImpl serverConfig = (XmlRpcServerConfigImpl) xmlRpcServer
					.getConfig();
			serverConfig.setEnabledForExtensions(true);
			serverConfig.setContentLengthOptional(false);

			webServer.start();

			System.out.println("Started successfully.");
			System.out.println("Accepting requests. (Halt program to stop.)");
		} catch (Exception exception) {
			System.err.println("JavaServer: " + exception);
		}
	}

	public static void log() {
		rootLogger = Logger.getRootLogger();
		rootLogger.setLevel(Level.INFO);
		rootLogger.addAppender(new ConsoleAppender(new PatternLayout(
				"%-6r [%p] %c - %m%n")));
	}

}