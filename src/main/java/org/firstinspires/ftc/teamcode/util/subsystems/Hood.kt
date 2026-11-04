package org.firstinspires.ftc.teamcode.util.subsystems

import com.acmerobotics.dashboard.config.Config
import dev.nextftc.control.util.InterpolatingMap
import dev.nextftc.hardware.actuators.NextMotor
import dev.nextftc.hardware.actuators.NextServo
import dev.nextftc.robot.*

@Config
class Hood : Mechanism {
    private val hoodLeft = NextServo("hoodLeft")
    private val hoodRight = NextServo("hoodRight")

    private var targetPosition = 0.63

    val decrement = instant { targetPosition += 0.01 }
    val increment = instant { targetPosition -= 0.01 }

    override fun periodic() {
        hoodLeft.position = targetPosition.coerceIn(0.5..0.8)
        hoodRight.position = targetPosition.coerceIn(0.5..0.8)

        Telemetry.log("hood/position", targetPosition.coerceIn(0.5..0.8))
    }
}