package burptech.integration;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInterModComms;
import net.minecraft.item.ItemStack;

public class AppliedEnergisticsIntegration 
{
	public static boolean addGrinderRecipe(ItemStack input, ItemStack output)
	{
        // TODO need to verify that it actually works somehow
        return Loader.isModLoaded("AppliedEnergistics") && FMLInterModComms.sendMessage("AppliedEnergistics", "add-grindable", input.getItem() + "," + input.getItemDamage() + "," + output.getItem() + "," + output.getItemDamage() + ",8");
    }
}
