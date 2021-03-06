package io.github.mooy1.infinityexpansion.implementation.blocks;

import io.github.mooy1.infinityexpansion.implementation.abstracts.AbstractCrafter;
import io.github.mooy1.infinityexpansion.implementation.materials.SmelteryItem;
import io.github.mooy1.infinityexpansion.setup.categories.Categories;
import io.github.mooy1.infinitylib.delayed.DelayedRecipeType;
import io.github.mooy1.infinitylib.player.MessageUtils;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

/**
 * A crafting machine for upgrading storage units and retaining the stored items
 *
 * @author Mooy1
 */
public final class StorageForge extends AbstractCrafter {
    
    public static final SlimefunItemStack ITEM = new SlimefunItemStack(
            "STORAGE_FORGE",
            Material.BEEHIVE,
            "&6Storage Forge",
            "&7Upgrades the tier of Storage Units",
            "&7Retains stored items"
    );
    
    public static final DelayedRecipeType TYPE = new DelayedRecipeType(ITEM);
    
    public StorageForge() {
        super(Categories.STORAGE_TRANSPORT, ITEM, TYPE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SmelteryItem.MAGSTEEL, new ItemStack(Material.ANVIL), SmelteryItem.MAGSTEEL,
                SmelteryItem.MAGSTEEL, new ItemStack(Material.CRAFTING_TABLE), SmelteryItem.MAGSTEEL,
                SmelteryItem.MAGSTEEL, new ItemStack(Material.BARREL), SmelteryItem.MAGSTEEL,
        });
    }
    
    @Override
    protected void modifyOutput(@Nonnull BlockMenu inv, @Nonnull ItemStack output) {
        output.setItemMeta(StorageUnit.transferData(inv.getItemInSlot(INPUT_SLOTS[4]).getItemMeta(), output.getItemMeta()));
    }

    @Override
    public void postCraft(@Nonnull Location l, @Nonnull BlockMenu inv, @Nonnull Player p) {
        MessageUtils.message(p, ChatColor.GREEN + "Transferred items to upgraded unit");
    }
    
}
