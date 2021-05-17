package cnmi.it.asthmaprototype;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;


public class TFadeAnimator {
    TextView blobText;
    public String[] text = new String[] {""};
    public int position = 0;
    Animation fadeInAnimationObject;
    Animation textDisplayAnimationObject;
    Animation delayBetweenAnimations;
    Animation fadeOutAnimationObject;
    int fadeEffectDuration;
    int delayDuration;
    int displayFor;

    public TFadeAnimator(TextView tv, String[] textList){
        this(tv, 700, 1000, 2000, textList);
    }
    public TFadeAnimator(TextView tv, int fadeEffectDuration, int delayDuration, int displayLength, String[] textList){
        blobText = tv;
        text = textList;
        this.fadeEffectDuration = fadeEffectDuration;
        this.delayDuration =  delayDuration;
        this.displayFor = displayLength;
        InnitializeAnimation();
    }

    private void InnitializeAnimation(){
        fadeInAnimationObject = new AlphaAnimation(0f, 1f);
        fadeInAnimationObject.setDuration(fadeEffectDuration);
        textDisplayAnimationObject = new AlphaAnimation(1f, 1f);
        textDisplayAnimationObject.setDuration(displayFor);
        delayBetweenAnimations = new AlphaAnimation(0f, 0f);
        delayBetweenAnimations.setDuration(delayDuration);
        fadeOutAnimationObject = new AlphaAnimation(1f, 0f);
        fadeOutAnimationObject.setDuration(fadeEffectDuration);
        fadeInAnimationObject.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                position++;
                if(position>=text.length)
                {
                    position = 0;
                }
                blobText.setText(text[position]);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                blobText.startAnimation(textDisplayAnimationObject);
            }
        });
        textDisplayAnimationObject.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO Auto-generated method stub
                blobText.startAnimation(fadeOutAnimationObject);
            }
        });
        fadeOutAnimationObject.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO Auto-generated method stub
                blobText.startAnimation(delayBetweenAnimations);
            }
        });
        delayBetweenAnimations.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO Auto-generated method stub
                blobText.startAnimation(fadeInAnimationObject);
            }
        });
    }
}
