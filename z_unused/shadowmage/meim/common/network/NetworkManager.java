package shadowmage.meim.common.network;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraftforge.fml.common.network.IPacketHandler;
import net.minecraftforge.fml.common.network.Player;

public class NetworkManager  implements IPacketHandler
{

@Override
public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
  {
 
  }

}
