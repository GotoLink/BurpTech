package burptech.gui;

import burptech.BurpTechCore;
import burptech.client.gui.GuiAdvancedWorkbench;
import burptech.client.gui.GuiPortableWorkbench;
import burptech.client.gui.GuiRucksack;
import burptech.item.ItemRucksack;
import burptech.lib.Constants;
import burptech.tileentity.TileEntityAdvancedWorkbench;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler
{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		switch (ID)
		{
			case Constants.GUI_PORTABLE_WORKBECH_ID:
				if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == BurpTechCore.configuration.items.portableWorkbench)
					return new ContainerPortableWorkbench(player.inventory, world);
				
			case Constants.GUI_RUCKSACK_ID:	
				if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ItemRucksack)
					return new ContainerRucksack(player.inventory, ((ItemRucksack) player.getCurrentEquippedItem().getItem()).getInventory(player, player.getCurrentEquippedItem()));
			
			case Constants.GUI_ENDER_RUCKSACK_ID:
				if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ItemRucksack)
					return new ContainerRucksack(player.inventory, player.getInventoryEnderChest());

            case Constants.GUI_ADVANCED_WORKBENCH_ID:
                return new ContainerAdvancedWorkbench(player.inventory, (TileEntityAdvancedWorkbench)world.getTileEntity(x,y,z));
		}
		
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,	int x, int y, int z) 
	{
		switch (ID)
		{
			case Constants.GUI_PORTABLE_WORKBECH_ID:
				if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == BurpTechCore.configuration.items.portableWorkbench)
					return new GuiPortableWorkbench(player.inventory, world);
				
			case Constants.GUI_RUCKSACK_ID:
				if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ItemRucksack)
					return new GuiRucksack(player.inventory, ((ItemRucksack)player.getCurrentEquippedItem().getItem()).getInventory(player,  player.getCurrentEquippedItem()));
			
			case Constants.GUI_ENDER_RUCKSACK_ID:
				if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ItemRucksack)
					return new GuiRucksack(player.inventory, player.getInventoryEnderChest());

            case Constants.GUI_ADVANCED_WORKBENCH_ID:
                return new GuiAdvancedWorkbench(player.inventory, (TileEntityAdvancedWorkbench)world.getTileEntity(x, y, z));

		}
		return null;
	}

}
