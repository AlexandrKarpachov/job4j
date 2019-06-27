package ru.job4j.bot;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Chat Client. Connects to a server, then sends and receives messages from it.
 *
 * @author Aleksander Karpachov
 * @version $Id$
 * @since 27.06.2019
 */
public class Client {
	private Socket socket;

	public Client(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		System.out.println("Подключение состоялось.");
		try (var out = new PrintWriter(this.socket.getOutputStream(), true);
		    var in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()))) {
			var console = new Scanner(System.in);
			String answer;
			var isEnd = false;
			while (!isEnd) {
				out.println(console.nextLine());
				answer = in.readLine();
				while (!answer.isEmpty()) {
					if (answer.startsWith("До свидания")) {
						isEnd = true;
					}
					System.out.println(answer);
					answer = in.readLine();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try (Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 5000)) {
			new Client(socket).run();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
