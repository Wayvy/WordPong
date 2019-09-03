package org.spacewave.wordpong.infrastructure;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Connection {
    private InputStream inputStream;
    private Scanner reader;
    private OutputStream putputStream;
    private PrintWriter writer;
    private Socket nemesis;
    private int port;
    private String address;
    private String nameHost;
    private String nameClient;



    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Scanner getReader() {
        return reader;
    }

    public void setReader(Scanner reader) {
        this.reader = reader;
    }

    public OutputStream getPutputStream() {
        return putputStream;
    }

    public void setPutputStream(OutputStream putputStream) {
        this.putputStream = putputStream;
    }

    public PrintWriter getWriter() {
        return writer;
    }

    public void setWriter(PrintWriter writer) {
        this.writer = writer;
    }

    public Socket getNemesis() {
        return nemesis;
    }

    public void setNemesis(Socket nemesis) {
        this.nemesis = nemesis;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNameHost() {
        return nameHost;
    }

    public void setNameHost(String nameHost) {
        this.nameHost = nameHost;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }
}
