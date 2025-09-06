package pt.gongas.customitems.platforms.bukkit.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.cryptomorin.xseries.XEnchantment;
import com.cryptomorin.xseries.XItemFlag;
import com.cryptomorin.xseries.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pt.gongas.customitems.platforms.bukkit.BukkitCustomItemsPlugin;
import pt.gongas.customitems.shared.customitem.CustomItem;
import pt.gongas.customitems.shared.customitem.service.CustomItemFoundationService;
import pt.gongas.customitems.platforms.bukkit.util.config.BukkitConfiguration;
import pt.gongas.customitems.shared.util.KeyConstants;
import de.tr7zw.nbtapi.NBT;

import java.util.*;
import java.util.Optional;

@CommandAlias("customitem|customitems|citem|citems|customi")
public class BukkitCustomItemCommand extends BaseCommand {

    private final BukkitCustomItemsPlugin plugin;

    private final BukkitConfiguration lang;

    private final CustomItemFoundationService<XEnchantment, XItemFlag, XMaterial, ItemStack> customItemService;

    public BukkitCustomItemCommand(BukkitCustomItemsPlugin plugin, BukkitConfiguration lang, CustomItemFoundationService<XEnchantment, XItemFlag, XMaterial, ItemStack> customItemService) {
        this.plugin = plugin;
        this.lang = lang;
        this.customItemService = customItemService;
    }

    @Subcommand("give")
    @CommandPermission("turbocustomitems.admin")
    @Syntax("<target> <customItem> <amount>")
    @CommandCompletion("@players @customitems 1")
    public void giveCustomItem(CommandSender sender, String targetName, String identifier, int amount) {

        Player target = Bukkit.getPlayer(targetName);

        if (target == null) {
            sender.sendMessage(lang.getString("invalid-player", "§cMissing 'invalid-player' in your 'lang/lang.yml'.").replace("&", "§"));
            return;
        }

        Optional<CustomItem<XEnchantment, XItemFlag, XMaterial, ItemStack>> customItemOptional = customItemService.get(identifier);

        if (!customItemOptional.isPresent()) {
            sender.sendMessage(lang.getString("invalid-customitem", "§cMissing 'invalid-customitem' in your 'lang/lang.yml'.").replace("%customItems%", String.join(", ", customItemService.getKeys())).replace("&", "§"));
            return;
        }

        if (amount <= 0) {
            sender.sendMessage(lang.getString("invalid-amount", "§cMissing 'invalid-amount' in your 'lang/lang.yml'.").replace("&", "§"));
            return;
        }

        CustomItem<XEnchantment, XItemFlag, XMaterial, ItemStack> customItem = customItemOptional.get();
        ItemStack itemToGive = customItem.getItem();

        if (!customItem.isStackable()) {
            NBT.modify(itemToGive, readWriteItemNBT -> {
                readWriteItemNBT.setUUID(KeyConstants.ITEM_UUID, UUID.randomUUID());
            });
        }

        target.sendMessage(lang.getString("customitem-received", "§cMissing 'customitem-received' in your 'lang/lang.yml'.").replace("%customItem%", customItem.getIdentifier()).replace("&", "§"));
        target.getInventory().addItem(itemToGive);
    }

    @Subcommand("reload")
    @CommandPermission("turbocustomitems.admin")
    public void reload(CommandSender sender) {
        plugin.reload();
        sender.sendMessage(lang.getString("reload", "§cMissing 'reload' in your 'lang/lang.yml'.").replace("&", "§"));
    }

}
