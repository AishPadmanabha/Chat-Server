package assignment8;

import assignment8.exceptions.InvalidIdentifierException;
import assignment8.protocol.*;

/**
 * The type Parser.
 */
public class ParserUtils {

    /**
     * Gets instance.
     *
     * @param args the args
     * @return the instance
     * @throws InvalidIdentifierException the invalid identifier exception
     */
    public static String getInstance(final String...args) throws InvalidIdentifierException {

        switch (Integer.parseInt(args[ConstantUtils.ZERO])) {
            case ConstantUtils.NINETEEN:
                return new ConnectMessage().process(args);
            case ConstantUtils.TWENTY_INT:
                return new ConnectResponse().process(args);
            case ConstantUtils.TWENTY_ONE:
                return new DisconnectMessage().process(args);
            case ConstantUtils.TWENTY_TWO:
                return new QueryUser().process(args);
            case ConstantUtils.TWENTY_THREE_INT:
                return new QueryResponse().process(args);
            case ConstantUtils.TWENTY_FOUR:
                return new BroadcastMessage().process(args);
            case ConstantUtils.TWENTY_FIVE:
                return new DirectMessage().process(args);
            case ConstantUtils.TWENTY_SIX_INT:
                return new FailedMessage().process(args);
            case ConstantUtils.TWENTY_SEVEN:
                return new SendInsult().process(args);
            case ConstantUtils.TWENTY_EIGHT_INT:
                return new DisconnectResponse().process(args);
            default:
                throw new InvalidIdentifierException(ConstantUtils.NO_STRING);
        }
    }
}
