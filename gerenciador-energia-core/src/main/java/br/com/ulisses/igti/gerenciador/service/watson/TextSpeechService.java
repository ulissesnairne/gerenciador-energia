package br.com.ulisses.igti.gerenciador.service.watson;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.stereotype.Service;

import com.ibm.cloud.sdk.core.service.security.IamOptions;
import com.ibm.watson.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.text_to_speech.v1.model.SynthesizeOptions;
import com.ibm.watson.text_to_speech.v1.util.WaveUtils;

@Service
public class TextSpeechService {

	private static final String API_KEY = "3zKuBLuJ5LZ-ahfYDblrs2nA8H5CSDe72fzWpHRgV8qY";
	private static final String URL = "https://stream.watsonplatform.net/text-to-speech/api";

	private IamOptions getOption() {
		IamOptions options = new IamOptions.Builder().apiKey(API_KEY).build();
		return options;
	}

	public void transformar(String text, String filename) {
		TextToSpeech textToSpeech = new TextToSpeech(getOption());
		textToSpeech.setEndPoint(URL);

		try {
			SynthesizeOptions synthesizeOptions = new SynthesizeOptions.Builder().text(text)
					.accept(SynthesizeOptions.Accept.AUDIO_WAV).voice(SynthesizeOptions.Voice.PT_BR_ISABELAVOICE)
					.build();

			InputStream inputStream = textToSpeech.synthesize(synthesizeOptions).execute().getResult();

			InputStream in = WaveUtils.reWriteWaveHeader(inputStream);

			OutputStream out = new FileOutputStream(
					"/Users/ulissesnairnedealmeida/Downloads/audios/".concat(filename).concat(".wav"));
			byte[] buffer = new byte[1024];
			int length;
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}

			out.close();
			in.close();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
