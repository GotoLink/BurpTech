package burptech.item.crafting;

import burptech.BurpTechCore;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public final class RecipesVanilla implements RecipeManager.IAdder
{
    public static final RecipeManager.IAdder INSTANCE = new RecipesVanilla();
    private RecipesVanilla(){}
    /**
     * Adds the crafting recipes to the CraftingManager.
     */
    public void addRecipes()
    {
    	if (BurpTechCore.configuration.recipeCobwebs.getBoolean(true))
    		GameRegistry.addRecipe(new ItemStack(Blocks.web), "# #"," # ","# #",'#', Items.string);
    }
}
