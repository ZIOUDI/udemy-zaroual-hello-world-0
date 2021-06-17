package fr.cegeleme.helloworld;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

// une tasklet est un bloc de code qui va s'executé dans le cadre d'une transaction d'une Step
public class HelloWorldTasklet implements Tasklet {

    private final String name;

    public HelloWorldTasklet(final String name){
        super();
        this.name = name ;
    }
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext)
            throws Exception {
        System.out.println("Hello "+ name);
        return RepeatStatus.FINISHED ;
    }
}
