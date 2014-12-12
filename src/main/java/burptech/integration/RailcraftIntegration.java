package burptech.integration;

import burptech.BurpTechCore;
import mods.railcraft.api.crafting.IRockCrusherRecipe;
import mods.railcraft.api.crafting.RailcraftCraftingManager;
import mods.railcraft.api.fuel.FuelManager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class RailcraftIntegration 
{
	public static boolean addRockCrusherRecipe(ItemStack input, ItemStack output, ItemStack bonus, float bonusChance)
	{
        if (!Integration.RAILCRAFT)
            return false;

        BurpTechCore.log.info("Adding Railcraft Rockcrusher Recipe for " + input.getDisplayName());

        IRockCrusherRecipe recipe = RailcraftCraftingManager.rockCrusher.createNewRecipe(input.copy(), true, true);

        recipe.addOutput(output.copy(), 1F);

        if (bonus != null)
            recipe.addOutput(bonus.copy(), bonusChance);

		return true;
	}

    public static boolean addBoilerFuel(Fluid fluid, int heatValuePerBucket)
    {
        if (!Integration.RAILCRAFT)
            return false;

        FuelManager.addBoilerFuel(fluid, heatValuePerBucket);

        return true;
    }

    public static boolean addCokeOvenRecipe(ItemStack input, boolean matchDamage, boolean matchNBT, ItemStack output, FluidStack liquidOutput, int cookTime)
    {
        if (!Integration.RAILCRAFT)
            return false;

        RailcraftCraftingManager.cokeOven.addRecipe(input, matchDamage, matchNBT, output, liquidOutput, cookTime);

        return true;
    }

    public static boolean addBlastFurnaceRecipe(ItemStack input, boolean matchDamage, boolean matchNBT, int cookTime, ItemStack output)
    {
        if (!Integration.RAILCRAFT)
            return false;

        RailcraftCraftingManager.blastFurnace.addRecipe(input, matchDamage, matchNBT, cookTime, output);

        return true;
    }
}
