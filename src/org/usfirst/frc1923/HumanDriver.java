package org.usfirst.frc1923;

import org.usfirst.frc1923.components.DriveComponent;
import org.usfirst.frc1923.components.DriveGearbox;
import org.usfirst.frc1923.components.Joyfulstick;
import org.usfirst.frc1923.components.PneumaticComponent;
import org.usfirst.frc1923.components.ShooterComponent;
import org.usfirst.frc1923.components.ShooterGearbox;
import org.usfirst.frc1923.components.XboxController;
import org.usfirst.frc1923.utils.Coalescor;

/**
 * Joysticks provide input to drive the robot
 * 
 * @author Aayush Sharma, Olu Olorode
 * @version 1.0
 * @since 1/19/12
 */
public class HumanDriver {

	Coalescor coalescer = new Coalescor();
	Joyfulstick left;
	Joyfulstick right;
	DriveComponent robotDrive;
	DriveGearbox driveGearBox;
	XboxController operator;
	ShooterGearbox shooterGearBox;
	ShooterComponent shooter;
	PneumaticComponent solenoid;
	Components components;
	
	public HumanDriver(Components components) {
		this.components = components;
		this.left = components.leftStick;
		this.right = components.rightStick;
		this.robotDrive = components.robotDrive;
		this.driveGearBox = components.driveGearbox;
		this.operator = components.operator;
		this.shooterGearBox = components.shooterGearbox;
		this.shooter = components.shooter;
	}

	public void handleActiveDriving() {
		double leftY = left.getCoalescedY();
		double rightY = right.getCoalescedY();
		robotDrive.tankDrive(leftY, -rightY);
	}
	
	public void handleActiveOperating() {
		if(operator.getButton(XboxController.Button.RB)) {
			shooter.runLeft(shooterGearBox.getLeftSpeed());
			shooter.runRight(shooterGearBox.getRightSpeed());
		}
		if(operator.getButton(XboxController.Button.LB)) {
			shooter.stop();
		}
		if (operator.getButton(XboxController.Button.Start)) {
			solenoid.activate();
			solenoid.deactivate();
		}
	}
	
	public void handlePassiveOperating() {
		if(operator.getButton(XboxController.Button.X) && shooterGearBox.getLeftSpeed() < 1.00)  {
			shooterGearBox.leftGearUp();
		} else if (operator.getButton(XboxController.Button.A) && shooterGearBox.getLeftGear() > 0) {
			shooterGearBox.leftGearDown();
		}
		if(operator.getButton(XboxController.Button.Y) && shooterGearBox.getRightSpeed() < 1.00) {
			shooterGearBox.rightGearUp();
		} else if(operator.getButton(XboxController.Button.B) && shooterGearBox.getRightSpeed() > 0) {
			shooterGearBox.rightGearDown();
		}
		if(operator.getButton(XboxController.Button.LB)) {
			shooter.stop();
		}
	}

	public void stop() {
		robotDrive.stopMotor();
		shooterGearBox.setLeftGear(0);
		shooterGearBox.setRightGear(0);
	}
}