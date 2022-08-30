package com.practice.scooterrentalspringapplication.filter.wrapper;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ServletInputStreamWrapper extends ServletInputStream {
    private InputStream inputStream;

    public ServletInputStreamWrapper(byte[] body)
    {
        this.inputStream = new ByteArrayInputStream(body);
    }

    @Override
    public boolean isFinished() {
        try
        {
            return inputStream.available() == 0;
        }
        catch (Exception ex)
        {
            return false;
        }
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void setReadListener(ReadListener readListener) {

    }

    @Override
    public int read() throws IOException {
        return this.inputStream.read();
    }
}
