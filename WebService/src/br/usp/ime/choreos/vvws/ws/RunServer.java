package br.usp.ime.choreos.vvws.ws;

import javax.xml.ws.Endpoint;


public class RunServer {

	private static void runStore(){
		StoreWS service = new StoreWS();
		Endpoint endpoint = Endpoint.create(service);
		endpoint.publish("http://localhost:1234/Store");
	}
	
	private static void runSimpleStore(){
		SimpleStoreWS service = new SimpleStoreWS();
		Endpoint endpoint = Endpoint.create(service);
		endpoint.publish("http://localhost:1234/SimpleStore");
	}
	
	
	public static void main(String [] args){
		
		System.out.println("Server running");
		runStore();
		runSimpleStore();
	}
	
}