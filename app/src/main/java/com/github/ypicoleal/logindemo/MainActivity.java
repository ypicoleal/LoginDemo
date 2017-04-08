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
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
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
    private boolean keyboardListenersAttached = false;
    private boolean isKEyboardShown = false;
    private ViewTreeObserver.OnGlobalLayoutListener keyboardLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            int heightDiff = rootLayout.getRootView().getHeight() - rootLayout.getHeight();
            double contentViewTop = rootLayout.getRootView().getHeight() * 0.20;

            Log.i("Keyboard", "diff " + heightDiff + " " + (rootLayout.getRootView().getHeight() * 0.20));

            if (heightDiff < contentViewTop) {
                if (isKEyboardShown) {
                    isKEyboardShown = false;
                    onHideKeyboard();
                }
            } else {
                if (!isKEyboardShown) {
                    isKEyboardShown = true;
                    onShowKeyboard();

                }
            }
        }
    };

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

    @Override
    protected void onStart() {
        super.onStart();
        //attachKeyboardListeners();

    }

    public void showSingUp(View view) {
        final View animationCircle = findViewById(R.id.animation_circle);
        final View animationFirstArist = findViewById(R.id.animation_first_arist);
        final View animationSecondArist = findViewById(R.id.animation_second_arist);
        final View animationSquare = findViewById(R.id.animation_square);
        final TextView animationTV = (TextView) findViewById(R.id.animation_tv);
        final ImageView twitterImageView = (ImageView) findViewById(R.id.twitter_img);
        final ImageView instagramImageView = (ImageView) findViewById(R.id.instagram_img);
        final ImageView facebokImageView = (ImageView) findViewById(R.id.facebook_img);
        final View singupFormContainer = findViewById(R.id.signup_form_container);
        final View loginFormContainer = findViewById(R.id.login_form_container);

        findViewById(R.id.singup_container).setVisibility(View.INVISIBLE);
        animationCircle.setVisibility(View.VISIBLE);
        animationFirstArist.setVisibility(View.VISIBLE);
        animationSecondArist.setVisibility(View.VISIBLE);
        animationSquare.setVisibility(View.VISIBLE);
        animationTV.setVisibility(View.VISIBLE);
        singupFormContainer.setVisibility(View.VISIBLE);

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

        twitterImageView.setImageResource(R.drawable.ic_twitter_pink);
        instagramImageView.setImageResource(R.drawable.ic_instagram_pink);
        facebokImageView.setImageResource(R.drawable.ic_facebook_pink);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorAccentDark));
        }


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

                margin = first_curr_margin + (int) (first_target_margin * interpolatedTime);
                RelativeLayout.LayoutParams params_second = (RelativeLayout.LayoutParams) animationSecondArist.getLayoutParams();
                params_second.setMargins(0, 0, margin_r, margin);
                params_second.width = width;
                animationSecondArist.requestLayout();

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
        animationFirstArist.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate_first_arist));
        animationSecondArist.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate_second_arist));
        singupFormContainer.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate_form));
    }

    private void showLoginButton() {
        final CardView singupButton = (CardView) findViewById(R.id.singup_button);
        final View loginButton = findViewById(R.id.login_button);

        loginButton.setVisibility(View.VISIBLE);
        loginButton.measure(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

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
                singupButton.setVisibility(View.GONE);
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

    protected void attachKeyboardListeners() {
        if (keyboardListenersAttached) {
            return;
        }

        rootLayout = (ViewGroup) findViewById(R.id.main_container);
        rootLayout.getViewTreeObserver().addOnGlobalLayoutListener(keyboardLayoutListener);

        keyboardListenersAttached = true;
    }

    private void onShowKeyboard() {
        Log.i("KEy", "Keyborad shown");
        final CardView logoCard = (CardView) findViewById(R.id.logo_card);
        final TextView logoTV = (TextView) findViewById(R.id.logo_tv);
        final TextView forgotTV = (TextView) findViewById(R.id.forgot_pass_tv);
        final TextView loginTV = (TextView) findViewById(R.id.login_tv);
        final LinearLayout buttonsContainer = (LinearLayout) findViewById(R.id.buttons_container);
        final LinearLayout signupContainer = (LinearLayout) findViewById(R.id.singup_container);

        final float scale = getResources().getDisplayMetrics().density;
        final int curr_height = (int) (52 * scale + 0.5f);
        final int targetHeight = (int) (32 * scale + 0.5f);

        final int curr_margin_forgot = (int) (50 * scale + 0.5f);
        final int target_margin_forgot = (int) (20 * scale + 0.5f);

        final int curr_margin_buttons = (int) (40 * scale + 0.5f);
        final int target_margin_buttons = (int) (-35 * scale + 0.5f);

        final int curr_text_size = 32;
        final int target_text_size = 12;

        final int curr_color = Color.parseColor("#5cffffff");
        final int target_color = Color.parseColor("#ffffffff");

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                int diff_size = curr_text_size - target_text_size;
                logoTV.setTextSize(target_text_size + (diff_size - (diff_size * interpolatedTime)));

                diff_size = curr_height - targetHeight;
                int size = targetHeight + (int) (diff_size - (diff_size * interpolatedTime));
                logoCard.getLayoutParams().height = size;
                logoCard.getLayoutParams().width = size;
                logoCard.setRadius(size / 2);
                logoCard.requestLayout();

                int diff_margin = curr_margin_forgot - target_margin_forgot;
                int margin = target_margin_forgot + (int) (diff_margin - (diff_margin * interpolatedTime));
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) forgotTV.getLayoutParams();
                params.setMargins(0, margin, 0, 0);
                forgotTV.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        a.setDuration(200);
        logoCard.startAnimation(a);

        EasingManager manager = new EasingManager(new EasingManager.EasingCallback() {

            @Override
            public void onEasingValueChanged(double value, double oldValue) {
                int diff_margin = curr_margin_buttons - target_margin_buttons;
                int margin = target_margin_buttons + (int) (diff_margin - (diff_margin * value));
                LinearLayout.LayoutParams params_buttons = (LinearLayout.LayoutParams) buttonsContainer.getLayoutParams();
                params_buttons.setMargins(0, 0, 0, margin);
                buttonsContainer.requestLayout();
            }

            @Override
            public void onEasingStarted(double value) {
                int diff_margin = curr_margin_buttons - target_margin_buttons;
                int margin = target_margin_buttons + (int) (diff_margin - (diff_margin * value));
                LinearLayout.LayoutParams params_buttons = (LinearLayout.LayoutParams) buttonsContainer.getLayoutParams();
                params_buttons.setMargins(0, 0, 0, margin);
                buttonsContainer.requestLayout();
            }

            @Override
            public void onEasingFinished(double value) {
            }
        });

        manager.start(Back.class, EasingManager.EaseType.EaseOut, 0, 1, 5000);


        ValueAnimator anim = new ValueAnimator();
        anim.setIntValues(curr_color, target_color);
        anim.setEvaluator(new ArgbEvaluator());
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                loginTV.setTextColor((Integer) animation.getAnimatedValue());
            }
        });
        anim.setDuration(200);
        anim.start();
    }

    private void onHideKeyboard() {
        Log.i("KEy", "Keyborad hide");
    }
}
