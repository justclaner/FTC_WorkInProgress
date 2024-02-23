package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);
        Pose2d redLeft = new Pose2d(12,-60,Math.toRadians(-90));

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(48, 48, Math.toRadians(180), Math.toRadians(180), 14.44)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(redLeft).setReversed(true)
                                .lineToLinearHeading(new Pose2d(12,-10,0)).setReversed(false)
                                .splineTo(new Vector2d(30,40),0)
//                                .lineTo(new Vector2d(12,-33)).setReversed(false)
//                                .splineTo(new Vector2d(35,-60),Math.toRadians(0))
//                                .lineTo(new Vector2d(60,-60))
                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}