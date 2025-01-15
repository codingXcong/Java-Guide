package io.zgc.design.patterns.structural.composite;

import java.util.*;

/**
 * 例子来源zuul框架
 *
 * 组合多个 DiscoveryClient 实例：
 * CompositeDiscoveryClient 是一个组合对象，它持有一个 DiscoveryClient 类型的列表，可以将多个 DiscoveryClient 实例组合在一起。
 * 它的核心目的是将多个 DiscoveryClient 的功能集合起来，统一对外提供服务。
 *
 * 统一接口：
 * CompositeDiscoveryClient 实现了 DiscoveryClient 接口，并通过实现接口中的方法（如 getInstances() 和 getServices()）来提供对多个 DiscoveryClient 的统一访问。
 * 即使内部有多个不同的客户端，它仍然提供了一个统一的接口，使得外部代码可以像操作单个 DiscoveryClient 那样，操作这个复合客户端。
 */

/**
public class CompositeDiscoveryClient implements DiscoveryClient {
    private final List<DiscoveryClient> discoveryClients;

    public CompositeDiscoveryClient(List<DiscoveryClient> discoveryClients) {
        AnnotationAwareOrderComparator.sort(discoveryClients);
        this.discoveryClients = discoveryClients;
    }

    public String description() {
        return "Composite Discovery Client";
    }

    public List<ServiceInstance> getInstances(String serviceId) {
        if (this.discoveryClients != null) {
            Iterator var2 = this.discoveryClients.iterator();

            while(var2.hasNext()) {
                DiscoveryClient discoveryClient = (DiscoveryClient)var2.next();
                List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
                if (instances != null && !instances.isEmpty()) {
                    return instances;
                }
            }
        }

        return Collections.emptyList();
    }

    public List<String> getServices() {
        LinkedHashSet<String> services = new LinkedHashSet();
        if (this.discoveryClients != null) {
            Iterator var2 = this.discoveryClients.iterator();

            while(var2.hasNext()) {
                DiscoveryClient discoveryClient = (DiscoveryClient)var2.next();
                List<String> serviceForClient = discoveryClient.getServices();
                if (serviceForClient != null) {
                    services.addAll(serviceForClient);
                }
            }
        }

        return new ArrayList(services);
    }

    public List<DiscoveryClient> getDiscoveryClients() {
        return this.discoveryClients;
    }
}
 */