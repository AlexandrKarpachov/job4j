package ru.job4j.bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Chat Server. Wait a client connect, then handle input messages by {@link ru.job4j.bot.Bot}
 * and sends answers.
 *
 * @author Aleksander Karpachov
 * @version $Id$
 * @since 27.06.2019
 */
public class Server {
	private Socket socket;

	public Server(Socket socket) {
		this.socket = socket;
	}

	public void run() throws IOException {
		var out = new PrintWriter(this.socket.getOutputStream(), true);
		var in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		String answer;
		Bot bot = new Bot().init();
		do {
			answer = bot.sayInReturn(in.readLine());
			out.println(answer);
			out.println();
		} while (!answer.startsWith("До свидания"));
	}

	public static void main(String[] args) {
		try (Socket socket =  new ServerSocket(5000).accept()) {
			new Server(socket).run();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
