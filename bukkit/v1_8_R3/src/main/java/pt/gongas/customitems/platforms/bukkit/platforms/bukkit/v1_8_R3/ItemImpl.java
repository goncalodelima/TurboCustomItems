package pt.gongas.customitems.platforms.bukkit.platforms.bukkit.v1_8_R3;

import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import pt.gongas.customitems.shared.util.Item;

public class ItemImpl implements Item<Player, EntityEquipment, ItemStack> {

    @Override
    public void setPlayerMainItemHand(Player player, ItemStack item) {
        player.setItemInHand(item);
    }

    @Override
    public ItemStack getPlayerMainItemHand(Player player) {
        return player.getItemInHand();
    }

    @Override
    public void setArmorStandMainItemHand(EntityEquipment equipment, ItemStack item) {
        equipment.setItemInHand(item);
    }

    @Override
    public ItemStack getArmorStandMainItemHand(EntityEquipment equipment) {
        return equipment.getItemInHand();
    }

    @Override
    public void setArmorStandOffHandItemHand(EntityEquipment equipment, ItemStack item) {
    }

    @Override
    public ItemStack getArmorStandOffHandItemHand(EntityEquipment equipment) {
        return null;
    }

}
