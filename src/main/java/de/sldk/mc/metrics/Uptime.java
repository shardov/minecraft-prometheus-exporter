package de.sldk.mc.metrics;

import io.prometheus.client.Gauge;
import org.bukkit.plugin.Plugin;

public class Uptime extends Metric {

    private static final Gauge UPTIME = Gauge.build()
            .name(prefix("uptime"))
            .help("Server uptime in minutes")
            .create();
    private final long serverStartTime;

    public Uptime(Plugin plugin) {
        super(plugin, UPTIME);
        this.serverStartTime = System.currentTimeMillis();
    }

    private double getUptimeInMinutes() {
        return (System.currentTimeMillis() - serverStartTime) / 1000 / 60;
    }

    @Override
    public void enable() {
        super.enable();
        doCollect();
    }

    @Override
    public void disable() {
        super.disable();
        doCollect();
    }


    public void doCollect() {
        UPTIME.set(getUptimeInMinutes());
    }
}
