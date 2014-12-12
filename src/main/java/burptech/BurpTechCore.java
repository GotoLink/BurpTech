package burptech;

import burptech.entity.living.tweaks.EntityLivingEventHandler;
import burptech.entity.living.tweaks.EntityVillagerEventHandler;
import burptech.entity.monster.tweaks.EntityEndermanTweaks;
import burptech.entity.monster.tweaks.EntityNetherMonsterEventHandler;
import burptech.entity.monster.tweaks.EntitySlimeEventHandler;
import burptech.entity.passive.tweaks.EntityAnimalEventHandler;
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

@Mod(modid = Constants.MOD_ID, name = Constants.MOD_NAME, dependencies = Constants.DEPS, useMetadata = true)
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
    		MinecraftForge.EVENT_BUS.register(EntitySlimeEventHandler.INSTANCE);
    	
    	if (configuration.enableNetherSpawningRestrictions.getBoolean())
    		MinecraftForge.EVENT_BUS.register(EntityNetherMonsterEventHandler.INSTANCE);
    	
    	if (configuration.enableMobsEatingOffOfGround.getBoolean())
    		MinecraftForge.EVENT_BUS.register(EntityAnimalEventHandler.INSTANCE);
    	
    	if (configuration.enableMobsWandering.getBoolean())
    		MinecraftForge.EVENT_BUS.register(EntityLivingEventHandler.INSTANCE);
    	
    	if (configuration.enableGreedyVillagers.getBoolean())
    		MinecraftForge.EVENT_BUS.register(EntityVillagerEventHandler.INSTANCE);

        // recipes
    	RecipeManager.INSTANCE.addRecipes(RecipesVanilla.INSTANCE, RecipesBurpTech.INSTANCE, RecipesNetherTech.INSTANCE);
    }
    
    @EventHandler
    public void postInitialization(FMLPostInitializationEvent e)
    {
    	// tweaks
    	if (configuration.disableEndermanGriefing.getBoolean())
    		EntityEndermanTweaks.enableAntiGriefing();

        proxy.postInitialization();

        // mod integrations
        RecipeManager.INSTANCE.postInitialization(RecipesNetherTech.INSTANCE, RecipesIntegration.INSTANCE);
    }
}