package assignment8.client;

import assignment8.ConstantUtils;
import assignment8.ParserUtils;
import assignment8.exceptions.InvalidIdentifierException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


/**
 * Write thread is used to write data into the server from the client
 */
public class WriteThread extends Thread {

  private transient PrintWriter writer;
  private transient Socket socket;
  private transient final Client client;
  public static Boolean valid = Boolean.TRUE;

  /**
   * Instantiates a new Write thread.
   *
   * @param socket the socket
   * @param client the client
   */
  public WriteThread(final Socket socket, final Client client) {
    this.socket = socket;
    this.client = client;

    try {
      final OutputStream output = socket.getOutputStream();
      writer = new PrintWriter(output, true);
    } catch (IOException ex) {
      System.out.println(ConstantUtils.OUTPUT_STREAM_ERROR_MESSAGE + ex.getMessage());
      ex.printStackTrace();
    }
  }

  /**
   * "consoleInput" is read from the console and broken down and sent to Parser. The parser
   * generates array of desired form (example, username would generate 19 3 aha) and pass it on to
   * the server. "message" contains the generated array which is then sent to the server
   */
  @Override
  public void run() {

    final Scanner scanner = new Scanner(System.in);

    System.out.println(ConstantUtils.GET_USERNAME);
    final String userName = scanner.nextLine();
    client.setUserName(userName);

    try {
      final String message = ParserUtils.getInstance(ConstantUtils.NINETEEN_STR, userName);
      writer.println(message);
    } catch (InvalidIdentifierException e) {
      e.printStackTrace();
    }

    String consoleInput;

    while (socket.isConnected() && valid) {
      consoleInput = scanner.nextLine();
      //extract command from output
      try {
        final String message;
        final String[] output;

        output = consoleInput.split(ConstantUtils.SPACE);

        if (consoleInput.startsWith(ConstantUtils.AT_USER)) {
          //starts with @ and doesnt have 'all'
          if (consoleInput.startsWith(ConstantUtils.AT_ALL)) {
            final String msg = consoleInput
                .replace(output[ConstantUtils.ZERO] + ConstantUtils.SPACE, ConstantUtils.NO_STRING);
            message = ParserUtils.getInstance(ConstantUtils.TWENTY_FOUR_STRING, userName, msg);
            writer.println(message);
          } else {
            final String msg = consoleInput
                .replace(output[ConstantUtils.ZERO] + ConstantUtils.SPACE, ConstantUtils.NO_STRING);
            message = ParserUtils.getInstance(ConstantUtils.TWENTY_FIVE_STRING, userName,
                output[ConstantUtils.ZERO].replace(ConstantUtils.AT_USER, ConstantUtils.NO_STRING),
                msg);
            writer.println(message);
          }
        } else if (consoleInput.startsWith(ConstantUtils.EXCLAMATION)) {
          final String receiverName = consoleInput
              .replace(ConstantUtils.EXCLAMATION, ConstantUtils.NO_STRING);
          message = ParserUtils
              .getInstance(ConstantUtils.TWENTY_SEVEN_STRING, userName, receiverName);
          writer.println(message);
        } else if (consoleInput.matches(ConstantUtils.QUESTION_MARK)) {
          System.out.println(ConstantUtils.COMMANDS);

        } else if (consoleInput.matches(ConstantUtils.LOG_OFF)) {
          message = ParserUtils
              .getInstance(ConstantUtils.protocolMapping.get(consoleInput).toString(), userName);
          writer.println(message);
          break;
        } else if (consoleInput.matches(ConstantUtils.WHO)) {
          message = ParserUtils
              .getInstance(ConstantUtils.protocolMapping.get(consoleInput).toString(), userName);
          writer.println(message);
        } else {
            if (valid) {
                System.out.println(ConstantUtils.INVALID_INPUT);
            }
        }
      } catch (InvalidIdentifierException e) {
        e.printStackTrace();
        break;
      }
    }

  }
}