package assignment8.protocol;

import assignment8.ConstantUtils;

/**
 * The type Send insult.
 */
public class SendInsult extends Protocol {

    @Override
    public String process(final String... args) {
        return args[ConstantUtils.ZERO] + ConstantUtils.SPACE + args[ConstantUtils.ONE].length()
                + ConstantUtils.SPACE + args[ConstantUtils.ONE] + ConstantUtils.SPACE + args[ConstantUtils.TWO].length()
                + ConstantUtils.SPACE + args[ConstantUtils.TWO];
    }
}
