package ru.job4j.pools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Aleksandr Karpachov
 * @version 1.0
 * @since 02.10.2019
 */
public class EmailNotification {
	private static final Logger LOG = LoggerFactory.getLogger(EmailNotification.class);
	private final static String SUBJECT = "Notification {username} to email {email}";
	private final static String BODY = "Add a new event to {username}";
	private final ExecutorService pool = Executors.newFixedThreadPool(
			Runtime.getRuntime().availableProcessors()
	);

	public void emailTo(User user) {
		var subject = SUBJECT.replaceAll("\\{username}", "nam")
				.replaceAll("\\{email}", "gf");
		var body = BODY.replaceAll("\\{username}", "nam");
		this.pool.submit(() -> send(subject, body, user.getEmail()));
	}

	public void send(String subject, String body, String email) {

	}

	public void close() {
		pool.shutdown();
		while (!pool.isTerminated()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				LOG.error(e.getMessage(), e);
			}
		}
	}
}
