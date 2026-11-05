package org.firstinspires.ftc.teamcode.util.subsystems

import com.qualcomm.robotcore.hardware.Gamepad
import dev.nextftc.hardware.actuators.NextMotor
import dev.nextftc.robot.Mechanism
import dev.nextftc.robot.drive.tankDrive

class Drivetrain : Mechanism {
    private val lf = NextMotor("fl").apply { zeroPowerBehavior = NextMotor.ZeroPowerBehavior.BRAKE }
    private val lb = NextMotor("bl").apply { zeroPowerBehavior = NextMotor.ZeroPowerBehavior.BRAKE }
    private val rf = NextMotor("fr").apply {
        direction = NextMotor.Direction.REVERSE
        zeroPowerBehavior = NextMotor.ZeroPowerBehavior.BRAKE
    }
    private val rb = NextMotor("br").apply {
        direction = NextMotor.Direction.REVERSE
        zeroPowerBehavior = NextMotor.ZeroPowerBehavior.BRAKE
    }

    val motors = listOf(lf, rf, lb, rb)

    fun tank(gamepad: Gamepad) = tankDrive(motors[0], motors[1], motors[2], motors[3], gamepad)

    val strafeLeft = setDtPowers(-1.0, 1.0, 1.0, -1.0)
    val strafeRight = setDtPowers(1.0, -1.0, -1.0, 1.0)
    val forward = setDtPowers(1.0, 1.0, 1.0, 1.0)
    val backward = setDtPowers(-1.0, -1.0, -1.0, -1.0)

    fun setDtPowers(lfPow: Double, lbPow: Double, rfPow: Double, rbPow: Double) = infinite {
        lf.throttle = lfPow
        rf.throttle = rfPow
        lb.throttle = lbPow
        rb.throttle = rbPow
    }.setEnd { stop() }.requiring(this)

    fun stop() = motors.forEach { it.throttle = 0.0 }

}