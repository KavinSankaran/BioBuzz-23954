package org.firstinspires.ftc.teamcode.opModes.auton

import com.pedropathing.api.Paths.line
import com.pedropathing.api.PoseFactory
import com.pedropathing.ivy.Scheduler.schedule
import com.pedropathing.ivy.groups.Groups.sequential
import com.pedropathing.ivy.pedro.PedroCommands.follow
import dev.nextftc.robot.opmode.BulkReadHook
import dev.nextftc.robot.opmode.NextAutonomous
import dev.nextftc.robot.opmode.NextOpMode
import org.firstinspires.ftc.teamcode.util.subsystems.Robot

@NextAutonomous("BlueAuto1Tip")
class BlueAuto1Tip(private val robot: Robot) : NextOpMode(robot, BulkReadHook) {
    private val poseFactory = PoseFactory.degrees()

    private val start = poseFactory.of(84.2343, 132.9226, 270.0)
    private val cycle1 = poseFactory.of(126.4101, 119.3055, 220.0)

    init {
        robot.setStart(start)
    }

    fun cycle1() = line(start, cycle1).linear(start, cycle1)

    override fun start() {
        robot.flywheel.setTargetVelocity(1510.0).schedule()

        schedule(
            sequential(
                follow(robot.pedro.follower, cycle1()),
                robot.shoot
            )
        )
    }
}