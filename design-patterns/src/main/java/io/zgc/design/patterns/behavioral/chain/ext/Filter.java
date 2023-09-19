package io.zgc.design.patterns.behavioral.chain.ext;

public interface Filter {

    void doFilter(Request request,Response response,FilterChain chain);
}
