package org.firstinspires.ftc.teamcode.Commands.intakeRoller;

import com.arcrobotics.ftclib.command.FunctionalCommand;
import com.arcrobotics.ftclib.command.ParallelDeadlineGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.WaitUntilCommand;

import org.firstinspires.ftc.teamcode.SubSystems.Intake;
public class IntakeUntilFull extends ParallelDeadlineGroup {
    private static final long waitTimeUntilStop = 1000;
    public IntakeUntilFull(Intake.Roller intakeRoller) {
        super(
                new SequentialCommandGroup(
                        new WaitUntilCommand(intakeRoller::isRobotFull),
                        new WaitCommand(waitTimeUntilStop)
                ).asProxy(),
                new IntakeRotate(intakeRoller, intakeRoller.COLLECT_POWER)
        );
    }
}