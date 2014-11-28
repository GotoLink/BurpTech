package burptech;

import burptech.block.Blocks;
import burptech.item.Items;
import burptech.lib.Constants;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;

/*
 * All config for BurpTech here
 */
public final class BurpTechConfig
{
    public Property enableCheckForUpdates;

	public Property disableEndermanGriefing;
	public Property enableSlimeSpawningRestrictions;
	public Property enableNetherSpawningRestrictions;
	public Property enableMobsEatingOffOfGround;
	public Property enableMobsWandering;
	public Property enableGreedyVillagers;
	public Property enableIlluminatedCocoa;
	
	public Property recipeCobwebs;
	public Property recipePortableWorkbench;
	public Property recipeRucksack;
	public Property recipeEnderRucksack;
    public Property recipeSickle;

    public Property recipeAdvancedWorkbench;
    public Property recipeCobbleGenerator;
	
	public Property recipeCookedEgg;
	
	public Property enableNetherTechSolidFuels;
    public Property enableNetherTechLiquidFuels;
    public Property enableNetherTechVanillaRecipes;
    public Property enableNetherTechIndustrialcraftRecipes;
    public Property enableNetherTechRailcraftRecipes;
    public Property enableNetherTechBuildcraftRecipes;

    public Property enableCreosoteToIndustrialcraftEnergy;
    public Property enableSaplingCokeOvenProcessing;

    public Property enableStoneDustCompression;
    public Property enableCompressedPlantBallEnrichment;
    public Property enableVanillaOreDoubling;

	/*
	 * BurpTech Items
	 */
	public Items items;
	
	/*
	 * BurpTech Blocks
	 */
	public Blocks blocks;
	
	/*
	 * Loads the burptech configuration file
	 */
	public static BurpTechConfig load(File configFolder)
	{
		BurpTechConfig result = new BurpTechConfig();
		
		File configurationFile = new File(configFolder.getAbsolutePath() + "/" + Constants.MOD_NAME + ".cfg");
		Configuration configuration = new Configuration(configurationFile);

        result.enableCheckForUpdates = configuration.get(Constants.CONFIG_CATEGORY_GENERAL, "Enable Update Checks", true);
        result.enableCheckForUpdates.comment = "Check for updates when the game starts, and then adds chat messages if updates are available";

		// Tweaks
		result.disableEndermanGriefing = configuration.get(Constants.CONFIG_CATEGORY_TWEAKS, "DisableEndermanGriefing", true);
		result.disableEndermanGriefing.comment = "Disables Enderman from picking up any blocks other than vanilla flowers";
		
		result.enableSlimeSpawningRestrictions = configuration.get(Constants.CONFIG_CATEGORY_TWEAKS,  "EnableSlimeSpawnRestrictions", true);
		result.enableSlimeSpawningRestrictions.comment = "Restricts Slimes (Green) to only spawn on Stone, Dirt, and Grass";
		
		result.enableNetherSpawningRestrictions = configuration.get(Constants.CONFIG_CATEGORY_TWEAKS,  "EnableNetherSpawnRestrictions", true);
		result.enableNetherSpawningRestrictions.comment = "Restricts Nether Mobs to only spawn on netherrack, nether brick, and soul sand";

		result.enableMobsEatingOffOfGround = configuration.get(Constants.CONFIG_CATEGORY_TWEAKS, "EnableMobsEatingDroppedFood", true);
		result.enableMobsEatingOffOfGround.comment = "Adds a new AI for mobs eating breeding food from the ground near them";
		
		result.enableMobsWandering = configuration.get(Constants.CONFIG_CATEGORY_TWEAKS, "EnableMobsWandering", true);
		result.enableMobsWandering.comment = "When enabled, mobs will keep wandering past the 32 block vanilla limit";
		
		result.enableGreedyVillagers = configuration.get(Constants.CONFIG_CATEGORY_TWEAKS, "EnableGreedyVillagers", true);
		result.enableGreedyVillagers.comment = "When enabled, villagers will follow players with diamonds and emeralds in there hands";
		
		result.enableIlluminatedCocoa = configuration.get(Constants.CONFIG_CATEGORY_TWEAKS, "IlluminatedCocoaPlants", true);
		result.enableIlluminatedCocoa.comment = "When enabled, allows you to right click a grown cocoa plant with glowstone to turn it into a lamp";
				
		// Recipes
		result.recipeCobwebs = configuration.get(Constants.CONFIG_CATEGORY_RECIPES, "Cobwebs", true);
		result.recipeCobwebs.comment = "Enables crafting of cobwebs from string";
		
		result.recipePortableWorkbench = configuration.get(Constants.CONFIG_CATEGORY_RECIPES, "PortableWorkbench", true);
		result.recipePortableWorkbench.comment = "Enables crafting of Portable Workbench";
		
		result.recipeRucksack = configuration.get(Constants.CONFIG_CATEGORY_RECIPES, "Rucksack", true);
		result.recipeRucksack.comment = "Enables crafting of Rucksacks";
		
		result.recipeEnderRucksack = configuration.get(Constants.CONFIG_CATEGORY_RECIPES, "EnderRucksack", true);
		result.recipeEnderRucksack.comment = "Enables crafting of Ender Rucksacks";

        result.recipeSickle = configuration.get(Constants.CONFIG_CATEGORY_RECIPES, "Sickle", false);
        result.recipeSickle.comment = "Adds Project Red/Red Power Style Sickles - this is automatically disabled regardless of setting if ProjectRed.Exploration is installed";

		result.recipeCookedEgg = configuration.get(Constants.CONFIG_CATEGORY_RECIPES, "CookedEggs", true);
		result.recipeCookedEgg.comment = "Enables cooked eggs for food";

        result.recipeCobbleGenerator = configuration.get(Constants.CONFIG_CATEGORY_RECIPES, "Cobble Generator", true);
        result.recipeCobbleGenerator.comment = "Enables the cobblestone generator block";

        result.recipeAdvancedWorkbench = configuration.get(Constants.CONFIG_CATEGORY_RECIPES, "Advanced Workbench", true);
        result.recipeAdvancedWorkbench.comment = "Enables the advanced workbench";
		
		// Nether Tech
		result.enableNetherTechSolidFuels = configuration.get(Constants.CONFIG_CATEGORY_NETHERTECH, "SolidFuels", true);
		result.enableNetherTechSolidFuels.comment = "Enables Nether Tech Solid Fuels";

        result.enableNetherTechLiquidFuels = configuration.get(Constants.CONFIG_CATEGORY_NETHERTECH, "LiquidFuels", true);
        result.enableNetherTechLiquidFuels.comment = "Enables Nether Tech Liquid Fuels";

        result.enableNetherTechVanillaRecipes = configuration.get(Constants.CONFIG_CATEGORY_NETHERTECH, "Vanilla.Recipes.Enabled", false);
        result.enableNetherTechVanillaRecipes.comment = "Enables the vanilla Nether Coal Recipe";

        result.enableNetherTechIndustrialcraftRecipes = configuration.get(Constants.CONFIG_CATEGORY_NETHERTECH, "Industrialcraft.Recipes.Enabled", true);
        result.enableNetherTechIndustrialcraftRecipes.comment = "Enables the Industrialcraft Nether Fuel and Coal recipes - only valid if Industrialcraft is loaded";

        result.enableNetherTechRailcraftRecipes = configuration.get(Constants.CONFIG_CATEGORY_NETHERTECH, "Railcraft.Recipes.Enabled", true);
        result.enableNetherTechRailcraftRecipes.comment = "Enables the Railcraft Nether Fuel and Coal recipes - only valid if Railcraft is loaded";

        result.enableNetherTechBuildcraftRecipes = configuration.get(Constants.CONFIG_CATEGORY_NETHERTECH, "Buildcraft.Recipes.Enabled", true);
        result.enableNetherTechBuildcraftRecipes.comment = "Enables the Buildcraft Nether Fuel - only valid if Buildcraft is loaded";

        // integration
        result.enableCreosoteToIndustrialcraftEnergy = configuration.get(Constants.CONFIG_CATEGORY_INTEGRATION, "SemiFluid.Generator.Creosote.Enabled", true);
        result.enableCreosoteToIndustrialcraftEnergy.comment = "Enables Creosote to be used in a Semi-Fluid Generator";

        result.enableSaplingCokeOvenProcessing = configuration.get(Constants.CONFIG_CATEGORY_INTEGRATION, "Coke.Oven.Sapplings.Enabled", true);
        result.enableSaplingCokeOvenProcessing.comment = "Enables 16x saplings to be processed in a coke oven to produce charcoal and creosote";

        result.enableStoneDustCompression = configuration.get(Constants.CONFIG_CATEGORY_INTEGRATION, "Compressor.StoneDust.Enabled", true);
        result.enableStoneDustCompression.comment = "Enables Compressing 8x Industrialcraft Stone Dust into Stone";

        result.enableCompressedPlantBallEnrichment = configuration.get(Constants.CONFIG_CATEGORY_INTEGRATION, "Enrichment.CompressedPlantBall.Enabled", true);
        result.enableCompressedPlantBallEnrichment.comment = "Enables using the canning machine to enrich compressed plant balls to biomass";

        result.enableVanillaOreDoubling = configuration.get(Constants.CONFIG_CATEGORY_INTEGRATION, "Processing.Vanilla.OreDoubling.Enabled", false);
        result.enableVanillaOreDoubling.comment = "When enabled will attempt to register vanilla ores for ore doubling - use this if you don't have another mod that adds it, but have Railcraft";

        result.blocks = new Blocks(result);
        result.items = new Items(result);
		
		// save only if modified
		if (configuration.hasChanged())
			configuration.save();
		
		// return
		return result;
	}
}
