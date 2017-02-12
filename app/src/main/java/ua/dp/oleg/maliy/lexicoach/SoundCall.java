package ua.dp.oleg.maliy.lexicoach;

import android.media.SoundPool;

public class SoundCall implements SoundPool.OnLoadCompleteListener  {

//    private SoundPool sp;
//
//    private int soundIdChpoon;
//    private int soundIdBell;
//
//
//
//    private void sound() {
//        sp = new SoundPool(Const.MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
//        sp.setOnLoadCompleteListener(this);
//
//        try {
//            soundIdChpoon = sp.load(getActivity().getAssets().openFd("LexiCoach.wav"), 1);
//            soundIdBell = sp.load(getActivity().getAssets().openFd("Bell.wav"), 1);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
    @Override
    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {

    }
}
