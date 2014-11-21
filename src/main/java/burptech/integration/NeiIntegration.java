package burptech.integration;

import burptech.BurpTechCore;
import net.minecraft.client.gui.inventory.GuiContainer;
import org.apache.logging.log4j.Level;

import java.lang.reflect.Method;

public class NeiIntegration 
{
	public static void registerCraftingContainers(Class<? extends GuiContainer> craftingContainer)
	{
		try
        {
            Class<?> API = Class.forName("codechicken.nei.api.API");
            Class<?> IOverlayHandler = Class.forName("codechicken.nei.api.IOverlayHandler");
            Class<?> defaultOverlayHandler = Class.forName("codechicken.nei.recipe.DefaultOverlayHandler");
            
            Method registerGuiOverlayMethod = API.getMethod("registerGuiOverlay", new Class[] { Class.class, String.class });
            Method registerGuiOverlayHandlerMethod = API.getMethod("registerGuiOverlayHandler", new Class[] { Class.class, IOverlayHandler, String.class });
            
            registerGuiOverlayMethod.invoke(null, craftingContainer, "crafting");
            registerGuiOverlayHandlerMethod.invoke(null, craftingContainer, defaultOverlayHandler.newInstance(), "crafting");
            BurpTechCore.log.log(Level.INFO, "NEI integration of " + craftingContainer.getName() + " was successful");
        }
        catch (ClassNotFoundException ex){
            BurpTechCore.log.log(Level.DEBUG, "NEI wasn't found.");
        }
        catch (Throwable ex){
        	BurpTechCore.log.log(Level.WARN, "NEI integration of " + craftingContainer.getName() + " failed:" + ex.getMessage());
        }
		
	}
}
