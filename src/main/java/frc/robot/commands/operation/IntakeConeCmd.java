package frc.robot.commands.operation;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.grabber.SuckObjectCmd;
import frc.robot.commands.grabber.OpenGrabberCmd;
import frc.robot.commands.grabber.ClampGrabberCmd;
import frc.robot.subsystems.GrabberSubsystem;

public class IntakeConeCmd extends CommandBase {

    private GrabberSubsystem grabberSubsystem;

    private OpenGrabberCmd openGrabberCmd = new OpenGrabberCmd(grabberSubsystem);
    private SuckObjectCmd suckObjectCmd = new SuckObjectCmd(grabberSubsystem, 500);
    private ClampGrabberCmd clampGrabberCmd = new ClampGrabberCmd(grabberSubsystem);

    private Command routine;

    public IntakeConeCmd(GrabberSubsystem grabberSubsystem) {
        this.grabberSubsystem = grabberSubsystem;

        addRequirements(grabberSubsystem);
    }

    @Override
    public void initialize() {
        routine = openGrabberCmd
                .andThen(new WaitCommand(0.5))
                .andThen(suckObjectCmd)
                .andThen(new WaitCommand(0.5))
                .andThen(clampGrabberCmd);

        routine.schedule();
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            routine.end(true);
        }
    }

    @Override
    public boolean isFinished() {
        return routine.isFinished();
    }

}