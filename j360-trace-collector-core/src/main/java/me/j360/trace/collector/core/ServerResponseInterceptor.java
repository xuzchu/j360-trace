package me.j360.trace.collector.core;

import java.util.logging.Logger;

import static me.j360.trace.collector.core.internal.Util.checkNotNull;


/**
 * Contains logic for dealing with response being returned at server side.
 *
 * - Add custom annotations if adapter provides them.
 * - Will submit server send annotation.
 *
 */
public class ServerResponseInterceptor {

    private final static Logger LOGGER = Logger.getLogger(ServerResponseInterceptor.class.getName());

    private final ServerTracer serverTracer;

    public ServerResponseInterceptor(ServerTracer serverTracer) {
        this.serverTracer = checkNotNull(serverTracer, "Null serverTracer");
    }

    public void handle(ServerResponseAdapter adapter) {
        // We can submit this in any case. When server state is not set or
        // we should not trace this request nothing will happen.
        LOGGER.fine("Sending server send.");
        try {
            for(KeyValueAnnotation annotation : adapter.responseAnnotations())
            {
                serverTracer.submitBinaryAnnotation(annotation.getKey(), annotation.getValue());
            }
            serverTracer.setServerSend();
        } finally {
            serverTracer.clearCurrentSpan();
        }
    }
}
