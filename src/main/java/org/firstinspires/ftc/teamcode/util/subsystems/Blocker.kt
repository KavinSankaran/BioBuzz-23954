package org.firstinspires.ftc.teamcode.util.subsystems

import com.pedropathing.ivy.commands.Commands.conditional
import dev.nextftc.hardware.actuators.NextMotor
import dev.nextftc.hardware.actuators.NextServo
import dev.nextftc.robot.Mechanism
import dev.nextftc.robot.Telemetry

class Blocker : Mechanism {
    private val blocker = NextServo("blocker").apply {
        direction = NextMotor.Direction.REVERSE
    }

    val open = instant { blocker.position = 0.63 }
    val close = instant { blocker.position = 0.75 }
    val toggle = conditional({ blocker.position == 0.63 }, close, open)

    override fun periodic() {
        Telemetry.log("blocker/position", blocker.position)
        Telemetry.log("blocker/state", if (blocker.position == 0.63) "open" else "closed")
    }
}