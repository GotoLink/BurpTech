package burptech.integration;

import buildcraft.api.fuels.BuildcraftFuelRegistry;
import cpw.mods.fml.common.event.FMLInterModComms;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;

public class BuildcraftIntegration
{
    public static boolean addFacade(Block blockId, int metaData)
    {
        return FMLInterModComms.sendMessage("BuildCraft|Silicon", "add-facade", new ItemStack(blockId, 1, metaData));
    }

    public static boolean addEngineFuel(Fluid fluid, int powerPerCycle, int totalBurningTime)
    {
        if (Integration.BUILDCRAFT)
            BuildcraftFuelRegistry.fuel.addFuel(fluid, powerPerCycle, totalBurningTime);
        return false;
    }
}
