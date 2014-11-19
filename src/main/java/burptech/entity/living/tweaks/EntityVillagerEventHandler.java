package burptech.entity.living.tweaks;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class EntityVillagerEventHandler 
{
	@SubscribeEvent
	public void entitySpawning(EntityJoinWorldEvent event)
	{
		if (event.entity instanceof EntityVillager)
			updateVillagerAI((EntityVillager)event.entity);
	}

	private void updateVillagerAI(EntityVillager entity)
	{
		entity.tasks.addTask(1, new EntityAITempt(entity, 1.0D, Items.diamond, false));
		entity.tasks.addTask(1, new EntityAITempt(entity, 1.0D, Items.emerald, false));

	}
}
