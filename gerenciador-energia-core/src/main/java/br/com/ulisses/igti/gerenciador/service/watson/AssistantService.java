package br.com.ulisses.igti.gerenciador.service.watson;

import java.util.List;
import java.util.logging.LogManager;

import org.springframework.stereotype.Service;

import com.ibm.cloud.sdk.core.service.security.IamOptions;
import com.ibm.watson.assistant.v2.Assistant;
import com.ibm.watson.assistant.v2.model.CreateSessionOptions;
import com.ibm.watson.assistant.v2.model.DeleteSessionOptions;
import com.ibm.watson.assistant.v2.model.DialogRuntimeResponseGeneric;
import com.ibm.watson.assistant.v2.model.MessageInput;
import com.ibm.watson.assistant.v2.model.MessageOptions;
import com.ibm.watson.assistant.v2.model.MessageResponse;
import com.ibm.watson.assistant.v2.model.RuntimeIntent;
import com.ibm.watson.assistant.v2.model.SessionResponse;

@Service
public class AssistantService {

	private static final String API_KEY = "w2kUH3ica2h6PhI2hwm-76OsusqI5yuXrBz0QGxIePFT";
	private static final String URL = "https://gateway.watsonplatform.net/assistant/api";
	private static final String ASSISTANT_ID = "18ecf074-1fd2-4a9c-b400-56dc7d85f28f";
	private String sessionId;
	private Assistant service;
	private String lastMessageReturn;
	private String lastIntentReturn;

	public AssistantService() {
		init();
		createService();
		createSession();
	}

	private void init() {
		LogManager.getLogManager().reset();
		sessionId = null;
		service = null;
		lastMessageReturn = null;
		lastIntentReturn = null;
	}

	private void createService() {
		// Set up Assistant service.
		service = new Assistant("2019-02-28", getOption());
		service.setEndPoint(URL);

	}

	private IamOptions getOption() {
		IamOptions options = new IamOptions.Builder().apiKey(API_KEY).build();
		return options;
	}

	private void createSession() {
		// Create session.
		CreateSessionOptions createSessionOptions = new CreateSessionOptions.Builder(ASSISTANT_ID).build();
		SessionResponse session = service.createSession(createSessionOptions).execute().getResult();
		this.sessionId = session.getSessionId();

	}

	private static void finalizeAssistant(Assistant service, String sessionId) {
		// We're done, so we delete the session.
		DeleteSessionOptions deleteSessionOptions = new DeleteSessionOptions.Builder(ASSISTANT_ID, sessionId).build();
		service.deleteSession(deleteSessionOptions).execute();
	}

	private static MessageResponse sendMessage(Assistant service, String sessionId, String inputText) {
		// Send message to assistant.
		MessageInput input = new MessageInput.Builder().text(inputText).build();
		MessageOptions messageOptions = new MessageOptions.Builder(ASSISTANT_ID, sessionId).input(input).build();
		MessageResponse response = service.message(messageOptions).execute().getResult();
		return response;
	}

	public void sendMessage(String message) {
		if (message.equalsIgnoreCase("sair")) {
			finalizeAssistant(service, sessionId);
		} else {
			MessageResponse response = sendMessage(service, sessionId, message);

			// If an intent was detected, print it to the console.
			List<RuntimeIntent> responseIntents = response.getOutput().getIntents();
			if (responseIntents.size() > 0) {
				this.lastIntentReturn = responseIntents.get(0).getIntent();
			} else {
				this.lastIntentReturn = null;
			}

			// Print the output from dialog, if any. Assumes a single text response.
			List<DialogRuntimeResponseGeneric> responseGeneric = response.getOutput().getGeneric();
			if (responseGeneric.size() > 0) {
				this.lastMessageReturn = response.getOutput().getGeneric().get(0).getText();
			} else {
				this.lastMessageReturn = null;
			}
		}

	}

	public String getLastMessageReturn() {
		return lastMessageReturn;
	}

	public String getLastIntentReturn() {
		return lastIntentReturn;
	}

}
