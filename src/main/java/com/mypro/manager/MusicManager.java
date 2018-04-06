package com.mypro.manager;

import java.io.File;

import javax.sound.sampled.*;

import com.mypro.model.GamingInfo;

/**
 * ±³¾°ÒôÀÖ
 */
public class MusicManager {
private static MusicManager manager;
private AudioInputStream audioInputStream;

private AudioFormat audioFormat;

private SourceDataLine sourceDataLine;

public static MusicManager getMusicManager() {
    if (manager == null) {
        manager = new MusicManager();
    }
    return manager;
}

private MusicManager() {

}

public void playMusicByR(String resId, boolean isLoop) {
    try {
        File file = new File("bgm" + File.separator + resId);
        System.out.println(file);
        Thread playThread = new Thread(new PlayThread(file, isLoop));
        playThread.start();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public static void release() {
}


class PlayThread extends Thread {
    public boolean isLoop;
    File musicFile;
    byte tempBuffer[] = new byte[1024];

    public PlayThread(File musicFile, boolean isLoop) {
        this.musicFile = musicFile;
        this.isLoop = isLoop;
    }

    public void run() {
        try {
            int cnt;
            do {
                System.out.println(musicFile);
                audioInputStream = AudioSystem.getAudioInputStream(musicFile);

                audioFormat = audioInputStream.getFormat();
                if (audioFormat.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
                    audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                            audioFormat.getSampleRate(), 16,
                            audioFormat.getChannels(),
                            audioFormat.getChannels() * 2,
                            audioFormat.getSampleRate(),
                            false);
                    audioInputStream = AudioSystem.getAudioInputStream(audioFormat, audioInputStream);
                }
                DataLine.Info dataLineInfo = new DataLine.Info(
                        SourceDataLine.class, audioFormat,
                        AudioSystem.NOT_SPECIFIED);
                sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
                sourceDataLine.open();
                sourceDataLine.start();
                while ((cnt = audioInputStream.read(tempBuffer, 0, tempBuffer.length)) != -1) {
                    if (!GamingInfo.getGamingInfo().isGaming())
                        break;

                    if (cnt > 0) {
                        sourceDataLine.write(tempBuffer, 0, cnt);
                    }
                }

                sourceDataLine.drain();
                sourceDataLine.close();
            } while (isLoop && GamingInfo.getGamingInfo().isGaming());
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

    }
}
}
