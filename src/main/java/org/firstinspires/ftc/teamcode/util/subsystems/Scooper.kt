package org.firstinspires.ftc.teamcode.util.subsystems

import com.pedropathing.ivy.commands.Commands.conditional
import dev.nextftc.hardware.actuators.NextServo
import dev.nextftc.robot.Mechanism
import dev.nextftc.robot.Telemetry


class Scooper : Mechanism {
    val scooper = NextServo("scooper")

    val up = instant { scooper.position = 0.5 }
    val down = instant { scooper.position = 0.16 }
    val toggle = conditional({ scooper.position == 0.16} , up, down)

    override fun periodic() {
        Telemetry.log("scooper/position", scooper.position)
    }
}