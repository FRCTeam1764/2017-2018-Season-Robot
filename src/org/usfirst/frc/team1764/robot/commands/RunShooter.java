package org.usfirst.frc.team1764.robot.commands;

import org.usfirst.frc.team1764.robot.Constants;
import org.usfirst.frc.team1764.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunShooter extends Command {

	boolean isTimed;
	Timer timer;
    public RunShooter(boolean isTimed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooter);
    	this.isTimed = isTimed;
    	if (isTimed)
    	{
    		timer = new Timer();
    	}
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (isTimed)
    	{
    		timer.start();
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    	Robot.shooter.set(Constants.FLYWHEEL_SPEED);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (isTimed)
    	{
    		if (timer.get() > Constants.INTAKE_WARMUP)
    		{
    			return true;
    		}
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.shooter.set(0);
    }
}
