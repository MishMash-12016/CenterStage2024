package org.firstinspires.ftc.teamcode.SubSystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Elevator extends SubsystemBase {
    private DcMotor[] elevatorMotors = new DcMotor[3];
    private DcMotor encoder;
    private final double LEVELS = 3;
    private final double TEETH_PER_REV = 8;
    private final double CHAIN_LINK_DISTANCE = 0.8;
    private final double TICKS_PER_REV = 384.5;
    private final double KG = 0;
    private PIDController pidController = new PIDController(1,0,1);


    public Elevator(DcMotor elevatorMotor1, DcMotor elevatorMotor2, DcMotor elevatorMotor3) {
        this.elevatorMotors[0] = elevatorMotor1;
        this.elevatorMotors[1] = elevatorMotor2;
        this.elevatorMotors[2] = elevatorMotor3;
        elevatorMotor2.setDirection(DcMotorSimple.Direction.REVERSE);
        this.encoder = elevatorMotor1;
        this.encoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void setPower(double power) {
        for (DcMotor motor: elevatorMotors) {
            motor.setPower(power);
        }
    }

    public double getHeight(){
        double motorRevs = encoder.getCurrentPosition() / TICKS_PER_REV;
        double lengthPerRev = CHAIN_LINK_DISTANCE * TEETH_PER_REV;
        double pulledLength = lengthPerRev * motorRevs;
        return LEVELS * pulledLength;
    }

    public double getKG() {
        return KG;
    }

    public PIDController getPidController() {
        return pidController;
    }
}

