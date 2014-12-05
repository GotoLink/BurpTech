package burptech.entity.passive.tweaks;

import burptech.entity.ai.EntityAIGroundEater;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public final class EntityAnimalEventHandler
{
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void entitySpawning(EntityJoinWorldEvent event)
	{
		if (!(event.entity instanceof EntityAnimal))
			return;

		EntityAnimal animal = (EntityAnimal)event.entity;
		
		// add
		animal.tasks.addTask(0, new EntityAIGroundEater(animal));
	}
}
