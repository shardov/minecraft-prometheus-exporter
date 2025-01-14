package de.sldk.mc.metrics;

import io.prometheus.client.Gauge;
import org.bukkit.plugin.Plugin;

public class Memory extends Metric {

    private static final Gauge MEMORY_USAGE_PERCENTAGE = Gauge.build()
            .name(prefix("jvm_memory"))
            .help("JVM memory usage percentage")
            .create();

    public Memory(Plugin plugin) {
        super(plugin, MEMORY_USAGE_PERCENTAGE);
    }

    @Override
    public void doCollect() {
        long maxMemory = Runtime.getRuntime().maxMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();
        long allocatedMemory = Runtime.getRuntime().totalMemory();
        long usedMemory = allocatedMemory - freeMemory;

        double memoryUsagePercentage = (double) usedMemory / maxMemory * 100;
        MEMORY_USAGE_PERCENTAGE.set(memoryUsagePercentage);
    }

    @Override
    public boolean isFoliaCapable() {
        return true;
    }

    @Override
    public boolean isAsyncCapable() {
        return true;
    }
}