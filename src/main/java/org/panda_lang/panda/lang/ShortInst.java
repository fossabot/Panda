package org.panda_lang.panda.lang;

import org.panda_lang.panda.core.parser.essential.util.NumberType;
import org.panda_lang.panda.core.parser.essential.util.Numeric;
import org.panda_lang.panda.core.statement.Structure;

public class ShortInst extends Numeric {

    static {
        Structure structure = new Structure("Short");
        structure.group("panda.lang");
    }

    private final short s;

    public ShortInst(short s) {
        this.s = s;
    }

    @Override
    public short getShort() {
        return s;
    }

    @Override
    public Number getNumber() {
        return getShort();
    }

    @Override
    public NumberType getNumberType() {
        return NumberType.SHORT;
    }

    @Override
    public Object getJavaValue() {
        return getShort();
    }

}