package org.kelf54.model.writer;

import java.io.IOException;

public class WriterInteger extends WriterData {
    public WriterInteger(String patch, boolean isAppendToFile) throws IOException {
        super(patch, "integer", isAppendToFile);
    }
}
