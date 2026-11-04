package org.firstinspires.ftc.teamcode.util.subsystems

import com.pedropathing.ivy.commands.Commands.waitMs
import com.pedropathing.ivy.groups.Groups.sequential
import dev.nextftc.hardware.RobotController
import dev.nextftc.hardware.actuators.NextMotor
import dev.nextftc.robot.Mechanism
import kotlin.math.sign

class Intake : Mechanism {
    private val intake = NextMotor("intake").apply {
        zeroPowerBehavior = NextMotor.ZeroPowerBehavior.BRAKE
        direction = NextMotor.Direction.REVERSE
    }

    val on = instant { intake.throttle = 1.0 }
    val off = instant { intake.throttle = 0.0 }
    val reverse = instant { intake.throttle = -1.0 }
    val transfer = sequential(on, waitMs(750.0), off)

    fun manual(throttle: () -> Double) = infinite { intake.throttle = throttle().sign }
}