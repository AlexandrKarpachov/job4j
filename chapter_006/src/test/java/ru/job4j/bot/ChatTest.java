package ru.job4j.bot;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.io.Resources;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ChatTest {
	private static final String NL = System.lineSeparator();
	private static final String BYE = String.format("До свидания. Надеюсь, ещё увидимся.%s%s", NL, NL);

	@Test
	public void whenAskByeThenAnswerBye() throws IOException {
		var answer = this.testServer("пока");

		assertThat(answer, is(BYE));
	}

	@Test
	public void whenAskNameThenAnswerByPattern() throws IOException {
		var ask = Joiner.on(NL).join("Как тебя зовут", "пока");
		var answer = this.testServer(ask);
		var expected = String.format("Меня, зовут  Оракл%s%s%s", NL, NL, BYE);

		assertThat(answer, is(expected));
	}

	@Test
	public void whenAskNonPatternStateThenAnswerRandom() throws IOException {
		var ask = Joiner.on(NL).join("Случайное выражение", "пока");
		var answer = this.testServer(ask);
		var result = answer.split(NL);
		var answers = Resources.toString(
				Resources.getResource("commonPhrases.txt"), Charsets.UTF_8);

		assert (answers.contains(result[0]));
	}

	@Test
	public void whenAskNonPatternQuestionThenAnswerRandomAnswer() throws IOException {
		var ask = Joiner.on(NL).join("Случайный вопрос?", "пока");
		var answer = this.testServer(ask);
		var result = answer.split(NL);
		var answers = Resources.toString(
				Resources.getResource("elusiveAnswers.txt"), Charsets.UTF_8);

		assert (answers.contains(result[0]));
	}

	private String testServer(String ask) throws IOException {
		var out = new ByteArrayOutputStream();
		var in = new ByteArrayInputStream(ask.getBytes());
		Socket socket = mock(Socket.class);
		when(socket.getInputStream()).thenReturn(in);
		when(socket.getOutputStream()).thenReturn(out);
		Server server = new Server(socket);
		server.run();
		return out.toString();
	}

}
