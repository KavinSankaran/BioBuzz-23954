package org.firstinspires.ftc.teamcode.util.subsystems

import com.acmerobotics.dashboard.config.Config
import dev.nextftc.hardware.actuators.NextMotor
import dev.nextftc.hardware.actuators.NextServo
import dev.nextftc.robot.*

@Config
class Turret : Mechanism {
    private val left = NextServo("turretLeft")
    private val right = NextServo("turretRight").apply {
        direction = NextMotor.Direction.REVERSE
    }

    val bluePosition = 0.5
    val redPosition = 0.5

    val center = instant { position = 0.5 }

    val aimBlue = instant { position = bluePosition }
    val aimRed = instant { position = redPosition }

    val increment = instant { position += 0.05 }
    val decrement = instant { position -= 0.05 }

    override fun periodic() {
        left.position = position
        right.position = position

        Telemetry.log("turret/leftPosition", left.position)
        Telemetry.log("turret/rightPosition", right.position)
    }

    companion object {
        @JvmField
        var position = 0.5
    }
}