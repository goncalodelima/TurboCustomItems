package pt.gongas.customitems.platforms.bukkit;

import co.aikar.commands.BukkitCommandManager;
import de.tr7zw.nbtapi.utils.metrics.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;
import pt.gongas.customitems.platforms.bukkit.command.CustomItemCommand;
import pt.gongas.customitems.platforms.bukkit.model.CustomItem;
import pt.gongas.customitems.platforms.bukkit.model.loader.CustomItemLoader;
import pt.gongas.customitems.platforms.bukkit.model.service.CustomItemFoundationService;
import pt.gongas.customitems.platforms.bukkit.model.service.CustomItemService;
import pt.gongas.customitems.platforms.bukkit.util.config.BukkitConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BukkitCustomItemsPlugin extends JavaPlugin {

    private BukkitConfiguration lang;

    private List<BukkitConfiguration> items;

    private CustomItemFoundationService customItemService;

    private Metrics metrics;

    @Override
    public void onEnable() {

        lang = new BukkitConfiguration(this, "lang", "lang.yml");
        lang.saveDefaultConfig();

        items = new ArrayList<>();
        loadItems();

        customItemService = new CustomItemService();

        Set<CustomItem> customItems = new CustomItemLoader(items).setup();
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

    public void register() {
        registerCommand();
    }

    public void registerCommand() {
        BukkitCommandManager commandManager = new BukkitCommandManager(this);
        commandManager.registerCommand(new CustomItemCommand(lang, customItemService));
        commandManager.getCommandCompletions().registerAsyncCompletion("customitems", c -> new ArrayList<>(customItemService.getKeys()));
    }

    private void loadItems() {

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

}
