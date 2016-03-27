package com.falcron.network;

import java.awt.Graphics2D;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Vector;

import com.ab2ds.util.GameData;
import com.falcron.core.Renderable;

public class UDPServer implements Renderable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DatagramSocket socket;
	private byte[] receiveData, sendData;
	private int port;
	private Vector<Client> clients;
	
	Thread receivingThread = new Thread(new Runnable() {
		@Override
		public void run() {
			while(true){
				try {
					//can receive well
					GameData m = (GameData) receiveObject();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	});
	
	Thread sendingThread = new Thread(new Runnable() {
		@Override
		public void run() {
			while(true){
				//can send well
				Vector<Renderable> renders = new Vector<Renderable>();
				try {
					for(Client c : clients){
						renders.addAll(c.gamedata.renderables);
					}
					for(Client c : clients){
						sendObject(new GameData(renders), c.address);
					}
				} catch (Exception e) {
					System.out.println(e.toString());
				}
					
			}
		}
	});
	
	public UDPServer(int port, int bytes) {
		this.clients = new Vector<Client>();
		this.receiveData = new byte[bytes];
		this.port = port;
		try {
			this.socket = new DatagramSocket(port);
			//socket.setSoTimeout(1000);
			receivingThread.start();
			sendingThread.start();
		} catch (SocketException e) {
			e.printStackTrace();
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
	
	private void checkClient(InetAddress address, GameData data){
		if(address != null){
			boolean b = false;
			for(Client add : clients){
				if(add.address.equals(address)){
					add.gamedata.renderables = data.renderables;
					b = true;
					break;
				}
			}
			if(!b){
				clients.add(new Client(address, data));
			}
		}
	}
	public Object receiveObject(){
		try {
			DatagramPacket packet = new DatagramPacket(receiveData, receiveData.length);
			socket.receive(packet);
			
			ObjectInputStream istream = new ObjectInputStream(new ByteArrayInputStream(receiveData));
			Object o = istream.readObject();
			
			InetAddress add = packet.getAddress();
			sendObject((GameData)o, add);
			
			istream.close();
			checkClient(packet.getAddress(), (GameData)o);
			return o;
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}
	
	public Vector<Client> getClients(){
		return this.clients;
	}
	
	public int getAllClientRenderables(){
		int n=0;
		for(Client c : clients){
			n += c.gamedata.renderables.size();
		}
		return n;
	}
	
	@Override
	public void update(float delta) {
			//System.out.println("Client count : "+clients.size());
	}

	@Override
	public void render(Graphics2D g2d) {
			for(Client c : clients){
				for(Renderable r : c.gamedata.renderables){
					r.render(g2d);
				}
			}
	}

}
