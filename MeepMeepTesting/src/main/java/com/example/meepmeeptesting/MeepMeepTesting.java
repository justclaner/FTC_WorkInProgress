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
        //region starting positions
        Pose2d redLeft = new Pose2d(12,-63,Math.toRadians(-90));
        Pose2d redRight = new Pose2d(-36,-63,Math.toRadians(-90));
        Pose2d blueLeft = new Pose2d(-36,63,Math.toRadians(90));
        Pose2d blueRight = new Pose2d(12,63,Math.toRadians(90));
        //endregion


//commit
        RoadRunnerBotEntity redLeftBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(48, 48, Math.toRadians(180), Math.toRadians(180), 14.2)
                .followTrajectorySequence(drive ->


                        drive.trajectorySequenceBuilder(redLeft)

                                //left
//                                .lineToLinearHeading(new Pose2d(12,-34,0)
////                                        ,SampleMecanumDrive.getVelocityConstraint(12, Math.toRadians(180), 14.2),
////                                        SampleMecanumDrive.getAccelerationConstraint(12)
//                                )
//                                .back(3).waitSeconds(0.2) //purple pixel
//                                .splineTo(new Vector2d(50,-28),0) //yellow pixel
//
//                                .setReversed(true).waitSeconds(0.2)

//                                .splineToLinearHeading(new Pose2d(12,-58,Math.toRadians(180)),Math.toRadians(270)).setReversed(false)
//                                .lineTo(new Vector2d(-20,-58))
//                                .splineTo(new Vector2d(-56,-35),Math.toRadians(180)).waitSeconds(0.2).setReversed(true)
//                                .splineTo(new Vector2d(-18,-11),0)
//                                .lineToConstantHeading(new Vector2d(18,-11))
//                                .splineTo(new Vector2d(40,-40),Math.toRadians(180))
//                                .lineTo(new Vector2d(50,-40))




                        //middle
//
                                .lineTo(new Vector2d(12,-34)).waitSeconds(0.3) //purple pixel

                                .splineToLinearHeading(new Pose2d(48,-35,0),Math.toRadians(90)) //yellow pixel
//
//                                .setReversed(true)
//                                .lineToLinearHeading(new Pose2d(24,-36,Math.toRadians(180)))
//
//                                .setReversed(false)
//                                .lineTo(new Vector2d(-58,-36))
//
//                                .lineTo(new Vector2d(24,-36))
//                                .lineToLinearHeading(new Pose2d(48,-40,0))
//                                .lineToLinearHeading(new Pose2d(24,-36,Math.toRadians(180)))
//
//                                .lineTo(new Vector2d(-58,-36))
//                                .lineTo(new Vector2d(24,-36))
//                                .lineToLinearHeading(new Pose2d(48,-40,0))

                        //right

//                                .lineTo(new Vector2d(22,-40)) //purple pixel
//                                .waitSeconds(0.1)
//                                .splineTo(new Vector2d(49,-41),Math.toRadians(0)) //yellow pixel

                                .build()
                );


        RoadRunnerBotEntity  redRightBot = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(48, 48, Math.toRadians(180), Math.toRadians(180), 14.2)
                .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(redRight)
                                    //left
//                                    .lineToLinearHeading(new Pose2d(-37,-34,0)) //purple
//
//                                    .forward(2)
//
//                                    .lineTo(new Vector2d(-35,-12))
//
//
//
//                                    .lineTo(new Vector2d(12,-12))
//                                    .splineTo(new Vector2d(49,-30),0) //yellow pixel



                                    //middle
//                                .lineTo(new Vector2d(-36,-33)) //purple
//                                .waitSeconds(0.1)
//
//                                .forward(2)
//
//                                .strafeLeft(48)
//
//                                .lineToLinearHeading(new Pose2d(49,-35,0)) //yellow pixel

                                    //right
                                .lineToLinearHeading(new Pose2d(-36,-32,Math.toRadians(180)))

                                .back(3) //purple
                                .waitSeconds(0.1)

                                    .forward(2)
                                    .splineTo(new Vector2d(-42,-20),Math.toRadians(90))
                                .splineTo(new Vector2d(-28,-12),0)

                                .lineTo(new Vector2d(12,-12))
                                .splineTo(new Vector2d(49,-42),0) //yellow pixel
                                    .build()
                                );

        RoadRunnerBotEntity  blueLeftBot = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(48, 48, Math.toRadians(180), Math.toRadians(180), 14.2)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(blueLeft)
                                //left
                                .lineToLinearHeading(new Pose2d(-36,32,Math.toRadians(180)))

                                .back(3) //purple
                                .waitSeconds(0.1)

                                .forward(2)
                                .splineTo(new Vector2d(-42,20),Math.toRadians(-90))
                                .splineTo(new Vector2d(-28,12),0)

                                .lineTo(new Vector2d(12,12))
                                .splineTo(new Vector2d(49,42),0) //yellow pixel



                                //middle
//                                .lineTo(new Vector2d(-36,33)) //purple
//                                .waitSeconds(0.1)
//
//                                .forward(2)
//
//                                .strafeRight(48)
//
//                                .lineToLinearHeading(new Pose2d(49,35,0)) //yellow pixel

                                //right
//                                .lineToLinearHeading(new Pose2d(-37,34,0)) //purple
//
//                                .forward(2)
//
//                                .lineTo(new Vector2d(-35,12))
//
//
//
//                                .lineTo(new Vector2d(12,12))
//                                .splineTo(new Vector2d(49,30),0) //yellow pixel
                                .build()
                );

        RoadRunnerBotEntity  blueRightBot = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(48, 48, Math.toRadians(180), Math.toRadians(180), 14.2)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(blueRight)

                                //left

//                                .lineTo(new Vector2d(23,40)) //purple pixel
//                                .waitSeconds(0.1)
//                                .forward(5)
//                                .splineTo(new Vector2d(49,42),0) //yellow pixel


                                //middle
//                                .lineTo(new Vector2d(12,33)) //purple pixel
//                                .waitSeconds(0.1)
//                                .splineTo(new Vector2d(49,35),0) //yellow pixel


                                //right
                                .lineToLinearHeading(new Pose2d(12,32,Math.toRadians(0)))
                                .back(2)  //purple pixel
                                .waitSeconds(0.1)
                                .splineTo(new Vector2d(49,28),0) //yellow pixel

                                .build()
                );


        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                //.addEntity(redLeftBot)
               // .addEntity(redRightBot)
                //.addEntity(blueLeftBot)
                .addEntity(blueRightBot)
                .start();
    }
    public static double r(double degree) {
        return Math.toRadians(degree);
    }
}