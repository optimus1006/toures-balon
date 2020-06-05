package org.openapitools.configuration;

import javax.enterprise.context.ApplicationScoped;

import io.jaegertracing.Configuration;
import io.jaegertracing.Configuration.SenderConfiguration;
import io.jaegertracing.internal.JaegerTracer;
import io.jaegertracing.internal.samplers.ProbabilisticSampler;
import io.quarkus.runtime.Startup;

@Startup 
@ApplicationScoped
public class OpenAPIDocumentationConfig {

	public JaegerTracer getTracer() {
		
		Configuration.SamplerConfiguration samplerConfig = Configuration.SamplerConfiguration.fromEnv()
                .withType(ProbabilisticSampler.TYPE).withParam(1);

        /* Update default sender configuration with custom host and port */
            SenderConfiguration senderConfig = Configuration.SenderConfiguration.fromEnv()
                    .withAgentHost("10.0.1.102")
//            		.withAgentHost("ec2-3-128-34-69.us-east-2.compute.amazonaws.com")
                    .withAgentPort(6831);
        /* End */

        Configuration.ReporterConfiguration reporterConfig = Configuration.ReporterConfiguration.fromEnv()
                .withLogSpans(true)
                .withSender(senderConfig);

        Configuration config = new Configuration("API Productos").withSampler(samplerConfig)
                .withReporter(reporterConfig);

        return config.getTracer();
	}
}
