package org.firstinspires.ftc.teamcode.opModes.teleop.tests

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.dashboard.config.Config
import dev.nextftc.robot.Telemetry
import dev.nextftc.robot.opmode.BulkReadHook
import dev.nextftc.robot.opmode.NextOpMode
import dev.nextftc.robot.opmode.NextTeleop
import org.firstinspires.ftc.teamcode.util.subsystems.Robot

@Config
@NextTeleop("TurretTuning", "tuning")
class TurretTuning(private val robot: Robot) : NextOpMode(robot, BulkReadHook) {
    init {
        Telemetry.addBackend(FtcDashboard.getInstance().telemetry)
    }
}