package com.lkx.demo.springboot.context;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author Dave Syer
 * @author Gregor Zurowski
 */
public class ElabFeignClientSpecification implements ElabNamedContextFactory.Specification {

	private String name;

	private Class<?>[] configuration;

	public ElabFeignClientSpecification() {}

	public ElabFeignClientSpecification(String name, Class<?>[] configuration) {
		this.name = name;
		this.configuration = configuration;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Class<?>[] getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Class<?>[] configuration) {
		this.configuration = configuration;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ElabFeignClientSpecification that = (ElabFeignClientSpecification) o;
		return Objects.equals(name, that.name) &&
				Arrays.equals(configuration, that.configuration);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, configuration);
	}

	@Override
	public String toString() {
		return new StringBuilder("ElabFeignClientSpecification{")
				.append("name='").append(name).append("', ")
				.append("configuration=").append(Arrays.toString(configuration))
				.append("}").toString();
	}

}
