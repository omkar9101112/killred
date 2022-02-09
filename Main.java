import com.raylib.Jaylib;
import com.raylib.Raylib;
import static com.raylib.Raylib.*;
import static com.raylib.Jaylib.*;


public class Main{
    public static void main(String[] args){
        InitWindow(2000,1000,"The Fighter");

        Texture bg = LoadTexture("resources/background.png");
        Texture heart = LoadTexture("resources/heart.png");
        player player = new player();
        bullets bullet = new bullets();
        //bullets bu = new bullets();
        bullets ebullet = new bullets();
        ebullet.x = 1600;
        Raylib.Color co = RED;
        Raylib.Color t = BLACK;
        //bu.x=30;
        menu m = new menu();
        Raylib.Color c = BLUE;
        boolean shoot = false;
        enemy enemy = new enemy();
        healthbar h = new healthbar();
        int x = -500;
        boolean endm = false;
        Raylib.Color BC = BLUE;
        Raylib.Color iBC = BLUE;
        Raylib.Color jBC = BLUE;
        boolean mainmenu = true;
        enemyhealth eh = new enemyhealth();
        Raylib.Color mo = RED;
        int score = 0;
        boolean jump = false;
        //Jaylib.Rectangle p = new Jaylib.Rectangle();
        while(!WindowShouldClose()){
            //player jump
            player.y+=2.0f;
            bullet.y+=2.0f;
            co=BLUE;


            //enemy shooting ability;
            if(mainmenu==false){
                ebullet.x-=10.0f;
            }

            if(player.y > 530){
                player.y-=2.0f;
                bullet.y-=2.0f;
            }



            if(IsKeyPressed(KEY_SPACE)){
                /*player.y-=5.0f;
                bullet.y-=5.0f;*/
                jump = true;
            }

            if(jump==true){
                player.y-=5.0f;
                bullet.y-=5.0f;
            }

            if(player.y<200){
                jump=false;
                player.y+=5.0f;
                bullet.y+=5.0f;
            }

            if(bullet.x <= 70){
                bullet.y+=5.0f;
            }

            //player shooting ability
            if(IsKeyPressed(KEY_ENTER)){
                shoot = true;
                c=RED;
            }

            if(shoot == true){
                bullet.x += 10.0f;
                //    bu.x += 10.0f;
            }

            if(bullet.x > 2000){
                bullet.x = 100;
                //      bu.x=30;
                shoot = false;
                c=BLUE;
            }

            if(ebullet.x<0){
                ebullet.x=1550;
                co=RED;
            }
            if(player.x<ebullet.x+ebullet.width&&
                    player.x+player.width>ebullet.x&&
                    player.y<ebullet.y+70&&
                    player.y+70>ebullet.y){
                h.width -= 0.1;
            }
            if(enemy.x<bullet.x+bullet.width&&
                    enemy.x+enemy.width>bullet.x&&
                    enemy.y<bullet.y+70&&
                    enemy.y+70>bullet.y){
                eh.width -= 5;
            }

            if(eh.width<15){
                eh.width = 150;
                h.width += 20;
                mo = SKYBLUE;
                score +=10;
                if(eh.width>15){
                    mo=RED;
                }
            }



            if(score == 50){
                h.width = 100;
            }


          //  System.out.println(GetMouseX());
            if(GetMouseX()>830 && GetMouseX()<950 && GetMouseY()>300&&GetMouseY()<350){
                BC=MAROON;
                if(IsGestureDetected(GESTURE_TAP)){
                    mainmenu = false;
                }

            }

            else{
                BC=BLUE;
            }

            if(GetMouseX()>830 && GetMouseX()<950 && GetMouseY()>500&&GetMouseY()<550){
                iBC = RED;
                if(IsGestureDetected(GESTURE_TAP)){
                    CloseWindow();
                }
            }
            else{
                iBC=BLUE;
            }

            if(GetMouseX()>830 && GetMouseX()<950 && GetMouseY()>500&&GetMouseY()<550){
                iBC = RED;
                if(IsGestureDetected(GESTURE_TAP)){
                    CloseWindow();
                }
            }

           /* if(GetMouseX()>830 && GetMouseX()<950 && GetMouseY()>700&&GetMouseY()<750){
                jBC = RED;
                if(IsGestureDetected(GESTURE_TAP)){
                    CloseWindow();
                }
            }

            else{
                jBC=BLUE;
            }*/

            if(h.width  <10 ){
                //ebullet.x +=10.0f;
                // bullet.x -= 10.0f;
                x=500;
                endm = true;
                shoot=false;
                if(IsGestureDetected(GESTURE_TAP)){
                    h.width = 100;
                    endm=false;
                    score=0;
                    //bullet.x = 100;
                    //x=-500;
                }



            }



            BeginDrawing();
            ClearBackground(WHITE);
            DrawTexture(heart,20,40,WHITE);
            DrawTexture(bg, 0, 0, WHITE);
            // DrawTexture(heart,20,40,WHITE);
            DrawRectangle(50, 600, 200, 600, RED);
            DrawCircle(player.x,player.y, 70,BLUE);
            DrawCircle(enemy.x,enemy.y,70,mo);
            DrawRectangle(bullet.x,bullet.y, bullet.width, bullet.height,c);
            //  DrawRectangle(bu.x, bullet.y, bullet.width, bullet.height,c);
            DrawRectangle(ebullet.x,ebullet.y, ebullet.width, ebullet.height,co);
            DrawRectangle(1500, 600, 200, 600, BLUE);
            DrawRectangle(50,40,150,30,RED);
            DrawRectangle(1500,200,150,30,RED);
            DrawRectangle(h.x,h.y,h.width,h.height,GREEN);
            DrawRectangle(eh.x,eh.y,eh.width,eh.height,GREEN);
            //    DrawRectangle(player.x,player.y, player.width, player.height,GREEN);
            //DrawText("Click To Reset",x,500,40,t);
            DrawText(""+score,500,50,20,BLACK);
            //buttons
            if(mainmenu ==true) {

                DrawRectangle(m.x, m.y, m.width, m.height, SKYBLUE);
                DrawText("Kill Red", 800, 50, 100, WHITE);
                //play goal and quit button
                DrawRectangle(830, 300, 150, 50, BC);
                DrawText("play", 880, 310, 20, BLACK);
                DrawRectangle(830, 500, 150, 50, iBC);
                DrawText("QUIT", 880, 510, 20, BLACK);
                //DrawRectangle(830, 700, 150, 50, jBC);
               // DrawText("Goal", 880, 710, 20, BLACK);
            }
            if(endm==true){
                DrawRectangle(50,50,1800,900,SKYBLUE);
                DrawText("y:"+score,700,70,100,BLACK);
                DrawText("ClickTo Reset",700,500,100,BLACK);
            }




            EndDrawing();
        }
    }
}
