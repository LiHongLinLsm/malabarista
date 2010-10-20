package org.factor45.malabarista.balancing;

import org.factor45.malabarista.util.HostAndPort;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author <a href="http://bruno.factor45.org/">Bruno de Carvalho</a>
 */
public class RoundRobinBalancingStrategy implements BalancingStrategy {

    // configuration --------------------------------------------------------------------------------------------------

    private final List<HostAndPort> targets;
    private final AtomicInteger currentTarget;

    // constructors ---------------------------------------------------------------------------------------------------

    public RoundRobinBalancingStrategy(List<HostAndPort> targets) {
        if ((targets == null) || targets.isEmpty()) {
            throw new IllegalArgumentException("Target list cannot be null or empty");
        }
        this.targets = new CopyOnWriteArrayList<HostAndPort>(targets);
        this.currentTarget = new AtomicInteger(0);
    }

    // BalancingStrategy ----------------------------------------------------------------------------------------------

    @Override
    public HostAndPort selectTarget(String originHost, int originPort) {
        int currentTarget;
        synchronized (this.currentTarget) {
            currentTarget = this.currentTarget.getAndIncrement();
            if (currentTarget >= this.targets.size()) {
                currentTarget = 0;
                this.currentTarget.set(0);
            }
        }

        return this.targets.get(currentTarget);
    }

    @Override
    public List<HostAndPort> geTargetAddresses() {
        return Collections.unmodifiableList(this.targets);
    }
}
