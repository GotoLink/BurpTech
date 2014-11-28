package burptech.lib;

import java.util.Locale;

/**
 * Static class that contains all of the constants for BurpTech
 * 
 */
public final class Constants
{
    /**
     * Flag that details if this is a debug build or not
     */
    public static final boolean ISDEBUG = false;
    
    /**
     * MOD ID
     */
    public static final String MOD_ID = "BurpTechCore";

    public static String MOD_ID(){
        return MOD_ID.toLowerCase(Locale.ENGLISH)+":";
    }
    
    /**
     * MOD NAME
     */
    public static final String MOD_NAME = "BurpTech";

    public static final String CONFIG_CATEGORY_GENERAL = "General";
    /**
     * Configuration Category for Tweaks
     */
    public static final String CONFIG_CATEGORY_TWEAKS = "Tweaks";
    
    /**
     * Configuration Category for Recipes
     */
    public static final String CONFIG_CATEGORY_RECIPES = "Recipes";
    
    /**
     * Configuration Category for Nether Tech
     */
    public static final String CONFIG_CATEGORY_NETHERTECH = "NetherTech";

    /**
     * Configuration category for integrating mods together.
     */
    public static final String CONFIG_CATEGORY_INTEGRATION = "Integration";
    
    /**
     * GUI ID for Portable Workbench
     */
    public static final int GUI_PORTABLE_WORKBECH_ID = 1;
    
    /**
     * GUI ID for rucksacks
     */
    public static final int GUI_RUCKSACK_ID = 2;
    
    /**
     * GUI ID for Ender rucksack
     */
    public static final int GUI_ENDER_RUCKSACK_ID = 3;

    /**
     * GUI ID for Advanced Workbench
     */
    public static final int GUI_ADVANCED_WORKBENCH_ID = 4;
    
}
