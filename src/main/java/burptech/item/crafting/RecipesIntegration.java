package burptech.item.crafting;

import burptech.BurpTechCore;
import burptech.integration.IndustrialcraftIntegration;
import burptech.integration.Integration;
import burptech.integration.RailcraftIntegration;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.util.ArrayList;

public final class RecipesIntegration implements RecipeManager.IPostMaker{

    public static final RecipeManager.IPostMaker INSTANCE = new RecipesIntegration();
    private RecipesIntegration(){}

    public void postInitialization()
    {
        if (BurpTechCore.configuration.enableCreosoteToIndustrialcraftEnergy.getBoolean())
        {
            if (Integration.RAILCRAFT && Integration.INDUSTRIALCRAFT)
            {
                Fluid creosote = FluidRegistry.getFluid("creosote");
                if (creosote != null)
                {
                    IndustrialcraftIntegration.addSemiFlueGeneratorFuel("creosote", 66, 10);
                    BurpTechCore.log.info("Added Railcraft Creosote to Industrialcraft Semi-Fluid Generator");
                }
            }
        }

        if (BurpTechCore.configuration.enableSaplingCokeOvenProcessing.getBoolean())
        {
            if (Integration.RAILCRAFT)
            {
                Fluid creosote = FluidRegistry.getFluid("creosote");
                if (creosote != null)
                {
                    // get all saplings from ore dictionary
                    ArrayList<ItemStack> saplings = new ArrayList<ItemStack>(OreDictionary.getOres("treeSapling"));
                    saplings.addAll(OreDictionary.getOres("saplingTree"));

                    for (ItemStack sapling : saplings) {
                        // 16x saplings = 1 charcoal and a bit of creosote
                        RailcraftIntegration.addCokeOvenRecipe(sapling.copy(), true, true, BurpTechCore.configuration.items.tinyCharcoalDust.copy(), FluidRegistry.getFluidStack("creosote", 30), 200);
                    }
                }
            }
        }
        if (BurpTechCore.configuration.enableStoneDustCompression.getBoolean())
        {
            if (Integration.INDUSTRIALCRAFT)
            {
                ItemStack stoneDust = IndustrialcraftIntegration.getItem("stoneDust");
                if (stoneDust != null)
                {
                    stoneDust.stackSize = 8;
                    IndustrialcraftIntegration.addCompressorRecipe(stoneDust, new ItemStack(Blocks.stone));
                    BurpTechCore.log.info("Added Compressor Recipe for IC2 Stone Dust to Stone");
                }
            }
        }

        if (BurpTechCore.configuration.enableCompressedPlantBallEnrichment.getBoolean())
        {
            if (Integration.INDUSTRIALCRAFT && FluidRegistry.isFluidRegistered("biomass"))
            {
                ItemStack compressedPlantBall = IndustrialcraftIntegration.getItem("compressedPlantBall");
                if (compressedPlantBall != null)
                {
                    // water- produces 1.0
                    IndustrialcraftIntegration.addEnrichmentRecipe(compressedPlantBall, FluidRegistry.getFluidStack(FluidRegistry.WATER.getName(), 250), FluidRegistry.getFluidStack("biomass", 250));
                    BurpTechCore.log.info("Added Enrichment Recipe for Water + Compressed Plant Ball = Biomass");

                    // juice - produces 1.5
                    if (FluidRegistry.isFluidRegistered("juice"))
                    {
                        IndustrialcraftIntegration.addEnrichmentRecipe(compressedPlantBall, FluidRegistry.getFluidStack("juice", 250), FluidRegistry.getFluidStack("biomass", 375));
                        BurpTechCore.log.info("Added Enrichment Recipe for Juice + Compressed Plant Ball = Biomass");
                    }

                    // honey - produces 1.5
                    if (FluidRegistry.isFluidRegistered("honey"))
                    {
                        IndustrialcraftIntegration.addEnrichmentRecipe(compressedPlantBall, FluidRegistry.getFluidStack("honey", 250), FluidRegistry.getFluidStack("biomass", 375));
                        BurpTechCore.log.info("Added Enrichment Recipe for Honey + Compressed Plant Ball = Biomass");
                    }
                }
            }
        }

        if (BurpTechCore.configuration.enableVanillaOreDoubling.getBoolean())
        {
            // iron
            ItemStack ironDust = BurpTechCore.configuration.items.ironDust.copy();
            ironDust.stackSize = 2;

            if (Integration.addCrushableItem(new ItemStack(Blocks.iron_ore), ironDust))
                FurnaceRecipes.smelting().func_151394_a(new ItemStack(ironDust.getItem(), 1, ironDust.getItemDamage()), new ItemStack(Items.iron_ingot), 0.7F);

            // gold
            ItemStack goldDust = BurpTechCore.configuration.items.goldDust.copy();
            goldDust.stackSize = 2;

            if (Integration.addCrushableItem(new ItemStack(Blocks.gold_ore), goldDust))
                FurnaceRecipes.smelting().func_151394_a(new ItemStack(goldDust.getItem(), 1, goldDust.getItemDamage()), new ItemStack(Items.gold_ingot), 1F);
        }

        if (Integration.RAILCRAFT)
        {
            GameRegistry.addRecipe(new ShapedOreRecipe(OreDictionary.getOres("dustCharcoal").get(0),
                    "###", "###", "###", '#', BurpTechCore.configuration.items.tinyCharcoalDust.copy()));
        }
        else
        {
            GameRegistry.addRecipe(new ItemStack(Items.coal, 1, 1),
                    "###", "###", "###", '#', BurpTechCore.configuration.items.tinyCharcoalDust.copy());
        }
    }
}
