package burptech.integration;

import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.RecipeInputItemStack;
import ic2.api.recipe.RecipeInputOreDict;
import ic2.api.recipe.Recipes;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

public class IndustrialcraftIntegration 
{
	public static boolean addMaceratorRecipe(ItemStack input, ItemStack output)
	{
        if (!Integration.INDUSTRIALCRAFT)
		    return false;
        Recipes.macerator.addRecipe(getInputFor(input), null, output);
        return true;
	}

    public static boolean addCompressorRecipe(ItemStack input, ItemStack output)
    {
        if (!Integration.INDUSTRIALCRAFT)
            return false;
        Recipes.compressor.addRecipe(getInputFor(input), null, output);
        return true;
    }

    public static boolean addEnrichmentRecipe(ItemStack itemInput, FluidStack fluidInput, FluidStack fluidOutput)
    {
        if (!Integration.INDUSTRIALCRAFT)
            return false;
        Recipes.cannerEnrich.addRecipe(fluidInput, getInputFor(itemInput), fluidOutput);
        return true;
    }

    private static IRecipeInput getInputFor(ItemStack input){
        int[] ids = OreDictionary.getOreIDs(input);
        if(ids.length>0) {
            return new RecipeInputOreDict(OreDictionary.getOreName(ids[0]), input.stackSize);
        }
        return new RecipeInputItemStack(input);
    }

    public static boolean addSemiFlueGeneratorFuel(String fluidName, int amount, double power)
    {
        if (!Integration.INDUSTRIALCRAFT)
            return false;

        Recipes.semiFluidGenerator.addFluid(fluidName, amount, power);
        return true;
    }

    public static ItemStack getItem(String itemName)
    {
        if (!Integration.INDUSTRIALCRAFT)
        {
            return null;
        }
        return ic2.api.item.Items.getItem(itemName).copy();
    }
}
