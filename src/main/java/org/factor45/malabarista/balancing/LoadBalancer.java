package org.factor45.malabarista.balancing;

import org.factor45.malabarista.util.HostAndPort;

import java.util.List;

/**
 * @author <a href="mailto:bruno.carvalho@wit-software.com">Bruno de Carvalho</a>
 */
public interface LoadBalancer {

    boolean init();

    void terminate();

    HostAndPort getBalancerAddress();

    List<HostAndPort> getTargetAddresses();

    BalancingStrategy getBalancingStrategy();
}
