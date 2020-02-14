package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Log {

  private static BufferedWriter bw;
  FileWriter writer = null;
  File file = new File("monster.txt");

  private Log() {
  }

  private static class LogWriter {
    public static final Log logwriter = new Log(); //딱 한번 실행하는 싱글톤 패턴이다.
  }

  public static Log getInstance() {
    return LogWriter.logwriter;
  }

  public void write(String monsterName, String monsterType) {
    try {
      writer = new FileWriter(file, true);
      writer.write(monsterName + " " + monsterType + "\n");
      writer.flush();

      System.out.println("DONE");
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (writer != null) writer.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
//
//    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//      //Java 9에서 finalize의 대체제로 사용. addShutdownHook은 JVM이 종료될 때 호출되는 것임.
//      //https://docs.oracle.com/javase/9/docs/api/java/lang/Runtime.html#addShutdownHook-java.lang.Thread-
//      try {
//        bw.close();
//      } catch (IOException e) {
//        e.printStackTrace();
//      }
//    }));
  }
}