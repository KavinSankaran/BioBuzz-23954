package org.firstinspires.ftc.teamcode.util

import com.pedropathing.api.PoseFactory
import com.pedropathing.math.Pose

private val blue = PoseFactory.degrees();
private val red = PoseFactory.degrees().mirrorX(70.75);

enum class Alliance(val hive1Pose: Pose, val hive2Pose: Pose) {
    BLUE(blue.of(59.0, 83.0, 0.0), blue.mirrorY(70.75).of(59.0, 83.0, 0.0)),
    RED(red.of(59.0, 83.0, 0.0), red.mirrorY(70.75).of(59.0, 83.0, 0.0))
}