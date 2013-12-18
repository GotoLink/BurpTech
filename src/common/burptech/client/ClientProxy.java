package burptech.client;

import burptech.BurpTechCore;
import burptech.CommonProxy;
import burptech.client.gui.GuiPortableWorkbench;
import burptech.integration.NeiIntegration;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class ClientProxy extends CommonProxy
{
    @EventHandler
    @Override
    public void postInitialization(FMLPostInitializationEvent e)
    {
         addNeiSupport();
    }

	private void addNeiSupport()
	{
		if (BurpTechCore.configuration.recipePortableWorkbench.getBoolean(true))
    	{
    		NeiIntegration.registerCraftingContainers(GuiPortableWorkbench.class);
    	}

    }

    @ForgeSubscribe
    @SideOnly(Side.CLIENT)
    public void textureHook(TextureStitchEvent.Post event)
    {
        if (event.map.textureType != 0)
            return;

        if (BurpTechCore.configuration.blocks.fluidNetherFluid != null && BurpTechCore.configuration.blocks.blockNetherFluid != null)
            BurpTechCore.configuration.blocks.fluidNetherFluid.setIcons(BurpTechCore.configuration.blocks.blockNetherFluid.getBlockTextureFromSide(1), BurpTechCore.configuration.blocks.blockNetherFluid.getBlockTextureFromSide(2));
    }
}
