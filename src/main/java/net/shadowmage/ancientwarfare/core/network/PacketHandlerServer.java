package net.shadowmage.ancientwarfare.core.network;

import net.minecraft.network.NetHandlerPlayServer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ServerCustomPacketEvent;

public class PacketHandlerServer
{

@SubscribeEvent
public void onServerPacket(ServerCustomPacketEvent evt)
  {
  PacketBase packet = PacketBase.readPacket(evt.packet.payload());
  packet.setPlayer(((NetHandlerPlayServer)evt.handler).playerEntity);
  packet.execute();
  }


}
