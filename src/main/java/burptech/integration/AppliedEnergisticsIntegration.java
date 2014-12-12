package burptech.integration;

import cpw.mods.fml.common.event.FMLInterModComms;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class AppliedEnergisticsIntegration 
{
	public static boolean addGrinderRecipe(ItemStack input, ItemStack output)
	{
        return FMLInterModComms.sendMessage("appliedenergistics2", "add-grindable", getGrinderTagInfo(input, output, 8));
    }

    private static NBTTagCompound getGrinderTagInfo(ItemStack input, ItemStack output, int turns)
    {
        NBTTagCompound grinderTag = new NBTTagCompound();
        NBTTagCompound inputTag = new NBTTagCompound();
        input.copy().writeToNBT(inputTag);
        NBTTagCompound outputTag = new NBTTagCompound();
        output.copy().writeToNBT(outputTag);
        grinderTag.setTag("in", inputTag);
        grinderTag.setTag("out", outputTag);
        grinderTag.setInteger("turns", turns);
        return grinderTag;
    }
}
