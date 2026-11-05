package org.firstinspires.ftc.teamcode.opModes.teleop.tests

import com.acmerobotics.dashboard.FtcDashboard
import com.pedropathing.ivy.Scheduler.schedule
import dev.nextftc.robot.Telemetry
import dev.nextftc.robot.opmode.BulkReadHook
import dev.nextftc.robot.opmode.NextOpMode
import dev.nextftc.robot.opmode.NextTeleop
import dev.nextftc.robot.triggers.CommandGamepad
import org.firstinspires.ftc.teamcode.util.subsystems.Robot

@NextTeleop("TestTeleop", "test")
class TestTeleop(private val robot: Robot) : NextOpMode(robot, BulkReadHook) {
    init {
        Telemetry.addBackend(FtcDashboard.getInstance().telemetry)

        schedule(
            robot.scooper.up,
            robot.blocker.open
        )
    }

    override fun start() {
        val gp1 = CommandGamepad(gamepad1)
        val gp2 = CommandGamepad(gamepad2)

        schedule(robot.drivetrain.tank(gamepad1))

        gp1.rightBumper.whileTrue(robot.drivetrain.strafeRight)
        gp1.leftBumper.whileTrue(robot.drivetrain.strafeLeft)
        gp1.y.whileTrue(robot.drivetrain.forward)
        gp1.a.whileTrue(robot.drivetrain.backward)

        gp2.a.onTrue(robot.intake.transfer)

        gp2.rightStickY.isBetween(-0.01..0.01)
            .whileFalse(robot.intake.manual { -gp2.rightStickY.value })
            .whileTrue(robot.intake.off)

        gp2.b.onTrue(robot.scooper.toggle)

        gp2.leftBumper.onTrue(robot.flywheel.setTargetVelocity(1510.0))
        gp2.rightBumper.onTrue(robot.flywheel.setTargetVelocity(0.0))

        gp2.rightTrigger.isOver(0.01).onTrue(robot.blocker.toggle)

        gp2.dpadDown.onTrue(robot.hood.decrement)
        gp2.dpadUp.onTrue(robot.hood.increment)
    }
}