 /*
      Course: CS 45500
      Name: Preston Porter
      Email: porter31@pnw.edu
      Assignment: 4
*/

import renderer.scene.*;
import renderer.models.*;
import renderer.pipeline.*;
import renderer.framebuffer.*;

import java.awt.Color;

/**

*/
public class OfflinePNW
{
   public static void main(String[] args)
   {
      // Create the Scene object that we shall render.
      final Scene scene = new Scene();

      // Create a set of x and y axes.
      Model axes = new Axes2D(-2, +2, -2, +4, 8, 12);
      ModelShading.setColor(axes, Color.red);
      Position axes_p = new Position( axes );
      scene.addPosition( axes_p );
      // Push the axes away from where the camera is.
      axes_p.matrix = Matrix.translate(0, 0, -3);

      // Add the letters to the Scene.
      scene.addPosition(new Position( new P() ),
                        new Position( new N() ),
                        new Position( new W() ));

      // Give the letters random colors.
      for (Position p : scene.positionList)
      {
         ModelShading.setRandomColor(p.model);
      }

      // Create a FrameBuffer to render our scene into.
      int width  = 900;
      int height = 900;
      double pos1 =0;
      double pos2 =0;
      double pos3 =0;
      int count1=0;
      int count2=0;
      int count3=0;
      FrameBuffer fb = new FrameBuffer(width, height);
      //scene.positionList.get(3).matrix.mult( Matrix.translate(4, 2, 0) );
      // Create the animation frames.
      for (int i = 0; i < 360; i++)
      {
         // Push the letters away from the camera.
         scene.positionList.get(1).matrix = Matrix.translate(0, 0, -3); // P
         scene.positionList.get(2).matrix = Matrix.translate(0, 0, -3); // N
         scene.positionList.get(3).matrix = Matrix.translate(1, 0, -3); // W
         //scene.positionList.get(3).matrix = Matrix.translate(2, 0, 0); // W
        
         // do P
         
          

         // Rotate the P
         scene.positionList.get(1).matrix.mult( Matrix.rotateZ(-6*i) ); // notice the difference here
         // Translate the P
         scene.positionList.get(1).matrix.mult( Matrix.translate(-2, 0, 0) );




         // do N
        
         if(i<20){
           pos1 = 0.1*i;
           scene.positionList.get(2).matrix.mult( Matrix.translate(0, 0.1*i, 0) );} 
         if((i>19)&&(i<116)){
           if(i<65){count1++;}
           if(i>64){count1--;}
           pos2 = -0.1*count1 + pos1;
           scene.positionList.get(2).matrix.mult( Matrix.translate(0, -0.1*count1 + pos1, 0) );
           }
           
          if((i>115)&&(i<211)){
             pos3 = -0.1*count2 +pos2;
             scene.positionList.get(2).matrix.mult( Matrix.translate(0,-0.1*count2 + pos2,0));
             if(i<165){count2++;}
             if(i>164){count2--;}
           }
          if(i>210){
            scene.positionList.get(2).matrix.mult( Matrix.translate(0,-0.1*count3 + pos3,0));
            if(i<261){count3++;}
            if((i>260)&&(i<316)){count3--;}
            if(i>316){count3++;}
          }
         
       
         scene.positionList.get(2).matrix.mult( Matrix.rotateY(9*i) );
         scene.positionList.get(2).matrix.mult( Matrix.translate(-0.5, -0.5, 0) );

         // do W
        // 
        
          scene.positionList.get(3).matrix.mult( Matrix.translate(1, 1, 0) );
          scene.positionList.get(3).matrix.mult( Matrix.rotateZ(6*i) );
         scene.positionList.get(3).matrix.mult( Matrix.translate(-1, -1, 0) );



         // Render again.
         fb.clearFB(Color.black);
         Pipeline.render(scene, fb.vp);
         fb.dumpFB2File(String.format("PPM_PNW_Frame%03d.ppm", i));
   }
      }
      }
   

