package net.dries007.tfc.objects.blocks.wood;

import net.dries007.tfc.objects.Wood;
import net.dries007.tfc.util.OreDictionaryHelper;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

import java.util.EnumMap;

public class BlockFenceTFC extends BlockFence
{
    private static final EnumMap<Wood, BlockFenceTFC> MAP = new EnumMap<>(Wood.class);

    public static BlockFenceTFC get(Wood wood)
    {
        return MAP.get(wood);
    }

    public final Wood wood;

    public BlockFenceTFC(Wood wood)
    {
        super(Material.WOOD, Material.WOOD.getMaterialMapColor());
        if (MAP.put(wood, this) != null) throw new IllegalStateException("There can only be one.");
        this.wood = wood;
        setHarvestLevel("axe", 0);
        OreDictionaryHelper.register(this, "fence");
        OreDictionaryHelper.register(this, "fence", wood);
        Blocks.FIRE.setFireInfo(this, 5, 20);
    }
}