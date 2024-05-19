package com.example.tritonhacks24;
import static com.example.tritonhacks24.Adoption.points;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Habitat extends AppCompatActivity {

    //public int points = Adoption.points;
    Dialog intro, question;
    Button introClose, button1, button2;
    ImageView turtle, net, trash, bubble;
    private final Random random = new Random();
    private final Handler handler = new Handler();
    private final int delayMillis = 10000;
    RelativeLayout relativeLayout;
    private ImageView ogTurtle;
    Rect trashRect;
    private boolean isAnimationInProgress = false;
    TextView points;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habitat);

        ImageView homeBtn = findViewById(R.id.homeBtn);

        points = findViewById(R.id.pointsTextView);
        points.setText("Points: "+ Adoption.points);
        relativeLayout = findViewById(R.id.RelativeLayout);
        turtle = findViewById(R.id.turtle);
        turtle.setTag("turtle");
        ogTurtle = turtle;
        turtleList.add(ogTurtle);

        net = findViewById(R.id.net);
        net.setY(-2000);

        trash = findViewById(R.id.trash);
        trash.setY(700);
        trash.setX(2000);

        bubble = findViewById(R.id.bubble);
        bubble.setVisibility(View.GONE);
        question = new Dialog(Habitat.this);
        question.setContentView(R.layout.question);
        question.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        question.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_bg));
        question.setCancelable(false);
        question.dismiss();
        button1 = question.findViewById(R.id.button1);
        button2 = question.findViewById(R.id.button2);



        intro = new Dialog(Habitat.this);
        intro.setContentView(R.layout.dialog_box);
        intro.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        intro.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_bg));
        intro.setCancelable(false);

        introClose = intro.findViewById(R.id.button);
        introClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(MainActivity.this, Habitat2.class);
                //startActivity(intent);
                intro.dismiss();
                moveTurtle(turtle);
            }
        });
        intro.show();
        startEvent();
        startCreatingTurtles();
        startCollisionDetection();
        startBubbleAnimation();

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open other view; create intent
                Intent aboutIntent = new Intent(Habitat.this, Home2.class);
                // start activity
                startActivity(aboutIntent);
            }
        });
        turtle.setOnTouchListener(new View.OnTouchListener() {
            float dx, dy;
            float previousX, previousY;
            int touchSlop = ViewConfiguration.get(getApplicationContext()).getScaledTouchSlop();

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                turtle.clearAnimation();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        previousX = event.getRawX();
                        previousY = event.getRawY();
                        dx = previousX - turtle.getX();
                        dy = previousY - turtle.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float newX = event.getRawX() - dx;
                        float newY = event.getRawY() - dy;

                        float leftBoundary = -touchSlop;
                        float topBoundary = -touchSlop;
                        float rightBoundary = getScreenWidth() - turtle.getWidth() + touchSlop;
                        float bottomBoundary = getScreenHeight() - turtle.getHeight() + touchSlop;

                        newX = Math.max(leftBoundary, Math.min(newX, rightBoundary));
                        newY = Math.max(topBoundary, Math.min(newY, bottomBoundary));

                        turtle.setX(newX);
                        turtle.setY(newY);
                        break;
                    case MotionEvent.ACTION_UP:
                        moveTurtle(turtle);
                        break;
                }
                return true;
            }
        });
    }


    private void startBubbleAnimation() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showBubble();
            }
        }, 5000);
    }

    private void showBubble() {
        bubble.setVisibility(View.VISIBLE);

        int randomX = random.nextInt(getScreenWidth() - bubble.getWidth());
        int randomY = random.nextInt(getScreenHeight() - bubble.getHeight());
        bubble.setX(randomX);
        bubble.setY(randomY);


        bubble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bubble.getVisibility() == View.VISIBLE) {
                    int random = (int)(Math.random()*2)+1;
                    if(random==1){
                        question.show();

                        button1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                button1.setBackgroundColor(Color.parseColor("#AAFF00"));
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        newTurtle();
                                        question.dismiss();
                                        button1.setBackgroundColor(Color.parseColor("#50C878"));
                                    }
                                }, 500);
                            }
                        });


                        button2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                button2.setBackgroundColor(Color.parseColor("#EE4B2B"));
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        question.dismiss();
                                        button2.setBackgroundColor(Color.parseColor("#50C878"));
                                    }
                                }, 500);
                            }
                        });

                    }
                    else {
                        TextView questionText = question.findViewById(R.id.Text);
                        questionText.setText("Hawksbill Turtle population is >30,000");
                        button1.setText("True");
                        button2.setText("False");
                        question.show();

                        //RIGHT
                        button2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                button2.setBackgroundColor(Color.parseColor("#AAFF00"));
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        newTurtle();
                                        question.dismiss();
                                        button2.setBackgroundColor(Color.parseColor("#50C878"));

                                        TextView questionText = question.findViewById(R.id.Text);
                                        questionText.setText("Where do Hawksbill Turtles live?");
                                        button1.setText("Coral Reefs");
                                        button2.setText("Shores");
                                    }
                                }, 500);
                            }
                        });


                        //WRONG
                        button1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                button1.setBackgroundColor(Color.parseColor("#EE4B2B"));
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        question.dismiss();
                                        button1.setBackgroundColor(Color.parseColor("#50C878"));

                                    }
                                }, 500);
                            }
                        });




                    }
                    random = (int)(Math.random()*2)+1;

                }
            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hideBubble();
            }
        }, 2000);
    }

    private void hideBubble() {
        bubble.setVisibility(View.GONE);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showBubble();
            }
        }, 5000);
    }

    private void startCollisionDetection() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                checkCollisionWithTurtles();
                startCollisionDetection();
            }
        }, 10);
    }

    private void startEvent() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int random = new Random().nextInt(3) + 1;

                if (random <= 2) {
                    netEvent();
                } else {
                    trashEvent();
                }

                startEvent();
            }
        }, 11000);
    }

    private void trashEvent() {
        trash.setVisibility(View.VISIBLE);
        trash.setX(2000);

        trash.setTag("trash");
        Animation slideHoriz = new TranslateAnimation(0, -4000, 700, 700);
        slideHoriz.setDuration(1500);
        slideHoriz.setFillAfter(true);

        slideHoriz.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isAnimationInProgress = true; // Animation starts
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                trash.setX(2000);
                trash.setVisibility(View.GONE);
                isAnimationInProgress = false; // Animation ends
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                trashRect = createRect(trash);
                checkCollisionWithTurtles();
            }
        });

        trash.startAnimation(slideHoriz);
    }

    private void netEvent() {
        net.setVisibility(View.VISIBLE);
        net.setY(-2000);

        net.setTag("net");
        Animation slideDown = new TranslateAnimation(0, 0, 0, 1500);
        slideDown.setDuration(2000);
        slideDown.setFillAfter(true);

        slideDown.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //  isAnimationInProgress = true; // Animation starts
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                net.setY(-500);
                slideUp();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        net.startAnimation(slideDown);
    }

    private void slideUp() {
        float startY = net.getY();
        Animation slideUp = new TranslateAnimation(0, 0, 0, -2000 - startY);
        slideUp.setDuration(1000);
        slideUp.setFillAfter(true);

        slideUp.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // isAnimationInProgress = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                net.setY(-2000);
                net.setVisibility(View.GONE);
                //   isAnimationInProgress = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        net.startAnimation(slideUp);
    }

    private List<ImageView> turtleList = new ArrayList<>();
    private void checkCollisionWithTurtles() {
        Rect trashRect = trashRect(trash);
        Rect netRect = dangerRect(net);

        List<ImageView> turtlesToRemove = new ArrayList<>();

        for (ImageView imageView : turtleList) {
            Rect turtleRect = createRect(imageView);
            if ((isCollision(trashRect, turtleRect) && isAnimationInProgress) || isCollision(netRect, turtleRect)) {
                Log.d("Collision", "Removing turtle at " + turtleRect.toString());
                turtlesToRemove.add(imageView);
            }
        }

        for (ImageView imageView : turtlesToRemove) {
            imageView.clearAnimation();
            relativeLayout.removeView(imageView);
            turtleList.remove(imageView);
            Adoption.points -= 5;
            points.setText("Points: "+ Adoption.points);
        }
    }




    private Rect createRect(ImageView imageView) {
        int[] location = new int[2];
        imageView.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];
        int w = imageView.getWidth();
        int h = imageView.getHeight();
        Log.d("Rect", "Creating rect for " + imageView.getTag() + ": x=" + x + " y=" + y + " w=" + w + " h=" + h);
        return new Rect(x, y, x + w, y + h);
    }

    private Rect trashRect(ImageView imageView) {
        int[] location = new int[2];
        imageView.getLocationOnScreen(location);
        int x = 0;
        int y =getScreenHeight()-400;
        int w = imageView.getWidth();
        int h = 400;
        Log.d("TRASHRECT", "Creating rect for " + imageView.getTag() + ": x=" + x + " y=" + y + " w=" + w + " h=" + h);
        return new Rect(x, y, w, y+h);
    }

    private Rect dangerRect(ImageView imageView) {
        int[] location = new int[2];
        imageView.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];
        int w = imageView.getWidth();
        int h = (imageView.getHeight()*2) /3;
        Log.d("Rect", "Creating rect for " + imageView.getTag() + ": x=" + x + " y=" + y + " w=" + w + " h=" + h);
        return new Rect(x, y, x + w, y + h);
    }

    private boolean isCollision(Rect rect1, Rect rect2) {
        Log.d("CollisionCheck", "Checking collision between " + rect1.toString() + " and " + rect2.toString());
        return rect1.intersect(rect2);
    }

    private void startCreatingTurtles() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                newTurtle();
                startCreatingTurtles();
            }
        }, delayMillis);
    }

    private void newTurtle() {
        Adoption.points += 10;
        points.setText("Points: "+ Adoption.points);
        ImageView newTurtle = new ImageView(this);
        newTurtle.setImageResource(R.drawable.turtle1);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(300, 300); // Set width and height to 300dp
        newTurtle.setLayoutParams(layoutParams);
        int randomX = random.nextInt(getScreenWidth() - 300);
        int randomY = random.nextInt(getScreenHeight() - 300);
        newTurtle.setX(randomX);
        newTurtle.setY(randomY);
        int temp = (int) (Math.random() * 2) + 1;
        if (temp % 2 == 0) {
            newTurtle.setScaleX(-1);
        } else {
            newTurtle.setScaleX(1);
        }

        newTurtle.setTag("turtle");

        moveTurtle(newTurtle);
        newTurtle.setOnTouchListener(new View.OnTouchListener() {
            float dx, dy;
            float previousX, previousY;
            int touchSlop = ViewConfiguration.get(getApplicationContext()).getScaledTouchSlop();

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                newTurtle.clearAnimation();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        previousX = event.getRawX();
                        previousY = event.getRawY();
                        dx = previousX - newTurtle.getX();
                        dy = previousY - newTurtle.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float newX = event.getRawX() - dx;
                        float newY = event.getRawY() - dy;

                        float leftBoundary = -touchSlop;
                        float topBoundary = -touchSlop;
                        float rightBoundary = getScreenWidth() - newTurtle.getWidth() + touchSlop;
                        float bottomBoundary = getScreenHeight() - newTurtle.getHeight() + touchSlop;

                        newX = Math.max(leftBoundary, Math.min(newX, rightBoundary));
                        newY = Math.max(topBoundary, Math.min(newY, bottomBoundary));

                        newTurtle.setX(newX);
                        newTurtle.setY(newY);
                        break;
                    case MotionEvent.ACTION_UP:
                        moveTurtle(newTurtle);
                        break;
                }
                return true;
            }
        });
        relativeLayout.addView(newTurtle);
        turtleList.add(newTurtle);
    }

    private void moveTurtle(final ImageView turtle) {
        int randomX = random.nextInt(getScreenWidth() - turtle.getWidth());
        int randomY = random.nextInt(getScreenHeight() - turtle.getHeight());

        Animation animation = new TranslateAnimation(0, randomX - turtle.getX(), 0, randomY - turtle.getY());
        animation.setDuration(1000);
        animation.setFillAfter(true);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                turtle.setX(randomX);
                turtle.setY(randomY);
                turtle.setScaleX(turtle.getScaleX() * -1);
                moveTurtle(turtle);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        turtle.startAnimation(animation);
    }

    private int getScreenWidth() {
        return getResources().getDisplayMetrics().widthPixels;
    }

    private int getScreenHeight() {
        return getResources().getDisplayMetrics().heightPixels;
    }

    private boolean isTrashAnimating() {
        Animation animation = trash.getAnimation();
        return animation != null;
    }
}
