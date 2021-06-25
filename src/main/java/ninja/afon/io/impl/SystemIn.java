package ninja.afon.io.impl;

import ninja.afon.io.IStdIn;

import java.io.InputStream;

public class SystemIn implements IStdIn {
    @Override
    public InputStream getInputStream() {
        return System.in;
    }
}
