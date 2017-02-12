package ua.dp.oleg.maliy.lexicoach;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

public class SoundCall {

    public static AssetFileDescriptor soundClick = null;
    public static AssetFileDescriptor soundBell = null;

    private static MediaPlayer mp = null;

    public static void audioPlayer(int soundType, Context context) {

        mp = new MediaPlayer();
        AssetFileDescriptor soundFileDescriptor = null;

        try {
            if (soundClick == null) {
                soundClick = context.getResources()
                        .openRawResourceFd(R.raw.sound_click);
            }
            if (soundBell == null) {
                soundBell = context.getResources()
                        .openRawResourceFd(R.raw.sound_bell);
            }
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
