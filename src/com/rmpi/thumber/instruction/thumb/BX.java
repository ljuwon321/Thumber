package com.rmpi.thumber.instruction.thumb;

import com.rmpi.thumber.format.BitFilter;
import com.rmpi.thumber.format.Width;

public class BX extends ThumbInstruction {
    public byte register = 14;

    @Override
    public void assemble() {
        bits = getBitFilter().toInteger()
                | (register & 0xF) << 3;
    }

    @Override
    public void disassemble() {
        register = (byte) ((bits & 0x0078) >>> 3);
    }

    @Override
    public BitFilter getBitFilter() {
        return new BitFilter("010001110XXXX000");
    }

    @Override
    public boolean isValid() {
        return super.isValid()
                && register < 0x10;
    }

    @Override
    public boolean isUnpredictable(boolean isInITBlock, boolean isLastInITBlock) {
        return isInITBlock && !isLastInITBlock;
    }

    @Override
    public Width getWidth() {
        return Width.NARROW;
    }
}
