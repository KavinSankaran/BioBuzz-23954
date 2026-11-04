package org.firstinspires.ftc.teamcode.opModes.teleop.tests

import com.acmerobotics.dashboard.config.Config
import dev.nextftc.robot.opmode.NextOpMode
import dev.nextftc.robot.opmode.NextUtility
import dev.nextftc.robot.triggers.CommandGamepad
import org.firstinspires.ftc.teamcode.util.subsystems.Robot

@Config
@NextUtility("HoodTest", "test")
class HoodTest(private val robot: Robot) : NextOpMode(robot) {
    override fun periodic() {
        val gp1 = CommandGamepad(gamepad1)

        gp1.a.onTrue(robot.hood.increment)
    }
}