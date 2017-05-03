package net.shadowmage.ancientwarfare.nei_plugin;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.shadowmage.ancientwarfare.core.config.AWLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;

@Mod
(
name = "Ancient Warfare NEI Plugin",
modid = "AncientWarfareNEIPlugin",
version = "@VERSION@",
dependencies = "required-after:AncientWarfare"
)

public class AncientWarfareNEIPlugin
{
@Instance(value="AncientWarfareNEIPlugin")
public static AncientWarfareNEIPlugin instance;


@EventHandler
public void preInit(FMLPreInitializationEvent evt)
  {
  MinecraftForge.EVENT_BUS.register(this);
//  AWLog.log("Ancient Warfare NEI Plugin Pre-Init started");
//    
//  AWLog.log("Ancient Warfare NEI Plugin Pre-Init completed");
  }

@EventHandler
public void init(FMLInitializationEvent evt)
  {
//  AWLog.log("Ancient Warfare NEI Plugin Init started"); 
//
//  AWLog.log("Ancient Warfare NEI Plugin Init completed");
  }

@EventHandler
public void postInit(FMLPostInitializationEvent evt)
  {
//  AWLog.log("Ancient Warfare NEI Plugin Post-Init started"); 
// 
//  AWLog.log("Ancient Warfare NEI Plugin Post-Init completed.  Successfully completed all loading stages.");
  }

boolean loaded = false;

@SubscribeEvent
public void worldLoaded(WorldEvent.Load evt)
  {
  if(evt.world.isRemote && !loaded) 
    {
    loaded = true;
    @SuppressWarnings("rawtypes")
    Class clz;
    try
      {
      clz = Class.forName("codechicken.nei.api.API");
      if(clz!=null)
        {
        AWLog.log("NEI Detected, attempting load of NEI Plugin");
        @SuppressWarnings("rawtypes")
        Class clz2 = Class.forName("net.shadowmage.ancientwarfare.nei_plugin.AWNeiProxyActual");
        try
          {
          AWNeiProxyBase proxy = (AWNeiProxyBase) clz2.newInstance();
          proxy.load();
          AWLog.log("NEI Plugin loaded successfully");
          } 
        catch (InstantiationException e)
          {
          e.printStackTrace();
          } 
        catch (IllegalAccessException e)
          {
          e.printStackTrace();
          }
        }
      } 
    catch (ClassNotFoundException e)
      {
      e.printStackTrace();
      AWLog.log("Skipping loading of NEI plugin, NEI not found!");
      }
    }
  }

public void clientLoginEvent(ClientConnectedToServerEvent evt)
  {
//  evt.
  }


}
