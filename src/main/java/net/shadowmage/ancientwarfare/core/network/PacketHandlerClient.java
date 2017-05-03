package net.shadowmage.ancientwarfare.core.network;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientCustomPacketEvent;

public class PacketHandlerClient
{

@SubscribeEvent
public void onClientPacket(ClientCustomPacketEvent evt)
  {
  PacketBase packet = PacketBase.readPacket(evt.packet.payload());
  packet.setPlayer(Minecraft.getMinecraft().thePlayer);
  packet.execute();
  }

}
