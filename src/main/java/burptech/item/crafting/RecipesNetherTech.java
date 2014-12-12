package burptech.item.crafting;

import burptech.BurpTechCore;
import burptech.integration.BuildcraftIntegration;
import burptech.integration.IndustrialcraftIntegration;
import burptech.integration.Integration;
import burptech.integration.RailcraftIntegration;
import burptech.item.BucketHandler;
import burptech.item.NetherTechLiquidFuelHandler;
import burptech.item.NetherTechSolidFuelHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

public final class RecipesNetherTech implements RecipeManager.IAdder, RecipeManager.IPostMaker
{
    public static final RecipesNetherTech INSTANCE = new RecipesNetherTech();
    private RecipesNetherTech(){}
    /**
     * Adds the crafting recipes to the CraftingManager.
     */
    public void addRecipes()
    {
        if (BurpTechCore.configuration.enableNetherTechSolidFuels.getBoolean())
		    addSolidFuels();
        if (BurpTechCore.configuration.enableNetherTechLiquidFuels.getBoolean())
            addLiquidFuels();
    }

    private void addLiquidFuels()
    {
        FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("nether", FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(BurpTechCore.configuration.items.bucketNetherFluid), new ItemStack(Items.bucket));
        BucketHandler.INSTANCE.buckets.put(BurpTechCore.configuration.blocks.blockNetherFluid, BurpTechCore.configuration.items.bucketNetherFluid);
    }

    private void addSolidFuels()
    {
		// nether coal block
		GameRegistry.addRecipe(new ItemStack(BurpTechCore.configuration.blocks.blockNetherCoal),
                "###", "###", "###", '#', BurpTechCore.configuration.items.netherCoal);

        // reverse nether coal block recipe
		GameRegistry.addShapelessRecipe(new ItemStack(BurpTechCore.configuration.items.netherCoal, 9), BurpTechCore.configuration.blocks.blockNetherCoal);

        // torches
        GameRegistry.addRecipe(new ItemStack(Blocks.torch, 8),
                "#", "S", '#', BurpTechCore.configuration.items.netherCoal, 'S', Items.stick);

    }

    public void postInitialization()
    {
        // add fuel handler
        if (BurpTechCore.configuration.enableNetherTechSolidFuels.getBoolean())
            GameRegistry.registerFuelHandler(NetherTechSolidFuelHandler.INSTANCE);

        if (BurpTechCore.configuration.enableNetherTechLiquidFuels.getBoolean())
            GameRegistry.registerFuelHandler(NetherTechLiquidFuelHandler.INSTANCE);

        // vanilla recipes for nether coal
        if (BurpTechCore.configuration.enableNetherTechVanillaRecipes.getBoolean(true))
        {
            // add vanilla recipe for nether coal if no mods are present to support it
            GameRegistry.addRecipe(new ItemStack(BurpTechCore.configuration.items.netherCoal),
                    "###", "#C#", "###", '#', new ItemStack(Blocks.netherrack), 'C', new ItemStack(Items.coal, 1, 1));
        }

        // ic2 recipes for nether dust
        if (Integration.INDUSTRIALCRAFT && BurpTechCore.configuration.enableNetherTechIndustrialcraftRecipes.getBoolean(true))
        {
            ItemStack netherDust = BurpTechCore.configuration.items.netherDust.copy();
            if (BurpTechCore.configuration.enableNetherTechLiquidFuels.getBoolean())
            {
                IndustrialcraftIntegration.addSemiFlueGeneratorFuel("nether", 10, 32);
                IndustrialcraftIntegration.addMaceratorRecipe(new ItemStack(Blocks.netherrack), netherDust.copy());
                IndustrialcraftIntegration.addEnrichmentRecipe(netherDust.copy(), FluidRegistry.getFluidStack("lava", FluidContainerRegistry.BUCKET_VOLUME), FluidRegistry.getFluidStack("nether", FluidContainerRegistry.BUCKET_VOLUME));

                Item ic2Cell = GameRegistry.findItem("IC2", "itemCellEmpty");
                if (ic2Cell != null)
                {
                    FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("nether", FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(BurpTechCore.configuration.items.cellNetherFluid), new ItemStack(ic2Cell));
                }
            }

            if (BurpTechCore.configuration.enableNetherTechSolidFuels.getBoolean())
            {
                if(!BurpTechCore.configuration.enableNetherTechLiquidFuels.getBoolean())
                    IndustrialcraftIntegration.addMaceratorRecipe(new ItemStack(Blocks.netherrack), netherDust.copy());
                netherDust.stackSize = 8;
                IndustrialcraftIntegration.addCompressorRecipe(netherDust, new ItemStack(BurpTechCore.configuration.items.netherCoal));
            }
        }

        if (Integration.RAILCRAFT && BurpTechCore.configuration.enableNetherTechRailcraftRecipes.getBoolean(true))
        {
            if (BurpTechCore.configuration.enableNetherTechSolidFuels.getBoolean()) {
                if (BurpTechCore.configuration.enableNetherTechLiquidFuels.getBoolean()) {
                    RailcraftIntegration.addBoilerFuel(FluidRegistry.getFluid("nether"), 32000);
                    RailcraftIntegration.addRockCrusherRecipe(new ItemStack(Blocks.netherrack), BurpTechCore.configuration.items.netherDust.copy(), null, 0);
                    RailcraftIntegration.addBlastFurnaceRecipe(BurpTechCore.configuration.items.netherDust.copy(), true, false, 600, BurpTechCore.configuration.items.infusedNetherDust.copy());
                    RailcraftIntegration.addCokeOvenRecipe(BurpTechCore.configuration.items.infusedNetherDust.copy(), true, false, new ItemStack(BurpTechCore.configuration.items.netherCoal), FluidRegistry.getFluidStack("nether", 250), 600);
                } else {
                    RailcraftIntegration.addRockCrusherRecipe(new ItemStack(Blocks.netherrack), BurpTechCore.configuration.items.netherDust.copy(), null, 0);
                    RailcraftIntegration.addBlastFurnaceRecipe(BurpTechCore.configuration.items.netherDust.copy(), true, false, 600, new ItemStack(BurpTechCore.configuration.items.netherCoal));
                }
            }
            else if (BurpTechCore.configuration.enableNetherTechLiquidFuels.getBoolean())
            {
                RailcraftIntegration.addBoilerFuel(FluidRegistry.getFluid("nether"), 32000);
                RailcraftIntegration.addRockCrusherRecipe(new ItemStack(Blocks.netherrack), BurpTechCore.configuration.items.netherDust.copy(), null, 0);
                RailcraftIntegration.addCokeOvenRecipe(BurpTechCore.configuration.items.netherDust.copy(), true, false, BurpTechCore.configuration.items.tinyCharcoalDust.copy(), FluidRegistry.getFluidStack("nether", 250), 600);
            }
        }

        if (Integration.BUILDCRAFT && BurpTechCore.configuration.enableNetherTechBuildcraftRecipes.getBoolean(true))
        {
            if (BurpTechCore.configuration.enableNetherTechLiquidFuels.getBoolean())
                BuildcraftIntegration.addEngineFuel(FluidRegistry.getFluid("nether"), 5, 20000);

            if (BurpTechCore.configuration.enableNetherTechSolidFuels.getBoolean())
                BuildcraftIntegration.addFacade(BurpTechCore.configuration.blocks.blockNetherCoal, 0);
        }
    }
}
