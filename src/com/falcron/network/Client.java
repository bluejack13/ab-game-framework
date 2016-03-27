package com.falcron.network;

import java.net.InetAddress;

import com.ab2ds.util.GameData;

public class Client {
	public InetAddress address;
	public GameData gamedata;
	public Client(InetAddress address, GameData gamedata) {
		super();
		this.address = address;
		this.gamedata = gamedata;
	}
	
	
}
