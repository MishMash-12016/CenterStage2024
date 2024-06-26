package org.firstinspires.ftc.teamcode.TeleOps;

import com.acmerobotics.dashboard.FtcDashboard;
import com.arcrobotics.ftclib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.ArmPositionSelector;
import org.firstinspires.ftc.teamcode.Commands.intakeLifter.IntakeSetLifterPosition;
import org.firstinspires.ftc.teamcode.RobotControl;
import org.firstinspires.ftc.teamcode.SubSystems.DroneLauncher;
import org.firstinspires.ftc.teamcode.SubSystems.Intake;
import org.firstinspires.ftc.teamcode.Utils.AllianceColor;
public class TeleOpMode extends CommandOpMode {
    RobotControl robot;

    AllianceColor allianceColor;

    private boolean firstIteration = true;

    public TeleOpMode(AllianceColor allianceColor) {
        this.allianceColor = allianceColor;
    }

    @Override
    public void initialize() {
        robot = new RobotControl(RobotControl.OpModeType.TELEOP, allianceColor, hardwareMap, gamepad1, gamepad2, telemetry);

        schedule(
                new IntakeSetLifterPosition(robot.intake.lifter, Intake.LifterPosition.INIT)
        );
    }

    @Override
    public void run() {
        super.run();
        if(firstIteration) {
            robot.intake.lifter.setPosition(Intake.LifterPosition.DEFAULT);
            robot.droneLauncher.setPosition(0);
            firstIteration = false;
        }

        ArmPositionSelector.telemetry(telemetry);


        telemetry.addData("inDebugMode", robot.inDebugMode);
        telemetry.addData("Elevator Height", robot.elevator.getHeight());
        telemetry.addData("elevator Switch", robot.elevator.getSwitchState());
        telemetry.addData("pixelCount", robot.intake.roller.getPixelCount());
        telemetry.addData("isRobotFull", robot.intake.roller.isRobotFull());
        telemetry.addData("selectedPosition", ArmPositionSelector.getPosition());
        telemetry.addData("isLeftOfBoard", ArmPositionSelector.getIsLeftOfBoard());
        telemetry.update();
    }
}