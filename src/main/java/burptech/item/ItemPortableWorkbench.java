package burptech.item;

import burptech.BurpTechCore;
import burptech.lib.Constants;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemPortableWorkbench extends Item
{
	public ItemPortableWorkbench()
	{
		super();
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		if (world.isRemote)
		{
			return player.getCurrentEquippedItem();
		}
		else
		{
			player.openGui(BurpTechCore.instance, Constants.GUI_PORTABLE_WORKBECH_ID, world, 0, 0, 0);
			return player.getCurrentEquippedItem();
		}
	}

    @Override
    public Item setUnlocalizedName(String name)
    {
        super.setUnlocalizedName(name);
        setTextureName(Constants.MOD_ID + ":" + name);
        return this;
    }
}
