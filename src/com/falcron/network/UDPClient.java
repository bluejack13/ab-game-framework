package com.falcron.network;

import java.awt.Graphics2D;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Vector;

import com.ab2ds.util.GameData;
import com.ab2ds.util.GameObject;
import com.falcron.core.Renderable;

public class UDPClient implements Renderable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DatagramSocket socket;
	private InetAddress address;
	private int port;
	private byte[] sendData, receiveData;
	private Vector<Renderable> renderables;
	
	private Thread sendingThread = new Thread(new Runnable() {
		@Override
		public void run() {
			while(true){
				//ok
					GameData data = new GameData(renderables);
					sendObject(data, UDPClient.this.address);				
			}
		}
	});
	
	private	Thread receivingThread = new Thread(new Runnable() {
		@Override
		public void run() {
			while(true){
				System.out.println("blocked by this shit!");
				GameData tmp = (GameData) receiveObject();
				if(tmp != null){
					renderables = tmp.renderables;
				}
			}
		}
	});
	
	public UDPClient(String host, int port, int receiveData) {
		try {
			this.renderables = new Vector<Renderable>();
			this.port = port;
			this.receiveData = new byte[receiveData];
			this.socket = new DatagramSocket();
			//socket.setSoTimeout(2000);
			this.address = InetAddress.getByName(host);
			receivingThread.start();
			sendingThread.start();
		} catch (Exception e) {
			System.out.println("err"+e.toString());
		}
	}
	
	public void sendObject(Object o, InetAddress address){
		try {
			ByteArrayOutputStream ostream = new ByteArrayOutputStream();
			ObjectOutputStream oo = new ObjectOutputStream(ostream);
			oo.writeObject(o);
			oo.close();
			sendData = ostream.toByteArray();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
			socket.send(sendPacket);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	public Object receiveObject(){
		try {
			DatagramPacket packet = new DatagramPacket(receiveData, receiveData.length);
			socket.receive(packet);
			ObjectInputStream istream = new ObjectInputStream(new ByteArrayInputStream(receiveData));
			Object o = istream.readObject();
			istream.close();
			return o;
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}
	
	@Override
	public void update(float delta) {
		//System.out.println("My render : "+renderables.size());
	}
	
	public void addRenderables(Renderable r){
		this.renderables.add(r);
	}

	@Override
	public void render(Graphics2D g2d) {
			for(Renderable r : renderables){
				((GameObject)r).position.x += 0.1f;
				r.render(g2d);
			}
	}

}
