package org.firstinspires.ftc.teamcode.util.subsystems

import com.acmerobotics.dashboard.config.Config
import dev.nextftc.hardware.actuators.*
import dev.nextftc.robot.*
import dev.nextftc.units.radiansPerSecond
import kotlin.math.abs

@Config
class Flywheel : Mechanism {
    private val flywheel1 = NextMotor("flywheel1").apply {
        direction = NextMotor.Direction.FORWARD
        velocityConstants.kP = kp
        velocityConstants.kV = kv
        velocityConstants.kS = ks
    }
    private val flywheel2 = NextMotor("flywheel2").apply {
        direction = NextMotor.Direction.REVERSE
        velocityConstants.kP = kp
        velocityConstants.kV = kv
        velocityConstants.kS = ks
    }

    var currentVelocity = 0.0
        private set

    fun setTargetVelocity(target: Double) = instant { targetVelocity = target }
    fun isAtTargetVelocity(): Boolean = targetVelocity > 0 && abs(targetVelocity - currentVelocity) < velocityTolerance

    override fun periodic() {
        currentVelocity = (flywheel1.encoderVelocity.magnitude + flywheel2.encoderVelocity.magnitude) / 2.0
        val difference = targetVelocity - currentVelocity

        flywheel1.velocityConstants.kP = kp
        flywheel1.velocityConstants.kV = kv
        flywheel1.velocityConstants.kS = ks

        flywheel2.velocityConstants.kP = kp
        flywheel2.velocityConstants.kV = kv
        flywheel2.velocityConstants.kS = ks

        if (targetVelocity == 0.0) {
            flywheel1.throttle = 0.0
            flywheel2.throttle = 0.0
        } else if (difference < -bangBangThreshold) {
            flywheel1.throttle = 0.0
            flywheel2.throttle = 0.0
        } else if (difference > bangBangThreshold) {
            flywheel1.throttle = 1.0
            flywheel2.throttle = 1.0
        } else {
            flywheel1.setVelocitySetpoint(targetVelocity.radiansPerSecond)
            flywheel2.setVelocitySetpoint(targetVelocity.radiansPerSecond)
        }

        Telemetry.log("shooter/velocity", currentVelocity)
        Telemetry.log("shooter/target velocity", targetVelocity)
    }

    companion object {
        @JvmField
        var kp = 0.003
        @JvmField
        var kv = 0.00042
        @JvmField
        var ks = 0.00008
        @JvmField
        var targetVelocity = 0.0
        @JvmField
        var velocityTolerance = 40.0
        @JvmField
        var bangBangThreshold = 150.0
    }
}