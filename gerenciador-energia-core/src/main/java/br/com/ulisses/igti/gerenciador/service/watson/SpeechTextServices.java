package br.com.ulisses.igti.gerenciador.service.watson;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.springframework.stereotype.Service;

import com.ibm.cloud.sdk.core.service.security.IamOptions;
import com.ibm.watson.speech_to_text.v1.SpeechToText;
import com.ibm.watson.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.speech_to_text.v1.model.SpeechRecognitionResults;
import com.ibm.watson.speech_to_text.v1.websocket.BaseRecognizeCallback;

@Service
public class SpeechTextServices {

	private static final String API_KEY = "YNaFVZODMkKEnpzMuXxHjNgyvVUYfeR4lluyfMGj1QYH";
	private static final String URL = "https://stream.watsonplatform.net/speech-to-text/api";
	private SpeechRecognitionResults recognitionResults = null;
	private static Boolean waitWatson = Boolean.FALSE;

	private IamOptions getOption() {
		IamOptions options = new IamOptions.Builder().apiKey(API_KEY).build();
		return options;
	}

	private void transformarTexto(String arquivo) {
		SpeechToText speechToText = new SpeechToText(getOption());
		speechToText.setEndPoint(URL);

		try {
			RecognizeOptions recognizeOptions = new RecognizeOptions.Builder().audio(new FileInputStream(arquivo))
					.contentType("audio/flac").model(RecognizeOptions.Model.PT_BR_BROADBANDMODEL).build();

			waitWatson = Boolean.TRUE;
			BaseRecognizeCallback baseRecognizeCallback = new BaseRecognizeCallback() {

				@Override
				public void onTranscription(SpeechRecognitionResults speechRecognitionResults) {
					recognitionResults = speechRecognitionResults;
				}

				@Override
				public void onDisconnected() {
					waitWatson = Boolean.FALSE;
				}

			};

			speechToText.recognizeUsingWebSocket(recognizeOptions, baseRecognizeCallback);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public String retornarTexto(String arquivo) {
		transformarTexto(arquivo);
		while (waitWatson) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(recognitionResults);

		if (recognitionResults.getResults() != null
				&& recognitionResults.getResults().get(0).getAlternatives() != null
				&& recognitionResults.getResults().get(0).getAlternatives().size() > 0) {
			return recognitionResults.getResults().get(0).getAlternatives().get(0).getTranscript();
		} else {
			return "";
		}

	}

}
