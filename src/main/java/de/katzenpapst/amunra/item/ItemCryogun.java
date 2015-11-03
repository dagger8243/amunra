package de.katzenpapst.amunra.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import de.katzenpapst.amunra.entity.EntityBaseLaserArrow;
import de.katzenpapst.amunra.entity.EntityCryoArrow;

public class ItemCryogun extends ItemAbstractRaygun {

	protected IIcon itemEmptyIcon;


	public ItemCryogun(String assetName) {
		super(assetName);

	}



    @Override
	protected void spawnProjectile(ItemStack itemStack, EntityPlayer entityPlayer, World world) {
    	EntityBaseLaserArrow ent = new EntityCryoArrow(world, entityPlayer);
		world.spawnEntityInWorld(ent);
    }

}
