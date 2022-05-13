import processing.core.PApplet;

public class Sketch extends PApplet {
	// global variables
  boolean blnWPressed = false;
  boolean blnAPressed = false;
  boolean blnSPressed = false;
  boolean blnDPressed = false;
  boolean blnDownPressed = false;
  boolean blnUpPressed = false;
  int intPlayerX = 25;
  int intPlayerY = 500;
  int intPlayerSpeed = 3;
  int intCircleSpeed = 3;
  int intLives = 3;



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
    // assign random values to the arrays to create coordinates for use in the draw method
    for (int i = 0; i < fltCircleX.length; i++) {
      fltCircleX[i] = random(width);
    }
    for (int i = 0; i < fltCircleY.length; i++) {
      fltCircleY[i] = random(height - 100);
    }
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    background(0);
    // draws 25 snowflakes travelling down the screen in random locations using fltCircleX and fltCircleY arrays
    for (int i = 0; i < fltCircleY.length; i++) {
      // the snowflakes will not draw if ballHideStatus[i] is true
      if (!ballHideStatus[i]){
        fill(255);
        ellipse(fltCircleX[i], fltCircleY[i], 50, 50);
        fltCircleY[i] += intCircleSpeed;
        
        // the snowflakes go back to the top of the screen once they reach the bottom
        if (fltCircleY[i] > height) {
          fltCircleY[i] = 0;
        }
      }      
    }

    // controls the speed of the snowflakes using arrow keys: up slows, down speeds up
    if (blnUpPressed && intCircleSpeed > 1) {
      intCircleSpeed -=1;
    } 
    else if (blnDownPressed && intCircleSpeed < 25){
      intCircleSpeed +=1;
    }
    
    // draws a blue player circle to the screen, who is controlled using the wasd keys. 
    fill(0, 0, 255);
    ellipse(intPlayerX, intPlayerY, 25, 25);
    if (blnWPressed){
      intPlayerY -= intPlayerSpeed;
    }
    if (blnSPressed){
      intPlayerY += intPlayerSpeed;
    }
    if (blnAPressed){
      intPlayerX -= intPlayerSpeed;
    }
    if (blnDPressed){
      intPlayerX += intPlayerSpeed;
    }

    // gives the player three lives, and a life is lost every time the player circle touches a snowflake
    for (int i = 0; i < fltCircleY.length; i++){
      if(dist(intPlayerX, intPlayerY, fltCircleX[i], fltCircleY[i]) < 37.5){
        fltCircleY[i] = 0;
        intLives -= 1;
      }
    }

    // draws three squares in the top right of the screen to show the user how many lives they have
    for (int i = 0; i < intLives; i++){
      fill(255, 0, 0);
      rect(550 - (i * 50), 30, 30, 30);
    }

    // if the user has no more lives, a white rectange is drawn to cover the entire screen
    if (intLives <= 0){
      fill(255);
      rect(0, 0, width, height);
    }

    // if the mouse is pressed within a snowflake, ballHideStatus[i] is changed to true
    for(int i = 0; i < fltCircleY.length; i++){
      if(dist(mouseX, mouseY, (fltCircleX[i]), fltCircleY[i]) <= 25){
        if(mousePressed){
          ballHideStatus[i] = true;
        }
      }
    }
  }

  /**
   * Assigns true values to boolean variables when various keys are pressed to be used in the draw method
   */
  public void keyPressed() {
    if (keyCode == DOWN) {
      blnDownPressed = true;
    }
    else if (keyCode == UP) {
      blnUpPressed = true;
    }
    else if (key == 'w') {
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
  
  /**
   * Assigns false values to boolean variables when various keys are released to be used in the draw method
   */
  public void keyReleased() {
    if (keyCode == DOWN) {
      blnDownPressed = false;
    }
    else if (keyCode == UP) {
      blnUpPressed = false;
    }
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