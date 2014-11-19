package burptech.item;

import burptech.lib.Constants;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;


public class ItemDust extends Item
{
    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    public static final String[] dusts = {"dustNetherrack", "dustIron", "dustGold", "dustInfusedNetherrack", "dustTinyCharcoal"};

    public ItemDust()
    {
        super();
        setHasSubtypes(true);
        setMaxDamage(0);
        this.setCreativeTab(CreativeTabs.tabMaterials);
        this.setTextureName(Constants.MOD_ID + ":genericDust");
        this.setUnlocalizedName("genericDust");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIconFromDamage(int damage)
    {
        if (damage >= 0 && damage < dusts.length && icons != null)
            return icons[damage];

        return super.getIconFromDamage(damage);
    }

    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        int damage = par1ItemStack.getItemDamage();
        if (damage >= 0 && damage < dusts.length)
            return "item." + dusts[damage];

        return super.getUnlocalizedName();
    }


    @SuppressWarnings("unchecked")
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(Item itemId, CreativeTabs creativeTab, List itemList)
    {
        for (int i = 0; i < dusts.length; i++)
        {
            itemList.add(new ItemStack(itemId, 1, i));
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister par1IIconRegister)
    {
        super.registerIcons(par1IIconRegister);

        icons = new IIcon[dusts.length];

        for (int i = 0; i < dusts.length; i++)
        {
            icons[i] = par1IIconRegister.registerIcon(Constants.MOD_ID + ":" + dusts[i]);
        }
    }
}
