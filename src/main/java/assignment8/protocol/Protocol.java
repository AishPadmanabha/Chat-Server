package assignment8.protocol;

import assignment8.ConstantUtils;

/**
 * The type Protocol.
 */
public class Protocol {

    /**
     * Process string.
     *
     * @param args the args
     * @return the string
     */
    public String process(final String...args){
    return args[ConstantUtils.ZERO]+ConstantUtils.SPACE+args[ConstantUtils.ONE].length()+ConstantUtils.SPACE+args[ConstantUtils.ONE];
  }

}
