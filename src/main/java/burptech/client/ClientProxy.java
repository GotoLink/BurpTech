package burptech.client;

import burptech.BurpTechCore;
import burptech.CommonProxy;
import burptech.client.gui.GuiAdvancedWorkbench;
import burptech.client.gui.GuiPortableWorkbench;
import burptech.client.render.EntityDropParticleFX;
import burptech.integration.NeiIntegration;
import burptech.lib.VersionChecker;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;

import java.util.Random;

public class ClientProxy extends CommonProxy
{
    @Override
    public void postInitialization()
    {
        addNeiSupport();

        MinecraftForge.EVENT_BUS.register(this);

        if (BurpTechCore.configuration.enableCheckForUpdates.getBoolean())
            FMLCommonHandler.instance().bus().register(new VersionChecker());
    }

    @Override
    public void doDropParticles(World world, int x, int y, int z, Random rand, float particleRed, float particleGreen, float particleBlue){
        double px = (double) ((float) x + rand.nextFloat());
        double py = (double) y - 1.05D;
        double pz = (double) ((float) z + rand.nextFloat());
        EntityFX fx = new EntityDropParticleFX(world, px, py, pz, particleRed, particleGreen, particleBlue);
        FMLClientHandler.instance().getClient().effectRenderer.addEffect(fx);
    }

	private void addNeiSupport()
	{
		if (BurpTechCore.configuration.recipePortableWorkbench.getBoolean())
    	{
    		NeiIntegration.registerCraftingContainers(GuiPortableWorkbench.class);
    	}

        if (BurpTechCore.configuration.recipeAdvancedWorkbench.getBoolean())
        {
            NeiIntegration.registerCraftingContainers(GuiAdvancedWorkbench.class);
        }
    }

    @SubscribeEvent
    public void textureHook(TextureStitchEvent.Post event)
    {
        if (event.map.getTextureType() != 0)
            return;

        if (BurpTechCore.configuration.blocks.fluidNetherFluid != null && BurpTechCore.configuration.blocks.blockNetherFluid != null)
        {
            BurpTechCore.configuration.blocks.fluidNetherFluid.setIcons(BurpTechCore.configuration.blocks.blockNetherFluid.getBlockTextureFromSide(1), BurpTechCore.configuration.blocks.blockNetherFluid.getBlockTextureFromSide(2));
        }
    }
}
