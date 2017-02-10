package ua.dp.oleg.maliy.lexicoach;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.app.Fragment;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FirstFragment extends Fragment implements View.OnClickListener, SoundPool.OnLoadCompleteListener {
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
        TextView tv = (TextView) v;
        String textV, textZero;

        textV = (String) ((TextView) v).getText();
        textZero = (String) hebrewWordZero.getText();

        setAnimation(v,textV);
        tv.setText(textZero);
        sp.play(soundIdChpoon, 1, 1, 0, 0, 2);
    }
    @Override
    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
    }

    public void setAnimation(final View v, final String textOne) {
        float xS = v.getX();
        float yS = v.getY();
        float xG = hebrewWordZero.getX();
        float yG = hebrewWordZero.getY();
        final int sendViewColor = ((TextView) v).getCurrentTextColor();
        int getViewColor = hebrewWordZero.getCurrentTextColor();


        TranslateAnimation transAnimSend = new TranslateAnimation(
                0,
                xG - xS,
                0,
                -150);
        transAnimSend.setDuration(200);

        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(sendViewColor, getViewColor);
        valueAnimator.setEvaluator(new ArgbEvaluator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                ((TextView) v).setTextColor((Integer) valueAnimator.getAnimatedValue());
                Log.d("click Update", (String) hebrewWordZero.getText());
            }
        });
        valueAnimator.setDuration(200);

        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.d("click Start", (String) hebrewWordZero.getText());
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.d("click End", (String) hebrewWordZero.getText());

                ((TextView) v).setTextColor(sendViewColor);
                hebrewWordZero.setText(textOne);
                Log.d("click End2", (String) hebrewWordZero.getText());
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        valueAnimator.start();
        v.setAnimation(transAnimSend);
    }
}
