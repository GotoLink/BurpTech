package burptech;

import burptech.gui.GuiHandler;
import burptech.item.BucketHandler;
import burptech.item.crafting.RecipeManager;
import burptech.lib.Constants;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.Logger;

@Mod(modid = Constants.MOD_ID, name = Constants.MOD_NAME, useMetadata = true)
public class BurpTechCore
{
	@Instance(Constants.MOD_ID)
    public static BurpTechCore instance;
    public static Logger log = null;
    public static BurpTechConfig configuration;
    public static GuiHandler guiHandler; 
    
    @SidedProxy(clientSide = "burptech.client.ClientProxy", serverSide = "burptech.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInitialization(FMLPreInitializationEvent e)
    {
        // setup logger
        log = e.getModLog();
        configuration = BurpTechConfig.load(e.getModConfigurationDirectory());
        
        //gui handler
        guiHandler = new GuiHandler();

        MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);

        // register keyboard bindings

    }
    
    @EventHandler
    public void initialization(FMLInitializationEvent e)
    {
        // gui handlers
    	NetworkRegistry.INSTANCE.registerGuiHandler(instance, guiHandler);
    	
        // event handlers
    	if (configuration.enableSlimeSpawningRestrictions.getBoolean(true))
    		MinecraftForge.EVENT_BUS.register(new burptech.entity.monster.tweaks.EntitySlimeEventHandler());
    	
    	if (configuration.enableNetherSpawningRestrictions.getBoolean(true))
    		MinecraftForge.EVENT_BUS.register(new burptech.entity.monster.tweaks.EntityNetherMonsterEventHandler());
    	
    	if (configuration.enableMobsEatingOffOfGround.getBoolean(true))
    		MinecraftForge.EVENT_BUS.register(new burptech.entity.passive.tweaks.EntityAnimalEventHandler());
    	
    	if (configuration.enableMobsWandering.getBoolean(true))
    		MinecraftForge.EVENT_BUS.register(new burptech.entity.living.tweaks.EntityLivingEventHandler());
    	
    	if (configuration.enableGreedyVillagers.getBoolean(true))
    		MinecraftForge.EVENT_BUS.register(new burptech.entity.living.tweaks.EntityVillagerEventHandler());    		
    	
    	// tile entity registrations
    	
        // recipes
    	(new RecipeManager()).addRecipes();
    }
    
    @EventHandler
    public void postInitialization(FMLPostInitializationEvent e)
    {
    	// tweaks
    	if (configuration.disableEndermanGriefing.getBoolean(true))
    		burptech.entity.monster.tweaks.EntityEndermanTweaks.enableAntiGriefing();

        proxy.postInitialization();

        // mod integrations
        (new RecipeManager()).postInitialization();
    }
}