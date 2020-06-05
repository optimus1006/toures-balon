package org.openapitools;

import com.fasterxml.jackson.databind.Module;

import io.jaegertracing.Configuration;
import io.jaegertracing.Configuration.SenderConfiguration;
import io.jaegertracing.internal.JaegerTracer;
import io.jaegertracing.internal.samplers.ProbabilisticSampler;

import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan(basePackages = {"org.openapitools", "com.touresbalon.api","com.touresbalon.api.repository" , "org.openapitools.configuration"})
//@EnableJpaRepositories("com.touresbalon.api.repository.read")
public class OpenAPI2SpringBoot implements CommandLineRunner {

	
	private static ApplicationContext applicationContext;
	
    @Override
    public void run(String... arg0) throws Exception {
        if (arg0.length > 0 && arg0[0].equals("exitcode")) {
            throw new ExitException();
        }
    }

    public static void main(String[] args) throws Exception {
    	new SpringApplication(OpenAPI2SpringBoot.class).run(args);
//    	checkBeansPresence("ClienteRepository", "springBootComponentScanApp");
    }

    static class ExitException extends RuntimeException implements ExitCodeGenerator {
        private static final long serialVersionUID = 1L;

        @Override
        public int getExitCode() {
            return 10;
        }

    }

    @Bean
    public WebMvcConfigurer webConfigurer() {
        return new WebMvcConfigurer() {
            /*@Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("*")
                        .allowedHeaders("Content-Type");
            }*/
        };
    }
    
    private static void checkBeansPresence(String... beans) {
        for (String beanName : beans) {
            System.out.println("Is " + beanName + " in ApplicationContext: " + 
              applicationContext.containsBean(beanName));
        }
    }

    @Bean
    public Module jsonNullableModule() {
        return new JsonNullableModule();
    }

	@Bean
	public static JaegerTracer getTracer() {

		System.out.println("Iniciando Jaeger");

//		Configuration.SamplerConfiguration samplerConfig = Configuration.SamplerConfiguration.fromEnv()
//				.withType("const").withParam(1);
//		Configuration.ReporterConfiguration reporterConfig = Configuration.ReporterConfiguration.fromEnv()
//				.withLogSpans(true);
//		Configuration config = new Configuration("API Cientes").withSampler(samplerConfig)
//				.withReporter(reporterConfig);
//		return config.getTracer();
		
		Configuration.SamplerConfiguration samplerConfig = Configuration.SamplerConfiguration.fromEnv()
                .withType(ProbabilisticSampler.TYPE).withParam(1);

        /* Update default sender configuration with custom host and port */
            SenderConfiguration senderConfig = Configuration.SenderConfiguration.fromEnv()
                    .withAgentHost("10.0.1.102")
                    .withAgentPort(6831);
        /* End */

        Configuration.ReporterConfiguration reporterConfig = Configuration.ReporterConfiguration.fromEnv()
                .withLogSpans(true)
                .withSender(senderConfig);

        Configuration config = new Configuration("API clientes").withSampler(samplerConfig)
                .withReporter(reporterConfig);

        return config.getTracer();
	}

}
