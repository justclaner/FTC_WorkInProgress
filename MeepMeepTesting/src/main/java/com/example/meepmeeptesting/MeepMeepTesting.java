package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.constraints.AngularVelocityConstraint;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.SampleMecanumDrive;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;


public class MeepMeepTesting {
    double angVel = r(180);
    double angAccel = r(180);
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);
        Pose2d redLeft = new Pose2d(12,-63,Math.toRadians(-90));
        Pose2d redRight = new Pose2d(-36,-63,Math.toRadians(-90));
        Pose2d blueLeft = new Pose2d(-36,63,Math.toRadians(90));
        Pose2d blueRight = new Pose2d(12,63,Math.toRadians(90));


//commit
        RoadRunnerBotEntity redLeftBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(48, 48, Math.toRadians(180), Math.toRadians(180), 14.2)
                .followTrajectorySequence(drive ->

                        //left
                        drive.trajectorySequenceBuilder(redLeft).setReversed(true)
                                .lineToLinearHeading(new Pose2d(12,-34,0)
//                                        ,SampleMecanumDrive.getVelocityConstraint(12, Math.toRadians(180), 14.2),
//                                        SampleMecanumDrive.getAccelerationConstraint(12)
                                )
                                .back(3)                      .setReversed(false)
                                .splineTo(new Vector2d(56,-56),0)
                                .lineToLinearHeading(redLeft).setReversed(true)
                                .lineTo(new Vector2d(12,-34)).setReversed(false)
                                .splineTo(new Vector2d(56,-56),Math.toRadians(0))


                        //middle
//                        drive.trajectorySequenceBuilder(redLeft).setReversed(true)Math.toRadians(180), Math.toRadians(180)
//                                .lineTo(new Vector2d(12,-34)).setReversed(false)
//                                .splineTo(new Vector2d(56,-56),Math.toRadians(0))

                        //right
//                        drive.trajectorySequenceBuilder(redLeft).setReversed(true)
//                                .lineTo(new Vector2d(12,-34)).setReversed(false)
//                                .splineTo(new Vector2d(56,-56),Math.toRadians(0))

                                .build()
                );


        RoadRunnerBotEntity  redRightBot = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(48, 48, Math.toRadians(180), Math.toRadians(180), 14.2)
                .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(redRight)
                                    .lineTo(new Vector2d())
                                    .build()
                                );

        RoadRunnerBotEntity  blueLeftBot = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(48, 48, Math.toRadians(180), Math.toRadians(180), 14.2)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(blueLeft)
                                .lineTo(new Vector2d())
                                .build()
                );

        RoadRunnerBotEntity  blueRightBot = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(48, 48, Math.toRadians(180), Math.toRadians(180), 14.2)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(blueRight)
                                .lineTo(new Vector2d())
                                .build()
                );


        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(redLeftBot)
//                .addEntity(redRightBot)
//                .addEntity(blueLeftBot)
//                .addEntity(blueRightBot)
                .start();
    }
    public static double r(double degree) {
        return Math.toRadians(degree);
    }
}