package com.gmail.filoghost.holographicdisplays.nms.v1_8_R1;

import net.minecraft.server.v1_8_R1.AxisAlignedBB;
import net.minecraft.server.v1_8_R1.DamageSource;
import net.minecraft.server.v1_8_R1.EntitySlime;
import net.minecraft.server.v1_8_R1.NBTTagCompound;
import net.minecraft.server.v1_8_R1.World;

import org.bukkit.craftbukkit.v1_8_R1.entity.CraftEntity;
import com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.NMSSlime;
import com.gmail.filoghost.holographicdisplays.object.line.CraftHologramLine;
import com.gmail.filoghost.holographicdisplays.object.line.CraftTouchSlimeLine;

public class EntityNMSSlime extends EntitySlime implements NMSSlime {

	private boolean lockTick;
	private CraftTouchSlimeLine parentPiece;
	
	public EntityNMSSlime(World world, CraftTouchSlimeLine parentPiece) {
		super(world);
		super.persistent = true;
		a(0.0F, 0.0F);
		setSize(1);
		setInvisible(true);
		this.parentPiece = parentPiece;
		forceSetBoundingBox(new NullBoundingBox());
	}
	
	@Override
	public void a(AxisAlignedBB boundingBox) {
		// Do not change it!
	}
	
	public void forceSetBoundingBox(AxisAlignedBB boundingBox) {
		super.a(boundingBox);
	}
	
	@Override
	public void s_() {
		// Checks every 20 ticks.
		if (ticksLived % 20 == 0) {
			// The slime dies without a vehicle.
			if (this.vehicle == null) {
				die();
			}
		}
		
		if (!lockTick) {
			super.s_();
		}
	}
	
	@Override
	public void b(NBTTagCompound nbttagcompound) {
		// Do not save NBT.
	}
	
	@Override
	public boolean c(NBTTagCompound nbttagcompound) {
		// Do not save NBT.
		return false;
	}

	@Override
	public boolean d(NBTTagCompound nbttagcompound) {
		// Do not save NBT.
		return false;
	}
	
	@Override
	public void e(NBTTagCompound nbttagcompound) {
		// Do not save NBT.
	}
	
	@Override
	public boolean isInvulnerable(DamageSource source) {
		/* 
		 * The field Entity.invulnerable is private.
		 * It's only used while saving NBTTags, but since the entity would be killed
		 * on chunk unload, we prefer to override isInvulnerable().
		 */
	    return true;
	}

	@Override
	public void setCustomName(String customName) {
		// Locks the custom name.
	}
	
	@Override
	public void setCustomNameVisible(boolean visible) {
		// Locks the custom name.
	}
	
	@Override
	public void makeSound(String sound, float volume, float pitch) {
	    // Remove sounds.
	}
	
	@Override
	public void setLockTick(boolean lock) {
		lockTick = lock;
	}
	
	@Override
	public void die() {
		setLockTick(false);
		super.die();
	}
	
	@Override
	public CraftEntity getBukkitEntity() {
		if (super.bukkitEntity == null) {
			this.bukkitEntity = new CraftNMSSlime(this.world.getServer(), this);
	    }
		return this.bukkitEntity;
	}

	@Override
	public boolean isDeadNMS() {
		return super.dead;
	}
	
	@Override
	public void killEntityNMS() {
		die();
	}
	
	@Override
	public void setLocationNMS(double x, double y, double z) {
		super.setPosition(x, y, z);
	}
	
	@Override
	public int getIdNMS() {
		return this.getId();
	}
	
	@Override
	public CraftHologramLine getHologramLine() {
		return parentPiece;
	}

	@Override
	public org.bukkit.entity.Entity getBukkitEntityNMS() {
		return getBukkitEntity();
	}
}
