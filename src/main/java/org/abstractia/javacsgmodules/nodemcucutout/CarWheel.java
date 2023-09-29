package org.abstractia.javacsgmodules.nodemcucutout;

import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;
import org.abstractica.javacsg.JavaCSGFactory;

public class CarWheel {

    public static void main(String[] args)
    {
        //Torus parametre
        JavaCSG csg = JavaCSGFactory.createDefault();
        Geometry3D torus = csg.torusSegment3D
                (
                        10,
                        40,
                        csg.degrees(360),
                        csg.degrees(360),
                        16,
                        64,
                        true
                );

        //Cylinder parametre
        Geometry3D rubber = csg.cylinder3D(41, 6, 64, true);
        Geometry3D rubberCut = csg.cylinder3D(36, 7, 64, true);
        rubber = csg.difference3D(rubber, rubberCut);

        //Spool and connect parametre
        Geometry3D spool = csg.cylinder3D(15, 3, 32, true);
        Geometry3D connect = csg.cylinder3D(5, 4, 32, true);
        spool = csg.difference3D(spool, connect);

        //Basic tread parametre
        Geometry3D tread1 = csg.box3D(40, 3,3, true);
        Geometry3D tread2 = csg.box3D(3, 40,3, true);

        //4-legged tread parametre
//		Geometry3D treads = csg.union3D(tread1, tread2);

        //8-legged tread parametre
        Geometry3D treads = csg.union3D(tread1, tread2);
        Geometry3D treads45 = csg.union3D(tread1, tread2);
        treads45 = csg.rotate3DZ(csg.degrees(45)).transform(treads45);
        treads = csg.union3D(treads, treads45);

        //12-legged tread parametre
//		Geometry3D treads = csg.union3D(tread1, tread2);
//		Geometry3D treads30 = csg.union3D(tread1, tread2);
//		Geometry3D treads60 = csg.union3D(tread1, tread2);
//		treads30 = csg.rotate3DZ(csg.degrees(30)).transform(treads30);
//		treads60 = csg.rotate3DZ(csg.degrees(60)).transform(treads60);
//		treads = csg.union3D(treads, treads30, treads60);

        //Parts put together
        Geometry3D wheel = csg.union3D(treads, spool);
        wheel = csg.difference3D(wheel, connect);

        //Wheel and rubber put together
        wheel = csg.union3D(wheel, rubber);
        csg.view(wheel);

        //Wheel and torus put together
//		torus = csg.union3D(torus, wheel);
//		csg.view(torus);
    }
}
