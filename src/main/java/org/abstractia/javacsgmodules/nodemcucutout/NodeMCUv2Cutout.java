package org.abstractia.javacsgmodules.nodemcucutout;

import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;

public class NodeMCUv2Cutout
{
    private double xSize = 26.2;
    private double ySize = 48.6;
    private double zSize = 6.0;
    private double holeSize = 2.6;
    private double holeXDist = 21.0;
    private double holeYDist = 43.0;
    private double pinLength = 38.4;
    private double pinWidth = 3.0;
    private double pinHeight = 10.0;

    public Geometry3D getCutout(JavaCSG csg)
    {

        Geometry3D innerFrame = csg.box3D(xSize, ySize, zSize, false);
//        Geometry3D outerFrame = csg.box3D(xSize+2, ySize+2, zSize+2, false);
        Geometry3D hole = csg.cylinder3D(holeSize, zSize+2, 32, false);
        hole = csg.translate3DZ(-1).transform(hole);

        Geometry3D hole1 =csg.translate3D(0.5*holeXDist, 0.5*holeYDist, 0).transform(hole);
        Geometry3D hole2 =csg.translate3D(-0.5*holeXDist, 0.5*holeYDist, 0).transform(hole);
        Geometry3D hole3 =csg.translate3D(0.5*holeXDist, -0.5*holeYDist, 0).transform(hole);
        Geometry3D hole4 =csg.translate3D(-0.5*holeXDist, -0.5*holeYDist, 0).transform(hole);

        Geometry3D diff = csg.difference3D(innerFrame, hole1, hole2, hole3, hole4);

        Geometry3D pin = csg.box3D(pinWidth, pinLength, pinHeight+1, false);
        pin = csg.translate3DZ(-pinHeight).transform(pin);

        return diff;

    }
}
