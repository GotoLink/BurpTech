package burptech.item;

import burptech.BurpTechConfig;
import burptech.lib.Constants;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;

/*
 * Item definitions for BurpTech 
 */
public final class Items
{
    // TODO: Don't create items if they are not enabled

	public Item portableWorkbench;
	public Item rucksack;
	public Item enderRucksack;

	public Item cookedEgg;

	public Item netherCoal;
    public Item genericDust;

    public ItemStack goldDust;
    public ItemStack ironDust;
    public ItemStack netherDust;
    public ItemStack infusedNetherDust;
    public ItemStack tinyCharcoalDust;

    public Item bucketNetherFluid;
    public Item cellNetherFluid;

    public Item woodSickle;
    public Item stoneSickle;
    public Item ironSickle;
    public Item goldSickle;
    public Item diamondSickle;

	/*
	 * Creates all of the item instances
	 */
	public Items(BurpTechConfig configuration)
	{
        // create items
		enderRucksack = new ItemRucksack(true).setUnlocalizedName("enderRucksack").setMaxStackSize(1).setCreativeTab(CreativeTabs.tabTools);
		rucksack = new ItemRucksack(false).setUnlocalizedName("rucksack").setMaxStackSize(1).setCreativeTab(CreativeTabs.tabTools);
		portableWorkbench = new ItemPortableWorkbench().setUnlocalizedName("portableWorkbench").setMaxStackSize(1).setCreativeTab(CreativeTabs.tabTools);;
		cookedEgg = new ItemFood(4, .3F, false).setUnlocalizedName("cookedEgg").setTextureName(Constants.MOD_ID() + "egg_cooked").setMaxStackSize(64);
		netherCoal = new Item().setUnlocalizedName("netherCoal").setCreativeTab(CreativeTabs.tabMaterials).setTextureName(Constants.MOD_ID() + "nether_coal");
        bucketNetherFluid = new ItemBucket(configuration.blocks.blockNetherFluid).setUnlocalizedName("bucketNetherFluid").setCreativeTab(CreativeTabs.tabMisc).setTextureName(Constants.MOD_ID() + "nether_fluid_bucket").setContainerItem(net.minecraft.init.Items.bucket);
        cellNetherFluid = new Item().setUnlocalizedName("cellNetherFluid").setCreativeTab(CreativeTabs.tabMisc).setTextureName(Constants.MOD_ID() + "nether_fluid_cell");
        genericDust = new ItemDust();

        // item registry
        GameRegistry.registerItem(enderRucksack, "enderRucksack");
        GameRegistry.registerItem(rucksack, "rucksack");
        GameRegistry.registerItem(portableWorkbench, "portableWorkbench");
        GameRegistry.registerItem(cookedEgg, "cookedEgg");
        GameRegistry.registerItem(netherCoal, "netherCoal");
        GameRegistry.registerItem(bucketNetherFluid, "bucketNetherFluid");
        GameRegistry.registerItem(cellNetherFluid, "cellNetherFluid");
        GameRegistry.registerItem(genericDust, "genericDust");

        // ore dictionary (pulled from: http://minecraftmodcustomstuff.wikia.com/wiki/Ore_Dictionary - more here: http://www.minecraftforge.net/wiki/Common_Oredict_names)

        // Ore Dusts
        ArrayList<ItemStack> goldDusts = OreDictionary.getOres("dustGold");
        ArrayList<ItemStack> ironDusts = OreDictionary.getOres("dustIron");
        ArrayList<ItemStack> netherDusts = OreDictionary.getOres("dustNetherrack");
        ArrayList<ItemStack> infusedNetherDusts = OreDictionary.getOres("dustInfusedNetherrack");

        if (netherDusts.isEmpty())
        {
            netherDust = new ItemStack(genericDust, 1, 0);
            OreDictionary.registerOre("dustNetherrack", netherDust);
        }
        else
        {
            netherDust = netherDusts.get(0);
        }

        if (ironDusts.isEmpty())
        {
            ironDust = new ItemStack(genericDust, 1, 1);
            OreDictionary.registerOre("dustIron", ironDust);
        }
        else
        {
            ironDust = ironDusts.get(0);
        }

        if (goldDusts.isEmpty())
        {
            goldDust = new ItemStack(genericDust, 1, 2);
            OreDictionary.registerOre("dustGold", goldDust);
        }
        else
        {
            goldDust = goldDusts.get(0);
        }

        if (infusedNetherDusts.isEmpty())
        {
            infusedNetherDust = new ItemStack(genericDust, 1, 3);
            OreDictionary.registerOre("dustInfusedNetherrack", infusedNetherDust);
        }
        else
        {
            infusedNetherDust = infusedNetherDusts.get(0);
        }

        tinyCharcoalDust = new ItemStack(genericDust, 1, 4);


        addSickles(configuration);
    }

    private void addSickles(BurpTechConfig configuration)
    {
        if (!configuration.recipeSickle.getBoolean(false) || Loader.isModLoaded("ProjRed|Exploration"))
            return;
        woodSickle = new ItemSickle(Item.ToolMaterial.WOOD).setUnlocalizedName("woodSickle");
        GameRegistry.registerItem(woodSickle, "woodSickle");
        stoneSickle = new ItemSickle(Item.ToolMaterial.STONE).setUnlocalizedName("stoneSickle");
        GameRegistry.registerItem(stoneSickle, "stoneSickle");
        ironSickle = new ItemSickle(Item.ToolMaterial.IRON).setUnlocalizedName("ironSickle");
        GameRegistry.registerItem(ironSickle, "ironSickle");
        goldSickle = new ItemSickle(Item.ToolMaterial.GOLD).setUnlocalizedName("goldSickle");
        GameRegistry.registerItem(goldSickle, "goldSickle");
        diamondSickle = new ItemSickle(Item.ToolMaterial.EMERALD).setUnlocalizedName("diamondSickle");
        GameRegistry.registerItem(diamondSickle, "diamondSickle");
    }
}
