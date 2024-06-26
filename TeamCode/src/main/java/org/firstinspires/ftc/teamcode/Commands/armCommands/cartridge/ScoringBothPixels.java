package org.firstinspires.ftc.teamcode.Commands.armCommands.cartridge;

import org.firstinspires.ftc.teamcode.RobotControl;
import org.firstinspires.ftc.teamcode.SubSystems.Cartridge;

import java.util.function.BooleanSupplier;

public class ScoringBothPixels extends ScoringTeleCommand {
    public ScoringBothPixels(RobotControl robot, BooleanSupplier triggerCondition) {
        super(
                robot,
                triggerCondition,
                Cartridge.State.OPEN
        );
    }
}
