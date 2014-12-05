package burptech.entity.monster.tweaks;

import burptech.BurpTechCore;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.CheckSpawn;

public final class EntityNetherMonsterEventHandler
{
	public EntityNetherMonsterEventHandler()
	{
		BurpTechCore.log.info("Enabling Nether Monster Spawning Restrictions");
	}
	
	@SubscribeEvent
	public void entitySpawn(CheckSpawn event)
	{
		if (event.entity.dimension == -1 && event.entity instanceof IMob && cannotSpawnHere(event.entity))
		{
			event.setResult(Event.Result.DENY);
		}
	}
	
	private boolean cannotSpawnHere(Entity entity)
	{
    	int x = MathHelper.floor_double(entity.posX);
    	int y = MathHelper.floor_double(entity.boundingBox.minY);
    	int z = MathHelper.floor_double(entity.posZ);
    	Block spawnBlock = entity.worldObj.getBlock(x, y - 1, z);
        return spawnBlock != Blocks.netherrack && spawnBlock != Blocks.nether_brick && spawnBlock != Blocks.soul_sand;
    }
}
