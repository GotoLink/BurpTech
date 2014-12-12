package burptech.entity.living.tweaks;

import burptech.BurpTechCore;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public final class EntityPlayerEventHandler {
    public static final EntityPlayerEventHandler INSTANCE = new EntityPlayerEventHandler();
    private EntityPlayerEventHandler(){}

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onPlayerInteract(PlayerInteractEvent event){
        if(event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK){
            ItemStack currentItem = event.entityPlayer.getCurrentEquippedItem();
            if (currentItem == null)
                return;
            if (currentItem.getItem() == Items.glowstone_dust) {
                if(event.world.getBlock(event.x, event.y, event.z) == Blocks.cocoa) {
                    int meta = event.world.getBlockMetadata(event.x, event.y, event.z);
                    if (((meta & 12) >> 2) == 2) {
                        event.world.setBlock(event.x, event.y, event.z, BurpTechCore.configuration.blocks.blockIlluminatedCocoaOn, meta, 1 | 2 | 4);
                        if (!event.entityPlayer.capabilities.isCreativeMode) {
                            currentItem.stackSize--;
                        }
                    }
                }
            }
        }
    }
}
