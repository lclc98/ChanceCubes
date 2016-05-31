package chanceCubes.blocks;

import chanceCubes.CCubesCore;
import chanceCubes.items.ItemChanceCube;
import chanceCubes.tileentities.TileChanceCube;
import chanceCubes.tileentities.TileChanceD20;
import chanceCubes.tileentities.TileCubeDispenser;
import chanceCubes.tileentities.TileGiantCube;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CCubesBlocks
{
	public static BaseChanceBlock chanceCube;
	public static BaseChanceBlock chanceIcosahedron;
	public static BaseChanceBlock chanceGiantCube;
	public static BaseChanceBlock chanceCompactGiantCube;
	public static BaseChanceBlock chanceCubeDispenser;

	public static void loadBlocks()
	{
		GameRegistry.register(chanceCube = new BlockChanceCube());
		GameRegistry.register(chanceIcosahedron = new BlockChanceD20());
		GameRegistry.register(chanceGiantCube = new BlockGiantCube());
		GameRegistry.register(chanceCompactGiantCube = new BlockCompactGiantCube());
		GameRegistry.register(chanceCubeDispenser = new BlockCubeDispenser());
		
		GameRegistry.register(new ItemChanceCube(chanceCube).setRegistryName(chanceCube.getRegistryName()));
		GameRegistry.register(new ItemChanceCube(chanceIcosahedron).setRegistryName(chanceIcosahedron.getRegistryName()));
		GameRegistry.register(new ItemChanceCube(chanceGiantCube).setRegistryName(chanceGiantCube.getRegistryName()));
		GameRegistry.register(new ItemChanceCube(chanceCompactGiantCube).setRegistryName(chanceCompactGiantCube.getRegistryName()));
		GameRegistry.register(new ItemChanceCube(chanceCubeDispenser).setRegistryName(chanceCubeDispenser.getRegistryName()));

		GameRegistry.registerTileEntity(TileChanceCube.class, "tileChanceCube");
		GameRegistry.registerTileEntity(TileChanceD20.class, "tileChanceIcosahedron");
		GameRegistry.registerTileEntity(TileGiantCube.class, "tileChanceGiant");
		GameRegistry.registerTileEntity(TileCubeDispenser.class, "tileCubeDispenser");
	}

	public static void registerBlocksItems()
	{
		ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();

		mesher.register(Item.getItemFromBlock(chanceCube), 0, new ModelResourceLocation(CCubesCore.MODID + ":" + chanceCube.getBlockName(), "inventory"));
		mesher.register(Item.getItemFromBlock(chanceGiantCube), 0, new ModelResourceLocation(CCubesCore.MODID + ":" + chanceGiantCube.getBlockName(), "inventory"));
		mesher.register(Item.getItemFromBlock(chanceCompactGiantCube), 0, new ModelResourceLocation(CCubesCore.MODID + ":" + chanceCompactGiantCube.getBlockName(), "inventory"));
		mesher.register(Item.getItemFromBlock(chanceCubeDispenser), 0, new ModelResourceLocation(CCubesCore.MODID + ":" + chanceCubeDispenser.getBlockName(), "inventory"));
		
	}
}