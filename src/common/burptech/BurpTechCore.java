package burptech;

import java.util.logging.Logger;

import burptech.gui.GuiHandler;
import burptech.item.crafting.*;
import burptech.lib.*;
import cpw.mods.fml.common.*;
import cpw.mods.fml.common.Mod.*;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraftforge.common.*;

/**
 * BurpTech core mod but not... a core mod !( . Y . )!
 * 										     ^
 * Acatera: OMG lol  ----------------------- |
 */
@Mod(modid = Constants.MOD_ID, name = Constants.MOD_NAME, version = Constants.MOD_VERSION)
@NetworkMod(clientSideRequired = true)
public class BurpTechCore
{
    /**
     * Default logger instance
     */
    public static Logger log = null;
    
    /**
     * Instance of the mod
     */
    @Instance(Constants.MOD_ID)
    public static BurpTechCore instance;
    
    /**
     * Instance of the configuration
     */
    public static BurpTechConfig configuration;
    
    /**
     * GUI handler
     */
    public static GuiHandler guiHandler; 
    
    @PreInit
    public void preInitialization(FMLPreInitializationEvent e)
    {
        // setup logger
        log = Logger.getLogger(Constants.MOD_ID);
        configuration = BurpTechConfig.load(e.getModConfigurationDirectory());
        
        //gui handler
        guiHandler = new GuiHandler();
        
        // initialize blocks/items
        configuration.items.create();
        configuration.blocks.create();
        
        // load up language translations
        LocalizationManager.addLocalization();
        
        // register keyboard bindings
    }
    
    @Init
    public void initialization(FMLInitializationEvent e)
    {
        // gui handlers
    	NetworkRegistry.instance().registerGuiHandler(instance, guiHandler);
    	
        // event handlers
    	if (configuration.enableSlimeSpawningRestrictions.getBoolean(true))
    		MinecraftForge.EVENT_BUS.register(new burptech.entity.monster.tweaks.EntitySlimeEventHandler());
    	
    	if (configuration.enableNetherSpawningRestrictions.getBoolean(true))
    		MinecraftForge.EVENT_BUS.register(new burptech.entity.monster.tweaks.EntityNetherMonsterEventHandler());
    	
    	if (configuration.enableMobsEatingOffOfGround.getBoolean(true))
    		MinecraftForge.EVENT_BUS.register(new burptech.entity.passive.tweaks.EntityAnimalEventHandler());
    	
    	if (configuration.enableMobsWandering.getBoolean(true))
    		MinecraftForge.EVENT_BUS.register(new burptech.entity.living.tweaks.EntityLivingEventHandler());
    	
    	// tile entity registrations
    	
        // recipes
    	(new RecipeManager()).addRecipes();
    }
    
    @PostInit
    public void postInitialization(FMLPostInitializationEvent e)
    {
    	// tweaks
    	if (configuration.disableEndermanGriefing.getBoolean(true))
    		burptech.entity.monster.tweaks.EntityEndermanTweaks.enableAntiGriefing();

    	
        // mod integrations
    	
    }
}