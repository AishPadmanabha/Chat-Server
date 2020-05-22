package assignment8.protocol;

import assignment8.ConstantUtils;

import java.security.CodeSigner;

/**
 * The type Connect response.
 */
public class ConnectResponse extends Protocol {

    @Override
    public String process(final String... args) {
        if (Boolean.parseBoolean(args[1])) {
            return args[ConstantUtils.ZERO] + ConstantUtils.SPACE + Boolean.TRUE + ConstantUtils.SPACE
                    + ConstantUtils.CONNECT_SUCCESS.length() + args[ConstantUtils.TWO].length() + ConstantUtils.SPACE
                    + ConstantUtils.CONNECT_SUCCESS + args[ConstantUtils.TWO];
        }
        return args[ConstantUtils.ZERO] + ConstantUtils.SPACE + Boolean.FALSE + ConstantUtils.SPACE
                + ConstantUtils.CONNECT_FAIL.length() + ConstantUtils.SPACE + ConstantUtils.CONNECT_FAIL;

    }
}
