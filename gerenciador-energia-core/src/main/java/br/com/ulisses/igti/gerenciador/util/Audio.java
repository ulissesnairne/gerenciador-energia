package br.com.ulisses.igti.gerenciador.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

import org.springframework.stereotype.Service;

import net.sourceforge.javaflacencoder.FLACFileWriter;

@Service
public class Audio {

	private static final AudioFormat format = new AudioFormat(16000.0f, 16, 1, true, false);

	public byte[] capturar() {
		try {
			TargetDataLine microphone = AudioSystem.getTargetDataLine(format);

			DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
			microphone = (TargetDataLine) AudioSystem.getLine(info);
			microphone.open(format);

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int numBytesRead;
			int CHUNK_SIZE = 1024;
			byte[] data = new byte[microphone.getBufferSize() / 5];
			microphone.start();

			int bytesRead = 0;

			try {
				while (bytesRead < 100000) {
					numBytesRead = microphone.read(data, 0, CHUNK_SIZE);
					bytesRead = bytesRead + numBytesRead;
					System.out.println(bytesRead);
					out.write(data, 0, numBytesRead);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			byte audioData[] = out.toByteArray();

			microphone.close();

			return audioData;

		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void tocarSom(String arquivo) {
		if (arquivo.contains(".wav")) {
			this.playWav(new File(arquivo));
		} else {
			byte[] audioData = null;
			try {
				audioData = Files.readAllBytes(Paths.get(arquivo));
			} catch (IOException e) {
				e.printStackTrace();
			}
			tocarSom(audioData);
		}

	}

	private void playWav(File file) {
		try {
			final Clip clip = (Clip) AudioSystem.getLine(new Line.Info(Clip.class));

			clip.addLineListener(new LineListener() {
				@Override
				public void update(LineEvent event) {
					if (event.getType() == LineEvent.Type.STOP)
						clip.close();

				}
			});

			clip.open(AudioSystem.getAudioInputStream(file));
			clip.start();
		} catch (Exception exc) {
			exc.printStackTrace(System.out);
		}
	}

	public void tocarSom(byte[] audioData) {
		InputStream byteArrayInputStream = new ByteArrayInputStream(audioData);
		AudioInputStream audioInputStream = new AudioInputStream(byteArrayInputStream, format,
				audioData.length / format.getFrameSize());
		DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, format);
		SourceDataLine sourceDataLine = null;
		try {
			sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
			sourceDataLine.open(format);
		} catch (LineUnavailableException e1) {
			e1.printStackTrace();
		} finally {
			if (sourceDataLine != null) {
				sourceDataLine.start();
				int cnt = 0;
				byte tempBuffer[] = new byte[10000];
				try {
					while ((cnt = audioInputStream.read(tempBuffer, 0, tempBuffer.length)) != -1) {
						if (cnt > 0) {
							sourceDataLine.write(tempBuffer, 0, cnt);
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				sourceDataLine.drain();
				sourceDataLine.close();
			}
		}
	}

	public void salvar(String caminho, String nome, byte[] audio) throws IOException {
		InputStream byteArrayInputStream = new ByteArrayInputStream(audio);
		AudioInputStream audioInputStream = new AudioInputStream(byteArrayInputStream, format,
				audio.length / format.getFrameSize());

		AudioSystem.write(audioInputStream, FLACFileWriter.FLAC,
				new File(caminho.concat("/").concat(nome).concat(".flac")));
	}

}
