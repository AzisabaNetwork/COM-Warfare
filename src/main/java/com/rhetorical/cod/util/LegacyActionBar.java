package com.rhetorical.cod.util;

import com.rhetorical.cod.ComWarfare;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

/**
 * This class is used to send action bars to users running Minecraft 1.8.8
 * */
public class LegacyActionBar {

	public static void sendActionBarMessage(Player bukkitPlayer, String message) {
		if (!ComWarfare.hasProtocolLib())
			return;
		sendRawActionBarMessage(bukkitPlayer, "{\"text\": \"" + message + "\"}");
	}

	private static void sendRawActionBarMessage(Player player, String rawMessage) {
		if(rawMessage == null || rawMessage.isEmpty()) {
			return;
		}

		com.comphenix.protocol.events.PacketContainer chat = new com.comphenix.protocol.events.PacketContainer(com.comphenix.protocol.PacketType.Play.Server.CHAT);
		chat.getBytes().write(0, (byte)2);
		chat.getChatComponents().write(0, com.comphenix.protocol.wrappers.WrappedChatComponent.fromJson(rawMessage));

		try {
			com.comphenix.protocol.ProtocolLibrary.getProtocolManager().sendServerPacket(player, chat);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}