package ru.job4j.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A program for downloading files from the Internet with a speed limit
 *
 *
 * @author Aleksandr Karpachov
 * @version 1.0
 * @since 25.09.2019
 */
public class WGet {
	private static final Logger LOG = LoggerFactory.getLogger(WGet.class);

	public static void main(String[] args) throws IOException {
		var url = new URL(args[0]);
		int speed = Integer.parseInt(args[1]);
		var con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		var dw = new WGet();
		System.out.println(dw.download(con.getInputStream(), speed));
	}


	/**
	 * Method loads data from input stream with speed limit.
	 * @param in input stream.
	 * @param speedKb speed limit.
	 * @return file as string.
	 * @throws IOException if input stream was wrong.
	 */
	public String download(InputStream in, int speedKb) throws IOException {
		var result = new StringBuilder();
		int speedB = speedKb * 1024;
		int red = 0;
		long loadSize = 0;
		try (BufferedInputStream bis = new BufferedInputStream(in)) {
			while (red != -1) {
				long current = System.currentTimeMillis();
				while ((red != -1 && System.currentTimeMillis() < current + 1000)) {
					red = bis.read();
					result.append(red);
					loadSize++;
					if (loadSize >= speedB) {
						try {
							long delay = 1000 - (System.currentTimeMillis() - current);
							Thread.sleep(delay);
						} catch (InterruptedException e) {
							LOG.error(e.getMessage(), e);
						}
					}
				}
				loadSize = 0;
			}
		}
		return result.toString();
	}
}
