package burptech.item;

import burptech.BurpTechCore;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.Loader;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

/**
 * Solid Fuel NetherTech Fuel Handler
 *
 */
public class NetherTechSolidFuelHandler implements IFuelHandler
{
    private int baseBurnValue = 1600;

    public NetherTechSolidFuelHandler()
    {
        baseBurnValue = TileEntityFurnace.getItemBurnTime(new ItemStack(Items.coal, 1, 0));
    }

	@Override
	public int getBurnTime(ItemStack fuel) 
	{
		if (fuel == null)
			return 0;

        int multiplier = 2;

        if (Loader.isModLoaded("Railcraft"))
            multiplier = 6;

		if (fuel.getItem() == BurpTechCore.configuration.items.netherCoal)
			return baseBurnValue * multiplier;
		
		if (fuel.getItem() == Item.getItemFromBlock(BurpTechCore.configuration.blocks.blockNetherCoal))
			return (baseBurnValue * multiplier) * 9;

		return 0;
	}
}
