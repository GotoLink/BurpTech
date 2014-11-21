package burptech.lib;

import burptech.BurpTechCore;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.util.ChatComponentText;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Based on PRVersionChecker from Project Red - https://github.com/MrTJP/ProjectRed/blob/master/common/mrtjp/projectred/core/PRVersionChecker.java
 */
public class VersionChecker extends Thread
{
    public boolean isOutdated = false;
    public String newVersion;
    public boolean hasRan = false;

    public VersionChecker()
    {
        this.setName("BurpTech Version Checker");
        this.setDaemon(true);
        this.start();
    }

    @Override
    public void run()
    {
        if (hasRan)
            return;

        hasRan = true;

        String current = FMLCommonHandler.instance().findContainerFor(Constants.MOD_ID).getVersion();

        try
        {
            if (current.contains("$"))
                return;

            BurpTechCore.log.info("Checking for updates...");

            URL url = new URL("https://raw.github.com/GotoLink/BurpTech/master/src/main/resources/ChangeLog");

            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            String version;
            while ((version = reader.readLine()) != null)
            {
                if (version.length() > 0 && version.startsWith("v"))
                {
                    newVersion = version.substring(1);
                    if(newVersion.startsWith(Loader.MC_VERSION) && compareVersion(current)) {
                        isOutdated = true;
                        break;
                    }
                }
            }
        }
        catch (Throwable e)
        {
            BurpTechCore.log.info("Version Check Failed With: " + e.getLocalizedMessage());
            return;
        }

        if (isOutdated)
            BurpTechCore.log.info("Found Updated Version " + newVersion);
        else
            BurpTechCore.log.info("Current Version Is Up To Date: " + current);
    }

    private boolean compareVersion(String current) {
        if(!current.equals(newVersion)){
            String[] splitCurrent = current.split("\\.");
            String[] splitLatest = newVersion.split("\\.");
            int min = Math.min(splitCurrent.length, splitLatest.length);
            if(min>3)//Ignore Minecraft version
                for(int i = 3; i < min; i++){
                    if(!splitCurrent[i].equals(splitLatest[i])){
                        try{
                           if(Integer.parseInt(splitCurrent[i]) < Integer.parseInt(splitLatest[i]))
                               return true;
                        }catch (Exception ignored){}
                    }
                }
        }
        return false;
    }

    @SubscribeEvent
    public void tickEnd(TickEvent.PlayerTickEvent event)
    {
        if (this.isAlive())
            return;
        if(isOutdated)
            event.player.addChatMessage(new ChatComponentText("Version " + newVersion + " of BurpTech is available"));
        FMLCommonHandler.instance().bus().unregister(this);
    }
}
