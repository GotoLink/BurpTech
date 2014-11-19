package burptech.entity.monster.tweaks;

import burptech.BurpTechCore;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.CheckSpawn;

/*
 * Slime Tweaks
 */
public class EntitySlimeEventHandler 
{	
	public EntitySlimeEventHandler()
	{
		BurpTechCore.log.info("Enabling Slime Spawning Restrictions");
	}
	
	@SubscribeEvent
	public void entitySpawn(CheckSpawn event)
	{
		if (event.entity instanceof EntitySlime && cannotSpawnHere((EntitySlime) event.entity))
		{
			event.setResult(Event.Result.DENY);
		}
	}
	
	private boolean cannotSpawnHere(EntitySlime entity)
	{
    	int x = MathHelper.floor_double(entity.posX);
    	int y = MathHelper.floor_double(entity.boundingBox.minY);
    	int z = MathHelper.floor_double(entity.posZ);
    	Block spawnBlock = entity.worldObj.getBlock(x, y - 1, z);
        return spawnBlock != Blocks.stone && spawnBlock != Blocks.dirt && spawnBlock != Blocks.grass;

    }
}
