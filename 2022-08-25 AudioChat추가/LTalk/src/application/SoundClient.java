package application;


import java.io.ByteArrayInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;


public class SoundClient {
	
	static AudioInputStream ais;
	static AudioFormat format;
	static int port = 52125;
	static float rate = 44100.0f;
	
	static DataLine.Info dataLineInfo;
	static SourceDataLine sourceDataLine;
	
	
	public static void start(){
		System.out.println("Client stated on port:"+port);
		System.setProperty("java.net.preferIPv4Stack", "true");
		try {
			
			InetAddress group = InetAddress.getByName("234.21.212.56");//멀티캐스트 주소를 
			MulticastSocket mSocket = new MulticastSocket(port);//생성과 동시에 포트에 바인드
			mSocket.joinGroup(group);//멀티캐스트 그룹에 조인
			
			byte[] reciveData = new byte[4096];// 받을 데이터의 크기
			
			format = new AudioFormat(rate, 16, 2, true, false);
			
			dataLineInfo =  new DataLine.Info(SourceDataLine.class, format);
			sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
			sourceDataLine.open();
			sourceDataLine.start();
			
			DatagramPacket recivePacket = new DatagramPacket(reciveData, reciveData.length);
			ByteArrayInputStream baiss = new ByteArrayInputStream(recivePacket.getData());
			
			while (true) {
				mSocket.receive(recivePacket);
				ais = new AudioInputStream(baiss, format, recivePacket.getLength());
				toSpeaker(recivePacket.getData());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void toSpeaker(byte soundbytes[]) {
		try {
			sourceDataLine.write(soundbytes, 0, soundbytes.length);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
