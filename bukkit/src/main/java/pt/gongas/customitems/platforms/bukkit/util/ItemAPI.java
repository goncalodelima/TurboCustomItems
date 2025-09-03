package pt.gongas.customitems.platforms.bukkit.util;

import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import pt.gongas.customitems.shared.util.Item;

public class ItemAPI implements Item<Player, EntityEquipment, ItemStack> {

    @Override
    public void setPlayerMainItemHand(Player player, ItemStack item) {
        player.getInventory().setItemInMainHand(item);
    }

    @Override
    public ItemStack getPlayerMainItemHand(Player player) {
        return player.getInventory().getItemInMainHand();
    }

    @Override
    public void setArmorStandMainItemHand(EntityEquipment equipment, ItemStack item) {
        equipment.setItemInMainHand(item);
    }

    @Override
    public ItemStack getArmorStandMainItemHand(EntityEquipment equipment) {
        return equipment.getItemInMainHand();
    }

    @Override
    public void setArmorStandOffHandItemHand(EntityEquipment equipment, ItemStack item) {
        equipment.setItemInOffHand(item);
    }

    @Override
    public ItemStack getArmorStandOffHandItemHand(EntityEquipment equipment) {
        return equipment.getItemInOffHand();
    }

}
