package pt.gongas.customitems.platforms.bukkit;

import co.aikar.commands.BukkitCommandManager;
import com.cryptomorin.xseries.XEnchantment;
import com.cryptomorin.xseries.XItemFlag;
import com.cryptomorin.xseries.XMaterial;
import de.tr7zw.nbtapi.utils.metrics.bukkit.Metrics;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import pt.gongas.customitems.platforms.bukkit.command.BukkitCustomItemCommand;
import pt.gongas.customitems.platforms.bukkit.model.loader.BukkitCustomItemLoader;
import pt.gongas.customitems.shared.customitem.CustomItem;
import pt.gongas.customitems.shared.customitem.service.CustomItemFoundationService;
import pt.gongas.customitems.platforms.bukkit.model.service.BukkitCustomItemService;
import pt.gongas.customitems.platforms.bukkit.util.config.BukkitConfiguration;
import pt.gongas.customitems.shared.platform.PlatformLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BukkitCustomItemsPlugin extends JavaPlugin implements PlatformLoader<XEnchantment, XItemFlag, XMaterial, ItemStack> {

    private BukkitConfiguration lang;

    private List<BukkitConfiguration> items;

    private CustomItemFoundationService<XEnchantment, XItemFlag, XMaterial, ItemStack> customItemService;

    private BukkitCommandManager bukkitCommandManager;

    private Metrics metrics;

    public static BukkitCustomItemsPlugin LOADER;

    @Override
    public void onLoad() {
        LOADER = this;
    }

    @Override
    public void onEnable() {

        lang = new BukkitConfiguration(this, "lang", "lang.yml");
        lang.saveDefaultConfig();

        items = new ArrayList<>();
        loadItems();

        customItemService = new BukkitCustomItemService();

        Set<CustomItem<XEnchantment, XItemFlag, XMaterial, ItemStack>> customItems = new BukkitCustomItemLoader(items).setup();
        customItems.forEach(customItem -> customItemService.put(customItem));

        register();

        metrics = new Metrics(this, 27163);

    }

    @Override
    public void onDisable() {
        if (metrics != null) {
            metrics.shutdown();
        }
    }

    @Override
    public void reload() {

        if (lang != null) {
            lang.reloadConfig();
        }

        items.clear();
        loadItems();

        customItemService.clear();

        Set<CustomItem<XEnchantment, XItemFlag, XMaterial, ItemStack>> customItems = new BukkitCustomItemLoader(items).setup();
        customItems.forEach(customItem -> customItemService.put(customItem));

        bukkitCommandManager.getCommandCompletions().registerAsyncCompletion("customitems", c -> new ArrayList<>(customItemService.getKeys()));
    }

    @Override
    public void registerCommands() {
        bukkitCommandManager = new BukkitCommandManager(this);
        bukkitCommandManager.registerCommand(new BukkitCustomItemCommand(this, lang, customItemService));
        bukkitCommandManager.getCommandCompletions().registerAsyncCompletion("customitems", c -> new ArrayList<>(customItemService.getKeys()));
    }

    @Override
    public void loadItems() {

        File folder = new File(getDataFolder(), "items");

        if (!folder.exists()) {
            folder.mkdirs();
        }

        File[] files = folder.listFiles((dir, name) -> name.endsWith(".yml"));

        if (files == null || files.length == 0) {
            saveResource("items/enchanted_stone.yml", false);
            files = folder.listFiles((dir, name) -> name.endsWith(".yml"));
        }

        if (files == null) {
            return;
        }

        for (File file : files) {
            BukkitConfiguration config = new BukkitConfiguration(this, "items", file.getName());
            config.reloadConfig();
            items.add(config);
        }

    }

    @Override
    public CustomItemFoundationService<XEnchantment, XItemFlag, XMaterial, ItemStack> getCustomItemService() {
        return customItemService;
    }

}
