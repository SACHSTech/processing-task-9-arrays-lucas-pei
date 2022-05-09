import processing.core.PApplet;

public class Sketch extends PApplet {
	// global variables
  float fltPlayerX = 25;
  float fltPlayerY = 25;
  boolean blnWPressed = false;
  boolean blnAPressed = false;
  boolean blnSPressed = false;
  boolean blnDPressed = false;
  float fltPlayerSpeed = 5;



  // initiate arrays
  float[] fltCircleX = new float[25];
  float[] fltCircleY = new float[25];
  boolean[] ballHideStatus = new boolean[25];


  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// put your size call here
    size(600, 600);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    for (int i = 0; i < fltCircleX.length; i++) {
      fltCircleX[i] = random(width);
    }
    for (int i = 0; i < fltCircleY.length; i++) {
      fltCircleY[i] = random(height);
    }
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    background(0);
    // level 2: draws 25 snowflakes in random locaions, snow falls faster if down arrow is pressed, slower if up arrow is pressed
    for (int i = 0; i < fltCircleY.length; i++) {
      fill(255);
      ellipse(fltCircleX[i], fltCircleY[i], 50, 50);

      if (fltCircleY[i] > height) {
        fltCircleY[i] = 0;
      }
      if (keyCode != UP && keyCode != DOWN) {
        fltCircleY[i] += 3;
      }
      else if (keyCode == UP){
        fltCircleY[i] += 1;
      }
      else if (keyCode == DOWN){
        fltCircleY[i] += 6;
      }
    }
    
    // level 3: blue player circle with 3 lives, and loses a life every time he collides with a snowflake
    fill(0, 0, 255);
    ellipse(fltPlayerX, fltPlayerY, 25, 25);
    if (blnWPressed){
      fltPlayerY -= fltPlayerSpeed;
    }
    if (blnSPressed){
      fltPlayerY += fltPlayerSpeed;
    }
    if (blnAPressed){
      fltPlayerX -= fltPlayerSpeed;
    }
    if (blnDPressed){
      fltPlayerX += fltPlayerSpeed;
    }
    for (int i = 0; i < fltCircleY.length; i++){
      if(dist(fltPlayerX + 12.5f, fltPlayerY + 12.5f, fltCircleX[i], fltCircleY[i]) < 25){
        ellipse(100, 100, 300, 300);
      }
    }
  }

  public void keyPressed() {
    if (key == 'w') {
      blnWPressed = true;
    }
    else if (key == 's') {
      blnSPressed = true;
    }
    else if (key == 'a') {
      blnAPressed = true;
    }
    else if (key == 'd') {
      blnDPressed = true;
    }
  }
  
  public void keyReleased() {
    if (key == 'w') {
      blnWPressed = false;
    }
    else if (key == 's') {
      blnSPressed = false;
    }
    else if (key == 'a') {
      blnAPressed = false;
    }
    else if (key == 'd') {
      blnDPressed = false;
    }
  }
  
}