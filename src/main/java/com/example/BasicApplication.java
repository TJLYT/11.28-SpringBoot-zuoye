package com.example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.support.ServletRequestHandledEvent;

@SpringBootApplication
public class BasicApplication {

	public static void main(String[] args) {
		ApplicationContext context=SpringApplication.run(BasicApplication.class, args);
		System.out.println(context.getBean(TaijCongfiguration.class));
		//System.out.println(context.getBean(MyHealthIndicator.class));
	}
	
	
	// 可用于递增、递减和重置指定计数器值的服务。
	//@param metricName是计数器的名称
	@Autowired
	private  CounterService counterService;
	
	@Bean
	public ApplicationListener<ApplicationEvent> helloListener() {
		final String HELLO_URL = "/xyz";

		return (ApplicationEvent event) -> {
			if (event instanceof ServletRequestHandledEvent) {
				ServletRequestHandledEvent e = (ServletRequestHandledEvent) event;
				if (e.getRequestUrl().equals(HELLO_URL))
					counterService.increment("xyz.hits");
			}
		};
	}
	
	@Bean
	public HealthIndicator myHealth() {
		return () -> {
			int errorCode = 400;
			if (errorCode != 0) {
				return Health.down().withDetail("Error Code", errorCode).build();
			}
			return Health.up().build();
		};
	}
	
	
	
}
