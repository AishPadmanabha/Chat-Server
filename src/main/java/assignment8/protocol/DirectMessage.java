package assignment8.protocol;

import assignment8.ConstantUtils;

/**
 * The type Direct message.
 */
public class DirectMessage extends Protocol {

    @Override
    public String process(final String... args) {
        return args[ConstantUtils.ZERO] + ConstantUtils.SPACE + args[ConstantUtils.ONE].length() + ConstantUtils.SPACE
                + args[ConstantUtils.ONE] + ConstantUtils.SPACE + args[ConstantUtils.TWO].length()
                + ConstantUtils.SPACE + args[ConstantUtils.TWO] + ConstantUtils.SPACE + args[ConstantUtils.THREE].length() + ConstantUtils.SPACE + args[ConstantUtils.THREE];
    }
}
