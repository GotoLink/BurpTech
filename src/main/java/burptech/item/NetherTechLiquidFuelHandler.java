package burptech.item;

import burptech.BurpTechCore;
import cpw.mods.fml.common.IFuelHandler;
import net.minecraft.item.ItemStack;

/**
 * Liquid Fuel NetherTech Fuel Handler
 */
public final class NetherTechLiquidFuelHandler implements IFuelHandler {

    public static final IFuelHandler INSTANCE = new NetherTechLiquidFuelHandler();
    private NetherTechLiquidFuelHandler(){}

    @Override
    public int getBurnTime(ItemStack fuel)
    {
        if (fuel!=null && fuel.getItem() == BurpTechCore.configuration.items.bucketNetherFluid)
            return 30000; // lava bucket * 1.5
        return 0;
    }
}
