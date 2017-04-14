package com.github.ypicoleal.logindemo;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import it.sephiroth.android.library.easing.Back;
import it.sephiroth.android.library.easing.EasingManager;

public class MainActivity extends AppCompatActivity {
    private ViewGroup rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootLayout = (ViewGroup) findViewById(R.id.main_container);

        EditText email = (EditText) findViewById(R.id.email);
        EditText password = (EditText) findViewById(R.id.password);

        EditText emailS = (EditText) findViewById(R.id.email_singup);
        EditText passwordS = (EditText) findViewById(R.id.password_singup);
        EditText passwordC = (EditText) findViewById(R.id.password_confirm);


        View.OnFocusChangeListener focuslistene = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    animateOnFocus(v);
                } else {
                    animateOnFocusLost(v);
                }
            }
        };

        email.setOnFocusChangeListener(focuslistene);
        password.setOnFocusChangeListener(focuslistene);
        emailS.setOnFocusChangeListener(focuslistene);
        passwordS.setOnFocusChangeListener(focuslistene);
        passwordC.setOnFocusChangeListener(focuslistene);
    }

    public void showSingUp(View view) {
        final CardView animationCircle = (CardView) findViewById(R.id.animation_circle);
        final View animationFirstArist = findViewById(R.id.animation_first_arist);
        final View animationSecondArist = findViewById(R.id.animation_second_arist);
        final View animationSquare = findViewById(R.id.animation_square);
        final LinearLayout squareParent = (LinearLayout) animationSquare.getParent();
        final TextView animationTV = (TextView) findViewById(R.id.animation_tv);
        final ImageView twitterImageView = (ImageView) findViewById(R.id.twitter_img);
        final ImageView instagramImageView = (ImageView) findViewById(R.id.instagram_img);
        final ImageView facebokImageView = (ImageView) findViewById(R.id.facebook_img);
        final View singupFormContainer = findViewById(R.id.signup_form_container);
        final View loginFormContainer = findViewById(R.id.login_form_container);
        final int backgroundColor = ContextCompat.getColor(this, R.color.colorPrimary);
        final TextView singupTV = (TextView) findViewById(R.id.singup_big_tv);

        final float scale = getResources().getDisplayMetrics().density;

        final int circle_curr_margin = (int) (82 * scale + 0.5f);
        final int circle_target_margin = rootLayout.getWidth() - ((int) (70 * scale + 0.5f));

        final int first_curr_width = (int) (120 * scale + 0.5f);
        final int first_target_width = (int) (rootLayout.getHeight() * 1.3);

        final int first_curr_height = (int) (70 * scale + 0.5f);
        final int first_target_height = rootLayout.getWidth();

        final int first_curr_margin = (int) (40 * scale + 0.5f);
        final int first_target_margin = (int) (35 * scale + 0.5f);
        final int first_expand_margin = (first_curr_margin - first_target_height);

        final int square_target_width = rootLayout.getWidth();
        final int square_target_height = (int) (80 * scale + 0.5f);

        final float tv_curr_x = findViewById(R.id.singup_tv).getX() + findViewById(R.id.singup_button).getX();
        final float tv_curr_y = findViewById(R.id.singup_tv).getY() + findViewById(R.id.buttons_container).getY() + findViewById(R.id.singup_container).getY();

        final float tv_target_x = findViewById(R.id.singup_big_tv).getX();
        final float tv_target_y = findViewById(R.id.singup_big_tv).getY();

        final float tv_curr_size = 16;
        final float tv_target_size = 56;

        final int tv_curr_color = Color.parseColor("#ffffff");
        final int tv_target_color = Color.parseColor("#5cffffff");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorAccentDark));
        }

        squareParent.setGravity(Gravity.END);
        animationTV.setText("SIGN UP");

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                int diff_margin = circle_curr_margin - circle_target_margin;
                int margin = circle_target_margin + (int) (diff_margin - (diff_margin * interpolatedTime));

                RelativeLayout.LayoutParams params_circle = (RelativeLayout.LayoutParams) animationCircle.getLayoutParams();
                params_circle.setMargins(0, 0, margin, (int) (40 * scale + 0.5f));
                animationCircle.requestLayout();


                int diff_width = first_curr_width - first_target_width;
                int width = first_target_width + (int) (diff_width - (diff_width * interpolatedTime));

                int diff_height = first_curr_height - first_target_height;
                int height = first_target_height + (int) (diff_height - ((diff_height - first_target_margin) * interpolatedTime));

                diff_margin = first_curr_margin - first_expand_margin;
                margin = first_expand_margin + (int) (diff_margin - (diff_margin * interpolatedTime));
                int margin_r = (int) (-(first_target_width - rootLayout.getWidth()) * interpolatedTime);

                RelativeLayout.LayoutParams params_first = (RelativeLayout.LayoutParams) animationFirstArist.getLayoutParams();
                params_first.setMargins(0, 0, margin_r, margin);
                params_first.width = width;
                params_first.height = height;
                animationFirstArist.requestLayout();

                animationFirstArist.setPivotX(0);
                animationFirstArist.setPivotY(0);
                animationFirstArist.setRotation(-90 * interpolatedTime);


                margin = first_curr_margin + (int) (first_target_margin * interpolatedTime);
                RelativeLayout.LayoutParams params_second = (RelativeLayout.LayoutParams) animationSecondArist.getLayoutParams();
                params_second.setMargins(0, 0, margin_r, margin);
                params_second.width = width;
                animationSecondArist.requestLayout();

                animationSecondArist.setPivotX(0);
                animationSecondArist.setPivotY(animationSecondArist.getHeight());
                animationSecondArist.setRotation(90 * interpolatedTime);

                animationSquare.getLayoutParams().width = (int) (square_target_width * interpolatedTime);
                animationSquare.requestLayout();

                float diff_x = tv_curr_x - tv_target_x;
                float x = tv_target_x + (diff_x - (diff_x * interpolatedTime));
                float diff_y = tv_curr_y - tv_target_y;
                float y = tv_target_y + (diff_y - (diff_y * interpolatedTime));

                animationTV.setX(x);
                animationTV.setY(y);
                animationTV.requestLayout();

                if (interpolatedTime >= 0.2f && interpolatedTime < 0.3f) {
                    twitterImageView.setImageResource(R.drawable.ic_twitter_blue);
                } else if (interpolatedTime >= 0.45f && interpolatedTime < 0.55f) {
                    instagramImageView.setImageResource(R.drawable.ic_instagram_blue);
                } else if (interpolatedTime >= 0.65f && interpolatedTime < 0.75f) {
                    facebokImageView.setImageResource(R.drawable.ic_facebook_blue);
                }

                singupFormContainer.setAlpha(interpolatedTime);
                loginFormContainer.setAlpha(1 - interpolatedTime);
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
                findViewById(R.id.singup_container).setVisibility(View.INVISIBLE);
                animationCircle.setVisibility(View.VISIBLE);
                animationFirstArist.setVisibility(View.VISIBLE);
                animationSecondArist.setVisibility(View.VISIBLE);
                animationSquare.setVisibility(View.VISIBLE);
                animationTV.setVisibility(View.VISIBLE);
                singupFormContainer.setVisibility(View.VISIBLE);

                animationFirstArist.bringToFront();
                squareParent.bringToFront();
                animationSecondArist.bringToFront();
                animationCircle.bringToFront();
                findViewById(R.id.buttons_container).bringToFront();
                singupFormContainer.bringToFront();
                singupTV.bringToFront();
                animationTV.bringToFront();

                animationFirstArist.setBackgroundColor(backgroundColor);
                animationSecondArist.setBackgroundColor(backgroundColor);
                animationCircle.setCardBackgroundColor(backgroundColor);
                animationSquare.setBackgroundColor(backgroundColor);
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        animationCircle.setVisibility(View.GONE);
                        animationFirstArist.setVisibility(View.GONE);
                        animationSecondArist.setVisibility(View.GONE);
                        animationTV.setVisibility(View.GONE);
                        animationSquare.setVisibility(View.GONE);
                        findViewById(R.id.singup_big_tv).setVisibility(View.VISIBLE);
                    }
                }, 100);
                rootLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
                ((View) animationSquare.getParent()).setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
                findViewById(R.id.login_form_container).setVisibility(View.GONE);
                findViewById(R.id.login_tv).setVisibility(View.GONE);
                showLoginButton();
            }
        });

        Animation a2 = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                animationSquare.getLayoutParams().height = (int) (square_target_height * interpolatedTime);
                animationSquare.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        ValueAnimator a3 = ValueAnimator.ofFloat(tv_curr_size, tv_target_size);
        a3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                animationTV.setTextSize(animatedValue);
            }
        });

        ValueAnimator a4 = ValueAnimator.ofInt(tv_curr_color, tv_target_color);
        a4.setEvaluator(new ArgbEvaluator());
        a4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                animationTV.setTextColor(animatedValue);
            }
        });

        a.setDuration(400);
        a2.setDuration(172);
        a3.setDuration(400);
        a4.setDuration(400);

        a4.start();
        a3.start();
        animationSquare.startAnimation(a2);
        animationCircle.startAnimation(a);
        // animationFirstArist.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate_first_arist));
        //animationSecondArist.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate_second_arist));
        singupFormContainer.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate_form));
    }

    public void showLogIn(View view) {
        final CardView animationCircle = (CardView) findViewById(R.id.animation_circle);
        final View animationFirstArist = findViewById(R.id.animation_first_arist);
        final View animationSecondArist = findViewById(R.id.animation_second_arist);
        final View animationSquare = findViewById(R.id.animation_square);
        final LinearLayout squareParent = (LinearLayout) animationSquare.getParent();
        final TextView animationTV = (TextView) findViewById(R.id.animation_tv);
        final ImageView twitterImageView = (ImageView) findViewById(R.id.twitter_img);
        final ImageView instagramImageView = (ImageView) findViewById(R.id.instagram_img);
        final ImageView facebokImageView = (ImageView) findViewById(R.id.facebook_img);
        final View singupFormContainer = findViewById(R.id.signup_form_container);
        final View loginFormContainer = findViewById(R.id.login_form_container);
        final TextView loginTV = (TextView) findViewById(R.id.login_tv);
        final int backgrounColor = ContextCompat.getColor(this, R.color.colorAccent);


        final float scale = getResources().getDisplayMetrics().density;

        final int circle_curr_margin = rootLayout.getWidth() - (int) (view.getWidth() - view.getX() - animationCircle.getWidth());
        final int circle_target_margin = 0;

        final int first_curr_width = (int) (108 * scale + 0.5f);
        final int first_target_width = (rootLayout.getHeight() * 2);

        final int first_curr_height = (int) (70 * scale + 0.5f);
        final int first_target_height = rootLayout.getWidth();

        final int first_curr_margin = (int) (40 * scale + 0.5f);
        final int first_target_margin = (int) (35 * scale + 0.5f);
        final int first_expand_margin = (first_curr_margin - first_target_height);
        final int first_curr_margin_r = rootLayout.getWidth() - first_curr_width;


        final int square_target_width = rootLayout.getWidth();
        final int square_target_height = (int) (80 * scale + 0.5f);

        final float tv_curr_x = findViewById(R.id.login_small_tv).getX() + findViewById(R.id.login_button).getX();
        final float tv_curr_y = findViewById(R.id.login_small_tv).getY() + findViewById(R.id.buttons_container).getY() + findViewById(R.id.login_container).getY();

        final float tv_target_x = findViewById(R.id.login_tv).getX();
        final float tv_target_y = findViewById(R.id.login_tv).getY();

        final float tv_curr_size = 16;
        final float tv_target_size = 56;

        final int tv_curr_color = Color.parseColor("#ffffff");
        final int tv_target_color = Color.parseColor("#5cffffff");


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }

        squareParent.setGravity(Gravity.START);
        animationTV.setText("LOG IN");

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                int diff_margin = circle_curr_margin - circle_target_margin;
                int margin = circle_target_margin + (int) (diff_margin - (diff_margin * interpolatedTime));

                RelativeLayout.LayoutParams params_circle = (RelativeLayout.LayoutParams) animationCircle.getLayoutParams();
                params_circle.setMargins(0, 0, margin, (int) (40 * scale + 0.5f));
                animationCircle.requestLayout();


                int diff_width = first_curr_width - first_target_width;
                int width = first_target_width + (int) (diff_width - (diff_width * interpolatedTime));

                int diff_height = first_curr_height - first_target_height;
                int height = first_target_height + (int) (diff_height - ((diff_height - first_target_margin) * interpolatedTime));

                diff_margin = first_curr_margin - first_expand_margin;
                margin = first_expand_margin + (int) (diff_margin - (diff_margin * interpolatedTime));
                int margin_r = first_curr_margin_r - (int) (first_curr_margin_r * interpolatedTime);
                int margin_l = rootLayout.getWidth() - width < 0 ? rootLayout.getWidth() - width : 0;

                RelativeLayout.LayoutParams params_first = (RelativeLayout.LayoutParams) animationFirstArist.getLayoutParams();
                params_first.setMargins(margin_l, 0, margin_r, margin);
                params_first.width = width;
                params_first.height = height;
                animationFirstArist.requestLayout();

                animationFirstArist.setPivotX(animationFirstArist.getWidth());
                animationFirstArist.setPivotY(0);
                animationFirstArist.setRotation(90 * interpolatedTime);

                margin = first_curr_margin + (int) (first_target_margin * interpolatedTime);
                RelativeLayout.LayoutParams params_second = (RelativeLayout.LayoutParams) animationSecondArist.getLayoutParams();
                params_second.setMargins(0, 0, margin_r, margin);
                params_second.width = width;
                animationSecondArist.requestLayout();

                animationSecondArist.setPivotX(animationSecondArist.getWidth());
                animationSecondArist.setPivotY(animationSecondArist.getHeight());
                animationSecondArist.setRotation(-(90 * interpolatedTime));

                animationSquare.getLayoutParams().width = (int) (square_target_width * interpolatedTime);
                animationSquare.requestLayout();

                float diff_x = tv_curr_x - tv_target_x;
                float x = tv_target_x + (diff_x - (diff_x * interpolatedTime));
                float diff_y = tv_curr_y - tv_target_y;
                float y = tv_target_y + (diff_y - (diff_y * interpolatedTime));

                animationTV.setX(x);
                animationTV.setY(y);
                animationTV.requestLayout();

                if (interpolatedTime >= 0.2f && interpolatedTime < 0.3f) {
                    facebokImageView.setImageResource(R.drawable.ic_facebook_pink);
                } else if (interpolatedTime >= 0.45f && interpolatedTime < 0.55f) {
                    instagramImageView.setImageResource(R.drawable.ic_instagram_pink);
                } else if (interpolatedTime >= 0.65f && interpolatedTime < 0.75f) {
                    twitterImageView.setImageResource(R.drawable.ic_twitter_pink);
                }

                loginFormContainer.setAlpha(interpolatedTime);
                singupFormContainer.setAlpha(1 - interpolatedTime);
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
                animationFirstArist.setBackgroundColor(backgrounColor);
                animationSecondArist.setBackgroundColor(backgrounColor);
                animationCircle.setCardBackgroundColor(backgrounColor);
                animationSquare.setBackgroundColor(backgrounColor);

                animationFirstArist.setVisibility(View.VISIBLE);
                findViewById(R.id.login_container).setVisibility(View.INVISIBLE);
                animationSecondArist.setVisibility(View.VISIBLE);
                animationCircle.setVisibility(View.VISIBLE);
                animationSquare.setVisibility(View.VISIBLE);
                animationTV.setVisibility(View.VISIBLE);
                loginFormContainer.setVisibility(View.VISIBLE);
                loginTV.setVisibility(View.INVISIBLE);

                animationFirstArist.bringToFront();
                squareParent.bringToFront();
                animationSecondArist.bringToFront();
                animationCircle.bringToFront();
                findViewById(R.id.buttons_container).bringToFront();
                loginFormContainer.bringToFront();
                loginTV.bringToFront();
                animationTV.bringToFront();
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        animationCircle.setVisibility(View.GONE);
                        animationFirstArist.setVisibility(View.GONE);
                        animationSecondArist.setVisibility(View.GONE);
                        animationTV.setVisibility(View.GONE);
                        animationSquare.setVisibility(View.GONE);
                        findViewById(R.id.login_tv).setVisibility(View.VISIBLE);
                        findViewById(R.id.login_tv).bringToFront();
                    }
                }, 100);
                rootLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));
                ((View) animationSquare.getParent()).setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));
                findViewById(R.id.signup_form_container).setVisibility(View.GONE);
                findViewById(R.id.singup_big_tv).setVisibility(View.GONE);
                showSingupButton();
            }
        });

        Animation a2 = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                animationSquare.getLayoutParams().height = (int) (square_target_height * interpolatedTime);
                animationSquare.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        ValueAnimator a3 = ValueAnimator.ofFloat(tv_curr_size, tv_target_size);
        a3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                animationTV.setTextSize(animatedValue);
            }
        });

        ValueAnimator a4 = ValueAnimator.ofInt(tv_curr_color, tv_target_color);
        a4.setEvaluator(new ArgbEvaluator());
        a4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                animationTV.setTextColor(animatedValue);
            }
        });

        a.setDuration(400);
        a2.setDuration(172);
        a3.setDuration(400);
        a4.setDuration(400);

        a4.start();
        a3.start();
        animationSquare.startAnimation(a2);
        animationCircle.startAnimation(a);
        loginFormContainer.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate_form_reverse));
    }

    private void showLoginButton() {
        final CardView singupButton = (CardView) findViewById(R.id.singup_button);
        final View loginButton = findViewById(R.id.login_button);

        loginButton.setVisibility(View.VISIBLE);
        loginButton.measure(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        findViewById(R.id.login_container).setVisibility(View.VISIBLE);

        final float scale = getResources().getDisplayMetrics().density;
        final int curr_singup_margin = (int) (-35 * scale + 0.5f);
        final int target_singup_margin = -singupButton.getWidth();

        final int curr_login_margin = -loginButton.getMeasuredWidth();
        final int target_login_margin = (int) (-35 * scale + 0.5f);

        EasingManager manager = new EasingManager(new EasingManager.EasingCallback() {

            @Override
            public void onEasingValueChanged(double value, double oldValue) {
                int diff_margin = curr_singup_margin - target_singup_margin;
                int margin = target_singup_margin + (int) (diff_margin - (diff_margin * value));

                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) singupButton.getLayoutParams();
                layoutParams.setMargins(0, 0, margin, 0);
                singupButton.requestLayout();

                diff_margin = curr_login_margin - target_login_margin;
                margin = target_login_margin + (int) (diff_margin - (diff_margin * value));

                layoutParams = (LinearLayout.LayoutParams) loginButton.getLayoutParams();
                layoutParams.leftMargin = margin;
                loginButton.requestLayout();
            }

            @Override
            public void onEasingStarted(double value) {
                int diff_margin = curr_singup_margin - target_singup_margin;
                int margin = target_singup_margin + (int) (diff_margin - (diff_margin * value));

                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) singupButton.getLayoutParams();
                layoutParams.setMargins(0, 0, margin, 0);
                singupButton.requestLayout();

                diff_margin = curr_login_margin - target_login_margin;
                margin = target_login_margin + (int) (diff_margin - (diff_margin * value));

                layoutParams = (LinearLayout.LayoutParams) loginButton.getLayoutParams();
                layoutParams.setMargins(margin, 0, 0, 0);
                loginButton.requestLayout();
            }

            @Override
            public void onEasingFinished(double value) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) singupButton.getLayoutParams();
                layoutParams.setMargins(0, 0, target_singup_margin, 0);
                singupButton.requestLayout();


                layoutParams = (LinearLayout.LayoutParams) loginButton.getLayoutParams();
                layoutParams.setMargins(target_login_margin, 0, 0, 0);
                loginButton.requestLayout();

                singupButton.setVisibility(View.GONE);
            }
        });

        manager.start(Back.class, EasingManager.EaseType.EaseOut, 0, 1, 600);
    }

    private void showSingupButton() {
        final CardView singupButton = (CardView) findViewById(R.id.singup_button);
        final View loginButton = findViewById(R.id.login_button);

        singupButton.setVisibility(View.VISIBLE);
        singupButton.measure(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        findViewById(R.id.singup_container).setVisibility(View.VISIBLE);

        final float scale = getResources().getDisplayMetrics().density;
        final int curr_singup_margin = -singupButton.getWidth();
        final int target_singup_margin = (int) (-35 * scale + 0.5f);

        final int curr_login_margin = (int) (-35 * scale + 0.5f);
        final int target_login_margin = -loginButton.getMeasuredWidth();

        EasingManager manager = new EasingManager(new EasingManager.EasingCallback() {

            @Override
            public void onEasingValueChanged(double value, double oldValue) {
                int diff_margin = curr_singup_margin - target_singup_margin;
                int margin = target_singup_margin + (int) (diff_margin - (diff_margin * value));

                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) singupButton.getLayoutParams();
                layoutParams.setMargins(0, 0, margin, 0);
                singupButton.requestLayout();

                diff_margin = curr_login_margin - target_login_margin;
                margin = target_login_margin + (int) (diff_margin - (diff_margin * value));

                layoutParams = (LinearLayout.LayoutParams) loginButton.getLayoutParams();
                layoutParams.leftMargin = margin;
                loginButton.requestLayout();
            }

            @Override
            public void onEasingStarted(double value) {
                int diff_margin = curr_singup_margin - target_singup_margin;
                int margin = target_singup_margin + (int) (diff_margin - (diff_margin * value));

                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) singupButton.getLayoutParams();
                layoutParams.setMargins(0, 0, margin, 0);
                singupButton.requestLayout();

                diff_margin = curr_login_margin - target_login_margin;
                margin = target_login_margin + (int) (diff_margin - (diff_margin * value));

                layoutParams = (LinearLayout.LayoutParams) loginButton.getLayoutParams();
                layoutParams.setMargins(margin, 0, 0, 0);
                loginButton.requestLayout();
            }

            @Override
            public void onEasingFinished(double value) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) singupButton.getLayoutParams();
                layoutParams.setMargins(0, 0, target_singup_margin, 0);
                singupButton.requestLayout();


                layoutParams = (LinearLayout.LayoutParams) loginButton.getLayoutParams();
                layoutParams.setMargins(target_login_margin, 0, 0, 0);
                loginButton.requestLayout();
                loginButton.setVisibility(View.GONE);
            }
        });

        manager.start(Back.class, EasingManager.EaseType.EaseOut, 0, 1, 600);
    }

    private void animateOnFocus(View v) {
        final CardView first_container = (CardView) v.getParent();
        final CardView second_container = (CardView) first_container.getParent();
        final float scale = getResources().getDisplayMetrics().density;

        final int first_curr_radius = (int) (18 * scale + 0.5f);
        final int first_target_radius = (int) (6 * scale + 0.5f);

        final int second_curr_radius = (int) (20 * scale + 0.5f);
        final int second_target_radius = (int) (8 * scale + 0.5f);

        final int first_curr_color = Color.parseColor("#00ffffff");
        final int first_target_color = ((ColorDrawable) rootLayout.getBackground()).getColor();

        final int second_curr_color = Color.parseColor("#5cffffff");
        final int second_target_color = Color.parseColor("#ffffff");

        ValueAnimator first_anim = new ValueAnimator();
        first_anim.setIntValues(first_curr_color, first_target_color);
        first_anim.setEvaluator(new ArgbEvaluator());
        first_anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                first_container.setCardBackgroundColor((Integer) animation.getAnimatedValue());
            }
        });

        ValueAnimator second_anim = new ValueAnimator();
        second_anim.setIntValues(second_curr_color, second_target_color);
        second_anim.setEvaluator(new ArgbEvaluator());
        second_anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                second_container.setCardBackgroundColor((Integer) animation.getAnimatedValue());
            }
        });

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                int diff_radius = first_curr_radius - first_target_radius;
                int radius = first_target_radius + (int) (diff_radius - (diff_radius * interpolatedTime));

                first_container.setRadius(radius);
                first_container.requestLayout();

                diff_radius = second_curr_radius - second_target_radius;
                radius = second_target_radius + (int) (diff_radius - (diff_radius * interpolatedTime));

                second_container.setRadius(radius);
                second_container.requestLayout();

            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setDuration(200);
        first_anim.setDuration(200);
        second_anim.setDuration(200);

        first_anim.start();
        second_anim.start();
        first_container.startAnimation(a);
    }

    private void animateOnFocusLost(View v) {
        final CardView first_container = (CardView) v.getParent();
        final CardView second_container = (CardView) first_container.getParent();
        final float scale = getResources().getDisplayMetrics().density;

        final int first_curr_radius = (int) (6 * scale + 0.5f);
        final int first_target_radius = (int) (18 * scale + 0.5f);

        final int second_curr_radius = (int) (8 * scale + 0.5f);
        final int second_target_radius = (int) (20 * scale + 0.5f);

        final int first_curr_color = ((ColorDrawable) rootLayout.getBackground()).getColor();
        final int first_target_color = Color.parseColor("#00ffffff");

        final int second_curr_color = Color.parseColor("#ffffff");
        final int second_target_color = Color.parseColor("#5cffffff");

        ValueAnimator first_anim = new ValueAnimator();
        first_anim.setIntValues(first_curr_color, first_target_color);
        first_anim.setEvaluator(new ArgbEvaluator());
        first_anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                first_container.setCardBackgroundColor((Integer) animation.getAnimatedValue());
            }
        });

        ValueAnimator second_anim = new ValueAnimator();
        second_anim.setIntValues(second_curr_color, second_target_color);
        second_anim.setEvaluator(new ArgbEvaluator());
        second_anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                second_container.setCardBackgroundColor((Integer) animation.getAnimatedValue());
            }
        });

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                int diff_radius = first_curr_radius - first_target_radius;
                int radius = first_target_radius + (int) (diff_radius - (diff_radius * interpolatedTime));

                first_container.setRadius(radius);
                first_container.requestLayout();

                diff_radius = second_curr_radius - second_target_radius;
                radius = second_target_radius + (int) (diff_radius - (diff_radius * interpolatedTime));

                second_container.setRadius(radius);
                second_container.requestLayout();

            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setDuration(200);
        first_anim.setDuration(200);
        second_anim.setDuration(200);

        first_anim.start();
        second_anim.start();
        first_container.startAnimation(a);

    }
}
