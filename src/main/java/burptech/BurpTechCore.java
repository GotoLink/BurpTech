package burptech;

import burptech.item.BucketHandler;
import burptech.item.crafting.*;
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
public final class BurpTechCore
{
	@Instance(Constants.MOD_ID)
    public static BurpTechCore instance;
    @SidedProxy(clientSide = "burptech.client.ClientProxy", serverSide = "burptech.CommonProxy")
    public static CommonProxy proxy;
    public static Logger log;
    public static BurpTechConfig configuration;

    @EventHandler
    public void preInitialization(FMLPreInitializationEvent e)
    {
        // setup logger
        log = e.getModLog();
        configuration = BurpTechConfig.load(e.getModConfigurationDirectory());

        MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);
    }
    
    @EventHandler
    public void initialization(FMLInitializationEvent e)
    {
        // gui handlers
    	NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
    	
        // event handlers
    	if (configuration.enableSlimeSpawningRestrictions.getBoolean())
    		MinecraftForge.EVENT_BUS.register(new burptech.entity.monster.tweaks.EntitySlimeEventHandler());
    	
    	if (configuration.enableNetherSpawningRestrictions.getBoolean())
    		MinecraftForge.EVENT_BUS.register(new burptech.entity.monster.tweaks.EntityNetherMonsterEventHandler());
    	
    	if (configuration.enableMobsEatingOffOfGround.getBoolean())
    		MinecraftForge.EVENT_BUS.register(new burptech.entity.passive.tweaks.EntityAnimalEventHandler());
    	
    	if (configuration.enableMobsWandering.getBoolean())
    		MinecraftForge.EVENT_BUS.register(new burptech.entity.living.tweaks.EntityLivingEventHandler());
    	
    	if (configuration.enableGreedyVillagers.getBoolean())
    		MinecraftForge.EVENT_BUS.register(new burptech.entity.living.tweaks.EntityVillagerEventHandler());    		

        // recipes
    	RecipeManager.INSTANCE.addRecipes(RecipesVanilla.INSTANCE, RecipesBurpTech.INSTANCE, RecipesNetherTech.INSTANCE);
    }
    
    @EventHandler
    public void postInitialization(FMLPostInitializationEvent e)
    {
    	// tweaks
    	if (configuration.disableEndermanGriefing.getBoolean())
    		burptech.entity.monster.tweaks.EntityEndermanTweaks.enableAntiGriefing();

        proxy.postInitialization();

        // mod integrations
        RecipeManager.INSTANCE.postInitialization(RecipesNetherTech.INSTANCE, RecipesIntegration.INSTANCE);
    }
}