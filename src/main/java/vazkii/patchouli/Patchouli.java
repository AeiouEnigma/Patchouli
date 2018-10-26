package vazkii.patchouli;

import java.lang.management.ManagementFactory;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import vazkii.patchouli.common.base.CommonProxy;

// TODO port multiblock system
// TODO port right click handler

@Mod(modid = Patchouli.MOD_ID, name = Patchouli.MOD_NAME)
public class Patchouli {

	public static boolean debug = ManagementFactory.getRuntimeMXBean().getInputArguments().toString().indexOf("-agentlib:jdwp") > 0;
	
	// Mod Constants
	public static final String MOD_ID = "patchouli";
	public static final String MOD_NAME = "Patchouli";
	public static final String BUILD = "GRADLE:BUILD";
	public static final String VERSION = "GRADLE:VERSION-" + BUILD;
	public static final String PREFIX = MOD_ID + ":";

	public static final String COMMON_PROXY = "vazkii.patchouli.common.base.CommonProxy";
	public static final String CLIENT_PROXY = "vazkii.patchouli.client.base.ClientProxy";

	@Instance(value = MOD_ID)
	public static Patchouli instance;

	@SidedProxy(clientSide = CLIENT_PROXY, serverSide = COMMON_PROXY)
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@EventHandler
	public void post(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}
	
	// TODO move to config
	@SubscribeEvent
	public void onConfigChanged(OnConfigChangedEvent event) {
		if(event.getModID().equals(Patchouli.MOD_ID))
			ConfigManager.sync(Patchouli.MOD_ID, Config.Type.INSTANCE);
	}

}

