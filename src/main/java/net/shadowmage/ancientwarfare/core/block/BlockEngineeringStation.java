package net.shadowmage.ancientwarfare.core.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.shadowmage.ancientwarfare.core.block.BlockRotationHandler.RelativeSide;
import net.shadowmage.ancientwarfare.core.block.BlockRotationHandler.RotationType;
import net.shadowmage.ancientwarfare.core.network.NetworkHandler;
import net.shadowmage.ancientwarfare.core.tile.TileEngineeringStation;
import net.shadowmage.ancientwarfare.core.util.InventoryTools;

public class BlockEngineeringStation extends BlockRotatableTile
{

protected BlockEngineeringStation(String regName)
  {
  super(Material.ROCK);
  this.setUnlocalizedName(regName);
  this.setCreativeTab(AWCoreBlockLoader.coreTab);
  iconMap.setIcon(this, RelativeSide.BOTTOM, "ancientwarfare:core/engineering_station_bottom");
  iconMap.setIcon(this, RelativeSide.TOP, "ancientwarfare:core/engineering_station_bottom");
  iconMap.setIcon(this, RelativeSide.FRONT, "ancientwarfare:core/engineering_station_bottom");
  iconMap.setIcon(this, RelativeSide.REAR, "ancientwarfare:core/engineering_station_bottom");
  iconMap.setIcon(this, RelativeSide.LEFT, "ancientwarfare:core/engineering_station_bottom");
  iconMap.setIcon(this, RelativeSide.RIGHT, "ancientwarfare:core/engineering_station_bottom");
  setHardness(2.f);
  }

@Override
public boolean hasTileEntity(IBlockState iBlockState)
  {
  return true;
  }

@Override
public TileEntity createTileEntity(World world, IBlockState iBlockState)
  {
  return new TileEngineeringStation();
  }

@Override
public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack istack, EnumFacing side, float hitX, float hitY, float hitZ)
  {
    int x = pos.getX();
    int y = pos.getY();
    int z = pos.getZ();

  if(!worldIn.isRemote)
    {
    NetworkHandler.INSTANCE.openGui(playerIn, NetworkHandler.GUI_CRAFTING, x, y, z);

    }
  return true;
  }



  @Override
public void breakBlock(World world, BlockPos bpos, IBlockState bstate)
  {
    int x = bpos.getX();
    int y = bpos.getY();
    int z = bpos.getZ();
  TileEngineeringStation tile = (TileEngineeringStation) world.getTileEntity(bpos);
  if(tile!=null)
    {
    InventoryTools.dropInventoryInWorld(world, tile.bookInventory, x, y, z);
    InventoryTools.dropInventoryInWorld(world, tile.extraSlots, x, y, z);
    InventoryTools.dropInventoryInWorld(world, tile.layoutMatrix, x, y, z);
    }
  super.breakBlock(world, bpos, bstate);
  }

@Override
public RotationType getRotationType()
  {
  return RotationType.FOUR_WAY;
  }

@Override
public boolean invertFacing()
  {
  return true;
  }

@Override
public Block setIcon(RelativeSide side, String texName)
  {
  iconMap.setIcon(this, side, texName);
  return this;
  }

/**@Override
public boolean shouldSideBeRendered(net.minecraft.world.IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_) {return false;}

@Override
public boolean isOpaqueCube() {return false;}

@Override
public boolean isNormalCube() {return false;}
**/
}
