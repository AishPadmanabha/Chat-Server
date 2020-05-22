package assignment8.server;

import assignment7.ExpressionParser;
import assignment7.Grammar;
import assignment7.JSONInputParser;
import assignment8.ByteStringParserUtils;
import assignment8.ConstantUtils;
import assignment8.ParserUtils;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The Server handles all: - data manipulation - accepting client socket requests - creation of user
 * threads. - maintains a list of users and their threads
 */
public class Server {

    private static Vector<UserThread> userThreads = new Vector<>();
    private transient Vector<String> userNames = new Vector<>();
    private transient Map<String, UserThread> users = new HashMap<>();
    private static Random rand = new Random();
    private static Grammar grammar = new Grammar();

    private transient Integer port;

    public Server(final Integer port) {
        this.port = port;
        grammar = JSONInputParser.dataReader(grammar, new File(ConstantUtils.GRAMMARS_DIRECTORY +
                File.separator + ConstantUtils.GRAMMARS_FILE));
    }

    private void run() {

        final DateFormat dateFormat = new SimpleDateFormat(ConstantUtils.DATE_FORMAT);
        final Calendar calendar = Calendar.getInstance();
        System.out.println(
                dateFormat.format(calendar.getTime()) + ConstantUtils.SERVER_WAITING_MESSAGE + port);
        try {
            final ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                final Socket socket = serverSocket.accept();

                final UserThread clientThread = new UserThread(socket, this);
                userThreads.add(clientThread);
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Broadcast message to clients.
     *
     * @param message the message
     * @param sender  the sender
     */
    public void broadcast(final String message, final UserThread sender) {
        try {
            for (final UserThread client : userThreads) {
                if (client != sender && users.containsValue(client)) {
                    client.outputMessage(message);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Peer to peer.
     *
     * @param message  the message
     * @param receiver the receiver
     */
    private void peerToPeer(final String message, final String receiver) {
        try {
            for (final UserThread client : userThreads) {
                if (users.get(receiver) == client && users.containsValue(client)) {
                    client.outputMessage(message);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Peer to peer.
     *
     * @param message  the message
     * @param receiver the receiver
     */
    void peerToPeer(final String message, final UserThread receiver) {
        try {
            for (final UserThread client : userThreads) {
                if (receiver.equals(client)) {
                    client.outputMessage(message);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Protocol response byte [ ] returns the response of protocols which are then sent to the client
     * with the help of user thread.
     *
     * @param requestByte the request byte
     * @param userThread  the user thread
     * @return the byte [ ]
     */
    byte[] protocolResponse(final byte[] requestByte, final UserThread userThread) {
        try {
            final String[] request = ByteStringParserUtils.byteToString(requestByte)
                    .split(ConstantUtils.SPACE, ConstantUtils.FIVE);
            final int identifier = Integer.parseInt(request[ConstantUtils.ZERO]);
            Boolean success = Boolean.FALSE;

            if (identifier == ConstantUtils.NINETEEN) {
                if (userNames.size() < ConstantUtils.CHATROOM_SIZE) {
                    System.out.println(ConstantUtils.NEW_USER_MESSAGE);
                    success = Boolean.TRUE;
                    userNames.add(request[ConstantUtils.TWO]);
                    users.put(request[ConstantUtils.TWO], userThread);
                }
                return ByteStringParserUtils.stringToByte(ParserUtils.getInstance(ConstantUtils.TWENTY,
                        success.toString(), Integer.toString(userNames.size())));
            } else if (identifier == ConstantUtils.TWENTY_ONE) {
                userNames.remove(request[ConstantUtils.TWO]);
                users.remove(request[ConstantUtils.TWO]);
                return ByteStringParserUtils.stringToByte(
                        ParserUtils.getInstance(ConstantUtils.TWENTY_EIGHT, ConstantUtils.DISCONNECT_MESSAGE));
            } else if (identifier == ConstantUtils.TWENTY_TWO) {
                final StringBuilder buffer = new StringBuilder(ConstantUtils.NO_STRING);
                for (final String client : userNames) {
                    buffer.append(ConstantUtils.SPACE).append(client.length()).append(ConstantUtils.SPACE)
                            .append(client);
                }
                return ByteStringParserUtils.stringToByte(ParserUtils
                        .getInstance(ConstantUtils.TWENTY_THREE, Integer.toString(userNames.size()),
                                buffer.toString()));
            } else if (identifier == ConstantUtils.TWENTY_FOUR) {
                final String message = request[ConstantUtils.TWO] + ConstantUtils.ARROW + request[ConstantUtils.FOUR];
                broadcast(message, userThread);
            } else if (identifier == ConstantUtils.TWENTY_FIVE) {
                final String[] special = ByteStringParserUtils.byteToString(requestByte)
                        .split(ConstantUtils.SPACE, ConstantUtils.SEVEN);
                if (userNames.contains(special[ConstantUtils.FOUR])) {
                    final String message = special[ConstantUtils.TWO] + ConstantUtils.ARROW + special[ConstantUtils.SIX];
                    peerToPeer(message, special[ConstantUtils.FOUR]);
                } else {
                    return ByteStringParserUtils.stringToByte(ParserUtils
                            .getInstance(ConstantUtils.TWENTY_SIX, ConstantUtils.NO_USER));
                }
            } else if (identifier == ConstantUtils.TWENTY_SEVEN) {
                if (userNames.contains(request[ConstantUtils.FOUR])) {
                    final String insult =
                            request[ConstantUtils.TWO] + ConstantUtils.ARROW + ExpressionParser.sentenceGenerator(grammar.getSentence()
                                    .get(rand.nextInt(grammar.getSentence().size())), grammar.getTags());
                    peerToPeer(insult, request[ConstantUtils.FOUR]);
                } else {
                    return ByteStringParserUtils.stringToByte(ParserUtils
                            .getInstance(ConstantUtils.TWENTY_SIX, ConstantUtils.NO_USER));
                }
            }
            return ByteStringParserUtils.stringToByte(ConstantUtils.MESSAGE_SENT);
        } catch (Exception e) {
            return ByteStringParserUtils.stringToByte(ConstantUtils.MESSAGE_SEND_ERROR);
        }

    }


    /**
     * Server starts here.
     *
     * @param args -> port
     */
    public static void main(final String... args) {
        final Integer port = Integer.parseInt(args[ConstantUtils.ZERO]);
        final Server server = new Server(port);
        server.run();
    }
}