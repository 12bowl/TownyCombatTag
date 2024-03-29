package net.earthmc.emccom;

import net.earthmc.emccom.combat.CombatHandler;
import net.earthmc.emccom.combat.bossbar.BossBarTask;
import net.earthmc.emccom.combat.listener.CombatListener;
import net.earthmc.emccom.combat.listener.CommandListener;
import net.earthmc.emccom.commands.CombatTagCommand;
import net.earthmc.emccom.util.Translation;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;

public final class EMCCOM extends JavaPlugin {

    private static EMCCOM instance;

    public static EMCCOM getInstance() {
        return instance;
    }

    private static Logger log = Bukkit.getLogger();

    public CombatHandler combatHandler;

    public CombatHandler getCombatHandler() {
        return combatHandler;
    }

    @Override
    public void onEnable() {
        instance = this;
        log.info("§e======= §a TownyCombatTag §e=======");

        Translation.loadStrings();

        setupListeners();
        setupCommands();
        runTasks();

        log.info("TownyCombatTag has been loaded.");
    }

    private void setupListeners() {
        getServer().getPluginManager().registerEvents(new CombatListener(), this);
        getServer().getPluginManager().registerEvents(new CommandListener(),this);
    }

    private void setupCommands() {
        log.info("§5= §bRegistering Commands");
        Objects.requireNonNull(getCommand("combattag")).setExecutor(new CombatTagCommand());
    }

    private void runTasks() {
        new BossBarTask().runTaskTimer(this, 10, 10);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        log.info(ChatColor.RED + ChatColor.BOLD.toString() + "TownyCombatTag has been disabled.");
    }

}
