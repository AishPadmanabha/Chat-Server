package assignment8;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Constant utils.
 */
public class ConstantUtils {

  /**
   * The constant ZERO.
   */
  public static final Integer ZERO = 0;
  /**
   * The constant ONE.
   */
  public static final Integer ONE = 1;
  /**
   * The constant CONNECT_SUCCESS.
   */
  public static final String CONNECT_SUCCESS = "You have successfully been connected "
            + "to the chat room. No of users currently present in the chat room are ";
  /**
   * The constant CONNECT_FAIL.
   */
  public static final String CONNECT_FAIL = "Sorry the chat room is currently full. No of users connected are ";
  public static final String NEXT_STEP= ".\nPress enter to exit.";
  public static final String TWENTY_SIX = "26";
  public static final String NO_USER = "Given user doesnt exist";
  public static final String TWENTY_THREE = "23";
  public static final String TWENTY_EIGHT = "28";
  public static final String DISCONNECT_MESSAGE = "Disconnected";
  public static final int TWENTY_TWO = 22;
  public static final Integer TWO = 2;
  public static final int TWENTY_ONE = 21;
  public static final String TWENTY = "20";
  public static final int TWENTY_INT = 20;
  public static final String NEW_USER_MESSAGE = "New user connected";
  public static final String SPACE = " ";
  public static final String NO_STRING = "";
  public static final Integer CHATROOM_SIZE = 3;
  public static final int NINETEEN = 19;
  public static final int TWENTY_FOUR = 24;
  public static final Integer FIVE = 5;
  public static final int TWENTY_FIVE = 25;
  public static final Integer FOUR = 4;
  public static final Integer SEVEN = 7;
  public static final int TWENTY_SEVEN = 27;
  public static final String DATE_FORMAT = "dd/MM/yy HH:mm:ss";
  public static final String SERVER_WAITING_MESSAGE = " Server waiting on port ";
  public static final int TWENTY_THREE_INT = 23;
  public static final int TWENTY_SIX_INT = 26;
  public static final int TWENTY_EIGHT_INT = 28;
  public static final String NEW_CLIENT_MESSAGE = "New user connected -> ";
  public static final String SERVER_CLOSE_MESSAGE = "User disconnecting -> ";
  public static final String USER_THREAD_ERROR = "Error in UserThread: ";
  public static final String SERVER_NOT_FOUND_MESSAGE = "Server not found: ";
  public static final String IO_MESSAGE = "I/O Error: ";
  public static final String INPUT_STREAM_ERROR_MESSAGE = "Error getting input stream: ";
  public static final String SERVER_READING_ERROR = "Error reading from server: ";
  public static final String OUTPUT_STREAM_ERROR_MESSAGE = "Error getting output stream: ";
  public static final String GET_USERNAME = "What's your username?";
  public static final String NINETEEN_STR = "19";
  public static final String ARROW = " -> ";
  public static final String MESSAGE_SENT = "Sent!";
  public static final String MESSAGE_SEND_ERROR = "Something went wrong!";
  public static final Integer SIX = 6;
  public static final Integer THREE = 3;
  public static final String COMMA = " ,";
  public static final String CONNECTED_USERS = "Connected users are: ";
  public static final String AT_USER = "@";
  public static final String AT_ALL = "@all";
  public static final String TWENTY_FOUR_STRING = "24";
  public static final String TWENTY_FIVE_STRING = "25";
  public static final String EXCLAMATION = "!";
  public static final String TWENTY_SEVEN_STRING = "27";
  public static final String QUESTION_MARK = "[?]";
  public static final String LOG_OFF = "logoff";
  public static final String WHO = "who";

  /**
   * The constant protocolMapping.
   */
  public static Map<String, Integer> protocolMapping = new HashMap<>();
  /**
   * The constant COMMANDS.
   */
  public static final String COMMANDS = "Below is the set of commands you can use\n\n"
      + "@user -> sends direct message to specified user\n" +
          "@all -> broadcasts message to all users in chatroom\n" +
          "who -> lists all users in the chatroom\n" +
          "!user -> sends an insult to specified user\n" +
          "logoff -> disconnects user from chatroom\n";
  /**
   * The constant INVALID_INPUT.
   */
   public static final String INVALID_INPUT = "Invalid input, try again!\n"
      + "Press '?' for help.";

  public static final String GRAMMARS_DIRECTORY = "Grammars";

  public static final String GRAMMARS_FILE = "insult_grammar.json";



  static{
      protocolMapping.put("logoff", TWENTY_ONE);
      protocolMapping.put("who", TWENTY_TWO);
    protocolMapping.put("@all",TWENTY_FOUR);
      protocolMapping.put("!",TWENTY_SEVEN);
    }
}
