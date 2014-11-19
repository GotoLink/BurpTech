package burptech.integration;

import cpw.mods.fml.common.Loader;
import net.minecraft.item.ItemStack;

public class Integration
{
    public static final boolean BUILDCRAFT = Loader.isModLoaded("BuildCraft|Silicon");
    public static final boolean INDUSTRIALCRAFT = Loader.isModLoaded("IC2");
    public static final boolean RAILCRAFT = Loader.isModLoaded("Railcraft");
	/*
	 * Adds a basic crushable item to mods that we integrate with. Returns true if any of them worked
	 */
	public static boolean addCrushableItem(ItemStack input, ItemStack output)
	{
		boolean isAddedToMod = AppliedEnergisticsIntegration.addGrinderRecipe(input, output);
        isAddedToMod = isAddedToMod | IndustrialcraftIntegration.addMaceratorRecipe(input, output);
		isAddedToMod = isAddedToMod | RailcraftIntegration.addRockCrusherRecipe(input, output, null, 0);

		return isAddedToMod;
	}
}
