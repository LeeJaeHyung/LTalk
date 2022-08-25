package application;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;

public class SoundSender {

	public static void start() {
		System.out.println("SoundSender.start() is Started");
		System.setProperty("java.net.prefetIPv4Stack", "ture");

		TargetDataLine line;
		DatagramPacket dgp;

		AudioFormat.Encoding encoding = AudioFormat.Encoding.PCM_SIGNED;
		float rate = 44100.0f;
		int channels = 2;
		int sampleSize = 16;
		boolean bigEndian = false;

		InetAddress addr;
		int port = 52125;

		System.out.println("Server started on port: " + port);

		AudioFormat format = new AudioFormat(encoding, rate, sampleSize, channels, (sampleSize / 8) * channels, rate,
				bigEndian);

		DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

		if (!AudioSystem.isLineSupported(info)) {
			System.out.println("Data Line not supported!");
		}

		try {
			line = (TargetDataLine) AudioSystem.getLine(info);
			line.open(format);
			line.start();
			byte[] data = new byte[4096];

			addr = InetAddress.getByName("234.21.212.56");
			MulticastSocket socket = new MulticastSocket();

			while (true) {
				line.read(data, 0, data.length);
				dgp = new DatagramPacket(data, data.length, addr, port);
				socket.send(dgp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}