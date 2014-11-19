package burptech.entity.monster.tweaks;

import burptech.BurpTechCore;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.init.Blocks;

/**
 * Enderman tweaks 
 */
public final class EntityEndermanTweaks 
{
	/**
	 * Will make Enderman only pickup and carry red and yellow flowers (anti-griefing)
	 */
	public static void enableAntiGriefing()
	{
		BurpTechCore.log.info("Enabling Enderman Anti Griefing");
		
		EntityEnderman.setCarriable(Blocks.grass, false);
        EntityEnderman.setCarriable(Blocks.dirt, false);
        EntityEnderman.setCarriable(Blocks.sand, false);
        EntityEnderman.setCarriable(Blocks.gravel, false);
        EntityEnderman.setCarriable(Blocks.yellow_flower, true);
        EntityEnderman.setCarriable(Blocks.red_flower, true);
        EntityEnderman.setCarriable(Blocks.brown_mushroom, false);
        EntityEnderman.setCarriable(Blocks.red_mushroom, false);
        EntityEnderman.setCarriable(Blocks.tnt, false);
        EntityEnderman.setCarriable(Blocks.cactus, false);
        EntityEnderman.setCarriable(Blocks.clay, false);
        EntityEnderman.setCarriable(Blocks.pumpkin, false);
        EntityEnderman.setCarriable(Blocks.melon_block, false);
        EntityEnderman.setCarriable(Blocks.mycelium, false);
	}
}
