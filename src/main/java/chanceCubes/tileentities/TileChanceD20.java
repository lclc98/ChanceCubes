package chanceCubes.tileentities;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import chanceCubes.CCubesCore;
import chanceCubes.registry.ChanceCubeRegistry;

public class TileChanceD20 extends TileEntity
{
	private boolean breaking = false;
	private int stage = 0;
	private EntityPlayer player;


	private int chance;

	public TileChanceD20()
	{
		this(new Random().nextInt(201)-100);
	}

	public TileChanceD20(int initialChance)
	{
		this.chance = initialChance;
	}

	public void setChance(int newChance)
	{
		this.chance = newChance;
	}

	public int getChance()
	{
		return this.chance;
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setInteger("chance", this.chance);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		this.chance = nbt.getInteger("chance");
	}

	public void updateEntity()
	{
		if (!breaking)
			return;
		stage++;
		if (stage > 200)
		{
			breaking = false;
			if (!this.worldObj.isRemote)
			{
				this.worldObj.setBlockToAir(this.xCoord, this.yCoord, this.zCoord);
				this.worldObj.removeTileEntity(this.xCoord, this.yCoord, this.zCoord);
				ChanceCubeRegistry.INSTANCE.triggerRandomReward(this.worldObj, this.xCoord, this.yCoord, this.zCoord, player, this.getChance());
			}
		}
	}

	public void startBreaking(EntityPlayer player)
	{
		if(!breaking)
		{
			player.worldObj.playSoundEffect(this.xCoord, this.yCoord, this.zCoord, CCubesCore.MODID + ":d20_Break", 1, 1);
			breaking = true;
			stage = 0;
			this.player = player;
		}
	}

	public int getStage()
	{
		return this.stage;
	}

	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound syncData = new NBTTagCompound();
		this.writeSyncableDataToNBT(syncData);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, syncData);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	{
		readSyncableDataFromNBT(pkt.func_148857_g());
	}
	
	
	private void writeSyncableDataToNBT(NBTTagCompound syncData)
	{
		syncData.setInteger("chance", this.getChance());
		
	}

	private void readSyncableDataFromNBT(NBTTagCompound nbt)
	{
		this.chance = nbt.getInteger("chance");
	}
}
