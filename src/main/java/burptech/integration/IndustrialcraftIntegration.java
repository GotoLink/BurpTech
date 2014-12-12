package burptech.integration;

import ic2.api.item.IC2Items;
import ic2.api.recipe.*;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

public class IndustrialcraftIntegration 
{
	public static boolean addMaceratorRecipe(ItemStack input, ItemStack output)
	{
        return Integration.INDUSTRIALCRAFT && addMachineRecipe(Recipes.macerator, input, output);
    }

    public static boolean addCompressorRecipe(ItemStack input, ItemStack output)
    {
        return Integration.INDUSTRIALCRAFT && addMachineRecipe(Recipes.compressor, input, output);
    }

    private static boolean addMachineRecipe(IMachineRecipeManager machine, ItemStack input, ItemStack output)
    {
        try{
            machine.addRecipe(getInputFor(input.copy()), null, output.copy());
            return true;
        }catch (Exception ignored){
        }
        return false;
    }

    public static boolean addEnrichmentRecipe(ItemStack itemInput, FluidStack fluidInput, FluidStack fluidOutput)
    {
        if (!Integration.INDUSTRIALCRAFT)
            return false;
        Recipes.cannerEnrich.addRecipe(fluidInput, getInputFor(itemInput), fluidOutput);
        return true;
    }

    private static IRecipeInput getInputFor(ItemStack input)
    {
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
            return null;
        ItemStack result = IC2Items.getItem(itemName);
        if(result!=null)
            return result.copy();
        return null;
    }
}
