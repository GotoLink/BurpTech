package burptech.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class BlockSimpleRock extends Block{

    public BlockSimpleRock() {
        super(Material.rock);
        setStepSound(soundTypeStone);
        setHarvestLevel("pickaxe", 1, 0);
        setHardness(5.0F);
        setResistance(10.0F);
    }

    public static void setDefaultDirection(World world, int x, int y, int z)
    {
        if (!world.isRemote)
        {
            Block l = world.getBlock(x, y, z - 1);
            Block i1 = world.getBlock(x, y, z + 1);
            Block j1 = world.getBlock(x - 1, y, z);
            Block k1 = world.getBlock(x + 1, y, z);
            byte front = 3;

            if (l.func_149730_j() && !i1.func_149730_j())
            {
                front = 3;
            }

            if (i1.func_149730_j() && !l.func_149730_j())
            {
                front = 2;
            }

            if (j1.func_149730_j() && !k1.func_149730_j())
            {
                front = 5;
            }

            if (k1.func_149730_j() && !j1.func_149730_j())
            {
                front = 4;
            }

            world.setBlockMetadataWithNotify(x, y, z, front, 2);
        }
    }
}
