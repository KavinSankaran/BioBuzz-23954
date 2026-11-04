package org.firstinspires.ftc.teamcode.util.subsystems

import com.acmerobotics.dashboard.config.Config
import com.pedropathing.follower.Follower
import com.pedropathing.math.Pose
import dev.nextftc.hardware.actuators.NextServo
import dev.nextftc.robot.*
import kotlin.math.atan2

@Config
class Turret : Mechanism {
    private val turretLeft = NextServo("turretLeft")
    private val turretRight = NextServo("turretRight")

    val gearRatio = 145.0 / 32.0

    companion object {
        @JvmField
        var targetAngle = 0.0

        @JvmField
        var minAngle = -90.0

        @JvmField
        var maxAngle = 90.0

    }


    private fun angleToPosition(angle: Double): Double {
        val servoAngle = angle * gearRatio

        return (0.5 + servoAngle / 350.0).coerceIn(0.0, 1.0)
    }

    fun setAngle(angle: Double) {
        targetAngle = angle.coerceIn(minAngle..maxAngle)
    }

    fun face(currentPose: Pose, targetPose: Pose) {
        val targetHeading = atan2(
            targetPose.y() - currentPose.y(),
            targetPose.x() - currentPose.x()
        )

        setAngle(
            Math.toDegrees(targetHeading) - Math.toDegrees(currentPose.heading())
        )
    }

    override fun periodic() {
        val position = angleToPosition(targetAngle)

        turretLeft.position = position
        turretRight.position = 1.0 - position
    }
}