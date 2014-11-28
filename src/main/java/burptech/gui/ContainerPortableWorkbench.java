package burptech.gui;

import burptech.BurpTechCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import invtweaks.api.container.ChestContainer;
import invtweaks.api.container.ContainerSection;
import invtweaks.api.container.ContainerSectionCallback;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.inventory.Slot;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

@ChestContainer(rowSize=9, isLargeChest=false) /** inventory tweaks support **/
public class ContainerPortableWorkbench extends ContainerWorkbench
{
    @SideOnly(Side.CLIENT)
    private java.util.Map<ContainerSection, List<Slot>> slotMap;

    public ContainerPortableWorkbench(InventoryPlayer par1InventoryPlayer, World world) {
        super(par1InventoryPlayer, world, 0, 0, 0);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
    	return player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == BurpTechCore.configuration.items.portableWorkbench;
    }

    /*
     * Provided for advanced inventory tweaks support
     */
    @SideOnly(Side.CLIENT)
    @ContainerSectionCallback
    public java.util.Map<ContainerSection, List<Slot>> getSlotMap()
    {
        if (slotMap != null)
            return slotMap;

        slotMap = new EnumMap<ContainerSection, List<Slot>>(ContainerSection.class);

        // craft result 0
        // IGNORE

        // craft matrix 1-9
        // IGNORE

        // player inventory
        List<Slot> playerInventory = new ArrayList<Slot>();
        for (int i = 10; i < 37; i++)
        {
            playerInventory.add(getSlot(i));
        }
        slotMap.put(ContainerSection.INVENTORY_NOT_HOTBAR, playerInventory);

        // player hot bar
        List<Slot> playerHotBar = new ArrayList<Slot>();
        for (int i = 37; i < 46; i++)
        {
            playerHotBar.add(getSlot(i));
        }
        slotMap.put(ContainerSection.INVENTORY_HOTBAR, playerHotBar);

        return slotMap;
    }
}
