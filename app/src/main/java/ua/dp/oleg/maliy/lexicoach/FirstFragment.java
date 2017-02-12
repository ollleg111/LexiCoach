package ua.dp.oleg.maliy.lexicoach;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.app.Fragment;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FirstFragment extends Fragment implements View.OnClickListener,
        SoundPool.OnLoadCompleteListener {
    private final int DURATION = 300;

    @BindView(R.id.buttonAdd)
    protected Button buttonAdd;
    @BindView(R.id.buttonOwn)
    protected Button buttonOwn;
    @BindView(R.id.buttonBell)
    protected ImageButton buttonBell;
    @BindView(R.id.buttonBox)
    protected ImageButton buttonBox;
    @BindView(R.id.englishWord)
    protected CustomTextView englishWord;
    @BindView(R.id.hebrewWordZero)
    protected CustomTextView hebrewWordZero;
    @BindView(R.id.hebrewWordOne)
    protected CustomTextView hebrewWordOne;
    @BindView(R.id.hebrewWordTwo)
    protected CustomTextView hebrewWordTwo;
    @BindView(R.id.hebrewWordThree)
    protected CustomTextView hebrewWordThree;

    private SoundPool sp;
    private int soundIdChpoon;
    private int soundIdBell;

    public FirstFragment() {
    }

    public static FirstFragment newInstance() {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_first, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        englishWord.setText(getString(R.string.englishWordOne));

        hebrewWordZero.setText(getString(R.string.hebrewWordZero));
        hebrewWordOne.setText(getString(R.string.hebrewWordOne));
        hebrewWordTwo.setText(getString(R.string.hebrewWordTwo));
        hebrewWordThree.setText(getString(R.string.hebrewWordThree));

        hebrewWordOne.setOnClickListener(this);
        hebrewWordTwo.setOnClickListener(this);
        hebrewWordThree.setOnClickListener(this);

        sound();
    }

    private void sound() {
        sp = new SoundPool(Const.MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        sp.setOnLoadCompleteListener(this);

        try {
            soundIdChpoon = sp.load(getActivity().getAssets().openFd("LexiCoach.wav"), 1);
            soundIdBell = sp.load(getActivity().getAssets().openFd("Bell.wav"), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.buttonAdd)
    void buttonAdd() {
        Toast.makeText(getActivity(), getString(R.string.button_add), Toast.LENGTH_SHORT).show();
        sp.play(soundIdChpoon, 1, 1, 0, 0, 2);
    }

    @OnClick(R.id.buttonOwn)
    void buttonOwn() {
        Toast.makeText(getActivity(), getString(R.string.button_own), Toast.LENGTH_SHORT).show();
        sp.play(soundIdChpoon, 1, 1, 0, 0, 2);
    }

    @OnClick(R.id.buttonBell)
    void buttonBell() {
        Toast.makeText(getActivity(), getString(R.string.button_bell), Toast.LENGTH_SHORT).show();
        sp.play(soundIdBell, 1, 1, 0, 0, 2);
    }

    @OnClick(R.id.buttonBox)
    void buttonBox() {
        Toast.makeText(getActivity(), getString(R.string.button_box), Toast.LENGTH_SHORT).show();
        sp.play(soundIdChpoon, 1, 1, 0, 0, 2);
    }

    @Override
    public void onClick(View v) {
        sp.play(soundIdChpoon, 1, 1, 0, 0, 2);
        setAnimation(v, hebrewWordZero);
    }

    private void setAnimation(final View actionView, final View endView) {
        final AnimationSet set = new AnimationSet(false);
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1f, ((TextView) endView).getTextSize() / ((TextView) actionView).getTextSize(),
                1.0f, endView.getHeight() * 1f / actionView.getHeight() * 1f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        set.addAnimation(scaleAnimation);

        TranslateAnimation translateAnimation = new TranslateAnimation(
                0, (endView.getX() + endView.getWidth() / 2) - (actionView.getX() + actionView.getWidth() / 2),
                0, (endView.getY() + endView.getHeight() / 2) - (actionView.getY() + actionView.getHeight() / 2)
        );
        set.addAnimation(translateAnimation);

        set.setDuration(DURATION);
        set.setAnimationListener(new Animation.AnimationListener() {

            ValueAnimator valueAnimator;
            final int colorActionView = ((TextView) actionView).getCurrentTextColor();

            @Override
            public void onAnimationStart(Animation animation) {
                valueAnimator = new ValueAnimator();
                valueAnimator.setIntValues(((TextView) actionView).getCurrentTextColor(), ((TextView) endView).getCurrentTextColor());
                valueAnimator.setEvaluator(new ArgbEvaluator());
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        ((TextView) actionView).setTextColor((Integer) valueAnimator.getAnimatedValue());
                    }
                });
                valueAnimator.setDuration(DURATION);
                valueAnimator.start();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                valueAnimator.end();
                ((TextView) actionView).setTextColor(colorActionView);
                String actonText = (String) ((TextView) actionView).getText();
                String endText = (String) ((TextView) endView).getText();
                ((TextView) actionView).setText(endText);
                ((TextView) endView).setText(actonText);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        actionView.startAnimation(set);
    }

    @Override
    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
    }
}
