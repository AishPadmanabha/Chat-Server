package assignment8.protocol;

import assignment8.ConstantUtils;

/**
 * The type Query response.
 */
public class QueryResponse extends Protocol {

    @Override
    public String process(final String... args) {
        return args[ConstantUtils.ZERO] + ConstantUtils.SPACE + args[ConstantUtils.ONE] + args[ConstantUtils.TWO];
    }
}
