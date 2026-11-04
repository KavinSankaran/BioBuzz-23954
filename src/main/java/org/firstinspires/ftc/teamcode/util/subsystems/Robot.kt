package org.firstinspires.ftc.teamcode.util.subsystems

import com.pedropathing.ivy.commands.Commands.waitMs
import com.pedropathing.ivy.groups.Groups.sequential
import com.pedropathing.math.Pose
import dev.nextftc.robot.Mechanism
import dev.nextftc.robot.NextRobot
import dev.nextftc.v2.pedro.PedroMechanism
import org.firstinspires.ftc.teamcode.util.Alliance
import org.firstinspires.ftc.teamcode.util.pedro.Constants

class Robot : NextRobot {
    val drivetrain = Drivetrain()
    val intake = Intake()
    val blocker = Blocker()
    val scooper = Scooper()
    val flywheel = Flywheel()
    val hood = Hood()
    //val turret = Turret()

    val pedro = PedroMechanism(Constants::create)

    fun setStart(pose: Pose){
        pedro.follower.setPose(pose)
    }

    val shoot = sequential(
        blocker.open,
        waitMs(200.0),
        intake.transfer,
        waitMs(200.0),
        blocker.close
    )

    /*override fun periodic() {
        pedro.follower.update()

        val currentPose = pedro.follower.pose()
        val hivePose = if (currentPose.x() < 70.75) currentAlliance.hive1Pose
        else currentAlliance.hive2Pose


    }*/

    override val mechanisms: Set<Mechanism>
        get() = setOf(drivetrain, intake, flywheel, blocker, scooper, hood, pedro)
}