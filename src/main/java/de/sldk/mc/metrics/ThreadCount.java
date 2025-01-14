package de.sldk.mc.metrics;

import io.prometheus.client.Gauge;
import org.bukkit.plugin.Plugin;

public class ThreadCount extends Metric {

    private static final Gauge THREAD_COUNT = Gauge.build()
            .name(prefix("thread_count"))
            .help("Number of threads used by the server")
            .create();

    public ThreadCount(Plugin plugin) {
        super(plugin, THREAD_COUNT);
    }

    private double getThreadCount() {
        return Thread.activeCount();
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
        THREAD_COUNT.set(getThreadCount());
    }
}