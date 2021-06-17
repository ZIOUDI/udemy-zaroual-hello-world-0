package fr.cegeleme.helloworld;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication // activer l'auto-configuration de Spring Boot
@EnableBatchProcessing // creer l'infrastructure d'un Job (tous les beans necessaire pour le demarrage d'un Job)
public class HelloWorldApplication {

    @Autowired // nous aide à créer des Steps
    private StepBuilderFactory stepBuilderFactory;

    @Autowired // nous aide à créer des Jobs
    JobBuilderFactory jobBuilderFactory ;

    @Bean
    public Step helloWorldStep(){ //1ere Step
       return stepBuilderFactory.get("step").tasklet(new HelloWorldTasklet(null)).build();
    }

    @Bean
    @StepScope // tasklet => StepScope
    public HelloWorldTasklet helloWorldTasklet(@Value("#{jobParameters['name']}") String name){
        return new HelloWorldTasklet(name);
    }
    @Bean
    public Job helloWorldJob(){
        return jobBuilderFactory.get("job").start(helloWorldStep()).build();
    }
    public static void main(String[] args) {
        SpringApplication.run(HelloWorldApplication.class, args);
    }

}
