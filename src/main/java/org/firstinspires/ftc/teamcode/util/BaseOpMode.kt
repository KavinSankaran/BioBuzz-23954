package org.firstinspires.ftc.teamcode.util

import com.acmerobotics.dashboard.FtcDashboard
import com.pedropathing.ivy.Scheduler.schedule
import dev.nextftc.robot.Telemetry
import dev.nextftc.robot.opmode.BulkReadHook
import dev.nextftc.robot.opmode.NextOpMode
import org.firstinspires.ftc.teamcode.util.subsystems.Robot

abstract class BaseOpMode(private val robot: Robot) : NextOpMode(robot, BulkReadHook) {
    init {
        Telemetry.addBackend(FtcDashboard.getInstance().telemetry)

        schedule(
            robot.scooper.up,
            robot.blocker.close
        )
    }
}