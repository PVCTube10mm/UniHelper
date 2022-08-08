import com.github.UniHelper.presenters.commands.Command;
import com.github.UniHelper.views.utils.NamedButton;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

class NamedButtonTest {
    NamedButton namedButton;

    @BeforeEach
    void initialize(){
        namedButton = new NamedButton("button name");
    }

    @Test
    void getNameShouldReturnCorrectName() {
        //Given

        //When
        String name = namedButton.getName();

        //Then
        Assertions.assertEquals("button name", name);
    }

    @Test
    void addedCommandShouldExecuteOneTimeOnceNotified() {
        //Given
        AtomicInteger numberOfExecutions = new AtomicInteger();
        Command command = numberOfExecutions::getAndIncrement;
        namedButton.addCommand(command);

        //When
        namedButton.doClick();

        //Then
        Assertions.assertEquals(1, numberOfExecutions.get());
    }

    @Test
    void addedMultipleCommandsShouldAllExecuteOnceNotified() {
        //Given
        AtomicInteger numberOfCommand1Executions = new AtomicInteger();
        AtomicInteger numberOfCommand2Executions = new AtomicInteger();
        Command command1 = numberOfCommand1Executions::getAndIncrement;
        Command command2 = numberOfCommand2Executions::getAndIncrement;
        namedButton.addCommand(command1);
        namedButton.addCommand(command2);

        //When
        namedButton.doClick();

        //Then
        Assertions.assertEquals(1, numberOfCommand1Executions.get());
        Assertions.assertEquals(1, numberOfCommand2Executions.get());
    }

    @Test
    void addedSameCommandMultipleTimesShouldExecuteAsManyTimesOnceNotified(){
        //Given
        AtomicInteger numberOfExecutions = new AtomicInteger();
        Command command = numberOfExecutions::getAndIncrement;
        namedButton.addCommand(command);
        namedButton.addCommand(command);
        namedButton.addCommand(command);

        //When
        namedButton.doClick();

        //Then
        Assertions.assertEquals(3, numberOfExecutions.get());
    }

    @Test
    void setCommandShouldRemoveOtherCommands(){
        //Given
        AtomicInteger numberOfCommand1Executions = new AtomicInteger();
        AtomicInteger numberOfCommand2Executions = new AtomicInteger();
        Command command1 = numberOfCommand1Executions::getAndIncrement;
        Command command2 = numberOfCommand2Executions::getAndIncrement;
        namedButton.addCommand(command1);

        //When
        namedButton.setCommand(command2);
        namedButton.doClick();

        //Then
        Assertions.assertEquals(0, numberOfCommand1Executions.get());
        Assertions.assertEquals(1, numberOfCommand2Executions.get());
    }

}
