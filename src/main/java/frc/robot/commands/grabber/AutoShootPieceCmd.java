package frc.robot.commands.grabber;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.RollerSubsystem;

public class AutoShootPieceCmd extends CommandBase {

    private RollerSubsystem rollerSubsystem;

    private double robotTime;
    private double shootOutTime;
    private boolean isDone;

    public AutoShootPieceCmd(RollerSubsystem rollerSubsystem) {
        this(rollerSubsystem, .15);
    }

    public AutoShootPieceCmd(RollerSubsystem rollerSubsystem, double shootOutTime) {
        this.rollerSubsystem = rollerSubsystem;
        this.shootOutTime = shootOutTime;

        addRequirements(rollerSubsystem);
    }

    @Override
    public void initialize() {
        robotTime = 0;
        isDone = false;

        robotTime = Timer.getFPGATimestamp();
        shootOutTime += robotTime;
    }

    @Override
    public void execute() {
        robotTime = Timer.getFPGATimestamp();
        if(robotTime < shootOutTime){
            rollerSubsystem.setRollerSpeedPercentage(.1);
        }
        else
        {
            isDone = true;
        }
    }

    @Override
    public void end(boolean interrupted) {
        rollerSubsystem.setRollerSpeedPercentage(0);
    }

    @Override
    public boolean isFinished() {
        return isDone;
    }
}