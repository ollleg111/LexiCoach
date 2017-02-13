package ua.dp.oleg.maliy.lexicoach;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

public class SoundCall {

    public static AssetFileDescriptor soundClick = null;
    public static AssetFileDescriptor soundBell = null;

    private static SoundCall sSoundCall;

    private static MediaPlayer mp = null;

    public static SoundCall init(Context context) {

        if (sSoundCall == null) {
            mp = new MediaPlayer();

            sSoundCall = new SoundCall();

            if (soundClick == null) {
                soundClick = context.getResources()
                        .openRawResourceFd(R.raw.sound_click);
            }
            if (soundBell == null) {
                soundBell = context.getResources()
                        .openRawResourceFd(R.raw.sound_bell);
            }
        }
        return sSoundCall;
    }

    public static void audioPlayer(int soundType) {

//        mp = new MediaPlayer();
//        AssetFileDescriptor soundFileDescriptor = null;

        try {
            AssetFileDescriptor soundFileDescriptor = null;
//            if (soundClick == null) {
//                soundClick = context.getResources()
//                        .openRawResourceFd(R.raw.sound_click);
//            }
//            if (soundBell == null) {
//                soundBell = context.getResources()
//                        .openRawResourceFd(R.raw.sound_bell);
//            }
            switch (soundType) {
                case Const.SOUND_CLICK:
                    soundFileDescriptor = soundClick;
                    break;
                case Const.SOUND_BELL:
                    soundFileDescriptor = soundBell;
                    break;
            }
            mp.reset();
            mp.setDataSource(soundFileDescriptor.getFileDescriptor(),
                    soundFileDescriptor.getStartOffset(),
                    soundFileDescriptor.getDeclaredLength());
            mp.prepare();
            mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                @Override
                public void onPrepared(MediaPlayer player) {
                    player.seekTo(0);
                    player.start();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//        sp = new SoundPool(Const.MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
//        sp.setOnLoadCompleteListener(this);
//
//        try {
//            soundIdChpoon = sp.load(getActivity().getAssets().openFd("LexiCoach.wav"), 1);
//            soundIdBell = sp.load(getActivity().getAssets().openFd("Bell.wav"), 1);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        sp.play(soundIdChpoon, 1, 1, 0, 0, 2);
//        sp.play(soundIdBell, 1, 1, 0, 0, 2);



//    private void createSoundPool(List<PageContent> contentList) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            createNewSoundPool();
//        } else {
//            createOldSoundPool();
//        }
//        sp.setOnLoadCompleteListener(this);
//        soundWin = sp.load(getContext(), R.raw.win_sound, 1);
//        soundFail = sp.load(getContext(), R.raw.fail_sound, 1);
//        soundsIds = new ArrayList<>();
//        for (int i = 0; i < contentList.size(); i++) {
//            int soundId = sp.load(getContext(), contentList.get(i).getSound(), 1);
//            soundsIds.add(soundId);
//        }
//    }
//
//    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    protected void createNewSoundPool() {
//        AudioAttributes attributes = new AudioAttributes.Builder()
//                .setUsage(AudioAttributes.USAGE_GAME)
//                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
//                .build();
//        sp = new SoundPool.Builder()
//                .setAudioAttributes(attributes)
//                .build();
//    }
//
//    @SuppressWarnings("deprecation")
//    protected void createOldSoundPool() {
//        sp = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
//    }
