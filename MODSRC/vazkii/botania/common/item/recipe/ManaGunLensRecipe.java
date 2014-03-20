/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 * 
 * File Created @ [Mar 13, 2014, 8:01:14 PM (GMT)]
 */
package vazkii.botania.common.item.recipe;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import vazkii.botania.api.mana.ILens;
import vazkii.botania.common.item.ItemManaGun;

public class ManaGunLensRecipe implements IRecipe {

	@Override
	public boolean matches(InventoryCrafting var1, World var2) {
		boolean foundLens = false;
		boolean foundGun = false;

		for(int i = 0; i < var1.getSizeInventory(); i++) {
			ItemStack stack = var1.getStackInSlot(i);
			if(stack != null) {
				if(stack.getItem() instanceof ItemManaGun && ItemManaGun.getLens(stack) == null)
					foundGun = true;

				else if(stack.getItem() instanceof ILens)
					foundLens = true;

				else return false; // Found an invalid item, breaking the recipe
			}
		}

		return foundLens && foundGun;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting var1) {
		ItemStack lens = null;
		ItemStack gun = null;

		for(int i = 0; i < var1.getSizeInventory(); i++) {
			ItemStack stack = var1.getStackInSlot(i);
			if(stack != null) {
				if(stack.getItem() instanceof ItemManaGun)
					gun = stack;
				else if(stack.getItem() instanceof ILens)
					lens = stack;
			}
		}

		if(lens == null || gun == null)
			return null;

		ItemStack gunCopy = gun.copy();
		ItemManaGun.setLens(gunCopy, lens);

		return gunCopy;
	}


	@Override
	public int getRecipeSize() {
		return 10;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return null;
	}

}