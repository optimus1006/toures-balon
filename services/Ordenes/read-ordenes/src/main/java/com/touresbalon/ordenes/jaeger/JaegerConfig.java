package com.touresbalon.ordenes.kafka;

import io.jaegertracing.Configuration;
import io.jaegertracing.internal.JaegerTracer;
import io.jaegertracing.internal.samplers.ProbabilisticSampler;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JaegerConfig {

    public JaegerTracer getTracer() {

        Configuration.SamplerConfiguration samplerConfig = Configuration.SamplerConfiguration.fromEnv()
                .withType(ProbabilisticSampler.TYPE).withParam(1);

        /* Update default sender configuration with custom host and port */
        Configuration.SenderConfiguration senderConfig = Configuration.SenderConfiguration.fromEnv()
                .withAgentHost("10.0.1.102")
                .withAgentPort(6831);
        /* End */

        Configuration.ReporterConfiguration reporterConfig = Configuration.ReporterConfiguration.fromEnv()
                .withLogSpans(true)
                .withSender(senderConfig);

        Configuration config = new Configuration("API Ordenes").withSampler(samplerConfig)
                .withReporter(reporterConfig);

        return config.getTracer();
    }

}
