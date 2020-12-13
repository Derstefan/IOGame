package org.iogame.main;


public class Main {

	public static void main(String[] args) {
		Server server = Server.getInstance();
		server.createGame("IOGame1");
	}

}
