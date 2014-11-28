package burptech.item.crafting;

import burptech.BurpTechCore;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class RecipesBurpTech 
{
    /**
     * Adds the crafting recipes to the CraftingManager.
     */
    public void addRecipes()
    {
    	if (BurpTechCore.configuration.recipeRucksack.getBoolean())
    	{
    		GameRegistry.addRecipe(new ItemStack(BurpTechCore.configuration.items.rucksack),
                    "#s#", "scs", "#s#", '#', Items.leather, 's', Items.string, 'c', Blocks.chest);
    		
    		GameRegistry.addRecipe(new ItemStack(BurpTechCore.configuration.items.rucksack),
                    "s#s", "#c#", "s#s", '#', Items.leather, 's', Items.string, 'c', Blocks.chest);
            RecipeSorter.register("burptech:rucksackdyes", RecipesRucksackDyes.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");
    		GameRegistry.addRecipe(new RecipesRucksackDyes());
    	}
    	
    	if (BurpTechCore.configuration.recipeEnderRucksack.getBoolean())
    	{
    		GameRegistry.addRecipe(new ItemStack(BurpTechCore.configuration.items.enderRucksack),
                    "#s#", "scs", "#s#", '#', Items.leather, 's', Items.string, 'c', Blocks.ender_chest);
    		
    		GameRegistry.addRecipe(new ItemStack(BurpTechCore.configuration.items.enderRucksack),
                    "s#s", "#c#", "s#s", '#', Items.leather, 's', Items.string, 'c', Blocks.ender_chest);
    	}
    	
    	if (BurpTechCore.configuration.recipePortableWorkbench.getBoolean())
    	{
    		GameRegistry.addShapelessRecipe(new ItemStack(BurpTechCore.configuration.items.portableWorkbench),
                    Blocks.crafting_table, Items.string);
    	}
    	
    	if (BurpTechCore.configuration.recipeCookedEgg.getBoolean())
    	{
    		GameRegistry.addSmelting(Items.egg, new ItemStack(BurpTechCore.configuration.items.cookedEgg), 0.35F); // xp matches standard food cooking xp
    	}

        if (BurpTechCore.configuration.recipeCobbleGenerator.getBoolean())
        {
            GameRegistry.addRecipe(new ShapedOreRecipe(BurpTechCore.configuration.blocks.blockCobbleGenerator,
                    "CCC", "CHC", "CPC", 'C', "cobblestone", 'H', Blocks.hopper, 'P', Blocks.piston));
        }

        if (BurpTechCore.configuration.recipeAdvancedWorkbench.getBoolean())
        {
            GameRegistry.addRecipe(new ShapedOreRecipe(BurpTechCore.configuration.blocks.blockAdvancedWorkbench,
                    "RcR", "PCP", "RWR", 'c', new ItemStack(Blocks.carpet, 1, 11), 'C', Blocks.chest, 'W', Blocks.crafting_table, 'R', "dyeRed", 'P', "plankWood"));
        }

        if (BurpTechCore.configuration.recipeSickle.getBoolean() && !Loader.isModLoaded("ProjRed|Exploration"))
        {
            GameRegistry.addRecipe(new ShapedOreRecipe(BurpTechCore.configuration.items.woodSickle, " m ", "  m", "sm ",  's', "stickWood", 'm', "plankWood"));
            GameRegistry.addRecipe(new ShapedOreRecipe(BurpTechCore.configuration.items.stoneSickle, " m ", "  m", "sm ",  's', "stickWood", 'm', "cobblestone"));
            GameRegistry.addRecipe(new ShapedOreRecipe(BurpTechCore.configuration.items.ironSickle, " m ", "  m", "sm ",  's', "stickWood", 'm', "ingotIron"));
            GameRegistry.addRecipe(new ShapedOreRecipe(BurpTechCore.configuration.items.goldSickle, " m ", "  m", "sm ",  's', "stickWood", 'm', "ingotGold"));
            GameRegistry.addRecipe(new ShapedOreRecipe(BurpTechCore.configuration.items.diamondSickle, " m ", "  m", "sm ",  's', "stickWood", 'm', "gemDiamond"));
        }
    }
}
