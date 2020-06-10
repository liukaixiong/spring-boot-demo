package com.lkx.demo.springboot.context;

import com.lkx.demo.springboot.configuration.FeignClientsConfiguration;

public class ElabFeignContext extends ElabNamedContextFactory<ElabFeignClientSpecification> {

	public ElabFeignContext() {
		super(FeignClientsConfiguration.class, "feign", "feign.client.name");
	}

}