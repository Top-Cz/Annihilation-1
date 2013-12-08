package net.coasterman10.Annihilation;

import net.coasterman10.Annihilation.bar.BarManager;

import org.bukkit.plugin.java.JavaPlugin;

public final class Annihilation extends JavaPlugin {
    public ConfigManager config;
    public BarManager bar;

    private boolean ticking;
    private int time = -120;

    @Override
    public void onEnable() {
	SchedulerUtil.initialize(this);

	config = new ConfigManager(this);
	config.loadConfigFile("config.yml");

	bar = new BarManager(this);
	bar.setPercent(0F);
	bar.setVisible(false);
    }

    public void startTimer() {
	ticking = true;
	bar.setVisible(true);
	SchedulerUtil.runRepeating(new Runnable() {
	    public void run() {
		onSecond();
	    }
	}, 20L, 20L);
    }

    public void startGame() {

    }

    public void advancePhase() {

    }

    public void onSecond() {
	if (ticking) {
	    time++;

	    float percent = 100 * (120F + time) / 120F;
	    bar.setPercent(percent);
	    getServer().broadcastMessage("percent: " + percent);

	    if (time == 0)
		startGame();

	    if (time % 600 == 0) {
		advancePhase();
	    }
	}
    }

    public int getPhase() {
	if (time < 0)
	    return 0;
	return time / 600 + 1;
    }
}