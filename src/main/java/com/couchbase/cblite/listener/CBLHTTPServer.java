package com.couchbase.cblite.listener;

import com.couchbase.cblite.CBLManager;

import java.util.Properties;

import Acme.Serve.Serve;

@SuppressWarnings("serial")
public class CBLHTTPServer extends Serve {

    public static final String CBLServer_KEY = "CBLServerInternal";
    public static final String CBL_URI_SCHEME = "cblite://";

    private Properties props;
    private CBLManager manager;
    private CBLListener listener;

    public CBLHTTPServer() {
        props = new Properties();
    }

    public void setManager(CBLManager manager) {
        this.manager = manager;
    }

    public void setListener(CBLListener listener) {
        this.listener = listener;
    }

    public void setPort(int port) {
        props.put("port", port);
    }

    @Override
    public int serve() {
        //pass our custom properties in
        this.arguments = props;

        //pass in the CBLServerInternal to the servlet
        CBLHTTPServlet servlet = new CBLHTTPServlet();
        servlet.setManager(manager);
        servlet.setListener(listener);

        this.addServlet("/", servlet);
        return super.serve();
    }

}
