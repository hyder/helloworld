package net.midgard.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.dekorate.kubernetes.annotation.KubernetesApplication;
import io.dekorate.kubernetes.annotation.Label;
import io.dekorate.kubernetes.annotation.Port;
import io.dekorate.kubernetes.annotation.Probe;
import io.dekorate.kubernetes.annotation.ResourceRequirements;

@SpringBootApplication
@KubernetesApplication(
    name = "helloworld",
    labels = @Label(key = "app", value = "helloworld"),
    ports = @Port(name = "http", containerPort = 8080),
    livenessProbe = @Probe(httpActionPath = "/health/liveness", initialDelaySeconds = 5, timeoutSeconds = 3, failureThreshold = 10),
    readinessProbe = @Probe(httpActionPath = "/health/readiness", initialDelaySeconds = 5, timeoutSeconds = 3, failureThreshold = 10),
		requestResources=@ResourceRequirements(memory="64Mi", cpu="1m"),
		limitResources=@ResourceRequirements(memory="256Mi", cpu="5m")
)
public class HelloworldApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloworldApplication.class, args);
	}

}
